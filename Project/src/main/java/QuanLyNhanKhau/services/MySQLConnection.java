package QuanLyNhanKhau.services;

import java.sql.*;
public class MySQLConnection {
    public static void main(String[] args) {
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quan_ly_nhan_khau",
                                                          "root", "abc123");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
