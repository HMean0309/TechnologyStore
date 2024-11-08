package DAO;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.LocalDate;

import DTO.HoaDonDTO;

public class HoaDonDAO extends ObjectDAO {
    public HoaDonDAO() {
        super();
    }

    public ResultSet getAllHoaDon() {
        String query = "SELECT * FROM HOA_DON";
        return super.executeQuery(query);
    }

    public ResultSet getAllHoaDonWithStatus(String status) {
        String query = "SELECT * FROM HOA_DON WHERE status = ? ";
        Object[] params = { status };
        return super.executeQuery(query, params);
    }

    public ResultSet getCountHoaDonWithStatus(String status) {
        String query = "SELECT COUNT(*) FROM HOA_DON WHERE status = ?";
        Object[] params = { status };
        return super.executeQuery(query, params);
    }

    public void updateStatusHoaDon(String id, String status) {
        String query = "UPDATE HOA_DON SET status = ? WHERE id = ?";
        Object[] params = { status, id };
        super.executeNonQuery(query, params);
    }

    public void addHoaDon(HoaDonDTO hoadon) {
        String query = "INSERT INTO HOA_DON (id,ngaylap,order_amount,discount_amount,status,km,pttt,id_khachhang,id_nhanvien,ghichu) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        Object[] params = { hoadon.getId(), hoadon.getNgayLap(), hoadon.getOrderAmount(), hoadon.getDiscountAmount(),
                hoadon.getStatus(), hoadon.getKhuyenMai(), hoadon.getPhuongThucTT(), hoadon.getKhachHang(),
                hoadon.getNhanVien(), hoadon.getGhichu() };
        super.executeNonQuery(query, params);
    }
}
