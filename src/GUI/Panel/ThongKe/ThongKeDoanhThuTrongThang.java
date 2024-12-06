package GUI.Panel.ThongKe;

import BUS.ThongKeBUS;
import DTO.ThongKeDTO;
import GUI.Component.PanelBorderRadius;
import GUI.Component.TableSorter;
import GUI.Component.Chart.BarChart.Chart;
import GUI.Component.Chart.BarChart.ModelChart;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import helper.Formater;
import helper.JTableExporter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
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


public final class ThongKeDoanhThuTrongThang extends JPanel{

    PanelBorderRadius pnlChart;
    JPanel pnl_top;
    ThongKeBUS thongkeBUS;
    JMonthChooser monthchooser;
    Chart chart;
    private JTable tableThongKe;
    private JScrollPane scrollTableThongKe;
    private DefaultTableModel tblModel;
    private JYearChooser yearchooser;
    private JButton btnThongKe, btnReset, btnExport;

    public ThongKeDoanhThuTrongThang(ThongKeBUS thongkeBUS) {
        this.thongkeBUS = thongkeBUS;
        initComponent();
        int thang = monthchooser.getMonth() + 1;
        int nam = yearchooser.getYear();
        loadThongKeTungNgayTrongThang(thang, nam);
    }

    public void initComponent() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        pnl_top = new JPanel(new FlowLayout());
        JLabel lblChonThang = new JLabel("Chọn tháng");
        monthchooser = new JMonthChooser();
        JLabel lblChonNam = new JLabel("Chọn năm");
        yearchooser = new JYearChooser();
        pnl_top.add(lblChonThang);
        pnl_top.add(monthchooser);
        pnl_top.add(lblChonNam);
        pnl_top.add(yearchooser);
        btnThongKe = new JButton("Thống kê");
        pnl_top.add(btnThongKe);
        btnExport = new JButton("Xuất Excel");
        pnl_top.add(btnExport);

        btnExport.addActionListener((ActionEvent e) -> {
            try {
                JTableExporter.exportJTableToExcel(tableThongKe);
            } catch (IOException ex) {
                Logger.getLogger(ThongKeDoanhThuTrongThang.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btnThongKe.addActionListener((ActionEvent e) -> {
            int thang = monthchooser.getMonth() + 1;
            int nam = yearchooser.getYear();
            loadThongKeTungNgayTrongThang(thang, nam);
        });

        pnlChart = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(pnlChart, BoxLayout.Y_AXIS);
        pnlChart.setLayout(boxly);

        chart = new Chart();
        chart.addLegend("Vốn", new Color(245, 189, 135));
        chart.addLegend("Doanh thu", new Color(135, 189, 245));
        chart.addLegend("Lợi nhuận", new Color(189, 135, 245));
        chart.addData(new ModelChart("Tháng 1", new double[]{100, 150, 200}));
        chart.addData(new ModelChart("Tháng 2", new double[]{600, 750, 300}));
        chart.addData(new ModelChart("Tháng 3", new double[]{200, 350, 1000}));
        chart.addData(new ModelChart("Tháng 4", new double[]{480, 150, 750}));
        chart.addData(new ModelChart("Tháng 5", new double[]{100, 150, 200}));
        chart.addData(new ModelChart("Tháng 6", new double[]{600, 750, 300}));
        chart.addData(new ModelChart("Tháng 7", new double[]{200, 350, 1000}));
        chart.addData(new ModelChart("Tháng 8", new double[]{480, 150, 750}));
        chart.addData(new ModelChart("Tháng 9", new double[]{100, 150, 200}));
        chart.addData(new ModelChart("Tháng 10", new double[]{600, 750, 300}));
        chart.addData(new ModelChart("Tháng 11", new double[]{200, 350, 1000}));
        chart.addData(new ModelChart("Tháng 12", new double[]{480, 150, 750}));
        pnlChart.add(chart);

        tableThongKe = new JTable();
        scrollTableThongKe = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Ngày", "Vốn", "Doanh Thu", "Lợi nhuận"};
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
        TableSorter.configureTableColumnSorter(tableThongKe, 1, TableSorter.VND_CURRENCY_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableThongKe, 2, TableSorter.VND_CURRENCY_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableThongKe, 3, TableSorter.VND_CURRENCY_COMPARATOR);
        
        this.add(pnl_top, BorderLayout.NORTH);
        this.add(pnlChart, BorderLayout.CENTER);
        this.add(scrollTableThongKe, BorderLayout.SOUTH);
    }

    public void loadThongKeTungNgayTrongThang(int thang, int nam) {
        // Lấy danh sách thống kê từ cơ sở dữ liệu
        LinkedHashSet<ThongKeDTO> rawSet = thongkeBUS.getThongKeTungNgayTrongThang(thang, nam);
        List<ThongKeDTO> list = new ArrayList<>(rawSet);

        // Xác định số ngày trong tháng
        java.time.YearMonth yearMonth = java.time.YearMonth.of(nam, thang);
        int daysInMonth = yearMonth.lengthOfMonth();

        // Bổ sung dữ liệu mặc định cho các ngày không có
        for (int day = 1; day <= daysInMonth; day++) {
            String dateKey = String.format("%04d-%02d-%02d", nam, thang, day); // Định dạng yyyy-MM-dd
            boolean exists = list.stream().anyMatch(dto -> dto.getKey().equals(dateKey));
            if (!exists) {
                list.add(new ThongKeDTO(String.format("%04d-%02d-%02d", nam, thang, day), 0, 0)); // Thêm ngày mặc định với giá trị 0
            }
        }

        // Sắp xếp danh sách theo ngày
        list.sort((dto1, dto2) -> dto1.getKey().compareTo(dto2.getKey()));

        // Xóa biểu đồ cũ và khởi tạo biểu đồ mới
        pnlChart.remove(chart);
        chart = new Chart();
        chart.addLegend("Chi phí", new Color(245, 189, 135));
        chart.addLegend("Doanh thu", new Color(135, 189, 245));
        chart.addLegend("Lợi nhuận", new Color(189, 135, 245));

        // Tính toán và hiển thị biểu đồ
        int sum_chiPhi = 0;
        int sum_doanhThu = 0;
        int sum_loiNhuan = 0;

        // Duyệt danh sách và cập nhật biểu đồ
        for (int i = 0; i < list.size(); i++) {
            ThongKeDTO tk = list.get(i);
            sum_chiPhi += tk.getChiPhi(); // Chi phí
            sum_doanhThu += tk.getDoanhThu(); // Doanh thu
            sum_loiNhuan += (tk.getDoanhThu() - tk.getChiPhi());

            
            if ((i + 1) % 3 == 0 || i == list.size() - 1) {
                chart.addData(new ModelChart(
                    "Ngày " + Math.max(1, i - 1) + "->" + (i + 1),
                    new double[]{sum_chiPhi, sum_doanhThu, sum_loiNhuan}
                ));
                sum_chiPhi = 0;
                sum_doanhThu = 0;
                sum_loiNhuan = 0;
            }
        }

        chart.repaint();
        chart.validate();
        pnlChart.add(chart);
        pnlChart.repaint();
        pnlChart.validate();

        // Cập nhật bảng
        tblModel.setRowCount(0);
        for (ThongKeDTO tk : list) {
            tblModel.addRow(new Object[]{
                tk.getKey(),
                Formater.formatVND(tk.getChiPhi()), 
                Formater.formatVND(tk.getDoanhThu()), 
                Formater.formatVND(tk.getDoanhThu() - tk.getChiPhi())
            });
        }
    }

}
