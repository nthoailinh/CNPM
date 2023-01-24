package QuanLyNhanKhau.services;

import QuanLyNhanKhau.controllers.tables.NhanKhauTable;
import QuanLyNhanKhau.models.NhanKhau;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class nhankhauDB {

    public ObservableList<NhanKhauTable> getListNhanKhau() throws SQLException {
        ObservableList<NhanKhauTable> list = FXCollections.observableArrayList();
        Connection connection = MySQL.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rsNhanKhau = stmt.executeQuery("SELECT * FROM NhanKhau JOIN HoKhau ON NhanKhau.idHoKhau = HoKhau.id");
        while(rsNhanKhau.next()) {
            NhanKhauTable nhanKhau = new NhanKhauTable(rsNhanKhau.getInt("id"), rsNhanKhau.getString("hoTen"), rsNhanKhau.getString("ngaySinh"),
                    rsNhanKhau.getString("gioiTinh"),"Số " + rsNhanKhau.getString("soNha") + ", ngõ " + rsNhanKhau.getString("ngo") + ", đường " + rsNhanKhau.getString("duong"));
            list.add(nhanKhau);
        }
        rsNhanKhau.close();
        stmt.close();
        connection.close();
        return list;
    }
}
