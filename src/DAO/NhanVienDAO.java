package DAO;

import java.sql.Statement;

import DTO.NhanVienDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NhanVienDAO extends ObjectDAO {
    public NhanVienDAO(){
        super();
    }
    
     public ResultSet getAllNhanVien(){
        String query = "SELECT * FROM PRODUCT WHERE isDelete = 0 ;";
        return super.executeQuery(query);
    }
     
     public ResultSet getNhanVien(String id){
        String query = "SELECT * FROM PRODUCT WHERE isDelete = 0 AND id_cate = ? ;";
        Object[] params = {id};
        return super.executeQuery(query, params);
    }
     
     
     public ResultSet getCountNhanVien(String id){
        String query = "SELECT COUNT(*) FROM PRODUCT WHERE isDelete = 0 AND id_cate = ? ;";
        Object[] params = {id};
        ResultSet rs = super.executeQuery(query, params);
        
        return rs;
    }
     // Thay doi Nhan Vien dua tren id
     public void updateNVById(NhanVienDTO nhanvien){
        String query = "UPDATE PRODUCT SET name = ?, id_cate = ?, baohanh = ?, des = ?, img = ? WHERE id = ? ;";
        Object[] params = {nhanvien.getId(), nhanvien.getName(), nhanvien.getPhone()};
        super.executeNonQuery(query, params);
    }
     
     // Them du lieu vao Nhan Vien
    public void addNVWithData(NhanVienDTO nhanvien) {
        String query = "INSERT INTO CATEGORY (id,name)" + "VALUES(?,?)";
        Object[] params = { nhanvien.getId(), nhanvien.getName(), nhanvien.getPhone()};
        super.executeNonQuery(query, params);
    }

    // Xoa Nhan Vien bang id
    public void removeNVById(String id) {
        String query = "UPDATE CATEGORY SET isDelete = 1 WHERE id = ? ;";
        Object[] params = { id };
        super.executeNonQuery(query, params);
    }
     
}
