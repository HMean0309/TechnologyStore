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
//            int doanhThu = (int) (Math.random() * 10_000_000); // Doanh thu ngẫu nhiên
//            int chiPhi = (int) (Math.random() * 8_000_000);    // Chi phí ngẫu nhiên
//            if (chiPhi > doanhThu) chiPhi = doanhThu - (int) (Math.random() * 2_000_000); // Đảm bảo chi phí không lớn hơn doanh thu
//            mockData.add(new ThongKeDTO(String.format("Tháng %02d", i), doanhThu, chiPhi));
//        }
//        return mockData;
//    }
    
//    private int getDaysInMonth(int thang, int nam) {
//        switch (thang) {
//            case 2: // Tháng 2
//                return (nam % 4 == 0 && (nam % 100 != 0 || nam % 400 == 0)) ? 29 : 28;
//            case 4: case 6: case 9: case 11: // Các tháng có 30 ngày
//                return 30;
//            default: // Các tháng có 31 ngày
//                return 31;
//        }
//    }
//    public LinkedHashSet<ThongKeDTO> getThongKeTungNgayTrongThang(int thang, int nam) {
//        LinkedHashSet<ThongKeDTO> mockData = new LinkedHashSet<>();
//        int daysInMonth = getDaysInMonth(thang, nam); // Lấy số ngày thực tế trong tháng
//        for (int day = 1; day <= daysInMonth; day++) {
//            int baseDoanhThu = 5_000_000;
//            int baseChiPhi = 3_000_000;
//
//            int doanhThu = baseDoanhThu + (int) (Math.random() * 2_000_000) - 1_000_000;
//            int chiPhi = baseChiPhi + (int) (Math.random() * 1_500_000) - 750_000;
//
//            if (chiPhi > doanhThu) chiPhi = doanhThu - (int) (Math.random() * 500_000);
//
//            mockData.add(new ThongKeDTO(
//                String.format("%04d-%02d-%02d", nam, thang, day),
//                doanhThu,
//                chiPhi
//            ));
//        }
//
//        return mockData;
//    }

    public LinkedHashSet<ThongKeDTO> thongKeSanPhamBanChay(int thang, int nam) {
        return thongKeDAO.thongKeSanPhamBanChay(thang, nam)
                .stream()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
