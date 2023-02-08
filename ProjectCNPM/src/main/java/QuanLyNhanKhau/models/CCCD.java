package QuanLyNhanKhau.models;

import java.time.LocalDate;

public class CCCD {
    private String cccd;
    private int idNhanKhau;
    private LocalDate ngayCap;
    private String noiCap;

    public CCCD(String cccd, int idNhanKhau, LocalDate ngayCap, String noiCap) {
        this.cccd = cccd;
        this.idNhanKhau = idNhanKhau;
        this.ngayCap = ngayCap;
        this.noiCap = noiCap;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public int getIdNhanKhau() {
        return idNhanKhau;
    }

    public void setIdNhanKhau(int idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
    }

    public LocalDate getNgayCap() {
        return ngayCap;
    }

    public void setNgayCap(LocalDate ngayCap) {
        this.ngayCap = ngayCap;
    }

    public String getNoiCap() {
        return noiCap;
    }

    public void setNoiCap(String noiCap) {
        this.noiCap = noiCap;
    }
}


