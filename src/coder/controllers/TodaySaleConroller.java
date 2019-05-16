package coder.controllers;


import coder.models.OrderDetail;
import coder.models.services.OrderService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class TodaySaleConroller implements Initializable {

    @FXML
    private TableColumn<OrderDetail, Integer> today_sale_table_col_price;

    @FXML
    private TableView<OrderDetail> today_sale_table;

    @FXML
    private TableColumn<OrderDetail, Integer> today_sale_table_col_count;

    @FXML
    private Label today_sale_total_lable;

    @FXML
    private TableColumn<OrderDetail, Integer> today_sale_table_col_id;

    @FXML
    private TableColumn<OrderDetail, Integer> today_sale_table_col_total;

    @FXML
    private TableColumn<OrderDetail, String> today_sale_table_col_name;
    ObservableList<OrderDetail> orderDetailsObs = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        today_sale_table_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        today_sale_table_col_count.setCellValueFactory(new PropertyValueFactory<>("count"));
        today_sale_table_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        today_sale_table_col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        today_sale_table_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));

        Date date = new Date();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        String startOfDay = localDateTime.with(LocalTime.MIN).toString();
        String endOfDay = localDateTime.with(LocalTime.MAX).toString();

        int total = 0;

        OrderService orderService = new OrderService();
        List<OrderDetail> orderDetailList = orderService.getOrderBetweenDate(startOfDay, endOfDay);

        for (OrderDetail orderDetail : orderDetailList) {
            orderDetailsObs.add(orderDetail);
            total += orderDetail.getTotal();
        }


        today_sale_table.setItems(orderDetailsObs);
        today_sale_total_lable.setText("Today Sale Total : " + total);
    }
}
