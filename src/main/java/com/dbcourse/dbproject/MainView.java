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
    ObservableList<ResultData> readUncommittedData = FXCollections.observableArrayList();
    @FXML
    private TableView<ResultData> readCommittedTable;
    ObservableList<ResultData> readCommittedData = FXCollections.observableArrayList();
    @FXML
    private TableView<ResultData> repeatableReadTable;
    ObservableList<ResultData> repeatableReadData = FXCollections.observableArrayList();
    @FXML
    private TableView<ResultData> serializableTable;
    ObservableList<ResultData> serializableData = FXCollections.observableArrayList();

    @FXML
    private Button runButton;

    @FXML
    private void runButtonClick(ActionEvent event) {
        // Open dialog
        UserInputDialog userInputDialog = new UserInputDialog();
        if (userInputDialog.showAndWait()) {
            int typeAUsers = userInputDialog.getTypeAUsers();
            int typeBUsers = userInputDialog.getTypeBUsers();

            // Assuming TransactionRunner has a static method runSimulations(int typeAUsers, int typeBUsers)
            try {
                TransactionRunner.runSimulations(typeAUsers, typeBUsers);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Update the tables with placeholder data (this should be replaced with actual results from the simulations)
            readUncommittedData.add(randomData(typeAUsers, typeBUsers));
            readCommittedData.add(randomData(typeAUsers, typeBUsers));
            repeatableReadData.add(randomData(typeAUsers, typeBUsers));
            serializableData.add(randomData(typeAUsers, typeBUsers));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize ObservableList
        readUncommittedData = FXCollections.observableArrayList();
        readCommittedData = FXCollections.observableArrayList();
        repeatableReadData = FXCollections.observableArrayList();
        serializableData = FXCollections.observableArrayList();

        // Set items to TableView
        readUncommittedTable.setItems(readUncommittedData);
        readCommittedTable.setItems(readCommittedData);
        repeatableReadTable.setItems(repeatableReadData);
        serializableTable.setItems(serializableData);
    }

    private ResultData randomData(int typeAUsers, int typeBUsers) {
        // Generate random values
        double typeADeadlocks = Math.random() * 10;
        double typeBDeadlocks = Math.random() * 10;
        double avgDurationTypeA = Math.random() * 100;
        double avgDurationTypeB = Math.random() * 100;

        // Create a ResultData object with the random values
        return new ResultData(typeAUsers, typeBUsers, typeADeadlocks, typeBDeadlocks, avgDurationTypeA, avgDurationTypeB);
    }
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
