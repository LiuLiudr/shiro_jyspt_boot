package com.flycinema.flycinema_boot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author liudingrong
 */
@Data
@NoArgsConstructor
public class UserDO implements Serializable {

    private Integer uid;

    private String username;

    private String password;

    private Integer age;

    private String birthday;

    private String email;

    private String status;

    private String code;

    private String payment;

    private Integer admin;
}
