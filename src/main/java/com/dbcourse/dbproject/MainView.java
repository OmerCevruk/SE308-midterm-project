package com.dbcourse.dbproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Dialog;
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
    private Button Run;

    @FXML
    private void runButtonClick(ActionEvent event) {
        // Open dialog
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Run Dialog");
        dialog.setContentText("This is a dialog opened when the Run button is clicked.");
        dialog.showAndWait();
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
