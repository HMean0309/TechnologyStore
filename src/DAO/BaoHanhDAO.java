package DAO;

import DTO.BaoHanhDTO;

import java.sql.ResultSet;

public class BaoHanhDAO extends ObjectDAO {
    public BaoHanhDAO() {
        super();
    }

    public ResultSet getAllPhieuBaoHanh() {
        super.connectDB();
        String query = "SELECT * FROM baohanh_nv_kh WHERE isDelete = 0;";
        ResultSet rs = super.executeQuery(query);

        return rs;
    }

    public ResultSet getCountPhieuBaoHanh() {
        super.connectDB();
        String query = "SELECT COUNT(*) FROM PHIEU_BAO_HANH";
        ResultSet rs = super.executeQuery(query);

        return rs;
    }

    public void addPhieuBaoHanh(BaoHanhDTO baohanh) {
        super.connectDB();
        String query = "INSERT INTO PHIEU_BAO_HANH (id, ngaybaohanh, id_nhanvien, id_hoadon, ngaytrahang) "
                + "VALUES(?,?,?,?,?)";
        Object[] params = { baohanh.getId(), baohanh.getNgayBaoHanh(),
                baohanh.getIdNhanVien(), baohanh.getIdHoaDon(), baohanh.getNgayTraHang() };
        super.executeNonQuery(query, params);

    }

    public void removeBaoHanh(String idBH) {
        super.connectDB();
        String query = "UPDATE phieu_bao_hanh SET isDelete = 1 WHERE id = ?";
        Object[] params = { idBH };
        super.executeNonQuery(query, params);
    }
}
