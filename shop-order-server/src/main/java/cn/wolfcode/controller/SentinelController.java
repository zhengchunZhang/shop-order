package cn.wolfcode.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
@RestController
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
        return "测试⾼并发下的问题";
    }
}
