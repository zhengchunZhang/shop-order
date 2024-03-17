package cn.wolfcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class OrderServer {
    public static void main(String[] args) {

        SpringApplication.run(OrderServer.class,args);
    }
    @Bean
    @LoadBalanced//表示继承ribbon进行负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
