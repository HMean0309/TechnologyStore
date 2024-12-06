package GUI.Panel.ThongKe;

import BUS.ThongKeBUS;
import DTO.ThongKeDTO;
import GUI.Component.NumericDocumentFilter;
import GUI.Component.PanelBorderRadius;
import GUI.Component.TableSorter;
import GUI.Component.Chart.BarChart.Chart;
import GUI.Component.Chart.BarChart.ModelChart;
import helper.Formater;
import helper.JTableExporter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.PlainDocument;

public final class ThongKeDoanhThuTungNam extends JPanel implements ActionListener {

    private PanelBorderRadius pnlChart;
    private JPanel pnlTop;
    private ThongKeBUS thongkeBUS;
    private JTextField yearStartField, yearEndField;
    private Chart chart;
    private JButton btnReset, btnThongKe, btnExport;
    private JTable tableThongKe;
    private JScrollPane scrollTableThongKe;
    private DefaultTableModel tblModel;
    private ArrayList<ThongKeDTO> dataset;

    public ThongKeDoanhThuTungNam(ThongKeBUS thongkeBUS) {
        this.thongkeBUS = thongkeBUS;
        this.dataset = new ArrayList<>();
        initComponent();
        loadDefaultData();
        loadDataTable(dataset);
        loadDataChart(dataset);
    }

    // Tải dữ liệu mặc định: 5 năm gần nhất từ năm hiện tại
    private void loadDefaultData() {
        int currentYear = Year.now().getValue();
        int yearStart = currentYear - 4;
        int yearEnd = currentYear;

        LinkedHashSet<ThongKeDTO> fetchedData = thongkeBUS.getThongKeTheoNam(yearStart, yearEnd);

        dataset = new ArrayList<>(fetchedData);

        for (int year = yearStart; year <= yearEnd; year++) {
            final int current_year = year;
            boolean exists = dataset.stream().anyMatch(dto -> Integer.parseInt(dto.getKey()) == current_year);
            if (!exists) {
                dataset.add(new ThongKeDTO(String.valueOf(year), 0, 0));
            }
        }

        dataset.sort((dto1, dto2) -> Integer.compare(
                Integer.parseInt(dto1.getKey()), 
                Integer.parseInt(dto2.getKey())
        ));
    }

    private void loadDataTable(ArrayList<ThongKeDTO> list) {
        tblModel.setRowCount(0);
        for (ThongKeDTO item : list) {
            int doanhThu = item.getDoanhThu();
            int chiPhi = item.getChiPhi();
            int loiNhuan = doanhThu - chiPhi;

            tblModel.addRow(new Object[]{
                item.getKey(), Formater.formatVND(chiPhi), Formater.formatVND(doanhThu), Formater.formatVND(loiNhuan)
            });
        }
    }

    private void loadDataChart(ArrayList<ThongKeDTO> list) {
        pnlChart.removeAll();
        chart = new Chart();
        chart.addLegend("Chi phí", new Color(245, 189, 135));
        chart.addLegend("Doanh thu", new Color(135, 189, 245));
        chart.addLegend("Lợi nhuận", new Color(189, 135, 245));

        for (ThongKeDTO item : list) {
            int chiPhi = item.getChiPhi();
            int doanhThu = item.getDoanhThu();
            int loiNhuan = doanhThu - chiPhi;

            chart.addData(new ModelChart("Năm " + item.getKey(), new double[]{chiPhi, doanhThu, loiNhuan}));
        }
        chart.repaint();
        pnlChart.add(chart);
        pnlChart.validate();
    }

    public void initComponent() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));


        pnlTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblYearStart = new JLabel("Từ năm:");
        JLabel lblYearEnd = new JLabel("Đến năm:");

        yearStartField = new JTextField(10);
        yearEndField = new JTextField(10);

        PlainDocument docStart = (PlainDocument) yearStartField.getDocument();
        docStart.setDocumentFilter(new NumericDocumentFilter());
        PlainDocument docEnd = (PlainDocument) yearEndField.getDocument();
        docEnd.setDocumentFilter(new NumericDocumentFilter());

        btnThongKe = new JButton("Thống kê");
        btnReset = new JButton("Làm mới");
        btnExport = new JButton("Xuất Excel");

        btnThongKe.addActionListener(this);
        btnReset.addActionListener(this);
        btnExport.addActionListener(this);

        pnlTop.add(lblYearStart);
        pnlTop.add(yearStartField);
        pnlTop.add(lblYearEnd);
        pnlTop.add(yearEndField);
        pnlTop.add(btnThongKe);
        pnlTop.add(btnReset);
        pnlTop.add(btnExport);

        pnlChart = new PanelBorderRadius();
        pnlChart.setLayout(new BoxLayout(pnlChart, BoxLayout.Y_AXIS));

        tblModel = new DefaultTableModel(new String[]{"Năm", "Vốn", "Doanh thu", "Lợi nhuận"}, 0);
        tableThongKe = new JTable(tblModel);
        tableThongKe.setAutoCreateRowSorter(true);
        tableThongKe.setDefaultEditor(Object.class, null);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableThongKe.setDefaultRenderer(Object.class, centerRenderer);

        TableSorter.configureTableColumnSorter(tableThongKe, 0, TableSorter.INTEGER_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableThongKe, 1, TableSorter.VND_CURRENCY_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableThongKe, 2, TableSorter.VND_CURRENCY_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableThongKe, 3, TableSorter.VND_CURRENCY_COMPARATOR);

        scrollTableThongKe = new JScrollPane(tableThongKe);
        scrollTableThongKe.setPreferredSize(new Dimension(0, 300));

        this.add(pnlTop, BorderLayout.NORTH);
        this.add(pnlChart, BorderLayout.CENTER);
        this.add(scrollTableThongKe, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == btnThongKe) {
            try {
                String startYearText = yearStartField.getText().trim();
                String endYearText = yearEndField.getText().trim();

                if (startYearText.isEmpty() || endYearText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập cả năm bắt đầu và năm kết thúc!");
                    return;
                }

                int startYear = Integer.parseInt(startYearText);
                int endYear = Integer.parseInt(endYearText);
                int currentYear = Year.now().getValue();

                // Kiểm tra tính hợp lệ của năm
                if (startYear < 2015) {
                    JOptionPane.showMessageDialog(this, "Năm bắt đầu phải từ 2015 trở đi!");
                    return;
                }
                if (endYear > currentYear) {
                    JOptionPane.showMessageDialog(this, "Năm kết thúc không được vượt quá năm hiện tại (" + currentYear + ")!");
                    return;
                }
                if (startYear > endYear) {
                    JOptionPane.showMessageDialog(this, "Năm bắt đầu không được lớn hơn năm kết thúc!");
                    return;
                }
                if ((endYear - startYear) > 5) {
                    JOptionPane.showMessageDialog(this, "Khoảng thời gian giữa năm bắt đầu và năm kết thúc không được lớn hơn 5 năm!");
                    return;
                }

                // Lấy dữ liệu thống kê
                LinkedHashSet<ThongKeDTO> fetchedData = thongkeBUS.getThongKeTheoNam(startYear, endYear);
                dataset = new ArrayList<>(fetchedData);

                for (int year = startYear; year <= endYear; year++) {
                    final int current_year = year;
                    boolean exists = dataset.stream().anyMatch(dto -> Integer.parseInt(dto.getKey()) == current_year);
                    if (!exists) {
                        dataset.add(new ThongKeDTO(String.valueOf(year), 0, 0));
                    }
                }

                dataset.sort((dto1, dto2) -> Integer.compare(
                        Integer.parseInt(dto1.getKey()), 
                        Integer.parseInt(dto2.getKey())
                ));

                loadDataTable(dataset);
                loadDataChart(dataset);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập năm hợp lệ (số nguyên)!");
            }
        } else if (source == btnReset) {
            yearStartField.setText("");
            yearEndField.setText("");
            loadDefaultData();
            loadDataTable(dataset);
            loadDataChart(dataset);
        } else if (source == btnExport) {
            try {
                JTableExporter.exportJTableToExcel(tableThongKe);
                
            } catch (IOException ex) {
                Logger.getLogger(ThongKeDoanhThuTungNam.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
    }

}
