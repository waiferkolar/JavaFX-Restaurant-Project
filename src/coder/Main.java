package coder;

import coder.models.Dish;
import coder.models.Table;
import coder.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage stage;
    public static BorderPane borderPane;

    public static User user = new User(0, 0, false, "", "");
    public static Dish dish = new Dish(0, 0, "", false);
    public static Table table = new Table(0, 0, 0, false);

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        borderPane = FXMLLoader.load(getClass().getResource("views/main.fxml"));
        AnchorPane login_panel = FXMLLoader.load(getClass().getResource("views/login.fxml"));
        this.stage.setTitle("My Restaurant");
        this.stage.setScene(new Scene(borderPane));
        this.borderPane.setCenter(login_panel);
        this.stage.show();
    }

    public static Stage getStage() {
        return stage;
    }

    public static BorderPane getBorderPane() {
        return borderPane;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
