package com.yc.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yc.bean.OpRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OpRecordMapper extends BaseMapper<OpRecord> {

    @Select("SELECT * FROM oprecord WHERE accountid = #{accountid}")
    List<OpRecord> findByAccountid(int accountid);

    @Delete("DELETE FROM oprecord WHERE accountid = #{accountid}")
    Long deleteByAccountid(int accountid);

    @Delete("DELETE FROM oprecord WHERE id = #{id}")
    Long deleteById(int id);

}
