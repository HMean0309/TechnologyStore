package BUS;

import DAO.ThongKeDAO;
import DTO.ThongKeDTO;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class ThongKeBUS {
    private ThongKeDAO thongKeDAO;

    public ThongKeBUS() {
        thongKeDAO = new ThongKeDAO();
    }

    public LinkedHashSet<ThongKeDTO> getThongKeTungNgayTrongThang(int thang, int nam) {
        return thongKeDAO.thongKeTheoNgay(thang, nam)
                .stream()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<ThongKeDTO> getThongKeTheoThang(int nam) {
        return thongKeDAO.thongKeTheoThang(nam)
                .stream()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
    
    public LinkedHashSet<ThongKeDTO> getThongKeTheoNam(int year_start, int year_end) {
        return thongKeDAO.thongKeTheoNam(year_start, year_end)
                .stream()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
    
    public LinkedHashSet<ThongKeDTO> getThongKeTongQuan(){
        return thongKeDAO.thongKe7NgayGanNhat()
                .stream()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<ThongKeDTO> getSanPhamBanChay(int thang, int nam) {
        return thongKeDAO.thongKeSanPhamBanChay(thang, nam)
                .stream()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
