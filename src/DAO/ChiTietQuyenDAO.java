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
        super.connectDB();
        String query = "SELECT * FROM CHI_TIET_QUYEN;";
        ResultSet rs = super.executeQuery(query);
        
        return rs;
    }

    public ResultSet getChiTietQuyen(String idQuyen){
        super.connectDB();
        String query = "SELECT * FROM CHI_TIET_QUYEN WHERE id_quyen = ?;";
        Object[] params = {idQuyen};
        ResultSet rs = super.executeQuery(query, params);
        
        return rs;
    }

    public ResultSet getCountChiTietQuyen(String idQuyen){
        super.connectDB();
        String query = "SELECT COUNT(*) FROM CHI_TIET_QUYEN WHERE id_quyen = ?;";
        Object[] params = {idQuyen};
        ResultSet rs = super.executeQuery(query, params);
        
        return rs;
    }

    // Thay doi Chi Tiet Quyen dua tren id
    public void updateChiTietQuyenById(ChiTietQuyenDTO ctQuyen){
        super.connectDB();
        String query = "UPDATE CHI_TIET_QUYEN WHERE id_quyen = ? AND id_chuc_nang = ?;";
        Object[] params = {
            ctQuyen.getIdQuyen(),
            ctQuyen.getIdChucNang()
        };
        super.executeNonQuery(query, params);
        
    }


    // Them du lieu vao chi tiet quyen
    public void addChiTietQuyenWithData(ChiTietQuyenDTO ctQuyen){
        super.connectDB();
        String query = "INSERT INTO CHI_TIET_QUYEN (id_quyen, id_chuc_nang) VALUES (?, ?);";
        Object[] params = {
            ctQuyen.getIdQuyen(),
            ctQuyen.getIdChucNang()
        };
        super.executeNonQuery(query, params);
        
    }
}
