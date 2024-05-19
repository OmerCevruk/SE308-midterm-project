package com.dbcourse.dbproject;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserInputDialog {
    private int typeAUsers;
    private int typeBUsers;

    public UserInputDialog() {
        // Set default values
        typeAUsers = 1;
        typeBUsers = 1;
    }

    public boolean showAndWait() {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle("Input Number of Users");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label typeALabel = new Label("Number of Type A Users:");
        Spinner<Integer> typeASpinner = new Spinner<>();
        typeASpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1));

        Label typeBLabel = new Label("Number of Type B Users:");
        Spinner<Integer> typeBSpinner = new Spinner<>();
        typeBSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1));

        Button okButton = new Button("OK");
        okButton.setOnAction(e -> {
            typeAUsers = typeASpinner.getValue();
            typeBUsers = typeBSpinner.getValue();
            dialogStage.close();
        });

        gridPane.add(typeALabel, 0, 0);
        gridPane.add(typeASpinner, 1, 0);
        gridPane.add(typeBLabel, 0, 1);
        gridPane.add(typeBSpinner, 1, 1);
        gridPane.add(okButton, 1, 2);

        Scene scene = new Scene(gridPane);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();

        return true;
    }

    public int getTypeAUsers() {
        return typeAUsers;
    }

    public int getTypeBUsers() {
        return typeBUsers;
    }
}
