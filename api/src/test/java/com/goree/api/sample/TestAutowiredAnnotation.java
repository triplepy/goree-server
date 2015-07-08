package com.goree.api.sample;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goree.api.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={Application.class})
public class TestAutowiredAnnotation {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private FooService service;
    
    @Test
    public void autowiredDatasource() {
        Assert.assertNotNull(dataSource);
    }
    
    @Test
    public void autowiredService() {
        Assert.assertNotNull(service);
    }

}
