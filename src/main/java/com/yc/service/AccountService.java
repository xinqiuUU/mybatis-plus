package com.yc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yc.bean.Accounts;
import com.yc.bean.OpRecord;
import com.yc.bean.OpType;
import com.yc.mappers.AccountMapper;
import com.yc.mappers.OpRecordMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Transactional // 事务管理  Spring AOP 的代理模式  事务传播
@Service
@Slf4j
public class AccountService extends ServiceImpl<AccountMapper, Accounts> implements IAccountService{

    @Autowired
    private OpRecordService op;

    // 缓存管理器
    @Autowired
    private CacheManager cacheManager;

    // 这里可以添加自定义的业务方法
   // 分页查询  pageNum（当前页码）和 pageSize（每页记录数）
    @Override
    public Page<Accounts> getUserPage(int pageNum, int pageSize) {
        Page<Accounts> page = new Page<>(pageNum, pageSize);
        return this.page(page);
    }

    // 开户
    @Override
    public Accounts openAccount(double balance) {
        // 创建新的账户对象
        Accounts a = new Accounts();
        // 设置账户余额
        a.setBalance(balance);
        a.setEmail("2921310632@qq.com");
        // 保存账户到数据库
        this.save(a);
        // 获取自动递增的 accountId
        int accountId = a.getAccountid();
        // 流水记录
        OpRecord record = new OpRecord();
        record.setAccountid(accountId);
        record.setOpmoney(a.getBalance());
        record.setOptype(OpType.DEPOSIT);
        op.save(record);
        // 返回新创建的账户对象
        return a;
    }
    // 存款
    @CachePut(cacheNames = "account",key = "#accountid")
    @Override
    public Accounts deposit(int accountid, double money) {
        // 根据 accountid 查询账户
        Accounts account = this.findAccount(accountid);
        // 检查账户是否存在
        if (account!= null) {
            // 计算新的余额
            double newBalance = account.getBalance() + money;
            // 更新账户余额
            account.setBalance(newBalance);
            this.updateById(account);
            // 流水记录
            OpRecord record = new OpRecord();
            record.setAccountid(accountid);
            record.setOpmoney(money);
            record.setOptype(OpType.DEPOSIT);
            op.save(record);
            // 返回更新后的账户对象
            return account;
        }else {
            throw new RuntimeException("账户不存在");
        }
    }
    // 取款
    @CachePut(cacheNames = "account",key = "#accountid")
    @Override
    public Accounts withdraw(int accountid, double money) {
        // 根据 accountid 查询账户
        Accounts account = this.findAccount(accountid);
        // 检查账户是否存在
        if (account!= null) {
            // 计算新的余额
            double newBalance = account.getBalance() - money;
            // 更新账户余额
            account.setBalance(newBalance);
            this.updateById(account);
            //2.流水记录
            OpRecord record = new OpRecord();
            record.setAccountid(accountid);
            record.setOpmoney(money);
            record.setOptype(OpType.WITHDRAW);
            op.save( record );
            // 返回更新后的账户对象
            return account;
        }else {
            throw new RuntimeException("账户不存在");
        }
    }
    // 转账
    @Override
    public Accounts transfer(int accountid, double money, int toid) {
        // 从 fromid 账户中取款
        Accounts fromaccount = this.withdraw(accountid, money);
        // 向 toid 账户中存款
        Accounts toaccount = this.deposit(toid, money);
        // 返回转账后的账户对象
        if (fromaccount!= null && toaccount!= null) {
            // 流水记录
            OpRecord record = new OpRecord();
            record.setAccountid(accountid);
            record.setOpmoney(money);
            record.setOptype(OpType.TRANSFER);
            record.setTransferid(toid);
            op.save(record);

            // 手动更新缓存
            cacheManager.getCache("account").put(accountid, fromaccount);
            cacheManager.getCache("account").put(toid, toaccount);

            return fromaccount;
        }else {
            throw new RuntimeException("账户不存在");
        }
    }
    // 查找账户
    @Cacheable(cacheNames = "account",key = "#accountid")  // cacheNames缓存的名称    key  缓存的键
    @Override
    public Accounts findAccount(int accountid) {
        // 使用 BaseMapper 提供的 selectById 方法查找账户
        Accounts account = this.getById(accountid);
        return account;
    }


}
