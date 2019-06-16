package com.ainijar.controller;

import com.ainijar.service.WebSocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping
public class TestController {

    @RequestMapping(value = "/test")
    public String test() {
        return "test1";
    }

    @RequestMapping(value = "/pushVideoListToWeb", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    Map<String, Object> pushVideoListToWeb(@RequestBody Map<String, Object> param) {
        Map<String, Object> result = new HashMap<String, Object>();

        WebSocketServer.sendInfo("有新客户呼入,sltAccountId:" + param.get("sltAccountId"));
        result.put("operationResult", true);
        return result;
    }
}
