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
        super.connectDB();
        String query = "SELECT * FROM PRODUCT WHERE isDelete = 0 ;";
        ResultSet rs = super.executeQuery(query);
        super.closeDB();
        return rs;
    }

    public ResultSet getSanPhamOfPhanLoai(String idCate){
        super.connectDB();
        String query = "SELECT * FROM PRODUCT WHERE isDelete = 0 AND id_cate = ? ;";
        Object[] params = {idCate};
        ResultSet rs = super.executeQuery(query, params);
        super.closeDB();
        return rs;
    }

    public ResultSet getCountSPOfPhanLoai(String idCate){
        super.connectDB();
        String query = "SELECT COUNT(*) FROM PRODUCT WHERE isDelete = 0 AND id_cate = ? ;";
        Object[] params = {idCate};
        ResultSet rs = super.executeQuery(query, params);
        return rs;
    }

    // Thay doi san pham dua tren id
    public void updateSPById(SanPhamDTO product){
        super.connectDB();
        String query = "UPDATE PRODUCT SET name = ?, id_cate = ?, baohanh = ?, des = ?, img = ? WHERE id = ? ;";
        Object[] params = {product.getName(), product.getIdCate(), product.getBaoHanh(), product.getDes(), product.getImg(), product.getId() };
        super.executeNonQuery(query, params);
        super.closeDB();
    }

    // Xoa san pham bang id
    public void removeSPById(String id){
        super.connectDB();
        String query = "UPDATE PRODUCT SET isDelete = 1 WHERE id = ? ;";
        Object[] params = {id};
        super.executeNonQuery(query, params);
        super.closeDB();
    }

    // Them du lieu vao san pham
    public void addSPWithData(SanPhamDTO product){
        super.connectDB();
        String query = "INSERT INTO PRODUCT (id,name,id_cate,baohanh,des,img)"+"VALUES(?,?,?,?,?,?) ;";
        Object[] params = {product.getId(),product.getName(),product.getIdCate(),product.getBaoHanh(),product.getDes(),product.getImg()};
        super.executeNonQuery(query,params);
        super.closeDB();
    }
}
