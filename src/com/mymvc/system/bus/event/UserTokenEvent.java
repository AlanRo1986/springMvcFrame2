package com.mymvc.system.bus.event;

import com.mymvc.repository.hibernate.pojo.UserToken;

/**
 * Created by alan.luo on 2017/11/2.
 */
public class UserTokenEvent extends ApplicationUserEvent {
    public UserTokenEvent(UserToken source) {
        super(source);
    }

}
