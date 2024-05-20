package com.dbcourse.dbproject;

import java.util.Vector;

public class TransactionRunner{
    User userA = new TypeAUser();
    User userB = new TypeBUser();
    long Acount;
    long Bcount;

    public double averageBTime = 0;
    public double averageATime = 0;

    double averageRunTime;
    Vector<Thread> threads = new Vector<>();
    public int aDeadlocksCount;
    public int bDeadlocksCount;
    int isolationLevel;

    public TransactionRunner(long Acount, long Bcount, int isolationLevel){
        this.Acount = Acount;
        this.Bcount = Bcount;
        this.isolationLevel = isolationLevel;
    }

    public void run(){
        for (int i = 0; i < this.Acount; i++) {
            threads.add(new ThreadUserB(userB, isolationLevel));
            threads.add(new ThreadUserA(userA, isolationLevel ));
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
                System.out.println("Thread Interrupt----------------------------------------------------------------");
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
