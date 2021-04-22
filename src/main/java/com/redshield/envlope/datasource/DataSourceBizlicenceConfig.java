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
 * 受理系统数据源
 */

@Configuration
@MapperScan(basePackages= {"com.redshield.envlope.mapper.bizlicence"},sqlSessionFactoryRef="bizlicenceSqlSessionFactory")
public class DataSourceBizlicenceConfig {

    /**
     * 数据源配置
     * @return
     */
    @Bean(name="bizlicenceDataSource")
    @ConfigurationProperties(prefix="spring.datasource.bizlicence")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 注入数据源
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name="bizlicenceSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("bizlicenceDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /**
     * 配置事务
     * @param dataSource
     * @return
     */
    @Bean(name="bizlicenceTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("bizlicenceDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 注入管理SqlSession
     * @param sqlSessionFactory
     * @return
     */
    @Bean(name="bizlicenceSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("bizlicenceSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
