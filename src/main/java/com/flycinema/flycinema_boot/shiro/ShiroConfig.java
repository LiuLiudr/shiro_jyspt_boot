package com.flycinema.flycinema_boot.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.flycinema.flycinema_boot.shiro.realm.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ShiroConfig
 * @Author admin
 * @Date 2021/3/29 11:05
 */
@Configuration
public class ShiroConfig {

    /**
     * shiro方言配置。html使用shiro标签才会被解析
     * @return
     */
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

    /**
     * 1.创建filter 负责拦截请求
     * @return shiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //配置受限资源
        Map<String ,String> map = new HashMap<>();
        //anon不受限要先写，authc受限资源后写。
        map.put("/login","anon");
        map.put("/register","anon");
        map.put("/selectUser","perms[user:add]");
        map.put("/**","authc");
        //默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/loginView");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //2.创建安全管理器
    /**
     *
     * @return
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager( Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //安全管理器注入realm
        defaultWebSecurityManager.setRealm(realm);
        return  defaultWebSecurityManager;
    }

    //3.船舰自定义realm

    /**
     *
     * @return
     */
    @Bean(name = "realm")
    public Realm getRealm(){
        CustomerRealm customerRealm =new CustomerRealm();
        //1.修改凭证匹配器
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(hashedCredentialsMatcher);

        //2.开启缓存管理(可以用EhCacheManager（）也可以用自定义的缓存管理器)
//        customerRealm.setCacheManager(new EhCacheManager());
        customerRealm.setCacheManager(new RedisCacheManager());
        //开启全局缓存管理
        customerRealm.setCachingEnabled(true);
        //开启授权和认证的缓存
        customerRealm.setAuthorizationCachingEnabled(true);
        customerRealm.setAuthenticationCachingEnabled(true);
        //设置缓存名字，也可以不设置。（默认缓存的名字为Realm+Authorization/Authentication）
        customerRealm.setAuthorizationCacheName("authorization");
        customerRealm.setAuthenticationCacheName("authentication");

        return customerRealm;
    }

}
