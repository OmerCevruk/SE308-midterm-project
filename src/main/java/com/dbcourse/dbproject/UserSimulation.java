package com.dbcourse.dbproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class UserSimulation {

    static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=AdventureWorks2022";
    static final String USER = "";
    static final String PASSWORD = "";

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int numberOfTypeAUsers = 5;
        int numberOfTypeBUsers = 8;
        int transactionRuns = 100;

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfTypeAUsers + numberOfTypeBUsers);

        List<Future<UserMetrics>> futures = new ArrayList<>();

        // Create and start Type A user threads
        for (int i = 0; i < numberOfTypeAUsers; i++) {
            futures.add(executorService.submit(new TypeAUser(transactionRuns)));
        }

        // Create and start Type B user threads
        for (int i = 0; i < numberOfTypeBUsers; i++) {
            futures.add(executorService.submit(new TypeBUser(transactionRuns)));
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);

        // Collect metrics
        int totalTypeAUsers = 0;
        int totalTypeBUsers = 0;
        double totalDurationTypeA = 0;
        double totalDurationTypeB = 0;

        for (Future<UserMetrics> future : futures) {
            UserMetrics metrics = future.get();
            if (metrics.getUserType() == 'A') {
                totalTypeAUsers++;
                totalDurationTypeA += metrics.getTotalDuration();
            } else {
                totalTypeBUsers++;
                totalDurationTypeB += metrics.getTotalDuration();
            }
        }

        double avgDurationTypeA = totalTypeAUsers == 0 ? 0 : totalDurationTypeA / totalTypeAUsers;
        double avgDurationTypeB = totalTypeBUsers == 0 ? 0 : totalDurationTypeB / totalTypeBUsers;

        System.out.println("Number of Type A Users: " + totalTypeAUsers);
        System.out.println("Number of Type B Users: " + totalTypeBUsers);
        System.out.println("Average Duration of Type A Users: " + avgDurationTypeA);
        System.out.println("Average Duration of Type B Users: " + avgDurationTypeB);
    }
}

class UserMetrics {
    private final char userType;
    private final double totalDuration;

    public UserMetrics(char userType, double totalDuration) {
        this.userType = userType;
        this.totalDuration = totalDuration;
    }

    public char getUserType() {
        return userType;
    }

    public double getTotalDuration() {
        return totalDuration;
    }
}

class TypeAUser implements Callable<UserMetrics> {
    private final int transactionRuns;

    public TypeAUser(int transactionRuns) {
        this.transactionRuns = transactionRuns;
    }

    @Override
    public UserMetrics call() {
        double totalDuration = 0;

        try (Connection connection = DriverManager.getConnection(UserSimulation.URL, UserSimulation.USER, UserSimulation.PASSWORD);
             Statement statement = connection.createStatement()) {

            for (int i = 0; i < transactionRuns; i++) {
                long startTime = System.nanoTime();

                connection.setAutoCommit(false);
                try {
                    // Simulate an update operation
                    statement.executeUpdate("UPDATE Sales.SalesOrderDetail SET OrderQty = OrderQty + 1 WHERE SalesOrderDetailID = 1");
                    connection.commit();
                } catch (SQLException e) {
                    connection.rollback();
                }

                long endTime = System.nanoTime();
                totalDuration += (endTime - startTime) / 1e6; // Convert to milliseconds
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new UserMetrics('A', totalDuration);
    }
}

class TypeBUser implements Callable<UserMetrics> {
    private final int transactionRuns;

    public TypeBUser(int transactionRuns) {
        this.transactionRuns = transactionRuns;
    }

    @Override
    public UserMetrics call() {
        double totalDuration = 0;

        try (Connection connection = DriverManager.getConnection(UserSimulation.URL, UserSimulation.USER, UserSimulation.PASSWORD);
             Statement statement = connection.createStatement()) {

            for (int i = 0; i < transactionRuns; i++) {
                long startTime = System.nanoTime();

                // Simulate a select operation
                statement.executeQuery("SELECT * FROM Sales.SalesOrderDetail WHERE SalesOrderDetailID = 1");

                long endTime = System.nanoTime();
                totalDuration += (endTime - startTime) / 1e6; // Convert to milliseconds
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new UserMetrics('B', totalDuration);
    }
}
