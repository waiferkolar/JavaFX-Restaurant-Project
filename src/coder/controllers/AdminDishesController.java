package coder.controllers;


import coder.Main;
import coder.models.Dish;
import coder.models.User;
import coder.models.services.DishService;
import coder.models.services.UserService;
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

public class AdminDishesController implements Initializable {

    @FXML
    private TableColumn<Dish, Boolean> admin_dish_col_enable;

    @FXML
    private TableColumn<Dish, String> admin_dish_col_name;

    @FXML
    private TableColumn<Dish, Integer> admin_dish_col_price;

    @FXML
    private TableColumn<Dish, Integer> admin_dish_col_id;

    @FXML
    private TableView<Dish> admin_dish_table;


    @FXML
    void startCreateDish(ActionEvent event) throws IOException {
        Main.dish = new Dish(0, 0, "", false);
        AnchorPane dish_create_panel = FXMLLoader.load(getClass().getResource("../views/dish_create.fxml"));
        Main.getBorderPane().setCenter(dish_create_panel);
    }

    ObservableList<Dish> obDishes = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        admin_dish_col_enable.setCellValueFactory(new PropertyValueFactory<>("enabled"));
        admin_dish_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        admin_dish_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        admin_dish_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));

        DishService dishService = new DishService();
        List<Dish> dishes = dishService.getAllDish();

        for (Dish dish : dishes) {
            obDishes.add(dish);
        }

        admin_dish_table.setItems(obDishes);
        addDeleteColumn("Delete", "delete");
        addDeleteColumn("Edit", "edit");
    }

    public void addDeleteColumn(String columnName, String buttonName) {
        TableColumn<Dish, Void> actionColumn = new TableColumn<>(columnName);

        Callback<TableColumn<Dish, Void>, TableCell<Dish, Void>> factory =
                new Callback<TableColumn<Dish, Void>, TableCell<Dish, Void>>() {

                    @Override
                    public TableCell<Dish, Void> call(TableColumn<Dish, Void> param) {
                        final TableCell<Dish, Void> cell = new TableCell<Dish, Void>() {
                            final Button button = new Button(buttonName);

                            {
                                button.setOnAction(e -> {
                                    int ind = admin_dish_table.getSelectionModel().getFocusedIndex();
                                    if (ind != -1) {
                                        Main.dish = obDishes.get(ind);
                                        if (buttonName.contentEquals("edit")) {
                                            try {
                                                AnchorPane dish_create_panel = FXMLLoader.load(getClass().getResource("../views/dish_create.fxml"));
                                                Main.getBorderPane().setCenter(dish_create_panel);
                                            } catch (IOException e1) {
                                                e1.printStackTrace();
                                            }
                                        } else {
                                            DishService service = new DishService();
                                            service.deleteDish(Main.dish.getId());
                                            try {
                                                VBox dish_create_panel = FXMLLoader.load(getClass().getResource("../views/admin_dishes.fxml"));
                                                Main.getBorderPane().setCenter(dish_create_panel);
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
        admin_dish_table.getColumns().add(actionColumn);
    }

}
