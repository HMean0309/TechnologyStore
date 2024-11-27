package BUS;

import DAO.QuyenDAO;
import DTO.ChiTietQuyenDTO;
import DTO.QuyenDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QuyenBUS {
    public static String[] typeSearch = {
            "Tất cả", "Mã Quyền", "Tên Quyền"
    };
    public static String[] duplicateMess = {
            "Tên quyền đã tồn tại! Vui lòng chọn tên khác"
    };
    private LinkedHashSet<QuyenDTO> setQuyen;
    private QuyenDAO daoQuyen;
    private ChiTietQuyenBUS busCTQuyen;

    public QuyenBUS() {
        setQuyen = new LinkedHashSet<>();
        daoQuyen = new QuyenDAO();
        busCTQuyen = new ChiTietQuyenBUS();

        setQuyen = QuyenBUS.toSet(daoQuyen.getAllQuyen());
        daoQuyen.closeDB();
    }

    public static QuyenBUS getInstance() {
        return new QuyenBUS();
    }

    public static LinkedHashSet<QuyenDTO> toSet(ResultSet rs) {
        LinkedHashSet<QuyenDTO> setQuyen = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                QuyenDTO that = new QuyenDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        false);
                setQuyen.add(that);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setQuyen;
    }

    public QuyenBUS(LinkedHashSet<QuyenDTO> setQuyen, QuyenDAO daoQuyen) {
        this.setQuyen = setQuyen;
        this.daoQuyen = daoQuyen;
    }

    public LinkedHashSet<QuyenDTO> getSetQuyen() {
        return setQuyen;
    }

    public void setSetSP(LinkedHashSet<QuyenDTO> setQuyen) {
        this.setQuyen = setQuyen;
    }

    public QuyenDAO getDaoQuyen() {
        return daoQuyen;
    }

    public void setDaoQuyen(QuyenDAO daoQuyen) {
        this.daoQuyen = daoQuyen;
    }

    public int getCountQuyen() {
        ResultSet rs = daoQuyen.getCountQuyen();
        int count = -1;
        try {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public boolean containsName(String name) {
        return setQuyen.stream()
                .anyMatch(quyenDTO -> quyenDTO.getName().equals(name));
    }


    public int addQuyen(QuyenDTO quyen, LinkedHashSet<ChiTietQuyenDTO> listCTQuyen) {
        if (containsName(quyen.getName())) {
            return 0;
        }
        setQuyen.add(quyen);
        daoQuyen.addQuyenWithData(quyen);
        daoQuyen.closeDB();
        busCTQuyen.addAllChiTietQuyen(listCTQuyen);

        return -1;
    }

    public void removeQuyen(QuyenDTO quyen) {
        if (setQuyen.remove(quyen)) {
            daoQuyen.removeQuyenById(quyen.getId());
            daoQuyen.closeDB();
            busCTQuyen.removeAllChiTietQuyen(quyen.getId());
        }
    }

    public int updateQuyen(QuyenDTO quyen, LinkedHashSet<ChiTietQuyenDTO> listCTQuyen, boolean checkDupliName) {
        if (checkDupliName && containsName(quyen.getName())) {
            return 0;
        }
        boolean updateSuccess = setQuyen.stream()
                .filter(q -> q.getId().equals(quyen.getId()))
                .findFirst()
                .map(q -> {
                    q.setName(quyen.getName());
                    return true;
                })
                .orElse(false);

        if (updateSuccess) {
            daoQuyen.updateQuyenById(quyen);
            daoQuyen.closeDB();
        }

        if (busCTQuyen.removeAllChiTietQuyen(quyen.getId())) {
            busCTQuyen.addAllChiTietQuyen(listCTQuyen);
        }

        return -1;
    }

    public QuyenDTO getQuyen(String idQuyen) {
        return setQuyen.stream()
                .filter(q -> q.getId().equals(idQuyen))
                .findFirst()
                .orElse(null);
    }

    public LinkedHashSet<ChiTietQuyenDTO> getAllCTQuyen(String idQuyen) {
        return busCTQuyen.getAllCTQuyen(idQuyen);
    }

    public String createID() {
        int index = getCountQuyen() + 1;
        return String.format("POS%03d", index);
    }

    public HashMap<String, String> toMap() {
        HashMap<String, String> mapQuyen = new HashMap<>();
        for (QuyenDTO quyen : setQuyen) {
            mapQuyen.put(quyen.getName(), quyen.getId());
        }
        return mapQuyen;
    }


    public LinkedHashSet<QuyenDTO> search(String content, String typeSearch) {
        if (typeSearch.equals(QuyenBUS.typeSearch[0])) {
            LinkedHashSet<QuyenDTO> setName = searchName(content);
            LinkedHashSet<QuyenDTO> setID = searchID(content);
            LinkedHashSet<QuyenDTO> setAll = Stream.of(setID, setName)
                    .flatMap(LinkedHashSet::stream)
                    .collect(Collectors.toCollection(LinkedHashSet::new));
            return setAll;
        }

        if (typeSearch.equals(QuyenBUS.typeSearch[1])) {
            return searchID(content);
        }
        if (typeSearch.equals(QuyenBUS.typeSearch[2])) {
            return searchName(content);
        }
        return getSetQuyen();
    }

    private LinkedHashSet<QuyenDTO> searchName(String content) {
        return setQuyen.stream()
                .filter(quyenDTO -> quyenDTO.getName().contains(content))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private LinkedHashSet<QuyenDTO> searchID(String content) {
        return setQuyen.stream()
                .filter(quyenDTO -> quyenDTO.getId().contains(content))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }


}
