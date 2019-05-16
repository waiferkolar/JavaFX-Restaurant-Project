package coder.models.services;

import coder.helper.DBHelper;
import coder.models.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableService {

    public boolean saveTable(Table table) {
        boolean condition = false;
        String query = "INSERT INTO tables (enabled,charge,chairs) VALUES (?,?,?)";

        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(query);
            ps.setBoolean(1, table.isEnable());
            ps.setInt(2, table.getCharge());
            ps.setInt(3, table.getChairs());

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

    public List<Table> getAllTable() {
        List<Table> tables = new ArrayList<>();

        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;
        ResultSet set = null;

        String query = "SELECT * FROM tables";

        try {
            ps = con.prepareStatement(query);

            set = ps.executeQuery();

            while (set.next()) {
                tables.add(new Table(
                        set.getInt("id"),
                        set.getInt("charge"),
                        set.getInt("chairs"),
                        set.getBoolean("enabled")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        DBHelper.closeConn(con, ps, set);


        return tables;
    }

    public List<Table> getAllActiveTable() {
        List<Table> tables = new ArrayList<>();

        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;
        ResultSet set = null;

        String query = "SELECT * FROM tables WHERE enabled=?";

        try {
            ps = con.prepareStatement(query);
            ps.setBoolean(1, true);
            set = ps.executeQuery();

            while (set.next()) {
                tables.add(new Table(
                        set.getInt("id"),
                        set.getInt("charge"),
                        set.getInt("chairs"),
                        set.getBoolean("enabled")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        DBHelper.closeConn(con, ps, set);


        return tables;
    }

    public boolean updateTable(Table table) {
        boolean condition = false;
        String query = "UPDATE tables SET enabled=?, charge=?, chairs=? WHERE id=?";

        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(query);
            ps.setBoolean(1, table.isEnable());
            ps.setInt(2, table.getCharge());
            ps.setInt(3, table.getChairs());
            ps.setInt(4, table.getId());

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

    public boolean deleteTable(int id) {
        boolean condition = false;
        String query = "DELETE From tables WHERE id=?";

        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(query);
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
