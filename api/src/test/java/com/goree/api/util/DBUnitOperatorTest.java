package com.goree.api.util;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.dbunit.dataset.IDataSet;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goree.api.Application;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={Application.class})
public class DBUnitOperatorTest {
    @Autowired
    private DataSource dataSource;
    
    private Connection conn;
    
    @Before
    public void setUp() throws Exception {
        conn = dataSource.getConnection();
        
        String createSql = "CREATE TABLE dbunit_test ( id int primary key auto_increment, name varchar(20))";
        
        conn.createStatement().executeUpdate(createSql);
        
    }
    
    @Test
    public void test() throws Exception {

        IDataSet dataset = IDataSetFactory.fromXml("src/test/resources/testdataset/dbunitoperator.xml");
        DBUnitOperator.cleanInsert(dataset);

        
        String selectSql = "SELECT * FROM dbunit_test";
        
        ResultSet resultSet = conn.prepareStatement(selectSql).executeQuery();
        
        if (resultSet.next()){
            Assert.assertEquals(resultSet.getInt("id"), 1);
            Assert.assertEquals(resultSet.getString("name"), "Hello");
        } else { 
            Assert.fail();
        }
    }
    
    @After
    public void tearDown() throws Exception {
        String dropSql = "DROP TABLE dbunit_test";
        conn.createStatement().executeUpdate(dropSql);
        conn.close();
    }
}