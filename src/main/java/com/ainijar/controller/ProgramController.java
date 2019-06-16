package com.ainijar.controller;

import com.ainijar.common.utils.PageUtils;
import com.ainijar.common.utils.Result;
import com.ainijar.entity.Program;
import com.ainijar.service.IProgramService;
import com.ainijar.service.WebSocketServer;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequestMapping("program")
@RestController
public class ProgramController {

    @Autowired
    private IProgramService iProgramService;

    @Autowired
    private WebSocketServer webSocketServer;
    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = iProgramService.queryPage(params);
        return Result.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id){
        Program program = iProgramService.getById(id);
        return Result.ok().put("program", program);
    }

    /**
     * 修改
     */
    @RequestMapping("/grade")
    public Result grade(@RequestBody Program program){
//        iProgramService.updateById(program);
        WebSocketServer.sendInfo(JSONObject.toJSONString(program));
        return Result.ok();
    }

}
