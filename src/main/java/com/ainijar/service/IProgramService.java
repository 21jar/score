package com.ainijar.service;

import com.ainijar.entity.Program;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ainijar.common.utils.PageUtils;

import java.util.Map;

/**
 * 数据字典
 */
public interface IProgramService extends IService<Program> {

    PageUtils queryPage(Map<String, Object> params);
}

