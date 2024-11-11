package BUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;

public class SanPhamBUS {
    private LinkedHashSet<SanPhamDTO> setSP;
    private SanPhamDAO daoSP;

    public SanPhamBUS(){
        setSP = new LinkedHashSet<>();
        daoSP = new SanPhamDAO();

        setSP = SanPhamBUS.toSet(daoSP.getAllSanPham());
    }

    public static LinkedHashSet<SanPhamDTO> toSet(ResultSet rs){
        LinkedHashSet<SanPhamDTO> setSP = new LinkedHashSet<>();
        try {
            while (rs.next()) {
                SanPhamDTO that = new SanPhamDTO(
                    rs.getString("id"),
                    rs.getString("name"),
                    false,
                    rs.getString("id_cate"),
                    rs.getInt("baohanh"),
                    rs.getString("des"),
                    rs.getString("img"));
                setSP.add(that);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setSP;
    }

    public SanPhamBUS(LinkedHashSet<SanPhamDTO> setSP, SanPhamDAO daoSP) {
        this.setSP = setSP;
        this.daoSP = daoSP;
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

    public int getCountSPOfPhanLoai(String idCate){
        ResultSet rs = daoSP.getCountSPOfPhanLoai(idCate);
        int count = -1;
        try {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public LinkedHashSet<SanPhamDTO> getSanPhamById(String id)
    {
        ResultSet rs = daoSP.getSanPhamById(id);
        try {
            while (rs.next()) {
                SanPhamDTO that = new SanPhamDTO(
                    rs.getString("id"),
                    rs.getString("name"),
                    false,
                    rs.getString("id_cate"),
                    rs.getInt("baohanh"),
                    rs.getString("des"),
                    rs.getString("img"));
                setSP.add(that);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setSP;
    }
    
    public LinkedHashSet<SanPhamDTO> getSPByIdCate(String idCate)
    {
        ResultSet rs = daoSP.getSanPhamById(idCate);
        try {
            while (rs.next()) {
                SanPhamDTO that = new SanPhamDTO(
                    rs.getString("id"),
                    rs.getString("name"),
                    false,
                    rs.getString("id_cate"),
                    rs.getInt("baohanh"),
                    rs.getString("des"),
                    rs.getString("img"));
                setSP.add(that);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return setSP;
    }
    
    public void removeSPById(String id){
        daoSP.removeSPById(id);
    }
    
    public void updateSPById(SanPhamDTO product)
    {
        daoSP.updateSPById(product);
    }
    
    public  void addSPWithData(SanPhamDTO product){
        daoSP.addSPWithData(product);
    }
}
