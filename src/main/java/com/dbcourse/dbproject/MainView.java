package com.dbcourse.dbproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
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
    private ChoiceBox<String> isolationLevel;
    ObservableList<String> isolationLevels = FXCollections.observableArrayList();

    @FXML
    private Button runButton;

    @FXML
    private TextField typeAUser;

    @FXML
    private TextField typeBUser;

    @FXML
    private void runButtonClick(ActionEvent event) {
        int numA = Integer.parseInt(typeAUser.getText());
        int numB = Integer.parseInt(typeBUser.getText());
        String selectedIsolationLevel = isolationLevel.getValue();

        //ugly looking if else block does the job
        if (selectedIsolationLevel.equals("SERIALIZABLE")) {
            TransactionRunner runner = new TransactionRunner(numA,numB,Connection.TRANSACTION_SERIALIZABLE);
            runner.run();
            serializableData.add(new ResultData(runner.Acount, runner.Bcount,runner.aDeadlocksCount,runner.bDeadlocksCount,runner.averageATime, runner.averageBTime));
        } else if (selectedIsolationLevel.equals("READ COMMITTED")) {
            TransactionRunner runner = new TransactionRunner(numA,numB,Connection.TRANSACTION_READ_COMMITTED);
            runner.run();
            readCommittedData.add(new ResultData(runner.Acount, runner.Bcount,runner.aDeadlocksCount,runner.bDeadlocksCount,runner.averageATime, runner.averageBTime));
        } else if (selectedIsolationLevel.equals("REPEATABLE READ")) {
            TransactionRunner runner = new TransactionRunner(numA,numB,Connection.TRANSACTION_REPEATABLE_READ);
            runner.run();
            repeatableReadData.add(new ResultData(runner.Acount, runner.Bcount,runner.aDeadlocksCount,runner.bDeadlocksCount,runner.averageATime, runner.averageBTime));
        } else if (selectedIsolationLevel.equals("READ UNCOMMITTED")) {
            TransactionRunner runner = new TransactionRunner(numA,numB,Connection.TRANSACTION_READ_UNCOMMITTED);
            runner.run();
            readUncommittedData.add(new ResultData(runner.Acount, runner.Bcount,runner.aDeadlocksCount,runner.bDeadlocksCount,runner.averageATime, runner.averageBTime));
        }  else {
            System.out.println("Invalid isolation level");
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

        isolationLevels.add("SERIALIZABLE");
        isolationLevels.add("READ COMMITTED");
        isolationLevels.add("REPEATABLE READ");
        isolationLevels.add("READ UNCOMMITTED");

        // set choice box
        isolationLevel.setItems(isolationLevels);
        isolationLevel.setDisable(false);
    }
}

