CREATE TABLE `SAN_PHAM` (
  `id` char(12) PRIMARY KEY NOT NULL,
  `name` nvarchar(50) UNIQUE NOT NULL COMMENT 'Tên sản phẩm',
  `isDelete` bool DEFAULT 0,
  `id_cate` char(7) COMMENT 'Loại sản phẩm',
  `baohanh` int(4) COMMENT 'Bảo hàng theo tháng',
  `img` varchar(200) COMMENT 'Link ảnh'
);

CREATE TABLE `OPTION_SAN_PHAM` (
  `id_sp` char(12) PRIMARY KEY NOT NULL,
  `color` nvarchar(30) NOT NULL COMMENT 'Màu',
  `isDelete` bool DEFAULT 0
);

CREATE TABLE `PHAN_LOAI` (
  `id` char(7) PRIMARY KEY NOT NULL,
  `name` nvarchar(50) UNIQUE NOT NULL,
  `isDelete` bool DEFAULT 0
);

CREATE TABLE `CT_SAN_PHAM` (
  `seri` char(14) PRIMARY KEY,
  `id_sp` char(12) NOT NULL,
  `color` nvarchar(30) NOT NULL COMMENT 'Màu',
  `price` int(10) NOT NULL COMMENT 'Giá bán của sp',
  'ngaynhap' datetime NOT NULL COMMENT 'Ngày nhập hàng',
  `isDelete` bool DEFAULT 0
);

CREATE TABLE `HOA_DON` (
  `id` char(16) PRIMARY KEY NOT NULL,
  `ngaylap` datetime NOT NULL COMMENT 'Ngày Lập Hóa Đơn',
  `order_amount` int(8) COMMENT 'Tổng tiền theo chi tiết hóa đơn',
  `discount_amount` int(8) COMMENT 'Tổng chiết khấu + giảm giá',
  `ghichu` nvarchar(200) COMMENT 'Ghi chú cho đơn',
  `km` char(14) COMMENT 'Khuyến Mãi',
  `pttt` char(6) NOT NULL COMMENT 'Phương thức thanh toán',
  `id_khachhang` char(14) NOT NULL COMMENT 'Địa chỉ giao hàng',
  `id_nhanvien` char(10) NOT NULL COMMENT 'Nhân viên bán hàng',
  `isDelete` bool DEFAULT 0
);

CREATE TABLE `CT_HOA_DON` (
  `id_hoadon` char(16) COMMENT 'Mã Hóa Đơn',
  `seri` char(14) COMMENT 'Số seri của sản phẩm được chọn',
  `don_gia` int(10) NOT NULL COMMENT 'Đơn giá sản phẩm',
  PRIMARY KEY (`id_hoadon`, `seri`)
);

CREATE TABLE `PTTT` (
  `id` char(6) PRIMARY KEY NOT NULL,
  `name` nvarchar(50) UNIQUE NOT NULL,
);

CREATE TABLE `KHUYEN_MAI` (
  `id` char(14) PRIMARY KEY NOT NULL,
  `startDatetime` datetime NOT NULL COMMENT 'Ngày bắt đầu',
  `endDatetime` datetime NOT NULL COMMENT 'Ngày kết thúc',
  `donviKM` bool DEFAULT 0,
  `value` int(10) NOT NULL COMMENT 'Giá trị áp dụng theo đơn vị',
  `isDelete` bool DEFAULT 0 
);

CREATE TABLE `CT_KM` (
  `id_km` char(14) PRIMARY KEY NOT NULL COMMENT 'Mã Khuyến Mãi',
  `id_sp` char(12) COMMENT 'Món Khuyến Mãi'
);

CREATE TABLE `KHACH_HANG` (
  `id` char(14) PRIMARY KEY NOT NULL,
  `name` nvarchar(50) NOT NULL,
  `phone` varchar(15) UNIQUE NOT NULL COMMENT 'Số điện thoại',
  `address` nvarchar(200) NOT NULL COMMENT 'Số nhà, tên đường',
  `district` nvarchar(50) NOT NULL COMMENT 'Quận',
  `ward` nvarchar(50) NOT NULL COMMENT 'Phường',
  `city` nvarchar(50) NOT NULL COMMENT 'Thành phố',
  `isDelete` bool DEFAULT 0 
);

CREATE TABLE `TAI_KHOAN` (
  `username` varchar(30) PRIMARY KEY,
  `password` varchar(20) NOT NULL,
  `id_nhanvien` varchar(14) COMMENT 'Mã Thông Tin Tài Khoản',
  `id_quyen` char(6) COMMENT 'Mã Quyền',
  `isDelete` bool DEFAULT 0 
);

CREATE TABLE `NHAN_VIEN` (
  `id` char(10) PRIMARY KEY NOT NULL,
  `name` nvarchar(50) NOT NULL,
  `phone` varchar(12) NOT NULL COMMENT 'Số điện thoại',
  `gender` bool NOT NULL COMMENT 'Giới tính',
  `birth` date NOT NULL COMMENT 'Ngày sinh',
  `email` varchar(50) NOT NULL COMMENT 'Email',
  `isDelete` bool DEFAULT 0 
);

CREATE TABLE `QUYEN` (
  `id` char(6) PRIMARY KEY NOT NULL,
  `name` nvarchar(50) NOT NULL,
  `isDelete` bool DEFAULT 0
);

CREATE TABLE `CHUC_NANG` (
  `id` char(6) PRIMARY KEY NOT NULL,
  `name` nvarchar(50) UNIQUE NOT NULL,
);

CREATE TABLE `CT_QUYEN` (
  `id_quyen` char(6) COMMENT 'Mã Quyền',
  `id_chucnang` char(6) COMMENT 'Mã Chức năng',
  `show` bool DEFAULT 1,
  `create` bool DEFAULT 1,
  `edit` bool DEFAULT 1,
  `remove` bool DEFAULT 1,
  PRIMARY KEY (`id_quyen`, `id_chucnang`)
);

CREATE TABLE `PHIEU_NHAP_KHO` (
  `id` char(8) PRIMARY KEY NOT NULL,
  `ngaynhap` datetime NOT NULL COMMENT 'Ngày nhập hàng',
  `total` int(8) NOT NULL COMMENT 'Tổng tiền nhập hàng',
  `id_nhanvien` char(10) COMMENT 'Nhân viên phụ trách',
  `id_ncc` char(7) COMMENT 'Nhà cung cấp',
  `isDelete` bool DEFAULT 0
);

CREATE TABLE `CT_NHAP_KHO` (
  `id_pn` char(12) COMMENT 'Mã Phiếu Nhập Kho',
  `seri` char(14) COMMENT 'Số seri',
  `cost` int(8) NOT NULL COMMENT 'Giá gốc thu mua',
  PRIMARY KEY (`id_pn`, `seri`)
);

CREATE TABLE `NCC` (
  `id` char(7) PRIMARY KEY NOT NULL,
  `name` nvarchar(50) UNIQUE NOT NULL COMMENT 'Tên Nhà Cung Cấp',
  `phone` varchar(12) NOT NULL COMMENT 'Số điện thoại liên lạc',
  `isDelete` bool DEFAULT 0,
  `address` nvarchar(50) COMMENT 'Số nhà, tên đường',
  `ward` nvarchar(50) COMMENT 'Phường',
  `district` nvarchar(50) COMMENT 'Quận',
  `city` nvarchar(50) COMMENT 'Thành phố'
);

CREATE TABLE `PHIEU_BAO_HANH` (
  `id` char(8) PRIMARY KEY NOT NULL,
  `ngaylap` datetime NOT NULL COMMENT 'Ngày lập phiếu',
  `id_nhanvien` char(10) COMMENT 'Nhân viên phụ trách',
  `id_hoadon` char(16) COMMENT 'Mã Hóa Đơn',
  `ngaytrahang` datetime NOT NULL COMMENT 'Ngày trả hàng dự kiến',
  `isDelete` bool DEFAULT 0
);

CREATE TABLE `CT_BAOHANH` (
  `id_bh` char(8) COMMENT 'Mã Phiếu Bảo Hành',
  `seri` char(14) COMMENT 'Số seri',
  PRIMARY KEY (`id_bh`, `seri`)
);

ALTER TABLE `SAN_PHAM` ADD FOREIGN KEY (`id_cate`) REFERENCES `PHAN_LOAI` (`id`);

ALTER TABLE `CT_SAN_PHAM` ADD FOREIGN KEY (`id_sp`) REFERENCES `SAN_PHAM` (`id`);

ALTER TABLE `OPTION_SAN_PHAM` ADD FOREIGN KEY (`id_sp`) REFERENCES `SAN_PHAM` (`id`);

ALTER TABLE `HOA_DON` ADD FOREIGN KEY (`km`) REFERENCES `KHUYEN_MAI` (`id`);

ALTER TABLE `HOA_DON` ADD FOREIGN KEY (`pttt`) REFERENCES `PTTT` (`id`);

ALTER TABLE `HOA_DON` ADD FOREIGN KEY (`id_khachhang`) REFERENCES `KHACH_HANG` (`id`);

ALTER TABLE `HOA_DON` ADD FOREIGN KEY (`id_nhanvien`) REFERENCES `NHAN_VIEN` (`id`);

ALTER TABLE `CT_HOA_DON` ADD FOREIGN KEY (`id_hoadon`) REFERENCES `HOA_DON` (`id`);

ALTER TABLE `CT_HOA_DON` ADD FOREIGN KEY (`seri`) REFERENCES `CT_SAN_PHAM` (`seri`);

ALTER TABLE `CT_KM` ADD FOREIGN KEY (`id_km`) REFERENCES `KHUYEN_MAI` (`id`);

ALTER TABLE `CT_KM` ADD FOREIGN KEY (`id_sp`) REFERENCES `SAN_PHAM` (`id`);

ALTER TABLE `TAI_KHOAN` ADD FOREIGN KEY (`id_nhanvien`) REFERENCES `NHAN_VIEN` (`id`);

ALTER TABLE `TAI_KHOAN` ADD FOREIGN KEY (`id_quyen`) REFERENCES `QUYEN` (`id`);

ALTER TABLE `CT_QUYEN` ADD FOREIGN KEY (`id_quyen`) REFERENCES `QUYEN` (`id`);

ALTER TABLE `CT_QUYEN` ADD FOREIGN KEY (`id_chucnang`) REFERENCES `CHUC_NANG` (`id`);

ALTER TABLE `PHIEU_NHAP_KHO` ADD FOREIGN KEY (`id_nhanvien`) REFERENCES `NHAN_VIEN` (`id`);

ALTER TABLE `PHIEU_NHAP_KHO` ADD FOREIGN KEY (`id_ncc`) REFERENCES `NCC` (`id`);

ALTER TABLE `CT_NHAP_KHO` ADD FOREIGN KEY (`id_pn`) REFERENCES `PHIEU_NHAP_KHO` (`id`);

ALTER TABLE `CT_NHAP_KHO` ADD FOREIGN KEY (`seri`) REFERENCES `CT_SAN_PHAM` (`seri`);

ALTER TABLE `PHIEU_BAO_HANH` ADD FOREIGN KEY (`id_nhanvien`) REFERENCES `NHAN_VIEN` (`id`);

ALTER TABLE `PHIEU_BAO_HANH` ADD FOREIGN KEY (`id_hoadon`) REFERENCES `HOA_DON` (`id`);

ALTER TABLE `CT_BAOHANH` ADD FOREIGN KEY (`id_bh`) REFERENCES `PHIEU_BAO_HANH` (`id`);

ALTER TABLE `CT_BAOHANH` ADD FOREIGN KEY (`seri`) REFERENCES `CT_SAN_PHAM` (`seri`);
