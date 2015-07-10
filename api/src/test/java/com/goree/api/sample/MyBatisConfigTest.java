package com.goree.api.sample;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goree.api.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={Application.class})
public class MyBatisConfigTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private FooService service;
    @Autowired
    private SqlSessionFactoryBean sqlSessionFactoryBean;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    @Autowired
    private SqlSession sqlSession;
    
    @Test
    public void autowiredDatasource() {
        Assert.assertNotNull(dataSource);
    }
    
    @Test
    public void autowiredService() {
        Assert.assertNotNull(service);
    }
    
    @Test
    public void sqlSessionFactoryBeanRegistered() {
        Assert.assertNotNull(sqlSessionFactoryBean);
    }
    
    @Test
    public void sqlSessionFactoryRegistered() {
        Assert.assertNotNull(sqlSessionFactory);
    }
    
    @Test
    public void sqlSessionTemplateRegistered() {
        Assert.assertNotNull(sqlSessionTemplate);
    }
    
    @Test
    public void sqlSessionRegistered() {
        Assert.assertNotNull(sqlSession);
    }
    
    // TODO test about configurations for mybatis-config.xml(SqlSessionFactory on javaconfig)
}
