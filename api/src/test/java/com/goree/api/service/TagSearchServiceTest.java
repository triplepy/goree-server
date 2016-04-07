package com.goree.api.service;

import com.goree.api.domain.Tag;
import com.goree.api.util.IDataSetFactory;
import com.goree.api.util.TestWithDBUnit;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TagSearchServiceTest extends TestWithDBUnit{
    @Autowired
    private TagService tagService;
    
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
                tag.setName((String) itable.getValue(i, "tag_name"));
                expecteds.add(tag);
            }
        } catch (DataSetException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    public void searchTagsByStartWord() throws Exception{
        List<Tag> actuals = tagService.searchTagsByStartWord("Search");
        Assert.assertTrue(actuals.containsAll(expecteds));
    }
    
    @Test
    public void searchTagsByStartWordOrdered() {
        List<Tag> actuals = tagService.searchTagsByStartWord("Search");
        for(int i = 0 ; i < expecteds.size(); i++){
            Assert.assertEquals(expecteds.get(i), actuals.get(i));
        }
    }
}
