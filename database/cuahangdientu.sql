CREATE TABLE `PRODUCT` (
  `id` char(12) PRIMARY KEY NOT NULL,
  `name` nvarchar(50) UNIQUE NOT NULL COMMENT 'Tên sản phẩm',
  `isDelete` bool DEFAULT 0,
  `id_cate` char(7) COMMENT 'Loại sản phẩm',
  `baohanh` int(4) COMMENT 'Bảo hàng theo tháng',
  `des` nvarchar(200) COMMENT 'Mô tả',
  `img` varchar(200) COMMENT 'Link ảnh'
);

CREATE TABLE `CATEGORY` (
  `id` char(7) PRIMARY KEY NOT NULL,
  `name` nvarchar(50) UNIQUE NOT NULL,
  `isDelete` bool DEFAULT 0
);

CREATE TABLE `CT_PRO` (
  `seri` char(14) PRIMARY KEY,
  `id_pr` char(12) NOT NULL,
  `color` nvarchar(30) NOT NULL COMMENT 'Màu',
  `price` int(10) NOT NULL COMMENT 'Giá bán của sp',
  `isDelete` bool DEFAULT 0
);

CREATE TABLE `HOA_DON` (
  `id` char(16) PRIMARY KEY NOT NULL,
  `ngaylap` date NOT NULL COMMENT 'Ngày Lập Hóa Đơn',
  `order_amount` int(8) COMMENT 'Tổng tiền theo chi tiết hóa đơn',
  `discount_amount` int(8) COMMENT 'Tổng chiết khấu + giảm giá',
  `status` ENUM ('CH_THANHTOAN', 'CH_GIAO', 'CH_HUY', 'CH_BAOHANH') NOT NULL COMMENT 'Trạng thái của đơn',
  `ghichu` nvarchar(100) COMMENT 'Ghi chú cho đơn',
  `km` char(14) COMMENT 'Khuyến Mãi',
  `pttt` char(6) NOT NULL COMMENT 'Phương thức thanh toán',
  `khachhang` char(14) NOT NULL COMMENT 'Địa chỉ giao hàng',
  `nhanvien` char(10) NOT NULL COMMENT 'Nhân viên bán hàng'
);

CREATE TABLE `CT_HOA_DON` (
  `id_hoa_don` char(16) COMMENT 'Mã Hóa Đơn',
  `seri` char(14) COMMENT 'Số seri của sản phẩm được chọn',
  `don_gia` int(10) NOT NULL COMMENT 'Đơn giá sản phẩm',
  PRIMARY KEY (`id_hoa_don`, `seri`)
);

CREATE TABLE `PTTT` (
  `id` char(6) PRIMARY KEY NOT NULL,
  `name` nvarchar(50) UNIQUE NOT NULL,
  `isDelete` bool DEFAULT 0
);

CREATE TABLE `KHUYEN_MAI` (
  `id` char(14) PRIMARY KEY NOT NULL,
  `startDate` date NOT NULL COMMENT 'Ngày bắt đầu',
  `endDate` date NOT NULL COMMENT 'Ngày kết thúc',
  `donviKM` bool DEFAULT 0,
  `value` int(10) NOT NULL COMMENT 'Giá trị áp dụng theo đơn vị',
  `isDelete` bool DEFAULT 0 ,
  `des` nvarchar(500) COMMENT 'Mô tả khuyến mãi'
);

CREATE TABLE `CT_KM` (
  `id_km` char(14) PRIMARY KEY NOT NULL COMMENT 'Mã Khuyến Mãi',
  `id_pr` char(12) COMMENT 'Món Khuyến Mãi'
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
  `password` varchar(20),
  `type` bool DEFAULT 0,
  `id_nv` varchar(14) COMMENT 'Mã Thông Tin Tài Khoản',
  `id_quyen` char(6) COMMENT 'Mã Quyền',
  `isDelete` bool DEFAULT 0 
);

CREATE TABLE `NHAN_VIEN` (
  `id` char(10) PRIMARY KEY NOT NULL,
  `name` nvarchar(50),
  `phone` varchar(12) NOT NULL COMMENT 'Số điện thoại',
  `isDelete` bool DEFAULT 0 
);

CREATE TABLE `QUYEN` (
  `id` char(6) PRIMARY KEY NOT NULL,
  `name` nvarchar(50),
  `des` nvarchar(100) COMMENT 'Mô tả chức vụ',
  `isDelete` bool DEFAULT 0
);

CREATE TABLE `CHUC_NANG` (
  `id` char(6) PRIMARY KEY NOT NULL,
  `name` nvarchar(100) UNIQUE NOT NULL,
  `isDelete` bool DEFAULT 0 
);

CREATE TABLE `CT_QUYEN` (
  `id_quyen` char(6) COMMENT 'Mã Quyền',
  `id_chucnang` char(6) COMMENT 'Mã Chức năng',
  `show` bool DEFAULT 1,
  `insert` bool DEFAULT 1,
  `edit` bool DEFAULT 1,
  `delete` bool DEFAULT 1,
  PRIMARY KEY (`id_quyen`, `id_chucnang`)
);

CREATE TABLE `PHIEU_NHAP_KHO` (
  `id` char(8) PRIMARY KEY NOT NULL,
  `ngaynhap` date NOT NULL COMMENT 'Ngày nhập hàng',
  `total` int(8) NOT NULL COMMENT 'Tổng tiền nhập hàng',
  `isDelete` bool DEFAULT 0,
  `nhanvien` char(10) COMMENT 'Nhân viên phụ trách',
  `id_ncc` char(7) COMMENT 'Nhà cung cấp'
);

CREATE TABLE `CT_NHAP_KHO` (
  `id_pn` char(12) COMMENT 'Mã Phiếu Nhập Kho',
  `seri` char(14) COMMENT 'Số seri',
  `cost` int(8) NOT NULL COMMENT 'Đơn giá / Giá gốc thu mua',
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
  `ngaylap` date NOT NULL COMMENT 'Ngày lập phiếu',
  `isDelete` bool DEFAULT 0,
  `nhanvien` char(10) COMMENT 'Nhân viên phụ trách',
  `id_hoa_don` char(16) COMMENT 'Mã Hóa Đơn',
  `ngaytrahang` date NOT NULL COMMENT 'Ngày trả hàng dự kiến'
);

CREATE TABLE `CT_BAOHANH` (
  `id_bh` char(8) COMMENT 'Mã Phiếu Bảo Hành',
  `seri` char(14) COMMENT 'Số seri',
  PRIMARY KEY (`id_bh`, `seri`)
);

ALTER TABLE `PRODUCT` ADD FOREIGN KEY (`id_cate`) REFERENCES `CATEGORY` (`id`);

ALTER TABLE `CT_PRO` ADD FOREIGN KEY (`id_pr`) REFERENCES `PRODUCT` (`id`);

ALTER TABLE `HOA_DON` ADD FOREIGN KEY (`km`) REFERENCES `KHUYEN_MAI` (`id`);

ALTER TABLE `HOA_DON` ADD FOREIGN KEY (`pttt`) REFERENCES `PTTT` (`id`);

ALTER TABLE `HOA_DON` ADD FOREIGN KEY (`khachhang`) REFERENCES `KHACH_HANG` (`id`);

ALTER TABLE `HOA_DON` ADD FOREIGN KEY (`nhanvien`) REFERENCES `NHAN_VIEN` (`id`);

ALTER TABLE `CT_HOA_DON` ADD FOREIGN KEY (`id_hoa_don`) REFERENCES `HOA_DON` (`id`);

ALTER TABLE `CT_HOA_DON` ADD FOREIGN KEY (`seri`) REFERENCES `CT_PRO` (`seri`);

ALTER TABLE `CT_KM` ADD FOREIGN KEY (`id_km`) REFERENCES `KHUYEN_MAI` (`id`);

ALTER TABLE `CT_KM` ADD FOREIGN KEY (`id_pr`) REFERENCES `PRODUCT` (`id`);

ALTER TABLE `TAI_KHOAN` ADD FOREIGN KEY (`id_nv`) REFERENCES `NHAN_VIEN` (`id`);

ALTER TABLE `TAI_KHOAN` ADD FOREIGN KEY (`id_quyen`) REFERENCES `QUYEN` (`id`);

ALTER TABLE `CT_QUYEN` ADD FOREIGN KEY (`id_quyen`) REFERENCES `QUYEN` (`id`);

ALTER TABLE `CT_QUYEN` ADD FOREIGN KEY (`id_chucnang`) REFERENCES `CHUC_NANG` (`id`);

ALTER TABLE `PHIEU_NHAP_KHO` ADD FOREIGN KEY (`nhanvien`) REFERENCES `NHAN_VIEN` (`id`);

ALTER TABLE `PHIEU_NHAP_KHO` ADD FOREIGN KEY (`id_ncc`) REFERENCES `NCC` (`id`);

ALTER TABLE `CT_NHAP_KHO` ADD FOREIGN KEY (`id_pn`) REFERENCES `PHIEU_NHAP_KHO` (`id`);

ALTER TABLE `CT_NHAP_KHO` ADD FOREIGN KEY (`seri`) REFERENCES `CT_PRO` (`seri`);

ALTER TABLE `PHIEU_BAO_HANH` ADD FOREIGN KEY (`nhanvien`) REFERENCES `NHAN_VIEN` (`id`);

ALTER TABLE `PHIEU_BAO_HANH` ADD FOREIGN KEY (`id_hoa_don`) REFERENCES `HOA_DON` (`id`);

ALTER TABLE `CT_BAOHANH` ADD FOREIGN KEY (`id_bh`) REFERENCES `PHIEU_BAO_HANH` (`id`);

ALTER TABLE `CT_BAOHANH` ADD FOREIGN KEY (`seri`) REFERENCES `CT_PRO` (`seri`);
