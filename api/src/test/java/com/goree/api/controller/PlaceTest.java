package com.goree.api.controller;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.goree.api.domain.Place;
import com.goree.api.util.TestWithDBUnit;

public class PlaceTest extends TestWithDBUnit{
    @Autowired
    private PlaceController placeController;

    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/place_test_setup.xml";
    }
    
    @Test
    public void findPlaceById() {
        Place expected = new Place();
        expected.setId(1);
        expected.setName("findPlaceById Test");
        expected.setAddress("Adddrerererereresss");
        expected.setXCoordinate(new BigDecimal("36.017194"));
        expected.setYCoordinate(new BigDecimal("128.6978236"));
    
        Place actual = placeController.findPlaceById(1);
        
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expected.getName(),actual.getName());
        Assert.assertEquals(0, expected.getXCoordinate().compareTo(actual.getXCoordinate()));
    }
    
    
    @Test
    public void createPlace() {
        Place expected = new Place();
        expected.setName("create place name");
        expected.setAddress("create place address");
        expected.setXCoordinate(new BigDecimal("36.017194"));
        expected.setYCoordinate(new BigDecimal("128.6978236"));
        
        Place actual = placeController.createPlace(expected);
        
        Assert.assertEquals(expected, actual);
    }
    
    @Test
    public void findPlaceByItself() {
        Place expected = new Place();
        expected.setName("findPlaceByItself");
        expected.setAddress("findPlaceByItself ADDD");
        expected.setXCoordinate(new BigDecimal("36.017194"));
        expected.setYCoordinate(new BigDecimal("128.6978236"));
        
        Place actual = placeController.findPlaceByItself(expected);
        Assert.assertEquals(expected, actual);
    }
}
