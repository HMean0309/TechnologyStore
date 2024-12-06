package DAO;

import DTO.ChiTietBaoHanhDTO;

import java.sql.ResultSet;

public class ChiTietBaoHanhDAO extends ObjectDAO {
    public ChiTietBaoHanhDAO() {
        super();
    }

    public ResultSet getAllCTBaoHanh() {
        super.connectDB();
        String query = "SELECT * FROM chitietbaohanh";
        ResultSet rs = super.executeQuery(query);

        return rs;
    }

    public void addCTBaoHanh(ChiTietBaoHanhDTO ctBaoHanh) {
        super.connectDB();
        String query = "INSERT INTO CT_BAO_HANH (id_bh, seri)"
                + "VALUES(?,?)";
        Object[] params = { ctBaoHanh.getIdBH(), ctBaoHanh.getSeri() };
        super.executeNonQuery(query, params);

    }
}
