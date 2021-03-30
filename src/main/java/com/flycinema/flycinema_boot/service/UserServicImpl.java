package com.flycinema.flycinema_boot.service;

import com.flycinema.flycinema_boot.dao.UserMapper;
import com.flycinema.flycinema_boot.entity.UserDO;
import com.flycinema.flycinema_boot.entity.UserPermsDO;
import com.flycinema.flycinema_boot.shiro.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName userServicImpl
 * @Author admin
 * @Date 2021/3/29 14:17
 */
@Service("userService")
public class UserServicImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(UserDO userDO) {
        //处理业务调用dao
        //1.生成随机盐并存入
        String salt = SaltUtils.getSalt(8);
        userDO.setPayment(salt);
        //明文密码进项md5+salt+hash散列
        Md5Hash md5Hash = new Md5Hash(userDO.getPassword(),salt,1024);
        userDO.setPassword(md5Hash.toHex());
        System.out.println("cccccccccc"+userDO);
        userMapper.save(userDO);
    }

    @Override
    public UserDO findUser(String username) {
        return  userMapper.findUser(username);
    }

    @Override
    public UserPermsDO findPermsByName(String username) {
        return userMapper.findPermsByName(username);
    }
}
