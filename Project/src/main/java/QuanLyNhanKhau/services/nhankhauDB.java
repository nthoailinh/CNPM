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
    public ObservableList<NhanKhau> getListNhanKhauWithSoHoKhau(String soHoKhau) throws SQLException {
        ObservableList<NhanKhau> list = FXCollections.observableArrayList();
        Connection connection = MySQL.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM NhanKhau JOIN HoKhau ON NhanKhau.idHoKhau = HoKhau.id WHERE HoKhau.soHoKhau = ?");
        stmt.setString(1, soHoKhau);
        ResultSet rsNhanKhau = stmt.executeQuery();

        while(rsNhanKhau.next()) {
            NhanKhau nhanKhau = new NhanKhau(rsNhanKhau.getInt("id"), rsNhanKhau.getString("hoTen"), rsNhanKhau.getDate("ngaySinh").toLocalDate(),
                    rsNhanKhau.getString("quanHeVoiChuHo"));
            list.add(nhanKhau);
        }
        rsNhanKhau.close();
        stmt.close();
        connection.close();
        return list;
    }

    public ObservableList<NhanKhauTable> getListNhanKhauTable() throws SQLException {
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

    public ObservableList<NhanKhauTable> getListNhanKhauNoCovidTable() throws SQLException {
        ObservableList<NhanKhauTable> list = FXCollections.observableArrayList();
        Connection connection = MySQL.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rsNhanKhau = stmt.executeQuery("SELECT * FROM NhanKhau JOIN HoKhau ON NhanKhau.idHoKhau = HoKhau.id WHERE NhanKhau.id not in (SELECT idNhanKhau from MacCOVID where ngayKhoi is null)");
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

    // for thong ke
    public ObservableList<NhanKhauTable> getListNhanKhau(String gioiTinh, String ageStart, String ageEnd, String tinhTrang, String ngayMacStart, String ngayMacEnd, String ngayKhoiStart, String ngayKhoiEnd) throws SQLException {
        ObservableList<NhanKhauTable> list = FXCollections.observableArrayList();
        Connection connection = MySQL.getConnection();
        Statement stmt = connection.createStatement();
        String defaultQuery = "SELECT * FROM NhanKhau JOIN HoKhau ON NhanKhau.idHoKhau = HoKhau.id";
        if (!(tinhTrang.equals("<lua chon>") && ngayMacStart.equals("") && ngayMacEnd.equals("") && ngayKhoiStart.equals("") && ngayKhoiEnd.equals(""))) {
            defaultQuery += " JOIN MacCOVID ON NhanKhau.id = MacCOVID.idNhanKhau";
        }
        String query = defaultQuery;
        if (!gioiTinh.equals("<lựa chọn>")) {
            query = query + " WHERE gioiTinh = '" + gioiTinh + "'";
        }

        if (!ageStart.equals("")) {
            if (!query.equals(defaultQuery)) {
                query = query + " AND ";
            } else {
                query = query+ " WHERE ";
            }
            LocalDate birthDate = LocalDate.now().minusYears(Long.parseLong(ageStart));
            query = query + "ngaySinh < '" + birthDate.toString() + "'";
        }

        if (!ageEnd.equals("")) {
            if (!query.equals(defaultQuery)) {
                query = query + " AND ";
            } else {
                query = query+ " WHERE ";
            }
            LocalDate birthDate = LocalDate.now().minusYears(Long.parseLong(ageEnd));
            query = query + "ngaySinh > '" + birthDate.toString() + "'";
        }

        if (!tinhTrang.equals("<lua chon>")) {
            if (!query.equals(defaultQuery)) {
                query = query + " AND ";
            } else {
                query = query+ " WHERE ";
            }
            query = query + "tinhTrangSucKhoe = '" + tinhTrang + "'";
        }

        if (!ngayMacStart.equals("")) {
            if (!query.equals(defaultQuery)) {
                query = query + " AND ";
            } else {
                query = query + " WHERE ";
            }
            query = query + "ngayMac > '" + ngayMacStart + "'";
        }

        if (!ngayMacEnd.equals("")) {
            if (!query.equals(defaultQuery)) {
                query = query + " AND ";
            } else {
                query = query + " WHERE ";
            }
            query = query + "ngayMac < '" + ngayMacEnd + "'";
        }

        if (!ngayKhoiStart.equals("")) {
            if (!query.equals(defaultQuery)) {
                query = query + " AND ";
            } else {
                query = query + " WHERE ";
            }
            query = query + "ngayKhoi > '" + ngayKhoiStart + "'";
        }

        if (!ngayKhoiEnd.equals("")) {
            if (!query.equals(defaultQuery)) {
                query = query + " AND ";
            } else {
                query = query + " WHERE ";
            }
            query = query + "ngayKhoi < '" + ngayKhoiEnd + "'";
        }

        System.out.println(query);
        ResultSet rsNhanKhau = stmt.executeQuery(query);
        while (rsNhanKhau.next()) {
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
