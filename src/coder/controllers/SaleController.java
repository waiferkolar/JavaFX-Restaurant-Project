package coder.controllers;


import coder.Main;
import coder.helper.MyDialog;
import coder.models.Dish;
import coder.models.Order;
import coder.models.Table;
import coder.models.services.DishService;
import coder.models.services.OrderService;
import coder.models.services.TableService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SaleController implements Initializable {
    Order order = new Order(0, 0, 0, 0, 0, true);
    @FXML
    private TextField order_table_id;

    @FXML
    private TextField order_count;

    @FXML
    private ListView<String> order_table_listview;

    @FXML
    private TextField order_dish_id;

    @FXML
    private ListView<String> order_dish_listview;

    ObservableList<String> obDishes = FXCollections.observableArrayList();
    ObservableList<String> obTables = FXCollections.observableArrayList();

    @FXML
    void startOrder(ActionEvent event) {

        OrderService orderService = new OrderService();

        int oc = Integer.parseInt(order_count.getText());
        int tableId = Integer.parseInt(order_table_id.getText());
        int dishId = Integer.parseInt(order_dish_id.getText());

        order.setCount(oc);
        order.setTable_id(tableId);
        order.setDish_id(dishId);

        if (orderService.addOrder(order)) {
            MyDialog.show("Success");
        } else {
            MyDialog.show("Fail to Insert Order!");
        }

    }

    @FXML
    void showOrders(ActionEvent event) throws IOException {
        SplitPane active_order_panel = FXMLLoader.load(getClass().getResource("../views/active_orders.fxml"));
        Main.getBorderPane().setCenter(active_order_panel);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableService tableService = new TableService();
        List<Table> tables = tableService.getAllTable();

        int price = 0;


        DishService dishService = new DishService();
        List<Dish> dishes = dishService.getAllDish();


        for (Table table : tables) {
            obTables.add(String.valueOf(table.getId()));
        }
        for (Dish dish : dishes) {
            obDishes.add(dish.getName());
        }

        order_table_listview.getItems().addAll(obTables);
        order_dish_listview.getItems().addAll(obDishes);

        order_table_listview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            int ind = order_table_listview.getSelectionModel().getSelectedIndex();
            Table table = tables.get(ind);
            order_table_id.setText(String.valueOf(table.getId()));
        });

        order_dish_listview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            int ind = order_dish_listview.getSelectionModel().getSelectedIndex();
            Dish dish = dishes.get(ind);
            order_dish_id.setText(String.valueOf(dish.getId()));
            order.setPrice(dish.getPrice());
        });


    }
}
