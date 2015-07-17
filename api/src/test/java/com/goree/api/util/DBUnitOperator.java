package com.goree.api.util;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;

public class DBUnitOperator {
	
	// 코드 냄새가 나지만 오늘 너무 머리를 많이 써서 일단 동작 되는 상태로 커밋을 한다.
	private static DataSource dataSource = getDataSource();
	
    private static DataSource getDataSource(){
    	if(dataSource != null){
    		return dataSource;
    	} else {
    		setDefaultDataSource();
    		return getDataSource();
    	}
    }
    
    public static void setDefaultDataSource(){
    	BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("net.sf.log4jdbc.DriverSpy");
        dataSource.setUrl("jdbc:log4jdbc:mysql://localhost:3306/goree");
        dataSource.setUsername("goree");
        dataSource.setPassword("goree!@#$");
        setDataSource(dataSource);
    }
    
    public static void setDataSource(DataSource dataSource){
    	DBUnitOperator.dataSource = dataSource;
    }
    
    public static void cleanInsert(IDataSet dataset) {
        
        IDatabaseTester tester = new DataSourceDatabaseTester(dataSource);
        IDatabaseConnection conn = null;
        try {
            conn = tester.getConnection();
            DatabaseOperation.CLEAN_INSERT.execute(conn, dataset);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
    }
}
