package QuanLyNhanKhau.services;

import QuanLyNhanKhau.controllers.tables.NhanKhauTable;
import QuanLyNhanKhau.models.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class NhanKhauDB {
    public ObservableList<NhanKhau> getListNhanKhauWithSoHoKhau(String soHoKhau) throws SQLException {
        Connection connection = MySQL.getConnection();
        ObservableList<NhanKhau> list = FXCollections.observableArrayList();
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
        Connection connection = MySQL.getConnection();
        ObservableList<NhanKhauTable> list = FXCollections.observableArrayList();
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
        Connection connection = MySQL.getConnection();
        ObservableList<NhanKhau> list = FXCollections.observableArrayList();
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
        Connection connection = MySQL.getConnection();
        ObservableList<NhanKhauTable> list = FXCollections.observableArrayList();
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
        Connection connection = MySQL.getConnection();
        ObservableList<NhanKhauTable> list = FXCollections.observableArrayList();
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

    public PreparedStatement addNhanKhau(String hoTen, Object ngaySinh, String gioiTinh, String noiSinh, String nguyenQuan,
                                      String danToc, String ngheNghiep, String noiLamViec) throws SQLException {
        Connection connection = MySQL.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO NhanKhau (`hoTen`, `ngaySinh`, `gioiTinh`, `noiSinh`, " +
                "`nguyenQuan`, `danToc`, `ngheNghiep`, `noiLamViec`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, hoTen);
        pstmt.setObject(2, ngaySinh);
        pstmt.setString(3, gioiTinh);
        pstmt.setString(4, noiSinh);
        pstmt.setString(5, nguyenQuan);
        pstmt.setString(6, danToc);
        pstmt.setString(7, ngheNghiep);
        pstmt.setString(8, noiLamViec);
        pstmt.executeUpdate();
        return pstmt;
    }


    public ObservableList<NhanKhau> getListNhanKhauBySoHoKhau(String soHoKhau) throws SQLException {
        Connection connection = MySQL.getConnection();
        HoKhauDB hokhauDB = new HoKhauDB();
        ObservableList<NhanKhau> listNK = FXCollections.observableArrayList();
        int idHoKhau = hokhauDB.getIDHoKhauBySoHoKhau(soHoKhau);
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM NhanKhau WHERE idHoKhau = ?");
        pstmt.setInt(1, idHoKhau);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            NhanKhau nk = new NhanKhau(rs.getInt("id"), idHoKhau, rs.getString("hoTen"), rs.getDate("ngaySinh").toLocalDate(),
                    rs.getString("gioiTinh"), rs.getString("noiSinh"), rs.getString("nguyenQuan"), rs.getString("danToc"),
                    rs.getString("ngheNghiep"), rs.getString("noiLamViec"), rs.getString("quanHeVoiChuHo"));
            listNK.add(nk);
        }
        return listNK;
    }

    public PreparedStatement updateIDHoKhauCuaNhanKhau(int idNhanKhau, int idHoKhau, String quanHeVoiChuHo) throws SQLException {
        Connection connection = MySQL.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("UPDATE NhanKhau SET idHoKhau = ?, quanHeVoiChuHo = ? WHERE id = ?");
        pstmt.setInt(1, idHoKhau);
        pstmt.setString(2, quanHeVoiChuHo);
        pstmt.setInt(3, idNhanKhau);
        pstmt.executeUpdate();
        return pstmt;
    }

    public PreparedStatement addTamTru(int idNhanKhau, String maGiayTamTru, Object batDau, Object ketThuc, String lyDo)
            throws SQLException {
        Connection connection = MySQL.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO TamTru (`idNhanKhau`, `maGiayTamTru`, "
                        + "`batDau`, `ketThuc`, `lyDo`) VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, idNhanKhau);
        pstmt.setString(2, maGiayTamTru);
        pstmt.setObject(3, batDau);
        pstmt.setObject(4, ketThuc);
        pstmt.setString(5, lyDo);
        pstmt.executeUpdate();
        return pstmt;
    }

    public PreparedStatement addTamVang(int idNhanKhau, String maGiayTamVang, Object batDau, Object ketThuc,
                                     String noiTamTru, String lyDo) throws SQLException {
        Connection connection = MySQL.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO TamVang (`idNhanKhau`, `maGiayTamVang`, "
                        + "`batDau`, `ketThuc`, `noiTamTru`, `lyDo`) VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, idNhanKhau);
        pstmt.setString(2, maGiayTamVang);
        pstmt.setObject(3, batDau);
        pstmt.setObject(4, ketThuc);
        pstmt.setString(5, noiTamTru);
        pstmt.setString(6, lyDo);
        pstmt.executeUpdate();
        return pstmt;
    }

    public PreparedStatement addKhaiTu(int idNhanKhau, String maGiayKhaiTu, String nguyenNhan, Object ngayQuaDoi, Object ngayKhaiTu,
                                    int idNguoiKhai) throws SQLException {
        Connection connection = MySQL.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO KhaiTu (`idNhanKhau`, `maGiayKhaiTu`,  "
                        + "`nguyenNhan`, `ngayQuaDoi`, `ngayKhaiTu`, `idNguoiKhai`) VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, idNhanKhau);
        pstmt.setString(2, maGiayKhaiTu);
        pstmt.setObject(3, nguyenNhan);
        pstmt.setObject(4, ngayQuaDoi);
        pstmt.setObject(5, ngayKhaiTu);
        pstmt.setInt(6, idNguoiKhai);
        pstmt.executeUpdate();
        return pstmt;
    }

    public PreparedStatement addCCCD(String cccd, int idNhanKhau, Object ngayCap, String noiCap) throws SQLException {
        Connection connection = MySQL.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO CCCD (cccd, idNhanKhau, ngayCap, noiCap) VALUES (?, ?, ?, ?)");
        pstmt.setString(1, cccd);
        pstmt.setInt(2, idNhanKhau);
        pstmt.setObject(3, ngayCap);
        pstmt.setString(4, noiCap);
        pstmt.executeUpdate();
        return pstmt;
    }
}
