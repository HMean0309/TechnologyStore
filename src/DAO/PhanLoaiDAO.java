package DAO;

import DTO.PhanLoaiDTO;
import config.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PhanLoaiDAO extends ObjectDAO {
    public PhanLoaiDAO() {
        super();
    }

    public ResultSet getAllPhanLoai() {
        super.connectDB();
        String query = "SELECT * FROM PHAN_lOAI WHERE isDelete = 0 ;";

        return super.executeQuery(query);
    }

    public ResultSet getCountPhanLoai() {
        super.connectDB();
        String query = "SELECT COUNT(*) FROM PHAN_lOAI;";

        return super.executeQuery(query);
    }

    public ResultSet getPhanLoaiById(String id) {
        super.connectDB();
        String query = "SELECT * FROM PHAN_LOAI WHERE isDelete = 0 AND id = ? ;";
        Object[] params = { id };

        return super.executeQuery(query, params);
    }

    // Them du lieu vao Phan Loai
    public void addPLWithData(PhanLoaiDTO PHAN_lOAI) {
        super.connectDB();
        String query = "INSERT INTO PHAN_lOAI (id,name)" + "VALUES(?,?)";
        Object[] params = { PHAN_lOAI.getId(), PHAN_lOAI.getName() };

        super.executeNonQuery(query, params);
    }

    // Xoa Phan Loai bang id
    public void removePLById(String id) {
        super.connectDB();
        String query = "UPDATE PHAN_lOAI SET isDelete = 1 WHERE id = ? ;";
        Object[] params = { id };

        super.executeNonQuery(query, params);
    }

    // Thay doi Phan Loai dua tren id
    public void updatePLById(PhanLoaiDTO PHAN_lOAI) {
        super.connectDB();
        String query = "UPDATE PHAN_lOAI SET name = ? WHERE id = ?;";
        Object[] params = { PHAN_lOAI.getName(), PHAN_lOAI.getId() };

        super.executeNonQuery(query, params);
    }
}