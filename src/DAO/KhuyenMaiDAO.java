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
        String query = "SELECT * FROM KHUYEN_MAI WHERE isDelete = 0 ;";
        return super.executeQuery(query);
    }

    public ResultSet getCountKMByDate(KhuyenMaiDTO km){
        String query = "SELECT COUNT(*) FROM KHUYEN_MAI WHERE startDateTime = ? AND endDatetime = ? ;";
        Object[] params = {km.getStartLocalDateTimetime(),km.getEndLocalDateTimetime()};
        return super.executeQuery(query, params);
    }
    
    public ResultSet getAllCountKM()
    {
        String query = "SELECT COUNT(*) FROM KHUYEN_MAI ;";
        return super.executeQuery(query);
    }

    public void addKMWithData(KhuyenMaiDTO km){
        String query = "INSERT INTO KHUYEN_MAI VALUES (id,startDatetime,endDatetime,donviKM,value,des)" +"(?,?,?,?,?,?)";
        Object[] params = {km.getId(),km.getStartLocalDateTimetime(),km.getEndLocalDateTimetime(),km.isDonviKM(),km.getDes()};
        super.executeNonQuery(query, params);
    }

    public void updateKMById(KhuyenMaiDTO km){
        String query = "UPDATE KHUYEN_MAI SET startDatetime = ?, endDatetime = ?, donviKM = ?, value = ?, des = ? WHERE id = ? ;";
        Object[] params = {km.getStartLocalDateTimetime(),km.getEndLocalDateTimetime(),km.isDonviKM(),km.getValue(),km.getDes(),km.getId()};
        super.executeNonQuery(query, params);
    }

    public void removeKMById(String id){
        String query = "UPDATE KHUYEN_MAi SET isDelete = 1 WHERE id = ? ;";
        Object[] params = {id};
        super.executeNonQuery(query, params);
    }
}