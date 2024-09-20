package com.yc.mappers.mp;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    // 插入时自动填充  数据库中的时间字段
    @Override
    public void insertFill(MetaObject metaObject) {
        //  1.插入时自动填充
        strictFillStrategy( metaObject , "optime",()->{
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df.format(date);
        });
         //  2. 插入时自动填充                String       LocalDateTime类型  类型不一致
//        strictFillStrategy(metaObject,"optime", LocalDateTime::now);
    }

    // 更新时自动填充
    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
