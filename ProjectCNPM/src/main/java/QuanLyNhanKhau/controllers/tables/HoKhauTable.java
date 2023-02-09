package QuanLyNhanKhau.controllers.tables;

public class HoKhauTable {
    private String soHoKhau;
    private String hoTen;
    private String diaChi;

    public HoKhauTable(String soHoKhau, String hoTen, String diaChi) {
        this.soHoKhau = soHoKhau;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
    }

    public String getSoHoKhau() {
        return soHoKhau;
    }

    public void setSoHoKhau(String soHoKhau) {
        this.soHoKhau = soHoKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
