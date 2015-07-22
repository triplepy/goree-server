package com.goree.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.goree.api.domain.Tag;
import com.goree.api.util.IDataSetFactory;
import com.goree.api.util.TestWithDBUnit;

public class TagSearchTest extends TestWithDBUnit{
    @Autowired
    private TagController tagController;
    
    private List<Tag> expecteds;

    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/tag_search_test.xml";
    }
    
    @Override
    @Before
    public void setUp() {
        super.setUp();
               
        expecteds = new ArrayList<>();
        IDataSet dataSet = IDataSetFactory.fromXml(getDatasetFilePath());
        try {
            ITable itable = dataSet.getTable("tag");
            for(int i = 0; i < itable.getRowCount() ; i++){
                Tag tag = new Tag();
                tag.setId(Integer.parseInt((String)itable.getValue(i, "tag_id")));
                tag.setTagName((String)itable.getValue(i, "tag_name"));
                expecteds.add(tag);
            }
        } catch (DataSetException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    public void searchTagsByStartWord() throws Exception{
        List<Tag> actuals = tagController.searchTagsByStartWord("Search");
        Assert.assertTrue(actuals.containsAll(expecteds));
    }
    
    @Test
    public void searchTagsByStartWordOrdered() {
        List<Tag> actuals = tagController.searchTagsByStartWord("Search");
        for(int i = 0 ; i < expecteds.size(); i++){
            Assert.assertEquals(expecteds.get(i), actuals.get(i));
        }
    }
}
