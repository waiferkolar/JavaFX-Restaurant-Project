package coder.controllers;


import coder.Main;
import coder.helper.MyDialog;
import coder.models.Table;
import coder.models.services.TableService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TableCreateController implements Initializable {

    @FXML
    private TextField admn_ct_charge;

    @FXML
    private ComboBox<String> admin_ct_enabled;

    @FXML
    private TextField admin_ct_chair;

    Table table = Main.table;

    @FXML
    void startCreateTableNow(ActionEvent event) throws IOException {
        if (!admin_ct_enabled.getSelectionModel().getSelectedItem().contentEquals("0")) {
            table.setEnable(true);
        }

        table.setChairs(Integer.parseInt(admin_ct_chair.getText()));
        table.setCharge(Integer.parseInt(admn_ct_charge.getText()));

        TableService tableService = new TableService();

        if (table.getId() != 0) {
            if (!tableService.updateTable(table)) {
                MyDialog.show("Table Update Fil");
            }
        } else {
            if (!tableService.saveTable(table)) {
                MyDialog.show("Table Create Fil");
            }
        }
        VBox admin_table_table_panel = FXMLLoader.load(getClass().getResource("../views/admin_table.fxml"));
        Main.getBorderPane().setCenter(admin_table_table_panel);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        admin_ct_enabled.getItems().addAll("0", "1");
        admin_ct_enabled.getSelectionModel().select(0);


        admn_ct_charge.setText(String.valueOf(table.getCharge()));
        admin_ct_chair.setText(String.valueOf(table.getChairs()));

        if (table.isEnable()) {
            admin_ct_enabled.getSelectionModel().select(1);
        }
    }
}
