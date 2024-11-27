package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KhachHangBUS {
    public static String[] typeSearch = { "Tất cả", "Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Số điện thoại" };
    public static String[] duplicateMess = {
            "Số điện thoại đã tồn tại. Vui lòng chọn số điện thoại khác!"
    };
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
        daoKH.closeDB();
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
        daoKH.closeDB();
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
        daoKH.closeDB();
        return count;
    }

    public int updateKhachHang(KhachHangDTO kh, boolean checkDupliPhone) {
        if (checkDupliPhone && containsPhone(kh.getPhone())) {
            return 0;
        }

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
            daoKH.updateKhachHang(kh);
            daoKH.closeDB();
        }

        return -1;
    }

    public int addKhachHang(KhachHangDTO kh) {
        if (containsPhone(kh.getPhone())) {
            return 0;
        }
        setKH.add(kh);
        daoKH.addKhachHang(kh);
        daoKH.closeDB();
        return -1;
    }

    public boolean removeKhachHang(KhachHangDTO kh) {
        boolean removeSuccess = setKH.remove(kh);
        if (removeSuccess) {
            daoKH.removeKhachHang(kh.getId());
            daoKH.closeDB();
        }

        return removeSuccess;
    }

    public boolean containsPhone(String phone) {
        return setKH.stream()
                .anyMatch(kh -> kh.getPhone().equals(phone));
    }

    public String createID() {
        int index = getCountKhachHang() + 1;
        return String.format("CUSTOMER%05d", index);
    }

    public LinkedHashSet<KhachHangDTO> searchName(String content) {
        return setKH.stream()
                .filter(khachHangDTO -> khachHangDTO.getName().toLowerCase().contains(content.toLowerCase()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<KhachHangDTO> searchID(String content) {
        return setKH.stream()
                .filter(khachHangDTO -> khachHangDTO.getId().contains(content))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<KhachHangDTO> searchFullAddress(String content) {
        return setKH.stream()
                .filter(khachHangDTO -> khachHangDTO.getFullAddress().toLowerCase().contains(content.toLowerCase()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<KhachHangDTO> searchPhone(String content) {
        return setKH.stream()
                .filter(khachHangDTO -> khachHangDTO.getPhone().startsWith(content))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<KhachHangDTO> search(String content, String searchType) {
        if (searchType.equals(KhachHangBUS.typeSearch[0])) {
            LinkedHashSet<KhachHangDTO> setName = searchName(content);
            LinkedHashSet<KhachHangDTO> setID = searchID(content);
            LinkedHashSet<KhachHangDTO> setPhone = searchPhone(content);
            LinkedHashSet<KhachHangDTO> setFullAddress = searchFullAddress(content);
            LinkedHashSet<KhachHangDTO> setAll = Stream.of(setID, setName, setPhone, setFullAddress)
                    .flatMap(LinkedHashSet::stream)
                    .collect(Collectors.toCollection(LinkedHashSet::new));
            return setAll;
        }

        if (searchType.equals(KhachHangBUS.typeSearch[1])) {
            return searchID(content);
        }
        if (searchType.equals(KhachHangBUS.typeSearch[2])) {
            return searchName(content);
        }
        if (searchType.equals(KhachHangBUS.typeSearch[3])) {
            return searchFullAddress(content);
        }
        if (searchType.equals(KhachHangBUS.typeSearch[4])) {
            return searchPhone(content);
        }
        return getSetKH();
    }
}
