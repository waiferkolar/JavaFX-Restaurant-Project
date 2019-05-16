package coder.controllers;

import coder.Main;
import coder.models.Dish;
import coder.models.User;
import coder.models.services.DishService;
import coder.models.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    UserService service = new UserService();

    @FXML
    void showLoginPage(ActionEvent event) throws IOException {
        AnchorPane login_panel = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
        Main.getBorderPane().setCenter(login_panel);

        List<User> users = service.getAllUser();

        for (User user : users) {
            System.out.println(user.getUsername());
        }
    }

    @FXML
    void showHomePage(ActionEvent event) throws IOException {
        GridPane home_panel = FXMLLoader.load(getClass().getResource("../views/home.fxml"));
        Main.getBorderPane().setCenter(home_panel);

        DishService ds = new DishService();
        Dish dish = ds.getDishById(1);

        dish.setName("Beef Curry");

        ds.updateDish(dish);

        dish = ds.getDishById(1);

        System.out.println(dish);


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
