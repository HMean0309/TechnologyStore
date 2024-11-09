package BUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import DAO.ChiTietHoaDonDAO;
import DTO.ChiTietHoaDonDTO;

public class ChiTietHoaDonBUS {
    private LinkedHashSet<ChiTietHoaDonDTO> setCTHD;
    private ChiTietHoaDonDAO daoCTHD;
    private String idHoaDon;

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public LinkedHashSet<ChiTietHoaDonDTO> getSetCTHD() {
        return setCTHD;
    }

    public void setSetCTHD(LinkedHashSet<ChiTietHoaDonDTO> setCTHD) {
        this.setCTHD = setCTHD;
    }

    public ChiTietHoaDonDAO getDaoCTHD() {
        return daoCTHD;
    }

    public void setDaoCTHD(ChiTietHoaDonDAO daoCTHD) {
        this.daoCTHD = daoCTHD;
    }

    public ChiTietHoaDonBUS(String idHoaDon) {
        this.idHoaDon = idHoaDon;
        setCTHD = new LinkedHashSet<>();
        daoCTHD = new ChiTietHoaDonDAO(idHoaDon);

        setCTHD = toSet(daoCTHD.getAllCTHoaDon());
    }

    public static LinkedHashSet<ChiTietHoaDonDTO> toSet(ResultSet rs) {
        LinkedHashSet<ChiTietHoaDonDTO> setCTHD = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                ChiTietHoaDonDTO that = new ChiTietHoaDonDTO(
                        rs.getString("id_hoadon"),
                        rs.getString("seri"),
                        rs.getInt("don_gia"));
                setCTHD.add(that);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setCTHD;
    }

    public LinkedHashSet<ChiTietHoaDonDTO> getAllCTHoaDon(){
        setSetCTHD(toSet(daoCTHD.getAllCTHoaDon()));
        return getSetCTHD();
    }

    public LinkedHashSet<ChiTietHoaDonDTO> getAllCTHoaDonOfSanPham(String idSanPham){
        setSetCTHD(toSet(daoCTHD.getAllCTHoaDonOfSanPham(idSanPham)));
        return getSetCTHD();
    }

    public int getCountCTHoaDon(){
        ResultSet rs = daoCTHD.getCountCTHoaDon();
        int count = -1;
        try {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getCountCTHoaDonOfSanPham(String idSanPham){
        ResultSet rs = daoCTHD.getCountCTHoaDonOfSanPham(idSanPham);
        int count = -1;
        try {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void addCTHoaDon(ChiTietHoaDonDTO ctHoaDon) {
        if (setCTHD.add(ctHoaDon)) {
            daoCTHD.addCTHoaDon(ctHoaDon);
        }
    }

    
}
