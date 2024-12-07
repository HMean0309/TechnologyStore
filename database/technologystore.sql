CREATE TABLE `SAN_PHAM`
(
    `id`       char(12) PRIMARY KEY NOT NULL,
    `name`     nvarchar(50)         NOT NULL COMMENT 'Tên sản phẩm',
    `isDelete` bool DEFAULT 0,
    `id_cate`  char(7) COMMENT 'Loại sản phẩm',
    `baohanh`  int(4) COMMENT 'Bảo hàng theo tháng',
    `img`      varchar(200) COMMENT 'Link ảnh'
);

CREATE TABLE `OPTION_SAN_PHAM`
(
    `id_sp`    char(12)     NOT NULL,
    `color`    nvarchar(30) NOT NULL COMMENT 'Màu',
    `isDelete` bool DEFAULT 0,
    PRIMARY KEY (`id_sp`, `color`)
);

CREATE TABLE `PHAN_LOAI`
(
    `id`       char(7) PRIMARY KEY NOT NULL,
    `name`     nvarchar(50)        NOT NULL,
    `isDelete` bool DEFAULT 0
);

CREATE TABLE `CT_SAN_PHAM`
(
    `seri`     char(14) PRIMARY KEY,
    `id_sp`    char(12)     NOT NULL,
    `color`    nvarchar(30) NOT NULL COMMENT 'Màu',
    `price`    int(10)      NOT NULL COMMENT 'Giá bán của sp',
    `isDelete` bool DEFAULT 0
);

CREATE TABLE `HOA_DON`
(
    `id`              char(16) PRIMARY KEY NOT NULL,
    `ngaylap`         datetime             NOT NULL COMMENT 'Ngày Lập Hóa Đơn',
    `order_amount`    int(8) COMMENT 'Tổng tiền theo chi tiết hóa đơn',
    `discount_amount` int(8) DEFAULT 0 COMMENT 'Tổng chiết khấu',
    `id_khachhang`    char(14)             NOT NULL COMMENT 'Địa chỉ giao hàng',
    `id_nhanvien`     char(10)             NOT NULL COMMENT 'Nhân viên bán hàng',
    `isDelete`        bool   DEFAULT 0
);

CREATE TABLE `CT_HOA_DON`
(
    `id_hoadon` char(16) COMMENT 'Mã Hóa Đơn',
    `seri`      char(14) COMMENT 'Số seri của sản phẩm được chọn',
    `don_gia`   int(10) NOT NULL COMMENT 'Đơn giá sản phẩm',
    PRIMARY KEY (`id_hoadon`, `seri`)
);

CREATE TABLE `KHACH_HANG`
(
    `id`       char(14) PRIMARY KEY NOT NULL,
    `name`     nvarchar(50)         NOT NULL,
    `phone`    varchar(15)          NOT NULL COMMENT 'Số điện thoại',
    `address`  nvarchar(200) COMMENT 'Số nhà, tên đường',
    `district` nvarchar(50) COMMENT 'Quận',
    `ward`     nvarchar(50) COMMENT 'Phường',
    `city`     nvarchar(50) COMMENT 'Thành phố',
    `isDelete` bool DEFAULT 0
);

CREATE TABLE `TAI_KHOAN`
(
    `username`    varchar(30) PRIMARY KEY,
    `password`    varchar(20) NOT NULL,
    `id_nhanvien` varchar(14) COMMENT 'Mã Thông Tin Tài Khoản',
    `id_quyen`    char(6) COMMENT 'Mã Quyền',
    `isDelete`    bool DEFAULT 0
);

CREATE TABLE `NHAN_VIEN`
(
    `id`       char(10) PRIMARY KEY NOT NULL,
    `name`     nvarchar(50)         NOT NULL,
    `phone`    varchar(12)          NOT NULL COMMENT 'Số điện thoại',
    `gender`   bool                 NOT NULL COMMENT 'Giới tính',
    `birth`    date                 NOT NULL COMMENT 'Ngày sinh',
    `email`    varchar(50)          NOT NULL COMMENT 'Email',
    `isDelete` bool DEFAULT 0
);

CREATE TABLE `QUYEN`
(
    `id`       char(6) PRIMARY KEY NOT NULL,
    `name`     nvarchar(50)        NOT NULL,
    `isDelete` bool DEFAULT 0
);

CREATE TABLE `CHUC_NANG`
(
    `id`   char(6) PRIMARY KEY NOT NULL,
    `name` nvarchar(50)        NOT NULL
);

CREATE TABLE `CT_QUYEN`
(
    `id_quyen`    char(6) COMMENT 'Mã Quyền',
    `id_chucnang` char(6) COMMENT 'Mã Chức năng',
    `permission`  ENUM ('create','view','update','delete') NOT NULL COMMENT 'Hành động được phép',
    PRIMARY KEY (`id_quyen`, `id_chucnang`, `permission`)
);

CREATE TABLE `PHIEU_NHAP_KHO`
(
    `id`          char(8) PRIMARY KEY NOT NULL,
    `ngaynhap`    datetime            NOT NULL COMMENT 'Ngày nhập hàng',
    `total`       int(8)              NOT NULL COMMENT 'Tổng tiền nhập hàng',
    `id_nhanvien` char(10) COMMENT 'Nhân viên phụ trách',
    `id_ncc`      char(7) COMMENT 'Nhà cung cấp',
    `isDelete`    bool DEFAULT 0
);

CREATE TABLE `CT_NHAP_KHO`
(
    `id_pn` char(12) COMMENT 'Mã Phiếu Nhập Kho',
    `seri`  char(14) COMMENT 'Số seri',
    `cost`  int(8) NOT NULL COMMENT 'Giá gốc thu mua',
    PRIMARY KEY (`id_pn`, `seri`)
);

CREATE TABLE `NCC`
(
    `id`       char(7) PRIMARY KEY NOT NULL,
    `name`     nvarchar(50)        NOT NULL COMMENT 'Tên Nhà Cung Cấp',
    `phone`    varchar(12)         NOT NULL COMMENT 'Số điện thoại liên lạc',
    `isDelete` bool DEFAULT 0,
    `address`  nvarchar(50) COMMENT 'Số nhà, tên đường',
    `ward`     nvarchar(50) COMMENT 'Phường',
    `district` nvarchar(50) COMMENT 'Quận',
    `city`     nvarchar(50) COMMENT 'Thành phố'
);

CREATE TABLE `PHIEU_BAO_HANH`
(
    `id`          char(8) PRIMARY KEY NOT NULL,
    `ngaybaohanh` datetime            NOT NULL COMMENT 'Ngày lập phiếu bảo hành',
    `id_nhanvien` char(10) COMMENT 'Nhân viên phụ trách',
    `id_hoadon`   char(16) COMMENT 'Mã Hóa Đơn',
    `ngaytrahang` date                NOT NULL COMMENT 'Ngày trả hàng dự kiến',
    `isDelete`    bool DEFAULT 0
);

CREATE TABLE CT_BAO_HANH
(
    `id_bh` char(8) COMMENT 'Mã Phiếu Bảo Hành',
    `seri`  char(14) COMMENT 'Số seri',
    PRIMARY KEY (`id_bh`, `seri`)
);

ALTER TABLE `SAN_PHAM`
    ADD FOREIGN KEY (`id_cate`) REFERENCES `PHAN_LOAI` (`id`);

ALTER TABLE `CT_SAN_PHAM`
    ADD FOREIGN KEY (`id_sp`) REFERENCES `SAN_PHAM` (`id`);

ALTER TABLE `OPTION_SAN_PHAM`
    ADD FOREIGN KEY (`id_sp`) REFERENCES `SAN_PHAM` (`id`);

ALTER TABLE `HOA_DON`
    ADD FOREIGN KEY (`id_khachhang`) REFERENCES `KHACH_HANG` (`id`);

ALTER TABLE `HOA_DON`
    ADD FOREIGN KEY (`id_nhanvien`) REFERENCES `NHAN_VIEN` (`id`);

ALTER TABLE `CT_HOA_DON`
    ADD FOREIGN KEY (`id_hoadon`) REFERENCES `HOA_DON` (`id`);

ALTER TABLE `CT_HOA_DON`
    ADD FOREIGN KEY (`seri`) REFERENCES `CT_SAN_PHAM` (`seri`);

ALTER TABLE `TAI_KHOAN`
    ADD FOREIGN KEY (`id_nhanvien`) REFERENCES `NHAN_VIEN` (`id`);

ALTER TABLE `TAI_KHOAN`
    ADD FOREIGN KEY (`id_quyen`) REFERENCES `QUYEN` (`id`);

ALTER TABLE `CT_QUYEN`
    ADD FOREIGN KEY (`id_quyen`) REFERENCES `QUYEN` (`id`);

ALTER TABLE `CT_QUYEN`
    ADD FOREIGN KEY (`id_chucnang`) REFERENCES `CHUC_NANG` (`id`);

ALTER TABLE `PHIEU_NHAP_KHO`
    ADD FOREIGN KEY (`id_nhanvien`) REFERENCES `NHAN_VIEN` (`id`);

ALTER TABLE `PHIEU_NHAP_KHO`
    ADD FOREIGN KEY (`id_ncc`) REFERENCES `NCC` (`id`);

ALTER TABLE `CT_NHAP_KHO`
    ADD FOREIGN KEY (`id_pn`) REFERENCES `PHIEU_NHAP_KHO` (`id`);

ALTER TABLE `CT_NHAP_KHO`
    ADD FOREIGN KEY (`seri`) REFERENCES `CT_SAN_PHAM` (`seri`);

ALTER TABLE `PHIEU_BAO_HANH`
    ADD FOREIGN KEY (`id_nhanvien`) REFERENCES `NHAN_VIEN` (`id`);

ALTER TABLE `PHIEU_BAO_HANH`
    ADD FOREIGN KEY (`id_hoadon`) REFERENCES `HOA_DON` (`id`);

ALTER TABLE CT_BAO_HANH
    ADD FOREIGN KEY (`id_bh`) REFERENCES `PHIEU_BAO_HANH` (`id`);

ALTER TABLE CT_BAO_HANH
    ADD FOREIGN KEY (`seri`) REFERENCES `CT_SAN_PHAM` (`seri`);

CREATE VIEW chitietsanpham AS
SELECT CTSP.seri,
       CTSP.id_sp,
       CTSP.color,
       CTSP.price,
       CNK.cost,
       CNK.id_pn,
       CHD.id_hoadon,
       PNK.ngaynhap,
       HD.ngaylap,
       CTSP.isDelete
FROM CT_SAN_PHAM CTSP
         LEFT JOIN CT_NHAP_KHO CNK on CTSP.seri = CNK.seri
         LEFT JOIN CT_HOA_DON CHD on CNK.seri = CHD.seri
         LEFT JOIN HOA_DON HD on CHD.id_hoadon = HD.id
         LEFT JOIN PHIEU_NHAP_KHO PNK on CNK.id_pn = PNK.id;

CREATE VIEW sanpham_count AS
SELECT sp.id,
       sp.name,
       (SELECT COUNT(*)
        FROM chitietsanpham ctsp
        WHERE ctsp.isDelete = 0
          and ctsp.id_hoadon IS NULL
          and ctsp.id_sp = sp.id) AS 'tonkho',
       sp.id_cate,
       PL.name                    as 'name_cate',
       sp.baohanh,
       sp.img
FROM SAN_PHAM sp
         LEFT JOIN PHAN_LOAI PL on sp.id_cate = PL.id
WHERE PL.isDelete = 0
  and sp.isDelete = 0;

CREATE VIEW chitietbaohanh AS
SELECT ctbh.id_bh,
       ctbh.seri,
       CSP.color,
       CSP.id_sp
FROM CT_BAO_HANH ctbh
         LEFT JOIN CT_SAN_PHAM CSP on ctbh.seri = CSP.seri;

CREATE VIEW TAIKHOAN_EMAIL AS
SELECT tk.username,
       tk.password,
       tk.id_nhanvien,
       tk.id_quyen,
       NV.email,
       Q.name      as 'name_quyen',
       tk.isDelete as 'trangthai'
FROM TAI_KHOAN tk
         LEFT JOIN NHAN_VIEN NV on NV.id = TK.id_nhanvien
         LEFT JOIN QUYEN Q on Q.id = TK.id_quyen
WHERE NV.isDelete = 0
  and Q.isDelete = 0;

CREATE VIEW NHANVIEN_USERNAME AS
SELECT NV.id,
       NV.name,
       NV.phone,
       NV.gender,
       NV.birth,
       NV.email,
       NV.isDelete,
       TK.username
FROM NHAN_VIEN NV
         LEFT JOIN TAI_KHOAN TK on TK.id_nhanvien = NV.id;

CREATE VIEW PHIEUNHAP_NV_NCC AS
SELECT PNK.id,
       PNK.ngaynhap,
       PNK.total,
       PNK.id_nhanvien,
       PNK.id_ncc,
       PNK.isDelete,
       NV.name  as 'name_nhanvien',
       NCC.name as 'name_ncc'
FROM PHIEU_NHAP_KHO PNK
         LEFT JOIN NHAN_VIEN NV on PNK.id_nhanvien = NV.id
         LEFT JOIN NCC NCC on PNK.id_ncc = NCC.id;

CREATE VIEW HOADON_NV_KH AS
SELECT HD.id,
       HD.ngaylap,
       HD.discount_amount,
       HD.order_amount,
       HD.id_khachhang,
       HD.id_nhanvien,
       HD.isDelete,
       NV.name as 'name_nhanvien',
       KH.name as 'name_khachhang'
FROM HOA_DON HD
         LEFT JOIN NHAN_VIEN NV on HD.id_nhanvien = NV.id
         LEFT JOIN KHACH_HANG KH on HD.id_khachhang = KH.id;

CREATE VIEW BAOHANH_NV_KH AS
SELECT PBH.id,
       PBH.ngaybaohanh,
       PBH.id_nhanvien,
       PBH.id_hoadon,
       PBH.ngaytrahang,
       PBH.isDelete,
       HD.id_khachhang,
       KH.name as 'name_khachhang',
       NV.name as 'name_nhanvien'
FROM PHIEU_BAO_HANH PBH
         LEFT JOIN HOA_DON HD on HD.id = PBH.id_hoadon
         LEFT JOIN KHACH_HANG KH on HD.id_khachhang = KH.id
         LEFT JOIN NHAN_VIEN NV on PBH.id_nhanvien = NV.id;

INSERT INTO `chuc_nang` (`id`, `name`)
VALUES ('SER001', 'Quản lý khách hàng'),
       ('SER002', 'Quản lý phân loại'),
       ('SER003', 'Quản lý nhà cung cấp'),
       ('SER004', 'Quản lý phiếu nhập'),
       ('SER005', 'Quản lý phân quyền'),
       ('SER006', 'Quản lý sản phẩm'),
       ('SER007', 'Quản lý tài khoản'),
       ('SER008', 'Quản lý thống kê'),
       ('SER010', 'Quản lý hóa đơn'),
       ('SER011', 'Quản lý nhân viên'),
       ('SER012', 'Quản lý bảo hảnh');

INSERT INTO `quyen` (`id`, `name`, `isDelete`)
VALUES ('POS001', 'Admin', 0),
       ('POS002', 'Quản Lý', 0);

INSERT INTO `ct_quyen` (`id_quyen`, `id_chucnang`, `permission`)
VALUES ('POS001', 'SER001', 'create'),
       ('POS001', 'SER001', 'view'),
       ('POS001', 'SER001', 'update'),
       ('POS001', 'SER001', 'delete'),
       ('POS001', 'SER002', 'create'),
       ('POS001', 'SER002', 'view'),
       ('POS001', 'SER002', 'update'),
       ('POS001', 'SER002', 'delete'),
       ('POS001', 'SER003', 'create'),
       ('POS001', 'SER003', 'view'),
       ('POS001', 'SER003', 'update'),
       ('POS001', 'SER003', 'delete'),
       ('POS001', 'SER004', 'create'),
       ('POS001', 'SER004', 'view'),
       ('POS001', 'SER004', 'update'),
       ('POS001', 'SER004', 'delete'),
       ('POS001', 'SER005', 'create'),
       ('POS001', 'SER005', 'view'),
       ('POS001', 'SER005', 'update'),
       ('POS001', 'SER005', 'delete'),
       ('POS001', 'SER006', 'create'),
       ('POS001', 'SER006', 'view'),
       ('POS001', 'SER006', 'update'),
       ('POS001', 'SER006', 'delete'),
       ('POS001', 'SER007', 'create'),
       ('POS001', 'SER007', 'view'),
       ('POS001', 'SER007', 'update'),
       ('POS001', 'SER007', 'delete'),
       ('POS001', 'SER008', 'create'),
       ('POS001', 'SER008', 'view'),
       ('POS001', 'SER008', 'update'),
       ('POS001', 'SER008', 'delete'),
       ('POS001', 'SER010', 'create'),
       ('POS001', 'SER010', 'view'),
       ('POS001', 'SER010', 'update'),
       ('POS001', 'SER010', 'delete'),
       ('POS001', 'SER011', 'create'),
       ('POS001', 'SER011', 'view'),
       ('POS001', 'SER011', 'update'),
       ('POS001', 'SER011', 'delete'),
       ('POS001', 'SER012', 'create'),
       ('POS001', 'SER012', 'view'),
       ('POS001', 'SER012', 'update'),
       ('POS001', 'SER012', 'delete'),
       ('POS002', 'SER001', 'create'),
       ('POS002', 'SER001', 'view'),
       ('POS002', 'SER001', 'update'),
       ('POS002', 'SER001', 'delete'),
       ('POS002', 'SER003', 'create'),
       ('POS002', 'SER003', 'view'),
       ('POS002', 'SER003', 'update'),
       ('POS002', 'SER003', 'delete'),
       ('POS002', 'SER011', 'create'),
       ('POS002', 'SER011', 'view'),
       ('POS002', 'SER011', 'update');

INSERT INTO `nhan_vien` (`id`, `name`, `phone`, `gender`, `birth`, `email`, `isDelete`)
VALUES ('STAFF00001', 'Huỳnh Ngọc Hải Dương', '0868707528', 1, '2004-09-22', 'duonghuynh22092004@gmail.com', 0);

INSERT INTO `tai_khoan` (`username`, `password`, `id_nhanvien`, `id_quyen`, `isDelete`)
VALUES ('admin', '12345678', 'STAFF00001', 'POS001', 0);