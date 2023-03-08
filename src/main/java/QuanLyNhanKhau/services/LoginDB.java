package QuanLyNhanKhau.services;

import java.sql.*;

public class LoginDB {
    public boolean validate(String username, String password) throws SQLException {
        Connection connection = MySQL.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM User WHERE user = ? AND password = ?");
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        return rs.next();
    }

}
