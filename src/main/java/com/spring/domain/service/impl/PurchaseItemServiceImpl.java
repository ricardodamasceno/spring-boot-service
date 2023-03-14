package com.spring.domain.service.impl;

import com.spring.adapters.vo.request.PurchaseRequestVO;
import com.spring.domain.entity.Product;
import com.spring.domain.entity.Purchase;
import com.spring.domain.entity.PurchaseItem;
import com.spring.domain.enums.LogTags;
import com.spring.domain.enums.PurchaseStatus;
import com.spring.domain.exception.PurchaseItemCreationException;
import com.spring.domain.repository.PurchaseItemRepository;
import com.spring.domain.repository.PurchaseRepository;
import com.spring.domain.service.ProductService;
import com.spring.domain.service.PurchaseItemService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PurchaseItemServiceImpl implements PurchaseItemService {

    private final PurchaseItemRepository purchaseItemRepository;
    private final PurchaseRepository purchaseRepository;
    private final ProductService productService;

    @Transactional
    public void savePurchaseItems(Purchase purchase, PurchaseRequestVO request) {

        List<Product> products = productService.getProductsByIdList(request.getProducts());

        try {
            validatePurchaseProductAmount(products, request.getProducts().size());
            List<PurchaseItem> purchaseItems = new ArrayList<>();
            products.forEach(product -> {
                purchase.setPurchaseValue(product.getValue().add(purchase.getPurchaseValue()));
                PurchaseItem purchaseItem = new PurchaseItem();
                purchaseItem.setItemValue(product.getValue());
                purchaseItem.setPurchase(purchase);
                purchaseItem.setProduct(product);
                purchaseItems.add(purchaseItem);
            });
            purchaseItemRepository.saveAll(purchaseItems);
            log.info("{} - Purchase Items saved for purchase {}",
                    LogTags.PURCHASE_ITEM_CREATION.name(), purchase.getId());
        } catch (Exception e) {
            log.error("{} - Canceling purchase {} due to an exception",
                    LogTags.PURCHASE_ITEM_CREATION_ERROR.name(), purchase.getId()
            );
            purchase.setStatus(PurchaseStatus.CANCELLED);
            log.error("{} - Failed to save purchase items. Cause: {}",
                    LogTags.PURCHASE_ITEM_CREATION_ERROR.name(), e.getMessage());
            if (e instanceof PurchaseItemCreationException) {
                throw e;
            }
            throw new PurchaseItemCreationException("Failed to create purchase items");
        } finally {
            purchaseRepository.save(purchase);
        }
    }

    private void validatePurchaseProductAmount(List<Product> products, int requestSize) {
        if (products.size() != requestSize) {
            throw new PurchaseItemCreationException(
                    "Couldn't find all the products to create the purchase");
        }
    }

}
