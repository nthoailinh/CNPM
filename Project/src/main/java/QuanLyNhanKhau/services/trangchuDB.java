package QuanLyNhanKhau.services;

import java.sql.*;
public class trangchuDB {
    public static void main(String[] args) throws SQLException {
        Connection connection = MySQL.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs1 = stmt.executeQuery("select COUNT(*) from NhanKhau");
        ResultSet rs2 = stmt.executeQuery("select COUNT(*) from HoKhau");
        ResultSet rs3 = stmt.executeQuery("select COUNT(*) from TamTru");
        ResultSet rs4 = stmt.executeQuery("select COUNT(*) from NhanKhau");
        while(rs.next())
            System.out.println(rs.getInt(1));
    }
}
