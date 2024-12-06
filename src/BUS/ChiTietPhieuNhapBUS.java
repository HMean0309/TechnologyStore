package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DTO.ChiTietPhieuNhapDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class ChiTietPhieuNhapBUS {
    private LinkedHashSet<ChiTietPhieuNhapDTO> setCTPN;
    private ChiTietPhieuNhapDAO daoCTPN;

    public ChiTietPhieuNhapBUS() {
        daoCTPN = new ChiTietPhieuNhapDAO();
        setCTPN = new LinkedHashSet<>();
        setCTPN = ChiTietPhieuNhapBUS.toSet(daoCTPN.getAllChiTietPhieuNhap());
        daoCTPN.closeDB();
    }

    public static LinkedHashSet<ChiTietPhieuNhapDTO> toSet(ResultSet rs) {
        LinkedHashSet<ChiTietPhieuNhapDTO> setCTPN = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                ChiTietPhieuNhapDTO ctpn = new ChiTietPhieuNhapDTO(
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

    public void addAllChiTietPhieuNhap(ArrayList<ChiTietPhieuNhapDTO> listCTPN) {
        LinkedHashSet<ChiTietPhieuNhapDTO> setAllCTPN = new LinkedHashSet<>(listCTPN);
        if (setCTPN.addAll(setAllCTPN)) {
            for (ChiTietPhieuNhapDTO ctpn : setAllCTPN) {
                daoCTPN.addChiTietPhieuNhap(ctpn);
            }
            daoCTPN.closeDB();
        }
    }

    public ChiTietPhieuNhapBUS(LinkedHashSet<ChiTietPhieuNhapDTO> setCTPN, ChiTietPhieuNhapDAO daoCTPN) {
        this.setCTPN = setCTPN;
        this.daoCTPN = daoCTPN;
    }

    public LinkedHashSet<ChiTietPhieuNhapDTO> getSetCTPN() {
        return setCTPN;
    }

    public void setSetCTPN(LinkedHashSet<ChiTietPhieuNhapDTO> setCTPN) {
        this.setCTPN = setCTPN;
    }

    public ChiTietPhieuNhapDAO getDaoCTPN() {
        return daoCTPN;
    }

    public void setDaoCTPN(ChiTietPhieuNhapDAO daoCTPN) {
        this.daoCTPN = daoCTPN;
    }
}
