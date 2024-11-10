package DAO;

import DTO.PhieuNhapKhoDTO;
import java.sql.ResultSet;


public class PhieuNhapDAO extends ObjectDAO {
    public PhieuNhapDAO(){
        super();
    }
    
    public ResultSet getAllPhieuNhap(){
        super.connectDB();
        String query = "SELECT * FROM PHIEU_NHAP_KHO";
        ResultSet rs = super.executeQuery(query);
        super.closeDB();
        return rs;
    }

    public ResultSet getAllPhieuNhapWithId(String id) {
        super.connectDB();
        String query = "SELECT * FROM PHIEU_NHAP_KHO WHERE id = ?";
        Object[] params = {id};
        ResultSet rs = super.executeQuery(query, params);
        super.closeDB();
        return rs;
    }
    
    public ResultSet getCountPhieuNhap() {
        super.connectDB();
        String query = "SELECT COUNT(*) FROM PHIEU_NHAP_KHO";
        ResultSet rs = super.executeQuery(query);
        super.closeDB();
        return rs;
    }
    
    public void addPhieuNhap(PhieuNhapKhoDTO phieunhap) {
        super.connectDB();
        String query = "INSERT INTO PHIEU_NHAP_KHO (id, ngaynhap, total, id_nhanvien, id_ncc) VALUES (?, ?, ?, ?, ?)";
        Object[] params = {phieunhap.getId(), phieunhap.getNgayNhap(), phieunhap.getTotal(), phieunhap.getidNhanVien(), phieunhap.getIdNCC()};
        super.executeNonQuery(query, params);
        super.closeDB();
    }
}
