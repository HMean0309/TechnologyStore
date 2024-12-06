package DAO;

import DTO.NhaCungCapDTO;

import java.sql.ResultSet;

public class NhaCungCapDAO extends ObjectDAO {
    public NhaCungCapDAO() {
        super();
    }

    public static NhaCungCapDAO getInstance() {
        return new NhaCungCapDAO();
    }

    public ResultSet getAllNhaCungCap() {
        super.connectDB();
        String query = "SELECT * FROM NCC WHERE isDelete = 0;";
        ResultSet rs = super.executeQuery(query);

        return rs;
    }

    public ResultSet getCountNhaCungCap() {
        super.connectDB();
        String query = "SELECT COUNT(*) FROM NCC;";
        ResultSet rs = super.executeQuery(query);

        return rs;
    }

    public void addNhaCungCap(NhaCungCapDTO nhaCungCap) {
        super.connectDB();
        String query = "INSERT INTO NCC (id, name, phone, address, ward, district, city) VALUES (?, ?, ?, ?, ?, ?, ?)";
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
        String query = "UPDATE NCC SET name = ?, phone = ?, address = ?, ward = ?, district = ?, city = ? WHERE id = ?";
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

    public void removeNhaCungCap(String id) {
        super.connectDB();
        String query = "UPDATE NCC SET isDelete = 1 WHERE id = ?";
        Object[] params = { id };
        super.executeNonQuery(query, params);

    }
    
    public NhaCungCapDTO selectById(String id) {
        super.connectDB();
        String query = "SELECT * FROM NCC WHERE id = ? ";
        Object[] params = { id };
        ResultSet rs = super.executeQuery(query, params);
        NhaCungCapDTO nhaCungCap = null;

        try {
            if (rs.next()) {
                nhaCungCap = new NhaCungCapDTO();
                nhaCungCap.setId(rs.getString("id"));
                nhaCungCap.setName(rs.getString("name"));
                nhaCungCap.setPhone(rs.getString("phone"));
                nhaCungCap.setAddress(rs.getString("address"));
                nhaCungCap.setWard(rs.getString("ward"));
                nhaCungCap.setDistrict(rs.getString("district"));
                nhaCungCap.setCity(rs.getString("city"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.closeDB();
        }

        return nhaCungCap;
    }
}
