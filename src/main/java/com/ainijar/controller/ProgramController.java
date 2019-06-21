package com.ainijar.controller;

import com.ainijar.common.utils.PageUtils;
import com.ainijar.common.utils.Result;
import com.ainijar.entity.Dept;
import com.ainijar.entity.Judges;
import com.ainijar.entity.Program;
import com.ainijar.entity.ProgramJudges;
import com.ainijar.service.*;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("program")
@RestController
public class ProgramController {

    @Autowired
    private IProgramService iProgramService;

    @Autowired
    private IJudgesService iJudgesService;

    @Autowired
    private IDeptService iDeptService;

    @Autowired
    private IProgramJudgesService iProgramJudgesService;

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
        Dept dept = iDeptService.getById(program.getDeptId());
        program.setDeptName(dept.getName());
        List<Judges> judgesList = iJudgesService.findList(id);
        Map map = new HashMap(16);
        map.put("cmd","init");
        map.put("program",program);
        map.put("judgesList",judgesList);
        // 推送基本信息到大屏
        WebSocketServer.sendInfo(JSONObject.toJSONString(map));
        return Result.ok().put("program", program).put("judgesList", judgesList);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody Program program){
        iProgramService.save(program);
        return Result.ok();
    }

    /**
     * 打分
     */
    @RequestMapping("/grade")
    public Result grade(@RequestBody Program program){
        ProgramJudges programJudges = new ProgramJudges();
        programJudges.setJudgesId(program.getJudgesId());
        programJudges.setProgramId(program.getId());
        programJudges.setScore(program.getJudgesScore());
        ProgramJudges db = iProgramJudgesService.getOne(new QueryWrapper<ProgramJudges>().eq("program_id",program.getId()).eq("judges_id",program.getJudgesId()));
        if (db != null){
            db.setScore(program.getJudgesScore());
        } else {
            db = programJudges;
        }
        iProgramJudgesService.saveOrUpdate(db);
        // 推动分数到大屏
        Map map = new HashMap(16);
        map.put("cmd","score");
        map.put("score",program.getJudgesScore());
        map.put("judgesId",program.getJudgesId());
        WebSocketServer.sendInfo(JSONObject.toJSONString(map));
        return Result.ok();
    }

    /**
     * 总得分
     */
    @RequestMapping("/finalScore/{id}")
    public Result finalScore(@PathVariable("id") Long id){
        Program program = iProgramService.getById(id);
        List<Judges> judgesList = iJudgesService.findList(id);

        List<Integer> scores=judgesList.stream().map(Judges::getJudgesScore).collect(Collectors.toList());
//        list.sort(Comparator.comparing(Judges::getJudgesScore).reversed());
        Collections.sort(scores);
        scores.remove(scores.size()-1);
        scores.remove(0);
        Integer sum = scores.stream().reduce(Integer::sum).orElse(0);
        float finalScore= (float)sum/scores.size();

        Map map = new HashMap(16);
        map.put("cmd","finalScore");
        map.put("finalScore",finalScore);
        // 推送总得分到大屏
        WebSocketServer.sendInfo(JSONObject.toJSONString(map));

        program.setScore(finalScore);
        iProgramService.updateById(program);

        return Result.ok().put("program", program);
    }

}
