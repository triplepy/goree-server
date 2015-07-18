package com.goree.api.util;

import org.dbunit.dataset.IDataSet;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goree.api.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={Application.class})
public abstract class TestWithDBUnit {
    @Autowired
    private DBUnitOperator operator;
    
	@Before
	public void setUp() {
		IDataSet dataset = IDataSetFactory.fromXml(getDatasetFilePath());
		operator.cleanInsert(dataset);
	}
	
	@After
	public void tearDown() {
		IDataSet dataset = IDataSetFactory.fromXml(getDatasetFilePath());
		operator.deleteAll(dataset);
	}
	
	public abstract String getDatasetFilePath();
	
}
