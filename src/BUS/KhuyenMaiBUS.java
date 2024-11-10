package BUS;

import DTO.KhuyenMaiDTO;
import DAO.KhuyenMaiDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

public class KhuyenMaiBUS {
    private LinkedHashSet<KhuyenMaiDTO> setKM;
    private KhuyenMaiDAO daoKM;
    
    public KhuyenMaiBUS(){
        setKM = new LinkedHashSet<>();
        daoKM = new KhuyenMaiDAO();
        
        setKM = KhuyenMaiBUS.toSet(daoKM.getAllKhuyenMai());
    }
    
    public static LinkedHashSet<KhuyenMaiDTO> toSet(ResultSet rs)
    {
        LinkedHashSet<KhuyenMaiDTO> setPL = new LinkedHashSet<>();
        try{
            while(rs.next())
            {
                KhuyenMaiDTO that = new KhuyenMaiDTO(
                        rs.getString("id"),
                        rs.getTimestamp("startDatetime").toLocalDateTime(),
                        rs.getTimestamp("endDatetime").toLocalDateTime(),
                        rs.getInt("donViKM"),
                        rs.getInt("value"),
                        false,
                        rs.getString("des"));
                setPL.add(that);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return setPL;
    }
    
    public KhuyenMaiBUS(LinkedHashSet<KhuyenMaiDTO> setKM, KhuyenMaiDAO daoKM){
        this.setKM = setKM;
        this.daoKM = daoKM;
   }
    
   public LinkedHashSet<KhuyenMaiDTO> getSetKM(){
       return setKM;
   }   
   
   
   public void setSetKM(LinkedHashSet<KhuyenMaiDTO> setKM) {
       this.setKM = setKM;
   }
   
   public KhuyenMaiDAO getDaoKM()
   {
       return daoKM;
   }
   
   public void setDaoKM(KhuyenMaiDAO daoKM){
       this.daoKM = daoKM;
   }
   
    public int getAllCountKM(){
        ResultSet rs = daoKM.getAllCountKM();
        int count = -1;
        try {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }  
    
    public int getCountKMByDate(KhuyenMaiDTO km){
        ResultSet rs = daoKM.getCountKMByDate(km);
        int count = -1;
        try {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }  
    
    public void addKMWithData(KhuyenMaiDTO km){
        daoKM.addKMWithData(km);
    }
    
    public void updateKMById(KhuyenMaiDTO km){
        daoKM.updateKMById(km);
    }
    
    public void removeKMById(String id){
        daoKM.removeKMById(id);
    }
}
