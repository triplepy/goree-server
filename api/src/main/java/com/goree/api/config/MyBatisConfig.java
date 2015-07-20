package com.goree.api.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.goree.api.exception.ConfigException;

@Configuration
@MapperScan(basePackages="com.goree.api.mapper")
public class MyBatisConfig {
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(mapperLocations());
        return sqlSessionFactoryBean;
    }
    
    private Resource[] mapperLocations() {
        PathMatchingResourcePatternResolver patternResolver = 
                new PathMatchingResourcePatternResolver();
        try {
            return patternResolver.getResources(
                    "classpath:src/main/resources/mapper/**/*.xml");
        } catch (IOException e) {
            throw new ConfigException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
