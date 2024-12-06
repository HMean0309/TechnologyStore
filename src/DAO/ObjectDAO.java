package DAO;

import config.MySQLConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ObjectDAO {
    protected Connection conn;
    protected Statement stmt;
    protected PreparedStatement pstmt;
    protected ResultSet rs;

    public ObjectDAO() {
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

    public int executeNonQuery(String query) {
        int result = -1;
        try {
            stmt = conn.createStatement();
            result = stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Hàm dùng để tự động gán dữ liệu vào câu truy vấn dựa theo kiểu dữ liệu của phần tử chứa trong params.
     * Không có kiểu trả về (phù hợp cho Update/Insert SQL)
     *
     * @param query  Câu truy vấn chứa các ? có thứ tự
     * @param params Mảng dữ liệu có thứ tự để thêm vào câu truy vấn hoàn chỉnh
     */
    public int executeNonQuery(String query, Object[] params) {
        int result = -1;
        try {
            pstmt = conn.prepareStatement(query);
            int i = 1; /*Thứ tự vị trí của dấu ? được thêm dữ liệu vào*/
            for (Object obj : params) {
                /*Lấy ra tên kiểu dữ liệu của phần tử đang xét*/
                if (obj != null) {
                    switch (obj.getClass().getSimpleName()) {
                        /*Xác định được kiểu dữ liệu đang xét, vị trí (dấu ?) của giá trị phần tử được thêm vào*/
                        case "Integer": {
                            /*Thêm vào câu query sao cho sql hiểu là dữ liệu kiểu int*/
                            pstmt.setInt(i, (int) obj);
                            break;
                        }
                        case "Boolean": {
                            /*Thêm vào câu query sao cho sql hiểu là dữ liệu kiểu boolean*/
                            pstmt.setBoolean(i, (boolean) obj);
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
                        case "LocalDate": {
                            /*Thêm vào câu query sao cho sql hiểu là dữ liệu kiểu Date*/
                            pstmt.setDate(i, Date.valueOf((LocalDate) obj));
                            break;
                        }
                    }
                } else {
                    pstmt.setNull(i, Types.CHAR);
                }
                i++; /*Tiếp tục xét đến vị trí (dấu ?) tiếp theo cần thêm giá trị*/
            }
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Hàm dùng để tự động gán dữ liệu vào câu truy vấn dựa theo kiểu dữ liệu của phần tử chứa trong params.
     * Có kiểu trả về (phù hợp cho Select SQL)
     *
     * @param query  Câu truy vấn chứa các ? có thứ tự
     * @param params Mảng dữ liệu có thứ tự để thêm vào câu truy vấn hoàn chỉnh
     */
    public ResultSet executeQuery(String query, Object[] params) {
        try {
            pstmt = conn.prepareStatement(query);
            int i = 1;
            for (Object obj : params) {
                if (obj != null) {
                    switch (obj.getClass().getSimpleName()) {
                        /*Xác định được kiểu dữ liệu đang xét, vị trí (dấu ?) của giá trị phần tử được thêm vào*/
                        case "Integer": {
                            /*Thêm vào câu query sao cho sql hiểu là dữ liệu kiểu int*/
                            pstmt.setInt(i, (int) obj);
                            break;
                        }
                        case "Boolean": {
                            /*Thêm vào câu query sao cho sql hiểu là dữ liệu kiểu boolean*/
                            pstmt.setBoolean(i, (boolean) obj);
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
                        case "LocalDate": {
                            /*Thêm vào câu query sao cho sql hiểu là dữ liệu kiểu Date*/
                            pstmt.setDate(i, Date.valueOf((LocalDate) obj));
                            break;
                        }
                    }
                } else {
                    pstmt.setNull(i, Types.CHAR);
                }
                i++;
            }
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void connectDB() {
        conn = MySQLConnection.getConnection();
    }

    public void closeDB() {
        MySQLConnection.closeConnection(conn);
        stmt = null;
        pstmt = null;
        rs = null;
    }
}
