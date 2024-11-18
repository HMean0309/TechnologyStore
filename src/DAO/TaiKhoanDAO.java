package DAO;

import java.sql.Statement;

import DTO.TaiKhoanDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaiKhoanDAO extends ObjectDAO {
    public TaiKhoanDAO(){
        super();
    }
    
    public ResultSet getAllTaiKhoan(){
        super.connectDB();
        String query = "SELECT * FROM TAI_KHOAN WHERE isDelete = 0;";
        ResultSet rs = super.executeQuery(query);
        
        return rs;
    }

    public ResultSet getTaiKhoan(String idNV){
        super.connectDB();
        String query = "SELECT * FROM TAI_KHOAN WHERE isDelete = 0 AND id_nhanvien = ?;";
        Object[] params = {idNV};
        ResultSet rs = super.executeQuery(query, params);
        
        return rs;
    }

    public ResultSet getCountTaiKhoan(String idNV){
        super.connectDB();
        String query = "SELECT COUNT(*) FROM TAI_KHOAN WHERE isDelete = 0;";
        ResultSet rs = super.executeQuery(query);
        
        return rs;
    }

    // Thay doi Tai kHoan dua tren id nhan vien
    public void updateTKByIdNV(TaiKhoanDTO taikhoan){
        super.connectDB();
        String query = "UPDATE TAI_KHOAN SET password = ?, type = ?, id_quyen = ? WHERE id_nhanvien = ?;";
        Object[] params = {
            taikhoan.getPassword(),
            taikhoan.isType(),
            taikhoan.getIdQuyen(),
            taikhoan.getIdNV()
        };
        super.executeNonQuery(query, params);
        
    }

    // Xoa Tai Khoan bang id nhan vien
    public void removeTKByIdNV(String idNV){
        super.connectDB();
        String query = "UPDATE TAI_KHOAN SET isDelete = 1 WHERE id_nhanvien = ?;";
        Object[] params = {idNV};
        super.executeNonQuery(query, params);
        
    }

    // Them du lieu vao Tai Khoan
    public void addTKWithData(TaiKhoanDTO taikhoan){
        super.connectDB();
        String query = "INSERT INTO TAI_KHOAN (username, password, type, id_nhanvien, id_quyen) VALUES (?, ?, ?, ?, ?);";
        Object[] params = {
            taikhoan.getUsername(),
            taikhoan.getPassword(),
            taikhoan.isType(),
            taikhoan.getIdNV(),
            taikhoan.getIdQuyen()
        };
        super.executeNonQuery(query, params);
        
    }
    
}
