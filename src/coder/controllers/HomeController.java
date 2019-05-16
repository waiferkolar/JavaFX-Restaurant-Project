package coder.controllers;

import coder.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


import java.io.IOException;

public class HomeController {


    @FXML
    void showAllTables(ActionEvent event) throws IOException {
        FlowPane table_panel = FXMLLoader.load(getClass().getResource("../views/tables.fxml"));
        Main.getBorderPane().setCenter(table_panel);
    }

    @FXML
    void showAllDishes(ActionEvent event) throws IOException {
        TableView dish_table = FXMLLoader.load(getClass().getResource("../views/dishes.fxml"));
        Main.getBorderPane().setCenter(dish_table);
    }

    @FXML
    void showSale(ActionEvent event) throws IOException {
        HBox sale_panel = FXMLLoader.load(getClass().getResource("../views/sale.fxml"));
        Main.getBorderPane().setCenter(sale_panel);
    }

    @FXML
    void showAdminHome(ActionEvent event) throws IOException {
        VBox admin_sidebar_panel = FXMLLoader.load(getClass().getResource("../views/admin_side_bar.fxml"));
        VBox admin_user_panel = FXMLLoader.load(getClass().getResource("../views/admin_users.fxml"));

        Main.getBorderPane().setLeft(admin_sidebar_panel);
        Main.getBorderPane().setCenter(admin_user_panel);

    }
}
