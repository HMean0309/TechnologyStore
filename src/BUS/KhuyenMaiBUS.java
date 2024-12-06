package BUS;

import DAO.KhuyenMaiDAO;
import DTO.KhuyenMaiDTO;

import java.sql.SQLException;
import java.util.LinkedHashSet;

public class KhuyenMaiBUS {
    private final KhuyenMaiDAO khuyenMaiDAO;

    // Constructor
    public KhuyenMaiBUS() {
        khuyenMaiDAO = new KhuyenMaiDAO();
    }

    // Lấy tất cả mã giảm giá
    public LinkedHashSet<KhuyenMaiDTO> getAllKhuyenMai() {
        try {
            return khuyenMaiDAO.getAllKhuyenMai();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new LinkedHashSet<>();
    }

    // Lấy mã giảm giá theo ID
    public KhuyenMaiDTO getKhuyenMaiById(int id) {
        try {
            return khuyenMaiDAO.selectKhuyenMaiById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Thêm mã giảm giá
    public boolean addKhuyenMai(KhuyenMaiDTO khuyenMai) {
        try {
            return khuyenMaiDAO.insertKhuyenMai(khuyenMai);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật mã giảm giá
    public boolean updateKhuyenMai(KhuyenMaiDTO khuyenMai) {
        try {
            return khuyenMaiDAO.updateKhuyenMai(khuyenMai);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa mã giảm giá
    public boolean deleteKhuyenMai(int id) {
        try {
            return khuyenMaiDAO.deleteKhuyenMai(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy danh sách mã giảm giá còn hiệu lực
    public LinkedHashSet<KhuyenMaiDTO> getKhuyenMaiConHieuLuc() {
        try {
            return khuyenMaiDAO.getKhuyenMaiTuNgayHienTai();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new LinkedHashSet<>();
    }

    // Lấy tổng số mã giảm giá
    public int getTongSoKhuyenMai() {
        try {
            return khuyenMaiDAO.getTotalKhuyenMai();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Lấy ID mã giảm giá lớn nhất
    public String getMaxKhuyenMaiId() {
        try {
            return khuyenMaiDAO.getMaxKhuyenMaiId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null ;
    }

    // Kiểm tra mã giảm giá có tồn tại
    public boolean isValidKhuyenMai(int id) {
        return khuyenMaiDAO.isValidMakhuyenmai(id);
    }

    // Lấy danh sách mã giảm giá theo tên (hỗ trợ tìm kiếm)
    public LinkedHashSet<KhuyenMaiDTO> searchKhuyenMaiByName(String keyword) {
        LinkedHashSet<KhuyenMaiDTO> allKhuyenMai = getAllKhuyenMai();
        LinkedHashSet<KhuyenMaiDTO> result = new LinkedHashSet<>();

        for (KhuyenMaiDTO khuyenMai : allKhuyenMai) {
            if (khuyenMai.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(khuyenMai);
            }
        }

        return result;
    }
//    public String generateMaKhuyenMai() throws SQLException {
//        int totalKhuyenMai = khuyenMaiDAO.getMaxKhuyenMaiId();
//        String maKhuyenMaiMoi;
//
//        if (totalKhuyenMai < 10) {
//            maKhuyenMaiMoi = "KM0" + (totalKhuyenMai + 1);
//        } else {
//            maKhuyenMaiMoi = "KM" + (totalKhuyenMai + 1);
//        }
//
//        return maKhuyenMaiMoi;
//    }
}
