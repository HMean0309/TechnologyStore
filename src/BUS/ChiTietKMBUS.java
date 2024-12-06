package BUS;

import DAO.ChiTietKMDAO;
import DTO.ChiTietKhuyenMaiDTO;

import java.util.LinkedHashSet;

public class ChiTietKMBUS {

    private LinkedHashSet<ChiTietKhuyenMaiDTO> chiTietKMSet; // Lưu trữ dữ liệu khuyến mãi
    private ChiTietKMDAO chiTietKMDAO;

    public ChiTietKMBUS() {
        chiTietKMDAO = new ChiTietKMDAO();
        chiTietKMSet = chiTietKMDAO.getAllChiTietKhuyenMai(); // Lấy tất cả chi tiết khuyến mãi từ DAO
    }

    // Lấy danh sách tất cả chi tiết khuyến mãi
    public LinkedHashSet<ChiTietKhuyenMaiDTO> getAllChiTietKhuyenMai() {
        return chiTietKMSet;
    }

    // Lấy danh sách sản phẩm áp dụng khuyến mãi theo ID khuyến mãi
    public LinkedHashSet<ChiTietKhuyenMaiDTO> getChiTietByKhuyenMai(String idKM) {
        return chiTietKMDAO.getChiTietKhuyenMaiById(idKM);
    }

    // Thêm chi tiết khuyến mãi mới
    public boolean addChiTietKhuyenMai(ChiTietKhuyenMaiDTO chiTiet) {
        if (chiTietKMDAO.insertChiTietKhuyenMai(chiTiet)) {
            chiTietKMSet.add(chiTiet);
            return true;
        }
        return false;
    }

    // Xóa chi tiết khuyến mãi theo ID khuyến mãi và ID sản phẩm
    public boolean deleteChiTietKhuyenMai(String idKM, String idSP) {
        if (chiTietKMDAO.deleteChiTietKhuyenMai(idKM, idSP)) {
            chiTietKMSet.removeIf(ctkm -> 
                ctkm.getIdKM().equals(idKM) && ctkm.getIdSP().equals(idSP));
            return true;
        }
        return false;
    }

    // Xóa tất cả chi tiết khuyến mãi theo ID khuyến mãi
    public boolean deleteAllChiTietByKhuyenMai(String idKM) {
        if (chiTietKMDAO.deleteChiTietKhuyenMai(idKM)) {
            chiTietKMSet.removeIf(ctkm -> ctkm.getIdKM().equals(idKM));
            return true;
        }
        return false;
    }

    // Cập nhật danh sách sản phẩm áp dụng khuyến mãi
    public boolean updateSanPhamApDungKhuyenMai(String idKM, LinkedHashSet<ChiTietKhuyenMaiDTO> newChiTietSet) {
        if (chiTietKMDAO.updateSanPhamApDungKhuyenMai(idKM, newChiTietSet)) {
            chiTietKMSet.removeIf(ctkm -> ctkm.getIdKM().equals(idKM));
            chiTietKMSet.addAll(newChiTietSet);
            return true;
        }
        return false;
    }
    
}
