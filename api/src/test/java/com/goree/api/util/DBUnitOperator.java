package com.goree.api.util;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public class DBUnitOperator {
    private static IDatabaseTester tester;

    @Autowired
    private DBUnitOperator(DataSource dataSource) {
        this.tester = new DataSourceDatabaseTester(dataSource);
    }
    
    public static final void cleanInsert(IDataSet dataset) {
        execute(DatabaseOperation.CLEAN_INSERT, dataset);
    }

    public static final void delete(IDataSet dataset) {
        execute(DatabaseOperation.DELETE, dataset);
    }

    public static final void deleteAll(IDataSet dataset) {
        execute(DatabaseOperation.DELETE_ALL, dataset);
    }

    public static final void insert(IDataSet dataset) {
        execute(DatabaseOperation.INSERT, dataset);
    }

    public static final void refresh(IDataSet dataset) {
        execute(DatabaseOperation.REFRESH, dataset);
    }

    public static final void truncateTable(IDataSet dataset) {
        execute(DatabaseOperation.TRUNCATE_TABLE, dataset);
    }
    
    private static final void execute(DatabaseOperation operation, IDataSet dataset) {
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
