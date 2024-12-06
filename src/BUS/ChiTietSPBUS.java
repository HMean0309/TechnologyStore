package BUS;

import DAO.ChiTietSPDAO;
import DTO.ChiTietSanPhamDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.stream.Collectors;

public class ChiTietSPBUS {
    private LinkedHashSet<ChiTietSanPhamDTO> setCTSP;
    private ChiTietSPDAO daoCTSP;

    public ChiTietSPBUS() {
        setCTSP = new LinkedHashSet<>();
        daoCTSP = new ChiTietSPDAO();

        setCTSP = ChiTietSPBUS.toSet(daoCTSP.getAllCTSP());
        daoCTSP.closeDB();
    }

    public static ChiTietSPBUS getInstance() {
        return new ChiTietSPBUS();
    }

    public static LinkedHashSet<ChiTietSanPhamDTO> toSet(ResultSet rs) {
        LinkedHashSet<ChiTietSanPhamDTO> setPL = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                ChiTietSanPhamDTO that = new ChiTietSanPhamDTO(
                        rs.getString("seri"),
                        rs.getString("id_sp"),
                        rs.getString("color"),
                        rs.getInt("price"),
                        rs.getInt("cost"),
                        rs.getString("id_pn"),
                        rs.getString("id_hoadon"),
                        false);
                setPL.add(that);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setPL;
    }

    public ChiTietSPBUS(LinkedHashSet<ChiTietSanPhamDTO> setCTSP, ChiTietSPDAO daoCTSP) {
        this.setCTSP = setCTSP;
        this.daoCTSP = daoCTSP;
    }

    public LinkedHashSet<ChiTietSanPhamDTO> getSetCTSP() {
        return setCTSP;
    }

    public void setSetCTSP(LinkedHashSet<ChiTietSanPhamDTO> setCTSP) {
        this.setCTSP = setCTSP;
    }

    public ChiTietSPDAO getDaoCTSP() {
        return daoCTSP;
    }

    public void setDaoCTSP(ChiTietSPDAO daoCTSP) {
        this.daoCTSP = daoCTSP;
    }

    public int getAllCountCTSPByIdSP(String idSP) {
        ResultSet rs = daoCTSP.getAllCountCTSPByIdSP(idSP);
        int count = -1;
        try {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public String createSeri(String idSP) {
        int index = getAllCountCTSPByIdSP(idSP) + 1;
        String indexSP = idSP.replace("PRODUCT", "");
        return String.format("SERI%s%05d", indexSP, index);
    }

    public ArrayList<String> createListSeri(String idSP, int startIndex, int size) {
        int count = getAllCountCTSPByIdSP(idSP);
        String indexSP = idSP.replace("PRODUCT", "");
        ArrayList<String> listID = new ArrayList<>(size);
        for (int i = 1; i <= size; i++) {
            listID.add(String.format("SERI%s%05d", indexSP, count + startIndex + i));
        }
        return listID;
    }

    public ArrayList<ChiTietSanPhamDTO> getSeriByColorSP(String idSP, String color) {
        return setCTSP.stream()
                .filter(ctsp -> ctsp.getIdSP().equals(idSP) && ctsp.getColor().equals(color) && ctsp.getIdHoaDon() == null)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void addAllOSP(ArrayList<ChiTietSanPhamDTO> series) {
        LinkedHashSet<ChiTietSanPhamDTO> seriSet = new LinkedHashSet<>(series);
        if (setCTSP.addAll(seriSet)) {
            for (ChiTietSanPhamDTO ctsp : seriSet) {
                daoCTSP.addCTSPWithData(ctsp);
            }
            daoCTSP.closeDB();
        }
    }

    public LinkedHashSet<String> getAllIdSPInPN(String idPN) {
        return setCTSP.stream()
                .filter(chiTietSanPhamDTO -> chiTietSanPhamDTO.getIdPhieuNhap().equals(idPN))
                .map(ChiTietSanPhamDTO::getIdSP)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<String> getAllOptionOfIdSPInPN(String idPN, String idSP) {
        return setCTSP.stream()
                .filter(chiTietSanPhamDTO -> chiTietSanPhamDTO.getIdPhieuNhap().equals(idPN)
                        && chiTietSanPhamDTO.getIdSP().equals(idSP))
                .map(ChiTietSanPhamDTO::getColor)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<ChiTietSanPhamDTO> getListOSPHaveIdSPColorInPN(String idPN, String idSP, String color) {
        return setCTSP.stream()
                .filter(chiTietSanPhamDTO -> chiTietSanPhamDTO.getIdPhieuNhap().equals(idPN)
                        && chiTietSanPhamDTO.getIdSP().equals(idSP)
                        && chiTietSanPhamDTO.getColor().equals(color))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<String> getAllIdSPInHD(String idHD) {
        return setCTSP.stream()
                .filter(chiTietSanPhamDTO -> Objects.equals(chiTietSanPhamDTO.getIdHoaDon(), idHD))
                .map(ChiTietSanPhamDTO::getIdSP)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<String> getAllOptionOfIdSPInHD(String idHD, String idSP) {
        return setCTSP.stream()
                .filter(chiTietSanPhamDTO -> Objects.equals(chiTietSanPhamDTO.getIdHoaDon(), idHD)
                        && chiTietSanPhamDTO.getIdSP().equals(idSP))
                .map(ChiTietSanPhamDTO::getColor)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<ChiTietSanPhamDTO> getListOSPHaveIdSPColorInHD(String idHD, String idSP, String color) {
        return setCTSP.stream()
                .filter(chiTietSanPhamDTO -> Objects.equals(chiTietSanPhamDTO.getIdHoaDon(), idHD)
                        && chiTietSanPhamDTO.getIdSP().equals(idSP)
                        && chiTietSanPhamDTO.getColor().equals(color))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static void main(String[] args) {

    }
}
