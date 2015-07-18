package com.goree.api.util;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBUnitOperator {
    private IDatabaseTester tester;
    
    @Autowired
    public DBUnitOperator(DataSource dataSource) {
        this.tester = new DataSourceDatabaseTester(dataSource); 
    }
    
    public void cleanInsert(IDataSet dataset) {
        execute(DatabaseOperation.CLEAN_INSERT, dataset);
    }
    
    public void delete(IDataSet dataset) {
        execute(DatabaseOperation.DELETE, dataset);
    }
    
    public void deleteAll(IDataSet dataset) {
        execute(DatabaseOperation.DELETE_ALL, dataset);
    }
    
    public void insert(IDataSet dataset) {
        execute(DatabaseOperation.INSERT, dataset);
    }
    
    public void refresh(IDataSet dataset) {
        execute(DatabaseOperation.REFRESH, dataset);
    }
    
    public void truncateTable(IDataSet dataset) {
        execute(DatabaseOperation.TRUNCATE_TABLE, dataset);
    }
    
    private void execute(DatabaseOperation operation, IDataSet dataset) {
        IDatabaseConnection conn = null;
        try {
            conn = tester.getConnection();
            operation.execute(conn, dataset);
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
