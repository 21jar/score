package com.ainijar.dao;

import com.ainijar.entity.ProgramJudges;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProgramJudgesDao extends BaseMapper<ProgramJudges> {
}
