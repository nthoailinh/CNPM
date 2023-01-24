package QuanLyNhanKhau.services;

import QuanLyNhanKhau.controllers.tables.HoKhauTable;
import QuanLyNhanKhau.models.HoKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class hokhauDB {
    public ObservableList<HoKhauTable> getListHoKhau() throws SQLException {
        ObservableList<HoKhauTable> list = FXCollections.observableArrayList();
        Connection connection = MySQL.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from HoKhau JOIN NhanKhau ON NhanKhau.idHoKhau = HoKhau.id WHERE NhanKhau.quanHeVoiChuHo = 'Chủ hộ' ");
        while(rs.next()) {
            HoKhauTable hoKhau = new HoKhauTable(rs.getString("soHoKhau"), rs.getString("hoTen"),
                    "Số " + rs.getString("soNha") + ", ngõ " + rs.getString("ngo") + ", đường " + rs.getString("duong"));
            list.add(hoKhau);
        }
        rs.close();
        stmt.close();
        connection.close();
        return list;
    }
}
