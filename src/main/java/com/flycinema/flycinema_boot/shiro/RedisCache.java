package com.flycinema.flycinema_boot.shiro;
import lombok.Data;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

/**
 * @ClassName RedisCache 自定义redis缓存实现
 * @Author admin
 * @Date 2021/3/29 10:04
 */
@Data
public class RedisCache<k,v> implements Cache<k,v> {

    String cacheName;

    public RedisCache(String cacheName) {
        this.cacheName = cacheName;
    }
    public RedisCache() {

    }


//    @Resource
//    RedisTemplate redisTemplate;

    @Override
    public v get(k k) throws CacheException {
        System.out.println("get key value:"+this.cacheName+"==and=="+k);
        if(k == null){
            return null;
        }
        return (v) getRedisTemplate().opsForHash().get(this.cacheName,k.toString());
    }

    @Override
    public v put(k k, v v) throws CacheException {
        System.out.println("put key: "+k);
        System.out.println("put value:"+v);
        //存值
        if(k!=null){
            getRedisTemplate().opsForHash().put(this.cacheName,k.toString(),v);
        }
        return null;
    }

    @Override
    public v remove(k k) throws CacheException {
        System.out.println("=============remove=============");
        return (v) getRedisTemplate().opsForHash().delete(this.cacheName,k.toString());
    }

    @Override
    public void clear() throws CacheException {
        System.out.println("=============clear==============");
        getRedisTemplate().delete(this.cacheName);
    }

    @Override
    public int size() {
        return getRedisTemplate().opsForHash().size(this.cacheName).intValue();
    }

    @Override
    public Set<k> keys() {
        return getRedisTemplate().opsForHash().keys(this.cacheName);
    }

    @Override
    public Collection<v> values() {
        return getRedisTemplate().opsForHash().values(this.cacheName);
    }

    private RedisTemplate getRedisTemplate(){
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        //hash相当于 Map<string(大key),Map<String,String>（小key）>
        // redis 大key的序列化方式改为String类型
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //redis  小key的序列化方式改为String类型
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return  redisTemplate;
    }
}
