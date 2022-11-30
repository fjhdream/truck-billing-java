package com.fjhdream.truckbilling.repository;

import com.fjhdream.truckbilling.repository.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author carota
 */
public interface UserRepository extends CrudRepository<User, String> {
}