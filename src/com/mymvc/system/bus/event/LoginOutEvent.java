package com.mymvc.system.bus.event;

/**
 * Created by alan.luo on 2017/10/16.
 */
public class LoginOutEvent extends ApplicationUserEvent {

    public LoginOutEvent(String userName) {
        super(userName);
    }

}
