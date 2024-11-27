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

//    public LinkedHashSet<ThongKeDTO> getThongKeTheoNam(int year_start, int year_end) {
//        LinkedHashSet<ThongKeDTO> result = new LinkedHashSet<>();
//        for (int year = year_start; year <= year_end; year++) {
//            int doanhThu = 10000000 + (year - year_start) * 5000000;
//            int chiPhi = 5000000 + (year - year_start) * 2000000;
//            result.add(new ThongKeDTO(String.valueOf(year), doanhThu, chiPhi));
//        }
//        return result;
//    }


//    public LinkedHashSet<ThongKeDTO> getThongKeTheoThang(int nam) {
//        LinkedHashSet<ThongKeDTO> mockData = new LinkedHashSet<>();
//        for (int i = 1; i <= 12; i++) {
//            int doanhThu = 1000000 * i + (nam % 10) * 500000;
//            int chiPhi = 500000 * i + (nam % 10) * 200000;
//            mockData.add(new ThongKeDTO("Tháng " + i , doanhThu, chiPhi));
//        }
//        return mockData;
//    }
//    public LinkedHashSet<ThongKeDTO> getThongKeTungNgayTrongThang(int thang, int nam) {
//        LinkedHashSet<ThongKeDTO> mockData = new LinkedHashSet<>();
//        for (int day = 1; day <= 30; day++) {
//            int doanhThu = 1000000 * day + (nam % 10) * 500000; 
//            int chiPhi = 500000 * day + (thang % 5) * 200000;
//            mockData.add(new ThongKeDTO("Ngày " + day + "/" + thang + "/" + nam, doanhThu, chiPhi));
//        }
//        return mockData;
//    }

    public LinkedHashSet<ThongKeDTO> thongKeSanPhamBanChay(int thang, int nam) {
        return thongKeDAO.thongKeSanPhamBanChay(thang, nam)
                .stream()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
