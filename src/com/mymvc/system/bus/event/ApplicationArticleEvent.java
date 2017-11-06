package com.mymvc.system.bus.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by alan.luo on 2017/10/16.
 */
public class ApplicationArticleEvent extends ApplicationEvent {
    public ApplicationArticleEvent(Object source) {
        super(source);
    }
}
