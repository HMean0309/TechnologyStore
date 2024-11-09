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
        String query = "SELECT * FROM PRODUCT WHERE isDelete = 0 ;";
        return super.executeQuery(query);
    }

    public ResultSet getTaiKhoan(String idNV){
        String query = "SELECT * FROM PRODUCT WHERE isDelete = 0 AND id_cate = ? ;";
        Object[] params = {idNV};
        return super.executeQuery(query, params);
    }

    public ResultSet getCountTaiKhoan(String idNV){
        String query = "SELECT COUNT(*) FROM PRODUCT WHERE isDelete = 0 AND id_cate = ? ;";
        Object[] params = {idNV};
        ResultSet rs = super.executeQuery(query, params);
        
        return rs;
    }

    // Thay doi Tai kHoan dua tren id nhan vien
    public void updateTKByIdNV(TaiKhoanDTO taikhoan){
        String query = "UPDATE PRODUCT SET name = ?, id_cate = ?, baohanh = ?, des = ?, img = ? WHERE id = ? ;";
        Object[] params = {taikhoan.getUsername(), taikhoan.getPassword(), taikhoan.isType(), taikhoan.getIdNV(), taikhoan.getIdQuyen()};
        super.executeNonQuery(query, params);
    }

    // Xoa Tai Khoan bang id nhan vien
    public void removeTKByIdNV(String idNV){
        String query = "UPDATE PRODUCT SET isDelete = 1 WHERE id = ? ;";
        Object[] params = {idNV};
        super.executeNonQuery(query, params);
    }

    // Them du lieu vao Tai Khoan
    public void addTKWithData(TaiKhoanDTO taikhoan){
        String query = "INSERT INTO PRODUCT (id,name,id_cate,baohanh,des,img)"+"VALUES(?,?,?,?,?,?) ;";
        Object[] params = {taikhoan.getUsername(), taikhoan.getPassword(), taikhoan.isType(), taikhoan.getIdNV(), taikhoan.getIdQuyen()};
        super.executeNonQuery(query,params);
    }
    
}
