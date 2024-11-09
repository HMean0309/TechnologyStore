package BUS;

import DTO.ChiTietSanPhamDTO;
import DAO.ChiTietSPDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;

public class ChiTietSPBUS {
    private LinkedHashSet<ChiTietSanPhamDTO> setCTSP;
    private ChiTietSPDAO daoCTSP;
    private List<String> listColorSP;
    private List<Integer> listPriceSP;
    
    public ChiTietSPBUS(){
        setCTSP = new LinkedHashSet<>();
        daoCTSP = new ChiTietSPDAO();
        
        setCTSP = ChiTietSPBUS.toSet(daoCTSP.getAllCTSP());
    }
    
    public static LinkedHashSet<ChiTietSanPhamDTO> toSet(ResultSet rs)
    {
        LinkedHashSet<ChiTietSanPhamDTO> setPL = new LinkedHashSet<>();
        try{
            while(rs.next())
            {
                ChiTietSanPhamDTO that = new ChiTietSanPhamDTO(
                        rs.getString("seri"),
                        rs.getString("id_sp"),
                        rs.getString("color"),
                        rs.getInt("price"),
                        false);
                setPL.add(that);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return setPL;
    }
    
    public ChiTietSPBUS(LinkedHashSet<ChiTietSanPhamDTO> setCTSP, ChiTietSPDAO daoCTSP){
        this.setCTSP = setCTSP;
        this.daoCTSP = daoCTSP;
   }
    
   public LinkedHashSet<ChiTietSanPhamDTO> getSetCTSP(){
       return setCTSP;
   } 
   
    public LinkedHashSet<ChiTietSanPhamDTO> getCTSPBySeri(String seri)
    {
        ResultSet rs = daoCTSP.getCTSPBySeri(seri);
        try{
            while(rs.next())
            {
                ChiTietSanPhamDTO that = new ChiTietSanPhamDTO(
                        rs.getString("seri"),
                        rs.getString("id_sp"),
                        rs.getString("color"),
                        rs.getInt("price"),
                        false);
                setCTSP.add(that);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return setCTSP;
    }
   
    public LinkedHashSet<ChiTietSanPhamDTO> getCTSPByIdSP(String id)
    {
        ResultSet rs = daoCTSP.getCTSPByIdSP(id);
        try{
            while(rs.next())
            {
                ChiTietSanPhamDTO that = new ChiTietSanPhamDTO(
                        rs.getString("seri"),
                        rs.getString("id_sp"),
                        rs.getString("color"),
                        rs.getInt("price"),
                        false);
                setCTSP.add(that);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return setCTSP;
    }
      
   public void setSetCTSP (LinkedHashSet<ChiTietSanPhamDTO> setCTSP) {
       this.setCTSP = setCTSP;
   }
   
   public ChiTietSPDAO getDaoCTSP()
   {
       return daoCTSP;
   }
   
   public void setDaoCTSP(ChiTietSPDAO daoCTSP){
       this.daoCTSP = daoCTSP;
   }
   
    public int getAllCountCTSP(){
        ResultSet rs = daoCTSP.getAllCountCTSP();
        int count = -1;
        try {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }  
    
    public int getCountCTSPByIdSP(String id_sp){
        ResultSet rs = daoCTSP.getCountCTSPByIdSP(id_sp);
        int count = -1;
        try {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }  
    
    public List<String> getColorInCTSPByIdSP(String id){
        ResultSet rs = daoCTSP.getColorInCTSPByIdSP(id);
        try {
            rs.next();
            listColorSP.add(rs.getString("color"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listColorSP;
    }
    
    public List<String> getColorInCTSPBySeri(String seri){
        ResultSet rs = daoCTSP.getColorInCTSPBySeri(seri);
        try {
            rs.next();
            listColorSP.add(rs.getString("color"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listColorSP;
    }
    
    public List<Integer> getPriceinCTSPByIdSP(String id)
    {
        ResultSet rs = daoCTSP.getPriceInCTSPByIdSP(id);
        try {
            rs.next();
            listPriceSP.add(rs.getInt("price"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPriceSP;      
    }
    
    public List<Integer> getPriceInCTSPBySeri(String seri){
        ResultSet rs = daoCTSP.getPriceInCTSPBySeri(seri);
        try {
            rs.next();
            listPriceSP.add(rs.getInt("price"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPriceSP;
    }
    
    public void addCTSPWithData(ChiTietSanPhamDTO CTSP){
        daoCTSP.addCTSPWithData(CTSP);
    }
    
    public void updateCTSPBySeri(ChiTietSanPhamDTO CTSP){
        daoCTSP.updateCTSPBySeri(CTSP);
    }
    
    public void removeCTSPBySeri(String id){
        daoCTSP.removeCTSPBySeri(id);
    }
}
