package BUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import DAO.ChiTietQuyenDAO;
import DTO.ChiTietQuyenDTO;

public class ChiTietQuyenBUS {
    private LinkedHashSet<ChiTietQuyenDTO> setCtQuyen;
    private ChiTietQuyenDAO daoCtQuyen;

    public ChiTietQuyenBUS(){
        setCtQuyen = new LinkedHashSet<>();
        daoCtQuyen = new ChiTietQuyenDAO();

        setCtQuyen = ChiTietQuyenBUS.toSet(daoCtQuyen.getAllChiTietQuyen());
    }

    public static LinkedHashSet<ChiTietQuyenDTO> toSet(ResultSet rs){
        LinkedHashSet<ChiTietQuyenDTO> setCtQuyen = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                ChiTietQuyenDTO that = new ChiTietQuyenDTO(
                    rs.getString("idQuyen"),
                    rs.getString("idChucNang"),
                    rs.getBoolean("show"),
                    rs.getBoolean("insert"),   
                    rs.getBoolean("edit"),    
                    false
                    );
                setCtQuyen.add(that);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setCtQuyen;
    }

    public ChiTietQuyenBUS(LinkedHashSet<ChiTietQuyenDTO> setCtQuyen, ChiTietQuyenDAO daoCtQuyen) {
        this.setCtQuyen = setCtQuyen;
        this.daoCtQuyen = daoCtQuyen;
    }
    
    public LinkedHashSet<ChiTietQuyenDTO> getSetChiTietQuyen() {
        return setCtQuyen;
    }

    public void setSetChiTietQuyen(LinkedHashSet<ChiTietQuyenDTO> setCtQuyen) {
        this.setCtQuyen = setCtQuyen;
    }

    public ChiTietQuyenDAO getDaoChiTietQuyen() {
        return daoCtQuyen;
    }

    public void setDaoChiTietQuyen(ChiTietQuyenDAO daoCtQuyen) {
        this.daoCtQuyen = daoCtQuyen;
    }

    public int getCountChiTietQuyen(String idQuyen){
        ResultSet rs = daoCtQuyen.getCountChiTietQuyen(idQuyen);
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
