package com.spring.adapters.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.adapters.vo.request.PurchaseRequestVO;
import com.spring.adapters.vo.response.PurchaseCreationResponseVO;
import com.spring.domain.entity.Product;
import com.spring.domain.entity.Purchase;
import com.spring.domain.entity.User;
import com.spring.domain.repository.ProductRepository;
import com.spring.domain.repository.PurchaseItemRepository;
import com.spring.domain.repository.PurchaseRepository;
import com.spring.domain.repository.UserRepository;
import static org.junit.jupiter.api.Assertions.*;

import com.spring.domain.service.UserService;
import com.spring.domain.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import utils.ProductMockedValues;
import utils.PurchaseMockedValues;
import utils.UserMockedValues;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PurchaseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PurchaseItemRepository purchaseItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp(){
        purchaseItemRepository.deleteAll();
        purchaseRepository.deleteAll();
    }

    @Test
    public void should_createPurchaseSuccessfully() throws Exception{

        PurchaseRequestVO request = new PurchaseRequestVO();
        User buyerMocked = userRepository.save(UserMockedValues.getMockedUser());

        List<Product> products = (List<Product>) productRepository.saveAll(ProductMockedValues.getMockedProductsList());

        request.setBuyerId(buyerMocked.getId());
        request.setProducts(new ArrayList<>(Arrays.asList(products.get(0).getId(), products.get(1).getId())));

        MvcResult result = mockMvc.perform(
                post("/purchase")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isCreated()).andReturn();

        String response = result.getResponse().getContentAsString();

        PurchaseCreationResponseVO purchaseResponse = objectMapper.readValue(response, PurchaseCreationResponseVO.class);

        assertNotNull(purchaseResponse);
        assertNotNull(purchaseResponse.getPurchaseId());

    }

}
