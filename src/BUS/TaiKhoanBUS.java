package BUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;

public class TaiKhoanBUS {
    private LinkedHashSet<TaiKhoanDTO> setTK;
    private TaiKhoanDAO daoTK;

    public TaiKhoanBUS(){
        setTK = new LinkedHashSet<>();
        daoTK = new TaiKhoanDAO();

        setTK = TaiKhoanBUS.toSet(daoTK.getAllTaiKhoan());
    }

    public static LinkedHashSet<TaiKhoanDTO> toSet(ResultSet rs){
        LinkedHashSet<TaiKhoanDTO> setTK = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                TaiKhoanDTO that = new TaiKhoanDTO(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getBoolean("type"),
                    rs.getString("idNV"),
                    rs.getString("idQuyen"),
                    false);
                    
                setTK.add(that);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setTK;
    }

    public TaiKhoanBUS(LinkedHashSet<TaiKhoanDTO> setTK, TaiKhoanDAO daoTK) {
        this.setTK = setTK;
        this.daoTK = daoTK;
    }
    
    public LinkedHashSet<TaiKhoanDTO> getSetTK() {
        return setTK;
    }

    public void setSetTK(LinkedHashSet<TaiKhoanDTO> setTK) {
        this.setTK = setTK;
    }

    public TaiKhoanDAO getDaoTK() {
        return daoTK;
    }

    public void setDaoTK(TaiKhoanDAO daoTK) {
        this.daoTK = daoTK;
    }

    public int getCountTaiKhoan(String idNV){
        ResultSet rs = daoTK.getCountTaiKhoan(idNV);
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
