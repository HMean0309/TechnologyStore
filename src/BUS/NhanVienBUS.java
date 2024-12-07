package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NhanVienBUS {
    public static String[] typeSearch = {
            "Tất cả", "Mã nhân viên", "Tên nhân viên", "Số điện thoại", "Email",
    };

    public static String[] typeFilter = {
            "Giới tính", "Ngày sinh từ", "Ngày sinh đến"
    };

    public static String[] duplicateMess = {
            "Số điện thoại đã tồn tại. Vui lòng chọn số điện thoại khác!",
            "Email đã tồn tại. Vui lòng chọn email khác!"
    };
    private LinkedHashSet<NhanVienDTO> setNV;
    private NhanVienDAO daoNV;

    public NhanVienBUS() {
        setNV = new LinkedHashSet<>();
        daoNV = new NhanVienDAO();

        setNV = NhanVienBUS.toSet(daoNV.getAllNhanVien());
        daoNV.closeDB();
    }

    public static NhanVienBUS getInstance() {
        return new NhanVienBUS();
    }

    public static LinkedHashSet<NhanVienDTO> toSet(ResultSet rs) {
        LinkedHashSet<NhanVienDTO> setNV = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                NhanVienDTO that = new NhanVienDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getDate("birth").toLocalDate(),
                        rs.getBoolean("gender"),
                        rs.getString("username"),
                        rs.getBoolean("isDelete"));
                setNV.add(that);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setNV;
    }

    public NhanVienBUS(LinkedHashSet<NhanVienDTO> setNV, NhanVienDAO daoNV) {
        this.setNV = setNV;
        this.daoNV = daoNV;
    }

    public LinkedHashSet<NhanVienDTO> getSetNV() {
        return setNV;
    }

    public void setSetNV(LinkedHashSet<NhanVienDTO> setNV) {
        this.setNV = setNV;
    }

    public NhanVienDAO getDaoNV() {
        return daoNV;
    }

    public void setDaoNV(NhanVienDAO daoNV) {
        this.daoNV = daoNV;
    }

    public int getCountNhanVien() {
        ResultSet rs = daoNV.getCountNhanVien();
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
        int index = getCountNhanVien() + 1;
        return String.format("STAFF%05d", index);
    }

    public NhanVienDTO getNhanVien(String idNhanVien) {
        return setNV.stream()
                .filter(nv -> nv.getId().equals(idNhanVien))
                .findFirst()
                .orElse(null);
    }

    public LinkedHashSet<NhanVienDTO> getAllNhanVienNotAcc() {
        return setNV.stream()
                .filter(nhanVienDTO -> nhanVienDTO.getUsername() == null)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public HashMap<String, String> toMap() {
        HashMap<String, String> mapNV = new HashMap<>();
        for (NhanVienDTO nv : setNV) {
            mapNV.put(nv.getId(), nv.getName());
        }
        return mapNV;
    }

    public boolean containsPhone(String phone) {
        return setNV.stream().anyMatch(nv -> nv.getPhone().equals(phone));
    }

    public boolean containsEmail(String email) {
        return setNV.stream().anyMatch(nv -> nv.getEmail().equals(email));
    }

    public int addNhanVien(NhanVienDTO nhanVienDTO) {
        if (containsPhone(nhanVienDTO.getPhone())) {
            return 0;
        }
        if (containsEmail(nhanVienDTO.getEmail())) {
            return 1;
        }
        setNV.add(nhanVienDTO);
        daoNV.addNVWithData(nhanVienDTO);
        daoNV.closeDB();
        return -1;
    }

    public boolean removeNhanVien(NhanVienDTO nhanVienDTO) {
        boolean removeSuccess = setNV.remove(nhanVienDTO);
        if (removeSuccess) {
            daoNV.removeNVById(nhanVienDTO.getId());
            daoNV.closeDB();
        }
        return removeSuccess;
    }

    public int updateNhanVien(NhanVienDTO nhanVienDTO, boolean checkDupliPhone, boolean checkDupliEmail) {
        if (checkDupliPhone && containsPhone(nhanVienDTO.getPhone())) {
            return 0;
        }
        if (checkDupliEmail && containsEmail(nhanVienDTO.getEmail())) {
            return 1;
        }
        boolean updateSuccess = setNV.stream()
                .filter(nv -> nv.getId().equals(nhanVienDTO.getId()))
                .findFirst()
                .map(nv -> {
                    nv.setName(nhanVienDTO.getName());
                    nv.setPhone(nhanVienDTO.getPhone());
                    nv.setBirth(nhanVienDTO.getBirth());
                    nv.setEmail(nhanVienDTO.getEmail());
                    nv.setGender(nhanVienDTO.getGender());
                    return true;
                })
                .orElse(false);
        if (updateSuccess) {
            daoNV.updateNVById(nhanVienDTO);
            daoNV.closeDB();
        }

        return -1;
    }

    public LinkedHashSet<NhanVienDTO> searchName(String content) {
        return setNV.stream()
                .filter(nhanVienDTO -> nhanVienDTO.getName().toLowerCase().contains(content.toLowerCase()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<NhanVienDTO> searchID(String content) {
        return setNV.stream()
                .filter(nhanVienDTO -> nhanVienDTO.getId().contains(content))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<NhanVienDTO> searchEmail(String content) {
        return setNV.stream()
                .filter(nhanVienDTO -> nhanVienDTO.getEmail().contains(content))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<NhanVienDTO> searchPhone(String content) {
        return setNV.stream()
                .filter(nhanVienDTO -> nhanVienDTO.getPhone().contains(content))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<NhanVienDTO> filterGender(String genderString) {
        if (genderString.equals("Nam") || genderString.equals("Nữ")) {
            return setNV.stream()
                    .filter(nhanVienDTO -> nhanVienDTO.getGenderValue().equals(genderString))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        }
        return getSetNV();
    }

    public LinkedHashSet<NhanVienDTO> filterFromBirth(LocalDate date) {
        if (date == null) {
            return getSetNV();
        }

        return setNV.stream()
                .filter(nhanVienDTO -> nhanVienDTO.getBirth().isAfter(date) || nhanVienDTO.getBirth().isEqual(date))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<NhanVienDTO> filterToBirth(LocalDate date) {
        if (date == null) {
            return getSetNV();
        }

        return setNV.stream()
                .filter(nhanVienDTO -> nhanVienDTO.getBirth().isBefore(date) || nhanVienDTO.getBirth().isEqual(date))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<NhanVienDTO> search(String searchContent, String searchType) {
        if (searchType.equals(typeSearch[0])) {
            LinkedHashSet<NhanVienDTO> setName = searchName(searchContent);
            LinkedHashSet<NhanVienDTO> setID = searchID(searchContent);
            LinkedHashSet<NhanVienDTO> setPhone = searchPhone(searchContent);
            LinkedHashSet<NhanVienDTO> setEmail = searchEmail(searchContent);
            LinkedHashSet<NhanVienDTO> setAll = Stream.of(setID, setName, setPhone, setEmail)
                    .flatMap(LinkedHashSet::stream)
                    .collect(Collectors.toCollection(LinkedHashSet::new));
            return setAll;
        }

        if (searchType.equals(typeSearch[1])) {
            return searchID(searchContent);
        }
        if (searchType.equals(typeSearch[2])) {
            return searchName(searchContent);
        }
        if (searchType.equals(typeSearch[3])) {
            return searchPhone(searchContent);
        }
        if (searchType.equals(typeSearch[4])) {
            return searchEmail(searchContent);
        }
        return getSetNV();
    }

    public LinkedHashSet<NhanVienDTO> searchAndfilter(String searchContent, String searchType, Object[] filterContent) {
        LinkedHashSet<NhanVienDTO> searchResult = search(searchContent, searchType);

        LinkedHashSet<NhanVienDTO> setGender = filterGender((String) filterContent[0]);
        LinkedHashSet<NhanVienDTO> setFromBirth = filterFromBirth((LocalDate) filterContent[1]);
        LinkedHashSet<NhanVienDTO> setToBirth = filterToBirth((LocalDate) filterContent[2]);

        LinkedHashSet<NhanVienDTO> result = new LinkedHashSet<>(searchResult);
        result.retainAll(setGender);
        result.retainAll(setFromBirth);
        result.retainAll(setToBirth);

        return result;
    }

}
