package com.spring.domain.service.impl;

import com.spring.adapters.vo.request.PurchaseRequestVO;
import com.spring.domain.entity.Product;
import com.spring.domain.entity.Purchase;
import com.spring.domain.entity.User;
import com.spring.domain.enums.PurchaseStatus;
import com.spring.domain.exception.PurchaseCreationException;
import com.spring.domain.repository.PurchaseRepository;
import com.spring.domain.service.ProductService;
import com.spring.domain.service.PurchaseItemService;
import com.spring.domain.service.PurchaseService;
import com.spring.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final UserService userService;
    private final ProductService productService;
    private final PurchaseItemService purchaseItemService;
    private final PurchaseRepository purchaseRepository;

    public String save(PurchaseRequestVO request) {
        User buyer = userService.findById(request.getBuyerId());
        List<Product> products = productService.getProductsByIdList(request.getProducts());

        try {
            Purchase purchase = savePurchase(buyer);
            purchaseItemService.savePurchaseItems(purchase, products);
            return purchase.getId();
        } catch (Exception e) {
            throw new PurchaseCreationException("Failed to create purchase");
        }
    }

    private Purchase savePurchase(User buyer){
        Purchase purchase = new Purchase();
        purchase.setBuyer(buyer);
        purchase.setPurchaseDate(LocalDateTime.now());
        purchase.setStatus(PurchaseStatus.APPROVED);
        purchase.setPurchaseValue(new BigDecimal(0));
        return purchaseRepository.save(purchase);
    }

}
