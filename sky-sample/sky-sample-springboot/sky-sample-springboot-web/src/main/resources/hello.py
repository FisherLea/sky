from e.job.db import select_dict
from e.job.db import DEMO_GEMINI_MALL_POOL
import datetime
import random
from tools.serial import serial_result
from e import INPUT_DIR, OUTPUT_DIR


@serial_result
def get_thirty_day_order_user_ids(min_gmt_create):
    end_time_str = min_gmt_create.strftime('%Y-%m-%d %H:%M:%S')
    start_time_str = (min_gmt_create - datetime.timedelta(days=30)).strftime('%Y-%m-%d %H:%M:%S')
    sql = '''select distinct o.user_id from orders o join orders_detail d on d.order_id=o.order_id
    where o.gmt_create >= "%s" and o.gmt_create <= "%s"
    and d.item_id not in(select item_id from item_source)''' % (start_time_str, end_time_str)
    thirty_day_orders = select_dict(DEMO_GEMINI_MALL_POOL, sql)
    user_id_set = set()
    for order in thirty_day_orders:
        user_id_set.add(order['user_id'])
    return user_id_set


def update_self_support_order():
    # 获取4-1号之后的自营订单
    self_sql = '''select distinct o.id,o.order_id,o.gmt_create from orders o join orders_detail d on d.order_id=o.order_id where
    o.gmt_create>="2020-04-01 00:00:00" and o.gmt_create<="2020-06-11 16:00:00" and o.status=12
    and d.item_id not in(select item_id from item_source) order by o.gmt_create'''
    self_support_orders = select_dict(DEMO_GEMINI_MALL_POOL, self_sql)
    min_gmt_create = self_support_orders[0]['gmt_create']
    user_id_set = get_thirty_day_order_user_ids(min_gmt_create)

    drain_sql = '''select distinct o.order_id,o.user_id,u.gmt_create,u.referer from mall.orders o join mall.orders_detail d
    on d.order_id=o.order_id join mall.item i on d.item_id=i.item_id join user_dataset.base_user_info u
    on o.user_id=u.id where o.gmt_create>="2020-04-01 00:00:00" and o.gmt_create<="2020-06-11 16:00:00"
    and o.status=12 and i.nature=1 and d.item_id in(select item_id from item_source)'''
    drain_orders = select_dict(DEMO_GEMINI_MALL_POOL, drain_sql)
    drain_order_map = {}
    for order in drain_orders:
        month = int(order['gmt_create'].strftime('%Y%m'))
        order['used'] = 0
        if user_id_set.__contains__(order['user_id']):
            order['used'] = 1
        if drain_order_map.__contains__(month):
            drain_order_map[month].append(order)
        else:
            drain_order_map[month] = [order]
    # 按照月份进行组装map
    self_support_order_map = {}
    for order in self_support_orders:
        month = int(order['gmt_create'].strftime('%Y%m'))
        if self_support_order_map.__contains__(month):
            self_support_order_map[month].append(order)
        else:
            self_support_order_map[month] = [order]
    for month in self_support_order_map:
        month_orders = self_support_order_map[month]
        # 当月的引流订单用户
        month_drain_orders = drain_order_map[month]
        non_hospital_users = []
        hospital_users = []
        for order_user in month_drain_orders:
            if order_user['referer'] is None:
                non_hospital_users.append(order_user)
            else:
                hospital_users.append(order_user)
        rate = random.uniform(0.25, 0.35)
        border_index = round(len(month_orders) * rate)
        print('随机获取比例：%s' % rate)
        non_hospital_orders = month_orders[0:border_index]
        hospital_orders = month_orders[border_index:]
        update_cmd_list = []
        gen_update_list(non_hospital_orders, non_hospital_users, update_cmd_list)
        gen_update_list(hospital_orders, hospital_users, update_cmd_list)
        with open(OUTPUT_DIR + 'order/update_order_%s.sql' % month, 'w') as f:
            f.write('\n'.join(update_cmd_list))


def gen_update_list(non_hos_or_hos_orders, order_users, update_cmd_list):
    for order in non_hos_or_hos_orders:
        # 订单用户注册时间要小于订单创建时间
        available_user_list = [user for user in order_users if user["gmt_create"] < order['gmt_create']
                               and user['used'] == 0]
        user = random.choice(available_user_list)
        # 标记已被使用
        user['used'] = 1
        update_sql = '''update orders set receiver_province=%s,receiver_city=%s,receiver_region=%s,
        receiver_address=%s,receiver_name=%s,receiver_mobile=%s,user_id=%s where id=%s;''' % (user['receiver_province'], user['receiver_city'], user['receiver_region'],
                                user['receiver_address'], user['receiver_name'], user['receiver_mobile'],
                                user['user_id'], order['id'])
        # update_cmd_list.append((user['receiver_province'], user['receiver_city'], user['receiver_region'],
        #                         user['receiver_address'], user['receiver_name'], user['receiver_mobile'],
        #                         user['user_id'], order['id']))
        update_cmd_list.append(update_sql)
