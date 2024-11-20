package BUS;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import DAO.NhanVienDAO;
import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;

public class NhanVienBUS {
    private LinkedHashSet<NhanVienDTO> setNV;
    private NhanVienDAO daoNV;
    
    public NhanVienBUS(){
        setNV = new LinkedHashSet<>();
        daoNV = new NhanVienDAO();

        setNV = NhanVienBUS.toSet(daoNV.getAllNhanVien());
    }
    
    public static LinkedHashSet<NhanVienDTO> toSet(ResultSet rs){
        LinkedHashSet<NhanVienDTO> setNV = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                NhanVienDTO that = new NhanVienDTO(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    false);
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
    
    public int getCountNhanVien(String id){
        ResultSet rs = daoNV.getCountNhanVien(id);
        int count = -1;
        try {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    public LinkedHashSet<NhanVienDTO> getAllNhanVien() {
        return setNV;
    }
    
    
}
