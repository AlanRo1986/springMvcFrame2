package com.mymvc.system.provider;


import com.mymvc.system.basic.BasicProvider;
import com.mymvc.system.provider.handler.CacheMemHandler;
import com.mymvc.system.annotation.Provider;
import com.mymvc.system.provider.handler.CacheRedisHandler;
import com.mymvc.system.utils.DateUtil;
import org.springframework.data.redis.cache.RedisCache;

import javax.xml.crypto.Data;

/**
 * Created by alan.luo on 2017/8/4.
 */
@Provider
public class CacheProvider extends BasicProvider {

//    protected CacheMemHandler cache;
    protected CacheRedisHandler cache;

    /**
     * default expired time is one hour.
     */
    private static final long EXPIRED_TIME = 3600;

    public CacheProvider(){
        super();
        cache = new CacheRedisHandler(DateUtil.getTime()+"");
    }

    public static CacheProvider getInstance(){
        return new CacheProvider();
    }

    public Object get(String key){
        return cache.get(key);
    }

    public void put(String key,Object val,long expired){
        if (expired <= 0){
            expired = EXPIRED_TIME ;
        }
        cache.put(key,val,expired);
    }

    public boolean has(String key){
        return cache.has(key);
    }

    public void remove(String key){
        cache.remove(key);
    }

    public void clear(){
        cache.clear();
    }

    public int count(){
       return cache.count();
    }

}
