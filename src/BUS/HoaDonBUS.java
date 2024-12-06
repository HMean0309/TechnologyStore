package BUS;

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class HoaDonBUS {
    public static String[] typeFilter = {
            "Nhân viên lập phiếu", "Khách hàng",
            "Ngày lập từ", "Ngày lập đến",
            "Tổng tiền từ", "Tổng tiền đến"
    };
    public static String[] typeSearch = {
            "Mã hoá đơn"
    };
    private LinkedHashSet<HoaDonDTO> setHD;
    private HoaDonDAO daoHD;

    public HoaDonBUS() {
        daoHD = new HoaDonDAO();
        setHD = new LinkedHashSet<>();

        setHD = HoaDonBUS.toSet(daoHD.getAllHoaDon());
        daoHD.closeDB();
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
                        rs.getString("id_khachhang"),
                        rs.getString("id_nhanvien"),
                        rs.getString("name_khachhang"),
                        rs.getString("name_nhanvien"),
                        false);
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


    public int getCountHoaDon() {
        ResultSet rs = daoHD.getCountAllHoaDon();
        int count = -1;
        try {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        daoHD.closeDB();
        return count;
    }

    public String createID() {
        int index = getCountHoaDon() + 1;
        LocalDate datenow = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMuuuu"); // Use "uuuu" for the year
        String formattedDate = datenow.format(formatter);
        return String.format("HD%s%06d", formattedDate, index);
    }

    public void addHoaDon(HoaDonDTO hoadon) {
        if (setHD.add(hoadon)) {
            daoHD.addHoaDon(hoadon);
            daoHD.closeDB();
        }
    }

    public LinkedHashSet<HoaDonDTO> searchID(String content) {
        return setHD.stream()
                .filter(hoadonDTO -> hoadonDTO.getId().contains(content))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<HoaDonDTO> search(String searchContent, String searchType) {
        if (searchType.equals(typeSearch[0])) {
            LinkedHashSet<HoaDonDTO> setID = searchID(searchContent);


            return setID;
        }
        return getSetHD();
    }

    public LinkedHashSet<HoaDonDTO> filterNameNhanVien(String nameNV) {
        if (nameNV.equals("Tất cả")) {
            return setHD;
        }
        return setHD.stream()
                .filter(hoadonDTO -> hoadonDTO.getNameNhanVien().equals(nameNV))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<HoaDonDTO> filterNameKhachHang(String nameKH) {
        if (nameKH.equals("Tất cả")) {
            return setHD;
        }
        return setHD.stream()
                .filter(hoadonDTO -> hoadonDTO.getNameKhachHang().equals(nameKH))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<HoaDonDTO> filterFromNgayLap(LocalDate fromDate) {
        if (fromDate == null) {
            return setHD;
        }
        LocalDateTime fromDateTime = fromDate.atTime(0, 0, 0);
        return setHD.stream()
                .filter(hoadonDTO -> hoadonDTO.getNgayLap().isAfter(fromDateTime) || hoadonDTO.getNgayLap().equals(fromDateTime))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<HoaDonDTO> filterToNgayLap(LocalDate toDate) {
        if (toDate == null) {
            return setHD;
        }
        LocalDateTime toDateTime = toDate.atTime(23, 59, 59);
        return setHD.stream()
                .filter(hoadonDTO -> hoadonDTO.getNgayLap().isBefore(toDateTime) || hoadonDTO.getNgayLap().equals(toDateTime))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<HoaDonDTO> filterFromTotal(Integer fromTotal) {
        if (fromTotal == null) {
            return setHD;
        }
        return setHD.stream()
                .filter(hoadonDTO -> hoadonDTO.getTotal() >= fromTotal)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<HoaDonDTO> filterToTotal(Integer toTotal) {
        if (toTotal == null) {
            return setHD;
        }
        return setHD.stream()
                .filter(hoadonDTO -> hoadonDTO.getTotal() <= toTotal)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }


    public LinkedHashSet<HoaDonDTO> searchAndfilter(String searchContent, String searchType, Object[] filterContent) {
        LinkedHashSet<HoaDonDTO> searchResult = search(searchContent, searchType);

        LinkedHashSet<HoaDonDTO> setNameNv = filterNameNhanVien((String) filterContent[0]);
        LinkedHashSet<HoaDonDTO> setNameKhachHang = filterNameKhachHang((String) filterContent[1]);
        LinkedHashSet<HoaDonDTO> setFromNgayLap = filterFromNgayLap((LocalDate) filterContent[2]);
        LinkedHashSet<HoaDonDTO> setToNgayLap = filterToNgayLap((LocalDate) filterContent[3]);
        LinkedHashSet<HoaDonDTO> setFromTotal = filterFromTotal((Integer) filterContent[4]);
        LinkedHashSet<HoaDonDTO> setToTotal = filterToTotal((Integer) filterContent[5]);

        LinkedHashSet<HoaDonDTO> result = new LinkedHashSet<>(searchResult);
        result.retainAll(setNameNv);
        result.retainAll(setNameKhachHang);
        result.retainAll(setFromNgayLap);
        result.retainAll(setToNgayLap);
        result.retainAll(setFromTotal);
        result.retainAll(setToTotal);

        return result;
    }
}
