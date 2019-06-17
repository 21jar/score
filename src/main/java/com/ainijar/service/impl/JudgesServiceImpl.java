package com.ainijar.service.impl;

import com.ainijar.common.utils.PageUtils;
import com.ainijar.common.utils.Query;
import com.ainijar.dao.JudgesDao;
import com.ainijar.dao.ProgramDao;
import com.ainijar.entity.Judges;
import com.ainijar.entity.Program;
import com.ainijar.service.IJudgesService;
import com.ainijar.service.IProgramService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class JudgesServiceImpl extends ServiceImpl<JudgesDao, Judges> implements IJudgesService {

    @Autowired
    private JudgesDao judgesDao;

}
