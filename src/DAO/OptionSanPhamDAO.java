package DAO;

import DTO.OptionSanPhamDTO;

import java.sql.ResultSet;

public class OptionSanPhamDAO extends ObjectDAO
{

    public OptionSanPhamDAO()
    {
        super();
    }

    public ResultSet getAllOSP()
    {
        super.connectDB();
        String query = "SELECT * FROM OPTION_SAN_PHAM WHERE isDelete = 0 ;";
        
        return super.executeQuery(query);
    }

    public ResultSet getOSPById(String idsp)
    {
        super.connectDB();
        String query = "SELECT * FROM OPTION_SAN_PHAM WHERE id_sp = ? AND isDelete = 0 ;";
        Object[] params =
        {
            idsp
        };
        
        return super.executeQuery(query, params);
    }

    public ResultSet getCountAllOSP()
    {
        super.connectDB();
        String query = "SELECT COUNT(*) FROM OPTION_SAN_PHAM WHERE isDelete = 0 ;";
        
        return super.executeQuery(query);
    }

    public ResultSet getColorOSPById(String id)
    {
        super.connectDB();
        String query = "SELECT color FROM OPTION_SAN_PHAM WHERE isDelete = 0  AND id_sp = ? ;";
        Object[] params =
        {
            id
        };
        
        return super.executeQuery(query, params);
    }

    public void addOSPWithData(OptionSanPhamDTO OSP)
    {
        super.connectDB();
        String query = "INSERT INTO OPTION_SAN_PHAM (id_sp,color)" + "VALUES(?,?) ;";
        Object[] params =
        {
            OSP.getIdSP(), OSP.getColor()
        };
        
        super.executeNonQuery(query, params);
    }

    public void updateOSPById(OptionSanPhamDTO OSP)
    {
        super.connectDB();
        String query = "UPDATE OPTION_SAN_PHAM SET color = ? WHERE id_sp = ? AND isDelete = 0 ;";
        Object[] params =
        {
            OSP.getColor(), OSP.getIdSP()
        };
        
        super.executeNonQuery(query, params);
    }

    public void removeOSPById(OptionSanPhamDTO OSP)
    {
        super.connectDB();
        String query = "UPDATE OPTION_SAN_PHAM SET isDelete = 1 WHERE id_sp = ? ;";
        Object[] params =
        {
            OSP.getColor(), OSP.getIdSP()
        };
        
        super.executeNonQuery(query, params);
    }
}
