package DAO;

import DTO.ChiTietKhuyenMaiDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

public class ChiTietKMDAO extends ObjectDAO {

    // Thêm chi tiết khuyến mãi mới
    public boolean insertChiTietKhuyenMai(ChiTietKhuyenMaiDTO chiTiet) {
        String sql = "INSERT INTO CT_KHUYEN_MAI (id_km, id_sp) VALUES (?, ?)";
        Object[] params = {chiTiet.getIdKM(), chiTiet.getIdSP()};
        return executeNonQuery(sql, params) > 0;
    }

    // Xóa chi tiết khuyến mãi theo ID khuyến mãi và sản phẩm
    public boolean deleteChiTietKhuyenMai(String idKM, String idSP) {
        String sql = "DELETE FROM CT_KHUYEN_MAI WHERE id_km = ? AND id_sp = ?";
        Object[] params = {idKM, idSP};
        return executeNonQuery(sql, params) > 0;
    }

    // Xóa tất cả chi tiết khuyến mãi theo ID khuyến mãi
    public boolean deleteChiTietKhuyenMai(String idKM) {
        String sql = "DELETE FROM CT_KHUYEN_MAI WHERE id_km = ?";
        Object[] params = {idKM};
        return executeNonQuery(sql, params) > 0;
    }

    // Lấy tất cả chi tiết khuyến mãi
    public LinkedHashSet<ChiTietKhuyenMaiDTO> getAllChiTietKhuyenMai() {
        String sql = "SELECT id_km, id_sp FROM CT_KHUYEN_MAI";
        ResultSet rs = executeQuery(sql);
        LinkedHashSet<ChiTietKhuyenMaiDTO> result = new LinkedHashSet<>();

        try {
            while (rs != null && rs.next()) {
                ChiTietKhuyenMaiDTO chiTiet = new ChiTietKhuyenMaiDTO(
                        rs.getString("id_km"),
                        rs.getString("id_sp")
                );
                result.add(chiTiet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
        }

        return result;
    }

    // Lấy danh sách sản phẩm áp dụng khuyến mãi theo ID khuyến mãi
    public  LinkedHashSet<ChiTietKhuyenMaiDTO> getChiTietKhuyenMaiById(String idKM) {
        super.connectDB();
        String sql = "SELECT * FROM CT_KHUYEN_MAI WHERE id_km = ?";
        Object[] params = {idKM};
        ResultSet rs = executeQuery(sql, params);
        LinkedHashSet<ChiTietKhuyenMaiDTO> result = new LinkedHashSet<>();

        try {
            while (rs != null && rs.next()) {
                ChiTietKhuyenMaiDTO chiTiet = new ChiTietKhuyenMaiDTO(
                        rs.getString("id_km"),
                        rs.getString("id_sp")
                );
                result.add(chiTiet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
        }

        return result;
    }

    // Cập nhật danh sách sản phẩm áp dụng khuyến mãi
    public boolean updateSanPhamApDungKhuyenMai(String idKM, LinkedHashSet<ChiTietKhuyenMaiDTO> chiTietSet) {
        String sqlDelete = "DELETE FROM CT_KHUYEN_MAI WHERE id_km = ?";
        String sqlInsert = "INSERT INTO CT_KHUYEN_MAI (id_km, id_sp) VALUES (?, ?)";
        Object[] paramsDelete = {idKM};

        try {
            super.connectDB();

            executeNonQuery(sqlDelete, paramsDelete);

            for (ChiTietKhuyenMaiDTO chiTiet : chiTietSet) {
                Object[] paramsInsert = {chiTiet.getIdKM(), chiTiet.getIdSP()};
                executeNonQuery(sqlInsert, paramsInsert);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            super.closeDB();
        }
    }

    // Đóng ResultSet an toàn
    private void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
