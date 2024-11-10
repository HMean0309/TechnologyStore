package DAO;

import java.sql.ResultSet;

import DTO.KhachHangDTO;

public class KhachHangDAO extends ObjectDAO {
    public KhachHangDAO() {
        super();
    }

    public ResultSet getAllKhachHang() {
        super.connectDB();
        String query = "SELECT * FROM KHACH_HANG WHERE isDelete = 0";
        ResultSet rs = super.executeQuery(query);
        super.closeDB();
        return rs;
    }

    public ResultSet getCountKhachHang() {
        super.connectDB();
        String query = "SELECT COUNT(*) FROM KHACH_HANG WHERE isDelete = 0";
        ResultSet rs = super.executeQuery(query);
        super.closeDB();
        return rs;
    }

    public void updateKhachHang(KhachHangDTO kh) {
        super.connectDB();
        String query = "UPDATE KHACH_HANG SET name = ?, phone = ?, address = ?, district = ?, ward = ?, city = ? WHERE id = ?";
        Object[] params = { kh.getName(), kh.getPhone(), kh.getAddress(),
                kh.getDistrict(), kh.getWard(), kh.getCity(), kh.getId() };
        super.executeNonQuery(query, params);
        super.closeDB();
    }

    public void addKhachHang(KhachHangDTO kh) {
        super.connectDB();
        String query = "INSERT INTO KHACH_HANG (id, name, phone, address, district, ward, city) "
                + "VALUES(?,?,?,?,?,?,?)";
        Object[] params = { kh.getId(), kh.getName(), kh.getPhone(), kh.getAddress(),
                kh.getDistrict(), kh.getWard(), kh.getCity() };
        super.executeNonQuery(query, params);
        super.closeDB();
    }

}
