package BUS;

import DAO.SanPhamPhieuNhapDAO;
import DTO.SanPhamDTO;
import java.util.LinkedHashSet;

public class SanPhamPhieuNhapBUS {
    public SanPhamPhieuNhapBUS() {
    }
    public LinkedHashSet<SanPhamDTO> getAllSanPham() {
        return SanPhamPhieuNhapDAO.getListSanPham();
    }
}
