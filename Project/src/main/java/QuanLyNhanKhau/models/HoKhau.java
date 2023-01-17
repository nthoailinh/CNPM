package QuanLyNhanKhau.models;

public class HoKhau {
    private int id;
    private int soHoKhau;
    private String tenChuHo;
    private int soNha;
    private String ngo;
    private String duong;

    public HoKhau(int id, int soHoKhau, String tenChuHo, int soNha, String ngo, String duong) {
        this.id = id;
        this.soHoKhau = soHoKhau;
        this.tenChuHo = tenChuHo;
        this.soNha = soNha;
        this.ngo = ngo;
        this.duong = duong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSoHoKhau() {
        return soHoKhau;
    }

    public void setSoHoKhau(int soHoKhau) {
        this.soHoKhau = soHoKhau;
    }

    public String getTenChuHo() {
        return tenChuHo;
    }

    public void setTenChuHo(String tenChuHo) {
        this.tenChuHo = tenChuHo;
    }

    public int getSoNha() {
        return soNha;
    }

    public void setSoNha(int soNha) {
        this.soNha = soNha;
    }

    public String getNgo() {
        return ngo;
    }

    public void setNgo(String ngo) {
        this.ngo = ngo;
    }

    public String getDuong() {
        return duong;
    }

    public void setDuong(String duong) {
        this.duong = duong;
    }
}

