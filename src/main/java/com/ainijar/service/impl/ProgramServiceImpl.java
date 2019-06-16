package com.ainijar.service.impl;

import com.ainijar.dao.ProgramDao;
import com.ainijar.entity.Program;
import com.ainijar.service.IProgramService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ainijar.common.utils.PageUtils;
import com.ainijar.common.utils.Query;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class ProgramServiceImpl extends ServiceImpl<ProgramDao, Program> implements IProgramService {

    @Autowired
    private ProgramDao programDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String)params.get("name");

        IPage<Program> page = programDao.findPage(
            new Query<Program>().getPage(params),
            new QueryWrapper<Program>()
                .like(StringUtils.isNotBlank(name),"a.name", name)
        );

        return new PageUtils(page);
    }

}
