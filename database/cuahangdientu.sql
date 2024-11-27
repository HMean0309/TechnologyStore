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
    `id_sp`    char(12) PRIMARY KEY NOT NULL,
    `color`    nvarchar(30)         NOT NULL COMMENT 'Màu',
    `isDelete` bool DEFAULT 0
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
    `ngaynhap` datetime     NOT NULL COMMENT 'Ngày nhập hàng',
    `isDelete` bool DEFAULT 0
);

CREATE TABLE `HOA_DON`
(
    `id`              char(16) PRIMARY KEY NOT NULL,
    `ngaylap`         datetime             NOT NULL COMMENT 'Ngày Lập Hóa Đơn',
    `order_amount`    int(8) COMMENT 'Tổng tiền theo chi tiết hóa đơn',
    `discount_amount` int(8) COMMENT 'Tổng chiết khấu + giảm giá',
    `ghichu`          nvarchar(200) COMMENT 'Ghi chú cho đơn',
    `km`              char(14) COMMENT 'Khuyến Mãi',
    `pttt`            char(6)              NOT NULL COMMENT 'Phương thức thanh toán',
    `id_khachhang`    char(14)             NOT NULL COMMENT 'Địa chỉ giao hàng',
    `id_nhanvien`     char(10)             NOT NULL COMMENT 'Nhân viên bán hàng',
    `isDelete`        bool DEFAULT 0
);

CREATE TABLE `CT_HOA_DON`
(
    `id_hoadon` char(16) COMMENT 'Mã Hóa Đơn',
    `seri`      char(14) COMMENT 'Số seri của sản phẩm được chọn',
    `don_gia`   int(10) NOT NULL COMMENT 'Đơn giá sản phẩm',
    PRIMARY KEY (`id_hoadon`, `seri`)
);

CREATE TABLE `PTTT`
(
    `id`   char(6) PRIMARY KEY NOT NULL,
    `name` nvarchar(50)        NOT NULL
);

CREATE TABLE `KHUYEN_MAI`
(
    `id`            char(14) PRIMARY KEY NOT NULL,
    `startDatetime` datetime             NOT NULL COMMENT 'Ngày bắt đầu',
    `endDatetime`   datetime             NOT NULL COMMENT 'Ngày kết thúc',
    `donviKM`       bool DEFAULT 0,
    `value`         int(10)              NOT NULL COMMENT 'Giá trị áp dụng theo đơn vị',
    `isDelete`      bool DEFAULT 0
);

CREATE TABLE `CT_KM`
(
    `id_km` char(14) PRIMARY KEY NOT NULL COMMENT 'Mã Khuyến Mãi',
    `id_sp` char(12) COMMENT 'Món Khuyến Mãi'
);

CREATE TABLE `KHACH_HANG`
(
    `id`       char(14) PRIMARY KEY NOT NULL,
    `name`     nvarchar(50)         NOT NULL,
    `phone`    varchar(15)          NOT NULL COMMENT 'Số điện thoại',
    `address`  nvarchar(200)        NOT NULL COMMENT 'Số nhà, tên đường',
    `district` nvarchar(50)         NOT NULL COMMENT 'Quận',
    `ward`     nvarchar(50)         NOT NULL COMMENT 'Phường',
    `city`     nvarchar(50)         NOT NULL COMMENT 'Thành phố',
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
    `ngaylap`     datetime            NOT NULL COMMENT 'Ngày lập phiếu',
    `id_nhanvien` char(10) COMMENT 'Nhân viên phụ trách',
    `id_hoadon`   char(16) COMMENT 'Mã Hóa Đơn',
    `ngaytrahang` datetime            NOT NULL COMMENT 'Ngày trả hàng dự kiến',
    `isDelete`    bool DEFAULT 0
);

CREATE TABLE `CT_BAOHANH`
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
    ADD FOREIGN KEY (`km`) REFERENCES `KHUYEN_MAI` (`id`);

ALTER TABLE `HOA_DON`
    ADD FOREIGN KEY (`pttt`) REFERENCES `PTTT` (`id`);

ALTER TABLE `HOA_DON`
    ADD FOREIGN KEY (`id_khachhang`) REFERENCES `KHACH_HANG` (`id`);

ALTER TABLE `HOA_DON`
    ADD FOREIGN KEY (`id_nhanvien`) REFERENCES `NHAN_VIEN` (`id`);

ALTER TABLE `CT_HOA_DON`
    ADD FOREIGN KEY (`id_hoadon`) REFERENCES `HOA_DON` (`id`);

ALTER TABLE `CT_HOA_DON`
    ADD FOREIGN KEY (`seri`) REFERENCES `CT_SAN_PHAM` (`seri`);

ALTER TABLE `CT_KM`
    ADD FOREIGN KEY (`id_km`) REFERENCES `KHUYEN_MAI` (`id`);

ALTER TABLE `CT_KM`
    ADD FOREIGN KEY (`id_sp`) REFERENCES `SAN_PHAM` (`id`);

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

ALTER TABLE `CT_BAOHANH`
    ADD FOREIGN KEY (`id_bh`) REFERENCES `PHIEU_BAO_HANH` (`id`);

ALTER TABLE `CT_BAOHANH`
    ADD FOREIGN KEY (`seri`) REFERENCES `CT_SAN_PHAM` (`seri`);

INSERT INTO `chuc_nang` (`id`, `name`) VALUES
('SER001', 'Quản lý khách hàng'),
('SER002', 'Quản lý khu vực kho'),
('SER003', 'Quản lý nhà cung cấp'),
('SER004', 'Quản lý nhập hàng'),
('SER005', 'Quản lý nhóm quyền'),
('SER006', 'Quản lý sản phẩm'),
('SER007', 'Quản lý tài khoản'),
('SER008', 'Quản lý thống kê'),
('SER009', 'Quản lý thuộc tính'),
('SER010', 'Quản lý xuất hàng'),
('SER011', 'Quản lý nhân viên');

INSERT INTO `quyen` (`id`, `name`, `isDelete`) VALUES
('POS001', 'Admin', 0);

INSERT INTO `ct_quyen` (`id_quyen`, `id_chucnang`, `permission`) VALUES
('POS001', 'SER001', 'create'),
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
('POS001', 'SER009', 'create'),
('POS001', 'SER009', 'view'),
('POS001', 'SER009', 'update'),
('POS001', 'SER009', 'delete'),
('POS001', 'SER010', 'create'),
('POS001', 'SER010', 'view'),
('POS001', 'SER010', 'update'),
('POS001', 'SER010', 'delete'),
('POS001', 'SER011', 'create'),
('POS001', 'SER011', 'view'),
('POS001', 'SER011', 'update'),
('POS001', 'SER011', 'delete');

INSERT INTO `ncc` (`id`, `name`, `phone`, `isDelete`, `address`, `ward`, `district`, `city`) VALUES
('NCC0001', 'Apple Pen', '0123456785', 0, '789 Tạ Quang Bửu', 'Quận Bình Thạnh', 'Phường 05', 'Thành phố Hồ Chí Minh'),
('NCC0002', 'Samsung', '0147852559', 0, '189 Cô Bắc', 'Quận Gò Vấp', 'Phường 03', 'Thành phố Hồ Chí Minh');

INSERT INTO `nhan_vien` (`id`, `name`, `phone`, `gender`, `birth`, `email`, `isDelete`) VALUES
('STAFF00001', 'Huỳnh Ngọc Hải Dương', '0868707529', 1, '2004-09-22', 'duonghnh2209@gmail.com', 0),
('STAFF00002', 'Hai Duong', '0868707527', 1, '2004-09-22', 'duong229@gmail.com', 0),
('STAFF00003', 'Duong Donate', '0868707528', 1, '2004-09-22', 'duong2209@gmail.com', 1);

INSERT INTO `tai_khoan` (`username`, `password`, `id_nhanvien`, `id_quyen`, `isDelete`) VALUES
('duongdonate', 'HD12345678', 'STAFF00001', 'POS001', 0);

INSERT INTO `khach_hang` (`id`, `name`, `phone`, `address`, `district`, `ward`, `city`, `isDelete`) VALUES
('CUSTOMER00001', 'Hải Dương', '0868707528', '125 Nguyễn Thị Tần', 'Quận 8', 'Phường 01', 'Thành phố Hồ Chí Minh', 0),
('CUSTOMER00002', 'Trần Hữu Nghĩa', '0125874662', '25 Nguyễn Trực', 'Quận 8', 'Phường 01', 'Thành phố Hồ Chí Minh', 0);

CREATE TABLE `taikhoan_email` (
`email` varchar(50)
,`id_nhanvien` varchar(14)
,`id_quyen` char(6)
,`name_quyen` varchar(50)
,`password` varchar(20)
,`trangthai` tinyint(1)
,`username` varchar(30)
);

DROP TABLE IF EXISTS `taikhoan_email`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `taikhoan_email`  AS SELECT `tk`.`username` AS `username`, `tk`.`password` AS `password`, `tk`.`id_quyen` AS `id_quyen`, `tk`.`id_nhanvien` AS `id_nhanvien`, `nv`.`email` AS `email`, `q`.`name` AS `name_quyen`, `tk`.`isDelete` AS `trangthai` FROM ((`tai_khoan` `tk` left join `nhan_vien` `nv` on((`tk`.`id_nhanvien` = `nv`.`id`))) left join `quyen` `q` on((`tk`.`id_quyen` = `q`.`id`))) WHERE ((`q`.`isDelete` = 0) AND (`nv`.`isDelete` = 0))  ;

CREATE TABLE `nhanvien_username` (
`birth` date
,`email` varchar(50)
,`gender` tinyint(1)
,`id` char(10)
,`isDelete` tinyint(1)
,`name` varchar(50)
,`phone` varchar(12)
,`username` varchar(30)
);

DROP TABLE IF EXISTS `nhanvien_username`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `nhanvien_username`  AS SELECT `nv`.`id` AS `id`, `nv`.`name` AS `name`, `nv`.`phone` AS `phone`, `nv`.`gender` AS `gender`, `nv`.`birth` AS `birth`, `nv`.`email` AS `email`, `nv`.`isDelete` AS `isDelete`, `tk`.`username` AS `username` FROM (`nhan_vien` `nv` left join `tai_khoan` `tk` on((`nv`.`id` = `tk`.`id_nhanvien`))) WHERE (`nv`.`isDelete` = 0)  ;