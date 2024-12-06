package BUS;

import DAO.ChiTietHoaDonDAO;
import DTO.ChiTietHoaDonDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class ChiTietHoaDonBUS {
    private LinkedHashSet<ChiTietHoaDonDTO> setCTHD;
    private ChiTietHoaDonDAO daoCTHD;

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

    public ChiTietHoaDonBUS() {
        setCTHD = new LinkedHashSet<>();
        daoCTHD = new ChiTietHoaDonDAO();

        setCTHD = toSet(daoCTHD.getAllCTHoaDon());
        daoCTHD.closeDB();
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


    public int getCountCTHoaDon() {
        ResultSet rs = daoCTHD.getCountCTHoaDon();
        int count = -1;
        try {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        daoCTHD.closeDB();
        return count;
    }

    public void addAllChiTietHoaDon(ArrayList<ChiTietHoaDonDTO> listCTHD) {
        LinkedHashSet<ChiTietHoaDonDTO> setAllCTHD = new LinkedHashSet<>(listCTHD);
        if (setCTHD.addAll(setAllCTHD)) {
            for (ChiTietHoaDonDTO cthd : setAllCTHD) {
                daoCTHD.addCTHoaDon(cthd);
            }
            daoCTHD.closeDB();
        }
    }


}
