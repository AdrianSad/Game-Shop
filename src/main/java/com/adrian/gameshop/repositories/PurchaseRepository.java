package com.adrian.gameshop.repositories;

import com.adrian.gameshop.models.Purchase;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
}
