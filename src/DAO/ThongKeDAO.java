package DAO;

import DTO.ThongKeDTO;
import java.sql.ResultSet;
import java.util.LinkedHashSet;

public class ThongKeDAO extends ObjectDAO {

    public ThongKeDAO() {
        super();
    }

    // Thống kê theo ngày trong tháng/năm
    public LinkedHashSet<ThongKeDTO> thongKeTheoNgay(int thang, int nam) {
        super.connectDB();
        String query = "WITH RECURSIVE months AS (\n" +
                       "    SELECT DATE(CONCAT(?, '-', ?, '-01')) AS month\n" +
                       "    UNION ALL\n" +
                       "    SELECT month + INTERVAL 1 DAY\n" +
                       "    FROM months\n" +
                       "    WHERE month + INTERVAL 1 DAY < DATE_ADD(DATE(CONCAT(?, '-', ?, '-01')), INTERVAL 1 MONTH)\n" +
                       ") " +
                       "SELECT months.month AS Ngay, " +
                       "COALESCE(SUM(CASE WHEN id_pn IS NOT NULL THEN chitietsanpham.cost ELSE 0 END), 0) AS ChiPhi, " +
                       "COALESCE(SUM(CASE WHEN id_hoadon IS NOT NULL THEN chitietsanpham.price ELSE 0 END), 0) AS DoanhThu " +
                       "FROM months " +
                       "LEFT JOIN chitietsanpham " +
                       "ON (DATE(chitietsanpham.ngaynhap) = months.month OR DATE(chitietsanpham.ngaylap) = months.month) " +
                       "GROUP BY months.month " +
                       "ORDER BY months.month";

        LinkedHashSet<ThongKeDTO> results = new LinkedHashSet<>();

        try (ResultSet rs = executeQuery(query, new Object[]{nam, thang, nam, thang})) {
            while (rs.next()) {
                String ngay = rs.getString("Ngay");
                int chiPhi = rs.getInt("ChiPhi");
                int doanhThu = rs.getInt("DoanhThu");

                results.add(new ThongKeDTO(
                    ngay,
                    chiPhi,
                    doanhThu
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }


    // Thống kê theo tháng trong năm
    public LinkedHashSet<ThongKeDTO> thongKeTheoThang(int nam) {
        super.connectDB();
        String query = "WITH RECURSIVE months AS (\n" +
                       "    SELECT 1 AS month\n" +
                       "    UNION ALL\n" +
                       "    SELECT month + 1\n" +
                       "    FROM months\n" +
                       "    WHERE month + 1 <= 12\n" +
                       ")\n" +
                       "SELECT months.month AS thang, " +
                       "COALESCE(SUM(CASE WHEN id_pn IS NOT NULL THEN chitietsanpham.cost ELSE 0 END), 0) AS chiPhi, " +
                       "COALESCE(SUM(CASE WHEN id_hoadon IS NOT NULL THEN chitietsanpham.price ELSE 0 END), 0) AS doanhThu " +
                       "FROM months " +
                       "LEFT JOIN chitietsanpham " +
                       "ON (YEAR(chitietsanpham.ngaynhap) = ? AND MONTH(chitietsanpham.ngaynhap) = months.month) " +
                       "OR (YEAR(chitietsanpham.ngaylap) = ? AND MONTH(chitietsanpham.ngaylap) = months.month) " +
                       "GROUP BY months.month " +
                       "ORDER BY months.month ASC";

        LinkedHashSet<ThongKeDTO> results = new LinkedHashSet<>();

        try (ResultSet rs = executeQuery(query, new Object[]{nam, nam})) {
            while (rs.next()) {
                results.add(new ThongKeDTO(
                    String.format("Tháng %02d", rs.getInt("thang")),
                    rs.getInt("chiPhi"),
                    rs.getInt("doanhThu")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }


    // Thống kê theo năm
    public LinkedHashSet<ThongKeDTO> thongKeTheoNam(int year_start, int year_end) {
        super.connectDB();
        String query = "WITH RECURSIVE years AS (\n" +
                       "    SELECT ? AS year\n" +
                       "    UNION ALL\n" +
                       "    SELECT year + 1\n" +
                       "    FROM years\n" +
                       "    WHERE year + 1 <= ?\n" +
                       ")\n" +
                       "SELECT years.year AS nam, " +
                       "COALESCE(SUM(CASE WHEN id_pn IS NOT NULL THEN chitietsanpham.cost ELSE 0 END), 0) AS chiPhi, " +
                       "COALESCE(SUM(CASE WHEN id_hoadon IS NOT NULL THEN chitietsanpham.price ELSE 0 END), 0) AS doanhThu " +
                       "FROM years " +
                       "LEFT JOIN chitietsanpham " +
                       "ON (YEAR(chitietsanpham.ngaynhap) = years.year OR YEAR(chitietsanpham.ngaylap) = years.year) " +
                       "GROUP BY years.year " +
                       "ORDER BY years.year ASC";

        LinkedHashSet<ThongKeDTO> results = new LinkedHashSet<>();

        try (ResultSet rs = executeQuery(query, new Object[]{year_start, year_end})) {
            while (rs.next()) {
                results.add(new ThongKeDTO(
                    String.valueOf(rs.getInt("nam")),
                    rs.getInt("chiPhi"), 
                    rs.getInt("doanhThu")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }
    
    public LinkedHashSet<ThongKeDTO> thongKe7NgayGanNhat() {
        super.connectDB();
        String query = "WITH RECURSIVE last7days AS (\n" +
                       "    SELECT CURDATE() AS day\n" +
                       "    UNION ALL\n" +
                       "    SELECT day - INTERVAL 1 DAY\n" +
                       "    FROM last7days\n" +
                       "    WHERE day - INTERVAL 1 DAY >= CURDATE() - INTERVAL 6 DAY\n" +
                       ")\n" +
                       "SELECT last7days.day AS ngay, \n" +
                       "       COALESCE(SUM(CASE WHEN id_pn IS NOT NULL THEN chitietsanpham.cost ELSE 0 END), 0) AS chiPhi,\n" +
                       "       COALESCE(SUM(CASE WHEN id_hoadon IS NOT NULL THEN chitietsanpham.price ELSE 0 END), 0) AS doanhThu\n" +
                       "FROM last7days\n" +
                       "LEFT JOIN chitietsanpham \n" +
                       "ON DATE(chitietsanpham.ngaynhap) = last7days.day \n" +
                       "   OR DATE(chitietsanpham.ngaylap) = last7days.day\n" +
                       "GROUP BY last7days.day\n" +
                       "ORDER BY last7days.day ASC";

        LinkedHashSet<ThongKeDTO> results = new LinkedHashSet<>();

        try (ResultSet rs = executeQuery(query)) {
            while (rs.next()) {
                results.add(new ThongKeDTO(
                    rs.getString("ngay"),
                    rs.getInt("chiPhi"),
                    rs.getInt("doanhThu")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }
}
