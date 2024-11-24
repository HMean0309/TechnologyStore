package DAO;

import DTO.TaiKhoanDTO;

import java.sql.ResultSet;

public class TaiKhoanDAO extends ObjectDAO {
    public TaiKhoanDAO() {
        super();
    }

    public static TaiKhoanDAO getInstance() {
        return new TaiKhoanDAO();
    }

    public ResultSet getAllTaiKhoan() {
        super.connectDB();
        String query = "SELECT * FROM taikhoan_email";
        ResultSet rs = super.executeQuery(query);

        return rs;
    }

    public ResultSet getCountTaiKhoan() {
        super.connectDB();
        String query = "SELECT COUNT(*) FROM TAI_KHOAN";
        ResultSet rs = super.executeQuery(query);

        return rs;
    }

    // Thay doi Tai kHoan dua tren id nhan vien
    public void updateTaiKhoan(TaiKhoanDTO taikhoan) {
        super.connectDB();
        String query = "UPDATE TAI_KHOAN SET id_quyen = ?, isDelete = ? WHERE username = ?;";
        Object[] params = {
                taikhoan.getIdQuyen(),
                taikhoan.getDelete(),
                taikhoan.getUsername()
        };
        super.executeNonQuery(query, params);

    }


    public void removeTK(String username) {
        super.connectDB();
        String query = "DELETE FROM TAI_KHOAN WHERE username = ?;";
        Object[] params = { username };
        super.executeNonQuery(query, params);

    }

    // Them du lieu vao Tai Khoan
    public void addTKWithData(TaiKhoanDTO taikhoan) {
        super.connectDB();
        String query = "INSERT INTO TAI_KHOAN (username, password, id_nhanvien, id_quyen) VALUES (?, ?, ?, ?);";
        Object[] params = {
                taikhoan.getUsername(),
                taikhoan.getPassword(),
                taikhoan.getIdNV(),
                taikhoan.getIdQuyen()
        };
        super.executeNonQuery(query, params);

    }

}
