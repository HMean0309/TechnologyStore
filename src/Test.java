import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedHashSet;

import BUS.SanPhamBUS;
import DAO.SanPhamDAO;
import DTO.SanPhamDTO;

public class Test {
    public static void main(String[] args) {
        //Test DAO
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
        
        //Test BUS
        SanPhamBUS sPhamBUS = new SanPhamBUS();
        LinkedHashSet<SanPhamDTO> result = sPhamBUS.getSetSP();
        result.forEach(System.out::println);
        
        System.out.println("TỔNG SP CỦA " + idCate + " :" + sPhamBUS.getCountSPOfPhanLoai(idCate));
    }
}
