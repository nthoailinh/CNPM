drop database quan_ly_nhan_khau;
create database quan_ly_nhan_khau;
use quan_ly_nhan_khau;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quan_ly_nhan_khau`
--

-- --------------------------------------------------------

-- Cấu trúc bảng cho bảng `User`
CREATE TABLE User(
    id int AUTO_INCREMENT NOT NULL,
    user char(40) NOT NULL,
    password char(40) NOT NULL,
    CONSTRAINT User_pk PRIMARY KEY (id)
);

--
-- Dữ liệu cho bảng `User`
--

INSERT INTO `User` (`user`, `password`) VALUES
('totruong', 'totruong'),
('canboyte', 'canboyte');


--
-- Cấu trúc bảng cho bảng `CCCD`
CREATE TABLE CCCD (
    cccd char(12)  NOT NULL,
    idNhanKhau int  DEFAULT NULL,
    ngayCap date DEFAULT NULL,
    noiCap varchar(100) DEFAULT NULL,
    CONSTRAINT CCCD_pk PRIMARY KEY (cccd)
);

--
-- Dữ liệu cho bảng `CCCD`
--

INSERT INTO `CCCD` (`cccd`, `idNhanKhau`, `ngayCap`, `noiCap`) VALUES
('000000000001', 1, '2022-08-04', 'Cục Cảnh sát quản lý hành chính về trật tự xã hội'),
('000000000002', 2, '2022-05-14', 'Cục Cảnh sát quản lý hành chính về trật tự xã hội'),
('000000000003', 3, '2021-09-29', 'Cục Cảnh sát quản lý hành chính về trật tự xã hội'),
('000000000004', 4, '2021-07-12', 'Cục Cảnh sát quản lý hành chính về trật tự xã hội'),
('000000000005', 5, '2022-03-19', 'Cục Cảnh sát quản lý hành chính về trật tự xã hội'),
('000000000006', 6, '2022-11-28', 'Cục Cảnh sát quản lý hành chính về trật tự xã hội'),
('000000000007', 7, '2018-12-22', 'Cục Cảnh sát quản lý hành chính về trật tự xã hội'),
('000000000008', 8, '2021-06-17', 'Cục Cảnh sát quản lý hành chính về trật tự xã hội'),
('000000000009', 9, '2021-02-13', 'Cục Cảnh sát quản lý hành chính về trật tự xã hội'),
('000000000012', 12, '2022-03-29', 'Cục Cảnh sát quản lý hành chính về trật tự xã hội'),
('000000000013', 13, '2022-08-24', 'Cục Cảnh sát quản lý hành chính về trật tự xã hội'),
('000000000014', 14, '2022-11-10', 'Cục Cảnh sát quản lý hành chính về trật tự xã hội'),
('000000000015', 15, '2021-12-19', 'Cục Cảnh sát quản lý hành chính về trật tự xã hội'),
('000000000016', 16, '2022-05-01', 'Cục Cảnh sát quản lý hành chính về trật tự xã hội'),
('000000000017', 17, '2018-07-15', 'Cục Cảnh sát quản lý hành chính về trật tự xã hội'),
('000000000018', 18, '2022-04-22', 'Cục Cảnh sát quản lý hành chính về trật tự xã hội'),
('000000000019', 19, '2022-09-11', 'Cục Cảnh sát quản lý hành chính về trật tự xã hội'),
('000000000020', 20, '2022-12-31', 'Cục Cảnh sát quản lý hành chính về trật tự xã hội'),
('000000000021', 21, '2021-01-01', 'Cục Cảnh sát quản lý hành chính về trật tự xã hội'),
('000000000022', 22, '2022-02-28', 'Cục Cảnh sát quản lý hành chính về trật tự xã hội'),
('000000000023', 23, '2022-04-30', 'Cục Cảnh sát quản lý hành chính về trật tự xã hội'),
('000000000024', 24, '2022-06-30', 'Cục Cảnh sát quản lý hành chính về trật tự xã hội');

-- Table: HoKhau
CREATE TABLE HoKhau (
    id int AUTO_INCREMENT NOT NULL,
    soHoKhau char(9)  NOT NULL,
    idChuHo varchar(100)  NOT NULL,
    soNha varchar(100) DEFAULT NULL,
    ngo varchar(100) DEFAULT NULL,
    duong varchar(100) DEFAULT NULL,
    CONSTRAINT HoKhau_pk PRIMARY KEY (id)
);

--
-- Dữ liệu cho bảng `HoKhau`
--
INSERT INTO `HoKhau` (`id`, `soHoKhau`, `idChuHo`, `soNha`, `ngo`, `duong`) VALUES
(1, 'TQB000001', 26, 1, 15, 'Tạ Quang Bửu'),
(2, 'TQB000002', 28, 2, 15, 'Tạ Quang Bửu'),
(3, 'TQB000003', 29, 3, 15, 'Tạ Quang Bửu'),
(4, 'TQB000004', 33, 4, 15, 'Tạ Quang Bửu'),
(5, 'TQB000005', 33, 7, 15, 'Tạ Quang Bửu'),
(6, 'TQB000006', 33, 9, 15, 'Tạ Quang Bửu'),
(7, 'TQB000007', 33, 10, 15, 'Tạ Quang Bửu'),
(8, 'TQB000008', 33, 14, 15, 'Tạ Quang Bửu');

-- Table: KhaiBao
CREATE TABLE KhaiBao (
    id int AUTO_INCREMENT NOT NULL,
    idMacCOVID int  NOT NULL,
    ngayKhaiBao date  NOT NULL,
    thoiDiemTest date  NOT NULL,
    hinhThucTest varchar(200)  NOT NULL,
    ketQuaTest char(10)  NOT NULL,
    tinhTrangSucKhoe varchar(400)  NOT NULL,
    CONSTRAINT KhaiBao_pk PRIMARY KEY (id)
);

-- Table: KhaiTu
CREATE TABLE KhaiTu (
    idNhanKhau int AUTO_INCREMENT NOT NULL,
    maGiayKhaiTu char(12) NOT NULL,
    nguyenNhan varchar(300)  NULL,
    ngayQuaDoi date  NULL,
    ngayKhaiTu date  NOT NULL,
    idNguoiKhai int NOT NULL,
    CONSTRAINT KhaiTu_pk PRIMARY KEY (idNhanKhau)
);

-- Table: LichSuThayDoi
CREATE TABLE LichSuThayDoi (
    id int AUTO_INCREMENT NOT NULL,
    soHoKhau varchar(20) DEFAULT NULL,
    tenNhanKhau varchar(200) DEFAULT NULL,
    ngayThayDoi date DEFAULT NULL,
    noiDungThayDoi varchar(200) DEFAULT NULL,
    CONSTRAINT LichSuThayDoi_pk PRIMARY KEY (id)
);

-- Table: MacCOVID
CREATE TABLE MacCOVID (
    id int AUTO_INCREMENT NOT NULL,
    idNhanKhau int  NOT NULL,
    ngayMac date  NOT NULL,
    ngayKhoi date  NULL,
    CONSTRAINT MacCOVID_pk PRIMARY KEY (id)
);

--
-- Cấu trúc bảng cho bảng `NhanKhau`
CREATE TABLE NhanKhau (
    id int AUTO_INCREMENT NOT NULL,
    idHoKhau int DEFAULT NULL,
    hoTen varchar(100)  NOT NULL,
    ngaySinh date  NOT NULL,
    gioiTinh char(3)  NOT NULL,
    noiSinh varchar(100)  NOT NULL,
    nguyenQuan varchar(100)  NOT NULL,
    danToc varchar(100)  NOT NULL,
    ngheNghiep varchar(100)  NOT NULL,
    noiLamViec varchar(200)  NOT NULL,
    quanHeVoiChuHo varchar(100) DEFAULT NULL,
    CONSTRAINT NhanKhau_pk PRIMARY KEY (id)
);

--
-- Dữ liệu cho bảng `NhanKhau`
--

INSERT INTO `NhanKhau` (`id`, `idHoKhau`, `hoTen`, `ngaySinh`, `gioiTinh`, `noiSinh`, `nguyenQuan`, `danToc`, `ngheNghiep`, `noiLamViec`, `quanHeVoiChuHo`) VALUES
(1, 1, 'Trinh Văn An', '1990-12-07', 'Nam', 'Thái Bình', 'Hà Nội', 'Kinh', 'Giáo Viên', 'Trường THCS Chu Văn An', 'Chủ hộ'),
(2, 2, 'Trần Thanh Duyên', '1997-12-23', 'Nữ', 'Hưng Yên', 'Hải Phòng', 'Kinh', 'Nhân viên văn phòng', 'Công ty ABC', 'Chủ hộ'),
(3, 5, 'Nguyễn Minh Quân', '1995-05-31', 'Nam', 'Hải Phòng', 'Hà Nội', 'Kinh', 'Kỹ sư', 'Viettel', 'Chủ hộ'),
(4, 3, 'Nguyễn Tiến Dũng', '1964-06-03', 'Nam', 'Thái Bình', 'Hải Dương', 'Kinh', 'Phó giám đốc', 'Công ty EXE', 'Chủ hộ'),
(5, 3, 'Vũ Mỹ Linh', '1965-12-06', 'Nữ', 'Hà Nội', 'Hà Nội', 'Kinh', 'Nội trợ', 'Tại nhà', 'Vợ'),
(6, 3, 'Nguyễn Tiến Đạt', '1990-09-09', 'Nam', 'Hải Dương', 'Hải Dương', 'Kinh', 'Kỹ sư điện', 'Công ty điện EVN', 'Con trai'),
(7, 3, 'Nguyễn Trà My', '1997-12-12', 'Nữ', 'Hải Dương', 'Hải Dương', 'Kinh', 'Luật sư', 'Văn phòng luật sư 123', 'Con gái'),
(8, 4, 'Trần Văn Nam', '1980-07-09', 'Nam', 'Hà Nội', 'Hà Nội', 'Kinh', 'Giảng viên đại học', 'Đại học Bách khoa Hà Nội', 'Chủ hộ'),
(9, 4, 'Nguyễn Minh Tuyết', '1985-09-02', 'Nữ', 'Hà Nội', 'Nam Định', 'Kinh', 'Bác sĩ', 'Bệnh viện quốc tế HJK', 'Vợ'),
(10, 4, 'Trần Trung Kiên', '2010-12-25', 'Nam', 'Hưng Yên', 'Hà Nội', 'Kinh', 'Học sinh', 'Trường THCS Chu Văn An', 'Con trai'),
(11, 4, 'Trần Thúy Ngọc', '2016-01-15', 'Nữ', 'Hà Nội', 'Hà Nội', 'Kinh', 'Học sinh', 'Trường tiểu học Chu Văn An', 'Con gái'),
(12, 6, 'Lý Văn Công', '1945-06-04', 'Nam', 'Hà Nội', 'Hà Nội', 'Kinh', 'Về hưu', 'Không', 'Chủ hộ'),
(13, 6, 'Bùi Thị Hà', '1948-02-03', 'Nữ', 'Hải Phòng', 'Hải Phòng', 'Kinh', 'Nội trợ', 'Tại nhà', 'Vợ'),
(14, 6, 'Lý Thành Nam', '1968-06-12', 'Nam', 'Hà Nội', 'Hà Nội', 'Kinh', 'Công nhân', 'Nhà máy Z113', 'Con trai'),
(15, 6, 'Lý Thu Thủy', '1971-03-05', 'Nữ', 'Hà Nội', 'Hà Nội', 'Kinh', 'Nhân viên văn phòng', 'Công ty TNHH một thành viên ABC', 'Con gái'),
(16, 7, 'Trần Thanh Hải', '1980-09-15', 'Nam', 'Hải Phòng', 'Hải Phòng', 'Kinh', 'Tự do', '', 'Chủ hộ'),
(17, 7, 'Hoàng Thị Huyền', '1985-06-02', 'Nữ', 'Hà Nội', 'Hà Nội', 'Kinh', 'Nhân viên văn phòng', 'Công ty Duy Tân', 'Vợ'),
(18, 7, 'Trần Minh Hiếu', '2000-03-19', 'Nam', 'Hà Nội', 'Hải Phòng', 'Kinh', 'Nhân viên văn phòng', 'Công ty điện EVN', 'Con trai'),
(19, 7, 'Trần Thị Tuyết', '2003-01-01', 'Nữ', 'Hà Nội', 'Hải Phòng', 'Kinh', 'Sinh viên', 'Đại học Kinh tế quốc dân', 'Con gái'),
(20, 7, 'Trần Văn Tùng', '1990-12-07', 'Nam', 'Hải Phòng', 'Hải Phòng', 'Kinh', 'Bác sĩ', 'Bệnh viện Bạch Mai', 'Em trai'),
(21, 8, 'Phạm Văn Quang', '1975-03-19', 'Nam', 'Vĩnh Phúc', 'Vĩnh Phúc', 'Kinh', 'Bác sĩ', 'Bệnh viên Thanh Nhàn', 'Chủ hộ'),
(22, 8, 'Phạm Thị Huyền', '2000-01-01', 'Nữ', 'Hà Nội', 'Vĩnh Phúc', 'Kinh', 'Sinh viên', 'Đại học Thương mại', 'Con gái'),
(23, 8, 'Phạm Văn Hiếu', '1999-03-19', 'Nam', 'Hà Nội', 'Vĩnh Phúc', 'Kinh', 'Kỹ sư', 'VNPT', 'Con trai'),
(24, 8, 'Phạm Văn Hiếu', '2007-06-19', 'Nam', 'Hà Nội', 'Vĩnh Phúc', 'Kinh', 'Học sinh', 'Trường THPT Thăng Long', 'Con trai');


-- Table: TamTru
CREATE TABLE TamTru (
    id int AUTO_INCREMENT NOT NULL,
    idNhanKhau int  NOT NULL,
    maGiayTamTru char(12) NOT NULL,
    batDau date  NOT NULL,
    ketThuc date  NOT NULL,
    lyDo varchar(200)  NOT NULL,
    CONSTRAINT TamTru_pk PRIMARY KEY (id)
);

-- Table: TamVang
CREATE TABLE TamVang (
    id int AUTO_INCREMENT NOT NULL,
    idNhanKhau int  NOT NULL,
    maGiayTamVang char(12) NOT NULL,
    batDau date  NOT NULL,
    ketThuc date  NOT NULL,
    noiTamTru varchar(100)  NOT NULL,
    lyDo varchar(200)  NOT NULL,
    CONSTRAINT TamVang_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: CCCD_NhanKhau (table: CCCD)
ALTER TABLE CCCD ADD CONSTRAINT CCCD_NhanKhau FOREIGN KEY CCCD_NhanKhau (idNhanKhau)
    REFERENCES NhanKhau (id);

-- Reference: KhaiBaoMacCOVID_MacCOVID (table: KhaiBao)
ALTER TABLE KhaiBao ADD CONSTRAINT KhaiBaoMacCOVID_MacCOVID FOREIGN KEY KhaiBaoMacCOVID_MacCOVID (idMacCOVID)
    REFERENCES MacCOVID (id);

-- Reference: KhaiTu_NhanKhau (table: KhaiTu)
ALTER TABLE KhaiTu ADD CONSTRAINT KhaiTu_NhanKhau FOREIGN KEY KhaiTu_NhanKhau (idNhanKhau)
    REFERENCES NhanKhau (id);

-- Reference: MacCOVID_NhanKhau (table: MacCOVID)
ALTER TABLE MacCOVID ADD CONSTRAINT MacCOVID_NhanKhau FOREIGN KEY MacCOVID_NhanKhau (idNhanKhau)
    REFERENCES NhanKhau (id);

-- Reference: NhanKhau_HoKhau (table: NhanKhau)
ALTER TABLE NhanKhau ADD CONSTRAINT NhanKhau_HoKhau FOREIGN KEY NhanKhau_HoKhau (idHoKhau)
    REFERENCES HoKhau (id);

-- Reference: TamTru_NhanKhau (table: TamTru)
ALTER TABLE TamTru ADD CONSTRAINT TamTru_NhanKhau FOREIGN KEY TamTru_NhanKhau (idNhanKhau)
    REFERENCES NhanKhau (id);

-- Reference: TamVang_NhanKhau (table: TamVang)
ALTER TABLE TamVang ADD CONSTRAINT TamVang_NhanKhau FOREIGN KEY TamVang_NhanKhau (idNhanKhau)
    REFERENCES NhanKhau (id);

-- End of file.

show tables