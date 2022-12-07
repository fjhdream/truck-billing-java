package com.fjhdream.truckbilling.repository;

import com.fjhdream.truckbilling.repository.entity.BillingItem;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BillingItemRepository extends CrudRepository<BillingItem, UUID> {
}