package DAO;

import java.sql.Statement;

import DTO.PhanLoaiDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PhanLoaiDAO extends ObjectDAO {
    public PhanLoaiDAO() {
        super();
    }

    public ResultSet getAllPhanLoai() {
        String query = "SELECT * FROM PHAN_lOAI WHERE isDelete = 0 ;";
        return super.executeQuery(query);
    }

    public ResultSet getPhanLoaiById(String id) {
        String query = "SELECT * FROM PHAN_lOAI WHERE isDelete = 0 AND id = ? ;";
        Object[] params = { id };
        return super.executeQuery(query, params);
    }

    public ResultSet getPhanLoaiByName(String name) {
        String query = "SELECT * FROM PHAN_lOAI WHERE isDelete = 0 AND name = ? ;";
        Object[] params = { name };
        return super.executeQuery(query, params);
    }

    public ResultSet getCountPLByName(String name) {
        String query = "SELECT COUNT(*) FROM PHAN_lOAI WHERE isDelete = 0 AND name = ? ;";
        Object[] params = { name };
        ResultSet rs = super.executeQuery(query, params);
        return rs;
    }

    public ResultSet getCountPLById(String id) {
        String query = "SELECT COUNT(*) FROM PHAN_lOAI WHERE isDelete = 0 AND id = ? ;";
        Object[] params = { id };
        ResultSet rs = super.executeQuery(query, params);
        return rs;
    }

    // Them du lieu vao Phan Loai
    public void addPLWithData(PhanLoaiDTO PHAN_lOAI) {
        String query = "INSERT INTO PHAN_lOAI (id,name)" + "VALUES(?,?)";
        Object[] params = { PHAN_lOAI.getId(), PHAN_lOAI.getName() };
        super.executeNonQuery(query, params);
    }

    // Xoa Phan Loai bang id
    public void removePLById(String id) {
        String query = "UPDATE PHAN_lOAI SET isDelete = 1 WHERE id = ? ;";
        Object[] params = { id };
        super.executeNonQuery(query, params);
    }

    public void removePLByName(String name) {
        String query = "UPDATE PHAN_lOAI SET isDelete = 1 WHERE name = ? ;";
        Object[] params = { name };
        super.executeNonQuery(query, params);
    }

    // Thay doi Phan Loai dua tren id
    public void updatePLById(PhanLoaiDTO PHAN_lOAI) {
        String query = "UPDATE PHAN_lOAI SET name = ? WHERE id = ? AND isDelete = 0 ;";
        Object[] params = { PHAN_lOAI.getName(), PHAN_lOAI.getId() };
        super.executeNonQuery(query, params);
    }
}