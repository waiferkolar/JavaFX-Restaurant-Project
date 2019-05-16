package coder.models.services;

import coder.helper.DBHelper;
import coder.models.Dish;
import coder.models.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishService {

    public Dish getDishById(int id) {
        Dish dish = null;

        String query = "SELECT * FROM dishes where id=?";

        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;
        ResultSet set = null;

        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, id);

            set = ps.executeQuery();

            while (set.next()) {
                dish = new Dish(
                        set.getInt("id"),
                        set.getInt("price"),
                        set.getString("name"),
                        set.getBoolean("enabled")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBHelper.closeConn(con, ps, set);

        return dish;
    }

    public boolean updateDish(Dish dish) {

        boolean condition = false;

        String query = "UPDATE dishes SET name=?, price=?, enabled=? where id=?";


        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;

        try {

            ps = con.prepareStatement(query);
            ps.setString(1, dish.getName());
            ps.setInt(2, dish.getPrice());
            ps.setBoolean(3, dish.isEnabled());
            ps.setInt(4, dish.getId());

            int result = ps.executeUpdate();

            if (result == 1)
                condition = true;


        } catch (SQLException e) {
            e.printStackTrace();
        }


        DBHelper.closeConn(con, ps, null);


        return condition;
    }

    public List<Dish> getAllDish() {
        List<Dish> dishes = new ArrayList<>();

        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;
        ResultSet set = null;

        String query = "SELECT * FROM dishes";

        try {
            ps = con.prepareStatement(query);

            set = ps.executeQuery();

            while (set.next()) {
                dishes.add(new Dish(
                        set.getInt("id"),
                        set.getInt("price"),
                        set.getString("name"),
                        set.getBoolean("enabled")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        DBHelper.closeConn(con, ps, set);


        return dishes;
    }

    public boolean saveDish(Dish dish) {

        boolean condition = false;

        String query = "INSERT INTO dishes (name,price,enabled) VALUES (?,?,?)";


        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;

        try {

            ps = con.prepareStatement(query);
            ps.setString(1, dish.getName());
            ps.setInt(2, dish.getPrice());
            ps.setBoolean(3, dish.isEnabled());

            int result = ps.executeUpdate();

            if (result == 1)
                condition = true;


        } catch (SQLException e) {
            e.printStackTrace();
        }


        DBHelper.closeConn(con, ps, null);


        return condition;
    }

    public boolean deleteDish(int id) {

        boolean condition = false;

        String query = "DELETE FROM dishes WHERE id=?";


        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;

        try {

            ps = con.prepareStatement(query);
            ps.setInt(1, id);

            int result = ps.executeUpdate();

            if (result == 1)
                condition = true;


        } catch (SQLException e) {
            e.printStackTrace();
        }


        DBHelper.closeConn(con, ps, null);


        return condition;
    }
}
