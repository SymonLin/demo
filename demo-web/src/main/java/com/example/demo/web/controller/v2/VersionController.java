package com.example.demo.web.controller.v2;

import com.example.demo.web.annotation.ApiVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linjian
 * @date 2021/2/9
 */
@ApiVersion(2)
@RestController("VersionController-v2")
@RequestMapping("/{api_version}")
public class VersionController {

    @GetMapping("/version")
    public String getVersion() {
        return "v2";
    }
}
