/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import DTO.PhieuNhapKhoDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PhieuNhapDAO extends ObjectDAO {
    public PhieuNhapDAO(){
        super();
    }
    
    public ResultSet getAllPhieuNhap(){
        String query = "SELECT * FROM PHIEU_NHAP_KHO";
        return super.executeQuery(query);
    }

    public ResultSet getAllPhieuNhapWithId(String id) {
        String query = "SELECT * FROM PHIEU_NHAP_KHO WHERE id = ?";
        Object[] params = {id};
        return super.executeQuery(query, params);
    }
    
    public ResultSet getCountPhieuNhapWithId(String id) {
        String query = "SELECT COUNT(*) FROM PHIEU_NHAP_KHO WHERE id = ?";
        Object[] params = {id};
        return super.executeQuery(query, params);
    }
    
    public void addPhieuNhap(PhieuNhapKhoDTO phieunhap) {
        String query = "INSERT INTO PHIEU_NHAP_KHO (id, ngaynhap, total, id_nhanvien, id_ncc) VALUES (?, ?, ?, ?, ?)";
        Object[] params = {phieunhap.getId(), phieunhap.getNgayNhap(), phieunhap.getTotal(), phieunhap.getidNhanVien(), phieunhap.getIdNCC()};
        super.executeNonQuery(query, params);
    }
}
