package GUI.Panel.ThongKe;

import BUS.ThongKeBUS;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public final class ThongKe extends JPanel {

    JTabbedPane tabbedPane;
    JPanel doanhthu;
    Color BackgroundColor = new Color(240, 247, 250);
    ThongKeBUS thongkeBUS = new ThongKeBUS();

    public ThongKe() {
        initComponent();
    }

    public void initComponent() {
        this.setLayout(new GridLayout(1, 1));
        this.setBackground(BackgroundColor);

        doanhthu = new ThongKeDoanhThu(thongkeBUS);

        tabbedPane = new JTabbedPane();
        tabbedPane.setOpaque(false);

        tabbedPane.addTab("Doanh thu", doanhthu);

        this.add(tabbedPane);
    }
}
