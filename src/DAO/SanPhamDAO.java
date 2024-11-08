package DAO;

import java.sql.Statement;

import DTO.SanPhamDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SanPhamDAO extends ObjectDAO {
    public SanPhamDAO(){
        super();
    }

    public ResultSet getAllSanPham(){
        String query = "SELECT * FROM PRODUCT WHERE isDelete = 0 ;";
        return super.executeQuery(query);
    }

    public ResultSet getSanPhamOfPhanLoai(String idCate){
        String query = "SELECT * FROM PRODUCT WHERE isDelete = 0 AND id_cate = ? ;";
        Object[] params = {idCate};
        return super.executeQuery(query, params);
    }

    public ResultSet getCountSPOfPhanLoai(String idCate){
        String query = "SELECT COUNT(*) FROM PRODUCT WHERE isDelete = 0 AND id_cate = ? ;";
        Object[] params = {idCate};
        ResultSet rs = super.executeQuery(query, params);
        
        return rs;
    }

    // Thay doi san pham dua tren id
    public void updateSPById(SanPhamDTO product){
        String query = "UPDATE PRODUCT SET name = ?, id_cate = ?, baohanh = ?, des = ?, img = ? WHERE id = ? ;";
        Object[] params = {product.getName(), product.getIdCate(), product.getBaoHanh(), product.getDes(), product.getImg(), product.getId() };
        super.executeNonQuery(query, params);
    }

    // Xoa san pham bang id
    public void removeSPById(int id){
        String query = "UPDATE PRODUCT SET isDelete = 1 WHERE id = ? ;";
        Object[] params = {id};
        super.executeNonQuery(query, params);
    }

    // Them du lieu vao san pham
    public void addSPWithData(SanPhamDTO product){
        String query = "INSERT INTO PRODUCT (id,name,id_cate,baohanh,des,img)"+"VALUES(?,?,?,?,?,?) ;";
        Object[] params = {product.getId(),product.getName(),product.getIdCate(),product.getBaoHanh(),product.getDes(),product.getImg()};
        super.executeNonQuery(query,params);
    }
}
