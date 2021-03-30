package com.flycinema.flycinema_boot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName uSERvO
 * @Author admin
 * @Date 2021/3/29 15:05
 */
@Data
@NoArgsConstructor
public class UserVo implements Serializable {
    private String username;
    private String password;
}
