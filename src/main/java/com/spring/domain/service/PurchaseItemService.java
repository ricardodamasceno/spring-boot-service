package com.spring.domain.service;

import com.spring.adapters.vo.request.PurchaseRequestVO;
import com.spring.domain.entity.Purchase;

public interface PurchaseItemService {

    void savePurchaseItems(Purchase purchase, PurchaseRequestVO request);

}
