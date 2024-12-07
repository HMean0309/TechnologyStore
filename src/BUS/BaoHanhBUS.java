package BUS;

import DAO.BaoHanhDAO;
import DTO.BaoHanhDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class BaoHanhBUS {
    public static String[] typeSearch = {
            "Mã phiếu bảo hành"
    };
    public static String[] typeFilter = {
            "Nhân viên lập phiếu", "Khách Hàng",
            "Ngày lập từ", "Ngày lập đến",
            "Ngày trả hàng từ", "Ngày trả hàng đến",
    };
    private LinkedHashSet<BaoHanhDTO> setBH;
    private BaoHanhDAO daoBH;

    public LinkedHashSet<BaoHanhDTO> getSetBH() {
        return setBH;
    }

    public void setSetBH(LinkedHashSet<BaoHanhDTO> setBH) {
        this.setBH = setBH;
    }

    public BaoHanhDAO getDaoBH() {
        return daoBH;
    }

    public void setDaoBH(BaoHanhDAO daoBH) {
        this.daoBH = daoBH;
    }

    public BaoHanhBUS() {
        setBH = new LinkedHashSet<>();
        daoBH = new BaoHanhDAO();

        setBH = toSet(daoBH.getAllPhieuBaoHanh());
        daoBH.closeDB();
    }

    public static BaoHanhBUS getInstance() {
        return new BaoHanhBUS();
    }

    public static LinkedHashSet<BaoHanhDTO> toSet(ResultSet rs) {
        LinkedHashSet<BaoHanhDTO> setBH = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                BaoHanhDTO that = new BaoHanhDTO(
                        rs.getString("id"),
                        rs.getTimestamp("ngaybaohanh").toLocalDateTime(),
                        rs.getDate("ngaytrahang").toLocalDate(),
                        rs.getString("id_nhanvien"),
                        rs.getString("name_nhanvien"),
                        rs.getString("id_hoadon"),
                        rs.getString("id_khachhang"),
                        rs.getString("name_khachhang"),
                        false);
                setBH.add(that);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setBH;
    }

    public int getCountPhieuBaoHanh() {
        ResultSet rs = daoBH.getCountPhieuBaoHanh();
        int count = -1;
        try {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public String createID() {
        int index = getCountPhieuBaoHanh() + 1;
        return String.format("BH%06d", index);
    }

    public ArrayList<String> getAllIDHDUseBaoHanh() {
        return setBH.stream()
                .filter(baoHanhDTO -> !baoHanhDTO.getNgayTraHang().isBefore(LocalDate.now()))
                .map(BaoHanhDTO::getIdHoaDon)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void addPhieuBaoHanh(BaoHanhDTO baoHanh) {
        if (setBH.add(baoHanh)) {
            daoBH.addPhieuBaoHanh(baoHanh);
            daoBH.closeDB();
        }
    }

    public LinkedHashSet<BaoHanhDTO> searchID(String content) {
        return setBH.stream()
                .filter(baohanhDTO -> baohanhDTO.getId().contains(content))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<BaoHanhDTO> search(String searchContent, String searchType) {
        if (searchType.equals(typeSearch[0])) {
            LinkedHashSet<BaoHanhDTO> setID = searchID(searchContent);

            return setID;
        }
        return getSetBH();
    }

    public LinkedHashSet<BaoHanhDTO> filterIdNhanVien(String idNV) {
        if (idNV.equals("Tất cả")) {
            return setBH;
        }
        return setBH.stream()
                .filter(baohanhDTO -> baohanhDTO.getIdNhanVien().equals(idNV))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<BaoHanhDTO> filterIdKhachHang(String idKH) {
        if (idKH.equals("Tất cả")) {
            return setBH;
        }
        return setBH.stream()
                .filter(baohanhDTO -> baohanhDTO.getIdKhachHang().equals(idKH))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<BaoHanhDTO> filterFromNgayBaoHanh(LocalDate fromDate) {
        if (fromDate == null) {
            return setBH;
        }
        LocalDateTime fromDateTime = fromDate.atTime(0, 0, 0);
        return setBH.stream()
                .filter(baohanhDTO -> baohanhDTO.getNgayBaoHanh().isAfter(fromDateTime) || baohanhDTO.getNgayBaoHanh().equals(fromDateTime))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<BaoHanhDTO> filterToNgayBaoHanh(LocalDate toDate) {
        if (toDate == null) {
            return setBH;
        }
        LocalDateTime toDateTime = toDate.atTime(23, 59, 59);
        return setBH.stream()
                .filter(baohanhDTO -> baohanhDTO.getNgayBaoHanh().isBefore(toDateTime) || baohanhDTO.getNgayBaoHanh().equals(toDateTime))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<BaoHanhDTO> filterFromNgayTraHang(LocalDate fromDate) {
        if (fromDate == null) {
            return setBH;
        }
        return setBH.stream()
                .filter(baohanhDTO -> baohanhDTO.getNgayTraHang().isAfter(fromDate) || baohanhDTO.getNgayTraHang().equals(fromDate))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<BaoHanhDTO> filterToNgayTraHang(LocalDate toDate) {
        if (toDate == null) {
            return setBH;
        }
        return setBH.stream()
                .filter(baohanhDTO -> baohanhDTO.getNgayTraHang().isBefore(toDate) || baohanhDTO.getNgayTraHang().equals(toDate))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<BaoHanhDTO> searchAndfilter(String searchContent, String searchType, Object[] filterContent) {
        LinkedHashSet<BaoHanhDTO> searchResult = search(searchContent, searchType);

        LinkedHashSet<BaoHanhDTO> setNameNv = filterIdNhanVien((String) filterContent[0]);
        LinkedHashSet<BaoHanhDTO> setNameKhachHang = filterIdKhachHang((String) filterContent[1]);
        LinkedHashSet<BaoHanhDTO> setFromNgayBaoHanh = filterFromNgayBaoHanh((LocalDate) filterContent[2]);
        LinkedHashSet<BaoHanhDTO> setToNgayBaoHanh = filterToNgayBaoHanh((LocalDate) filterContent[3]);
        LinkedHashSet<BaoHanhDTO> setFromNgayTraHang = filterFromNgayTraHang((LocalDate) filterContent[4]);
        LinkedHashSet<BaoHanhDTO> setToNgayTraHang = filterToNgayTraHang((LocalDate) filterContent[5]);

        LinkedHashSet<BaoHanhDTO> result = new LinkedHashSet<>(searchResult);
        result.retainAll(setNameNv);
        result.retainAll(setNameKhachHang);
        result.retainAll(setFromNgayBaoHanh);
        result.retainAll(setToNgayBaoHanh);
        result.retainAll(setFromNgayTraHang);
        result.retainAll(setToNgayTraHang);


        return result;
    }
}
