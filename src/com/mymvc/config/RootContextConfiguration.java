package com.mymvc.config;

import com.mymvc.constant.Constant;
import com.mymvc.system.annotation.ServiceHibernate;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

/**
 * Created by alan.luo on 2017/9/18.
 */
@Configuration
@ComponentScan(
        basePackages = Constant.basePackages,
        excludeFilters = @ComponentScan.Filter({RestController.class,Controller.class,Configuration.class /*ServiceHibernate.class*/})
)
@Import({WebMvcAdapterConfiguration.class,AsyncConfiguration.class,SocketConfiguration.class,RedisMessageListenerConfiguration.class,/*MybatisConfiguration.class*/
        HibernateConfiguration.class})
public class RootContextConfiguration {

    public RootContextConfiguration(){
        System.out.println("RootContextConfiguration>>");
    }

    /**
     * local message.
     * @return
     */
    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setCacheSeconds(-1);
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        messageSource.setBasenames("/WEB-INF/i18n/messages","/WEB-INF/i18n/errors",
                "/WEB-INF/i18n/validator");
        return messageSource;
    }

    /**
     * validate factory bean.
     * @return
     */
    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean(){
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        factoryBean.setProviderClass(HibernateValidator.class);
        factoryBean.setValidationMessageSource(this.messageSource());
        return factoryBean;
    }

//    /**
//     * use method validate post processor.
//     * @return
//     */
//    @Bean
//    public MethodValidationPostProcessor methodValidationPostProcessor(){
//        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
//        postProcessor.setValidator(this.localValidatorFactoryBean());
//        return postProcessor;
//    }


}
