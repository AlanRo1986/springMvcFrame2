package com.mymvc.system.bus.event;

import com.mymvc.system.pojo.SocketTextMessagePojo;
import org.springframework.context.ApplicationEvent;

/**
 * Created by alan.luo on 2017/11/6.
 */
public class ApplicationWebSocketEvent extends ApplicationEvent {
    public ApplicationWebSocketEvent(SocketTextMessagePojo source) {
        super(source);
    }
}
