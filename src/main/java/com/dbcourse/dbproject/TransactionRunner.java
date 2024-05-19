package com.dbcourse.dbproject;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionRunner {
    public static void runTransactions(User userA, User userB) throws InterruptedException {
        Thread threadUserA1 = new Thread(() -> {
            try {
                userA.transaction(Connection.TRANSACTION_READ_COMMITTED);
            } catch (SQLException e) {
                System.out.println("Error running transaction for user A 1: " + e.getMessage());
            }
        });

        Thread threadUserA2 = new Thread(() -> {
            try {
                userA.transaction(Connection.TRANSACTION_READ_UNCOMMITTED);
            } catch (SQLException e) {
                System.out.println("Error running transaction for user A 2: " + e.getMessage());
            }
        });

        Thread threadUserA3 = new Thread(() -> {
            try {
                userA.transaction(Connection.TRANSACTION_REPEATABLE_READ);
            } catch (SQLException e) {
                System.out.println("Error running transaction for user B 1: " + e.getMessage());
            }
        });

        Thread threadUserA4 = new Thread(() -> {
            try {
                userA.transaction(Connection.TRANSACTION_SERIALIZABLE);
            } catch (SQLException e) {
                System.out.println("Error running transaction for user B 2: " + e.getMessage());
            }
        });

        Thread threadUserB1 = new Thread(() -> {
            try {
                userB.transaction(Connection.TRANSACTION_READ_COMMITTED);
            } catch (SQLException e) {
                System.out.println("Error running transaction for user A 1: " + e.getMessage());
            }
        });

        Thread threadUserB2 = new Thread(() -> {
            try {
                userB.transaction(Connection.TRANSACTION_READ_UNCOMMITTED);
            } catch (SQLException e) {
                System.out.println("Error running transaction for user A 2: " + e.getMessage());
            }
        });

        Thread threadUserB3 = new Thread(() -> {
            try {
                userB.transaction(Connection.TRANSACTION_REPEATABLE_READ);
            } catch (SQLException e) {
                System.out.println("Error running transaction for user B 1: " + e.getMessage());
            }
        });

        Thread threadUserB4 = new Thread(() -> {
            try {
                userB.transaction(Connection.TRANSACTION_SERIALIZABLE);
            } catch (SQLException e) {
                System.out.println("Error running transaction for user B 2: " + e.getMessage());
            }
        });

        threadUserA1.start();
        threadUserA2.start();
        threadUserA3.start();
        threadUserA4.start();
        threadUserB1.start();
        threadUserB2.start();
        threadUserB3.start();
        threadUserB4.start();

        // Wait for all threads to finish
        threadUserA1.join();
        threadUserA2.join();
        threadUserA3.join();
        threadUserA4.join();

        threadUserB1.join();
        threadUserB2.join();
        threadUserB3.join();
        threadUserB4.join();
    }
}
