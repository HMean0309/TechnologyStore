package BUS;

import DAO.ChucNangDAO;
import DTO.ChucNangDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

public class ChucNangBUS {
    private ChucNangDAO daoChucNang;
    private LinkedHashSet<ChucNangDTO> setChucNang;

    public ChucNangDAO getDaoChucNang() {
        return daoChucNang;
    }

    public void setDaoChucNang(ChucNangDAO daoChucNang) {
        this.daoChucNang = daoChucNang;
    }

    public LinkedHashSet<ChucNangDTO> getSetChucNang() {
        return setChucNang;
    }

    public void setSetChucNang(LinkedHashSet<ChucNangDTO> setChucNang) {
        this.setChucNang = setChucNang;
    }

    public ChucNangBUS() {
        setChucNang = new LinkedHashSet<>();
        daoChucNang = new ChucNangDAO();

        setChucNang = toSet(daoChucNang.getAllChucNang());
        daoChucNang.connectDB();
    }

    public static ChucNangBUS getInstance() {
        return new ChucNangBUS();
    }

    public static LinkedHashSet<ChucNangDTO> toSet(ResultSet rs) {
        LinkedHashSet<ChucNangDTO> setChucNang = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                ChucNangDTO that = new ChucNangDTO(
                        rs.getString("id"),
                        rs.getString("name"));
                setChucNang.add(that);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setChucNang;
    }
}
