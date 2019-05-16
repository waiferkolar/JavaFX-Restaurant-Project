package coder.models.services;

import coder.helper.DBHelper;
import coder.models.SaleHsitory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleHistoryService {

    public List<SaleHsitory> getAllHistory() {
        List<SaleHsitory> histories = new ArrayList<>();

        Connection con = DBHelper.getConn();
        PreparedStatement ps = null;
        ResultSet set = null;

        String query = "SELECT \n" +
                "\tSUM(price * count) as saleTotal,\n" +
                "\tcreated_at as saleDate \n" +
                "FROM \n" +
                "\torders \n" +
                "GROUP BY \n" +
                "\tcreated_at";

        try {
            ps = con.prepareStatement(query);
            set = ps.executeQuery();

            while (set.next()) {
                histories.add(new SaleHsitory(
                        set.getString("saleDate"),
                        set.getInt("saleTotal")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBHelper.closeConn(con, ps, set);

        return histories;
    }
}
