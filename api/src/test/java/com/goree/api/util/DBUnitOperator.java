package com.goree.api.util;

import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;

public class DBUnitOperator {
    private static BasicDataSource dataSource;
    
    public static void cleanInsert(IDataSet dataset) {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("net.sf.log4jdbc.DriverSpy");
        dataSource.setUrl("jdbc:log4jdbc:mysql://localhost:3306/goree");
        dataSource.setUsername("goree");
        dataSource.setPassword("goree!@#$");
        
        IDatabaseTester tester = new DataSourceDatabaseTester(dataSource);
        IDatabaseConnection conn = null;
        try {
            conn = tester.getConnection();
            DatabaseOperation.CLEAN_INSERT.execute(conn, dataset);
        } catch (Exception e) {
            throw new RuntimeException();
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
