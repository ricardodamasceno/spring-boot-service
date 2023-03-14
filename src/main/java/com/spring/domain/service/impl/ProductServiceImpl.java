package com.spring.domain.service.impl;

import com.spring.adapters.vo.request.ProductRequestVO;
import com.spring.domain.entity.Product;
import com.spring.domain.exception.ProductNotFoundException;
import com.spring.domain.repository.ProductRepository;
import com.spring.domain.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public Product create(ProductRequestVO request) {
        return productRepository.save(
                new Product(
                        null,
                        request.getName(),
                        request.getProductCategory(),
                        request.getValue()
                )
        );
    }

    public List<Product> getProductsByIdList(List<String> ids) {
        return productRepository.findProductByIdIn(ids)
                .orElseThrow(() -> new ProductNotFoundException("Failed to get products by ids"));
    }

}
