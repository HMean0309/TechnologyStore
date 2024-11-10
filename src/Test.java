import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import BUS.KhachHangBUS;
import DTO.KhachHangDTO;

public class Test {
    public static void main(String[] args) {
        KhachHangDTO that = new KhachHangDTO(
                        "CUSTOMER000003",
                        "Hải Dương",
                        "0868505762",
                        "125 Nguyễn Thị Tần",
                        "Q8",
                        "P1",
                        "TP HCM",
                        false);
        KhachHangBUS busKH = new KhachHangBUS();
        //busKH.addKhachHang(that);

        busKH.getSetKH().forEach(System.out::println);
        for (KhachHangDTO kh : busKH.getSetKH()) {
            if (kh.getName().equals(that.getName())) {
                System.out.println("Có nhân viên tên Hải Dương");
            }
        }
    }
}
