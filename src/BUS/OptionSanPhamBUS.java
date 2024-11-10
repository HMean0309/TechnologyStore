package BUS;
import DTO.OptionSanPhamDTO;
import DAO.OptionSanPhamDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;

public class OptionSanPhamBUS {
    private LinkedHashSet<OptionSanPhamDTO> setOSP;
    private OptionSanPhamDAO daoOSP;
    private List<String> listColorSP;
    
    public OptionSanPhamBUS(){
        setOSP = new LinkedHashSet<>();
        daoOSP = new OptionSanPhamDAO();
        
        setOSP= OptionSanPhamBUS.toSet(daoOSP.getAllOSP());
    }
    
    public static LinkedHashSet<OptionSanPhamDTO> toSet(ResultSet rs)
    {
        LinkedHashSet<OptionSanPhamDTO> setPL = new LinkedHashSet<>();
        try{
            while(rs.next())
            {
                OptionSanPhamDTO that = new OptionSanPhamDTO(
                        rs.getString("id_sp"),
                        rs.getString("color"),
                        false);
                setPL.add(that);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return setPL;
    }
    
    public OptionSanPhamBUS(LinkedHashSet<OptionSanPhamDTO> setCTKM, OptionSanPhamDAO daoCTKM){
        this.setOSP = setCTKM;
        this.daoOSP = daoCTKM;
   }
    
   public LinkedHashSet<OptionSanPhamDTO> getSetCTKM(){
       return setOSP;
   } 
   
    public LinkedHashSet<OptionSanPhamDTO> getOSPById(String id)
    {
        ResultSet rs = daoOSP.getOSPById(id);
        try{
            while(rs.next())
            {
                OptionSanPhamDTO that = new OptionSanPhamDTO(
                        rs.getString("id_km"),
                        rs.getString("color"),
                        false);
                setOSP.add(that);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return setOSP;
    }
      
   public void setSetOSP (LinkedHashSet<OptionSanPhamDTO> setCTKM) {
       this.setOSP = setCTKM;
   }
   
   public OptionSanPhamDAO getDaoOSP()
   {
       return daoOSP;
   }
   
   public void setDaoOSP(OptionSanPhamDAO daoCTKM){
       this.daoOSP = daoCTKM;
   }
   
    public int getAllCountOSP(){
        ResultSet rs = daoOSP.getCountAllOSP();
        int count = -1;
        try {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public List<String> getColorOSPById(String id)
    {
        ResultSet rs = daoOSP.getColorOSPById(id);
          try {
            rs.next();
            listColorSP.add(rs.getString("color"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listColorSP;
    }
    
    public void addOSPWithData(OptionSanPhamDTO CTKM){
        daoOSP.addOSPWithData(CTKM);
    }
    
    public void updateOSPByIdK(OptionSanPhamDTO CTKM){
        daoOSP.updateOSPById(CTKM);
    }
    
    public void removeOSPById(OptionSanPhamDTO CTKM){
        daoOSP.removeOSPById(CTKM);
    }
}
