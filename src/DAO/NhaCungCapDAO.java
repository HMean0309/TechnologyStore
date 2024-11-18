package DAO;

import DTO.NhaCungCapDTO;
import java.sql.ResultSet;

public class NhaCungCapDAO extends ObjectDAO {
    public NhaCungCapDAO(){
        super();
    }
    
    public ResultSet getAllNhaCungCap(){
        super.connectDB();
        String query = "SELECT * FROM ncc WHERE isDelete = 0;";
        ResultSet rs = super.executeQuery(query);
        
        return rs;
    }

    public ResultSet getCountNhaCungCap(){
        super.connectDB();
        String query = "SELECT COUNT(*) FROM ncc WHERE isDelete = 0;";
        ResultSet rs = super.executeQuery(query);
        
        return rs;
    }

    public void addNhaCungCap(NhaCungCapDTO nhaCungCap) {
        super.connectDB();
        String query = "INSERT INTO ncc (id, name, phone, address, ward, district, city) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Object[] params = {
            nhaCungCap.getId(),
            nhaCungCap.getName(),
            nhaCungCap.getPhone(),
            nhaCungCap.getAddress(),
            nhaCungCap.getWard(),
            nhaCungCap.getDistrict(),
            nhaCungCap.getCity()
        };
        super.executeNonQuery(query, params);
        
    }

    public void updateNhaCungCap(NhaCungCapDTO nhaCungCap) {
        super.connectDB();
        String query = "UPDATE ncc SET name = ?, phone = ?, address = ?, ward = ?, district = ?, city = ? WHERE id = ?";
        Object[] params = {
            nhaCungCap.getName(),
            nhaCungCap.getPhone(),
            nhaCungCap.getAddress(),
            nhaCungCap.getWard(),
            nhaCungCap.getDistrict(),
            nhaCungCap.getCity(),
            nhaCungCap.getId()
        };
        super.executeNonQuery(query, params);
        
    }

    public void deleteNhaCungCap(String id) {
        super.connectDB();
        String query = "UPDATE ncc SET isDelete = 1 WHERE id = ?";
        Object[] params = {id};
        super.executeNonQuery(query, params);
        
    }
}
