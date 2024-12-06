package DAO;

import DTO.PhieuNhapDTO;

import java.sql.ResultSet;


public class PhieuNhapDAO extends ObjectDAO {
    public PhieuNhapDAO() {
        super();
    }

    public ResultSet getAllPhieuNhap() {
        super.connectDB();
        String query = "SELECT * FROM phieunhap_nv_ncc where isDelete = 0;";
        ResultSet rs = super.executeQuery(query);

        return rs;
    }

    public ResultSet getCountPhieuNhap() {
        super.connectDB();
        String query = "SELECT COUNT(*) FROM PHIEU_NHAP_KHO";
        ResultSet rs = super.executeQuery(query);

        return rs;
    }

    public void addPhieuNhap(PhieuNhapDTO phieunhap) {
        super.connectDB();
        String query = "INSERT INTO PHIEU_NHAP_KHO (id, ngaynhap, total, id_nhanvien, id_ncc) VALUES (?, ?, ?, ?, ?)";
        Object[] params = { phieunhap.getId(), phieunhap.getNgayNhap(), phieunhap.getTotal(), phieunhap.getIdNhanVien(), phieunhap.getIdNCC() };
        super.executeNonQuery(query, params);

    }
    // in pdf
    public static PhieuNhapDAO getInstance() {
        return new PhieuNhapDAO();
    }
    public PhieuNhapDTO selectById(String id) {
        super.connectDB();
        String query = "SELECT * FROM PHIEU_NHAP_KHO WHERE id = ? AND isDelete = 0;";
        Object[] params = { id };
        ResultSet rs = super.executeQuery(query, params);
        PhieuNhapDTO phieuNhap = null;

        try {
            if (rs.next()) {
                phieuNhap = new PhieuNhapDTO(
                    rs.getString("id"),
                    rs.getTimestamp("ngaynhap").toLocalDateTime(),
                    rs.getInt("total"),
                    rs.getString("id_nhanvien"),
                    rs.getString("id_ncc"),
                    null, // nameNhanVien sẽ lấy từ nơi khác nếu cần
                    null, // nameNCC sẽ lấy từ nơi khác nếu cần
                    rs.getBoolean("isDelete")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.closeDB();
        }

        return phieuNhap;
    }
}
