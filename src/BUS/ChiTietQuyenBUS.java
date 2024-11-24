package BUS;

import DAO.ChiTietQuyenDAO;
import DTO.ChiTietQuyenDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class ChiTietQuyenBUS {
    private LinkedHashSet<ChiTietQuyenDTO> setCTQuyen;
    private ChiTietQuyenDAO daoCTQuyen;

    public ChiTietQuyenBUS() {
        setCTQuyen = new LinkedHashSet<>();
        daoCTQuyen = new ChiTietQuyenDAO();

        setCTQuyen = ChiTietQuyenBUS.toSet(daoCTQuyen.getAllChiTietQuyen());
        daoCTQuyen.closeDB();
    }

    public static ChiTietQuyenBUS getInstance() {
        return new ChiTietQuyenBUS();
    }

    public static LinkedHashSet<ChiTietQuyenDTO> toSet(ResultSet rs) {
        LinkedHashSet<ChiTietQuyenDTO> setCtQuyen = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                ChiTietQuyenDTO that = new ChiTietQuyenDTO(
                        rs.getString("id_quyen"),
                        rs.getString("id_chucnang"),
                        rs.getString("permission")
                );
                setCtQuyen.add(that);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setCtQuyen;
    }

    public ChiTietQuyenBUS(LinkedHashSet<ChiTietQuyenDTO> setCTQuyen, ChiTietQuyenDAO daoCTQuyen) {
        this.setCTQuyen = setCTQuyen;
        this.daoCTQuyen = daoCTQuyen;
    }

    public LinkedHashSet<ChiTietQuyenDTO> getSetChiTietQuyen() {
        return setCTQuyen;
    }

    public void setSetChiTietQuyen(LinkedHashSet<ChiTietQuyenDTO> setCtQuyen) {
        this.setCTQuyen = setCtQuyen;
    }

    public ChiTietQuyenDAO getDaoChiTietQuyen() {
        return daoCTQuyen;
    }

    public void setDaoChiTietQuyen(ChiTietQuyenDAO daoCtQuyen) {
        this.daoCTQuyen = daoCtQuyen;
    }

    public int getCountChiTietQuyen() {
        ResultSet rs = daoCTQuyen.getCountChiTietQuyen();
        int count = -1;
        try {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void addChiTietQuyen(ChiTietQuyenDTO ctQuyen) {
        if (setCTQuyen.add(ctQuyen)) {
            daoCTQuyen.addChiTietQuyenWithData(ctQuyen);
            daoCTQuyen.closeDB();
        }
    }

    public void removeChiTietQuyen(ChiTietQuyenDTO ctQuyen) {
        if (setCTQuyen.remove(ctQuyen)) {
            daoCTQuyen.removeChiTietQuyen(ctQuyen.getIdQuyen());
            daoCTQuyen.closeDB();
        }
    }

    public boolean checkPermission(ChiTietQuyenDTO ctQuyen) {
        return setCTQuyen.contains(ctQuyen);
    }

    public LinkedHashSet<ChiTietQuyenDTO> getAllCTQuyen(String idQuyen) {
        LinkedHashSet<ChiTietQuyenDTO> result = setCTQuyen.stream()
                .filter(ctq -> ctq.getIdQuyen().equals(idQuyen))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        
        return result.isEmpty() ? null : result;
    }
}
