package BUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import DAO.PhieuNhapDAO;
import DTO.PhieuNhapKhoDTO;

public class PhieuNhapBUS {
    private LinkedHashSet<PhieuNhapKhoDTO> setPN;
    private PhieuNhapDAO daoPN;

    public PhieuNhapBUS() {
        daoPN = new PhieuNhapDAO();
        setPN = new LinkedHashSet<>();

        setPN = PhieuNhapBUS.toSet(daoPN.getAllPhieuNhap());
    }

    public static LinkedHashSet<PhieuNhapKhoDTO> toSet(ResultSet rs) {
        LinkedHashSet<PhieuNhapKhoDTO> setPN = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                PhieuNhapKhoDTO phieuNhap = new PhieuNhapKhoDTO(
                        rs.getString("id"),
                        rs.getTimestamp("ngaynhap").toLocalDateTime(),
                        rs.getInt("total"),
                        rs.getString("id_ncc"),
                        rs.getString("id_nhanvien"));
                setPN.add(phieuNhap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setPN;
    }
    
    public LinkedHashSet<PhieuNhapKhoDTO> getSetPN() {
        return setPN;
    }

    public void setSetPN(LinkedHashSet<PhieuNhapKhoDTO> setPN) {
        this.setPN = setPN;
    }

    public PhieuNhapDAO getDaoPN() {
        return daoPN;
    }

    public void setDaoPN(PhieuNhapDAO daoPN) {
        this.daoPN = daoPN;
    }
}
