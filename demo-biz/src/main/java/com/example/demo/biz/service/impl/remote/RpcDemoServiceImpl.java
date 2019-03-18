package com.example.demo.biz.service.impl.remote;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.demo.biz.service.DemoService;
import com.example.demo.common.entity.Result;
import com.example.demo.remote.model.param.DemoParam;
import com.example.demo.remote.model.result.DemoDTO;
import com.example.demo.remote.service.RpcDemoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author linjian
 * @date 2019/3/15
 */
@Service
public class RpcDemoServiceImpl implements RpcDemoService {

    @Autowired
    private DemoService demoService;

    @Override
    public Result<DemoDTO> test(DemoParam param) {
        DemoDTO demo = new DemoDTO();
        demo.setStr(demoService.test(param.getId()));
        return Result.wrapSuccessfulResult(demo);
    }
}
