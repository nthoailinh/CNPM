-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2022-12-25 06:05:29.971

-- tables
-- Table: CCCD
CREATE TABLE CCCD (
    cccd char(12) NOT NULL,
    id_NhanKhau int NOT NULL,
    ngay_cap date NOT NULL,
    noi_cap varchar(100) NOT NULL,
    CONSTRAINT CCCD_pk PRIMARY KEY (cccd)
);

-- Table: HoKhau
CREATE TABLE HoKhau (
    id int NOT NULL,
    so_ho_khau int NOT NULL,
    ten_chu_ho varchar(100) NOT NULL,
    so_nha int NOT NULL,
    ngo varchar(100) NULL,
    duong varchar(100) NOT NULL,
    CONSTRAINT HoKhau_pk PRIMARY KEY (id)
);

-- Table: KhaiBao
CREATE TABLE KhaiBao (
    id int NOT NULL,
    id_MacCOVID int NOT NULL,
    ngay_khai_bao date NOT NULL,
    thoi_diem_test date NOT NULL,
    hinh_thuc_test varchar(200) NOT NULL,
    ket_qua_test char(10) NOT NULL,
    tinh_trang_suc_khoe varchar(400) NOT NULL,
    CONSTRAINT KhaiBao_pk PRIMARY KEY (id)
);

-- Table: KhaiTu
CREATE TABLE KhaiTu (
    id_NhanKhau int NOT NULL,
    nguyen_nhan varchar(300) NULL,
    ngay_qua_doi date NULL,
    ngay_khai_tu date NOT NULL,
    CONSTRAINT KhaiTu_pk PRIMARY KEY (id_NhanKhau)
);

-- Table: LichSuThayDoi
CREATE TABLE LichSuThayDoi (
    id int NOT NULL,
    id_HoKhau int NOT NULL,
    ngay_thay_doi date NOT NULL,
    bang_thay_doi varchar(30) NOT NULL,
    id_bang_thay_doi int NOT NULL,
    thong_tin_thay_doi varchar(200) NOT NULL,
    thay_doi_tu varchar(200) NOT NULL,
    thay_doi_thanh varchar(200) NOT NULL,
    nguoi_thuc_hien varchar(200) NOT NULL,
    CONSTRAINT LichSuThayDoi_pk PRIMARY KEY (id)
);

-- Table: MacCOVID
CREATE TABLE MacCOVID (
    id int NOT NULL,
    id_NhanKhau int NOT NULL,
    ngay_mac date NOT NULL,
    ngay_khoi date NULL,
    CONSTRAINT MacCOVID_pk PRIMARY KEY (id)
);

-- Table: NhanKhau
CREATE TABLE NhanKhau (
    id int NOT NULL,
    id_HoKhau int NOT NULL,
    ho_ten varchar(100) NOT NULL,
    ngay_sinh date NOT NULL,
    gioi_tinh char(3) NOT NULL,
    noi_sinh varchar(100) NOT NULL,
    nguyen_quan varchar(100) NOT NULL,
    dan_toc varchar(100) NOT NULL,
    nghe_nghiep varchar(100) NOT NULL,
    noi_lam_viec varchar(200) NOT NULL,
    quan_he_voi_chu_ho varchar(100) NOT NULL,
    CONSTRAINT NhanKhau_pk PRIMARY KEY (id)
);

-- Table: TamTru
CREATE TABLE TamTru (
    id int NOT NULL,
    id_NhanKhau int NOT NULL,
    bat_dau date NOT NULL,
    ket_thuc date NOT NULL,
    ly_do varchar(200) NOT NULL,
    CONSTRAINT TamTru_pk PRIMARY KEY (id)
);

-- Table: TamVang
CREATE TABLE TamVang (
    id int NOT NULL,
    id_NhanKhau int NOT NULL,
    bat_dau date NOT NULL,
    ket_thuc date NOT NULL,
    noi_tam_tru varchar(100) NOT NULL,
    ly_do varchar(200) NOT NULL,
    CONSTRAINT TamVang_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: CCCD_NhanKhau (table: CCCD)
ALTER TABLE CCCD ADD CONSTRAINT CCCD_NhanKhau FOREIGN KEY CCCD_NhanKhau (id_NhanKhau)
    REFERENCES NhanKhau (id);

-- Reference: KhaiBaoMacCOVID_MacCOVID (table: KhaiBao)
ALTER TABLE KhaiBao ADD CONSTRAINT KhaiBaoMacCOVID_MacCOVID FOREIGN KEY KhaiBaoMacCOVID_MacCOVID (id_MacCOVID)
    REFERENCES MacCOVID (id);

-- Reference: KhaiTu_NhanKhau (table: KhaiTu)
ALTER TABLE KhaiTu ADD CONSTRAINT KhaiTu_NhanKhau FOREIGN KEY KhaiTu_NhanKhau (id_NhanKhau)
    REFERENCES NhanKhau (id);

-- Reference: LichSuThayDoi_HoKhau (table: LichSuThayDoi)
ALTER TABLE LichSuThayDoi ADD CONSTRAINT LichSuThayDoi_HoKhau FOREIGN KEY LichSuThayDoi_HoKhau (id_HoKhau)
    REFERENCES HoKhau (id);

-- Reference: MacCOVID_NhanKhau (table: MacCOVID)
ALTER TABLE MacCOVID ADD CONSTRAINT MacCOVID_NhanKhau FOREIGN KEY MacCOVID_NhanKhau (id_NhanKhau)
    REFERENCES NhanKhau (id);

-- Reference: NhanKhau_HoKhau (table: NhanKhau)
ALTER TABLE NhanKhau ADD CONSTRAINT NhanKhau_HoKhau FOREIGN KEY NhanKhau_HoKhau (id_HoKhau)
    REFERENCES HoKhau (id);

-- Reference: TamTru_NhanKhau (table: TamTru)
ALTER TABLE TamTru ADD CONSTRAINT TamTru_NhanKhau FOREIGN KEY TamTru_NhanKhau (id_NhanKhau)
    REFERENCES NhanKhau (id);

-- Reference: TamVang_NhanKhau (table: TamVang)
ALTER TABLE TamVang ADD CONSTRAINT TamVang_NhanKhau FOREIGN KEY TamVang_NhanKhau (id_NhanKhau)
    REFERENCES NhanKhau (id);

-- End of file.

