package BUS;

import DTO.ChiTietKhuyenMaiDTO;
import DAO.ChiTietKMDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;

public class ChiTietKMBUS {
    private LinkedHashSet<ChiTietKhuyenMaiDTO> setCTKM;
    private ChiTietKMDAO daoCTKM;
    private List<String> listColorKM;
    private List<Integer> listPriceKM;
    
    public ChiTietKMBUS(){
        setCTKM = new LinkedHashSet<>();
        daoCTKM = new ChiTietKMDAO();
        
        setCTKM = ChiTietKMBUS.toSet(daoCTKM.getAllCTKM());
    }
    
    public static LinkedHashSet<ChiTietKhuyenMaiDTO> toSet(ResultSet rs)
    {
        LinkedHashSet<ChiTietKhuyenMaiDTO> setPL = new LinkedHashSet<>();
        try{
            while(rs.next())
            {
                ChiTietKhuyenMaiDTO that = new ChiTietKhuyenMaiDTO(
                        rs.getString("id_km"),
                        rs.getString("id_sp"));
                setPL.add(that);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return setPL;
    }
    
    public ChiTietKMBUS(LinkedHashSet<ChiTietKhuyenMaiDTO> setCTKM, ChiTietKMDAO daoCTKM){
        this.setCTKM = setCTKM;
        this.daoCTKM = daoCTKM;
   }
    
   public LinkedHashSet<ChiTietKhuyenMaiDTO> getSetCTKM(){
       return setCTKM;
   } 
   
    public LinkedHashSet<ChiTietKhuyenMaiDTO> getCTKMByIdKM(String id)
    {
        ResultSet rs = daoCTKM.getCTKMByIdKM(id);
        try{
            while(rs.next())
            {
                ChiTietKhuyenMaiDTO that = new ChiTietKhuyenMaiDTO(
                        rs.getString("id_km"),
                        rs.getString("id_sp"));
                setCTKM.add(that);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return setCTKM;
    }
      
   public void setSetCTKM (LinkedHashSet<ChiTietKhuyenMaiDTO> setCTKM) {
       this.setCTKM = setCTKM;
   }
   
   public ChiTietKMDAO getDaoCTKM()
   {
       return daoCTKM;
   }
   
   public void setDaoCTKM(ChiTietKMDAO daoCTKM){
       this.daoCTKM = daoCTKM;
   }
   
    public int getAllCountCTKM(){
        ResultSet rs = daoCTKM.getCountAllCTKM();
        int count = -1;
        try {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }  
    
    public void addCTKMWithData(ChiTietKhuyenMaiDTO CTKM){
        daoCTKM.addCTKMWithData(CTKM);
    }
    
    public void updateCTKMByIdKM(ChiTietKhuyenMaiDTO CTKM){
        daoCTKM.updateCTKMByIdKM(CTKM);
    }
}

