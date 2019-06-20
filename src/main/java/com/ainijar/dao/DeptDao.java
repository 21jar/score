package com.ainijar.dao;

import com.ainijar.entity.Dept;
import com.ainijar.entity.Judges;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DeptDao extends BaseMapper<Dept> {
}
