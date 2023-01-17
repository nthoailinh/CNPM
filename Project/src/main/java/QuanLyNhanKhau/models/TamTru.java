package QuanLyNhanKhau.models;

import java.util.Date;

public class TamTru {
    private int id;
    private int idNhanKhau;
    private Date batDau;
    private Date ketThuc;
    private String lyDo;

    public TamTru(int id, int idNhanKhau, Date batDau, Date ketThuc, String lyDo) {
        this.id = id;
        this.idNhanKhau = idNhanKhau;
        this.batDau = batDau;
        this.ketThuc = ketThuc;
        this.lyDo = lyDo;
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

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }
}
