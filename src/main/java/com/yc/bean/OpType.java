package com.yc.bean;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum OpType {

    WITHDRAW("withdraw",1),//取款
    DEPOSIT("deposit",2),//存款
    TRANSFER("transfer",3);//转账

    @EnumValue // 标记数据库存的是  key   在这个程序是将枚举的键当成值存到了数据库中
    private String key;
    private int index;

    private OpType(String key,int index) {
        this.key = key;
        this.index = index;
    }

    public String getKey() {
        return key;
    }

    public int getIndex() {
        return index;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
