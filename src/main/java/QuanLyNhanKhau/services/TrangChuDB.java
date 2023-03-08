package QuanLyNhanKhau.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class TrangChuDB {
    public HashMap<String, Integer> getQuantityToDanPho() throws SQLException {
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

    public HashMap<String, Integer> getQuantityYTe() throws SQLException {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        Connection connection = MySQL.getConnection();
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM MacCOVID WHERE ngayKhoi IS NOT NULL");
        while(rs.next()) map.put("SoCaKhoi", rs.getInt(1));

        rs = stmt.executeQuery("SELECT COUNT(*) FROM MacCOVID WHERE ngayKhoi IS NULL");
        while(rs.next()) map.put("SoCaMac", rs.getInt(1));

        rs.close();
        stmt.close();
        connection.close();
        return map;
    }
}
