package coder.models.services;

import coder.helper.DBHelper;
import coder.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();

        Connection con = DBHelper.getConn();

        String query = "SELECT * FROM users";
        PreparedStatement ps = null;
        ResultSet set = null;
        try {
            ps = con.prepareStatement(query);
            set = ps.executeQuery();

            while (set.next()) {
                users.add(
                        new User(
                                set.getInt("id"),
                                set.getInt("role"),
                                set.getBoolean("role"),
                                set.getString("username"),
                                set.getString("password")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBHelper.closeConn(con, ps, set);

        return users;
    }

    public boolean loginUser(String username, String password) {
        boolean bol = false;

        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;
        ResultSet set = null;

        String query = "SELECT * FROM users WHERE username=? AND password=?";

        try {
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);

            set = ps.executeQuery();

            if (set.next()) {
                bol = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        DBHelper.closeConn(con, ps, set);

        return bol;
    }

    public boolean saveUser(User user) {
        boolean condition = false;

        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;

        String sql = "INSERT INTO users (username,password,enabled,role) VALUE (?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setBoolean(3, user.isEnabled());
            ps.setInt(4, user.getRole());

            int result = ps.executeUpdate();
            if (result == 1) {
                condition = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBHelper.closeConn(con, ps, null);

        return condition;
    }

    public boolean updateUser(User user) {
        boolean condition = false;

        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;

        String sql = "UPDATE users SET username=?, password=?, enabled=?,role=? where id=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setBoolean(3, user.isEnabled());
            ps.setInt(4, user.getRole());
            ps.setInt(5, user.getId());

            int result = ps.executeUpdate();
            if (result == 1) {
                condition = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBHelper.closeConn(con, ps, null);

        return condition;
    }

    public boolean deleteUser(int id) {
        boolean condition = false;

        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;

        String sql = "DELETE FROM users where id=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int result = ps.executeUpdate();
            if (result == 1) {
                condition = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBHelper.closeConn(con, ps, null);

        return condition;
    }

}
