package BUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;

public class KhachHangBUS {
    private LinkedHashSet<KhachHangDTO> setKH;
    private KhachHangDAO daoKH;

    public LinkedHashSet<KhachHangDTO> getSetKH() {
        return setKH;
    }

    public void setSetKH(LinkedHashSet<KhachHangDTO> setKH) {
        this.setKH = setKH;
    }

    public KhachHangDAO getDaoKH() {
        return daoKH;
    }

    public void setDaoKH(KhachHangDAO daoKH) {
        this.daoKH = daoKH;
    }

    public KhachHangBUS() {
        setKH = new LinkedHashSet<>();
        daoKH = new KhachHangDAO();

        setKH = toSet(daoKH.getAllKhachHang());
    }

    public static LinkedHashSet<KhachHangDTO> toSet(ResultSet rs) {
        LinkedHashSet<KhachHangDTO> setKH = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                KhachHangDTO that = new KhachHangDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("district"),
                        rs.getString("ward"),
                        rs.getString("city"),
                        false);
                setKH.add(that);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setKH;
    }

    public LinkedHashSet<KhachHangDTO> getAllKhachHang() {
        setSetKH(toSet(daoKH.getAllKhachHang()));
        return getSetKH();
    }

    public int getCountKhachHang() {
        ResultSet rs = daoKH.getCountKhachHang();
        int count = -1;
        try {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void updateKhachHang(KhachHangDTO kh) {
        boolean updateSuccess = setKH.stream()
                .filter(khachHang -> khachHang.getId().equals(kh.getId()))
                .findFirst()
                .map(khachHang -> {
                    khachHang.setName(kh.getName());
                    khachHang.setPhone(kh.getPhone());
                    khachHang.setAddress(kh.getAddress());
                    khachHang.setDistrict(kh.getDistrict());
                    khachHang.setWard(kh.getWard());
                    khachHang.setCity(kh.getCity());
                    return true;
                })
                .orElse(false);

        if (updateSuccess) {
            daoKH.addKhachHang(kh);
        }
    }

    public void addKhachHang(KhachHangDTO kh) {
        if (setKH.add(kh)) {
            daoKH.addKhachHang(kh);
        }
    }
}
