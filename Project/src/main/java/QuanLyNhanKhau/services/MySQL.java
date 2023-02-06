package QuanLyNhanKhau.services;

import java.sql.*;

public class MySQL {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/quan_ly_nhan_khau_1",
                    "root", "abc123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
