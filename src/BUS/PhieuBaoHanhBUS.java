package BUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;

import DAO.PhieuBaoHanhDAO;
import DTO.PhieuBaoHanhDTO;

public class PhieuBaoHanhBUS {
    private LinkedHashSet<PhieuBaoHanhDTO> setPBH;
    private PhieuBaoHanhDAO daoPBH;

    public LinkedHashSet<PhieuBaoHanhDTO> getSetPBH() {
        return setPBH;
    }

    public void setSetPBH(LinkedHashSet<PhieuBaoHanhDTO> setPBH) {
        this.setPBH = setPBH;
    }

    public PhieuBaoHanhDAO getDaoPBH() {
        return daoPBH;
    }

    public void setDaoPBH(PhieuBaoHanhDAO daoPBH) {
        this.daoPBH = daoPBH;
    }

    public PhieuBaoHanhBUS() {
        setPBH = new LinkedHashSet<>();
        daoPBH = new PhieuBaoHanhDAO();

        setPBH = toSet(daoPBH.getAllPhieuBaoHanh());
    }

    public static LinkedHashSet<PhieuBaoHanhDTO> toSet(ResultSet rs) {
        LinkedHashSet<PhieuBaoHanhDTO> setPBH = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                PhieuBaoHanhDTO that = new PhieuBaoHanhDTO(
                        rs.getString("id"),
                        rs.getTimestamp("ngaylap").toLocalDateTime(),
                        false,
                        rs.getString("id_nhanvien"),
                        rs.getString("id_hoadon"),
                        rs.getTimestamp("ngaytrahang").toLocalDateTime());
                setPBH.add(that);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setPBH;
    }

    public LinkedHashSet<PhieuBaoHanhDTO> getAllPhieuBaoHanh() {
        setSetPBH(toSet(daoPBH.getAllPhieuBaoHanh()));
        return getSetPBH();
    }

    public int getCountPhieuBaoHanh() {
        ResultSet rs = daoPBH.getCountPhieuBaoHanh();
        int count = -1;
        try {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void updateNgayTraHang(String id, LocalDateTime ngayTraHang) {
        boolean updateSuccess = setPBH.stream()
                .filter(baoHanh -> baoHanh.getId().equals(id))
                .findFirst()
                .map(baoHanh -> {
                    baoHanh.setNgayTraHang(ngayTraHang);
                    return true;
                })
                .orElse(false);

        if (updateSuccess) {
            daoPBH.updateNgayTraHang(id, ngayTraHang);
        }
    }

    public void addPhieuBaoHanh(PhieuBaoHanhDTO baoHanh) {
        if (setPBH.add(baoHanh)) {
            daoPBH.addPhieuBaoHanh(baoHanh);
        }
    }
}
