package com.fjhdream.truckbilling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author carota
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@EntityScan
public class TruckBillingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TruckBillingApplication.class, args);
    }
}
