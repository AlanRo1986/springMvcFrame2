package com.mymvc.system.provider.handler;

import com.mymvc.constant.ConstantRedis;
import com.mymvc.system.callback.IRedisCallback;
import com.mymvc.system.provider.basic.DCacheData;
import com.mymvc.system.provider.basic.ICache;
import com.mymvc.system.utils.SerializeUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by alan.luo on 2017/8/4.
 *
 * CacheRedis redis = new CacheRedis("testKey");
 * redis.put("redisTest","redisTest1",10);
 * redis.put("redisTest1","redisTest1",0);
 * redis.remove("redisTest2");
 * redis.put("redisUserList",Object,50);
 *
 *  redis.count()
 *  redis.get("redisTest")
 *  redis.get("redisTest2")
 *  redis.get("redisUserList")
 *
 */
public class CacheRedisHandler implements ICache {

    protected JedisPool jedisPool = null;
    protected String id = null;

    public CacheRedisHandler(String id){
        jedisPool = new JedisPool(ConstantRedis.IP, ConstantRedis.PORT);
        this.id = id;
    }

    public Object execute(IRedisCallback callback){
        Jedis jedis = jedisPool.getResource();
        try {
            return callback.doWithRedis(jedis);
        }finally {
            jedis.close();
        }
    }

    public String getId() {
        return id;
    }

    public Object get(final String key){
        return execute(new IRedisCallback() {
            @Override
            public Object doWithRedis(Jedis jedis) {
                if (jedis.hexists(getId().getBytes(),key.getBytes())){
                    DCacheData val = (DCacheData) SerializeUtil.unSerialize(jedis.hget(id.getBytes(),key.getBytes()));
                    if (val.checkExpired()){
                        return val.getVal();
                    }
                    jedis.hdel(getId().getBytes(),key.getBytes());
                }
                return null;
            }
        });
    }

    @Override
    public boolean put(final String key, Object val, long millis) {
        if (millis <= 0){
            millis = 86400;
        }
        millis = millis * 1000;

        millis = System.currentTimeMillis() + millis;
        final DCacheData data = new DCacheData(val,millis);

        return (boolean) execute(new IRedisCallback() {
            @Override
            public Object doWithRedis(Jedis jedis) {
                long in = jedis.hset(getId().getBytes(),key.getBytes(), SerializeUtil.serialize(data));
                return in == 1 ? true : false;
            }
        });
    }

    @Override
    public boolean has(final String key) {
        return (boolean) execute(new IRedisCallback() {
            @Override
            public Object doWithRedis(Jedis jedis) {
                return jedis.hexists(getId().getBytes(),key.getBytes());
            }
        });
    }

    @Override
    public boolean remove(final String key) {
        return (boolean) execute(new IRedisCallback() {
            @Override
            public Object doWithRedis(Jedis jedis) {
                long in = jedis.hdel(getId().getBytes(),key.getBytes());
                return in == 1 ? true : false;
            }
        });

    }

    @Override
    public int count() {
        return (int) execute(new IRedisCallback() {
            @Override
            public Object doWithRedis(Jedis jedis) {
                return jedis.hgetAll(getId().getBytes()).size();
            }
        });
    }

    @Override
    public void clear() {
        execute(new IRedisCallback() {
            @Override
            public Object doWithRedis(Jedis jedis) {
                jedis.del(getId().getBytes());
                return null;
            }
        });
    }
}
