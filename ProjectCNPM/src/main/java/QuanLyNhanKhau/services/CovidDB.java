package QuanLyNhanKhau.services;

import QuanLyNhanKhau.controllers.tables.CovidTable;
import QuanLyNhanKhau.models.MacCOVID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class CovidDB {
    private final Connection connection = MySQL.getConnection();

    public ObservableList<CovidTable> getListCovidTable() throws SQLException {
        ObservableList<CovidTable> list = FXCollections.observableArrayList();
        Statement stmt = connection.createStatement();
        String createTmpTable = "CREATE TEMPORARY TABLE tmpTable AS SELECT * FROM KhaiBao WHERE KhaiBao.id IN (SELECT max(id) as id from KhaiBao GROUP BY KhaiBao.idMacCOVID) ";
        stmt.executeUpdate(createTmpTable);
        ResultSet rs = stmt.executeQuery("SELECT MacCOVID.id, NhanKhau.hoTen, MacCOVID.ngayMac, MacCOVID.ngayKhoi, tmpTable.tinhTrangSucKhoe, tmpTable.ketQuaTest, tmpTable.ngayKhaiBao FROM (MacCOVID INNER JOIN tmpTable on tmpTable.idMacCOVID = MacCOVID.id) INNER JOIN NhanKhau on NhanKhau.id = MacCOVID.idNhanKhau");
        while (rs.next()) {
            if (rs.getDate("ngayKhoi") != null) {
                CovidTable covid = new CovidTable(rs.getInt("id"), rs.getString("hoTen"), rs.getDate("ngayMac").toString(), rs.getDate("ngayKhoi").toString(), rs.getString("tinhTrangSucKhoe"),
                        rs.getString("ketQuaTest"));
                list.add(covid);
            } else {
                CovidTable covid = new CovidTable(rs.getInt("id"), rs.getString("hoTen"), rs.getDate("ngayMac").toString(), "Chưa khỏi", rs.getString("tinhTrangSucKhoe"),
                        rs.getString("ketQuaTest"));
                list.add(covid);
            }
        }
        rs.close();
        stmt.close();
        connection.close();
        return list;
    }

    public ObservableList<MacCOVID> getListMacCovid() throws SQLException {
        ObservableList<MacCOVID> list = FXCollections.observableArrayList();
        Statement stmt = connection.createStatement();
        String createTmpTable = "CREATE TEMPORARY TABLE tmpTable AS SELECT * FROM KhaiBao WHERE KhaiBao.id IN (SELECT max(id) as id from KhaiBao GROUP BY KhaiBao.idMacCOVID) ";
        stmt.executeUpdate(createTmpTable);
        ResultSet rs = stmt.executeQuery("SELECT MacCOVID.id, NhanKhau.hoTen, MacCOVID.ngayMac, MacCOVID.ngayKhoi, tmpTable.tinhTrangSucKhoe, tmpTable.ketQuaTest, tmpTable.ngayKhaiBao, tmpTable.thoiDiemTest, tmpTable.ngayKhaiBao, tmpTable.hinhThucTest FROM (MacCOVID INNER JOIN tmpTable on tmpTable.idMacCOVID = MacCOVID.id) INNER JOIN NhanKhau on NhanKhau.id = MacCOVID.idNhanKhau");
        while (rs.next()) {
            if (rs.getDate("ngayKhoi") != null) {
                MacCOVID covid = new MacCOVID(rs.getInt("id"), rs.getString("hoTen"), rs.getDate("ngayMac").toString(), rs.getDate("ngayKhoi").toString(), rs.getString("tinhTrangSucKhoe"),
                        rs.getString("ketQuaTest"), rs.getString("hinhThucTest"), rs.getDate("thoiDiemTest").toString(), rs.getDate("ngayKhaiBao").toString());
                list.add(covid);
            } else {
                MacCOVID covid = new MacCOVID(rs.getInt("id"), rs.getString("hoTen"), rs.getDate("ngayMac").toString(), "Chưa khỏi", rs.getString("tinhTrangSucKhoe"),
                        rs.getString("ketQuaTest"), rs.getString("hinhThucTest"), rs.getDate("thoiDiemTest").toString(), rs.getDate("ngayKhaiBao").toString());
                list.add(covid);
            }
        }
        rs.close();
        stmt.close();
        connection.close();
        return list;
    }

    public ObservableList<MacCOVID> getListAllHistoryMacCOVID() throws SQLException {
        ObservableList<MacCOVID> list = FXCollections.observableArrayList();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT MacCOVID.id, NhanKhau.hoTen, MacCOVID.ngayMac, MacCOVID.ngayKhoi, KhaiBao.tinhTrangSucKhoe, KhaiBao.ketQuaTest, KhaiBao.ngayKhaiBao, KhaiBao.thoiDiemTest, KhaiBao.ngayKhaiBao, KhaiBao.hinhThucTest FROM (MacCOVID INNER JOIN KhaiBao on KhaiBao.idMacCOVID = MacCOVID.id) INNER JOIN NhanKhau on NhanKhau.id = MacCOVID.idNhanKhau");
        while (rs.next()) {
            if (rs.getDate("ngayKhoi") != null) {
                MacCOVID covid = new MacCOVID(rs.getInt("id"), rs.getString("hoTen"), rs.getDate("ngayMac").toString(), rs.getDate("ngayKhoi").toString(), rs.getString("tinhTrangSucKhoe"),
                        rs.getString("ketQuaTest"), rs.getString("hinhThucTest"), rs.getDate("thoiDiemTest").toString(), rs.getDate("ngayKhaiBao").toString());
                list.add(covid);
            } else {
                MacCOVID covid = new MacCOVID(rs.getInt("id"), rs.getString("hoTen"), rs.getDate("ngayMac").toString(), "Chưa khỏi", rs.getString("tinhTrangSucKhoe"),
                        rs.getString("ketQuaTest"), rs.getString("hinhThucTest"), rs.getDate("thoiDiemTest").toString(), rs.getDate("ngayKhaiBao").toString());
                list.add(covid);
            }
        }
        rs.close();
        stmt.close();
        connection.close();
        return list;
    }

    public PreparedStatement deleteMacCovid(int id) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("DELETE FROM KhaiBao where idMacCOVID = ?",
                Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        pstmt.close();
        pstmt = connection.prepareStatement("DELETE FROM MacCOVID where id = ?",
                Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        return pstmt;
    }

    public PreparedStatement addMacCOVID(int idNguoiMac, String ngayMac) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO MacCOVID (`idNhanKhau`, `ngayMac`)" +
                " VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, idNguoiMac);
        pstmt.setString(2, ngayMac);
        pstmt.executeUpdate();
        return pstmt;
    }

    public PreparedStatement addKhaiBao(int idMacCOVID, String ngayKhaiBao, String thoiDiemTest, String hinhThucTest,
                                     String ketQuaTest, String tinhTrangSucKhoe) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO KhaiBao (`idMacCOVID`, `ngayKhaiBao`,  "
                + "`thoiDiemTest`, `hinhThucTest`, `ketQuaTest`, `tinhTrangSucKhoe`) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, idMacCOVID);
        pstmt.setString(2, ngayKhaiBao);
        pstmt.setString(3, thoiDiemTest);
        pstmt.setString(4, hinhThucTest);
        pstmt.setString(5, ketQuaTest);
        pstmt.setString(6, tinhTrangSucKhoe);
        pstmt.executeUpdate();
        return pstmt;
    }

    public PreparedStatement addKhaiBao(int idMacCovid, String tinhTrangSK, String ketQuaTest, String hinhThucTest, Object thoiDiemTest, Object ngayKhaiBao) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(" INSERT INTO KhaiBao (`idMacCOVID`, `ngayKhaiBao`, `thoiDiemTest`, `hinhThucTest`, `ketQuaTest`, `tinhTrangSucKhoe`) VALUES (?, ?, ?, ?, ?, ?) ",
                Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, idMacCovid);
        pstmt.setObject(2, ngayKhaiBao);
        pstmt.setObject(3, thoiDiemTest);
        pstmt.setString(4, hinhThucTest);
        pstmt.setString(5, ketQuaTest);
        pstmt.setString(6, tinhTrangSK);
        pstmt.executeUpdate();
        return pstmt;
    }

    public PreparedStatement updateMacCOVID(int idNguoiMac, String ngayMac, String ngayKhoi) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("Update MacCOVID set ngayMac = ?, ngayKhoi = ? where id = ?"
                , Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, ngayMac);
        pstmt.setString(2, ngayKhoi);
        pstmt.setInt(3, idNguoiMac);
        pstmt.executeUpdate();
        return pstmt;
    }

}
