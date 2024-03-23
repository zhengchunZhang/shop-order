package cn.wolfcode.filter;


import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
public class TimeGatewayFilterFactory extends AbstractGatewayFilterFactory<TimeGatewayFilterFactory.Config> {
    private static final String BEGIN_TIME = "beginTime";
    //构造函数
    public TimeGatewayFilterFactory() {
        super(Config.class);
    }
    //读取配置⽂件中的参数 赋值到 配置类中
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("show","xx","yy");
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                //前置逻辑
                System.out.println("前置逻辑");
                if(!config.show) {
                    return chain.filter(exchange);
                }
                exchange.getAttributes().put(BEGIN_TIME,
                        System.currentTimeMillis());
                return chain.filter(exchange).then(Mono.fromRunnable(()->{
                    Long startTime = exchange.getAttribute(BEGIN_TIME);
                    if (startTime != null) {
                        System.out.println(exchange.getRequest().getURI() +
                                "请求耗时: " + (System.currentTimeMillis() - startTime) + "ms");}
                    //后置的逻辑
                    System.out.println("后置逻辑");
                }));
            }
        };
    }

    @Setter
    @Getter
    static class Config{
        private boolean show;
        private Long xx;
        private String yy;
    }
}
