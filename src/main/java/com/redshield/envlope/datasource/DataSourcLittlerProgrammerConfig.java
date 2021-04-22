package com.redshield.envlope.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 小程序系统数据源
 */

@Configuration
@MapperScan(basePackages= {"com.redshield.envlope.mapper.app"},sqlSessionFactoryRef="litterProgrammerSqlSessionFactory")
public class DataSourcLittlerProgrammerConfig {

    /**
     * 数据源配置
     * @return
     */
    @Bean(name="litterProgrammerDataSource")
    @ConfigurationProperties(prefix="spring.datasource.littlerprogrammer")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 注入数据源
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name="litterProgrammerSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("litterProgrammerDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /**
     * 配置事务
     * @param dataSource
     * @return
     */
    @Bean(name="litterProgrammerTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("litterProgrammerDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 注入管理SqlSession
     * @param sqlSessionFactory
     * @return
     */
    @Bean(name="litterProgrammerSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("litterProgrammerSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
