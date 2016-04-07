package com.goree.api.util;

import java.sql.Connection;
import java.sql.ResultSet;

import javax.sql.DataSource;

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
import com.goree.api.domain.Group;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={Application.class})
public class DBUnitOperatorTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private DBUnitOperator opeartor;
    
    private Connection conn;
    
    @Before
    public void setUp() throws Exception {
        conn = dataSource.getConnection();
        String createSql = "CREATE TABLE dbunit_test ( id int primary key auto_increment, name varchar(20))";
        conn.createStatement().executeUpdate(createSql);
    }
    
    @Test
    public void cleanInsert() throws Exception {
        IDataSet dataset = IDataSetFactory.fromXml("src/test/resources/testdataset/dbunitoperator.xml");
        opeartor.cleanInsert(dataset);

        String selectSql = "SELECT * FROM dbunit_test";
        ResultSet resultSet = conn.prepareStatement(selectSql).executeQuery();

        Group expected = new Group();
        expected.setId(1);
        expected.setName("Hello");
        
        if (resultSet.next()){
            Assert.assertEquals(expected.getId(), resultSet.getInt("id"));
            Assert.assertEquals(expected.getName(), resultSet.getString("name"));
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