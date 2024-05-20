package com.dbcourse.dbproject;

import java.sql.Connection;
import java.util.Vector;

public class TransactionRunner{
    User userA = new TypeAUser();
    User userB = new TypeBUser();
    int Acount;
    int Bcount;

    public double averageBTime = 0;
    public double averageATime = 0;

    double averageRunTime;
    Vector<Thread> threads = new Vector<>();
    public int aDeadlocksCount;
    public int bDeadlocksCount;
    int isolationLevel;

    public TransactionRunner(int Acount, int Bcount,int isolationLevel){
        this.Acount = Acount;
        this.Bcount = Bcount;
        this.isolationLevel = isolationLevel;
    }

    public void run(){
        for (int i = 0; i < this.Acount; i++) {
            threads.add(new ThreadUserA(userA, isolationLevel ));
            threads.add(new ThreadUserB(userB, isolationLevel));
        }

        for(Thread thread : threads){
            thread.start();
        }

        // Wait for each thread to finish
        for(Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // set the values that are open to other classes
        averageATime = ThreadUserA.totalTime/Acount;
        averageBTime = ThreadUserB.totalTime/Acount;
        aDeadlocksCount = ThreadUserA.deadlocksCount;
        bDeadlocksCount = ThreadUserB.deadlocksCount;

        //reset static variables to zero so it does not build up
        ThreadUserA.totalTime = 0;
        ThreadUserB.totalTime = 0;
        ThreadUserA.deadlocksCount = 0;
        ThreadUserB.deadlocksCount = 0;

    }


}
