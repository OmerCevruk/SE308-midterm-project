package com.dbcourse.dbproject;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

        // Load data from the database and set it to the table
        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        List<ResultData> results = new ArrayList<>();
        String query = "SELECT * FROM YourTable"; // Update with your actual query

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int typeAUsers = rs.getInt("typeAUsers");
                int typeBUsers = rs.getInt("typeBUsers");
                double typeADeadlocks = rs.getDouble("typeADeadlocks");
                double typeBDeadlocks = rs.getDouble("typeBDeadlocks");
                int typeAThreads = rs.getInt("typeAThreads");
                int typeBThreads = rs.getInt("typeBThreads");
                double avgDurationTypeA = rs.getDouble("avgDurationTypeA");
                double avgDurationTypeB = rs.getDouble("avgDurationTypeB");

                results.add(new ResultData(typeAUsers, typeBUsers, typeADeadlocks, typeBDeadlocks,
                        typeAThreads, typeBThreads, avgDurationTypeA, avgDurationTypeB));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        resultTable.getItems().setAll(results);
    }
}
