package com.dbcourse.dbproject;

import java.sql.SQLException;
import java.util.concurrent.Semaphore;

public class ThreadUserA extends Thread{
    Semaphore semaphore = new Semaphore(1);
    User user;
    public static double totalTime;
    public static int deadlocksCount;

    public int getIsolationLevel() {
        return isolationLevel;
    }

    public void setIsolationLevel(int isolationLevel) {
        this.isolationLevel = isolationLevel;
    }
    int isolationLevel;
    public ThreadUserA(User user, int isolationLevel){
        this.isolationLevel = isolationLevel;
        this.user = user;
    }

    public void run(){
        try {
            //runs the transaction and counts deadlocks
            double time = user.transaction(this.isolationLevel);
            semaphore.acquire();
            totalTime += time;
            semaphore.release();
        } catch (SQLException e) {
            System.out.println("Error running transaction for user A 2: " + e.getMessage() + e.getErrorCode());
            if(e.getErrorCode() == 1205) {
                System.out.println("Deadlock detected. Rerun the transaction.");// sql server always blames the type a for some reason
                try {
                    semaphore.acquire();
                    deadlocksCount++; // Count the deadlock
                    semaphore.release();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }

        } catch (InterruptedException e) {
            System.out.println("Error running transaction for user A: " + e.getMessage());
        }
    }
}
