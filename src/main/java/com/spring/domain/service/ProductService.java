package com.spring.domain.service;

import com.spring.adapters.vo.request.ProductRequestVO;
import com.spring.domain.entity.Product;

import java.util.List;

public interface ProductService {

    Product create(ProductRequestVO request);
    List<Product> getProductsByIdList(List<String>ids);

}
