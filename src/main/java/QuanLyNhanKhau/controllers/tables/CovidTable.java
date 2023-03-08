package QuanLyNhanKhau.controllers.tables;

public class CovidTable {
    private int id;
    private String hoTen;
    private String ngayMac;
    private String ngayKhoi;
    private String tinhTrangSK;
    private String ketQuaTest;

    public CovidTable(int id, String hoTen, String ngayMac, String ngayKhoi, String tinhTrangSK, String ketQuaTest) {
        this.id = id;
        this.hoTen = hoTen;
        this.ngayMac = ngayMac;
        this.ngayKhoi = ngayKhoi;
        this.tinhTrangSK = tinhTrangSK;
        this.ketQuaTest = ketQuaTest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
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
}
