package com.mymvc.system.listener;

import com.mymvc.constant.ConstantRedis;
import com.mymvc.socket.MessageWebSocketHandler;
import com.mymvc.system.pojo.SocketTextMessagePojo;
import com.mymvc.system.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

/**
 * Created by alan.luo on 2017/11/9.
 */

public class RedisMessageListener implements MessageListener {

    @Autowired
    private MessageWebSocketHandler webSocketHandler;

    private RedisTemplate<Serializable,Serializable> redisTemplate;

    public void setRedisTemplate(RedisTemplate<Serializable, Serializable> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();

        String content = (String) redisTemplate.getValueSerializer().deserialize(body);
        String topic = redisTemplate.getStringSerializer().deserialize(channel);

        System.out.println("+++++++>>"+topic+":"+content);
        if (topic.equals(ConstantRedis.CHANNEL)){
            SocketTextMessagePojo pojo = JsonUtil.JsonToObject(content,SocketTextMessagePojo.class);
            webSocketHandler.sendMessageToUser(pojo.getToken(),pojo.getMessage());
        }

    }

}
