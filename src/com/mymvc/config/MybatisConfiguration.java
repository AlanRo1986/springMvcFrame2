package com.mymvc.config;



import com.mymvc.constant.ConstantInit;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;



import javax.sql.DataSource;


/**
 * Created by alan.luo on 2017/10/18.
 */
//@Configuration(value = "mybatisConfiguration")
@EnableTransactionManagement
public class MybatisConfiguration implements TransactionManagementConfigurer {

    public MybatisConfiguration(){

    }

    /*@Bean
    public DataSource getDataSource(){
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver(ConstantInit.driver);
        dataSource.setUrl(ConstantInit.url);
        dataSource.setUsername(ConstantInit.username);
        dataSource.setPassword(ConstantInit.password);
        dataSource.setPoolMaximumIdleConnections(ConstantInit.maxIdle);
        dataSource.setPoolMaximumActiveConnections(ConstantInit.maxActive);

        dataSource.setPoolPingEnabled(ConstantInit.testWhileIdle);
        dataSource.setPoolPingQuery(ConstantInit.validationQuery);
        dataSource.setPoolPingConnectionsNotUsedFor(ConstantInit.timeBetweenEvictionRunsMillis);

        return dataSource;
    }*/

    @Bean(destroyMethod = "close")
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(ConstantInit.driver);
        dataSource.setUrl(ConstantInit.url);
        dataSource.setUsername(ConstantInit.username);
        dataSource.setPassword(ConstantInit.password);

        dataSource.setInitialSize(ConstantInit.initialSize);
        dataSource.setMaxIdle(ConstantInit.maxIdle);
        dataSource.setMaxActive(ConstantInit.maxActive);
        dataSource.setMaxOpenPreparedStatements(ConstantInit.maxOpen);

        dataSource.setTestWhileIdle(ConstantInit.testWhileIdle);
        dataSource.setValidationQuery(ConstantInit.validationQuery);
        dataSource.setTimeBetweenEvictionRunsMillis(ConstantInit.timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(ConstantInit.minEvictableIdleTimeMillis);
        dataSource.setTestOnBorrow(ConstantInit.testOnBorrow);

        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sessionFactoryBean() throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(this.dataSource());
        sqlSessionFactoryBean.setConfiguration(this.getConfiguration());
        //sqlSessionFactoryBean.setTypeAliasesPackage(Constant.basePackages+".repository.mybatis");

        String locationPattern = ResourcePatternResolver.CLASSPATH_URL_PREFIX + "com/mymvc/repository/mybatis/resource/**.xml";
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(locationPattern));

        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.mymvc.repository.mybatis.mapper");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sessionFactoryBean");
        return mapperScannerConfigurer;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(this.dataSource());
        manager.setDefaultTimeout(40);
        return manager;
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return this.transactionManager();
    }

    public org.apache.ibatis.session.Configuration getConfiguration(){
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setLogImpl(StdOutImpl.class);
        return configuration;
    }


}
