package DAO;

import java.sql.ResultSet;

import DTO.HoaDonDTO;

public class HoaDonDAO extends ObjectDAO {
    public HoaDonDAO() {
        super();
    }

    public ResultSet getAllHoaDon() {
        super.connectDB();
        String query = "SELECT * FROM HOA_DON";
        ResultSet rs = super.executeQuery(query);
        super.closeDB();
        return rs;
    }

    public ResultSet getAllHoaDonWithStatus(String status) {
        super.connectDB();
        String query = "SELECT * FROM HOA_DON WHERE status = ? ";
        Object[] params = { status };
        ResultSet rs = super.executeQuery(query, params);
        super.closeDB();
        return rs;
    }

    public ResultSet getCountHoaDonWithStatus(String status) {
        super.connectDB();
        String query = "SELECT COUNT(*) FROM HOA_DON WHERE status = ?";
        Object[] params = { status };
        ResultSet rs = super.executeQuery(query, params);
        super.closeDB();
        return rs;
    }

    public void updateStatusHoaDon(String id, String status) {
        super.connectDB();
        String query = "UPDATE HOA_DON SET status = ? WHERE id = ?";
        Object[] params = { status, id };
        super.executeNonQuery(query, params);
        super.closeDB();
    }

    public void addHoaDon(HoaDonDTO hoadon) {
        super.connectDB();
        String query = "INSERT INTO HOA_DON (id,ngaylap,order_amount,discount_amount,status,km,pttt,id_khachhang,id_nhanvien,ghichu) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        Object[] params = { hoadon.getId(), hoadon.getNgayLap(), hoadon.getOrderAmount(), hoadon.getDiscountAmount(),
                hoadon.getStatus(), hoadon.getKhuyenMai(), hoadon.getPhuongThucTT(), hoadon.getKhachHang(),
                hoadon.getNhanVien(), hoadon.getGhichu() };
        super.executeNonQuery(query, params);
        super.closeDB();
    }
}
