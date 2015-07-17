package com.goree.api.util;

import java.io.File;
import java.net.MalformedURLException;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

public class IDataSetFactory {
    public static IDataSet fromXml(String filePath) {
        try {
            return new FlatXmlDataSetBuilder().build(new File(filePath));
        } catch (MalformedURLException | DataSetException e) {
            throw new RuntimeException(e);
        }
    }
}
