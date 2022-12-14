package com.fjhdream.truckbilling.repository;

import com.fjhdream.truckbilling.repository.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RoleRepository extends CrudRepository<Role, UUID> {
}