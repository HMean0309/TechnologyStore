package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import config.MySQLConnection;

public class ObjectDAO {
    protected Connection conn;
    protected Statement stmt;
    protected PreparedStatement pstmt;
    protected ResultSet rs;
    public ObjectDAO(){
        conn = MySQLConnection.getConnection();
    }
    
    public ResultSet executeQuery(String query) {
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void executeNonQuery(String query) {
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm dùng để tự động gán dữ liệu vào câu truy vấn dựa theo kiểu dữ liệu của phần tử chứa trong params.
     * Không có kiểu trả về (phù hợp cho Update/Insert SQL)
     * @param query Câu truy vấn chứa các ? có thứ tự
     * @param params Mảng dữ liệu có thứ tự để thêm vào câu truy vấn hoàn chỉnh
     *
     */
    public void executeNonQuery(String query, Object[] params) {
        try {
            pstmt = conn.prepareStatement(query);
            int i = 1; /*Thứ tự vị trí của dấu ? được thêm dữ liệu vào*/
            for (Object obj : params) {
                /*Lấy ra tên kiểu dữ liệu của phần tử đang xét*/
                switch (obj.getClass().getSimpleName()) {
                    /*Xác định được kiểu dữ liệu đang xét, vị trí (dấu ?) của giá trị phần tử được thêm vào*/
                    case "Integer": {
                        /*Thêm vào câu query sao cho sql hiểu là dữ liệu kiểu int*/
                        pstmt.setInt(i, (int) obj);
                        break;
                    }
                    case "String": {
                        /*Thêm vào câu query sao cho sql hiểu là dữ liệu kiểu nvarchar/varchar (phần này thì code tự động biết để thêm N'' cho câu lệnh sql)*/
                        pstmt.setString(i, (String) obj);
                        break;
                    }
                    case "LocalDateTime": {
                        /*Thêm vào câu query sao cho sql hiểu là dữ liệu kiểu DateTime*/
                        pstmt.setTimestamp(i, Timestamp.valueOf((LocalDateTime) obj));
                        break;
                    }
                }
                i++; /*Tiếp tục xét đến vị trí (dấu ?) tiếp theo cần thêm giá trị*/
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm dùng để tự động gán dữ liệu vào câu truy vấn dựa theo kiểu dữ liệu của phần tử chứa trong params.
     * Có kiểu trả về (phù hợp cho Select SQL)
     * @param query Câu truy vấn chứa các ? có thứ tự
     * @param params Mảng dữ liệu có thứ tự để thêm vào câu truy vấn hoàn chỉnh
     *
     */
    public ResultSet executeQuery(String query, Object[] params) {
        try {
            pstmt = conn.prepareStatement(query);
            int i = 1;
            for (Object obj : params) {
                switch (obj.getClass().getSimpleName()) {
                    case "Integer": {
                        pstmt.setInt(i, (int) obj);
                        break;
                    }
                    case "String": {
                        pstmt.setString(i, (String) obj);
                        break;
                    }
                    case "LocalDateTime": {
                        pstmt.setTimestamp(i, Timestamp.valueOf((LocalDateTime) obj));
                        break;
                    }
                }
                i++;
            }
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void close() {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
