package com.dbcourse.dbproject;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainView {
    @FXML
    private TableView<ResultData> resultTable;

    @FXML
    private void initialize() {
        // Create and configure the table columns
        TableColumn<ResultData, Integer> typeAUsersCol = new TableColumn<>("Number of Type A Users");
        typeAUsersCol.setCellValueFactory(new PropertyValueFactory<>("typeAUsers"));

        TableColumn<ResultData, Integer> typeBUsersCol = new TableColumn<>("Number of Type B Users");
        typeBUsersCol.setCellValueFactory(new PropertyValueFactory<>("typeBUsers"));

        TableColumn<ResultData, Double> avgDurationTypeACol = new TableColumn<>("Average Duration of Type A Users");
        avgDurationTypeACol.setCellValueFactory(new PropertyValueFactory<>("avgDurationTypeA"));

        TableColumn<ResultData, Integer> typeADeadlocksCol = new TableColumn<>("Number of Deadlocks (Type A)");
        typeADeadlocksCol.setCellValueFactory(new PropertyValueFactory<>("typeADeadlocks"));

        TableColumn<ResultData, Double> avgDurationTypeBCol = new TableColumn<>("Average Duration of Type B Users");
        avgDurationTypeBCol.setCellValueFactory(new PropertyValueFactory<>("avgDurationTypeB"));

        TableColumn<ResultData, Integer> typeBDeadlocksCol = new TableColumn<>("Number of Deadlocks (Type B)");
        typeBDeadlocksCol.setCellValueFactory(new PropertyValueFactory<>("typeBDeadlocks"));

        // Add the columns to the table
        resultTable.getColumns().addAll(
                typeAUsersCol,
                typeBUsersCol,
                avgDurationTypeACol,
                typeADeadlocksCol,
                avgDurationTypeBCol,
                typeBDeadlocksCol
        );
    }
}
