package DAO;

import java.sql.Statement;

import DTO.QuyenDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuyenDAO extends ObjectDAO {
    public QuyenDAO(){
        super();
    }
    
    public ResultSet getAllQuyen(){
        String query = "SELECT * FROM PRODUCT WHERE isDelete = 0 ;";
        return super.executeQuery(query);
    }

    public ResultSet getQuyen(String id){
        String query = "SELECT * FROM PRODUCT WHERE isDelete = 0 AND id_cate = ? ;";
        Object[] params = {id};
        return super.executeQuery(query, params);
    }

    public ResultSet getCountQuyen(String id){
        String query = "SELECT COUNT(*) FROM PRODUCT WHERE isDelete = 0 AND id_cate = ? ;";
        Object[] params = {id};
        ResultSet rs = super.executeQuery(query, params);
        
        return rs;
    }

    // Thay doi quyen dua tren id
    public void updateQuyenById(QuyenDTO quyen){
        String query = "UPDATE PRODUCT SET name = ?, id_cate = ?, baohanh = ?, des = ?, img = ? WHERE id = ? ;";
        Object[] params = {quyen.getId(), quyen.getName(), quyen.getDes()};
        super.executeNonQuery(query, params);
    }

    // Xoa quyen bang id
    public void removeQuyenById(String id){
        String query = "UPDATE PRODUCT SET isDelete = 1 WHERE id = ? ;";
        Object[] params = {id};
        super.executeNonQuery(query, params);
    }

    // Them du lieu vao quyen
    public void addQuyenWithData(QuyenDTO quyen){
        String query = "INSERT INTO PRODUCT (id,name,id_cate,baohanh,des,img)"+"VALUES(?,?,?,?,?,?) ;";
        Object[] params = {quyen.getId(), quyen.getName(), quyen.getDes()};
        super.executeNonQuery(query,params);
    }
}
