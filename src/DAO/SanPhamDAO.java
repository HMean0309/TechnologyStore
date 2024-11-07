package DAO;

import java.sql.Statement;

import config.MySQLConnection;

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
}
