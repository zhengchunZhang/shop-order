package cn.wolfcode.dao;


import cn.wolfcode.domian.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product, Long> {
}
