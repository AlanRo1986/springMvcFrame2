package com.mymvc.config;

import com.mymvc.socket.MessageWebSocketHandler;
import com.mymvc.socket.WebSocketHandshakeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by alan.luo on 2017/11/6.
 */
@Configuration
@EnableWebSocket
public class SocketConfiguration implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        String[] allowsOrigins = {"*"};

        /**
         * web socket 管道
         */
        registry.addHandler(messageWebSocketHandler(),"/notifyServer")
                .setAllowedOrigins(allowsOrigins)
                .addInterceptors(webSocketHandshakeInterceptor());

        registry.addHandler(messageWebSocketHandler(),"/sockjs/notifyServer")
                .setAllowedOrigins(allowsOrigins)
                .addInterceptors(webSocketHandshakeInterceptor())
                .withSockJS();
    }

    @Bean
    public MessageWebSocketHandler messageWebSocketHandler(){
        return new MessageWebSocketHandler();
    }

    @Bean
    public WebSocketHandshakeInterceptor webSocketHandshakeInterceptor(){
        return new WebSocketHandshakeInterceptor();
    }

}
