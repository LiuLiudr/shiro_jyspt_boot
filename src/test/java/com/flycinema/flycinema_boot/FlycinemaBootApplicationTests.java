package com.flycinema.flycinema_boot;

import com.flycinema.flycinema_boot.dao.UserMapper;
import com.flycinema.flycinema_boot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FlycinemaBootApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Test
    void contextLoads() {
        System.out.println(userService.findPermsByName("fsy123"));
    }

}
