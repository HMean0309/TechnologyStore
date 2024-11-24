package DAO;

import DTO.NhanVienDTO;

import java.sql.ResultSet;

public class NhanVienDAO extends ObjectDAO {
    public NhanVienDAO() {
        super();
    }

    public static NhanVienDAO getInstance() {
        return new NhanVienDAO();
    }

    public ResultSet getAllNhanVien() {
        super.connectDB();
        String query = "SELECT * FROM nhanvien_username";
        ResultSet rs = super.executeQuery(query);

        return rs;
    }

    public ResultSet getCountNhanVien() {
        super.connectDB();
        String query = "SELECT COUNT(*) FROM NHAN_VIEN";
        ResultSet rs = super.executeQuery(query);

        return rs;
    }

    // Thay doi Nhan Vien dua tren id
    public void updateNVById(NhanVienDTO nhanvien) {
        super.connectDB();
        String query = "UPDATE NHAN_VIEN SET name = ?, phone = ?, gender = ?, birth = ?, email = ? WHERE id = ?;";
        Object[] params = { nhanvien.getName(), nhanvien.getPhone(), nhanvien.getGender(), nhanvien.getBirth(), nhanvien.getEmail(), nhanvien.getId() };
        super.executeNonQuery(query, params);

    }

    // Them du lieu vao Nhan Vien
    public void addNVWithData(NhanVienDTO nhanvien) {
        super.connectDB();
        String query = "INSERT INTO NHAN_VIEN (id, name, phone, gender, birth, email) VALUES (?, ?, ?, ?, ?, ?);";
        Object[] params = { nhanvien.getId(), nhanvien.getName(), nhanvien.getPhone(),
                nhanvien.getGender(), nhanvien.getBirth(), nhanvien.getEmail() };
        super.executeNonQuery(query, params);

    }

    // Xoa Nhan Vien bang id
    public void removeNVById(String id) {
        super.connectDB();
        String query = "UPDATE NHAN_VIEN SET isDelete = 1 WHERE id = ?;";
        Object[] params = { id };
        super.executeNonQuery(query, params);
    }
}
