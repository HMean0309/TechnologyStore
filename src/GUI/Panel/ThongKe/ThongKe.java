package GUI.Panel.ThongKe;

import BUS.ThongKeBUS;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public final class ThongKe extends JPanel {

    JTabbedPane tabbedPane;
    JPanel doanhthu, tongquan;
    Color BackgroundColor = new Color(240, 247, 250);
    ThongKeBUS thongkeBUS = new ThongKeBUS();
    private ThongKeSanPhamBanChay banchay;

    public ThongKe() {
        initComponent();
    }

    public void initComponent() {
        this.setLayout(new GridLayout(1, 1));
        this.setBackground(BackgroundColor);

        doanhthu = new ThongKeDoanhThu(thongkeBUS);
        tongquan = new ThongKeTongQuan(thongkeBUS);
        banchay = new ThongKeSanPhamBanChay(thongkeBUS);

        tabbedPane = new JTabbedPane();
        tabbedPane.setOpaque(false);
        tabbedPane.addTab("Tổng quan", tongquan);
        tabbedPane.addTab("Doanh thu", doanhthu);
        tabbedPane.addTab("Danh sách bán chạy", banchay);

        this.add(tabbedPane);
    }
}
