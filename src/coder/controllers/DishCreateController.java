package coder.controllers;


import coder.Main;
import coder.helper.MyDialog;
import coder.models.Dish;
import coder.models.services.DishService;
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

public class DishCreateController implements Initializable {

    @FXML
    private ComboBox<String> dish_enabled;

    @FXML
    private TextField dish_name;

    @FXML
    private TextField dish_price;

    Dish dish = Main.dish;

    @FXML
    void createDishNow(ActionEvent event) {

        dish.setName(dish_name.getText());
        dish.setPrice(Integer.parseInt(dish_price.getText()));
        if (!dish_enabled.getSelectionModel().getSelectedItem().contentEquals("0")) {
            dish.setEnabled(true);
        }

        DishService dishService = new DishService();
        if (dish.getId() != 0) {
            if (!dishService.updateDish(dish)) {
                MyDialog.show("Dish Update Error!");
            }
        } else {
            if (!dishService.saveDish(dish)) {
                MyDialog.show("Dish Insert Error!");
            }
        }

        try {
            VBox dish_create_panel = FXMLLoader.load(getClass().getResource("../views/admin_dishes.fxml"));
            Main.getBorderPane().setCenter(dish_create_panel);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dish_enabled.getItems().addAll("0", "1");
        dish_enabled.getSelectionModel().select(0);


        if (dish.getId() != 0) {
            dish_name.setText(dish.getName());
            dish_price.setText(String.valueOf(dish.getPrice()));

            if (dish.isEnabled()) {
                dish_enabled.getSelectionModel().select(1);
            } else {
                dish_enabled.getSelectionModel().select(0);
            }
        }
    }
}