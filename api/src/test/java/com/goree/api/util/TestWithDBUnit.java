package com.goree.api.util;

import com.goree.api.Application;
import org.dbunit.dataset.IDataSet;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={Application.class})
public abstract class TestWithDBUnit {
	private final IDataSet dataset = IDataSetFactory.fromXml(getDatasetFilePath());;

	@Before
	public void setUp() {
		DBUnitOperator.cleanInsert(dataset);
	}
	
	@After
	public void tearDown() {
		DBUnitOperator.deleteAll(dataset);
	}
	
	protected abstract String getDatasetFilePath();
	
}
