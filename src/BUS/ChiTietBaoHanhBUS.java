package BUS;

import DAO.ChiTietBaoHanhDAO;
import DTO.ChiTietBaoHanhDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class ChiTietBaoHanhBUS {
    private LinkedHashSet<ChiTietBaoHanhDTO> setCTBH;
    private ChiTietBaoHanhDAO daoCTBH;

    public LinkedHashSet<ChiTietBaoHanhDTO> getSetCTBH() {
        return setCTBH;
    }

    public void setSetCTBH(LinkedHashSet<ChiTietBaoHanhDTO> setCTBH) {
        this.setCTBH = setCTBH;
    }

    public ChiTietBaoHanhDAO getDaoCTBH() {
        return daoCTBH;
    }

    public void setDaoCTBH(ChiTietBaoHanhDAO daoCTBH) {
        this.daoCTBH = daoCTBH;
    }


    public ChiTietBaoHanhBUS() {
        setCTBH = new LinkedHashSet<>();
        daoCTBH = new ChiTietBaoHanhDAO();

        setCTBH = toSet(daoCTBH.getAllCTBaoHanh());
        daoCTBH.closeDB();
    }

    public static ChiTietBaoHanhBUS getInstance() {
        return new ChiTietBaoHanhBUS();
    }

    public static LinkedHashSet<ChiTietBaoHanhDTO> toSet(ResultSet rs) {
        LinkedHashSet<ChiTietBaoHanhDTO> setCTBH = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                ChiTietBaoHanhDTO that = new ChiTietBaoHanhDTO(
                        rs.getString("id_bh"),
                        rs.getString("seri"),
                        rs.getString("color"),
                        rs.getString("id_sp"));
                setCTBH.add(that);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setCTBH;
    }

    public void addAllChiTietBaoHanh(ArrayList<ChiTietBaoHanhDTO> ctBaoHanh) {
        LinkedHashSet<ChiTietBaoHanhDTO> data = new LinkedHashSet<>(ctBaoHanh);
        if (setCTBH.addAll(data)) {
            for (ChiTietBaoHanhDTO ctbh : data) {
                daoCTBH.addCTBaoHanh(ctbh);
            }
            daoCTBH.closeDB();
        }
    }

    public LinkedHashSet<String> getAllIdSPInBH(String idBH) {
        return setCTBH.stream()
                .filter(chiTietBaoHanhDTO -> chiTietBaoHanhDTO.getIdBH().equals(idBH))
                .map(ChiTietBaoHanhDTO::getIdSP)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<String> getAllOptionOfIdSPInBH(String idBH, String idSP) {
        return setCTBH.stream()
                .filter(chiTietBaoHanhDTO -> chiTietBaoHanhDTO.getIdBH().equals(idBH)
                        && chiTietBaoHanhDTO.getIdSP().equals(idSP))
                .map(ChiTietBaoHanhDTO::getColor)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<ChiTietBaoHanhDTO> getListOSPHaveIdSPColorInBH(String idBH, String idSP, String option) {
        return setCTBH.stream()
                .filter(chiTietBaoHanhDTO -> chiTietBaoHanhDTO.getIdBH().equals(idBH)
                        && chiTietBaoHanhDTO.getIdSP().equals(idSP)
                        && chiTietBaoHanhDTO.getColor().equals(option))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public void removeAllChiTietBaoHanh(String idBaoHanh) {
        if (setCTBH.removeIf(chiTietBaoHanhDTO -> chiTietBaoHanhDTO.getIdBH().equals(idBaoHanh))) {
            daoCTBH.removeCTBaoHanh(idBaoHanh);
            daoCTBH.closeDB();
        }
    }
}
