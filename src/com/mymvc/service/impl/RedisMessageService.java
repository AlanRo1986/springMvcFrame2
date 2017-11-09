package com.mymvc.service.impl;

import com.mymvc.service.resource.IRedisMessageService;
import com.mymvc.system.basic.CompactService;
import com.mymvc.system.exception.IllegalServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by alan.luo on 2017/11/9.
 */
@Service
public class RedisMessageService extends CompactService implements IRedisMessageService {

    @Autowired
    private RedisTemplate redisTemplate;

    public RedisMessageService() {
        super(RedisMessageService.class);
    }

    @Override
    public void sendMessage(String channel, Serializable message) throws IllegalServiceException {
        redisTemplate.convertAndSend(channel,message);
    }
}
