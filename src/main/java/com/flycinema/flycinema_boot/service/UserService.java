package com.flycinema.flycinema_boot.service;

import com.flycinema.flycinema_boot.entity.UserDO;
import com.flycinema.flycinema_boot.entity.UserPermsDO;

import java.util.List;

public interface UserService {
    void save(UserDO userDO);
    UserDO findUser(String username);
    UserPermsDO findPermsByName(String username);
}
