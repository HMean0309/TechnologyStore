package DAO;

import java.sql.Statement;

import DTO.ChiTietSanPhamDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChiTietSPDAO extends ObjectDAO{
    public ChiTietSPDAO(){
        super();
    }

    public ResultSet getAllCTSP() {
        super.connectDB();
        String query = "SELECT * FROM CT_SAN_PHAM WHERE isDelete = 0 ;";
        super.closeDB();
        return super.executeQuery(query);
    }
    
    public ResultSet getAllCountCTSP() {
        super.connectDB();
        String query = "SELECT COUNT(*) FROM CT_SAN_PHAM WHERE isDelete = 0 ;";
        super.closeDB();
        return super.executeQuery(query);       
    }
    
     public ResultSet getCountCTSPById(String id_sp) {
        super.connectDB();
        String query = "SELECT COUNT(*) FROM CT_SAN_PHAM WHERE isDelete = 0 AND id_sp = ? ;";
        Object[] params = { id_sp };
        super.closeDB();
        return super.executeQuery(query, params);       
    }

    public ResultSet getCTSPByIdSP(String id) {
        super.connectDB();
        String query = "SELECT * FROM CT_SAN_PHAM WHERE isDelete = 0 AND id_sp = ? ;";
        Object[] params = { id };
        super.closeDB();
        return super.executeQuery(query, params);
    }

    public ResultSet getColorInCTSPByIdSP(String id){
        super.connectDB();
        String query = "SELECT color FROM CT_SAN_PHAM WHERE isDelete = 0 AND id_sp = ? ;";
        Object[] params = {id};
        super.closeDB();
        return super.executeQuery(query, params);
    }

    public ResultSet getColorInCTSPBySeri(String seri){
        super.connectDB();
        String query = "SELECT color FROM CT_SAN_PHAM WHERE isDelete = 0 AND seri = ? ;";
        Object[] params = {seri};
        super.closeDB();
        return super.executeQuery(query, params);
    }

    public ResultSet getPriceInCTSPByIdSP(String id){
        super.connectDB();
        String query = "SELECT price FROM CT_SAN_PHAM WHERE isDelete = 0 AND id_sp = ? ;";
        Object[] params = {id};
        super.closeDB();
        return super.executeQuery(query, params);
    }   

    public ResultSet getPriceInCTSPBySeri(String seri){
        super.connectDB();
        String query = "SELECT price FROM CT_SAN_PHAM WHERE isDelete = 0 AND seri = ? ;";
        Object[] params = {seri};
        super.closeDB();
        return super.executeQuery(query, params);
    }   

    public ResultSet getCTSPBySeri(String seri) {
        super.connectDB();
        String query = "SELECT * FROM CT_SAN_PHAM WHERE isDelete = 0 AND seri = ? ;";
        Object[] params = { seri };
        super.closeDB();
        return super.executeQuery(query, params);
    }

    public ResultSet getCountCTSPByIdSP(String idSP) {
        super.connectDB();
        String query = "SELECT COUNT(*) FROM CT_SAN_PHAM WHERE isDelete = 0 AND id_sp = ? ;";
        Object[] params = { idSP };
        ResultSet rs = super.executeQuery(query, params);
        super.closeDB();
        return rs;
    }

    // Them du lieu vao Phan Loai
    public void addCTSPWithData(ChiTietSanPhamDTO ctsp) {
        super.connectDB();
        String query = "INSERT INTO CT_SAN_PHAM (seri,id_sp,color,price)" + "VALUES(?,?,?,?)";
        Object[] params = { ctsp.getSeri(), ctsp.getidSP(), ctsp.getColor(), ctsp.getPrice() };
        super.closeDB();
        super.executeNonQuery(query, params);
    }

    public void removeCTSPBySeri(String seri) {
        super.connectDB();
        String query = "UPDATE CT_SAN_PHAM SET isDelete = 1 WHERE seri = ? ;";
        Object[] params = { seri };
        super.closeDB();
        super.executeNonQuery(query, params);
    }

    // Thay doi Phan Loai dua tren id
    public void updateCTSPBySeri(ChiTietSanPhamDTO ctsp) {
        super.connectDB();
        String query = "UPDATE CT_SAN_PHAM SET id_sp = ?, color = ?, price = ? WHERE seri = ? AND isDelete = 0 ;";
        Object[] params = { ctsp.getidSP(), ctsp.getColor(), ctsp.getPrice(), ctsp.getSeri()};
        super.closeDB();
        super.executeNonQuery(query, params);
    }
  
}