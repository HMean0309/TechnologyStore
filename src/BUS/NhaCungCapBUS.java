package BUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;

public class NhaCungCapBUS {
    private LinkedHashSet<NhaCungCapDTO> setNCC;
    private NhaCungCapDAO daoNCC;

    public NhaCungCapBUS() {
        daoNCC = new NhaCungCapDAO();
        setNCC = new LinkedHashSet<>();

        setNCC = NhaCungCapBUS.toSet(daoNCC.getAllNhaCungCap());
    }

    public static LinkedHashSet<NhaCungCapDTO> toSet(ResultSet rs) {
        LinkedHashSet<NhaCungCapDTO> setNCC = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                NhaCungCapDTO nhaCungCap = new NhaCungCapDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("ward"),
                        rs.getString("district"),
                        rs.getString("city"));
                setNCC.add(nhaCungCap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setNCC;
    }

    public NhaCungCapBUS(LinkedHashSet<NhaCungCapDTO> setNCC, NhaCungCapDAO daoNCC) {
        this.setNCC = setNCC;
        this.daoNCC = daoNCC;
    }

    public LinkedHashSet<NhaCungCapDTO> getSetNCC() {
        return setNCC;
    }

    public void setSetNCC(LinkedHashSet<NhaCungCapDTO> setNCC) {
        this.setNCC = setNCC;
    }

    public NhaCungCapDAO getDaoNCC() {
        return daoNCC;
    }

    public void setDaoNCC(NhaCungCapDAO daoNCC) {
        this.daoNCC = daoNCC;
    }

}
