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
        String query = "SELECT * FROM CT_SAN_PHAM WHERE isDelete = 0 ;";
        return super.executeQuery(query);
    }

    public ResultSet getCTSPByIdSP(String id) {
        String query = "SELECT * FROM CT_SAN_PHAM WHERE isDelete = 0 AND id_sp = ? ;";
        Object[] params = { id };
        return super.executeQuery(query, params);
    }

    public ResultSet getColorInCTSPByIdSP(String id){
        String query = "SELECT color FROM CT_SAN_PHAM WHERE isDelete = 0 AND id_sp = ? ;";
        Object[] params = {id};
        return super.executeQuery(query, params);
    }

    public ResultSet getColorInCTSPBySeri(String seri){
        String query = "SELECT color FROM CT_SAN_PHAM WHERE isDelete = 0 AND seri = ? ;";
        Object[] params = {seri};
        return super.executeQuery(query, params);
    }

    public ResultSet getPriceInCTSPByIdSP(String id){
        String query = "SELECT price FROM CT_SAN_PHAM WHERE isDelete = 0 AND id_sp = ? ;";
        Object[] params = {id};
        return super.executeQuery(query, params);
    }   

    public ResultSet getPriceInCTSPBySeri(String seri){
        String query = "SELECT price FROM CT_SAN_PHAM WHERE isDelete = 0 AND seri = ? ;";
        Object[] params = {seri};
        return super.executeQuery(query, params);
    }   

    public ResultSet getCTSPBySeri(String seri) {
        String query = "SELECT * FROM CT_SAN_PHAM WHERE isDelete = 0 AND seri = ? ;";
        Object[] params = { seri };
        return super.executeQuery(query, params);
    }

    public ResultSet getCountCTSPByIdSP(String idSP) {
        String query = "SELECT COUNT(*) FROM CT_SAN_PHAM WHERE isDelete = 0 AND id_sp = ? ;";
        Object[] params = { idSP };
        ResultSet rs = super.executeQuery(query, params);
        return rs;
    }

    public ResultSet getCountCTSPByIdSP(String idSP) {
        String query = "SELECT COUNT(*) FROM CT_SAN_PHAM WHERE isDelete = 0 AND id_sp = ? ;";
        Object[] params = { idSP };
        ResultSet rs = super.executeQuery(query, params);
        return rs;
    }

    // Them du lieu vao Phan Loai
    public void addCTSPWithData(ChiTietSanPhamDTO ctsp) {
        String query = "INSERT INTO CT_SAN_PHAM (seri,id_sp,color,price)" + "VALUES(?,?,?,?)";
        Object[] params = { ctsp.getSeri(), ctsp.getidSP(), ctsp.getColor(), ctsp.getPrice() };
        super.executeNonQuery(query, params);
    }

    public void removeCTSPBySeri(String seri) {
        String query = "UPDATE CT_SAN_PHAM SET isDelete = 1 WHERE seri = ? ;";
        Object[] params = { seri };
        super.executeNonQuery(query, params);
    }

    // Thay doi Phan Loai dua tren id
    public void updateCTSPBySeri(ChiTietSanPhamDTO CT_SAN_PHAM) {
        String query = "UPDATE CT_SAN_PHAM SET id_sp = ?, color = ?, price = ? WHERE seri = ? AND isDelete = 0 ;";
        Object[] params = { ctsp.getidSP(), ctsp.getColor(), ctsp.getPrice(), ctsp.getSeri()};
        super.executeNonQuery(query, params);
    }

    public void updateCTSPByIdSP(ChiTietSanPhamDTO CT_SAN_PHAM) {
        String query = "UPDATE CT_SAN_PHAM SET seri = ?, color = ?, price = ? WHERE id_sp = ? AND isDelete = 0 ;";
        Object[] params = { ctsp.getSeri(), ctsp.getColor(), ctsp.getPrice(), ctsp.getidSP()};
        super.executeNonQuery(query, params);
    }    
}