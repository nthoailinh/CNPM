package QuanLyNhanKhau.services;

import QuanLyNhanKhau.controllers.tables.HoKhauTable;
import QuanLyNhanKhau.models.LichSuThayDoi;
import QuanLyNhanKhau.models.HoKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class HoKhauDB {
    public ObservableList<HoKhauTable> getListHoKhauTable() throws SQLException {
        Connection connection = MySQL.getConnection();
        ObservableList<HoKhauTable> list = FXCollections.observableArrayList();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from HoKhau JOIN NhanKhau ON NhanKhau.idHoKhau = HoKhau.id WHERE NhanKhau.quanHeVoiChuHo = 'Chủ hộ' ");
        while (rs.next()) {
            HoKhauTable hoKhau = new HoKhauTable(rs.getString("soHoKhau"), rs.getString("hoTen"),
                    "Số " + rs.getString("soNha") + ", ngõ " + rs.getString("ngo") + ", đường " + rs.getString("duong"));
            list.add(hoKhau);
        }
        rs.close();
        stmt.close();
        connection.close();
        return list;
    }

    public void deleteHoKhau(String soHoKhau) throws SQLException {
        Connection connection = MySQL.getConnection();
        String deleteCCCD = "DELETE FROM CCCD WHERE idNhanKhau IN (SELECT id FROM NhanKhau WHERE idHoKhau = (SELECT id FROM HoKhau WHERE soHoKhau = ?))";
        String deleteNhanKhau = "DELETE FROM NhanKhau WHERE idHoKhau = (SELECT id FROM HoKhau WHERE soHoKhau = ?)";
        String deleteHoKhau = "DELETE FROM HoKhau WHERE soHoKhau = ?";

        PreparedStatement pstmt1 = connection.prepareStatement(deleteCCCD);
        PreparedStatement pstmt2 = connection.prepareStatement(deleteNhanKhau);
        PreparedStatement pstmt3 = connection.prepareStatement(deleteHoKhau);

        pstmt1.setString(1, soHoKhau);
        pstmt2.setString(1, soHoKhau);
        pstmt3.setString(1, soHoKhau);

        pstmt1.executeUpdate();
        pstmt2.executeUpdate();
        pstmt3.executeUpdate();
    }

    public int getIDHoKhauBySoHoKhau(String soHoKhau) throws SQLException {
        Connection connection = MySQL.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("SELECT id FROM HoKhau WHERE soHoKhau = ?");
        pstmt.setString(1, soHoKhau);
        ResultSet rs = pstmt.executeQuery();
        int idHoKhau = -1;
        if (rs.next()) {
            idHoKhau = rs.getInt("id");
        }
        return idHoKhau;
    }

    public String getSoHoKhauByID(int id) throws SQLException{
        Connection connection = MySQL.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("SELECT soHoKhau FROM HoKhau WHERE id = ?");
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getString("soHoKhau");
        }
        return null;
    }

    public PreparedStatement insertHoKhau(String soHoKhau, int idChuHo, String soNha, String ngo, String duong) throws SQLException {
        Connection connection = MySQL.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO HoKhau (`soHoKhau`, `idChuHo`,  "
                + "`soNha`, `ngo`, `duong`) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, soHoKhau);
        pstmt.setInt(2, idChuHo);
        pstmt.setString(3, soNha);
        pstmt.setString(4, ngo);
        pstmt.setString(5, duong);
        pstmt.executeUpdate();
        return pstmt;
    }

    public PreparedStatement updateHoKhau(String soHoKhau, int idChuHo, String soNha, String ngo, String duong) throws SQLException {
        Connection connection = MySQL.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("UPDATE HoKhau SET `idChuHo` = ?, `soNha` = ?, `ngo` = ?, `duong` = ? WHERE `soHoKhau` = ?", Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, idChuHo);
        pstmt.setString(2, soNha);
        pstmt.setString(3, ngo);
        pstmt.setString(4, duong);
        pstmt.setString(5, soHoKhau);
        pstmt.executeUpdate();
        return pstmt;
    }

    public HoKhau getHoKhau(String soHoKhau) throws SQLException {
        Connection connection = MySQL.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM HoKhau WHERE soHoKhau = ?");
        pstmt.setString(1, soHoKhau);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return new HoKhau(rs.getInt("id"), rs.getString("soHoKhau"), rs.getInt("idChuHo"),
                    rs.getString("soNha"), rs.getString("ngo"), rs.getString("duong"));
        }
        return null;
    }

    public void addThayDoiNhanKhauTrongHoKhau(String soHoKhau, String tenNhanKhau, String noiDungThayDoi) throws SQLException{
        LocalDate today = java.time.LocalDate.now();

        Connection connection = MySQL.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO LichSuThayDoi (`soHoKhau`,  "
                + "`tenNhanKhau`, `ngayThayDoi`, `noiDungThayDOi`) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, soHoKhau);
        pstmt.setString(2, tenNhanKhau);
        pstmt.setDate(3, java.sql.Date.valueOf(today));
        pstmt.setString(4, noiDungThayDoi);
        pstmt.executeUpdate();
    }

    public ObservableList<LichSuThayDoi> getLichSuThayDoiTableList() throws SQLException{
        NhanKhauDB nhankhauDB = new NhanKhauDB();
        Connection connection = MySQL.getConnection();
        ObservableList<LichSuThayDoi> list = FXCollections.observableArrayList();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM LichSuThayDoi");
        while (rs.next()) {
            String soHoKhau = rs.getString("soHoKhau");
            String tenNhanKhau = rs.getString("tenNhanKhau");
            LocalDate ngayThayDoi = rs.getDate("ngayThayDoi").toLocalDate();
            String noiDungThayDoi = rs.getString("noiDungThayDoi");
            list.add(new LichSuThayDoi(soHoKhau, tenNhanKhau, ngayThayDoi, noiDungThayDoi));
        }
        rs.close();
        stmt.close();
        connection.close();
        return list;
    }
}
