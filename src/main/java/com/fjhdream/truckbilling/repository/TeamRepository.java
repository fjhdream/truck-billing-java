package com.fjhdream.truckbilling.repository;

import com.fjhdream.truckbilling.repository.entity.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author carota
 */
public interface TeamRepository extends CrudRepository<Team, UUID> {
}