package com.yc.bean;

import lombok.Data;

@Data
public class TransferRequest {
    private int fromid;
    private double balance;
    private int toid;

}
