package com.flycinema.flycinema_boot.controller;

import com.flycinema.FlycinemaBootApplication;
import com.flycinema.flycinema_boot.dao.UserMapper;
import com.flycinema.flycinema_boot.entity.UserDO;
import com.flycinema.flycinema_boot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FlycinemaBootApplication.class)
@WebAppConfiguration
public class MapperController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() {
        List<UserDO> userDOS = userMapper.queryUserList();
        System.out.println(userDOS);
        UserDO userDO = new UserDO();
//        userDO.setUsername("liuyong");
//        userDO.setPassword("123");
//        userService.save(userDO);
        System.out.println("find"+userService.findUser("liuyong"));
    }

    @Test
    public void test2() {
        //向redis中添加数据
//        redisTemplate.opsForValue().set("keys", "value值",600, TimeUnit.SECONDS);

        //根据键值取出数据
//        System.out.println(redisTemplate.opsForValue().get("keys"));
//        System.out.println(redisTemplate.opsForHash().get("com.flycinema.flycinema_boot.shiro.realm.CustomerRealm.authorizationCache","fsy123"));
        System.out.println(redisTemplate.opsForHash().size("com.flycinema.flycinema_boot.shiro.realm.CustomerRealm.authorizationCache"));
    }


}
