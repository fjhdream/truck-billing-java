package com.fjhdream.truckbilling.repository;

import com.fjhdream.truckbilling.repository.entity.TeamCar;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author carota
 */
public interface TeamCarRepository extends CrudRepository<TeamCar, UUID> {
}