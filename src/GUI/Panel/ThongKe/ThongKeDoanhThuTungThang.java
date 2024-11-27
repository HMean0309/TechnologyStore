package GUI.Panel.ThongKe;

import BUS.ThongKeBUS;
import DTO.ThongKeDTO;
import GUI.Component.PanelBorderRadius;
import GUI.Component.TableSorter;
import GUI.Component.Chart.BarChart.Chart;
import GUI.Component.Chart.BarChart.ModelChart;
import com.toedter.calendar.JYearChooser;
import helper.Formater;
import helper.JTableExporter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public final class ThongKeDoanhThuTungThang extends JPanel {

    private PanelBorderRadius pnlChart;
    private JPanel pnl_top;
    private ThongKeBUS thongkeBUS;
    private JYearChooser yearchooser;
    private Chart chart;
    private JButton export, btnReset;
    private JTable tableThongKe;
    private JScrollPane scrollTableThongKe;
    private DefaultTableModel tblModel;

    public ThongKeDoanhThuTungThang(ThongKeBUS thongkeBUS) {
        this.thongkeBUS = thongkeBUS;
        initComponent();
        
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        yearchooser.setYear(currentYear);

        loadThongKeThang(currentYear);
    }

    public void initComponent() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        pnl_top = new JPanel(new FlowLayout());
        JLabel lblChonNam = new JLabel("Chọn năm thống kê");
        yearchooser = new JYearChooser();

        // Lắng nghe sự thay đổi năm
        yearchooser.addPropertyChangeListener("year", (PropertyChangeEvent e) -> {
            int year = (Integer) e.getNewValue();
            loadThongKeThang(year);
        });

        // Nút xuất Excel
        export = new JButton("Xuất Excel");
        export.addActionListener(e -> exportTableToExcel());

        // Nút làm mới
        btnReset = new JButton("Làm mới");
        btnReset.addActionListener(e -> {
            int defaultYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
            yearchooser.setYear(defaultYear); 
            loadThongKeThang(defaultYear); 
        });

        pnl_top.add(lblChonNam);
        pnl_top.add(yearchooser);
        pnl_top.add(export);
        pnl_top.add(btnReset);

        pnlChart = new PanelBorderRadius();
        pnlChart.setLayout(new BoxLayout(pnlChart, BoxLayout.Y_AXIS));
        chart = new Chart();
        chart.addLegend("Chi phí", new Color(245, 189, 135));
        chart.addLegend("Doanh thu", new Color(135, 189, 245));
        chart.addLegend("Lợi nhuận", new Color(189, 135, 245));
        pnlChart.add(chart);

        tableThongKe = new JTable();
        scrollTableThongKe = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = {"Tháng", "Doanh thu", "Chi phí", "Lợi nhuận"};
        tblModel.setColumnIdentifiers(header);
        tableThongKe.setModel(tblModel);
        tableThongKe.setAutoCreateRowSorter(true);
        tableThongKe.setDefaultEditor(Object.class, null);
        scrollTableThongKe.setViewportView(tableThongKe);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableThongKe.setDefaultRenderer(Object.class, centerRenderer);
        tableThongKe.setFocusable(false);
        scrollTableThongKe.setPreferredSize(new Dimension(0, 300));

        TableSorter.configureTableColumnSorter(tableThongKe, 0, TableSorter.STRING_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableThongKe, 1, TableSorter.VND_CURRENCY_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableThongKe, 2, TableSorter.NUMERIC_COMPARATOR);

        this.add(pnl_top, BorderLayout.NORTH);
        this.add(pnlChart, BorderLayout.CENTER);
        this.add(scrollTableThongKe, BorderLayout.SOUTH);
    }

    public void loadThongKeThang(int nam) {
        LinkedHashSet<ThongKeDTO> rawSet = thongkeBUS.getThongKeTheoThang(nam);
        List<ThongKeDTO> list = new ArrayList<>(rawSet);

        // Bổ sung dữ liệu mặc định cho từng tháng nếu thiếu
        for (int month = 1; month <= 12; month++) {
            final String monthKey = String.format("Tháng %02d", month);
            boolean exists = list.stream().anyMatch(dto -> dto.getKey().equals(monthKey));
            if (!exists) {
                list.add(new ThongKeDTO(monthKey, 0, 0));
            }
        }

        // Sắp xếp danh sách theo tháng
        list.sort((dto1, dto2) -> dto1.getKey().compareTo(dto2.getKey()));

        // Cập nhật biểu đồ
        pnlChart.remove(chart);
        chart = new Chart();
        chart.addLegend("Chi phí", new Color(245, 189, 135));
        chart.addLegend("Doanh thu", new Color(135, 189, 245));
        chart.addLegend("Lợi nhuận", new Color(189, 135, 245));

        // Duyệt danh sách và thêm dữ liệu vào biểu đồ
        for (ThongKeDTO tk : list) {
            int loiNhuan = tk.getValue() - tk.getCount();
            chart.addData(new ModelChart(
                tk.getKey(),
                new double[]{tk.getValue(), tk.getCount(), loiNhuan}
            ));
        }

        // Cập nhật biểu đồ
        chart.repaint();
        chart.validate();
        pnlChart.add(chart);
        pnlChart.repaint();
        pnlChart.validate();

        // Cập nhật bảng
        tblModel.setRowCount(0);
        for (ThongKeDTO tk : list) {
            int loiNhuan = tk.getValue() - tk.getCount();
            tblModel.addRow(new Object[]{
                tk.getKey(),
                Formater.formatVND(tk.getValue()),
                tk.getCount(),
                Formater.formatVND(loiNhuan)
            });
        }
    }

    private void exportTableToExcel() {
        try {
            JTableExporter.exportJTableToExcel(tableThongKe);
        } catch (IOException ex) {
            Logger.getLogger(ThongKeDoanhThuTungThang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

