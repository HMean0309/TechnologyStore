package DAO;

import java.sql.Statement;

import DTO.QuyenDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuyenDAO extends ObjectDAO {
    public QuyenDAO(){
        super();
    }
    
    public ResultSet getAllQuyen(){
        super.connectDB();
        String query = "SELECT * FROM QUYEN WHERE isDelete = 0;";
        ResultSet rs = super.executeQuery(query);
        
        return rs;
    }

    public ResultSet getQuyen(String id){
        super.connectDB();
        String query = "SELECT * FROM QUYEN WHERE isDelete = 0 AND id = ?;";
        Object[] params = {id};
        ResultSet rs = super.executeQuery(query, params);
        
        return rs;
    }

    public ResultSet getCountQuyen(String id){
        super.connectDB();
        String query = "SELECT COUNT(*) FROM QUYEN WHERE isDelete = 0;";
        ResultSet rs = super.executeQuery(query);
        
        return rs;
    }

    // Thay doi quyen dua tren id
    public void updateQuyenById(QuyenDTO quyen){
        super.connectDB();
        String query = "UPDATE QUYEN SET name = ?, des = ? WHERE id = ?;";
        Object[] params = {
            quyen.getId(),
            quyen.getName(),
            quyen.getDes()
            
        };
        super.executeNonQuery(query, params);
        
    }

    // Xoa quyen bang id
    public void removeQuyenById(String id){
        super.connectDB();
        String query = "UPDATE QUYEN SET isDelete = 1 WHERE id = ?;";
        Object[] params = {id};
        super.executeNonQuery(query, params);
        
    }

    // Them du lieu vao quyen
    public void addQuyenWithData(QuyenDTO quyen){
        super.connectDB();
        String query = "INSERT INTO QUYEN (id, name, des) VALUES (?, ?, ?);";
        Object[] params = {quyen.getId(), quyen.getName(), quyen.getDes()};
        super.executeNonQuery(query, params);
        
    }
}
