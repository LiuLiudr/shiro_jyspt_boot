package com.flycinema.flycinema_boot.shiro;

import org.apache.shiro.util.SimpleByteSource;

import java.io.Serializable;

/**
 * @ClassName MySimpleByteSource
 * @Author admin
 * @Date 2021/3/30 12:21
 */
public class MySimpleByteSource extends SimpleByteSource implements Serializable {
    public MySimpleByteSource(byte[] bytes) {
        super(bytes);
    }

    public MySimpleByteSource(String string) {
        super(string);
    }
}
