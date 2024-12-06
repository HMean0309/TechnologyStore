package DAO;

import DTO.ChiTietHoaDonDTO;

import java.sql.ResultSet;

public class ChiTietHoaDonDAO extends ObjectDAO {

    public ChiTietHoaDonDAO() {
        super();
    }

    public ResultSet getAllCTHoaDon() {
        super.connectDB();
        String query = "SELECT * FROM CT_HOA_DON";
        return super.executeQuery(query);
    }

    public ResultSet getCountCTHoaDon() {
        super.connectDB();
        String query = "SELECT COUNT(*) FROM CT_HOA_DON";
        return super.executeQuery(query);
    }

    public void addCTHoaDon(ChiTietHoaDonDTO ctHoaDon) {
        super.connectDB();
        String query = "INSERT INTO CT_HOA_DON (id_hoadon, seri ,don_gia) "
                + "VALUES(?,?,?)";
        Object[] params = { ctHoaDon.getIdHoaDon(), ctHoaDon.getSeri(), ctHoaDon.getDonGia() };
        super.executeNonQuery(query, params);

    }


}
