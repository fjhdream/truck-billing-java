package com.fjhdream.truckbilling.repository;

import com.fjhdream.truckbilling.repository.entity.TeamDriver;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TeamDriverRepository extends CrudRepository<TeamDriver, UUID> {
}