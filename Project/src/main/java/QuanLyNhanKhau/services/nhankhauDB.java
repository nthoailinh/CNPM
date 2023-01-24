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
        ResultSet rs = stmt.executeQuery("select * from NhanKhau");
        while(rs.next()) {
            NhanKhau nhanKhau = new NhanKhau();
            nhanKhau.setId(rs.getInt("id"));
            nhanKhau.setHoTen(rs.getString("hoTen"));
            nhanKhau.setNgaySinh(LocalDate.parse(rs.getString("ngaySinh")));
            nhanKhau.setGioiTinh(rs.getString("gioiTinh"));
            nhanKhau.setNoiSinh(rs.getString("noiSinh"));
            nhanKhau.setNguyenQuan(rs.getString("nguyenQuan"));
            nhanKhau.setDanToc(rs.getString("danToc"));
            nhanKhau.setNgheNghiep(rs.getString("ngheNghiep"));
            nhanKhau.setNoiLamViec(rs.getString("noiLamViec"));
            nhanKhau.setQuanHeVoiChuHo(rs.getString("quanHeVoiChuHo"));
            list.add(nhanKhau);
        }
        rs.close();
        stmt.close();
        connection.close();
        return list;
    }
}
