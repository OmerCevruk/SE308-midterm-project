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
        TableColumn<ResultData, Integer> typeAUsersCol = new TableColumn<>("Number of Type A Users");
        typeAUsersCol.setCellValueFactory(new PropertyValueFactory<>("typeAUsers"));

        TableColumn<ResultData, Integer> typeBUsersCol = new TableColumn<>("Number of Type B Users");
        typeBUsersCol.setCellValueFactory(new PropertyValueFactory<>("typeBUsers"));

        TableColumn<ResultData, Double> typeADeadlocksCol = new TableColumn<>("Duration of Deadlocks (Type A)");
        typeADeadlocksCol.setCellValueFactory(new PropertyValueFactory<>("typeADeadlocks"));

        TableColumn<ResultData, Double> typeBDeadlocksCol = new TableColumn<>("Duration of Deadlocks (Type B)");
        typeBDeadlocksCol.setCellValueFactory(new PropertyValueFactory<>("typeBDeadlocks"));

        TableColumn<ResultData, Integer> typeAThreadsCol = new TableColumn<>("Type A Threads Encountered by Type B Users");
        typeAThreadsCol.setCellValueFactory(new PropertyValueFactory<>("typeAThreads"));

        TableColumn<ResultData, Integer> typeBThreadsCol = new TableColumn<>("Type B Threads Encountered by Type A Users");
        typeBThreadsCol.setCellValueFactory(new PropertyValueFactory<>("typeBThreads"));

        TableColumn<ResultData, Double> avgDurationTypeACol = new TableColumn<>("Average Duration of Type A Users");
        avgDurationTypeACol.setCellValueFactory(new PropertyValueFactory<>("avgDurationTypeA"));

        TableColumn<ResultData, Double> avgDurationTypeBCol = new TableColumn<>("Average Duration of Type B Users");
        avgDurationTypeBCol.setCellValueFactory(new PropertyValueFactory<>("avgDurationTypeB"));

        resultTable.getColumns().addAll(typeAUsersCol, typeBUsersCol, typeADeadlocksCol, typeBDeadlocksCol,
                typeAThreadsCol, typeBThreadsCol, avgDurationTypeACol, avgDurationTypeBCol);
    }
}
