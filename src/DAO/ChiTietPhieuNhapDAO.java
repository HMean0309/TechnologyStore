package DAO;

import DTO.ChiTietNhapKhoDTO;
import java.sql.ResultSet;

public class ChiTietPhieuNhapDAO extends ObjectDAO {
    public ChiTietPhieuNhapDAO(){
        super();
    }
    
    public ResultSet getAllChiTietPhieuNhap() {
        String query = "SELECT * FROM ct_nhap_kho";
        return super.executeQuery(query);
    }
    public ResultSet getChiTietPhieuNhapWithId(String id_pn) {
        String query = "SELECT * FROM ct_nhap_kho WHERE id = ? ";
        Object[] params = {id_pn};
        return super.executeQuery(query, params);
    }
    
    public ResultSet getCountChiTietPhieuNhapWithId(String id_pn) {
        String query = "SELECT COUNT(*) FROM ct_nhap_kho WHERE id = ? ";
        Object[] params = {id_pn};
        return super.executeQuery(query, params);
    }
    
    public void addChiTietPhieuNhap(ChiTietNhapKhoDTO ctpn) {
        String query = "INSERT INTO ct_pro (id_pn, seri, cost) VALUES (?, ?, ?)";
        Object[] params = {ctpn.getIdPN(), ctpn.getSeri(), ctpn.getCost()};
        super.executeNonQuery(query, params);
    }

}
