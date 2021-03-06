package com.ainijar.dao;

import com.ainijar.entity.Judges;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface JudgesDao extends BaseMapper<Judges> {

    @Select("select j.*,pj.score AS judgesScore from judges j left join program_judges pj on j.id=pj.judges_id and pj.program_id=#{programId} order by order_num")
    List<Judges> findList(Long programId);
}
