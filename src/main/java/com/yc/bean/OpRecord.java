package com.yc.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

//流水记录
@Data
@TableName("oprecord")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class OpRecord {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer accountid;
    private Double opmoney;

    @TableField(value = "optime" , fill =  FieldFill.INSERT) //插入时自动填充
    private String optime;

    @TableField(value = "optype")
    private OpType optype; //枚举类型
    private Integer transferid;
}
