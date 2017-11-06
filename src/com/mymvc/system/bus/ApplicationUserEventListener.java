package com.mymvc.system.bus;

import com.mymvc.system.annotation.EventBus;
import com.mymvc.system.bus.event.ApplicationUserEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by alan.luo on 2017/10/17.
 */
@EventBus
public class ApplicationUserEventListener implements ApplicationListener<ApplicationUserEvent> {

    @Async
    @Override
    public void onApplicationEvent(ApplicationUserEvent event) {
        System.out.println("ApplicationUserInterestedParty:"+event.getSource());
    }
}
