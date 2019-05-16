package coder.controllers;

import coder.models.SaleHsitory;
import coder.models.services.SaleHistoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SlaeHistoryController implements Initializable {

    @FXML
    private TableView<SaleHsitory> sale_history_table;

    @FXML
    private TableColumn<SaleHsitory, String> sale_history_table_col_date;

    @FXML
    private TableColumn<SaleHsitory, Integer> sale_history_table_col_total_sale;

    ObservableList<SaleHsitory> obHistories = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sale_history_table_col_date.setCellValueFactory(new PropertyValueFactory<>("saleDate"));
        sale_history_table_col_total_sale.setCellValueFactory(new PropertyValueFactory<>("saleTotal"));

        SaleHistoryService service = new SaleHistoryService();
        List<SaleHsitory> histories = service.getAllHistory();

        for (SaleHsitory hsitory : histories) {
            obHistories.add(hsitory);
        }
        sale_history_table.setItems(obHistories);
    }
}