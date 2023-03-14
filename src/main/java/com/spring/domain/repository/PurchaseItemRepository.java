package com.spring.domain.repository;

import com.spring.domain.entity.PurchaseItem;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseItemRepository extends CrudRepository<PurchaseItem, String> {
}
