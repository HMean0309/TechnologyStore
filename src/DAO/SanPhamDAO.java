package DAO;

import java.sql.Statement;

import config.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SanPhamDAO {
    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;
    public SanPhamDAO(){
        conn = MySQLConnection.getConnection();
    }

}
