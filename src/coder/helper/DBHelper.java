package coder.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBHelper {


    private DBHelper() {
    }

    public static Connection getConn() {
        Properties properties = new Properties();
        Connection con = null;
        try {
            properties.load(new FileInputStream(new DBHelper().getClass().getResource("../../resources/db.properties").getPath()));
            Class.forName(properties.getProperty("jdbc.driver"));
            con = DriverManager.getConnection(
                    properties.getProperty("jdbc.url"),
                    properties.getProperty("jdbc.user"),
                    properties.getProperty("jdbc.password")
            );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void closeConn(Connection con, PreparedStatement ps, ResultSet set){
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(set != null){
                try {
                    set.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    }
}

