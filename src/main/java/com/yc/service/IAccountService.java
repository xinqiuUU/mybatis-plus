package com.yc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.bean.Accounts;

public interface IAccountService {
    // 分页查询
    Page<Accounts> getUserPage(int pageNum, int pageSize);
    // 开户
    public Accounts openAccount(double balance);

    // 存款
    public Accounts deposit(int accountid , double money);

    // 取款
    public Accounts withdraw(int accountid, double money);

    // 转账
    public Accounts transfer(int fromid,  double money ,int toid);

    // 查询
    public Accounts findAccount(int accountid);
}
