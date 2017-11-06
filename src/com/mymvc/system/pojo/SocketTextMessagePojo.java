package com.mymvc.system.pojo;

import com.mymvc.model.basic.Model;
import org.springframework.web.socket.TextMessage;

/**
 * Created by alan.luo on 2017/11/6.
 */
public class SocketTextMessagePojo extends Model {
    private String token;
    private TextMessage message;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TextMessage getMessage() {
        return message;
    }

    public void setMessage(TextMessage message) {
        this.message = message;
    }
}
