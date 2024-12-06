package DAO;

import DTO.HoaDonDTO;

import java.sql.ResultSet;

public class HoaDonDAO extends ObjectDAO {
    public HoaDonDAO() {
        super();
    }

    public ResultSet getAllHoaDon() {
        super.connectDB();
        String query = "SELECT * FROM hoadon_nv_kh where isDelete = 0;";
        return super.executeQuery(query);
    }

    public ResultSet getCountAllHoaDon() {
        super.connectDB();
        String query = "SELECT COUNT(*) FROM HOA_DON";
        ResultSet rs = super.executeQuery(query);
        return rs;
    }


    public void removeHoaDon(String id) {
        super.connectDB();
        String query = "UPDATE HOA_DON SET isDelete = 1 WHERE id = ?";
        Object[] params = { id };
        super.executeNonQuery(query, params);

    }

    public void addHoaDon(HoaDonDTO hoadon) {
        super.connectDB();
        String query = "INSERT INTO HOA_DON (id,ngaylap,order_amount,id_khachhang,id_nhanvien) "
                + "VALUES(?,?,?,?,?)";
        Object[] params = { hoadon.getId(), hoadon.getNgayLap(), hoadon.getOrderAmount(),
                hoadon.getIdKhachHang(), hoadon.getIdNhanVien()
        };
        super.executeNonQuery(query, params);

    }
}
