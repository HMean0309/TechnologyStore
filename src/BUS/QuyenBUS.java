package BUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import DAO.QuyenDAO;
import DTO.QuyenDTO;

public class QuyenBUS {
    private LinkedHashSet<QuyenDTO> setQuyen;
    private QuyenDAO daoQuyen;

    public QuyenBUS(){
        setQuyen = new LinkedHashSet<>();
        daoQuyen = new QuyenDAO();

        setQuyen = QuyenBUS.toSet(daoQuyen.getAllQuyen());
    }

    public static LinkedHashSet<QuyenDTO> toSet(ResultSet rs){
        LinkedHashSet<QuyenDTO> setQuyen = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                QuyenDTO that = new QuyenDTO(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("des"),
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

    public int getCountQuyen(String id){
        ResultSet rs = daoQuyen.getCountQuyen(id);
        int count = -1;
        try {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
