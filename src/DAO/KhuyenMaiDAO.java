package DAO;

import java.sql.Statement;

import DTO.KhuyenMaiDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KhuyenMaiDAO extends ObjectDAO {
    public KhuyenMaiDAO(){
        super();
    }

    public ResultSet getAllKhuyenMai()
    {
        super.connectDB();
        String query = "SELECT * FROM KHUYEN_MAI WHERE isDelete = 0 ;";
        super.closeDB();
        return super.executeQuery(query);
    }

    public ResultSet getCountKMByDate(KhuyenMaiDTO km){
        super.connectDB();
        String query = "SELECT COUNT(*) FROM KHUYEN_MAI WHERE startDateTime = ? AND endDatetime = ? ;";
        Object[] params = {km.getStartLocalDateTimetime(),km.getEndLocalDateTimetime()};
        return super.executeQuery(query, params);
    }
    
    public ResultSet getAllCountKM()
    {
        super.connectDB();
        String query = "SELECT COUNT(*) FROM KHUYEN_MAI ;";
        super.closeDB();
        return super.executeQuery(query);
    }

    public void addKMWithData(KhuyenMaiDTO km){
        super.connectDB();
        String query = "INSERT INTO KHUYEN_MAI VALUES (id,startDatetime,endDatetime,donviKM,value,des)" +"(?,?,?,?,?,?)";
        Object[] params = {km.getId(),km.getStartLocalDateTimetime(),km.getEndLocalDateTimetime(),km.isDonviKM(),km.getDes()};
        super.closeDB();
        super.executeNonQuery(query, params);
    }

    public void updateKMById(KhuyenMaiDTO km){
        super.connectDB();
        String query = "UPDATE KHUYEN_MAI SET startDatetime = ?, endDatetime = ?, donviKM = ?, value = ?, des = ? WHERE id = ? ;";
        Object[] params = {km.getStartLocalDateTimetime(),km.getEndLocalDateTimetime(),km.isDonviKM(),km.getValue(),km.getDes(),km.getId()};
        super.closeDB();
        super.executeNonQuery(query, params);
    }

    public void removeKMById(String id){
        super.connectDB();
        String query = "UPDATE KHUYEN_MAi SET isDelete = 1 WHERE id = ? ;";
        Object[] params = {id};
        super.closeDB();
        super.executeNonQuery(query, params);
    }
}