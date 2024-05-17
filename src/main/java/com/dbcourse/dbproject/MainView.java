package com.dbcourse.dbproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainView implements Initializable {
    @FXML
    private TableView<ResultData> readUncommittedTable;
    ObservableList<ResultData> readUncommitedData = FXCollections.observableArrayList();
    @FXML
    private TableView<ResultData> readCommittedTable;
    ObservableList<ResultData> readCommitedData = FXCollections.observableArrayList();
    @FXML
    private TableView<ResultData> repeatableReadTable;
    ObservableList<ResultData> repeatableReadData = FXCollections.observableArrayList();
    @FXML
    private TableView<ResultData> serializableTable;
    ObservableList<ResultData> serializableData = FXCollections.observableArrayList();

    @FXML
    private Button Run;

    @FXML
    private void runButtonClick(ActionEvent event) {
        // Open dialog
        readUncommitedData.add(randomData());
        readCommitedData.add(randomData());
        repeatableReadData.add(randomData());
        serializableData.add(randomData());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize ObservableList

        readUncommitedData = FXCollections.observableArrayList();
        readCommitedData = FXCollections.observableArrayList();
        repeatableReadData = FXCollections.observableArrayList();
        serializableData = FXCollections.observableArrayList();
        // Set items to TableView
        readUncommittedTable.setItems(readUncommitedData);
        readCommittedTable.setItems(readCommitedData);
        repeatableReadTable.setItems(repeatableReadData);
        serializableTable.setItems(serializableData);
    }

    private ResultData randomData(){
            // Generate random values
            int typeAUsers = (int) (Math.random() * 100);
            int typeBUsers = (int) (Math.random() * 100);
            double typeADeadlocks = Math.random() * 10;
            double typeBDeadlocks = Math.random() * 10;
            double avgDurationTypeA = Math.random() * 100;
            double avgDurationTypeB = Math.random() * 100;

            // Create a ResultData object with the random values
            ResultData randomData = new ResultData(typeAUsers, typeBUsers, typeADeadlocks, typeBDeadlocks, avgDurationTypeA, avgDurationTypeB);
            return randomData;
    }
//    private void loadDataFromDatabase() {
//        List<ResultData> results = new ArrayList<>();
//        String query = "SELECT * FROM YourTable"; // Update with your actual query
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query);
//             ResultSet rs = stmt.executeQuery()) {
//
//            while (rs.next()) {
//                int typeAUsers = rs.getInt("typeAUsers");
//                int typeBUsers = rs.getInt("typeBUsers");
//                double typeADeadlocks = rs.getDouble("typeADeadlocks");
//                double typeBDeadlocks = rs.getDouble("typeBDeadlocks");
//                int typeAThreads = rs.getInt("typeAThreads");
//                int typeBThreads = rs.getInt("typeBThreads");
//                double avgDurationTypeA = rs.getDouble("avgDurationTypeA");
//                double avgDurationTypeB = rs.getDouble("avgDurationTypeB");
//
//                results.add(new ResultData(typeAUsers, typeBUsers, typeADeadlocks, typeBDeadlocks,
//                        typeAThreads, typeBThreads, avgDurationTypeA, avgDurationTypeB));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        resultTable.getItems().setAll(results);
//    }
}
