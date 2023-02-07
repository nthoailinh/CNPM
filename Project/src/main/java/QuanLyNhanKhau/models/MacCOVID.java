package QuanLyNhanKhau.models;

import java.time.LocalDate;

public class MacCOVID {
    private int id;
    private int idNhanKhau;
    private String hoTen;
    private String tinhTrangSK;
    private String ketQuaTest;
    private String hinhThucTest;
    private String ngayMac;
    private String ngayKhoi;
    private String ngayTest;
    private String ngayKhaiBao;



    public MacCOVID(int id, String hoTen, String ngayMac, String ngayKhoi, String tinhTrangSK, String ketQuaTest, String hinhThucTest, String ngayTest, String ngayKhaiBao) {
        this.id = id;
        this.hoTen = hoTen;
        this.tinhTrangSK = tinhTrangSK;
        this.ketQuaTest = ketQuaTest;
        this.ngayMac = ngayMac;
        this.ngayKhoi = ngayKhoi;
        this.hinhThucTest = hinhThucTest;
        this.ngayTest = ngayTest;
        this.ngayKhaiBao = ngayKhaiBao;
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

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getTinhTrangSK() {
        return tinhTrangSK;
    }

    public void setTinhTrangSK(String tinhTrangSK) {
        this.tinhTrangSK = tinhTrangSK;
    }

    public String getKetQuaTest() {
        return ketQuaTest;
    }

    public void setKetQuaTest(String ketQuaTest) {
        this.ketQuaTest = ketQuaTest;
    }

    public String getHinhThucTest() {
        return hinhThucTest;
    }

    public void setHinhThucTest(String hinhThucTest) {
        this.hinhThucTest = hinhThucTest;
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

    public String getNgayTest() {
        return ngayTest;
    }

    public void setNgayTest(String ngayTest) {
        this.ngayTest = ngayTest;
    }

    public String getNgayKhaiBao() {
        return ngayKhaiBao;
    }

    public void setNgayKhaiBao(String ngayKhaiBao) {
        this.ngayKhaiBao = ngayKhaiBao;
    }
}

