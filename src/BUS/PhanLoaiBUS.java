package BUS;

import DAO.PhanLoaiDAO;
import DTO.PhanLoaiDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PhanLoaiBUS {
    public static String[] typeSearch = {
            "Tất cả", "Mã loại", "Tên loại"
    };
    public static String[] duplicateMess = {
            "Tên loại đã tồn tại. Vui lòng chọn tên khác!"
    };
    private LinkedHashSet<PhanLoaiDTO> setPL;
    private PhanLoaiDAO daoPL;

    public PhanLoaiBUS() {
        setPL = new LinkedHashSet<>();
        daoPL = new PhanLoaiDAO();

        setPL = PhanLoaiBUS.toSet(daoPL.getAllPhanLoai());
        daoPL.closeDB();
    }

    public static PhanLoaiBUS getInstance() {
        return new PhanLoaiBUS();
    }

    public static LinkedHashSet<PhanLoaiDTO> toSet(ResultSet rs) {
        LinkedHashSet<PhanLoaiDTO> setPL = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                PhanLoaiDTO that = new PhanLoaiDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        false);
                setPL.add(that);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setPL;
    }

    public PhanLoaiBUS(LinkedHashSet<PhanLoaiDTO> setPL, PhanLoaiDAO daoPL) {
        this.setPL = setPL;
        this.daoPL = daoPL;
    }

    public LinkedHashSet<PhanLoaiDTO> getSetPL() {
        return setPL;
    }

    public void setSetPL(LinkedHashSet<PhanLoaiDTO> setPL) {
        this.setPL = setPL;
    }

    public PhanLoaiDAO getDaoPL() {
        return daoPL;
    }

    public void setDaoPL(PhanLoaiDAO daoPL) {
        this.daoPL = daoPL;
    }


    public LinkedHashSet<PhanLoaiDTO> getPhanLoaiById(String id) {
        ResultSet rs = daoPL.getPhanLoaiById(id);
        try {
            while (rs.next()) {
                PhanLoaiDTO that = new PhanLoaiDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        false);
                setPL.add(that);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setPL;
    }

    public HashMap<String, String> toMap() {
        HashMap<String, String> mapLoai = new HashMap<>();
        for (PhanLoaiDTO loai : setPL) {
            mapLoai.put(loai.getId(), loai.getName());
        }
        return mapLoai;
    }

    public boolean containsName(String name) {
        return setPL.stream()
                .anyMatch(phanLoaiDTO -> phanLoaiDTO.getName().equals(name));
    }

    public void removePhanLoai(PhanLoaiDTO pl) {
        if (setPL.remove(pl)) {
            daoPL.removePLById(pl.getId());
            daoPL.closeDB();
        }
    }

    public int updatePhanLoai(PhanLoaiDTO pl, boolean checkDupliName) {
        if (checkDupliName && containsName(pl.getName())) {
            return 0;
        }

        boolean updateSuccess = setPL.stream()
                .filter(phanLoaiDTO -> phanLoaiDTO.getId().equals(pl.getId()))
                .findFirst()
                .map(phanLoaiDTO -> {
                    phanLoaiDTO.setId(pl.getId());
                    phanLoaiDTO.setName(pl.getName());
                    return true;
                })
                .orElse(false);
        if (updateSuccess) {
            daoPL.updatePLById(pl);
            daoPL.closeDB();
        }
        return -1;
    }

    public int addPhanLoai(PhanLoaiDTO pl) {
        if (containsName(pl.getName())) {
            return 0;
        }
        setPL.add(pl);
        daoPL.addPLWithData(pl);
        daoPL.closeDB();
        return -1;
    }

    public int getCountPhanLoai() {
        ResultSet rs = daoPL.getCountPhanLoai();
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
        int index = getCountPhanLoai() + 1;
        return String.format("CATE%03d", index);
    }

    public LinkedHashSet<PhanLoaiDTO> searchID(String content) {
        return setPL.stream()
                .filter(phanLoaiDTO -> phanLoaiDTO.getId().contains(content))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<PhanLoaiDTO> searchName(String content) {
        return setPL.stream()
                .filter(phanLoaiDTO -> phanLoaiDTO.getName().contains(content))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<PhanLoaiDTO> search(String searchContent, String searchType) {
        if (searchType.equals(typeSearch[0])) {
            LinkedHashSet<PhanLoaiDTO> setID = searchID(searchContent);
            LinkedHashSet<PhanLoaiDTO> setName = searchName(searchContent);
            LinkedHashSet<PhanLoaiDTO> setAll = Stream.of(setID, setName)
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
        return getSetPL();
    }
}
