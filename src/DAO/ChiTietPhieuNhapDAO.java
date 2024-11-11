package DAO;

import DTO.ChiTietNhapKhoDTO;
import java.sql.ResultSet;

public class ChiTietPhieuNhapDAO extends ObjectDAO {
    public ChiTietPhieuNhapDAO(){
        super();
    }
    
    public ResultSet getAllChiTietPhieuNhap() {
        super.connectDB();
        String query = "SELECT * FROM ct_nhap_kho";
        ResultSet rs = super.executeQuery(query);
        super.closeDB();
        return rs;
    }
    public ResultSet getChiTietPhieuNhapWithId(String id_pn) {
        super.connectDB();
        String query = "SELECT * FROM ct_nhap_kho WHERE id = ? ";
        Object[] params = {id_pn};
        ResultSet rs = super.executeQuery(query, params);
        super.closeDB();
        return rs;
    }
    
    public ResultSet getCountChiTietPhieuNhap() {
        super.connectDB();
        String query = "SELECT COUNT(*) FROM ct_nhap_kho";
        ResultSet rs = super.executeQuery(query);
        super.closeDB();
        return rs;
    }
    
    public void addChiTietPhieuNhap(ChiTietNhapKhoDTO ctpn) {
        super.connectDB();
        String query = "INSERT INTO ct_pro (id_pn, seri, cost) VALUES (?, ?, ?)";
        Object[] params = {ctpn.getIdPN(), ctpn.getSeri(), ctpn.getCost()};
        super.executeNonQuery(query, params);
        super.closeDB();
    }

}
