package QuanLyNhanKhau.models;

import java.util.Date;

public class CCCD {
    private String cccd;
    private int idNhanKhau;
    private Date ngayCap;
    private String noiCap;

    public CCCD(String cccd, int idNhanKhau, Date ngayCap, String noiCap) {
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

    public Date getNgayCap() {
        return ngayCap;
    }

    public void setNgayCap(Date ngayCap) {
        this.ngayCap = ngayCap;
    }

    public String getNoiCap() {
        return noiCap;
    }

    public void setNoiCap(String noiCap) {
        this.noiCap = noiCap;
    }
}


