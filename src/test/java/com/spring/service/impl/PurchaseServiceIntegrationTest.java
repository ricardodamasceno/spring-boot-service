package com.spring.service.impl;

import com.spring.adapters.vo.request.PurchaseRequestVO;
import com.spring.domain.entity.Product;
import com.spring.domain.entity.Purchase;
import com.spring.domain.entity.PurchaseItem;
import com.spring.domain.entity.User;
import com.spring.domain.enums.ProductCategory;
import com.spring.domain.enums.PurchaseStatus;
import com.spring.domain.exception.ProductNotFoundException;
import com.spring.domain.exception.PurchaseItemCreationException;
import com.spring.domain.exception.UserNotFoundException;
import com.spring.domain.repository.ProductRepository;
import com.spring.domain.repository.PurchaseItemRepository;
import com.spring.domain.repository.PurchaseRepository;
import com.spring.domain.repository.UserRepository;
import com.spring.domain.service.PurchaseItemService;
import com.spring.domain.service.PurchaseService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Used to make setUp method non-static
public class PurchaseServiceIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PurchaseItemRepository purchaseItemRepository;

    private User buyer;
    private Product product1;
    private Product product2;

    @BeforeEach
    public void setUp(){
        purchaseItemRepository.deleteAll();
        purchaseRepository.deleteAll();
        productRepository.deleteAll();
        userRepository.deleteAll();

        buyer = userRepository.save(
                new User(null, "Integration User", "integration@email.com", LocalDateTime.now()));
        product1 = productRepository.save(
                new Product(null, "Product 1" ,ProductCategory.NOTEBOOK, new BigDecimal(10)));
        product2 = productRepository.save(
                new Product(null, "Product 2" ,ProductCategory.PHONE, new BigDecimal(10)));
    }

    @Test
    public void shouldCreatePurchaseSuccessfully(){

        PurchaseRequestVO request = getPurchaseRequestVO(
                Arrays.asList(product1.getId(), product2.getId()));

        Purchase purchase = purchaseService.save(request);

        assertNotNull(purchase);
        assertNotNull(purchase.getId());
        assertEquals(PurchaseStatus.APPROVED, purchase.getStatus());
        assertEquals(buyer.getId(), purchase.getBuyer().getId());

    }

    @Test
    public void shouldFailDueToProductNotFound(){

        PurchaseRequestVO request = getPurchaseRequestVO(
                Arrays.asList(product1.getId() + "test", product2.getId()));

        Executable executable = () -> purchaseService.save(request);

        assertThrows(PurchaseItemCreationException.class, executable);

    }

    @Test
    public void shouldFailDueToUserNotFound(){

        buyer.setId(buyer.getId().concat("test"));

        PurchaseRequestVO request = getPurchaseRequestVO(
                Arrays.asList(product1.getId(), product2.getId()));

        Executable executable = () -> purchaseService.save(request);

        assertThrows(UserNotFoundException.class, executable);

    }

    private PurchaseRequestVO getPurchaseRequestVO(List<String> products){
        PurchaseRequestVO request = new PurchaseRequestVO();
        request.setBuyerId(buyer.getId());
        request.setProducts(products);
        return request;
    }

}
