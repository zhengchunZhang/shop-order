package cn.wolfcode.feign.fallback;

import cn.wolfcode.domian.Product;
import cn.wolfcode.feign.ProductFeignApi;
import org.springframework.stereotype.Component;

@Component
public class ProductFeignFallback implements ProductFeignApi {

    @Override
    public Product findByPid(Long pid) {
        System.out.println("返回兜底数据");
        return new Product();
    }
}
