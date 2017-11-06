package com.mymvc.config;

import com.mymvc.constant.Constant;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * Created by alan.luo on 2017/9/18.
 */
@Configuration
@EnableWebMvc
@ComponentScan(
        basePackages = Constant.basePackages+".app"
//        useDefaultFilters = false,
//        excludeFilters = @ComponentScan.Filter({Configuration.class,Service.class, Component.class}),
//        includeFilters = @ComponentScan.Filter({RestController.class,Controller.class})
)
@Import({ViewResolverConfiguration.class})
public class DispatcherContextConfiguration {

    public DispatcherContextConfiguration(){
        System.out.println("DispatcherContextConfiguration>>>");
    }
}
