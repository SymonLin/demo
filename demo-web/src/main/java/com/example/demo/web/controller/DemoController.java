package com.example.demo.web.controller;

import com.example.demo.biz.service.DemoService;
import com.example.demo.common.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linjian
 * @date 2019/1/15
 */
@Api(tags = "demo")
@RestController
@RequestMapping("demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("test")
    @ApiOperation("测试")
    public Result<String> test(@RequestParam("id") Integer id) {
        return Result.wrapSuccessfulResult(demoService.test(id));
    }
}
