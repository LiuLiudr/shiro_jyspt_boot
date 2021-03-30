package com.flycinema.flycinema_boot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName UserPermsDO
 * @Author admin
 * @Date 2021/3/30 9:30
 */
@Data
@NoArgsConstructor
public class UserPermsDO implements Serializable {
    private Integer id;
    private String username;
    private String perms;
}
