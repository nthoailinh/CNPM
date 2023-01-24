package QuanLyNhanKhau.services;

import QuanLyNhanKhau.models.HoKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class hokhauDB {
    public ObservableList<HoKhau> getListHoKhau() throws SQLException {
        ObservableList<HoKhau> list = FXCollections.observableArrayList();
        Connection connection = MySQL.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from HoKhau");
        while(rs.next()) {
            HoKhau hoKhau = new HoKhau(rs.getInt("id"),
                    rs.getString("soHoKhau"),
                    rs.getInt("idChuHo"),
                    rs.getInt("soNha"),
                    rs.getString("ngo"),
                    rs.getString("duong"));
            list.add(hoKhau);
        }
        rs.close();
        stmt.close();
        connection.close();
        return list;
    }
}
