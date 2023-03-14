package com.spring.domain.service;

import com.spring.domain.entity.Product;
import com.spring.domain.entity.Purchase;

import java.util.List;

public interface PurchaseItemService {

    void savePurchaseItems(Purchase purchase, List<Product> products);

}
