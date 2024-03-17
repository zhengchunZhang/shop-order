package cn.wolfcode.service.impl;

import cn.wolfcode.dao.OrderDao;
import cn.wolfcode.domain.Order;
import cn.wolfcode.domian.Product;
import cn.wolfcode.service.IOrderService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public Order createOrder(Long productId, Long userId) {
        log.info("接收到{}号商品的下单请求,接下来调⽤商品微服务查询此商品信息",
                productId);

        //远程调⽤商品微服务,查询商品信息
        Product product = restTemplate.getForObject(
                "http://product-service/product/"+productId,Product.class);
        log.info("查询到{}号商品的信息,内容是:{}", productId,
                JSON.toJSONString(product));
        //创建订单并保存
        Order order = new Order();
        order.setUid(userId);
        order.setUsername("叩丁狼教育");
        order.setPid(productId);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        orderDao.save(order);
        log.info("创建订单成功,订单信息为{}", JSON.toJSONString(order));
        return order;
    }
}
