
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


CREATE TABLE `baohanh_nv_kh` (
`id` char(8)
,`ngaybaohanh` datetime
,`id_nhanvien` char(10)
,`id_hoadon` char(16)
,`ngaytrahang` date
,`name_nhanvien` varchar(50)
,`id_khachhang` char(14)
,`name_khachhang` varchar(50)
,`isDelete` tinyint(1)
);


CREATE TABLE `chitietbaohanh` (
`id_bh` char(8)
,`seri` char(14)
,`id_sp` char(12)
,`color` varchar(30)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `chitietsanpham`
-- (See below for the actual view)
--
CREATE TABLE `chitietsanpham` (
`seri` char(14)
,`id_sp` char(12)
,`color` varchar(30)
,`price` int(10)
,`isDelete` tinyint(1)
,`id_hoadon` char(16)
,`id_pn` char(12)
,`cost` int(8)
,`ngaynhap` datetime
,`ngaylap` datetime
);

-- --------------------------------------------------------

--
-- Table structure for table `chuc_nang`
--

CREATE TABLE `chuc_nang` (
  `id` char(6) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chuc_nang`
--

INSERT INTO `chuc_nang` (`id`, `name`) VALUES
('SER001', 'Quản lý khách hàng'),
('SER002', 'Quản lý phân loại'),
('SER003', 'Quản lý nhà cung cấp'),
('SER004', 'Quản lý nhập hàng'),
('SER005', 'Quản lý phân quyền'),
('SER006', 'Quản lý sản phẩm'),
('SER007', 'Quản lý tài khoản'),
('SER008', 'Quản lý thống kê'),
('SER009', 'Quản lý khuyến mãi'),
('SER010', 'Quản lý hóa đơn'),
('SER011', 'Quản lý nhân viên'),
('SER012', 'Quản lý bảo hảnh');

-- --------------------------------------------------------

--
-- Table structure for table `ct_bao_hanh`
--

CREATE TABLE `ct_bao_hanh` (
  `id_bh` char(8) NOT NULL COMMENT 'Mã Phiếu Bảo Hành',
  `seri` char(14) NOT NULL COMMENT 'Số seri'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ct_bao_hanh`
--

INSERT INTO `ct_bao_hanh` (`id_bh`, `seri`) VALUES
('BH000001', 'SERI0000700010'),
('BH000001', 'SERI0000700011');

-- --------------------------------------------------------

--
-- Table structure for table `ct_hoa_don`
--

CREATE TABLE `ct_hoa_don` (
  `id_hoadon` char(16) NOT NULL COMMENT 'Mã Hóa Đơn',
  `seri` char(14) NOT NULL COMMENT 'Số seri của sản phẩm được chọn',
  `don_gia` int(10) NOT NULL COMMENT 'Đơn giá sản phẩm'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ct_hoa_don`
--

INSERT INTO `ct_hoa_don` (`id_hoadon`, `seri`, `don_gia`) VALUES
('HD29112024000001', 'SERI0000300001', 50000),
('HD29112024000001', 'SERI0000300002', 50000),
('HD29112024000001', 'SERI0000700010', 80000),
('HD29112024000001', 'SERI0000700011', 80000);

-- --------------------------------------------------------

--
-- Table structure for table `ct_khuyen_mai`
--

CREATE TABLE `ct_khuyen_mai` (
  `id_km` char(14) NOT NULL COMMENT 'Mã khuyến mãi',
  `id_sp` char(12) NOT NULL COMMENT 'Mã sản phẩm',
  `isDelete` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ct_nhap_kho`
--

CREATE TABLE `ct_nhap_kho` (
  `id_pn` char(12) NOT NULL COMMENT 'Mã Phiếu Nhập Kho',
  `seri` char(14) NOT NULL COMMENT 'Số seri',
  `cost` int(8) NOT NULL COMMENT 'Giá gốc thu mua'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ct_nhap_kho`
--

INSERT INTO `ct_nhap_kho` (`id_pn`, `seri`, `cost`) VALUES
('PN000001', 'SERI0000300001', 40000),
('PN000001', 'SERI0000300002', 40000),
('PN000001', 'SERI0000300003', 40000),
('PN000001', 'SERI0000300004', 40000),
('PN000001', 'SERI0000300005', 40000),
('PN000001', 'SERI0000700001', 50000),
('PN000001', 'SERI0000700002', 50000),
('PN000002', 'SERI0000200001', 15000),
('PN000002', 'SERI0000200002', 15000),
('PN000002', 'SERI0000200003', 15000),
('PN000002', 'SERI0000200004', 15000),
('PN000003', 'SERI0000400001', 45000),
('PN000003', 'SERI0000400002', 45000),
('PN000003', 'SERI0000700003', 75000),
('PN000003', 'SERI0000700004', 75000),
('PN000003', 'SERI0000700005', 75000),
('PN000003', 'SERI0000700006', 75000),
('PN000004', 'SERI0001200001', 78000),
('PN000004', 'SERI0001200002', 78000),
('PN000004', 'SERI0001200003', 78000),
('PN000004', 'SERI0001200004', 78000),
('PN000004', 'SERI0001200005', 78000),
('PN000004', 'SERI0001200006', 78000),
('PN000004', 'SERI0001200007', 78000),
('PN000004', 'SERI0001200008', 78000),
('PN000004', 'SERI0001200009', 78000),
('PN000004', 'SERI0001200010', 78000),
('PN000005', 'SERI0001200011', 45000),
('PN000005', 'SERI0001200012', 45000),
('PN000005', 'SERI0001200013', 45000),
('PN000005', 'SERI0001200014', 45000),
('PN000005', 'SERI0001200015', 45000),
('PN000006', 'SERI0001200016', 45000),
('PN000006', 'SERI0001200017', 45000),
('PN000006', 'SERI0001200018', 45000),
('PN000006', 'SERI0001200019', 45000),
('PN000006', 'SERI0001200020', 75000),
('PN000007', 'SERI0001200021', 45000),
('PN000007', 'SERI0001200022', 45000),
('PN000007', 'SERI0001200023', 74000),
('PN000007', 'SERI0001200024', 74000),
('PN000007', 'SERI0001200025', 74000),
('PN000008', 'SERI0001200026', 40000),
('PN000008', 'SERI0001200027', 40000),
('PN000008', 'SERI0001200028', 45000),
('PN000008', 'SERI0001200029', 45000),
('PN000008', 'SERI0001200030', 45000),
('PN000009', 'SERI0000200005', 60000),
('PN000009', 'SERI0000200006', 60000),
('PN000009', 'SERI0000200007', 45000),
('PN000009', 'SERI0000200008', 45000),
('PN000009', 'SERI0000200009', 45000),
('PN000009', 'SERI0000200010', 45000),
('PN000009', 'SERI0000200011', 45000),
('PN000009', 'SERI0000200012', 45000),
('PN000009', 'SERI0000200013', 45000),
('PN000009', 'SERI0000200014', 45000),
('PN000010', 'SERI0000200015', 45000),
('PN000010', 'SERI0000200016', 45000),
('PN000010', 'SERI0000200017', 45000),
('PN000011', 'SERI0000700007', 50000),
('PN000011', 'SERI0000700008', 50000),
('PN000011', 'SERI0000700009', 45000),
('PN000011', 'SERI0000700010', 45000),
('PN000011', 'SERI0000700011', 45000),
('PN000011', 'SERI0000700012', 45000),
('PN000011', 'SERI0000700013', 45000);

-- --------------------------------------------------------

--
-- Table structure for table `ct_quyen`
--

CREATE TABLE `ct_quyen` (
  `id_quyen` char(6) NOT NULL COMMENT 'Mã Quyền',
  `id_chucnang` char(6) NOT NULL COMMENT 'Mã Chức năng',
  `permission` enum('create','view','update','delete') NOT NULL COMMENT 'Hành động được phép'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ct_quyen`
--

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

-- --------------------------------------------------------

--
-- Table structure for table `ct_san_pham`
--

CREATE TABLE `ct_san_pham` (
  `seri` char(14) NOT NULL,
  `id_sp` char(12) NOT NULL,
  `color` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Màu',
  `price` int(10) NOT NULL COMMENT 'Giá bán của sp',
  `isDelete` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ct_san_pham`
--

INSERT INTO `ct_san_pham` (`seri`, `id_sp`, `color`, `price`, `isDelete`) VALUES
('SERI0000200001', 'PRODUCT00002', 'Cam', 25000, 0),
('SERI0000200002', 'PRODUCT00002', 'Cam', 25000, 0),
('SERI0000200003', 'PRODUCT00002', 'Cam', 25000, 0),
('SERI0000200004', 'PRODUCT00002', 'Cam', 25000, 0),
('SERI0000200005', 'PRODUCT00002', 'Hồng', 85000, 0),
('SERI0000200006', 'PRODUCT00002', 'Hồng', 85000, 0),
('SERI0000200007', 'PRODUCT00002', 'Hồng', 80000, 0),
('SERI0000200008', 'PRODUCT00002', 'Hồng', 80000, 0),
('SERI0000200009', 'PRODUCT00002', 'Hồng', 80000, 0),
('SERI0000200010', 'PRODUCT00002', 'Hồng', 80000, 0),
('SERI0000200011', 'PRODUCT00002', 'Hồng', 80000, 0),
('SERI0000200012', 'PRODUCT00002', 'Hồng', 80000, 0),
('SERI0000200013', 'PRODUCT00002', 'Hồng', 80000, 0),
('SERI0000200014', 'PRODUCT00002', 'Hồng', 80000, 0),
('SERI0000200015', 'PRODUCT00002', 'Hồng', 50000, 0),
('SERI0000200016', 'PRODUCT00002', 'Hồng', 50000, 0),
('SERI0000200017', 'PRODUCT00002', 'Hồng', 50000, 0),
('SERI0000300001', 'PRODUCT00003', 'Xanh Duong', 50000, 0),
('SERI0000300002', 'PRODUCT00003', 'Xanh Duong', 50000, 0),
('SERI0000300003', 'PRODUCT00003', 'Xanh Duong', 50000, 0),
('SERI0000300004', 'PRODUCT00003', 'Xanh Duong', 50000, 0),
('SERI0000300005', 'PRODUCT00003', 'Xanh Duong', 50000, 0),
('SERI0000400001', 'PRODUCT00004', 'Red', 75000, 0),
('SERI0000400002', 'PRODUCT00004', 'Red', 75000, 0),
('SERI0000700001', 'PRODUCT00007', 'Pink', 80000, 0),
('SERI0000700002', 'PRODUCT00007', 'Pink', 80000, 0),
('SERI0000700003', 'PRODUCT00007', 'Pink', 100000, 0),
('SERI0000700004', 'PRODUCT00007', 'Pink', 100000, 0),
('SERI0000700005', 'PRODUCT00007', 'Pink', 100000, 0),
('SERI0000700006', 'PRODUCT00007', 'Pink', 100000, 0),
('SERI0000700007', 'PRODUCT00007', 'Pink', 90000, 0),
('SERI0000700008', 'PRODUCT00007', 'Pink', 90000, 0),
('SERI0000700009', 'PRODUCT00007', 'Fuck', 80000, 0),
('SERI0000700010', 'PRODUCT00007', 'Fuck', 80000, 0),
('SERI0000700011', 'PRODUCT00007', 'Fuck', 80000, 0),
('SERI0000700012', 'PRODUCT00007', 'Fuck', 80000, 0),
('SERI0000700013', 'PRODUCT00007', 'Fuck', 80000, 0),
('SERI0001200001', 'PRODUCT00012', 'Pink', 96000, 0),
('SERI0001200002', 'PRODUCT00012', 'Pink', 96000, 0),
('SERI0001200003', 'PRODUCT00012', 'Pink', 96000, 0),
('SERI0001200004', 'PRODUCT00012', 'Pink', 96000, 0),
('SERI0001200005', 'PRODUCT00012', 'Pink', 96000, 0),
('SERI0001200006', 'PRODUCT00012', 'Pink', 96000, 0),
('SERI0001200007', 'PRODUCT00012', 'Pink', 96000, 0),
('SERI0001200008', 'PRODUCT00012', 'Pink', 96000, 0),
('SERI0001200009', 'PRODUCT00012', 'Pink', 96000, 0),
('SERI0001200010', 'PRODUCT00012', 'Pink', 96000, 0),
('SERI0001200011', 'PRODUCT00012', 'Pink', 86000, 0),
('SERI0001200012', 'PRODUCT00012', 'Pink', 86000, 0),
('SERI0001200013', 'PRODUCT00012', 'Pink', 86000, 0),
('SERI0001200014', 'PRODUCT00012', 'Pink', 86000, 0),
('SERI0001200015', 'PRODUCT00012', 'Pink', 86000, 0),
('SERI0001200016', 'PRODUCT00012', 'Pink', 60000, 0),
('SERI0001200017', 'PRODUCT00012', 'Pink', 60000, 0),
('SERI0001200018', 'PRODUCT00012', 'Pink', 60000, 0),
('SERI0001200019', 'PRODUCT00012', 'Pink', 60000, 0),
('SERI0001200020', 'PRODUCT00012', 'Elec', 96000, 0),
('SERI0001200021', 'PRODUCT00012', 'Elec', 60000, 0),
('SERI0001200022', 'PRODUCT00012', 'Elec', 60000, 0),
('SERI0001200023', 'PRODUCT00012', 'Pink', 95000, 0),
('SERI0001200024', 'PRODUCT00012', 'Pink', 95000, 0),
('SERI0001200025', 'PRODUCT00012', 'Pink', 95000, 0),
('SERI0001200026', 'PRODUCT00012', 'Elec', 60000, 0),
('SERI0001200027', 'PRODUCT00012', 'Elec', 60000, 0),
('SERI0001200028', 'PRODUCT00012', 'Pink', 63000, 0),
('SERI0001200029', 'PRODUCT00012', 'Pink', 63000, 0),
('SERI0001200030', 'PRODUCT00012', 'Pink', 63000, 0);

-- --------------------------------------------------------

--
-- Stand-in structure for view `hoadon_nv_kh`
-- (See below for the actual view)
--
CREATE TABLE `hoadon_nv_kh` (
`id` char(16)
,`ngaylap` datetime
,`order_amount` int(8)
,`discount_amount` int(8)
,`km` char(14)
,`pttt` char(6)
,`id_khachhang` char(14)
,`id_nhanvien` char(10)
,`isDelete` tinyint(1)
,`name_nhanvien` varchar(50)
,`name_khachhang` varchar(50)
,`name_pttt` varchar(50)
);

-- --------------------------------------------------------

--
-- Table structure for table `hoa_don`
--

CREATE TABLE `hoa_don` (
  `id` char(16) NOT NULL,
  `ngaylap` datetime NOT NULL COMMENT 'Ngày Lập Hóa Đơn',
  `order_amount` int(8) DEFAULT NULL COMMENT 'Tổng tiền theo chi tiết hóa đơn',
  `discount_amount` int(8) DEFAULT NULL COMMENT 'Tổng chiết khấu + giảm giá',
  `ghichu` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'Ghi chú cho đơn',
  `km` char(14) DEFAULT NULL COMMENT 'Khuyến Mãi',
  `pttt` char(6) DEFAULT NULL COMMENT 'Phương thức thanh toán',
  `id_khachhang` char(14) NOT NULL COMMENT 'Địa chỉ giao hàng',
  `id_nhanvien` char(10) NOT NULL COMMENT 'Nhân viên bán hàng',
  `isDelete` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hoa_don`
--

INSERT INTO `hoa_don` (`id`, `ngaylap`, `order_amount`, `discount_amount`, `ghichu`, `km`, `pttt`, `id_khachhang`, `id_nhanvien`, `isDelete`) VALUES
('HD29112024000001', '2024-11-29 04:44:49', 260000, 0, NULL, NULL, NULL, 'CUSTOMER00007', 'STAFF00001', 0);

-- --------------------------------------------------------

--
-- Table structure for table `khach_hang`
--

CREATE TABLE `khach_hang` (
  `id` char(14) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(15) NOT NULL COMMENT 'Số điện thoại',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Số nhà, tên đường',
  `district` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Quận',
  `ward` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Phường',
  `city` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Thành phố',
  `isDelete` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `khach_hang`
--

INSERT INTO `khach_hang` (`id`, `name`, `phone`, `address`, `district`, `ward`, `city`, `isDelete`) VALUES
('CUSTOMER00001', 'Hải Dương', '0868707528', '125 Nguyễn Thị Tần', 'Quận 8', 'Phường 01', 'Thành phố Hồ Chí Minh', 0),
('CUSTOMER00002', 'Trần Hữu Nghĩa', '0125874662', '25 Nguyễn Trực', 'Quận 8', 'Phường 01', 'Thành phố Hồ Chí Minh', 0),
('CUSTOMER00003', 'Tiến Toàn', '0905286214', '147 Dương Bá Trạc', 'Quận 8', 'Phường 02', 'Thành phố Hồ Chí Minh', 1),
('CUSTOMER00004', 'Anh Thư', '0907530625', '789 Phạm Hữu Lầu', 'Quận 7', 'Phường Phú Thuận', 'Thành phố Hồ Chí Minh', 0),
('CUSTOMER00005', 'Duong Domic', '0868707527', '45 Dong Da', 'Huyện Ba Vì', 'Xã Ba Vì', 'Thành phố Hà Nội', 0),
('CUSTOMER00006', 'Duong', '0868707456', '145 Nguyen Thi Tan', 'Vui lòng chọn Tỉnh/Thành phố', 'Vui lòng chọn Quận/Huyện', 'Chọn Tỉnh/Thành phố', 1),
('CUSTOMER00007', 'Gia Liêm', '0907560528', '789 Ấp Kênh Kháng Chiến', 'Thị xã Cai Lậy', 'Xã Tân Bình', 'Tỉnh Tiền Giang', 0),
('CUSTOMER00008', 'Nhu Quynh', '0868737528', '78 Nguyen Du', 'Quận 8', 'Phường 01', 'Thành phố Hồ Chí Minh', 0),
('CUSTOMER00009', 'Gia Lao', '0907530962', '784/78 Nguyen Thi Dinh', 'Quận Tân Bình', 'Phường 03', 'Thành phố Hồ Chí Minh', 1),
('CUSTOMER00010', 'Huu Nghia', '0127452215', '125/744 Nguyen Thi Tan', 'Quận 8', 'Phường 01', 'Thành phố Hồ Chí Minh', 0),
('CUSTOMER00011', 'Duong Domic', '0868752521', '75 Bui Thi Xuan', 'Quận 1', 'Phường Cô Giang', 'Thành phố Hồ Chí Minh', 0),
('CUSTOMER00012', 'Nguyen Thanh Sang', '0123547855', '74 An Lac', 'Huyện An Lão', 'Thị trấn Trường Sơn', 'Thành phố Hải Phòng', 0),
('CUSTOMER00013', 'Tri Son', '0907530964', 'Chung cu Phong Xich Lam', 'Huyện Bình Chánh', 'Xã An Phú Tây', 'Thành phố Hồ Chí Minh', 0),
('CUSTOMER00014', 'Hung', '0905263456', 'Chung Cu Phu My', 'Huyện Thanh Oai', 'Thị trấn Kim Bài', 'Thành phố Hà Nội', 0);

-- --------------------------------------------------------

--
-- Table structure for table `khuyen_mai`
--

CREATE TABLE `khuyen_mai` (
  `id` char(14) NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Tên khuyến mãi',
  `discount_percent` int(11) NOT NULL COMMENT 'Phần trăm giảm giá',
  `min_order` int(11) NOT NULL COMMENT 'Giá trị đơn hàng tối thiểu',
  `startDate` datetime NOT NULL COMMENT 'Ngày bắt đầu',
  `endDate` datetime NOT NULL COMMENT 'Ngày kết thúc',
  `status` enum('Chưa bắt đầu','Có hiệu lực','Hết hạn') DEFAULT 'Chưa bắt đầu' COMMENT 'Trạng thái khuyến mãi',
  `isDelete` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `khuyen_mai`
--

INSERT INTO `khuyen_mai` (`id`, `name`, `discount_percent`, `min_order`, `startDate`, `endDate`, `status`, `isDelete`) VALUES
('KM01', 'Thang 11', 30, 0, '2024-11-27 00:00:00', '2024-11-30 00:00:00', 'Chưa bắt đầu', 0);

-- --------------------------------------------------------

--
-- Table structure for table `ncc`
--

CREATE TABLE `ncc` (
  `id` char(7) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Tên Nhà Cung Cấp',
  `phone` varchar(12) NOT NULL COMMENT 'Số điện thoại liên lạc',
  `isDelete` tinyint(1) DEFAULT 0,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'Số nhà, tên đường',
  `ward` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'Phường',
  `district` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'Quận',
  `city` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'Thành phố'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ncc`
--

INSERT INTO `ncc` (`id`, `name`, `phone`, `isDelete`, `address`, `ward`, `district`, `city`) VALUES
('NCC0001', 'Apple', '0907530962', 0, '789 Dương Bá Trạc', 'Phường Bến Thành', 'Quận 1', 'Thành phố Hồ Chí Minh');

-- --------------------------------------------------------

--
-- Stand-in structure for view `nhanvien_username`
-- (See below for the actual view)
--
CREATE TABLE `nhanvien_username` (
`id` char(10)
,`name` varchar(50)
,`phone` varchar(12)
,`gender` tinyint(1)
,`birth` date
,`email` varchar(50)
,`isDelete` tinyint(1)
,`username` varchar(30)
);

-- --------------------------------------------------------

--
-- Table structure for table `nhan_vien`
--

CREATE TABLE `nhan_vien` (
  `id` char(10) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(12) NOT NULL COMMENT 'Số điện thoại',
  `gender` tinyint(1) NOT NULL COMMENT 'Giới tính',
  `birth` date NOT NULL COMMENT 'Ngày sinh',
  `email` varchar(50) NOT NULL COMMENT 'Email',
  `isDelete` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhan_vien`
--

INSERT INTO `nhan_vien` (`id`, `name`, `phone`, `gender`, `birth`, `email`, `isDelete`) VALUES
('STAFF00001', 'Huỳnh Ngọc Hải Dương', '0868707529', 1, '2004-09-22', 'duonghuynh2209@gmail.com', 0),
('STAFF00002', 'Hai Duong', '0868707528', 1, '2004-09-22', 'duong229@gmail.com', 0),
('STAFF00003', 'Duong Donate', '0868707528', 1, '2004-09-22', 'duong2209@gmail.com', 1),
('STAFF00004', 'Anh Thu', '0987755224', 0, '2004-07-31', 'at4455@gmail.com', 0),
('STAFF00005', 'Gia Lào', '0125578466', 1, '2004-08-14', 'gialao784@gmail.com', 0),
('STAFF00006', 'Nhu Quynh', '0147855554', 0, '2005-12-12', 'nhuquynh788@gmail.com', 0),
('STAFF00007', 'Vo Quang Phu', '0122455445', 1, '2004-08-14', 'phuvq2004@gmail.com', 0);

-- --------------------------------------------------------

--
-- Table structure for table `option_san_pham`
--

CREATE TABLE `option_san_pham` (
  `id_sp` char(12) NOT NULL,
  `color` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Màu',
  `isDelete` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `option_san_pham`
--

INSERT INTO `option_san_pham` (`id_sp`, `color`, `isDelete`) VALUES
('PRODUCT00002', 'Cam', 0),
('PRODUCT00002', 'Hồng', 0),
('PRODUCT00003', 'Xanh Duong', 0),
('PRODUCT00004', 'Red', 0),
('PRODUCT00006', 'Red', 0),
('PRODUCT00007', 'Fuck', 0),
('PRODUCT00007', 'Pink', 0),
('PRODUCT00007', 'Red', 0),
('PRODUCT00012', 'Elec', 0),
('PRODUCT00012', 'Pink', 0),
('PRODUCT00013', 'Gay', 0),
('PRODUCT00013', 'Xanh', 0);

-- --------------------------------------------------------

--
-- Stand-in structure for view `permission`
-- (See below for the actual view)
--
CREATE TABLE `permission` (
`Tên quyền` varchar(50)
,`Tên chức năng` varchar(50)
,`Các hành động` mediumtext
);

-- --------------------------------------------------------

--
-- Table structure for table `phan_loai`
--

CREATE TABLE `phan_loai` (
  `id` char(7) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `isDelete` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phan_loai`
--

INSERT INTO `phan_loai` (`id`, `name`, `isDelete`) VALUES
('CATE001', 'Điện Thoại', 0),
('CATE002', 'Laptop', 0),
('CATE003', 'Tủ Lạnh', 0);

-- --------------------------------------------------------

--
-- Stand-in structure for view `phieunhap_nv_ncc`
-- (See below for the actual view)
--
CREATE TABLE `phieunhap_nv_ncc` (
`id` char(8)
,`ngaynhap` datetime
,`total` int(8)
,`id_nhanvien` char(10)
,`id_ncc` char(7)
,`isDelete` tinyint(1)
,`name_nhanvien` varchar(50)
,`name_ncc` varchar(50)
);

-- --------------------------------------------------------

--
-- Table structure for table `phieu_bao_hanh`
--

CREATE TABLE `phieu_bao_hanh` (
  `id` char(8) NOT NULL,
  `ngaybaohanh` datetime NOT NULL COMMENT 'Ngày lập phiếu',
  `id_nhanvien` char(10) DEFAULT NULL COMMENT 'Nhân viên phụ trách',
  `id_hoadon` char(16) DEFAULT NULL COMMENT 'Mã Hóa Đơn',
  `ngaytrahang` date NOT NULL COMMENT 'Ngày trả hàng dự kiến',
  `isDelete` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phieu_bao_hanh`
--

INSERT INTO `phieu_bao_hanh` (`id`, `ngaybaohanh`, `id_nhanvien`, `id_hoadon`, `ngaytrahang`, `isDelete`) VALUES
('BH000001', '2024-12-05 23:54:35', 'STAFF00001', 'HD29112024000001', '2024-12-06', 0);

-- --------------------------------------------------------

--
-- Table structure for table `phieu_nhap_kho`
--

CREATE TABLE `phieu_nhap_kho` (
  `id` char(8) NOT NULL,
  `ngaynhap` datetime NOT NULL COMMENT 'Ngày nhập hàng',
  `total` int(8) NOT NULL COMMENT 'Tổng tiền nhập hàng',
  `id_nhanvien` char(10) DEFAULT NULL COMMENT 'Nhân viên phụ trách',
  `id_ncc` char(7) DEFAULT NULL COMMENT 'Nhà cung cấp',
  `isDelete` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phieu_nhap_kho`
--

INSERT INTO `phieu_nhap_kho` (`id`, `ngaynhap`, `total`, `id_nhanvien`, `id_ncc`, `isDelete`) VALUES
('PN000001', '2024-11-27 02:39:49', 300000, 'STAFF00001', 'NCC0001', 0),
('PN000002', '2024-11-27 14:38:52', 60000, 'STAFF00001', 'NCC0001', 0),
('PN000003', '2024-11-28 02:24:38', 390000, 'STAFF00001', 'NCC0001', 0),
('PN000004', '2024-11-28 15:05:38', 1140000, 'STAFF00001', 'NCC0001', 0),
('PN000005', '2024-11-28 15:11:30', 459000, 'STAFF00001', 'NCC0001', 0),
('PN000006', '2024-11-28 15:13:11', 555000, 'STAFF00001', 'NCC0001', 0),
('PN000007', '2024-11-28 15:33:47', 312000, 'STAFF00001', 'NCC0001', 0),
('PN000008', '2024-11-28 15:45:37', 215000, 'STAFF00001', 'NCC0001', 0),
('PN000009', '2024-11-28 19:59:44', 480000, 'STAFF00001', 'NCC0001', 0),
('PN000010', '2024-11-28 20:09:49', 390000, 'STAFF00001', 'NCC0001', 0),
('PN000011', '2024-11-28 20:18:39', 325000, 'STAFF00001', 'NCC0001', 0);

-- --------------------------------------------------------

--
-- Table structure for table `pttt`
--

CREATE TABLE `pttt` (
  `id` char(6) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `quyen`
--

CREATE TABLE `quyen` (
  `id` char(6) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `isDelete` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `quyen`
--

INSERT INTO `quyen` (`id`, `name`, `isDelete`) VALUES
('POS001', 'Admin', 0),
('POS002', 'Quản Lý Cửa Hàng', 0);

-- --------------------------------------------------------

--
-- Stand-in structure for view `sanpham_count`
-- (See below for the actual view)
--
CREATE TABLE `sanpham_count` (
`id` char(12)
,`name` varchar(50)
,`tonkho` bigint(21)
,`id_cate` char(7)
,`name_cate` varchar(50)
,`baohanh` int(4)
,`img` varchar(200)
);

-- --------------------------------------------------------

--
-- Table structure for table `san_pham`
--

CREATE TABLE `san_pham` (
  `id` char(12) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Tên sản phẩm',
  `isDelete` tinyint(1) DEFAULT 0,
  `id_cate` char(7) DEFAULT NULL COMMENT 'Loại sản phẩm',
  `baohanh` int(4) DEFAULT NULL COMMENT 'Bảo hàng theo tháng',
  `img` varchar(200) DEFAULT NULL COMMENT 'Link ảnh'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `san_pham`
--

INSERT INTO `san_pham` (`id`, `name`, `isDelete`, `id_cate`, `baohanh`, `img`) VALUES
('PRODUCT00001', 'Samsung', 1, 'CATE001', 4, ''),
('PRODUCT00002', 'Lenovo', 0, 'CATE002', 12, ''),
('PRODUCT00003', 'Test', 0, 'CATE003', 0, ''),
('PRODUCT00004', 'Test2', 0, 'CATE003', 0, ''),
('PRODUCT00005', 'Zenitsu', 1, 'CATE002', 0, ''),
('PRODUCT00006', 'Hay Nha', 1, 'CATE002', 12, ''),
('PRODUCT00007', 'Ua', 0, 'CATE002', 74, ''),
('PRODUCT00008', 'shshshshs', 1, 'CATE001', 0, ''),
('PRODUCT00009', 'Magaming', 1, 'CATE001', 1, '115IMG_20210115_203906.jpg'),
('PRODUCT00010', 'Hay nha ba', 1, 'CATE001', 0, ''),
('PRODUCT00011', 'jjj', 1, 'CATE003', 0, ''),
('PRODUCT00012', 'Zenisu', 0, 'CATE001', 12, '742465911133_548708884581051_3241521075463914694_n.jpg'),
('PRODUCT00013', 'Sanpham', 0, 'CATE003', 5, '126SanPham.png');

-- --------------------------------------------------------

--
-- Stand-in structure for view `taikhoan_email`
-- (See below for the actual view)
--
CREATE TABLE `taikhoan_email` (
`username` varchar(30)
,`password` varchar(20)
,`id_quyen` char(6)
,`id_nhanvien` varchar(14)
,`email` varchar(50)
,`name_quyen` varchar(50)
,`trangthai` tinyint(1)
);

-- --------------------------------------------------------

--
-- Table structure for table `tai_khoan`
--

CREATE TABLE `tai_khoan` (
  `username` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL,
  `id_nhanvien` varchar(14) DEFAULT NULL COMMENT 'Mã Thông Tin Tài Khoản',
  `id_quyen` char(6) DEFAULT NULL COMMENT 'Mã Quyền',
  `isDelete` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tai_khoan`
--

INSERT INTO `tai_khoan` (`username`, `password`, `id_nhanvien`, `id_quyen`, `isDelete`) VALUES
('duongdonate', 'HD12345678', 'STAFF00001', 'POS001', 0),
('gialong45', 'long12345678', 'STAFF00005', 'POS002', 0),
('jorgani', 'AT12345678', 'STAFF00004', 'POS001', 0),
('nhuquynh45', 'NQ12345678', 'STAFF00006', 'POS001', 0);

-- --------------------------------------------------------

--
-- Structure for view `baohanh_nv_kh`
--
DROP TABLE IF EXISTS `baohanh_nv_kh`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `baohanh_nv_kh`  AS SELECT `pbh`.`id` AS `id`, `pbh`.`ngaybaohanh` AS `ngaybaohanh`, `pbh`.`id_nhanvien` AS `id_nhanvien`, `pbh`.`id_hoadon` AS `id_hoadon`, `pbh`.`ngaytrahang` AS `ngaytrahang`, `nv`.`name` AS `name_nhanvien`, `hd`.`id_khachhang` AS `id_khachhang`, `kh`.`name` AS `name_khachhang`, `pbh`.`isDelete` AS `isDelete` FROM (((`phieu_bao_hanh` `pbh` left join `nhan_vien` `nv` on(`nv`.`id` = `pbh`.`id_nhanvien`)) left join `hoa_don` `hd` on(`pbh`.`id_hoadon` = `hd`.`id`)) left join `khach_hang` `kh` on(`hd`.`id_khachhang` = `kh`.`id`)) ;

-- --------------------------------------------------------

--
-- Structure for view `chitietbaohanh`
--
DROP TABLE IF EXISTS `chitietbaohanh`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `chitietbaohanh`  AS SELECT `ctbh`.`id_bh` AS `id_bh`, `ctbh`.`seri` AS `seri`, `csp`.`id_sp` AS `id_sp`, `csp`.`color` AS `color` FROM (`ct_bao_hanh` `ctbh` left join `ct_san_pham` `csp` on(`csp`.`seri` = `ctbh`.`seri`)) ;

-- --------------------------------------------------------

--
-- Structure for view `chitietsanpham`
--
DROP TABLE IF EXISTS `chitietsanpham`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `chitietsanpham`  AS SELECT `ctsp`.`seri` AS `seri`, `ctsp`.`id_sp` AS `id_sp`, `ctsp`.`color` AS `color`, `ctsp`.`price` AS `price`, `ctsp`.`isDelete` AS `isDelete`, `chd`.`id_hoadon` AS `id_hoadon`, `cnk`.`id_pn` AS `id_pn`, `cnk`.`cost` AS `cost`, `pnk`.`ngaynhap` AS `ngaynhap`, `hd`.`ngaylap` AS `ngaylap` FROM ((((`ct_san_pham` `ctsp` left join `ct_hoa_don` `chd` on(`ctsp`.`seri` = `chd`.`seri`)) left join `ct_nhap_kho` `cnk` on(`ctsp`.`seri` = `cnk`.`seri`)) left join `phieu_nhap_kho` `pnk` on(`cnk`.`id_pn` = `pnk`.`id`)) left join `hoa_don` `hd` on(`chd`.`id_hoadon` = `hd`.`id`)) ;

-- --------------------------------------------------------

--
-- Structure for view `hoadon_nv_kh`
--
DROP TABLE IF EXISTS `hoadon_nv_kh`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `hoadon_nv_kh`  AS SELECT `hd`.`id` AS `id`, `hd`.`ngaylap` AS `ngaylap`, `hd`.`order_amount` AS `order_amount`, `hd`.`discount_amount` AS `discount_amount`, `hd`.`km` AS `km`, `hd`.`pttt` AS `pttt`, `hd`.`id_khachhang` AS `id_khachhang`, `hd`.`id_nhanvien` AS `id_nhanvien`, `hd`.`isDelete` AS `isDelete`, `nv`.`name` AS `name_nhanvien`, `kh`.`name` AS `name_khachhang`, `p`.`name` AS `name_pttt` FROM (((`hoa_don` `hd` left join `nhan_vien` `nv` on(`hd`.`id_nhanvien` = `nv`.`id`)) left join `khach_hang` `kh` on(`kh`.`id` = `hd`.`id_khachhang`)) left join `pttt` `p` on(`hd`.`pttt` = `p`.`id`)) ;

-- --------------------------------------------------------

--
-- Structure for view `nhanvien_username`
--
DROP TABLE IF EXISTS `nhanvien_username`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `nhanvien_username`  AS SELECT `nv`.`id` AS `id`, `nv`.`name` AS `name`, `nv`.`phone` AS `phone`, `nv`.`gender` AS `gender`, `nv`.`birth` AS `birth`, `nv`.`email` AS `email`, `nv`.`isDelete` AS `isDelete`, `tk`.`username` AS `username` FROM (`nhan_vien` `nv` left join `tai_khoan` `tk` on(`nv`.`id` = `tk`.`id_nhanvien`)) WHERE `nv`.`isDelete` = 0 ;

-- --------------------------------------------------------

--
-- Structure for view `permission`
--
DROP TABLE IF EXISTS `permission`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `permission`  AS SELECT `q`.`name` AS `Tên quyền`, `cn`.`name` AS `Tên chức năng`, group_concat(`ctq`.`permission` separator ', ') AS `Các hành động` FROM ((`ct_quyen` `ctq` left join `chuc_nang` `cn` on(`cn`.`id` = `ctq`.`id_chucnang`)) left join `quyen` `q` on(`ctq`.`id_quyen` = `q`.`id`)) GROUP BY `ctq`.`id_quyen`, `ctq`.`id_chucnang` ;

-- --------------------------------------------------------

--
-- Structure for view `phieunhap_nv_ncc`
--
DROP TABLE IF EXISTS `phieunhap_nv_ncc`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `phieunhap_nv_ncc`  AS SELECT `pnk`.`id` AS `id`, `pnk`.`ngaynhap` AS `ngaynhap`, `pnk`.`total` AS `total`, `pnk`.`id_nhanvien` AS `id_nhanvien`, `pnk`.`id_ncc` AS `id_ncc`, `pnk`.`isDelete` AS `isDelete`, `nv`.`name` AS `name_nhanvien`, `ncc`.`name` AS `name_ncc` FROM ((`phieu_nhap_kho` `pnk` left join `nhan_vien` `nv` on(`pnk`.`id_nhanvien` = `nv`.`id`)) left join `ncc` on(`pnk`.`id_ncc` = `ncc`.`id`)) ;

-- --------------------------------------------------------

--
-- Structure for view `sanpham_count`
--
DROP TABLE IF EXISTS `sanpham_count`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `sanpham_count`  AS SELECT `sp`.`id` AS `id`, `sp`.`name` AS `name`, (select count(0) from `ct_san_pham` `ctsp` where `ctsp`.`id_sp` = `sp`.`id`) AS `tonkho`, `sp`.`id_cate` AS `id_cate`, `pl`.`name` AS `name_cate`, `sp`.`baohanh` AS `baohanh`, `sp`.`img` AS `img` FROM (`san_pham` `sp` left join `phan_loai` `pl` on(`sp`.`id_cate` = `pl`.`id`)) WHERE `sp`.`isDelete` = 0 AND `pl`.`isDelete` = 0 ;

-- --------------------------------------------------------

--
-- Structure for view `taikhoan_email`
--
DROP TABLE IF EXISTS `taikhoan_email`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `taikhoan_email`  AS SELECT `tk`.`username` AS `username`, `tk`.`password` AS `password`, `tk`.`id_quyen` AS `id_quyen`, `tk`.`id_nhanvien` AS `id_nhanvien`, `nv`.`email` AS `email`, `q`.`name` AS `name_quyen`, `tk`.`isDelete` AS `trangthai` FROM ((`tai_khoan` `tk` left join `nhan_vien` `nv` on(`tk`.`id_nhanvien` = `nv`.`id`)) left join `quyen` `q` on(`tk`.`id_quyen` = `q`.`id`)) WHERE `q`.`isDelete` = 0 AND `nv`.`isDelete` = 0 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chuc_nang`
--
ALTER TABLE `chuc_nang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ct_bao_hanh`
--
ALTER TABLE `ct_bao_hanh`
  ADD PRIMARY KEY (`id_bh`,`seri`),
  ADD KEY `seri` (`seri`);

--
-- Indexes for table `ct_hoa_don`
--
ALTER TABLE `ct_hoa_don`
  ADD PRIMARY KEY (`id_hoadon`,`seri`),
  ADD KEY `seri` (`seri`);

--
-- Indexes for table `ct_khuyen_mai`
--
ALTER TABLE `ct_khuyen_mai`
  ADD PRIMARY KEY (`id_km`,`id_sp`),
  ADD KEY `id_sp` (`id_sp`);

--
-- Indexes for table `ct_nhap_kho`
--
ALTER TABLE `ct_nhap_kho`
  ADD PRIMARY KEY (`id_pn`,`seri`),
  ADD KEY `seri` (`seri`);

--
-- Indexes for table `ct_quyen`
--
ALTER TABLE `ct_quyen`
  ADD PRIMARY KEY (`id_quyen`,`id_chucnang`,`permission`),
  ADD KEY `id_chucnang` (`id_chucnang`);

--
-- Indexes for table `ct_san_pham`
--
ALTER TABLE `ct_san_pham`
  ADD PRIMARY KEY (`seri`),
  ADD KEY `id_sp` (`id_sp`);

--
-- Indexes for table `hoa_don`
--
ALTER TABLE `hoa_don`
  ADD PRIMARY KEY (`id`),
  ADD KEY `km` (`km`),
  ADD KEY `pttt` (`pttt`),
  ADD KEY `id_khachhang` (`id_khachhang`),
  ADD KEY `id_nhanvien` (`id_nhanvien`);

--
-- Indexes for table `khach_hang`
--
ALTER TABLE `khach_hang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `khuyen_mai`
--
ALTER TABLE `khuyen_mai`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ncc`
--
ALTER TABLE `ncc`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `nhan_vien`
--
ALTER TABLE `nhan_vien`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `option_san_pham`
--
ALTER TABLE `option_san_pham`
  ADD PRIMARY KEY (`id_sp`,`color`);

--
-- Indexes for table `phan_loai`
--
ALTER TABLE `phan_loai`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `phieu_bao_hanh`
--
ALTER TABLE `phieu_bao_hanh`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_nhanvien` (`id_nhanvien`),
  ADD KEY `id_hoadon` (`id_hoadon`);

--
-- Indexes for table `phieu_nhap_kho`
--
ALTER TABLE `phieu_nhap_kho`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_nhanvien` (`id_nhanvien`),
  ADD KEY `id_ncc` (`id_ncc`);

--
-- Indexes for table `pttt`
--
ALTER TABLE `pttt`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `quyen`
--
ALTER TABLE `quyen`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `san_pham`
--
ALTER TABLE `san_pham`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_cate` (`id_cate`);

--
-- Indexes for table `tai_khoan`
--
ALTER TABLE `tai_khoan`
  ADD PRIMARY KEY (`username`),
  ADD KEY `id_nhanvien` (`id_nhanvien`),
  ADD KEY `id_quyen` (`id_quyen`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `ct_bao_hanh`
--
ALTER TABLE `ct_bao_hanh`
  ADD CONSTRAINT `ct_bao_hanh_ibfk_1` FOREIGN KEY (`id_bh`) REFERENCES `phieu_bao_hanh` (`id`),
  ADD CONSTRAINT `ct_bao_hanh_ibfk_2` FOREIGN KEY (`seri`) REFERENCES `ct_san_pham` (`seri`);

--
-- Constraints for table `ct_hoa_don`
--
ALTER TABLE `ct_hoa_don`
  ADD CONSTRAINT `ct_hoa_don_ibfk_1` FOREIGN KEY (`id_hoadon`) REFERENCES `hoa_don` (`id`),
  ADD CONSTRAINT `ct_hoa_don_ibfk_2` FOREIGN KEY (`seri`) REFERENCES `ct_san_pham` (`seri`);

--
-- Constraints for table `ct_khuyen_mai`
--
ALTER TABLE `ct_khuyen_mai`
  ADD CONSTRAINT `ct_khuyen_mai_ibfk_1` FOREIGN KEY (`id_km`) REFERENCES `khuyen_mai` (`id`),
  ADD CONSTRAINT `ct_khuyen_mai_ibfk_2` FOREIGN KEY (`id_sp`) REFERENCES `san_pham` (`id`);

--
-- Constraints for table `ct_nhap_kho`
--
ALTER TABLE `ct_nhap_kho`
  ADD CONSTRAINT `ct_nhap_kho_ibfk_1` FOREIGN KEY (`id_pn`) REFERENCES `phieu_nhap_kho` (`id`),
  ADD CONSTRAINT `ct_nhap_kho_ibfk_2` FOREIGN KEY (`seri`) REFERENCES `ct_san_pham` (`seri`);

--
-- Constraints for table `ct_quyen`
--
ALTER TABLE `ct_quyen`
  ADD CONSTRAINT `ct_quyen_ibfk_1` FOREIGN KEY (`id_quyen`) REFERENCES `quyen` (`id`),
  ADD CONSTRAINT `ct_quyen_ibfk_2` FOREIGN KEY (`id_chucnang`) REFERENCES `chuc_nang` (`id`);

--
-- Constraints for table `ct_san_pham`
--
ALTER TABLE `ct_san_pham`
  ADD CONSTRAINT `ct_san_pham_ibfk_1` FOREIGN KEY (`id_sp`) REFERENCES `san_pham` (`id`);

--
-- Constraints for table `hoa_don`
--
ALTER TABLE `hoa_don`
  ADD CONSTRAINT `hoa_don_ibfk_1` FOREIGN KEY (`km`) REFERENCES `khuyen_mai` (`id`),
  ADD CONSTRAINT `hoa_don_ibfk_2` FOREIGN KEY (`pttt`) REFERENCES `pttt` (`id`),
  ADD CONSTRAINT `hoa_don_ibfk_3` FOREIGN KEY (`id_khachhang`) REFERENCES `khach_hang` (`id`),
  ADD CONSTRAINT `hoa_don_ibfk_4` FOREIGN KEY (`id_nhanvien`) REFERENCES `nhan_vien` (`id`);

--
-- Constraints for table `option_san_pham`
--
ALTER TABLE `option_san_pham`
  ADD CONSTRAINT `option_san_pham_ibfk_1` FOREIGN KEY (`id_sp`) REFERENCES `san_pham` (`id`);

--
-- Constraints for table `phieu_bao_hanh`
--
ALTER TABLE `phieu_bao_hanh`
  ADD CONSTRAINT `phieu_bao_hanh_ibfk_1` FOREIGN KEY (`id_nhanvien`) REFERENCES `nhan_vien` (`id`),
  ADD CONSTRAINT `phieu_bao_hanh_ibfk_2` FOREIGN KEY (`id_hoadon`) REFERENCES `hoa_don` (`id`);

--
-- Constraints for table `phieu_nhap_kho`
--
ALTER TABLE `phieu_nhap_kho`
  ADD CONSTRAINT `phieu_nhap_kho_ibfk_1` FOREIGN KEY (`id_nhanvien`) REFERENCES `nhan_vien` (`id`),
  ADD CONSTRAINT `phieu_nhap_kho_ibfk_2` FOREIGN KEY (`id_ncc`) REFERENCES `ncc` (`id`);

--
-- Constraints for table `san_pham`
--
ALTER TABLE `san_pham`
  ADD CONSTRAINT `san_pham_ibfk_1` FOREIGN KEY (`id_cate`) REFERENCES `phan_loai` (`id`);

--
-- Constraints for table `tai_khoan`
--
ALTER TABLE `tai_khoan`
  ADD CONSTRAINT `tai_khoan_ibfk_1` FOREIGN KEY (`id_nhanvien`) REFERENCES `nhan_vien` (`id`),
  ADD CONSTRAINT `tai_khoan_ibfk_2` FOREIGN KEY (`id_quyen`) REFERENCES `quyen` (`id`);
COMMIT;


