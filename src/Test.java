import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.SanPhamDAO;

public class Test {
    public static void main(String[] args) {
        SanPhamDAO sPhamDAO = new SanPhamDAO();
        String idCate = "CATE002";
        ResultSet rs = sPhamDAO.getSanPhamOfPhanLoai(idCate);
        try {
            while (rs.next()) {
                System.out.println(rs.getString("id"));
                System.out.println(rs.getString("name"));
                System.out.println(rs.getString("id_cate"));
                System.out.println("//");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("TỔNG SP CỦA " + idCate + " :" + sPhamDAO.getCountSPOfPhanLoai(idCate));
    }
}
