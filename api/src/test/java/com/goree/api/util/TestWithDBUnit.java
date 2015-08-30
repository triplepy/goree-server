package com.goree.api.util;

import com.goree.api.Application;
import org.dbunit.dataset.IDataSet;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={Application.class})
public abstract class TestWithDBUnit {
    @Autowired
    private DBUnitOperator operator;

	private final IDataSet dataset = IDataSetFactory.fromXml(getDatasetFilePath());;

	@Before
	public void setUp() {
		operator.cleanInsert(dataset);
	}
	
	@After
	public void tearDown() {
        operator.deleteAll(dataset);
	}
	
	protected abstract String getDatasetFilePath();
	
}
