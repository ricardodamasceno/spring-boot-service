package com.spring.domain.repository;

import com.spring.domain.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {

    Optional<List<Product>> findProductByIdIn(List<String>ids);

}
