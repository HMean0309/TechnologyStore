package DAO;

import java.sql.Statement;

import DTO.PhanLoaiDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import config.MySQLConnection;

public class PhanLoaiDAO extends ObjectDAO {
    public PhanLoaiDAO() {
        super();
    }

    public ResultSet getAllPhanLoai() {
        String query = "SELECT * FROM PHAN_LOAI WHERE isDelete = 0 ;";
        return super.executeQuery(query);
    }

    public ResultSet getPhanLoaiById(String id) {
        String query = "SELECT * FROM PHAN_LOAI WHERE isDelete = 0 AND id = ? ;";
        Object[] params = { id };
        return super.executeQuery(query, params);
    }

    public ResultSet getPhanLoaiByName(String name) {
        String query = "SELECT * FROM PHAN_LOAI WHERE isDelete = 0 AND name = ? ;";
        Object[] params = { name };
        return super.executeQuery(query, params);
    }

    public ResultSet getCountPLByName(String name) {
        String query = "SELECT COUNT(*) FROM PHAN_LOAI WHERE isDelete = 0 AND name = ? ;";
        Object[] params = { name };
        ResultSet rs = super.executeQuery(query, params);
        return rs;
    }

    // Them du lieu vao Phan Loai
    public void addPLWithData(PhanLoaiDTO category) {
        String query = "INSERT INTO PHAN_LOAI (id,name)" + "VALUES(?,?)";
        Object[] params = { category.getId(), category.getName() };
        super.executeNonQuery(query, params);
    }
    
    public ResultSet getCountPhanLoai()
    {
        String query = "SELECT COUNT(*) FROM PHAN_lOAI WHERE isDelete = 0 ;";
        return super.executeQuery(query);
    }
    

    // Xoa Phan Loai bang id
    public void removePLById(String id) {
        String query = "UPDATE PHAN_LOAI SET isDelete = 1 WHERE id = ? ;";

        Object[] params = { id };
        super.executeNonQuery(query, params);
    }

    // Thay doi Phan Loai dua tren id
    public void updatePLById(PhanLoaiDTO category) {
        String query = "UPDATE PHAN_LOAI SET name = ? WHERE id = ? ;";
        Object[] params = { category.getName(), category.getId() };
        super.executeNonQuery(query, params);
    }
}

