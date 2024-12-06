package DAO;

import DTO.KhachHangDTO;

import java.sql.ResultSet;

public class KhachHangDAO extends ObjectDAO {
    public KhachHangDAO() {
        super();
    }

    public ResultSet getAllKhachHang() {
        super.connectDB();
        String query = "SELECT * FROM KHACH_HANG WHERE isDelete = 0";
        ResultSet rs = super.executeQuery(query);
        return rs;
    }

    public ResultSet getCountKhachHang() {
        super.connectDB();
        String query = "SELECT COUNT(*) FROM KHACH_HANG";
        ResultSet rs = super.executeQuery(query);
        return rs;
    }

    public void updateKhachHang(KhachHangDTO kh) {
        super.connectDB();
        String query = "UPDATE KHACH_HANG SET name = ?, phone = ?, address = ?, district = ?, ward = ?, city = ? WHERE id = ?";
        Object[] params = { kh.getName(), kh.getPhone(), kh.getAddress(),
                kh.getDistrict(), kh.getWard(), kh.getCity(), kh.getId() };
        super.executeNonQuery(query, params);
    }

    public void addKhachHang(KhachHangDTO kh) {
        super.connectDB();
        String query = "INSERT INTO KHACH_HANG (id, name, phone, address, district, ward, city) "
                + "VALUES(?,?,?,?,?,?,?)";
        Object[] params = { kh.getId(), kh.getName(), kh.getPhone(), kh.getAddress(),
                kh.getDistrict(), kh.getWard(), kh.getCity() };
        super.executeNonQuery(query, params);
    }

    public void removeKhachHang(String id) {
        super.connectDB();
        String query = "UPDATE KHACH_HANG SET isDelete = 1 WHERE id = ?";
        Object[] params = { id };
        super.executeNonQuery(query, params);
    }

}
