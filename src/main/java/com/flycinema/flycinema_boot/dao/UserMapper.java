package com.flycinema.flycinema_boot.dao;

import com.flycinema.flycinema_boot.entity.UserDO;
import com.flycinema.flycinema_boot.entity.UserPermsDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * @author liudingrong
 */
//@Mapper
@Repository
public interface UserMapper {

    List<UserDO> queryUserList();
    void save(UserDO userDO);
    UserDO findUser(String username);
    UserPermsDO findPermsByName(String username);
}
