package com.flycinema.flycinema_boot.jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * jasypt加密配置
 * @ClassName customEncryptorBean
 * @Author admin
 * @Date 2021/3/30 13:59
 */
@Configuration
public class customEncryptorConfig {
    @Bean(name = "customEncryptorBean")
    public StringEncryptor stringEncryptor(){
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        //设置密钥
        config.setPassword("ldr");
        //加密算法
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        //设置应用湖区加密密钥的哈希迭代次数
        config.setKeyObtentionIterations("1000");
        //创建加密程序池大小
        config.setPoolSize("1");
        //设置要请求加密算法的安全提供程序的名称
        config.setProviderName("SunJCE");
        //设置salt发生器
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        //设置iv发生器
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        //字符串输出编码格式
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }
}
