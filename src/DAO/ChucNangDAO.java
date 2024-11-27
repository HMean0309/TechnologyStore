package DAO;

import java.sql.ResultSet;

public class ChucNangDAO extends ObjectDAO {
    public ChucNangDAO() {
        super();
    }

    public ResultSet getAllChucNang() {
        super.connectDB();
        String query = "SELECT * FROM CHUC_NANG";
        ResultSet rs = super.executeQuery(query);
        return rs;
    }
}
