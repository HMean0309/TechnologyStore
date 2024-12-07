package BUS;

import DAO.PhieuNhapDAO;
import DTO.PhieuNhapDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class PhieuNhapBUS {
    public static String[] typeSearch = {
            "Mã Phiếu Nhập"
    };
    public static String[] typeFilter = {
            "Nhân viên lập phiếu", "Nhà cung cấp",
            "Ngày nhập từ", "Ngày nhập đến",
            "Tổng tiền từ", "Tổng tiền đến"
    };
    private LinkedHashSet<PhieuNhapDTO> setPN;
    private PhieuNhapDAO daoPN;

    public PhieuNhapBUS() {
        daoPN = new PhieuNhapDAO();
        setPN = new LinkedHashSet<>();

        setPN = PhieuNhapBUS.toSet(daoPN.getAllPhieuNhap());
        daoPN.closeDB();
    }

    public static LinkedHashSet<PhieuNhapDTO> toSet(ResultSet rs) {
        LinkedHashSet<PhieuNhapDTO> setPN = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                PhieuNhapDTO phieuNhap = new PhieuNhapDTO(
                        rs.getString("id"),
                        rs.getTimestamp("ngaynhap").toLocalDateTime(),
                        rs.getInt("total"),
                        rs.getString("id_nhanvien"),
                        rs.getString("id_ncc"),
                        rs.getString("name_nhanvien"),
                        rs.getString("name_ncc"),
                        false);
                setPN.add(phieuNhap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setPN;
    }

    public void addPhieuNhap(PhieuNhapDTO phieuNhap) {
        if (setPN.add(phieuNhap)) {
            daoPN.addPhieuNhap(phieuNhap);
            daoPN.closeDB();
        }
    }

    public PhieuNhapBUS(LinkedHashSet<PhieuNhapDTO> setPN, PhieuNhapDAO daoPN) {
        this.setPN = setPN;
        this.daoPN = daoPN;
    }

    public LinkedHashSet<PhieuNhapDTO> getSetPN() {
        return setPN;
    }

    public void setSetPN(LinkedHashSet<PhieuNhapDTO> setPN) {
        this.setPN = setPN;
    }

    public PhieuNhapDAO getDaoPN() {
        return daoPN;
    }

    public void setDaoPN(PhieuNhapDAO daoPN) {
        this.daoPN = daoPN;
    }

    public int getCountAllPN() {
        ResultSet rs = daoPN.getCountPhieuNhap();
        int count = -1;
        try {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        daoPN.closeDB();
        return count;
    }

    public String createID() {
        int index = getCountAllPN() + 1;
        return String.format("PN%06d", index);
    }

    public LinkedHashSet<PhieuNhapDTO> searchID(String content) {
        return setPN.stream()
                .filter(phieuNhapDTO -> phieuNhapDTO.getId().contains(content))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<PhieuNhapDTO> search(String searchContent, String searchType) {
        if (searchType.equals(typeSearch[0])) {
            LinkedHashSet<PhieuNhapDTO> setID = searchID(searchContent);


            return setID;
        }
        return getSetPN();
    }

    public LinkedHashSet<PhieuNhapDTO> filterIdNhanVien(String idNV) {
        if (idNV.equals("Tất cả")) {
            return setPN;
        }
        return setPN.stream()
                .filter(phieuNhapDTO -> phieuNhapDTO.getIdNhanVien().equals(idNV))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<PhieuNhapDTO> filterIdNCC(String idNCC) {
        if (idNCC.equals("Tất cả")) {
            return setPN;
        }
        return setPN.stream()
                .filter(phieuNhapDTO -> phieuNhapDTO.getIdNCC().equals(idNCC))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<PhieuNhapDTO> filterFromNgayNhap(LocalDate fromDate) {
        if (fromDate == null) {
            return setPN;
        }
        LocalDateTime fromDateTime = fromDate.atTime(0, 0, 0);
        return setPN.stream()
                .filter(phieuNhapDTO -> phieuNhapDTO.getNgayNhap().isAfter(fromDateTime) || phieuNhapDTO.getNgayNhap().equals(fromDateTime))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<PhieuNhapDTO> filterToNgayNhap(LocalDate toDate) {
        if (toDate == null) {
            return setPN;
        }
        LocalDateTime toDateTime = toDate.atTime(23, 59, 59);
        return setPN.stream()
                .filter(phieuNhapDTO -> phieuNhapDTO.getNgayNhap().isBefore(toDateTime) || phieuNhapDTO.getNgayNhap().equals(toDateTime))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<PhieuNhapDTO> filterFromTotal(Integer fromTotal) {
        if (fromTotal == null) {
            return setPN;
        }
        return setPN.stream()
                .filter(phieuNhapDTO -> phieuNhapDTO.getTotal() >= fromTotal)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<PhieuNhapDTO> filterToTotal(Integer toTotal) {
        if (toTotal == null) {
            return setPN;
        }
        return setPN.stream()
                .filter(phieuNhapDTO -> phieuNhapDTO.getTotal() <= toTotal)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }


    public LinkedHashSet<PhieuNhapDTO> searchAndfilter(String searchContent, String searchType, Object[] filterContent) {
        LinkedHashSet<PhieuNhapDTO> searchResult = search(searchContent, searchType);

        LinkedHashSet<PhieuNhapDTO> setNameNv = filterIdNhanVien((String) filterContent[0]);
        LinkedHashSet<PhieuNhapDTO> setNameNCC = filterIdNCC((String) filterContent[1]);
        LinkedHashSet<PhieuNhapDTO> setFromNgayNhap = filterFromNgayNhap((LocalDate) filterContent[2]);
        LinkedHashSet<PhieuNhapDTO> setToNgayNhap = filterToNgayNhap((LocalDate) filterContent[3]);
        LinkedHashSet<PhieuNhapDTO> setFromTotal = filterFromTotal((Integer) filterContent[4]);
        LinkedHashSet<PhieuNhapDTO> setToTotal = filterToTotal((Integer) filterContent[5]);

        LinkedHashSet<PhieuNhapDTO> result = new LinkedHashSet<>(searchResult);
        result.retainAll(setNameNv);
        result.retainAll(setNameNCC);
        result.retainAll(setFromNgayNhap);
        result.retainAll(setToNgayNhap);
        result.retainAll(setFromTotal);
        result.retainAll(setToTotal);

        return result;
    }
}
