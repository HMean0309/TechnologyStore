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
        String query = "SELECT DAY(hd.ngaylap) AS ngay, MONTH(hd.ngaylap) AS thang, YEAR(hd.ngaylap) AS nam, SUM(hd.order_amount - hd.discount_amount) AS doanhThu, " +
                       "SUM(ctnk.cost) AS chiPhi " +
                       "FROM HOA_DON hd " +
                       "JOIN CT_HOA_DON cthd ON hd.id = cthd.id_hoadon " +
                       "JOIN CT_SAN_PHAM ctsp ON cthd.seri = ctsp.seri " +
                       "JOIN CT_NHAP_KHO ctnk ON ctsp.seri = ctnk.seri " +
                       "WHERE MONTH(hd.ngaylap) = ? AND YEAR(hd.ngaylap) = ? " +
                       "GROUP BY DAY(hd.ngaylap)";
        LinkedHashSet<ThongKeDTO> results = new LinkedHashSet<>();
        
        try (ResultSet rs = executeQuery(query, new Object[]{thang, nam})) {
            while (rs.next()) {
                results.add(new ThongKeDTO(
                    String.format("%04d-%02d-%02d", rs.getInt("nam"), rs.getInt("thang"), rs.getInt("ngay")),
                    rs.getInt("doanhThu"),
                    rs.getInt("chiPhi")
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
        String query = "SELECT MONTH(hd.ngaylap) AS thang, YEAR(hd.ngaylap) AS nam, SUM(hd.order_amount - hd.discount_amount) AS doanhThu, " +
                       "SUM(ctnk.cost) AS chiPhi " +
                       "FROM HOA_DON hd " +
                       "JOIN CT_HOA_DON cthd ON hd.id = cthd.id_hoadon " +
                       "JOIN CT_SAN_PHAM ctsp ON cthd.seri = ctsp.seri " +
                       "JOIN CT_NHAP_KHO ctnk ON ctsp.seri = ctnk.seri " +
                       "WHERE YEAR(hd.ngaylap) = ? " +
                       "GROUP BY MONTH(hd.ngaylap)";
        LinkedHashSet<ThongKeDTO> results = new LinkedHashSet<>();
        
        try (ResultSet rs = executeQuery(query, new Object[]{nam})) {
            while (rs.next()) {
                results.add(new ThongKeDTO(
                    String.format("Tháng %02d", rs.getInt("thang")),
                    rs.getInt("doanhThu"),
                    rs.getInt("chiPhi")
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
        String query = "SELECT YEAR(hd.ngaylap) AS nam, " +
                       "SUM(hd.order_amount - hd.discount_amount) AS doanhThu, " +
                       "SUM(ctnk.cost) AS chiPhi " +
                       "FROM HOA_DON hd " +
                       "JOIN CT_HOA_DON cthd ON hd.id = cthd.id_hoadon " +
                       "JOIN CT_SAN_PHAM ctsp ON cthd.seri = ctsp.seri " +
                       "JOIN CT_NHAP_KHO ctnk ON ctsp.seri = ctnk.seri " +
                       "WHERE YEAR(hd.ngaylap) BETWEEN ? AND ? " +
                       "GROUP BY YEAR(hd.ngaylap) " +
                       "ORDER BY YEAR(hd.ngaylap) ASC";
        LinkedHashSet<ThongKeDTO> results = new LinkedHashSet<>();
        
        try (ResultSet rs = executeQuery(query, new Object[]{year_start, year_end})) {
            while (rs.next()) {
                results.add(new ThongKeDTO(
                    String.valueOf(rs.getInt("nam")),
                    rs.getInt("doanhThu"),
                    rs.getInt("chiPhi")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return results;
    }


    // Thống kê sản phẩm bán chạy theo tháng/năm
    public LinkedHashSet<ThongKeDTO> thongKeSanPhamBanChay(int thang, int nam) {
        super.connectDB();
        String query = "SELECT sp.name AS tenSanPham, COUNT(cthd.seri) AS soLuongBan " +
                       "FROM HOA_DON hd " +
                       "JOIN CT_HOA_DON cthd ON hd.id = cthd.id_hoadon " +
                       "JOIN CT_SAN_PHAM ctsp ON cthd.seri = ctsp.seri " +
                       "JOIN SAN_PHAM sp ON ctsp.id_sp = sp.id " +
                       "WHERE MONTH(hd.ngaylap) = ? AND YEAR(hd.ngaylap) = ? " +
                       "GROUP BY sp.name " +
                       "ORDER BY soLuongBan DESC" +
                       "LIMIT 10";
        LinkedHashSet<ThongKeDTO> results = new LinkedHashSet<>();
        
        try (ResultSet rs = executeQuery(query, new Object[]{thang, nam})) {
            while (rs.next()) {
                ThongKeDTO thongKe = new ThongKeDTO(
                    rs.getString("tenSanPham"),
                    0,
                    rs.getInt("soLuongBan")
                );
                results.add(thongKe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return results;
    }
}
