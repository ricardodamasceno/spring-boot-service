package com.spring.domain.repository;

import com.spring.domain.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {

    List<Product> findProductByIdIn(List<String>ids);

}
