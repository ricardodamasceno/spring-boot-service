package com.spring.adapters.controller;

import com.spring.adapters.vo.request.ProductRequestVO;
import com.spring.domain.entity.Product;
import com.spring.domain.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequestVO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(request));
    }

}
