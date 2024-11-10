package DAO;

import java.sql.ResultSet;

import DTO.ChiTietBaoHanhDTO;

public class ChiTietBaoHanhDAO extends ObjectDAO {
    private String idBaoHanh;
    public ChiTietBaoHanhDAO(String idBaoHanh) {
        super();
        this.idBaoHanh = idBaoHanh;
    }

    public ResultSet getAllCTBaoHanh() {
        super.connectDB();
        String query = "SELECT * FROM CT_BAO_HANH WHERE id_bh = ?";
        Object[] params = { idBaoHanh };
        ResultSet rs = super.executeQuery(query, params);
        
        return rs;
    }

    public ResultSet getCountCTBaoHanh() {
        super.connectDB();
        String query = "SELECT COUNT(*) FROM CT_BAO_HANH WHERE id_bh = ?";
        Object[] params = { idBaoHanh };
        ResultSet rs = super.executeQuery(query, params);
        
        return rs;
    }

    public void addCTBaoHanh(ChiTietBaoHanhDTO ctBaoHanh) {
        super.connectDB();
        String query = "INSERT INTO CT_BAO_HANH (id_bh, seri)"
                + "VALUES(?,?)";
        Object[] params = {ctBaoHanh.getIdBH(), ctBaoHanh.getSeri()};
        super.executeNonQuery(query, params);
        
    }
}
