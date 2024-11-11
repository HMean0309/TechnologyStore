package DAO;

import java.sql.ResultSet;

import DTO.ChiTietHoaDonDTO;

public class ChiTietHoaDonDAO extends ObjectDAO {
    private String idHoaDon;
    public ChiTietHoaDonDAO(String idHoaDon){
        super();
        this.idHoaDon = idHoaDon;
    }

    public ResultSet getAllCTHoaDon(){
        super.connectDB();
        String query = "SELECT * FROM CT_HOA_DON WHERE id_hoadon = ?";
        Object[] params = { idHoaDon };
        return super.executeQuery(query, params);
    }

    public ResultSet getCountCTHoaDon(){
        super.connectDB();
        String query = "SELECT COUNT(*) FROM CT_HOA_DON WHERE id_hoadon = ?";
        Object[] params = { idHoaDon };
        return super.executeQuery(query, params);
    }

    public ResultSet getAllCTHoaDonOfSanPham(String idSanPham){
        super.connectDB();
        String query = "SELECT * FROM CT_HOA_DON cthd LEFT JOIN CT_SAN_PHAM ctsp ON cthd.seri = ctsp.seri WHERE id_hoadon = ? and id_sp = ?";
        Object[] params = { idHoaDon, idSanPham };
        return super.executeQuery(query, params);
    }

    public ResultSet getCountCTHoaDonOfSanPham(String idSanPham){
        super.connectDB();
        String query = "SELECT COUNT(*) FROM CT_HOA_DON cthd LEFT JOIN CT_SAN_PHAM ctsp ON cthd.seri = ctsp.seri WHERE id_hoadon = ? and id_sp = ?";
        Object[] params = { idHoaDon, idSanPham };
        return super.executeQuery(query, params);
    }

    public void addCTHoaDon(ChiTietHoaDonDTO ctHoaDon){
        super.connectDB();
        String query = "INSERT INTO CT_HOA_DON (id_hoadon,seri,don_gia) "
                + "VALUES(?,?,?)";
        Object[] params = { ctHoaDon.getIdHoaDon(), ctHoaDon.getSeri(), ctHoaDon.getDonGia() };
        super.executeNonQuery(query, params);
        
    }


}
