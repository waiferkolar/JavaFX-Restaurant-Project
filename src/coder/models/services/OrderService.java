package coder.models.services;

import coder.helper.DBHelper;
import coder.models.Order;
import coder.models.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    public boolean addOrder(Order order) {
        boolean condition = false;

        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;

        String query = "INSERT INTO orders (table_id,dish_id,price,count) VALUES (?,?,?,?)";

        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, order.getTable_id());
            ps.setInt(2, order.getDish_id());
            ps.setInt(3, order.getPrice());
            ps.setInt(4, order.getCount());

            int result = ps.executeUpdate();

            if (result == 1)
                condition = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBHelper.closeConn(con, ps, null);


        return condition;
    }

    public List<OrderDetail> getOrderDetailByTableId(int id) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String query = "SELECT \n" +
                "\td.name as name,\n" +
                "    o.price,\n" +
                "    o.count\n" +
                "FROM \n" +
                "\torders as o \n" +
                "RIGHT JOIN \n" +
                "\tdishes as d \n" +
                "ON \n" +
                "\to.dish_id=d.id \n" +
                "WHERE \n" +
                "\to.table_id=?";

        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;
        ResultSet set = null;

        try {
            ps = con.prepareStatement(query);

            ps.setInt(1, id);

            set = ps.executeQuery();
            while (set.next()) {
                orderDetails.add(new OrderDetail(
                        id,
                        set.getInt("price"),
                        set.getInt("count"),
                        set.getInt("price") * set.getInt("count"),
                        set.getString("name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBHelper.closeConn(con, ps, set);

        return orderDetails;
    }

    public List<OrderDetail> getOrderBetweenDate(String start, String end) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String query = "SELECT \n" +
                "\to.table_id,\n" +
                "    o.count,\n" +
                "    o.price,\n" +
                "    d.name \n" +
                "FROM \n" +
                "\torders as o \n" +
                "LEFT JOIN \n" +
                "\tdishes as d \n" +
                "ON \n" +
                "\to.dish_id=d.id\n" +
                "WHERE \n" +
                "\to.created_at \n" +
                "BETWEEN\n" +
                "\t ? AND ?";

        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;
        ResultSet set = null;

        try {
            ps = con.prepareStatement(query);

            ps.setString(1, start);
            ps.setString(2, end);

            set = ps.executeQuery();
            while (set.next()) {
                orderDetails.add(new OrderDetail(
                        set.getInt("table_id"),
                        set.getInt("price"),
                        set.getInt("count"),
                        set.getInt("price") * set.getInt("count"),
                        set.getString("name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBHelper.closeConn(con, ps, set);

        return orderDetails;
    }


}
