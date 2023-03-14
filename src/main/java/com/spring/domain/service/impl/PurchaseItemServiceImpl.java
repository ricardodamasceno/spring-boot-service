package com.spring.domain.service.impl;

import com.spring.domain.entity.Product;
import com.spring.domain.entity.Purchase;
import com.spring.domain.entity.PurchaseItem;
import com.spring.domain.enums.PurchaseStatus;
import com.spring.domain.exception.PurchaseItemCreationException;
import com.spring.domain.repository.PurchaseItemRepository;
import com.spring.domain.repository.PurchaseRepository;
import com.spring.domain.service.PurchaseItemService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PurchaseItemServiceImpl implements PurchaseItemService {

    private final PurchaseItemRepository purchaseItemRepository;
    private final PurchaseRepository purchaseRepository;

    @Transactional
    public void savePurchaseItems(Purchase purchase, List<Product> products) {
        try {
            List<PurchaseItem> purchaseItems = new ArrayList<>();
            products.forEach( product -> {
                purchase.setPurchaseValue(product.getValue().add(purchase.getPurchaseValue()));
                PurchaseItem purchaseItem = new PurchaseItem();
                purchaseItem.setItemValue(product.getValue());
                purchaseItem.setPurchase(purchase);
                purchaseItem.setProduct(product);
                purchaseItems.add(purchaseItem);
            });
            purchaseItemRepository.saveAll(purchaseItems);
        } catch (Exception e) {
            purchase.setStatus(PurchaseStatus.CANCELLED);
            throw new PurchaseItemCreationException("Failed to create ");
        } finally {
            purchaseRepository.save(purchase);
        }
    }

}
