package DAO;

import java.sql.ResultSet;
import java.time.LocalDateTime;

import DTO.PhieuBaoHanhDTO;

public class PhieuBaoHanhDAO extends ObjectDAO {
    public PhieuBaoHanhDAO() {
        super();
    }

    public ResultSet getAllPhieuBaoHanh() {
        super.connectDB();
        String query = "SELECT * FROM PHIEU_BAO_HANH";
        ResultSet rs = super.executeQuery(query);
        
        return rs;
    }

    public ResultSet getCountPhieuBaoHanh() {
        super.connectDB();
        String query = "SELECT COUNT(*) FROM PHIEU_BAO_HANH";
        ResultSet rs = super.executeQuery(query);
        
        return rs;
    }

    public void updateNgayTraHang(String id, LocalDateTime ngayTraHang){
        super.connectDB();
        String query = "UPDATE PHIEU_BAO_HANH SET ngaytrahang = ? WHERE id = ?";
        Object[] params = {ngayTraHang, id};
        super.executeNonQuery(query, params);
        
    }

    public void addPhieuBaoHanh(PhieuBaoHanhDTO baohanh) {
        super.connectDB();
        String query = "INSERT INTO PHIEU_BAO_HANH (id, ngaylap, id_nhanvien, id_hoadon, ngaytrahang) "
                + "VALUES(?,?,?,?,?)";
        Object[] params = {baohanh.getId(), baohanh.getNgayLap(), 
                baohanh.getIdNhanVien(), baohanh.getIdHoaDon(), baohanh.getNgayTraHang()};
        super.executeNonQuery(query, params);
        
    }
}
