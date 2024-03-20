package cn.wolfcode.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AnnoController {
    @RequestMapping("/anno1")
    @SentinelResource(value = "anno1",
            blockHandler="anno1BlockHandler", //接⼝被限流或者降级了会调用这个方法
            fallback = "anno1Fallback"//接口出错误也会调用fallback方法
    )
    public String anno1(String name){
        if("wolfcode".equals(name)){
            throw new RuntimeException();
        }
        return "anno1";
    }
    public String anno1BlockHandler(String name, BlockException ex){
        log.error("{}", ex);
        return "接⼝被限流或者降级了";
    }
    //Throwable时进⼊的⽅法
    public String anno1Fallback(String name,Throwable throwable) {
        log.error("{}", throwable);
        return "接⼝发⽣异常了";
    }
}
