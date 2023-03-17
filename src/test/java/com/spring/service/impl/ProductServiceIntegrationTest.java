package com.spring.service.impl;

import com.spring.adapters.vo.request.ProductRequestVO;
import com.spring.domain.entity.Product;
import com.spring.domain.enums.ProductCategory;
import com.spring.domain.repository.ProductRepository;
import com.spring.domain.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProductServiceIntegrationTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp(){
        productRepository.deleteAll();
    }

    @Test
    public void shouldCreateProductsSuccessfully(){

        Product product1 = productService.create(
                new ProductRequestVO("product 1", ProductCategory.NOTEBOOK, new BigDecimal(10)));

        Product product2 = productService.create(
                new ProductRequestVO("product 2", ProductCategory.PHONE, new BigDecimal(10)));

        assertNotNull(product1);
        assertNotNull(product2);
        assertEquals("product 1", product1.getName());
        assertEquals("product 2", product2.getName());
    }

    @Test
    public void shouldReturnProductsByIds() {

        Product product1 = productService.create(
                new ProductRequestVO("product 1", ProductCategory.NOTEBOOK, new BigDecimal(10)));

        Product product2 = productService.create(
                new ProductRequestVO("product 2", ProductCategory.PHONE, new BigDecimal(10)));

        List<Product> products = productService.getProductsByIdList(
                Arrays.asList(product1.getId(), product2.getId()));

        assertNotNull(products);
        assertEquals(2, products.size());

    }
}
