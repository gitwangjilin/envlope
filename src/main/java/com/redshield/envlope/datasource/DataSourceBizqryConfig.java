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
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


/**
 * 查询系统数据库:数据源配置
 */

@Configuration
@MapperScan(basePackages= {"com.redshield.envlope.mapper.bizqry"},sqlSessionFactoryRef="bizqrySqlSessionFactory")
public class DataSourceBizqryConfig {

    /**
     * 数据源配置
     * @return
     */
    @Bean(name="bizqryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.bizqry")
    @Primary
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 注入SqlSessionFactory
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name="bizqrySqlSessionFactory")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("bizqryDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /**
     * 注入事务
     * @param dataSource
     * @return
     */
    @Bean(name="bizqryTransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("bizqryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


    /**
     * 注入管理SqlSession
     */
    @Bean(name="bizqrySqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("bizqrySqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
