package models;

import java.util.Date;

public class TamVang {
    private int id;
    private int idNhanKhau;
    private Date batDau;
    private Date ketThuc;
    private String noiTamTru;
    private String lyDo;

    public TamVang(int id, int idNhanKhau, Date batDau, Date ketThuc, String noiTamTru, String lyDo) {
        this.id = id;
        this.idNhanKhau = idNhanKhau;
        this.batDau = batDau;
        this.ketThuc = ketThuc;
        this.noiTamTru = noiTamTru;
        this.lyDo = lyDo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getidNhanKhau() {
        return idNhanKhau;
    }

    public void setidNhanKhau(int idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
    }

    public Date getBatDau() {
        return batDau;
    }

    public void setBatDau(Date batDau) {
        this.batDau = batDau;
    }

    public Date getKetThuc() {
        return ketThuc;
    }

    public void setKetThuc(Date ketThuc) {
        this.ketThuc = ketThuc;
    }

    public String getNoiTamTru() {
        return noiTamTru;
    }

    public void setNoiTamTru(String noiTamTru) {
        this.noiTamTru = noiTamTru;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }
}
