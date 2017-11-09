package com.mymvc.service.resource;

import com.mymvc.system.exception.IllegalServiceException;

import java.io.Serializable;

/**
 * Created by alan.luo on 2017/11/9.
 */
public interface IRedisMessageService {
    void sendMessage(String channel, Serializable message) throws IllegalServiceException;
}
