package BUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;

public class HoaDonBUS {
    private LinkedHashSet<HoaDonDTO> setHD;
    private HoaDonDAO daoHD;

    public HoaDonBUS() {
        daoHD = new HoaDonDAO();
        setHD = new LinkedHashSet<>();

        setHD = HoaDonBUS.toSet(daoHD.getAllHoaDon());
    }

    public static LinkedHashSet<HoaDonDTO> toSet(ResultSet rs) {
        LinkedHashSet<HoaDonDTO> setHD = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                HoaDonDTO that = new HoaDonDTO(
                        rs.getString("id"),
                        rs.getTimestamp("ngaylap").toLocalDateTime(),
                        rs.getInt("order_amount"),
                        rs.getInt("discount_amount"),
                        rs.getString("status"),
                        rs.getString("ghichu"),
                        rs.getString("km"),
                        rs.getString("pttt"),
                        rs.getString("id_khachhang"),
                        rs.getString("id_nhanvien"));
                setHD.add(that);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setHD;
    }

    public HoaDonBUS(LinkedHashSet<HoaDonDTO> setHD, HoaDonDAO daoHD) {
        this.setHD = setHD;
        this.daoHD = daoHD;
    }

    public LinkedHashSet<HoaDonDTO> getSetHD() {
        return setHD;
    }

    public void setSetHD(LinkedHashSet<HoaDonDTO> setHD) {
        this.setHD = setHD;
    }

    public HoaDonDAO getDaoHD() {
        return daoHD;
    }

    public void setDaoHD(HoaDonDAO daoHD) {
        this.daoHD = daoHD;
    }
}
