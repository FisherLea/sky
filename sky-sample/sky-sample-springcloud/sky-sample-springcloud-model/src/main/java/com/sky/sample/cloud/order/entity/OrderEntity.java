package com.sky.sample.cloud.order.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderEntity implements Serializable {

    private Long id;
    private String orderNo;
    private Double amount;
    private Long productId;
}
