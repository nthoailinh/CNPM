package QuanLyNhanKhau.services;

import QuanLyNhanKhau.controllers.tables.HoKhauTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class HoKhauDB {
    public ObservableList<HoKhauTable> getListHoKhauTable() throws SQLException {
        Connection connection = MySQL.getConnection();
        ObservableList<HoKhauTable> list = FXCollections.observableArrayList();
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

    public PreparedStatement insertHoKhau(String soHoKhau, int idChuHo, int soNha, String ngo, String duong) throws SQLException {
        Connection connection = MySQL.getConnection();
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO HoKhau (`soHoKhau`, `idChuHo`,  "
                + "`soNha`, `ngo`, `duong`) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, soHoKhau);
        pstmt.setInt(2, idChuHo);
        pstmt.setInt(3, soNha);
        pstmt.setString(4, ngo);
        pstmt.setString(5, duong);
        pstmt.executeUpdate();
        return pstmt;
    }
}
