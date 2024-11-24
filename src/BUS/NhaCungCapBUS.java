package BUS;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NhaCungCapBUS {
    public static String[] type = { "Tất cả", "Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "Số điện thoại" };
    public static String[] duplicateMess = {
            "Số điện thoại đã tồn tại. Vui lòng chọn số điện thoại khác!"
    };
    private LinkedHashSet<NhaCungCapDTO> setNCC;
    private NhaCungCapDAO daoNCC;

    public NhaCungCapBUS() {
        daoNCC = new NhaCungCapDAO();
        setNCC = new LinkedHashSet<>();

        setNCC = NhaCungCapBUS.toSet(daoNCC.getAllNhaCungCap());
        daoNCC.closeDB();
    }

    public static NhaCungCapBUS getInstance() {
        return new NhaCungCapBUS();
    }

    public static LinkedHashSet<NhaCungCapDTO> toSet(ResultSet rs) {
        LinkedHashSet<NhaCungCapDTO> setNCC = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                NhaCungCapDTO nhaCungCap = new NhaCungCapDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("ward"),
                        rs.getString("district"),
                        rs.getString("city"),
                        false);
                setNCC.add(nhaCungCap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setNCC;
    }

    public NhaCungCapBUS(LinkedHashSet<NhaCungCapDTO> setNCC, NhaCungCapDAO daoNCC) {
        this.setNCC = setNCC;
        this.daoNCC = daoNCC;
    }

    public boolean containsPhone(String phone) {
        return setNCC.stream()
                .anyMatch(ncc -> ncc.getPhone().equals(phone));
    }

    public int addNhaCungCap(NhaCungCapDTO ncc) {
        boolean addSuccess = containsPhone(ncc.getPhone());
        if (!addSuccess) {
            return 0;
        }
        setNCC.add(ncc);
        daoNCC.addNhaCungCap(ncc);
        daoNCC.closeDB();
        return -1;
    }

    public boolean updateNhaCungCap(NhaCungCapDTO ncc) {
        boolean updateSuccess = setNCC.stream()
                .filter(nhaCungCap -> nhaCungCap.getId().equals(ncc.getId()))
                .findFirst()
                .map(nhaCungCap -> {
                    nhaCungCap.setName(ncc.getName());
                    nhaCungCap.setPhone(ncc.getPhone());
                    nhaCungCap.setAddress(ncc.getAddress());
                    nhaCungCap.setDistrict(ncc.getDistrict());
                    nhaCungCap.setWard(ncc.getWard());
                    nhaCungCap.setCity(ncc.getCity());
                    return true;
                })
                .orElse(false);

        if (updateSuccess) {
            daoNCC.updateNhaCungCap(ncc);
            daoNCC.closeDB();
        }

        return updateSuccess;
    }

    public boolean removeNhaCungCap(NhaCungCapDTO ncc) {
        boolean removeSuccess = setNCC.remove(ncc);
        if (removeSuccess) {
            daoNCC.removeNhaCungCap(ncc.getId());
            daoNCC.closeDB();
        }
        return removeSuccess;
    }


    public LinkedHashSet<NhaCungCapDTO> getSetNCC() {
        return setNCC;
    }

    public void setSetNCC(LinkedHashSet<NhaCungCapDTO> setNCC) {
        this.setNCC = setNCC;
    }

    public NhaCungCapDAO getDaoNCC() {
        return daoNCC;
    }

    public void setDaoNCC(NhaCungCapDAO daoNCC) {
        this.daoNCC = daoNCC;
    }

    public int getCountNhaCungCap() {
        ResultSet rs = daoNCC.getCountNhaCungCap();
        int count = -1;
        try {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        daoNCC.closeDB();
        return count;
    }

    public String createID() {
        int index = getCountNhaCungCap() + 1;
        return String.format("NCC%04d", index);
    }

    public LinkedHashSet<NhaCungCapDTO> searchName(String content) {
        return setNCC.stream()
                .filter(nhaCungCapDTO -> nhaCungCapDTO.getName().toLowerCase().contains(content.toLowerCase()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<NhaCungCapDTO> searchID(String content) {
        return setNCC.stream()
                .filter(nhaCungCapDTO -> nhaCungCapDTO.getId().contains(content))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<NhaCungCapDTO> searchFullAddress(String content) {
        return setNCC.stream()
                .filter(nhaCungCapDTO -> nhaCungCapDTO.getFullAddress().toLowerCase().contains(content.toLowerCase()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<NhaCungCapDTO> searchPhone(String content) {
        return setNCC.stream()
                .filter(nhaCungCapDTO -> nhaCungCapDTO.getPhone().startsWith(content))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<NhaCungCapDTO> search(String content, String typeSearch) {
        if (typeSearch.equals(type[0])) {
            LinkedHashSet<NhaCungCapDTO> setName = searchName(content);
            LinkedHashSet<NhaCungCapDTO> setID = searchID(content);
            LinkedHashSet<NhaCungCapDTO> setPhone = searchPhone(content);
            LinkedHashSet<NhaCungCapDTO> setFullAddress = searchFullAddress(content);
            LinkedHashSet<NhaCungCapDTO> setAll = Stream.of(setID, setName, setPhone, setFullAddress)
                    .flatMap(LinkedHashSet::stream)
                    .collect(Collectors.toCollection(LinkedHashSet::new));
            return setAll;
        }

        if (typeSearch.equals(type[1])) {
            return searchID(content);
        }
        if (typeSearch.equals(type[2])) {
            return searchName(content);
        }
        if (typeSearch.equals(type[3])) {
            return searchFullAddress(content);
        }
        if (typeSearch.equals(type[4])) {
            return searchPhone(content);
        }
        return getSetNCC();
    }

}
