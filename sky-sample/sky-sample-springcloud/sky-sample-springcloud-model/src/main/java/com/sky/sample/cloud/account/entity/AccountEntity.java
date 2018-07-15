package com.sky.sample.cloud.account.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AccountEntity implements Serializable {

    private Long id;
    private String username;
    private BigDecimal balance;
    private String accountNo;
}
