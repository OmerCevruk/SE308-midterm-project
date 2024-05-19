package com.dbcourse.dbproject;
import java.sql.*;
import java.time.LocalDate;
import java.util.Random;

public class TypeBUser implements User {

    @Override
    public double transaction(int isolationLevel) throws SQLException {
        // Start timer
        long beginTime = System.currentTimeMillis();

        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            // Connect to database
            Connection conn = DatabaseConnection.getConnection();
            // Set transaction isolation level
            conn.setTransactionIsolation(isolationLevel);
            conn.setAutoCommit(false);

            int randomValue = rand.nextInt(2);
            if (randomValue < 0.5) {
                selectQuery(conn, LocalDate.parse("2011-01-01"), LocalDate.parse("2011-12-31"));
            }
            if (randomValue < 0.5) {
                selectQuery(conn, LocalDate.parse("2012-01-01"), LocalDate.parse("2012-12-31"));
            }
            if (randomValue < 0.5) {
                selectQuery(conn, LocalDate.parse("2013-01-01"), LocalDate.parse("2013-12-31"));
            }
            if (randomValue < 0.5) {
                selectQuery(conn, LocalDate.parse("2014-01-01"), LocalDate.parse("2014-12-31"));
            }
            if (randomValue < 0.5) {
                selectQuery(conn, LocalDate.parse("2015-01-01"), LocalDate.parse("2015-12-31"));
            }

            conn.commit();
            conn.close();
        }
        // End timer
        long endTime = System.currentTimeMillis();
        double elapsedTime = (endTime - beginTime) / 1000.0; // convert milliseconds to seconds

        return elapsedTime;
    }

    private static void selectQuery(Connection conn, LocalDate beginDate, LocalDate endDate) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(
                "SELECT SalesOrderID, OrderDate, OnlineOrderFlag " +
                        "FROM Sales.SalesOrderHeader " +
                        "WHERE OrderDate BETWEEN ? AND ? AND OnlineOrderFlag = 1"
        );

        pstmt.setDate(1, Date.valueOf(beginDate));
        pstmt.setDate(2, Date.valueOf(endDate));

        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            // Fetch data from ResultSet if needed
            int salesOrderID = rs.getInt("SalesOrderID");
            Date orderDate = rs.getDate("OrderDate");
            boolean onlineOrderFlag = rs.getBoolean("OnlineOrderFlag");

            // For this simulation, we are not doing anything with the fetched data.
            // You can add logic here if needed.
        }
        rs.close();
        pstmt.close();
    }
}
