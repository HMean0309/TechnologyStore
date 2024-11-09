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
        String query = "SELECT * FROM PHIEU_NHAP_KHO WHERE isDelete = 0 ;";
        return super.executeQuery(query);
    }

    public ResultSet selectPhieuNhapById(String id) {
        String query = "SELECT * FROM PHIEU_NHAP_KHO WHERE id = ? AND isDelete = 0;";
        Object[] params = {id};
        return super.executeQuery(query, params);
    }
    
    public void addPhieuNhap(PhieuNhapKhoDTO phieunhap) {
        String query = "INSERT INTO PHIEU_NHAP_KHO (id, ngaynhap, total, id_nhanvien, id_ncc) VALUES (?, ?, ?, ?, ?)";
        Object[] params = {phieunhap.getId(), phieunhap.getNgayNhap(), phieunhap.getTotal(), phieunhap.getidNhanVien(), phieunhap.getIdNCC()};
        super.executeNonQuery(query, params);
    }
    
    public List<PhieuNhapKhoDTO> searchPhieuNhap(String id_pn, String tenNcc, String tenNhanVien,LocalDateTime fromDate, LocalDateTime toDate,Integer fromTotal, Integer toTotal) {
        List<PhieuNhapKhoDTO> result = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT pn.id_pn, ncc.id_ncc, nv.id_nhanvien, pn.ngay_nhap, pn.total " +
                                                "FROM phieu_nhap pn " +
                                                "JOIN ncc ON pn.id_ncc = ncc.id " +
                                                "JOIN nhan_vien nv ON pn.id_nhanvien = nv.id " +
                                                "WHERE 1=1");
        List<Object> params = new ArrayList<>();

        checkGanGiong(query, params, "pn.id_pn LIKE ?", id_pn);
        checkGanGiong(query, params, "ncc.name LIKE ?", tenNcc);
        checkGanGiong(query, params, "nv.name LIKE ?", tenNhanVien);
        checkNgay(query, params, "pn.ngaynhap >= ?", fromDate);
        checkNgay(query, params, "pn.ngaynhap <= ?", toDate);
        checkGiongNhau(query, params, "pn.total >= ?", fromTotal);
        checkGiongNhau(query, params, "pn.total <= ?", toTotal);

        try {
            ResultSet rs = executeQuery(query.toString(), params.toArray());
            while (rs.next()) {
                PhieuNhapKhoDTO phieuNhap = new PhieuNhapKhoDTO();
                phieuNhap.setId(rs.getString("id"));
                phieuNhap.setIdNCC(rs.getString("id_ncc"));
                phieuNhap.setidNhanVien(rs.getString("id_nhanvien"));
                phieuNhap.setNgayNhap(rs.getTimestamp("ngaynhap").toLocalDateTime());
                phieuNhap.setTotal(rs.getInt("total"));
                result.add(phieuNhap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    // Hàm thêm điều kiện cho các trường hợp kiểm tra bằng
    private void checkGiongNhau(StringBuilder query, List<Object> params, String condition, Object value) {
        if (value != null) {
            query.append(" AND ").append(condition);
            params.add(value);
        }
    }

    // Hàm thêm điều kiện cho các trường hợp kiểm tra giống nhau
    private void checkGanGiong(StringBuilder query, List<Object> params, String condition, String value) {
        if (value != null && !value.trim().isEmpty()) {
            query.append(" AND ").append(condition);
            params.add("%" + value + "%");
        }
    }

    // Hàm thêm điều kiện cho các trường hợp kiểm tra ngày
    private void checkNgay(StringBuilder query, List<Object> params, String condition, LocalDateTime date) {
        if (date != null) {
            query.append(" AND ").append(condition);
            params.add(Timestamp.valueOf(date));
        }
    }

}
