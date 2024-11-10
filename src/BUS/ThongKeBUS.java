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

    public LinkedHashSet<ThongKeDTO> thongKeTheoNgay(int thang, int nam) {
        return thongKeDAO.thongKeTheoNgay(thang, nam)
                .stream()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<ThongKeDTO> thongKeTheoThang(int nam) {
        return thongKeDAO.thongKeTheoThang(nam)
                .stream()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<ThongKeDTO> thongKeTheoNam() {
        return thongKeDAO.thongKeTheoNam()
                .stream()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public LinkedHashSet<ThongKeDTO> thongKeSanPhamBanChay(int thang, int nam) {
        return thongKeDAO.thongKeSanPhamBanChay(thang, nam)
                .stream()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
