package com.fjhdream.truckbilling.repository;

import com.fjhdream.truckbilling.repository.entity.Billing;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BillingRepository extends CrudRepository<Billing, UUID> {
}