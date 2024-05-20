package com.dbcourse.dbproject;
import javafx.beans.property.*;
//TODO remove un necessary properties
public class ResultData {
    private final LongProperty typeAUsers;
    private final LongProperty typeBUsers;
    private final DoubleProperty typeADeadlocks;
    private final DoubleProperty typeBDeadlocks;
    private final DoubleProperty avgDurationTypeA;
    private final DoubleProperty avgDurationTypeB;

    public ResultData(long typeAUsers, long typeBUsers, double typeADeadlocks, double typeBDeadlocks,
                      double avgDurationTypeA, double avgDurationTypeB) {
        this.typeAUsers = new SimpleLongProperty(typeAUsers);
        this.typeBUsers = new SimpleLongProperty(typeBUsers);
        this.typeADeadlocks = new SimpleDoubleProperty(typeADeadlocks);
        this.typeBDeadlocks = new SimpleDoubleProperty(typeBDeadlocks);

        this.avgDurationTypeA = new SimpleDoubleProperty(avgDurationTypeA);
        this.avgDurationTypeB = new SimpleDoubleProperty(avgDurationTypeB);
    }

    // Getters
    public long getTypeAUsers() {
        return typeAUsers.get();
    }

    public long getTypeBUsers() {
        return typeBUsers.get();
    }

    public double getTypeADeadlocks() {
        return typeADeadlocks.get();
    }

    public double getTypeBDeadlocks() {
        return typeBDeadlocks.get();
    }

    public double getAvgDurationTypeA() {
        return avgDurationTypeA.get();
    }

    public double getAvgDurationTypeB() {
        return avgDurationTypeB.get();
    }
}