package com.spring.domain.service.impl;

import com.spring.adapters.vo.request.PurchaseRequestVO;
import com.spring.domain.entity.Purchase;
import com.spring.domain.entity.User;
import com.spring.domain.enums.LogTags;
import com.spring.domain.enums.PurchaseStatus;
import com.spring.domain.exception.PurchaseCreationException;
import com.spring.domain.repository.PurchaseRepository;
import com.spring.domain.service.PurchaseItemService;
import com.spring.domain.service.PurchaseService;
import com.spring.domain.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final UserService userService;
    private final PurchaseItemService purchaseItemService;
    private final PurchaseRepository purchaseRepository;

    public Purchase save(PurchaseRequestVO request) {
        User buyer = userService.findById(request.getBuyerId());
        Purchase purchase;
        try {
            purchase = savePurchase(buyer);
            log.info("{} - Purchase {} saved successfully",
                    LogTags.PURCHASE_CREATION.name(), purchase.getId());
        } catch (Exception e) {
            log.error("{} - Failed to save purchase. Trace {}",
                    LogTags.PURCHASE_CREATION_ERROR.name(), e.getMessage());
            throw new PurchaseCreationException("Failed to create purchase");
        }
        purchaseItemService.savePurchaseItems(purchase, request);
        return purchase;
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
