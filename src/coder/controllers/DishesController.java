package coder.controllers;

import coder.models.Dish;
import coder.models.services.DishService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DishesController implements Initializable {

    @FXML
    private TableColumn<Dish, Integer> col_dish_price;

    @FXML
    private TableColumn<Dish, Boolean> col_dish_enabled;

    @FXML
    private TableColumn<Dish, Integer> col_dish_id;

    @FXML
    private TableColumn<Dish, String> col_dish_name;

    @FXML
    private TableView<Dish> dishTable;

    ObservableList<Dish> obDishes = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DishService dishService = new DishService();

        List<Dish> dishes = dishService.getAllDish();

        for (Dish dish : dishes) {
            obDishes.add(dish);
        }

        col_dish_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_dish_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_dish_enabled.setCellValueFactory(new PropertyValueFactory<>("enabled"));
        col_dish_name.setCellValueFactory(new PropertyValueFactory<>("name"));

        dishTable.setItems(obDishes);
        addActionTableColumn();
    }

    public void addActionTableColumn() {
        TableColumn<Dish, Void> actionColumn = new TableColumn<>("Action");

        Callback<TableColumn<Dish, Void>, TableCell<Dish, Void>> factory =
                new Callback<TableColumn<Dish, Void>, TableCell<Dish, Void>>() {

                    @Override
                    public TableCell<Dish, Void> call(TableColumn<Dish, Void> param) {
                        final TableCell<Dish, Void> cell = new TableCell<Dish, Void>() {
                            final Button button = new Button("action");

                            {
                                button.setOnAction(e -> {
                                    int ind = dishTable.getSelectionModel().getFocusedIndex();
                                    if (ind != -1) {
                                        System.out.println(ind);
                                        Dish dish = obDishes.get(ind);
                                        System.out.println(dish);
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
        dishTable.getColumns().add(actionColumn);
    }
}
