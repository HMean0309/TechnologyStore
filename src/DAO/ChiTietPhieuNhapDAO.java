package DAO;

import java.sql.ResultSet;
import DTO.ChiTietNhapKhoDTO;

public class ChiTietPhieuNhapDAO extends ObjectDAO {
    public ChiTietPhieuNhapDAO(){
        super();
    }
    
    public void insertChiTietPhieuNhap(ChiTietNhapKhoDTO ctpn) {
        String query = "INSERT INTO ct_pro (id_pn, seri, cost) VALUES (?, ?, ?)";
        Object[] params = {ctpn.getIdPN(), ctpn.getSeri(), ctpn.getCost()};
        super.executeNonQuery(query, params);
    }
    
    public ResultSet getDetailedChiTietPhieuNhap(String id_pn) {
        String query = "SELECT pn.id, nv.ten AS tenNhanVien, ncc.ten AS tenNhaCungCap, " +
                       "pn.ngayNhap, ctsp.seri, cate.name AS loaiSanPham, prod.name AS tenSanPham, " +
                       "ctsp.color, ctpn.cost " +
                       "FROM ct_nhap_kho ctpn " +
                       "JOIN phieu_nhap_kho pn ON ctpn.id_pn = pn.id " +
                       "JOIN nhan_vien nv ON pn.id_nhanvien = nv.id_nhanvien " +
                       "JOIN ncc ON pn.id_ncc = ncc.id_ncc " +
                       "JOIN ct_pro ctsp ON ctpn.seri = ctsp.seri " +
                       "JOIN product prod ON ctsp.id_product = prod.id_product " +
                       "JOIN category cate ON prod.id_cate = cate.id_cate " +
                       "WHERE pn.id = ?";
        
        Object[] params = {id_pn};
        return super.executeQuery(query, params);
    }
}
