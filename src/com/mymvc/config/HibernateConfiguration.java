package com.mymvc.config;

import com.mymvc.constant.Constant;
import com.mymvc.constant.ConstantInit;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.sql.DataSource;
import java.util.Properties;


/**
 *
 * Created by alan.luo on 2017/10/18.
 */
@Configuration
@EnableTransactionManagement
public class HibernateConfiguration implements TransactionManagementConfigurer{

    public HibernateConfiguration(){

    }

    @Bean
    public PersistenceExceptionTranslator persistenceExceptionTranslator(){
        return new HibernateExceptionTranslator();
    }

//    @Bean
//    public HibernateTransactionManager transactionManager(){
//        HibernateTransactionManager manager = new HibernateTransactionManager();
//        SessionFactory sessionFactory = this.sessionFactoryBuilder();
//        manager.setSessionFactory(sessionFactory);
//
//        return manager;
//    }
//
//    @Bean
//    public SessionFactory sessionFactoryBuilder(){
//        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(this.getDataSource());
//       // builder.scanPackages(Constant.basePackages+".repository.hibernate");
//        builder.addResource("com/mymvc/repository/hibernate/resource/User.hbm.xml");
//
//        Properties properties = new Properties();
//        properties.setProperty("dialect","org.hibernate.dialect.MySQL5InnoDBDialect");
//        properties.setProperty("show_sql","true");
//        properties.setProperty("format_sql","true");
//        properties.setProperty("auto","update");
//        builder.setProperties(properties);
//
//        return builder.buildSessionFactory();
//    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(){
        return new JpaTransactionManager(this.localContainerEntityManagerFactoryBean().getObject());
    }

    @Bean
    public PlatformTransactionManager dataSourceTransactionManager(){
        return new DataSourceTransactionManager(this.getDataSource());
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return this.platformTransactionManager();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(){

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");

        Properties properties = new Properties();
        properties.setProperty("dialect","org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.setProperty("show_sql","true");
        properties.setProperty("format_sql","true");
        properties.setProperty("auto","update");

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName("SpringJPA");
        factoryBean.setJpaVendorAdapter(adapter);
        factoryBean.setDataSource(this.getDataSource());
        factoryBean.setSharedCacheMode(SharedCacheMode.ENABLE_SELECTIVE);
        factoryBean.setValidationMode(ValidationMode.NONE);
        factoryBean.setPackagesToScan(Constant.basePackages+".repository.hibernate");
        //factoryBean.setMappingResources("com/mymvc/repository/hibernate/resource/User.hbm.xml");
        factoryBean.setJpaProperties(properties);

        return factoryBean;
    }

    @Bean(destroyMethod = "close")
    public DataSource getDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(ConstantInit.driver);
        dataSource.setUrl(ConstantInit.url);
        dataSource.setUsername(ConstantInit.username);
        dataSource.setPassword(ConstantInit.password);

        dataSource.setInitialSize(ConstantInit.initialSize);
        dataSource.setMaxIdle(ConstantInit.maxIdle);
        dataSource.setMaxActive(ConstantInit.maxActive);
        dataSource.setMaxOpenPreparedStatements(ConstantInit.maxOpen);

        dataSource.setValidationQuery(ConstantInit.validationQuery);
        dataSource.setTimeBetweenEvictionRunsMillis(ConstantInit.timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(ConstantInit.minEvictableIdleTimeMillis);
        dataSource.setTestOnBorrow(ConstantInit.testOnBorrow);

        return dataSource;
    }



}
