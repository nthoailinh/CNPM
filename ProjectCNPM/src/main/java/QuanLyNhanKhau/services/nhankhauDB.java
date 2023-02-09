package QuanLyNhanKhau.services;

import QuanLyNhanKhau.controllers.tables.NhanKhauTable;
import QuanLyNhanKhau.models.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class nhankhauDB {
    public ObservableList<NhanKhau> getListNhanKhauWithSoHoKhau(String soHoKhau) throws SQLException {
        ObservableList<NhanKhau> list = FXCollections.observableArrayList();
        Connection connection = MySQL.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM NhanKhau JOIN HoKhau ON NhanKhau.idHoKhau = HoKhau.id WHERE HoKhau.soHoKhau = ?");
        stmt.setString(1, soHoKhau);
        ResultSet rsNhanKhau = stmt.executeQuery();

        while (rsNhanKhau.next()) {
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
        ResultSet rsNhanKhau = stmt.executeQuery("SELECT NhanKhau.*, HoKhau.* FROM NhanKhau LEFT JOIN HoKhau ON NhanKhau.idHoKhau = HoKhau.id");
        while (rsNhanKhau.next()) {
            String soNha = rsNhanKhau.getString("soNha");
            String ngo = rsNhanKhau.getString("ngo");
            String duong = rsNhanKhau.getString("duong");

            String address = "";
            if (soNha != null && !soNha.isEmpty()) {
                address = "Số " + soNha;
            }
            if (ngo != null && !ngo.isEmpty()) {
                address += !address.isEmpty() ? ", ngõ " + ngo : "Ngõ " + ngo;
            }
            if (duong != null && !duong.isEmpty()) {
                address += !address.isEmpty() ? ", đường " + duong : "Đường " + duong;
            }

            NhanKhauTable nhanKhau = new NhanKhauTable(rsNhanKhau.getInt("id"), rsNhanKhau.getString("hoTen"), rsNhanKhau.getString("ngaySinh"),
                    rsNhanKhau.getString("gioiTinh"), address);
            list.add(nhanKhau);
        }
        rsNhanKhau.close();
        stmt.close();
        connection.close();
        return list;
    }

    public ObservableList<NhanKhau> getListNhanKhau() throws SQLException {
        ObservableList<NhanKhau> list = FXCollections.observableArrayList();
        Connection connection = MySQL.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rsNhanKhau = stmt.executeQuery("SELECT * FROM NhanKhau");
        while (rsNhanKhau.next()) {
            NhanKhau nhanKhau = new NhanKhau(rsNhanKhau.getInt("id"),
                    rsNhanKhau.getInt("idHoKhau"),
                    rsNhanKhau.getString("hoTen"),
                    rsNhanKhau.getDate("ngaySinh").toLocalDate(),
                    rsNhanKhau.getString("gioiTinh"),
                    rsNhanKhau.getString("noiSinh"),
                    rsNhanKhau.getString("nguyenQuan"),
                    rsNhanKhau.getString("danToc"),
                    rsNhanKhau.getString("ngheNghiep"),
                    rsNhanKhau.getString("noiLamViec"),
                    rsNhanKhau.getString("quanHeVoiChuHo"));
            list.add(nhanKhau);
        }
        rsNhanKhau.close();
        stmt.close();
        connection.close();
        return list;
    }

    public ObservableList<NhanKhauTable> getListNhanKhau(String gioiTinh, String ageStart, String ageEnd, String tinhTrang, String ngayMacStart, String ngayMacEnd, String ngayKhoiStart, String ngayKhoiEnd) throws SQLException {
        ObservableList<NhanKhauTable> list = FXCollections.observableArrayList();
        Connection connection = MySQL.getConnection();
        Statement stmt = connection.createStatement();
        String defaultQuery = "SELECT * FROM NhanKhau JOIN HoKhau ON NhanKhau.idHoKhau = HoKhau.id";
        if (!(tinhTrang.equals("<lựa chọn>") && ngayMacStart.equals("") && ngayMacEnd.equals("") && ngayKhoiStart.equals("") && ngayKhoiEnd.equals(""))) {
            defaultQuery += " JOIN MacCOVID ON NhanKhau.id = MacCOVID.idNhanKhau JOIN KhaiBao ON KhaiBao.idMacCOVID = MacCOVID.id";
        }
        String query = defaultQuery;
        if (!gioiTinh.equals("<lựa chọn>")) {
            query = query + " WHERE gioiTinh = '" + gioiTinh + "'";
        }

        if (!ageStart.equals("")) {
            if (!query.equals(defaultQuery)) {
                query = query + " AND ";
            } else {
                query = query + " WHERE ";
            }
            LocalDate birthDate = LocalDate.now().minusYears(Long.parseLong(ageStart));
            query = query + "ngaySinh < '" + birthDate + "'";
        }

        if (!ageEnd.equals("")) {
            if (!query.equals(defaultQuery)) {
                query = query + " AND ";
            } else {
                query = query + " WHERE ";
            }
            LocalDate birthDate = LocalDate.now().minusYears(Long.parseLong(ageEnd));
            query = query + "ngaySinh > '" + birthDate + "'";
        }

        if (!tinhTrang.equals("<lựa chọn>")) {
            if (!query.equals(defaultQuery)) {
                query = query + " AND ";
            } else {
                query = query + " WHERE ";
            }
            query = query + "ketQuaTest = '" + tinhTrang + "'";
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
                    rsNhanKhau.getString("gioiTinh"), "Số " + rsNhanKhau.getString("soNha") + ", ngõ " + rsNhanKhau.getString("ngo") + ", đường " + rsNhanKhau.getString("duong"));
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
        while (rsNhanKhau.next()) {
            NhanKhauTable nhanKhau = new NhanKhauTable(rsNhanKhau.getInt("id"), rsNhanKhau.getString("hoTen"), rsNhanKhau.getString("ngaySinh"),
                    rsNhanKhau.getString("gioiTinh"), "Số " + rsNhanKhau.getString("soNha") + ", ngõ " + rsNhanKhau.getString("ngo") + ", đường " + rsNhanKhau.getString("duong"));
            list.add(nhanKhau);
        }
        rsNhanKhau.close();
        stmt.close();
        connection.close();
        return list;
    }

    public String getCCCD(int idNhanKhau) throws SQLException {
        Connection connection = MySQL.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rsNhanKhau = stmt.executeQuery("SELECT cccd FROM CCCD WHERE idNhanKhau = " + idNhanKhau);
        if (rsNhanKhau.next()) {
            return rsNhanKhau.getString("cccd");
        } else {
            return "";
        }
    }
}
