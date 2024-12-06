package BUS;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SanPhamBUS {
    public static String[] typeSearch = {
            "Tất cả", "Mã Sản Phẩm", "Tên Sản Phẩm"
    };
    public static String[] duplicateMess = {
            "Tên sản phẩm đã tồn tại! Vui lòng chọn tên khác"
    };
    private LinkedHashSet<SanPhamDTO> setSP;
    private SanPhamDAO daoSP;

    public SanPhamBUS() {
        setSP = new LinkedHashSet<>();
        daoSP = new SanPhamDAO();

        setSP = SanPhamBUS.toSet(daoSP.getAllSanPham());
        daoSP.closeDB();
    }

    public SanPhamBUS(ArrayList<String> limitListID) {
        setSP = new LinkedHashSet<>();
        daoSP = new SanPhamDAO();

        setSP = SanPhamBUS.toSet(daoSP.getAllSanPham());
        daoSP.closeDB();
        LinkedHashSet<SanPhamDTO> limitSanPham = setSP.stream()
                .filter(sanPhamDTO -> limitListID.contains(sanPhamDTO.getId()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        setSetSP(limitSanPham);
    }

    public static SanPhamBUS getInstance() {
        return new SanPhamBUS();
    }

    public HashMap<String, String> toMapName() {
        HashMap<String, String> mapName = new HashMap<>();
        for (SanPhamDTO sp : setSP) {
            mapName.put(sp.getId(), sp.getName());
        }
        return mapName;
    }

    public static LinkedHashSet<SanPhamDTO> toSet(ResultSet rs) {
        LinkedHashSet<SanPhamDTO> setSP = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                SanPhamDTO that = new SanPhamDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("img"),
                        rs.getString("id_cate"),
                        rs.getString("name_cate"),
                        rs.getInt("baohanh"),
                        rs.getInt("tonkho"),
                        false);
                setSP.add(that);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setSP;
    }

    public LinkedHashSet<SanPhamDTO> getSetSP() {
        return setSP;
    }

    public void setSetSP(LinkedHashSet<SanPhamDTO> setSP) {
        this.setSP = setSP;
    }

    public SanPhamDAO getDaoSP() {
        return daoSP;
    }

    public void setDaoSP(SanPhamDAO daoSP) {
        this.daoSP = daoSP;
    }

    public int getCountSanPham() {
        ResultSet rs = daoSP.getCountSanPham();
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
        int index = getCountSanPham() + 1;
        return String.format("PRODUCT%05d", index);
    }

    public boolean containsName(String name) {
        return setSP.stream()
                .anyMatch(sanPhamDTO -> sanPhamDTO.getName().equals(name));
    }

    public LinkedHashSet<SanPhamDTO> filterNameCate(String nameCate) {
        if (nameCate.equals("Tất cả")) {
            return getSetSP();
        }
        return setSP.stream()
                .filter(sanPhamDTO -> sanPhamDTO.getNameCate().equals(nameCate))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public void removeSanPham(SanPhamDTO sp) {
        if (setSP.remove(sp)) {
            daoSP.removeSPById(sp.getId());
            daoSP.closeDB();
        }
    }

    public int updateSanPham(SanPhamDTO product, boolean checkDupliName) {
        if (checkDupliName && containsName(product.getName())) {
            return 0;
        }
        boolean updateSuccess = setSP.stream()
                .filter(sanPhamDTO -> sanPhamDTO.getId().equals(product.getId()))
                .findFirst()
                .map(sanPhamDTO -> {
                    sanPhamDTO.setName(product.getName());
                    sanPhamDTO.setImg(product.getImg());
                    sanPhamDTO.setBaoHanh(product.getBaoHanh());
                    sanPhamDTO.setIdCate(product.getIdCate());
                    sanPhamDTO.setNameCate(product.getNameCate());
                    return true;
                })
                .orElse(false);
        if (updateSuccess) {
            daoSP.updateSP(product);
            daoSP.closeDB();
        }
        return -1;
    }

    public int addSPWithData(SanPhamDTO product) {
        if (containsName(product.getName())) {
            return 0;
        }
        setSP.add(product);
        daoSP.addSPWithData(product);
        daoSP.closeDB();
        return -1;
    }

    public LinkedHashSet<SanPhamDTO> searchID(String content) {
        return setSP.stream()
                .filter(sanPhamDTO -> sanPhamDTO.getId().contains(content))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<SanPhamDTO> searchName(String content) {
        return setSP.stream()
                .filter(sanPhamDTO -> sanPhamDTO.getName().contains(content))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<SanPhamDTO> search(String searchContent, String searchType) {
        if (searchType.equals(typeSearch[0])) {
            LinkedHashSet<SanPhamDTO> setID = searchID(searchContent);
            LinkedHashSet<SanPhamDTO> setName = searchName(searchContent);
            LinkedHashSet<SanPhamDTO> setAll = Stream.of(setID, setName)
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
        return getSetSP();
    }

    public LinkedHashSet<SanPhamDTO> searchAndfilter(String searchContent, String searchType, Object[] filterContent) {
        LinkedHashSet<SanPhamDTO> searchResult = search(searchContent, searchType);

        LinkedHashSet<SanPhamDTO> setNameCate = filterNameCate((String) filterContent[0]);

        LinkedHashSet<SanPhamDTO> result = new LinkedHashSet<>(searchResult);
        result.retainAll(setNameCate);

        return result;
    }

}
