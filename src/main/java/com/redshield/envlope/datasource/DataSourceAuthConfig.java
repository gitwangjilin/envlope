package com.redshield.envlope.datasource;
import javax.sql.DataSource;

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

/**
 * 验证管理数据源
 */

@Configuration
@MapperScan(basePackages= {"com.redshield.envlope.mapper.auth"},sqlSessionFactoryRef="authSqlSessionFactory")
public class DataSourceAuthConfig {

    /**
     * 数据源配置
     * @return
     */
    @Bean(name="authDataSource")
    @ConfigurationProperties(prefix="spring.datasource.auth")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 注入数据源
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name="authSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("authDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /**
     * 配置事务
     * @param dataSource
     * @return
     */
    @Bean(name="authTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("authDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 注入管理SqlSession
     * @param sqlSessionFactory
     * @return
     */
    @Bean(name="authSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("authSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
