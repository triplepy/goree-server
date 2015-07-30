package com.goree.api.config;

import com.goree.api.Application;
import com.goree.api.sample.FooService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

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
    @Autowired
    private DataSourceTransactionManager transactionManager;
    
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
    
    @Test
    public void transactionManagerRegistered() {
        Assert.assertNotNull(transactionManager);
    }
}
