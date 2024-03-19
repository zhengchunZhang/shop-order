package cn.wolfcode.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
@RestController
@Slf4j
public class SentinelController {
    @RequestMapping("/sentinel1")
    public String sentinel1(){
        //模拟⼀次⽹络延时
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "sentinel1";
    }
    @SentinelResource
    @RequestMapping("/sentinel2")
    public String sentinel2(){
        return "sentinel2";
    }

    @RequestMapping("/sentinel3")
    public String sentinel3(){
        return "sentinel3";
    }

    int i=0;
    @RequestMapping("/fallBack2")
    public String fallBack2(){
        log.info("fallBack2执⾏业务逻辑");
        //模拟出现异常，异常⽐例为33%
        if(++i%3==0){
            throw new RuntimeException();
        }
        return "fallBack2";
    }
}
