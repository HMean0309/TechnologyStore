package DAO;

import DTO.OptionSanPhamDTO;

import java.sql.ResultSet;

public class OptionSanPhamDAO extends ObjectDAO {
    public OptionSanPhamDAO() {
        super();
    }

    public ResultSet getAllOSP() {
        super.connectDB();
        String query = "SELECT * FROM OPTION_SAN_PHAM WHERE isDelete = 0 ;";

        return super.executeQuery(query);
    }

    public void addOSPWithData(OptionSanPhamDTO OSP) {
        super.connectDB();
        String query = "INSERT INTO OPTION_SAN_PHAM (id_sp,color)" + "VALUES(?,?) ;";
        Object[] params =
                {
                        OSP.getIdSP(), OSP.getColor()
                };

        super.executeNonQuery(query, params);
    }

    public void removeOSP(OptionSanPhamDTO OSP) {
        super.connectDB();
        String query = "DELETE FROM OPTION_SAN_PHAM WHERE id_sp = ? and color = ? ;";
        Object[] params =
                {
                        OSP.getIdSP(), OSP.getColor()
                };
        super.executeNonQuery(query, params);
    }
}
