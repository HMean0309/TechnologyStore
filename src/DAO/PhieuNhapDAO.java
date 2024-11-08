/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietNhapKhoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JOptionPane;

public class PhieuNhapDAO extends ObjectDAO {
    public PhieuNhapDAO(){
        super();
    }
    
    public ResultSet getAllPhieuNhap(){
        String query = "SELECT * FROM PHIEU_NHAP_KHO WHERE isDelete = 0 ;";
        return super.executeQuery(query);
    }

    public ResultSet selectPhieuNhapById(String id) {
        String query = "SELECT * FROM PHIEU_NHAP_KHO WHERE id = ? AND isDelete = 0;";
        Object[] params = {id};
        return super.executeQuery(query, params);
    }
    
    public void addPhieuNhap(String id, LocalDateTime ngayNhap, int total, String id_nhanvien, String idNCC) {
        String query = "INSERT INTO PHIEU_NHAP_KHO (id, ngaynhap, total, id_nhanvien, id_ncc) VALUES (?, ?, ?, ?, ?)";
        Object[] params = {id, ngayNhap, total, id_nhanvien, idNCC};
        executeNonQuery(query, params);
    }
    
    public void editPhieuNhap(String id, LocalDateTime ngayNhap, int total, String id_nhanvien, String idNCC) {
        String query = "UPDATE PHIEU_NHAP_KHO (id, ngaynhap, total, id_nhanvien, id_ncc) VALUES (?, ?, ?, ?, ?)";
        Object[] params = {id, ngayNhap, total, id_nhanvien, idNCC};
        executeNonQuery(query, params);
    }
    
    public void deletePhieuNhap(String idPhieuNhap, List<ChiTietNhapKhoDTO> listPn) {
        if (!canDeletePhieuNhap(listPn)) {
            return;
        }

        String query = "DELETE FROM PHIEU_NHAP_KHO WHERE id = ?";
        Object[] params = {idPhieuNhap};
        executeNonQuery(query, params);
        JOptionPane.showMessageDialog(null, "Phiếu nhập đã được xóa thành công.");
    }


    
    public boolean canDeletePhieuNhap(List<ChiTietNhapKhoDTO> listPn) {
        try {
            for (ChiTietNhapKhoDTO chiTiet : listPn) {
                String seriSanPham = chiTiet.getSeri();

                String query = "SELECT COUNT(*) FROM ChiTietHoaDon WHERE seriSanPham = ?";
                Object[] params = {seriSanPham};

                try (ResultSet rs = executeQuery(query, params)) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        JOptionPane.showMessageDialog(null, "Phiếu này có sản phẩm đã bán, không thể hủy!");
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
