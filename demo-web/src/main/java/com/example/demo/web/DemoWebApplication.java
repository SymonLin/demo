package com.example.demo.web;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

/**
 * @author linjian
 * @date 2019/1/15
 */
@SpringBootApplication(scanBasePackages = "com.example.demo")
@DubboComponentScan(basePackages = "com.example.demo.biz.service.impl.remote")
@MapperScan("com.example.demo.dao.mapper")
@ImportResource({"classpath:application-context.xml"})
public class DemoWebApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoWebApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoWebApplication.class, args);
    }
}
