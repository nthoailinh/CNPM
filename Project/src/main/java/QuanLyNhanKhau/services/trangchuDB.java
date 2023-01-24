package QuanLyNhanKhau.services;

import java.sql.*;
import java.util.HashMap;

public class trangchuDB {
    public static HashMap<String, Integer> getQuantity() throws SQLException {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        Connection connection = MySQL.getConnection();
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery("select COUNT(*) from NhanKhau");
        while(rs.next()) map.put("NhanKhau", rs.getInt(1));

        rs = stmt.executeQuery("select COUNT(*) from HoKhau");
        while(rs.next()) map.put("HoKhau", rs.getInt(1));

        rs = stmt.executeQuery("select COUNT(*) from TamTru");
        while(rs.next()) map.put("TamTru", rs.getInt(1));

        rs = stmt.executeQuery("select COUNT(*) from TamVang");
        while(rs.next()) map.put("TamVang", rs.getInt(1));

        rs.close();
        stmt.close();
        connection.close();
        return map;
    }
}
