package com.fjhdream.truckbilling.repository;

import com.fjhdream.truckbilling.repository.entity.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ItemRepository extends CrudRepository<Item, UUID> {
}