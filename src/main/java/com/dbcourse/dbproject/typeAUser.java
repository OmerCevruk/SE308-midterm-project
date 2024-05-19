package com.dbcourse.dbproject;
import java.sql.*;
import java.time.LocalDate;
import java.util.Random;

public class typeAUser implements user{

    @Override
    public long transaction(int isolationLevel) throws SQLException {
        //start clock
        long beginTime = System.currentTimeMillis();

        // create connection and rand
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            // Connect to database
            Connection conn = DatabaseConnection.getConnection();
            // Set transaction isolation level
            conn.setTransactionIsolation(isolationLevel);
            conn.setAutoCommit(false);
            // Begin transaction


            int randomValue = rand.nextInt(2);
            if (randomValue < 0.5) {
                updateQuery(conn, LocalDate.parse("2011-01-01"), LocalDate.parse("2011-12-31"));
            }
            if (randomValue < 0.5) {
                updateQuery(conn, LocalDate.parse("2012-01-01"), LocalDate.parse("2012-12-31"));
            }
            if (randomValue < 0.5) {
                updateQuery(conn, LocalDate.parse("2013-01-01"), LocalDate.parse("2013-12-31"));
            }
            if (randomValue < 0.5) {
                updateQuery(conn, LocalDate.parse("2014-01-01"), LocalDate.parse("2014-12-31"));
            }
            if (randomValue < 0.5) {
                updateQuery(conn, LocalDate.parse("2015-01-01"), LocalDate.parse("2015-12-31"));
            }

            // Commit transaction
            conn.commit();

            // Disconnect from database
            conn.close();
        }
        
        return 0;

    }
    private static void updateQuery(Connection conn, LocalDate beginDate, LocalDate endDate) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("UPDATE Sales.SalesOrderDetail" +
                " SET UnitPrice = UnitPrice * 10.0 / 10.0" +
                " WHERE UnitPrice > 100 AND EXISTS (" +
                " SELECT * FROM Sales.SalesOrderHeader" +
                " WHERE Sales.SalesOrderHeader.SalesOrderID = Sales.SalesOrderDetail.SalesOrderID" +
                " AND Sales.SalesOrderHeader.OrderDate BETWEEN ? AND ?" +
                " AND Sales.SalesOrderHeader.OnlineOrderFlag = 1)" +
                "");

        pstmt.setDate(1, Date.valueOf(beginDate));
        pstmt.setDate(2, Date.valueOf(endDate));

        pstmt.executeUpdate();
    }
}
