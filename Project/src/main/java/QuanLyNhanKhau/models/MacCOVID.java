package QuanLyNhanKhau.models;

import java.time.LocalDate;

public class MacCOVID {
    private int id;
    private int idNhanKhau;
    private LocalDate ngayMac;
    private LocalDate ngayKhoi;

    public MacCOVID(int id, int idNhanKhau, LocalDate ngayMac, LocalDate ngayKhoi) {
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

    public LocalDate getNgayMac() {
        return ngayMac;
    }

    public void setNgayMac(LocalDate ngayMac) {
        this.ngayMac = ngayMac;
    }

    public LocalDate getNgayKhoi() {
        return ngayKhoi;
    }

    public void setNgayKhoi(LocalDate ngayKhoi) {
        this.ngayKhoi = ngayKhoi;
    }
}

