/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.ResultSet;

/**
 *
 * @author nghia
 */
public class NhaCungCapDAO extends ObjectDAO {
    public NhaCungCapDAO(){
        super();
    }
    public ResultSet getAllNhaCungCap(){
        String query = "SELECT * FROM ncc WHERE isDelete = 0 ;";
        return super.executeQuery(query);
    }
    
}
