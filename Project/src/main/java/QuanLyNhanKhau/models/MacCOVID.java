package QuanLyNhanKhau.models;

import java.util.Date;

public class MacCOVID {
    private int id;
    private int idNhanKhau;
    private Date ngayMac;
    private Date ngayKhoi;

    public MacCOVID(int id, int idNhanKhau, Date ngayMac, Date ngayKhoi) {
        this.id = id;
        this.idNhanKhau = idNhanKhau;
        this.ngayMac = ngayMac;
        this.ngayKhoi = ngayKhoi;
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

    public Date getNgayMac() {
        return ngayMac;
    }

    public void setNgayMac(Date ngayMac) {
        this.ngayMac = ngayMac;
    }

    public Date getNgayKhoi() {
        return ngayKhoi;
    }

    public void setNgayKhoi(Date ngayKhoi) {
        this.ngayKhoi = ngayKhoi;
    }
}

