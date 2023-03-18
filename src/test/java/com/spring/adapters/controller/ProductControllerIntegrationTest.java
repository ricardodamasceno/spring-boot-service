package com.spring.adapters.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.adapters.vo.request.ProductRequestVO;
import com.spring.domain.entity.Product;
import static org.junit.jupiter.api.Assertions.*;

import com.spring.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import utils.ProductMockedValues;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp(){
        productRepository.deleteAll();
    }

    @Test
    public void shouldCreateProductSuccessfully() throws Exception {

        ProductRequestVO request = ProductMockedValues.getProductRequestVO("product-test");

        MvcResult result = mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isCreated()).andReturn();

        String response = result.getResponse().getContentAsString();
        Product product = objectMapper.readValue(response, Product.class);

        assertNotNull(product);
        assertEquals(request.getName(), product.getName());
        assertEquals(request.getProductCategory(), product.getProductCategory());
        assertEquals(request.getValue(), product.getValue());

    }

    private void saveMockedProducts(){
        List<Product> products = ProductMockedValues.getMockedProductsList();
        productRepository.saveAll(products);
    }

}
