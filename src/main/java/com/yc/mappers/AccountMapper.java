package com.yc.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yc.bean.Accounts;
import org.apache.ibatis.annotations.Mapper;

public interface AccountMapper extends BaseMapper<Accounts> {
    /*
    BaseMapper 是 MyBatis-Plus 提供的一个基础接口，包含了常用的 CRUD（创建、读取、更新、删除）操作方法
     */
}
