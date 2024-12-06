package DAO;

import DTO.KhuyenMaiDTO;
import java.sql.*;
import java.util.LinkedHashSet;

public class KhuyenMaiDAO extends ObjectDAO {

    // Phương thức thêm một mã giảm giá
    public boolean insertKhuyenMai(KhuyenMaiDTO khuyenMai) {
        String sql = "INSERT INTO KHUYEN_MAI (id, name, discount_percent, min_order, ngaybatdau, ngayketthuc, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        Object[] params = {
            khuyenMai.getId(),
            khuyenMai.getName(),
            khuyenMai.getDiscountPercent(),
            khuyenMai.getMinOrder(),
            new java.sql.Date(khuyenMai.getStartDate().getTime()),
            new java.sql.Date(khuyenMai.getEndDate().getTime()),
            khuyenMai.getStatus()
        };
        return executeNonQuery(sql, params) > 0;
    }

    // Phương thức lấy tất cả mã giảm giá
    public LinkedHashSet<KhuyenMaiDTO> getAllKhuyenMai() throws SQLException {
        LinkedHashSet<KhuyenMaiDTO> list = new LinkedHashSet<>();
        super.connectDB();
        String sql = "SELECT * FROM KHUYEN_MAI";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapResultSetToKhuyenMai(rs));
            }
        }
        return list; 
    }

    // Phương thức lấy các mã giảm giá có hiệu lực từ ngày hiện tại trở đi
    public LinkedHashSet<KhuyenMaiDTO> getKhuyenMaiTuNgayHienTai() throws SQLException {
        LinkedHashSet<KhuyenMaiDTO> list = new LinkedHashSet<>();
        super.connectDB();
        String sql = "SELECT * FROM KHUYEN_MAI WHERE startDate <= CURDATE()";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapResultSetToKhuyenMai(rs));
            }
        }
        return list;
    }

    // Phương thức lấy mã giảm giá theo ID
    public KhuyenMaiDTO selectKhuyenMaiById(int id) throws SQLException {
        String sql = "SELECT * FROM KHUYEN_MAI WHERE id = ?";
        Object[] params = { id };
        try (ResultSet rs = executeQuery(sql, params)) {
            if (rs.next()) {
                return mapResultSetToKhuyenMai(rs);
            }
        }
        return null;
    }

    // Phương thức cập nhật giảm giá
    public boolean updateKhuyenMai(KhuyenMaiDTO khuyenMai) throws SQLException {
        String sql = "UPDATE KHUYEN_MAI SET name = ?, discount_percent = ?, min_order = ?, status = ? WHERE id = ?";
        Object[] params = {
            khuyenMai.getName(),
            khuyenMai.getDiscountPercent(),
            khuyenMai.getMinOrder(),
            khuyenMai.getStatus(),
            khuyenMai.getId()
        };
        return executeNonQuery(sql, params) > 0;
    }

    // Phương thức xóa mã giảm giá
    public boolean deleteKhuyenMai(int id) throws SQLException {
        String sql = "DELETE FROM KHUYEN_MAI WHERE id = ?";
        Object[] params = { id };
        return executeNonQuery(sql, params) > 0;
    }

    // Hàm lấy tổng số mã giảm giá
    public int getTotalKhuyenMai() throws SQLException {
        String sql = "SELECT COUNT(*) FROM KHUYEN_MAI";
        try (ResultSet rs = executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    // Hàm lấy mã giảm giá lớn nhất
    public String getMaxKhuyenMaiId() throws SQLException {
        super.connectDB();
        String sql = "SELECT MAX(id) FROM KHUYEN_MAI";
        try (ResultSet rs = executeQuery(sql)) {
            if (rs.next()) {
                return rs.getString(1);
            }
        }
        return null;
    }

    // Kiểm tra mã giảm giá có tồn tại
    public boolean isValidMakhuyenmai(int id) {
        String sql = "SELECT COUNT(*) FROM KHUYEN_MAI WHERE id = ?";
        Object[] params = { id };
        try (ResultSet rs = executeQuery(sql, params)) {
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Hàm hỗ trợ: Ánh xạ ResultSet sang KhuyenMaiDTO
    private KhuyenMaiDTO mapResultSetToKhuyenMai(ResultSet rs) throws SQLException {
        KhuyenMaiDTO khuyenMai = new KhuyenMaiDTO();
        khuyenMai.setId(rs.getString("id"));
        khuyenMai.setName(rs.getString("name"));
        khuyenMai.setDiscountPercent(rs.getInt("discount_percent"));
        khuyenMai.setMinOrder(rs.getInt("min_order"));
        khuyenMai.setStartDate(rs.getDate("startDate"));
        khuyenMai.setEndDate(rs.getDate("endDate"));
        khuyenMai.setStatus(rs.getString("status"));
        return khuyenMai;
    }
}
