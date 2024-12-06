package DAO;

import DTO.ChiTietSanPhamDTO;

import java.sql.ResultSet;

public class ChiTietSPDAO extends ObjectDAO {
    public ChiTietSPDAO() {
        super();
    }

    public ResultSet getAllCTSP() {
        super.connectDB();
        String query = "SELECT * FROM chitietsanpham where isDelete = 0;";

        return super.executeQuery(query);
    }

    public ResultSet getAllCountCTSPByIdSP(String idSP) {
        super.connectDB();
        String query = "SELECT COUNT(*) FROM CT_SAN_PHAM where id_sp = ?";
        Object[] params = { idSP };

        return super.executeQuery(query, params);
    }

    // Them du lieu vao Phan Loai
    public void addCTSPWithData(ChiTietSanPhamDTO ctsp) {
        super.connectDB();
        String query = "INSERT INTO CT_SAN_PHAM (seri, id_sp, color, price)" + "VALUES(?,?,?,?)";
        Object[] params = { ctsp.getSeri(), ctsp.getIdSP(), ctsp.getColor(), ctsp.getPrice() };

        super.executeNonQuery(query, params);
    }

    public void removeCTSPBySeri(String seri) {
        super.connectDB();
        String query = "UPDATE CT_SAN_PHAM SET isDelete = 1 WHERE seri = ? ;";
        Object[] params = { seri };

        super.executeNonQuery(query, params);
    }

}