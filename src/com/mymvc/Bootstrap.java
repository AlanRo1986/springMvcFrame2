package com.mymvc;

import com.mymvc.config.DispatcherContextConfiguration;
import com.mymvc.config.RootContextConfiguration;
import com.mymvc.system.core.ApplicationContextListener;
import com.mymvc.system.core.CrossDomainFilter;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.IntrospectorCleanupListener;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * Created by alan.luo on 2017/9/18.
 */
@Order(1)
public class Bootstrap implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        servletContext.getServletRegistration("default").addMapping("/resource/*");

        /**
         * init application context configuration.
         */
        AnnotationConfigWebApplicationContext rootConfig = new AnnotationConfigWebApplicationContext();
        rootConfig.register(RootContextConfiguration.class);

        /**
         * add a listener for ContextLoaderListener
         */
        servletContext.addListener(new ApplicationContextListener(rootConfig));


        /**
         * init dispatcher context configuration
         */
        AnnotationConfigWebApplicationContext dispatcherConfig = new AnnotationConfigWebApplicationContext();
        dispatcherConfig.register(DispatcherContextConfiguration.class);

        /**
         * xml config of the dispatcher configuration.
         */
//        XmlWebApplicationContext dispatcherConfigForXml = new XmlWebApplicationContext();
//        dispatcherConfigForXml.setConfigLocation("/WEB-INF/dispatcher-servlet.xml");
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",new DispatcherServlet(dispatcherConfigForXml));

        /**
         * add a DispatcherServlet to this mvc server.
         */
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",new DispatcherServlet(dispatcherConfig));

        /**
         * initialize the upload file config.
         */
        dispatcher.setMultipartConfig(new MultipartConfigElement("",5242880,5242880,5242880));

        /**
         * add filter
         */
        this.addFilter(servletContext);

        /**
         * add listener
         */
        this.addListener(servletContext);

        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

    }

    /**
     * 过滤程序
     * @param servletContext
     */
    private void addFilter(ServletContext servletContext) {

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        FilterRegistration.Dynamic dynamic =  servletContext.addFilter("characterEncodingFilter",characterEncodingFilter);
        dynamic.addMappingForUrlPatterns(null,true,"/*");


        FilterRegistration.Dynamic dynamic2 =  servletContext.addFilter("crossDomainFilter",new CrossDomainFilter());
        dynamic2.setInitParameter("targetFilterLifecycle","true");
        dynamic2.addMappingForUrlPatterns(null,true,"/*");

    }

    /**
     * listener application
     * @param servletContext
     */
    private void addListener(ServletContext servletContext) {
        servletContext.addListener(new IntrospectorCleanupListener());
        servletContext.addListener(new RequestContextListener());
    }

}
