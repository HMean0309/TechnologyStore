
package DAO;

import DTO.SanPhamDTO;
import config.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

public class SanPhamPhieuNhapDAO {
    public SanPhamPhieuNhapDAO() {

    }
    public static LinkedHashSet<SanPhamDTO> getListSanPham() {
        LinkedHashSet<SanPhamDTO> list = new LinkedHashSet<>();
        ObjectDAO dao = new ObjectDAO();
        String query = "SELECT id, name, baohanh FROM san_pham WHERE trangthai = 1";
        try {
            dao.connectDB(); // Kết nối cơ sở dữ liệu
            ResultSet rs = dao.executeQuery(query);
            while (rs.next()) {
                SanPhamDTO sp = new SanPhamDTO();
                sp.setId(rs.getString("id"));
                sp.setName(rs.getString("name"));
                sp.setBaoHanh(rs.getInt("baohanh"));
                list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.closeDB();
        }
        return list;
    }
}
