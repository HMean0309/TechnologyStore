package BUS;

import DAO.OptionSanPhamDAO;
import DTO.OptionSanPhamDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class OptionSanPhamBUS {
    public static String[] duplicateMess = {
            "Màu đã tồn tại!"
    };
    private LinkedHashSet<OptionSanPhamDTO> setOSP;
    private OptionSanPhamDAO daoOSP;

    public OptionSanPhamBUS() {
        setOSP = new LinkedHashSet<>();
        daoOSP = new OptionSanPhamDAO();

        setOSP = OptionSanPhamBUS.toSet(daoOSP.getAllOSP());
        daoOSP.connectDB();
    }

    public static LinkedHashSet<OptionSanPhamDTO> toSet(ResultSet rs) {
        LinkedHashSet<OptionSanPhamDTO> setOSP = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                OptionSanPhamDTO that = new OptionSanPhamDTO(
                        rs.getString("id_sp"),
                        rs.getString("color"),
                        false);
                setOSP.add(that);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setOSP;
    }

    public OptionSanPhamBUS(LinkedHashSet<OptionSanPhamDTO> setOSP, OptionSanPhamDAO daoOSP) {
        this.setOSP = setOSP;
        this.daoOSP = daoOSP;
    }

    public LinkedHashSet<OptionSanPhamDTO> getSetOSP() {
        return setOSP;
    }

    public void setSetOSP(LinkedHashSet<OptionSanPhamDTO> setOSP) {
        this.setOSP = setOSP;
    }

    public OptionSanPhamDAO getDaoOSP() {
        return daoOSP;
    }

    public void setDaoOSP(OptionSanPhamDAO daoOSP) {
        this.daoOSP = daoOSP;
    }

    public int addOSP(OptionSanPhamDTO option) {
        if (setOSP.add(option)) {
            daoOSP.addOSPWithData(option);
            daoOSP.closeDB();
            return -1;
        }
        return 0;
    }

    public void removeOSP(OptionSanPhamDTO option) {
        if (setOSP.remove(option)) {
            daoOSP.removeOSP(option);
            daoOSP.closeDB();
        }
    }

    public LinkedHashSet<OptionSanPhamDTO> getAllOSPByIDSP(String idSP) {
        return setOSP.stream()
                .filter(optionSanPhamDTO -> optionSanPhamDTO.getIdSP().equals(idSP))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
