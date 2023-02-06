package QuanLyNhanKhau.models;

import java.time.LocalDate;

public class MacCOVID {
    private int id;
    private int idNhanKhau;
    private String hoTen;
    private String tinhTrangSK;
    private String ketQuaTest;
    private String ngayMac;
    private String ngayKhoi;

    public MacCOVID(int id, int idNhanKhau, String ngayMac, String ngayKhoi) {
        this.id = id;
        this.idNhanKhau = idNhanKhau;
        this.ngayMac = ngayMac;
        this.ngayKhoi = ngayKhoi;
    }

    public MacCOVID(int id, String hoTen, String ngayMac, String tinhTrangSK, String ketQuaTest) {
        this.id = id;
        this.hoTen = hoTen;
        this.tinhTrangSK = tinhTrangSK;
        this.ketQuaTest = ketQuaTest;
        this.ngayMac = ngayMac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdNhanKhau() {
        return idNhanKhau;
    }

    public void setIdNhanKhau(int idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
    }

    public String getNgayMac() {
        return ngayMac;
    }

    public void setNgayMac(String ngayMac) {
        this.ngayMac = ngayMac;
    }

    public String getNgayKhoi() {
        return ngayKhoi;
    }

    public void setNgayKhoi(String ngayKhoi) {
        this.ngayKhoi = ngayKhoi;
    }
}

