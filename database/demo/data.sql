-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 29, 2024 at 03:17 AM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: cuahangdientu_1
--

--
-- Dumping data for table phan_loai
--

INSERT INTO phan_loai (id, name, isDelete) VALUES
('CATE001', 'Điện Thoại', 0),
('CATE002', 'Laptop', 0),
('CATE003', 'Tủ Lạnh', 0);

--
-- Dumping data for table chuc_nang
--

INSERT INTO chuc_nang (id, name) VALUES
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
('SER011', 'Quản lý nhân viên');

--
-- Dumping data for table quyen
--

INSERT INTO quyen (id, name, isDelete) VALUES
('POS001', 'Admin', 0),
('POS002', 'Quản Lý Cửa Hàng', 0);

--
-- Dumping data for table ct_quyen
--

INSERT INTO ct_quyen (id_quyen, id_chucnang, permission) VALUES
('POS001', 'SER001', 'create'),
('POS001', 'SER001', 'view'),
('POS001', 'SER001', 'update'),
('POS001', 'SER001', 'delete'),
('POS002', 'SER001', 'create'),
('POS002', 'SER001', 'view'),
('POS002', 'SER001', 'update'),
('POS002', 'SER001', 'delete'),
('POS001', 'SER002', 'create'),
('POS001', 'SER002', 'view'),
('POS001', 'SER002', 'update'),
('POS001', 'SER002', 'delete'),
('POS001', 'SER003', 'create'),
('POS001', 'SER003', 'view'),
('POS001', 'SER003', 'update'),
('POS001', 'SER003', 'delete'),
('POS002', 'SER003', 'create'),
('POS002', 'SER003', 'view'),
('POS002', 'SER003', 'update'),
('POS002', 'SER003', 'delete'),
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
('POS002', 'SER011', 'create'),
('POS002', 'SER011', 'view'),
('POS002', 'SER011', 'update');

--
-- Dumping data for table khach_hang
--

INSERT INTO khach_hang (id, name, phone, address, district, ward, city, isDelete) VALUES
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

--
-- Dumping data for table ncc
--

INSERT INTO ncc (id, name, phone, isDelete, address, ward, district, city) VALUES
('NCC0001', 'Apple', '0907530962', 0, '789 Dương Bá Trạc', 'Phường Bến Thành', 'Quận 1', 'Thành phố Hồ Chí Minh');

--
-- Dumping data for table nhan_vien
--

INSERT INTO nhan_vien (id, name, phone, gender, birth, email, isDelete) VALUES
('STAFF00001', 'Huỳnh Ngọc Hải Dương', '0868707529', 1, '2004-09-22', 'duonghuynh2209@gmail.com', 0),
('STAFF00002', 'Hai Duong', '0868707528', 1, '2004-09-22', 'duong229@gmail.com', 0),
('STAFF00003', 'Duong Donate', '0868707528', 1, '2004-09-22', 'duong2209@gmail.com', 1),
('STAFF00004', 'Anh Thu', '0987755224', 0, '2004-07-31', 'at4455@gmail.com', 0),
('STAFF00005', 'Gia Lào', '0125578466', 1, '2004-08-14', 'gialao784@gmail.com', 0),
('STAFF00006', 'Nhu Quynh', '0147855554', 0, '2005-12-12', 'nhuquynh788@gmail.com', 0),
('STAFF00007', 'Vo Quang Phu', '0122455445', 1, '2004-08-14', 'phuvq2004@gmail.com', 0);

--
-- Dumping data for table tai_khoan
--

INSERT INTO tai_khoan (username, password, id_nhanvien, id_quyen, isDelete) VALUES
('duongdonate', 'HD12345678', 'STAFF00001', 'POS001', 0),
('gialong45', 'long12345678', 'STAFF00005', 'POS002', 0),
('jorgani', 'AT12345678', 'STAFF00004', 'POS001', 0),
('nhuquynh45', 'NQ12345678', 'STAFF00006', 'POS001', 0);


--
-- Dumping data for table san_pham
--

INSERT INTO san_pham (id, name, isDelete, id_cate, baohanh, img) VALUES
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
('PRODUCT00012', 'Zenisu', 0, 'CATE001', 12, '742465911133_548708884581051_3241521075463914694_n.jpg');


--
-- Dumping data for table ct_san_pham
--

INSERT INTO ct_san_pham (seri, id_sp, color, price, isDelete) VALUES
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


--
-- Dumping data for table option_san_pham
--

INSERT INTO option_san_pham (id_sp, color, isDelete) VALUES
('PRODUCT00002', 'Cam', 0),
('PRODUCT00002', 'Hồng', 0),
('PRODUCT00003', 'Xanh Duong', 0),
('PRODUCT00004', 'Red', 0),
('PRODUCT00006', 'Red', 0),
('PRODUCT00007', 'Fuck', 0),
('PRODUCT00007', 'Pink', 0),
('PRODUCT00007', 'Red', 0),
('PRODUCT00012', 'Elec', 0),
('PRODUCT00012', 'Pink', 0);


--
-- Dumping data for table phieu_nhap_kho
--

INSERT INTO phieu_nhap_kho (id, ngaynhap, total, id_nhanvien, id_ncc, isDelete) VALUES
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

--
-- Dumping data for table ct_nhap_kho
--

INSERT INTO ct_nhap_kho (id_pn, seri, cost) VALUES
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


--
-- Dumping data for table hoa_don
--

INSERT INTO hoa_don (id, ngaylap, order_amount, discount_amount, km, pttt, id_khachhang, id_nhanvien, isDelete) VALUES
('HD29112024000001', '2024-11-29 04:44:49', 260000, 0, NULL, NULL, 'CUSTOMER00007', 'STAFF00001', 0);


--
-- Dumping data for table ct_hoa_don
--
INSERT INTO ct_hoa_don (id_hoadon, seri, don_gia) VALUES
('HD29112024000001', 'SERI0000300001', 50000),
('HD29112024000001', 'SERI0000300002', 50000),
('HD29112024000001', 'SERI0000700010', 80000),
('HD29112024000001', 'SERI0000700011', 80000);


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
