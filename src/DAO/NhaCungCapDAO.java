/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhaCungCapDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author nghia
 */
public class NhaCungCapDAO extends ObjectDAO {
    public NhaCungCapDAO(){
        super();
    }
    public ResultSet getAllNhaCungCap(){
        String query = "SELECT * FROM ncc WHERE isDelete = 0 ;";
        return super.executeQuery(query);
    }
    public void addNhaCungCap(NhaCungCapDTO nhaCungCap) {
        String query = "INSERT INTO ncc (id, name, phone, address, ward, district, city) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Object[] params = {nhaCungCap.getId(),nhaCungCap.getName(),nhaCungCap.getPhone(),nhaCungCap.getAddress(),nhaCungCap.getWard(),nhaCungCap.getDistrict(),nhaCungCap.getCity()};
        executeNonQuery(query, params);
    }

    // Cập nhật thông tin nhà cung cấp
    public void updateNhaCungCap(NhaCungCapDTO nhaCungCap) {
        String query = "UPDATE ncc SET name = ?, phone = ?, address = ?, ward = ?, district = ?, city = ? WHERE id = ?";
        Object[] params = {
            nhaCungCap.getName(),
            nhaCungCap.getPhone(),
            nhaCungCap.getAddress(),
            nhaCungCap.getWard(),
            nhaCungCap.getDistrict(),
            nhaCungCap.getCity(),
            nhaCungCap.getId()
        };
        executeNonQuery(query, params);
    }

    // Xóa nhà cung cấp (cập nhật trường isDelete thành 1)
    public void deleteNhaCungCap(String id) {
        String query = "UPDATE ncc SET isDelete = 1 WHERE id = ?";
        Object[] params = {id};
        executeNonQuery(query, params);
    }
    
    public ResultSet searchNhaCungCap(String id, String name) {
        StringBuilder query = new StringBuilder("SELECT * FROM ncc WHERE isDelete = 0");

        if (id != null && !id.isEmpty()) {
            query.append(" AND id LIKE");
        }
        if (name != null && !name.isEmpty()) {
            query.append(" AND name LIKE ?");
        }

        try {
            PreparedStatement pstmt = conn.prepareStatement(query.toString());
            int index = 1;

            if (id != null && !id.isEmpty()) {
                pstmt.setString(index++, "%" + id + "%");
            }
            if (name != null && !name.isEmpty()) {
                pstmt.setString(index++, "%" + name + "%");
            }

            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
