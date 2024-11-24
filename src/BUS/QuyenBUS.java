package BUS;

import DAO.QuyenDAO;
import DTO.QuyenDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class QuyenBUS {
    private LinkedHashSet<QuyenDTO> setQuyen;
    private QuyenDAO daoQuyen;

    public QuyenBUS() {
        setQuyen = new LinkedHashSet<>();
        daoQuyen = new QuyenDAO();

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

    public void addQuyen(QuyenDTO quyen) {
        if (setQuyen.add(quyen)) {
            daoQuyen.addQuyenWithData(quyen);
            daoQuyen.closeDB();
        }
    }

    public void removeQuyen(QuyenDTO quyen) {
        if (setQuyen.remove(quyen)) {
            daoQuyen.removeQuyenById(quyen.getId());
            daoQuyen.closeDB();
        }
    }

    public void updateTaiKhoan(QuyenDTO quyen) {
        boolean updateSuccess = setQuyen.stream()
                .filter(q -> q.getId().equals(quyen.getId()))
                .findFirst()
                .map(q -> {
                    q.setId(quyen.getId());
                    q.setName(quyen.getName());
                    return true;
                })
                .orElse(false);

        if (updateSuccess) {
            daoQuyen.updateQuyenById(quyen);
            daoQuyen.closeDB();
        }
    }

    public QuyenDTO getQuyen(String idQuyen) {
        return setQuyen.stream()
                .filter(q -> q.getId().equals(idQuyen))
                .findFirst()
                .orElse(null);
    }

    public HashMap<String, String> toMap() {
        HashMap<String, String> mapQuyen = new HashMap<>();
        for (QuyenDTO quyen : setQuyen) {
            mapQuyen.put(quyen.getName(), quyen.getId());
        }
        return mapQuyen;
    }
}
