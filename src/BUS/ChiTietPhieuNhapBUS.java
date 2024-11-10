package BUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import DAO.ChiTietPhieuNhapDAO;
import DTO.ChiTietNhapKhoDTO;

public class ChiTietPhieuNhapBUS {
    private LinkedHashSet<ChiTietNhapKhoDTO> setCTPN;
    private ChiTietPhieuNhapDAO daoCTPN;

    public ChiTietPhieuNhapBUS() {
        daoCTPN = new ChiTietPhieuNhapDAO();
        setCTPN = new LinkedHashSet<>();
        setCTPN = ChiTietPhieuNhapBUS.toSet(daoCTPN.getAllChiTietPhieuNhap());
    }

    public static LinkedHashSet<ChiTietNhapKhoDTO> toSet(ResultSet rs) {
        LinkedHashSet<ChiTietNhapKhoDTO> setCTPN = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                ChiTietNhapKhoDTO ctpn = new ChiTietNhapKhoDTO(
                        rs.getString("id_pn"),
                        rs.getString("seri"),
                        rs.getInt("cost"));
                setCTPN.add(ctpn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setCTPN;
    }
    
    public void addChiTietPhieuNhap(ChiTietNhapKhoDTO ctpn){
        daoCTPN.addChiTietPhieuNhap(ctpn);
    }
    
    public ChiTietPhieuNhapBUS(LinkedHashSet<ChiTietNhapKhoDTO> setCTPN, ChiTietPhieuNhapDAO daoCTPN) {
        this.setCTPN = setCTPN;
        this.daoCTPN = daoCTPN;
    }

    public LinkedHashSet<ChiTietNhapKhoDTO> getSetCTPN() {
        return setCTPN;
    }

    public void setSetCTPN(LinkedHashSet<ChiTietNhapKhoDTO> setCTPN) {
        this.setCTPN = setCTPN;
    }

    public ChiTietPhieuNhapDAO getDaoCTPN() {
        return daoCTPN;
    }

    public void setDaoCTPN(ChiTietPhieuNhapDAO daoCTPN) {
        this.daoCTPN = daoCTPN;
    }
}
