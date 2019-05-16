package coder.controllers;

import coder.helper.Printty;
import coder.models.DishPrint;
import coder.models.OrderDetail;
import coder.models.Table;
import coder.models.services.OrderService;
import coder.models.services.TableService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.print.PrinterException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ActiveOrders implements Initializable {
    ObservableList<String> obTables = FXCollections.observableArrayList();
    ObservableList<OrderDetail> orderDetailObservableList = FXCollections.observableArrayList();
    @FXML
    private TableView<OrderDetail> active_order_tableview;

    @FXML
    private TableColumn<OrderDetail, Integer> active_order_col_total;

    @FXML
    private TableColumn<OrderDetail, String> active_roder_col_name;

    @FXML
    private TableColumn<OrderDetail, Integer> active_order_col_count;

    @FXML
    private ListView<String> active_order_listview;

    @FXML
    private TableColumn<OrderDetail, Integer> active_order_col_price;

    @FXML
    private TableColumn<OrderDetail, Integer> active_order_col_id;

    @FXML
    private Label active_order_grand_total;


    @FXML
    private Label active_order_tax;

    @FXML
    private Label active_order_total;

    int currentIndex = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableService tableService = new TableService();
        List<Table> tables = tableService.getAllActiveTable();
        for (Table table : tables) {
            obTables.add(String.valueOf(table.getId()));
        }

        active_order_listview.setItems(obTables);

        active_order_listview.getSelectionModel().selectedItemProperty()
                .addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                        int ind = active_order_listview.getSelectionModel().getSelectedIndex();
                        generateOrdersByTable(Integer.parseInt(newValue));
                        currentIndex = Integer.parseInt(newValue);
                    }
                });

    }

    @FXML
    void billOut(ActionEvent event) {
        OrderService orderService = new OrderService();
        List<OrderDetail> orderDetails = orderService.getOrderDetailByTableId(currentIndex);
        List<DishPrint> dishPrints = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            dishPrints.add(new DishPrint(
                    orderDetail.getName(),
                    orderDetail.getPrice(),
                    orderDetail.getCount(),
                    orderDetail.getTotal()
            ));
        }

        try {
            Printty.print(dishPrints);
        } catch (PrinterException e) {
            e.printStackTrace();
        }

    }

    public void generateOrdersByTable(int id) {
        OrderService orderService = new OrderService();


        active_order_col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        active_roder_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        active_order_col_count.setCellValueFactory(new PropertyValueFactory<>("count"));
        active_order_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        active_order_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));

        active_order_tableview.getItems().remove(0, active_order_tableview.getItems().size());
        List<OrderDetail> orderDetails = orderService.getOrderDetailByTableId(id);

        int total = 0;

        for (OrderDetail orderDetail : orderDetails) {
            orderDetailObservableList.add(orderDetail);
            total += orderDetail.getPrice() * orderDetail.getCount();
        }
        active_order_tableview.setItems(orderDetailObservableList);

        active_order_grand_total.setText("Grand Total : " + (total + 500));
        active_order_tax.setText("Tax 500");
        active_order_total.setText("Total : " + total);

    }
}
