package DAO;

import DTO.ChiTietPhieuNhapDTO;

import java.sql.ResultSet;

public class ChiTietPhieuNhapDAO extends ObjectDAO {
    public ChiTietPhieuNhapDAO() {
        super();
    }
    public static ChiTietPhieuNhapDAO getInstance() {
        return new ChiTietPhieuNhapDAO();
    }
    public ResultSet getAllChiTietPhieuNhap() {
        super.connectDB();
        String query = "SELECT * FROM ct_nhap_kho";
        ResultSet rs = super.executeQuery(query);

        return rs;
    }

    public ResultSet getCountChiTietPhieuNhap() {
        super.connectDB();
        String query = "SELECT COUNT(*) FROM ct_nhap_kho";
        ResultSet rs = super.executeQuery(query);

        return rs;
    }

    public void addChiTietPhieuNhap(ChiTietPhieuNhapDTO ctpn) {
        super.connectDB();
        String query = "INSERT INTO ct_nhap_kho (id_pn, seri, cost) VALUES (?, ?, ?)";
        Object[] params = { ctpn.getIdPN(), ctpn.getSeri(), ctpn.getCost() };
        super.executeNonQuery(query, params);

    }

}
