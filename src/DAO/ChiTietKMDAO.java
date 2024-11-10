package DAO;

import java.sql.Statement;

import DTO.ChiTietKhuyenMaiDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChiTietKMDAO extends ObjectDAO{
    public ChiTietKMDAO(){
        super();
    }

    public ResultSet getAllCTKM(){
        super.connectDB();
        String query = "SELECT * FROM CT_KM ;";
        super.closeDB();
        return super.executeQuery(query);
    }
    
    public ResultSet getCTKMByIdKM(String idkm){
        super.connectDB();
        String query = "SELECT * FROM CT_KM WHERE id_km = ? ;";
        Object[] params = {idkm};
        super.closeDB();
        return super.executeQuery(query, params);
    }

    public ResultSet getCountAllCTKM(){
        super.connectDB();
        String query = "SELECT COUNT(*) FROM CT_KM ;";
        super.closeDB();
        return super.executeQuery(query);
    }

    public void addCTKMWithData(ChiTietKhuyenMaiDTO ctkm){
        super.connectDB();
        String query = "INSERT INTO CT_KM (id_km,id_sp)"+"VALUES(?,?) ;";
        Object[] params = {ctkm.getIdKM(),ctkm.getidSP()};
        super.closeDB();
        super.executeNonQuery(query, params);
    }

    public void updateCTKMByIdKM(ChiTietKhuyenMaiDTO ctkm){
        super.connectDB();
        String query = "UPDATE CT_KM SET id_sp = ? WHERE id_km = ? ;";
        Object[] params = {ctkm.getidSP(),ctkm.getIdKM()};
        super.closeDB();
        super.executeNonQuery(query, params);
    }
}