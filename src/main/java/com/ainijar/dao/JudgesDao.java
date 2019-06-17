package com.ainijar.dao;

import com.ainijar.entity.Judges;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface JudgesDao extends BaseMapper<Judges> {


}
