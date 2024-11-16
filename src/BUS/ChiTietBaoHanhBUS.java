package BUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import DAO.ChiTietBaoHanhDAO;
import DTO.ChiTietBaoHanhDTO;

public class ChiTietBaoHanhBUS {
    private String idBaoHanh;
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

    public String getIdBaoHanh() {
        return idBaoHanh;
    }
    
    public ChiTietBaoHanhBUS(String idBaoHanh) {
        this.idBaoHanh = idBaoHanh;
        setCTBH = new LinkedHashSet<>();
        daoCTBH = new ChiTietBaoHanhDAO(idBaoHanh);

        setCTBH = toSet(daoCTBH.getAllCTBaoHanh());
        daoCTBH.closeDB();
    }

    public static LinkedHashSet<ChiTietBaoHanhDTO> toSet(ResultSet rs) {
        LinkedHashSet<ChiTietBaoHanhDTO> setCTBH = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                ChiTietBaoHanhDTO that = new ChiTietBaoHanhDTO(
                        rs.getString("id_bh"),
                        rs.getString("seri"));
                setCTBH.add(that);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setCTBH;
    }

    public LinkedHashSet<ChiTietBaoHanhDTO> getAllCTBaoHanh() {
        setSetCTBH(toSet(daoCTBH.getAllCTBaoHanh()));
        daoCTBH.closeDB();
        return getSetCTBH();
    }

    public int getCountCTBaoHanh() {
        ResultSet rs = daoCTBH.getCountCTBaoHanh();
        int count = -1;
        try {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        daoCTBH.closeDB();
        return count;
    }

    public void addCTBaoHanh(ChiTietBaoHanhDTO ctBaoHanh) {
        if (setCTBH.add(ctBaoHanh)) {
            daoCTBH.addCTBaoHanh(ctBaoHanh);
            daoCTBH.closeDB();
        }
    }

}
