package DAO;

import java.sql.Statement;
import DTO.PhanLoaiDTO;
import config.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.MySQLConnection;

public class PhanLoaiDAO extends ObjectDAO {
  public PhanLoaiDAO(){
    super();
  }

  public ResultSet getAllPhanLoai(){
    String query = "SELECT * FROM CATEGORY WHERE isDelete = 0 ;";
    return super.executeQuery(query);
  }

  public ResultSet getPhanLoaiById(int id)
  {
    String query = "SELECT * FROM CATEGORY WHERE isDelete = 0 AND id = ? ;";
    Object[] params = {id};
    return super.executeQuery(query, params);
  }

  public ResultSet getPhanLoaiByName(String name){
    String query = "SELECT * FROM CATEGORY WHERE isDelete = 0 AND name = ? ;";
    Object[] params = {name};
    return super.executeQuery(query, params);
  }
  
  public ResultSet getCountPLByName(String name){
    String query = "SELECT COUNT(*) FROM CATEGORY WHERE isDelete = 0 AND name = ? ;";
    Object[] params = {name};
    ResultSet rs = super.executeQuery(query, params);
    return rs;
  }

    // Them du lieu vao Phan Loai
    public void addPLWithData(PhanLoaiDTO category){
        String query = "INSERT INTO CATEGORY (id,name,isDelete)"+"VALUES(?,?,?)";
        Object[] params = {category.getId(),category.getName(),category.isDelete()};
        super.executeNonQuery(query,params);
    }
      // Xoa Phan Loai bang id
    public void removePLById(int id){
        String query = "UPDATE CATEGORY SET isDelete = 1 WHERE id = ? ;";
        Object[] params = {id};
        super.executeNonQuery(query, params);
    }
  
      // Thay doi Phan Loai dua tren id
    public void updatePLById(PhanLoaiDTO category){
        String query = "UPDATE CATEGORY SET name = ? WHERE id = ? ;";
        Object[] params = {category.getName(), category.getId()};
        super.executeNonQuery(query, params);
    }
}