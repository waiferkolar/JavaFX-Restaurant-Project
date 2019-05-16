package coder.controllers;


import coder.Main;
import coder.models.Dish;
import coder.models.Table;
import coder.models.services.TableService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminTableController implements Initializable {

    @FXML
    private TableView<Table> admin_table_table;

    @FXML
    private TableColumn<Table, Boolean> admin_table_table_col_enabled;

    @FXML
    private TableColumn<Table, Integer> admin_table_table_col_id;

    @FXML
    private TableColumn<Table, Integer> admin_table_table_col_chair;

    @FXML
    private TableColumn<Table, Integer> admin_table_table_col_charge;

    ObservableList<Table> obTables = FXCollections.observableArrayList();

    @FXML
    void createTable(ActionEvent event) throws IOException {
        Main.table = new Table(0, 0, 0, false);
        AnchorPane admin_table_create_panel = FXMLLoader.load(getClass().getResource("../views/table_create.fxml"));
        Main.getBorderPane().setCenter(admin_table_create_panel);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableService tableService = new TableService();
        List<Table> tables = tableService.getAllTable();

        for (Table table : tables) {
            obTables.add(table);
        }

        admin_table_table_col_enabled.setCellValueFactory(new PropertyValueFactory<>("enabled"));
        admin_table_table_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        admin_table_table_col_chair.setCellValueFactory(new PropertyValueFactory<>("chairs"));
        admin_table_table_col_charge.setCellValueFactory(new PropertyValueFactory<>("charge"));

        admin_table_table.setItems(obTables);
        addDeleteColumn("Delete", "delete");
        addDeleteColumn("Edit", "edit");

    }

    public void addDeleteColumn(String columnName, String buttonName) {
        TableColumn<Table, Void> actionColumn = new TableColumn<>(columnName);

        Callback<TableColumn<Table, Void>, TableCell<Table, Void>> factory =
                new Callback<TableColumn<Table, Void>, TableCell<Table, Void>>() {

                    @Override
                    public TableCell<Table, Void> call(TableColumn<Table, Void> param) {
                        final TableCell<Table, Void> cell = new TableCell<Table, Void>() {
                            final Button button = new Button(buttonName);

                            {
                                button.setOnAction(e -> {
                                    int ind = admin_table_table.getSelectionModel().getFocusedIndex();
                                    if (ind != -1) {
                                        Main.table = obTables.get(ind);
                                        if (buttonName.contentEquals("edit")) {
                                            try {
                                                AnchorPane admin_table_create_panel = FXMLLoader.load(getClass().getResource("../views/table_create.fxml"));
                                                Main.getBorderPane().setCenter(admin_table_create_panel);
                                            } catch (IOException e1) {
                                                e1.printStackTrace();
                                            }

                                        } else {
                                            TableService service = new TableService();
                                            service.deleteTable(Main.table.getId());
                                            try {
                                                VBox admin_table_table_panel = FXMLLoader.load(getClass().getResource("../views/admin_table.fxml"));
                                                Main.getBorderPane().setCenter(admin_table_table_panel);
                                            } catch (IOException e1) {
                                                e1.printStackTrace();
                                            }

                                        }

                                    }
                                });
                            }

                            @Override
                            protected void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    setGraphic(button);
                                }
                            }
                        };
                        return cell;
                    }
                };
        actionColumn.setCellFactory(factory);
        admin_table_table.getColumns().add(actionColumn);
    }

}
