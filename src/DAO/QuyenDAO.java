package DAO;

import DTO.QuyenDTO;

import java.sql.ResultSet;

public class QuyenDAO extends ObjectDAO {
    public QuyenDAO() {
        super();
    }

    public static QuyenDAO getInstance() {
        return new QuyenDAO();
    }

    public ResultSet getAllQuyen() {
        super.connectDB();
        String query = "SELECT * FROM QUYEN WHERE isDelete = 0;";
        ResultSet rs = super.executeQuery(query);

        return rs;
    }

    public ResultSet getQuyen(String id) {
        super.connectDB();
        String query = "SELECT * FROM QUYEN WHERE isDelete = 0 AND id = ?;";
        Object[] params = { id };
        ResultSet rs = super.executeQuery(query, params);

        return rs;
    }

    public ResultSet getCountQuyen() {
        super.connectDB();
        String query = "SELECT COUNT(*) FROM QUYEN";
        ResultSet rs = super.executeQuery(query);

        return rs;
    }

    // Thay doi quyen dua tren id
    public void updateQuyenById(QuyenDTO quyen) {
        super.connectDB();
        String query = "UPDATE QUYEN SET name = ? WHERE id = ?;";
        Object[] params = {
                quyen.getName(),
                quyen.getId()
        };
        super.executeNonQuery(query, params);

    }

    // Xoa quyen bang id
    public void removeQuyenById(String id) {
        super.connectDB();
        String query = "UPDATE QUYEN SET isDelete = 1 WHERE id = ?;";
        Object[] params = { id };
        super.executeNonQuery(query, params);

    }

    // Them du lieu vao quyen
    public void addQuyenWithData(QuyenDTO quyen) {
        super.connectDB();
        String query = "INSERT INTO QUYEN (id, name) VALUES (?, ?);";
        Object[] params = { quyen.getId(), quyen.getName() };
        super.executeNonQuery(query, params);

    }
}
