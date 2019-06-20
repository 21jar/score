package com.ainijar.service;

import com.ainijar.entity.Judges;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 数据字典
 */
public interface IJudgesService extends IService<Judges> {

    List<Judges> findList(Long programId);
}

