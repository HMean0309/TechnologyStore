package DAO;

import java.sql.Statement;

import DTO.ChiTietQuyenDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChiTietQuyenDAO extends ObjectDAO {
    public ChiTietQuyenDAO(){
        super();
    }

    public ResultSet getAllChiTietQuyen(){
        String query = "SELECT * FROM PRODUCT WHERE isDelete = 0 ;";
        return super.executeQuery(query);
    }

    public ResultSet getChiTietQuyen(String idQuyen){
        String query = "SELECT * FROM PRODUCT WHERE isDelete = 0 AND id_cate = ? ;";
        Object[] params = {idQuyen};
        return super.executeQuery(query, params);
    }

    public ResultSet getCountChiTietQuyen(String idQuyen){
        String query = "SELECT COUNT(*) FROM PRODUCT WHERE isDelete = 0 AND id_cate = ? ;";
        Object[] params = {idQuyen};
        ResultSet rs = super.executeQuery(query, params);
        
        return rs;
    }

    // Thay doi Chi Tiet Quyen dua tren id
    public void updateChiTietQuyenById(ChiTietQuyenDTO ctQuyen){
        String query = "UPDATE PRODUCT SET name = ?, id_cate = ?, baohanh = ?, des = ?, img = ? WHERE id = ? ;";
        Object[] params = {ctQuyen.getIdQuyen(), ctQuyen.getIdChucNang(), ctQuyen.isShow(), ctQuyen.isInsert(), ctQuyen.isEdit()};
        super.executeNonQuery(query, params);
    }

    // Xoa Chi Tiet Quyen bang id
    public void removeChiTietQuyenById(String idQuyen){
        String query = "UPDATE PRODUCT SET isDelete = 1 WHERE id = ? ;";
        Object[] params = {idQuyen};
        super.executeNonQuery(query, params);
    }

    // Them du lieu vao chi tiet quyen
    public void addChiTietQuyenWithData(ChiTietQuyenDTO ctQuyen){
        String query = "INSERT INTO PRODUCT (id,name,id_cate,baohanh,des,img)"+"VALUES(?,?,?,?,?,?) ;";
        Object[] params = {ctQuyen.getIdQuyen(), ctQuyen.getIdChucNang(), ctQuyen.isShow(), ctQuyen.isInsert(), ctQuyen.isEdit()};
        super.executeNonQuery(query,params);
    }
}
