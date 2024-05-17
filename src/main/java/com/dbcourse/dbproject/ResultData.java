package com.dbcourse.dbproject;
import javafx.beans.property.*;
//TODO remove un necessary properties
public class ResultData {
    private final IntegerProperty typeAUsers;
    private final IntegerProperty typeBUsers;
    private final DoubleProperty typeADeadlocks;
    private final DoubleProperty typeBDeadlocks;
    private final DoubleProperty avgDurationTypeA;
    private final DoubleProperty avgDurationTypeB;

    public ResultData(int typeAUsers, int typeBUsers, double typeADeadlocks, double typeBDeadlocks,
                     double avgDurationTypeA, double avgDurationTypeB) {
        this.typeAUsers = new SimpleIntegerProperty(typeAUsers);
        this.typeBUsers = new SimpleIntegerProperty(typeBUsers);
        this.typeADeadlocks = new SimpleDoubleProperty(typeADeadlocks);
        this.typeBDeadlocks = new SimpleDoubleProperty(typeBDeadlocks);

        this.avgDurationTypeA = new SimpleDoubleProperty(avgDurationTypeA);
        this.avgDurationTypeB = new SimpleDoubleProperty(avgDurationTypeB);
    }

    // Getters
    public int getTypeAUsers() {
        return typeAUsers.get();
    }

    public int getTypeBUsers() {
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