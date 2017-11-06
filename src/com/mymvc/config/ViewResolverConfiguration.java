package com.mymvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by alan.luo on 2017/9/22.
 */
@Configuration
public class ViewResolverConfiguration extends DelegatingWebMvcConfiguration {

    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/jsp/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

//    @Bean
//    public RequestToViewNameTranslator requestToViewNameTranslator(){
//        DefaultRequestToViewNameTranslator viewNameTranslator = new DefaultRequestToViewNameTranslator();
//        viewNameTranslator.setPrefix("/WEB-INF/jsp/views/");
//        viewNameTranslator.setSuffix(".jsp");
//
//        return viewNameTranslator;
//    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(viewResolver());
        super.configureViewResolvers(registry);
    }

}
