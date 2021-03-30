package com.flycinema.flycinema_boot.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @ClassName RedisCacheManager 自定义缓存管理器
 * @Author admin
 * @Date 2021/3/29 9:52
 */
public class RedisCacheManager implements CacheManager {
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        return new RedisCache<K,V>(cacheName);
    }
}
