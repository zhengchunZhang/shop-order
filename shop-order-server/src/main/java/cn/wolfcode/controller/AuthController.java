package cn.wolfcode.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthController {
    @RequestMapping("/auth1")
    public String auth1(String type){
        log.info("应⽤:{},访问接⼝",type);
        return "auth1";
    }
}
