package BUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import DAO.PhanLoaiDAO;
import DTO.PhanLoaiDTO;

public class PhanLoaiBUS {
    private LinkedHashSet<PhanLoaiDTO> setPL;
    private PhanLoaiDAO daoPL;
    
    public PhanLoaiBUS(){
        setPL = new LinkedHashSet<>();
        daoPL = new PhanLoaiDAO();
        
        setPL = PhanLoaiBUS.toSet(daoPL.getAllPhanLoai());
    }
    
    public static LinkedHashSet<PhanLoaiDTO> toSet(ResultSet rs)
    {
        LinkedHashSet<PhanLoaiDTO> setPL = new LinkedHashSet<>();
        try{
            while(rs.next())
            {
                PhanLoaiDTO that = new PhanLoaiDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        false);
                setPL.add(that);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return setPL;
    }
    
    public PhanLoaiBUS(LinkedHashSet<PhanLoaiDTO> setPL, PhanLoaiDAO daoPL){
        this.setPL = setPL;
        this.daoPL = daoPL;
    }
    
    public LinkedHashSet<PhanLoaiDTO> getSetPL()
    {
        return setPL;
    }
    
    public void setSetPL(LinkedHashSet<PhanLoaiDTO> setPL)
    {
        this.setPL = setPL;
    }
    
    public PhanLoaiDAO getDaoPL()
    {
        return daoPL;
    }
    
    public void setDaoPL(PhanLoaiDAO daoPL){
        this.daoPL = daoPL;
    }
    
    
    public LinkedHashSet<PhanLoaiDTO> getPhanLoaiById(String id){
        ResultSet rs = daoPL.getPhanLoaiById(id);
        try{
            while(rs.next())
            {
                PhanLoaiDTO that = new PhanLoaiDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        false);
                setPL.add(that);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return setPL;
   }
            
    public void removePLById(String id)
    {
        daoPL.removePLById(id);
    }
    
    public void updatePLById(PhanLoaiDTO pl)
    {
        daoPL.updatePLById(pl);
    }
    
    public void addPLWithData(PhanLoaiDTO pl){
        daoPL.addPLWithData(pl);
    }
    
    public int getCountPhanLoai(){
        ResultSet rs = daoPL.getCountPhanLoai();
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
