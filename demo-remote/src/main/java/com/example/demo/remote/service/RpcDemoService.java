package com.example.demo.remote.service;

import com.example.demo.common.entity.Result;
import com.example.demo.remote.model.param.DemoParam;
import com.example.demo.remote.model.result.DemoDTO;

/**
 * @author linjian
 * @date 2019/3/15
 */
public interface RpcDemoService {

    /**
     * Dubbo 接口测试
     *
     * @param param DemoParam
     * @return DemoDTO
     */
    Result<DemoDTO> test(DemoParam param);
}
