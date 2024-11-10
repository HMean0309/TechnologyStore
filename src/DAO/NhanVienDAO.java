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
        super.connectDB();
        String query = "SELECT * FROM NHAN_VIEN WHERE isDelete = 0;";
        ResultSet rs = super.executeQuery(query);
        super.closeDB();
        return rs;
    }
     
     public ResultSet getNhanVien(String id){
        super.connectDB();
        String query = "SELECT * FROM NHAN_VIEN WHERE isDelete = 0 AND id = ?;";
        Object[] params = {id};
        ResultSet rs = super.executeQuery(query, params);
        super.closeDB();
        return rs;
    }
     
     
     public ResultSet getCountNhanVien(String id){
        super.connectDB();
        String query = "SELECT COUNT(*) FROM NHAN_VIEN WHERE isDelete = 0 AND id = ?;";
        Object[] params = {id};
        ResultSet rs = super.executeQuery(query, params);
        super.closeDB();
        return rs;
    }
     // Thay doi Nhan Vien dua tren id
     public void updateNVById(NhanVienDTO nhanvien){
        super.connectDB();
        String query = "UPDATE NHAN_VIEN SET name = ?, phone = ? WHERE id = ?;";
        Object[] params = {nhanvien.getId(), nhanvien.getName(), nhanvien.getPhone()};
        super.executeNonQuery(query, params);
        super.closeDB();
    }
     
     // Them du lieu vao Nhan Vien
    public void addNVWithData(NhanVienDTO nhanvien) {
       super.connectDB();
        String query = "INSERT INTO NHAN_VIEN (id, name, phone) VALUES (?, ?, ?);";
        Object[] params = {nhanvien.getId(), nhanvien.getName(), nhanvien.getPhone()};
        super.executeNonQuery(query, params);
        super.closeDB();
    }

    // Xoa Nhan Vien bang id
    public void removeNVById(String id) {
        super.connectDB();
        String query = "UPDATE NHAN_VIEN SET isDelete = 1 WHERE id = ?;";
        Object[] params = {id};
        super.executeNonQuery(query, params);
        super.closeDB();
    }
     
}
