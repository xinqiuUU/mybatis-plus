package com.yc.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

// 账户
@Data
@TableName("accounts")
public class Accounts implements java.io.Serializable{
    @TableId(value = "accountid", type = IdType.AUTO)
    private Integer accountid;
    private Double balance;
    private String email;

}
