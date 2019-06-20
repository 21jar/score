package com.ainijar.service.impl;

import com.ainijar.dao.DeptDao;
import com.ainijar.dao.JudgesDao;
import com.ainijar.entity.Dept;
import com.ainijar.entity.Judges;
import com.ainijar.service.IDeptService;
import com.ainijar.service.IJudgesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeptServiceImpl extends ServiceImpl<DeptDao, Dept> implements IDeptService {
}
