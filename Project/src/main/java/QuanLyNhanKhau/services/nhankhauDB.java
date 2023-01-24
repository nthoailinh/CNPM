package QuanLyNhanKhau.services;

import QuanLyNhanKhau.models.NhanKhau;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class nhankhauDB {

    public ObservableList<NhanKhau> getListNhanKhau() throws SQLException {
        ObservableList<NhanKhau> list = FXCollections.observableArrayList();
        Connection connection = MySQL.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rsNhanKhau = stmt.executeQuery("SELECT * FROM NhanKhau JOIN HoKhau ON NhanKhau.idHoKhau = HoKhau.id");
        while(rsNhanKhau.next()) {
            NhanKhau nhanKhau = new NhanKhau();
            nhanKhau.setId(rsNhanKhau.getInt("id"));
            nhanKhau.setHoTen(rsNhanKhau.getString("hoTen"));
            nhanKhau.setNgaySinh(LocalDate.parse(rsNhanKhau.getString("ngaySinh")));
            nhanKhau.setGioiTinh(rsNhanKhau.getString("gioiTinh"));
            nhanKhau.setNoiSinh(rsNhanKhau.getString("noiSinh"));
            nhanKhau.setNguyenQuan(rsNhanKhau.getString("nguyenQuan"));
            nhanKhau.setDanToc(rsNhanKhau.getString("danToc"));
            nhanKhau.setNgheNghiep(rsNhanKhau.getString("ngheNghiep"));
            nhanKhau.setNoiLamViec(rsNhanKhau.getString("noiLamViec"));
            nhanKhau.setQuanHeVoiChuHo(rsNhanKhau.getString("quanHeVoiChuHo"));

            list.add(nhanKhau);
        }
        rsNhanKhau.close();
        stmt.close();
        connection.close();
        return list;
    }
}
