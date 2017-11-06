package com.mymvc.system.bus.event;

import com.mymvc.model.UserModel;
import com.mymvc.repository.hibernate.pojo.User;

/**
 * Created by alan.luo on 2017/10/16.
 *
 * like.
 * @Autowired
 * private ApplicationEventPublisher publisher;
 *
 * UserModel userModel = new UserModel();
 * userModel.setUsername(request.getParameter("userName"));
 * userModel.setLoginPassword(request.getParameter("userPasswd"));
 * publisher.publishEvent(new LoginEvent(userModel));
 */
public class LoginEvent extends ApplicationUserEvent {

    public LoginEvent(User user) {
        super(user);
    }

}
