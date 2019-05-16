package coder.controllers;


import coder.models.Table;
import coder.models.services.TableService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TableController implements Initializable {

    @FXML
    private FlowPane tables_flow_pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableService tableService = new TableService();
        List<Table> tables = tableService.getAllTable();

        for (Table table : tables) {
            Button button = new Button(String.valueOf(table.getId()));
            button.setPrefHeight(100);
            button.setPrefWidth(100);
            button.getStyleClass().add("table_button");
            button.setOnAction(e -> System.out.println(table.getId()));
            tables_flow_pane.getChildren().add(button);
        }
    }
}
