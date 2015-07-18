package com.goree.api.util;

import javax.sql.DataSource;

import org.dbunit.dataset.IDataSet;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.goree.api.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={Application.class})
public abstract class TestWithDBUnit {
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void setUp() {
		DBUnitOperator.setDataSource(dataSource);
		IDataSet dataset = IDataSetFactory.fromXml(getDatasetFilePath());
		DBUnitOperator.cleanInsert(dataset);
	}
	
	@After
	public void tearDown() {
		DBUnitOperator.setDataSource(dataSource);
		IDataSet dataset = IDataSetFactory.fromXml(getDatasetFilePath());
		DBUnitOperator.deleteAll(dataset);
	}
	
	public abstract String getDatasetFilePath();
	
}
