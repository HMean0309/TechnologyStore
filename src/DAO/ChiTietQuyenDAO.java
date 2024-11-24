package DAO;

import DTO.ChiTietQuyenDTO;

import java.sql.ResultSet;

public class ChiTietQuyenDAO extends ObjectDAO {
    public ChiTietQuyenDAO() {
        super();
    }

    public ResultSet getAllChiTietQuyen() {
        super.connectDB();
        String query = "SELECT * FROM CT_QUYEN;";
        ResultSet rs = super.executeQuery(query);

        return rs;
    }

    public ResultSet getCountChiTietQuyen() {
        super.connectDB();
        String query = "SELECT COUNT(*) FROM CT_QUYEN";
        ResultSet rs = super.executeQuery(query);

        return rs;
    }

    // Thay doi Chi Tiet Quyen dua tren id
    public void updateChiTietQuyenById(ChiTietQuyenDTO ctQuyen) {
        super.connectDB();
        String query = "UPDATE CT_QUYEN SET permission = ? WHERE id_quyen = ? AND id_chuc_nang = ?;";
        Object[] params = {
                ctQuyen.getPermission(),
                ctQuyen.getIdQuyen(),
                ctQuyen.getIdChucNang()
        };
        super.executeNonQuery(query, params);
    }

    // Them du lieu vao chi tiet quyen
    public void addChiTietQuyenWithData(ChiTietQuyenDTO ctQuyen) {
        super.connectDB();
        String query = "INSERT INTO CT_QUYEN (id_quyen, id_chuc_nang, permission) VALUES (?, ?, ?);";
        Object[] params = {
                ctQuyen.getIdQuyen(),
                ctQuyen.getIdChucNang(),
                ctQuyen.getPermission()
        };
        super.executeNonQuery(query, params);
    }

    public void removeChiTietQuyen(String idQuyen) {
        super.connectDB();
        String query = "DELETE FROM CT_QUYEN WHERE id_quyen = ? ;";
        Object[] params = { idQuyen };
        super.executeNonQuery(query, params);
    }
}
