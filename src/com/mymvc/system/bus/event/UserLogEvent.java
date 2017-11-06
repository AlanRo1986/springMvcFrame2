package com.mymvc.system.bus.event;

import com.mymvc.repository.hibernate.pojo.UserLog;

/**
 * Created by alan.luo on 2017/11/2.
 */
public class UserLogEvent extends ApplicationUserEvent {
    public UserLogEvent(UserLog source) {
        super(source);
    }

}
