package DAO;

import DTO.SanPhamDTO;

import java.sql.ResultSet;

public class SanPhamDAO extends ObjectDAO {
    public SanPhamDAO() {
        super();
    }

    public ResultSet getAllSanPham() {
        super.connectDB();
        String query = "SELECT * FROM sanpham_count;";

        return super.executeQuery(query);
    }

    public ResultSet getCountSanPham() {
        super.connectDB();
        String query = "SELECT COUNT(*) FROM SAN_PHAM;";
        ResultSet rs = super.executeQuery(query);

        return rs;
    }


    // Thay doi san pham dua tren id
    public void updateSP(SanPhamDTO product) {
        super.connectDB();
        String query = "UPDATE SAN_PHAM SET name = ?, id_cate = ?, baohanh = ?, img = ? WHERE id = ? ;";
        Object[] params = { product.getName(), product.getIdCate(), product.getBaoHanh(), product.getImg(), product.getId() };

        super.executeNonQuery(query, params);

    }

    // Xoa san pham bang id
    public void removeSPById(String id) {
        super.connectDB();
        String query = "UPDATE SAN_PHAM SET isDelete = 1 WHERE id = ? ;";
        Object[] params = { id };

        super.executeNonQuery(query, params);

    }

    // Them du lieu vao san pham
    public void addSPWithData(SanPhamDTO product) {
        super.connectDB();
        String query = "INSERT INTO SAN_PHAM (id,name,id_cate,baohanh,img)" + "VALUES(?,?,?,?,?) ;";
        Object[] params = { product.getId(), product.getName(), product.getIdCate(), product.getBaoHanh(), product.getImg() };

        super.executeNonQuery(query, params);

    }
}
