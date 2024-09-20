package com.yc.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yc.bean.Accounts;
import com.yc.bean.TransferRequest;
import com.yc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService service;

    // 查询所有账户信息
    @GetMapping
    public List<Accounts> list() {
        return service.list();
    }

    // 新增账户信息
    @PostMapping
    public boolean save(@RequestBody Accounts a) {
        return service.save(a);
    }

    // 更新账户信息
    @PutMapping
    public boolean update(@RequestBody Accounts a) {
        return service.updateById(a);
    }
    // 根据accountid删除账户信息 http://localhost:8080/account/3
    @DeleteMapping("/{accountid}")
    public boolean delete(@PathVariable Long accountid) {
        return service.removeById(accountid);
    }
    //分页查询
    @GetMapping("/page") //请求头上的参数可以不用写@RequestParam 会根据参数名进行匹配注入
    public Page<Accounts> getUserPage(int pageNum, int pageSize) {
        return service.getUserPage(pageNum, pageSize);
    }
    // 开户
    @RequestMapping("/openAccount")
    public Accounts openAccount(double balance) {
        return service.openAccount(balance);
    }

    // 存款
    @PutMapping("/deposit") //请求体:@RequestBody 表示接收请求体中的 JSON 数据并将其转换为 Java 对象
    public Accounts deposit(@RequestBody Accounts a) {
        return service.deposit(a.getAccountid(), a.getBalance());
    }

    // 取款
    @PutMapping("/withdraw")
    public Accounts withdraw(@RequestBody Accounts a) {
        return service.withdraw(a.getAccountid(), a.getBalance());
    }

    // 转账
    @PutMapping("/transfer")
    public Accounts transfer(@RequestBody TransferRequest t) {
        return service.transfer(t.getFromid(), t.getBalance(), t.getToid());
    }

    // 查找账户
    @RequestMapping( value = "/findAccount", method = RequestMethod.GET)
    public Accounts findAccount(int accountid) {
        return service.findAccount(accountid);
    }
}
