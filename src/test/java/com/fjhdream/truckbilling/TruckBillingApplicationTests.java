package com.fjhdream.truckbilling;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(TestContainerConfiguration.class)
class TruckBillingApplicationTests {

    @Test
    void contextLoads() {
    }

}
