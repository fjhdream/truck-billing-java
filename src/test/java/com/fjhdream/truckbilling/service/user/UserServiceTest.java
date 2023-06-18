package com.fjhdream.truckbilling.service.user;

import com.fjhdream.truckbilling.TestContainerConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(TestContainerConfiguration.class)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void check_not_exist_user() {
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            userService.checkUser("not_exist_user");
        });
    }
}