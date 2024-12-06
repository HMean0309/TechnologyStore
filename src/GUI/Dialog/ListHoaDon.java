/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog;

import BUS.BaoHanhBUS;
import BUS.HoaDonBUS;
import DTO.HoaDonDTO;
import DTO.NhanVienDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.TableSorter;
import GUI.Panel.BaoHanh;
import GUI.Panel.TaoBaoHanh;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import helper.Formater;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class ListHoaDon extends JDialog {

    private BaoHanh guiBaoHanh;
    private JTable tableHoaDon;
    private JScrollPane scrollTableHoaDon;
    private DefaultTableModel tblModel;
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    public HoaDonBUS hoadonBUS = new HoaDonBUS();
    public ArrayList<HoaDonDTO> dataTable;
    public ArrayList<HoaDonDTO> resultTable;
    private JTextField tfSearch;
    private JPanel panelSearch;
    private JPanel panelTable;
    private HoaDonDTO hoaDon;

    public HoaDonDTO getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDonDTO hoaDon) {
        this.hoaDon = hoaDon;
    }

    NhanVienDTO nv;

    public ListHoaDon(NhanVienDTO nv, BaoHanh guiBaoHanh, JFrame owner, String title, boolean modal) {
        super(owner, title, modal);
        this.guiBaoHanh = guiBaoHanh;
        this.nv = nv;
        ArrayList<String> listIDHDUseBaoHanh = BaoHanhBUS.getInstance().getAllIDHDUseBaoHanh();
        if (listIDHDUseBaoHanh.isEmpty()) {
            dataTable = new ArrayList<>(hoadonBUS.getSetHD());
        } else {
            LinkedHashSet<HoaDonDTO> allHD = hoadonBUS.getSetHD();
            LinkedHashSet<HoaDonDTO> filter = allHD.stream()
                    .filter(hoaDonDTO -> !listIDHDUseBaoHanh.contains(hoaDonDTO.getId()))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
            hoadonBUS.setSetHD(filter);
            dataTable = new ArrayList<>(filter);
        }

        resultTable = dataTable;
        init();
        tableHoaDon.setDefaultEditor(Object.class, null);
        loadDataTable();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void init() {
        this.setSize(new Dimension(850, 600));
        this.setLayout(new BorderLayout());

        //Panel Search
        panelSearch = new JPanel(new BorderLayout());
        panelSearch.setSize(new Dimension(0, 150));
        panelSearch.setBorder(new EmptyBorder(20, 20, 20, 20));
        JLabel jLabelSearch = new JLabel("Tìm kiếm  ");
        jLabelSearch.setSize(new Dimension(100, 0));
        tfSearch = new JTextField();
        tfSearch.setFont(new Font(FlatRobotoFont.FAMILY, 0, 13));
        tfSearch.putClientProperty("JTextField.placeholderText", "Tìm kiếm hóa đơn....");
        tfSearch.putClientProperty("JTextField.showClearButton", true);
        tfSearch.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String type = HoaDonBUS.typeSearch[0];
                String txt = tfSearch.getText().trim();
                resultTable = new ArrayList<>(hoadonBUS.search(txt, type));
                loadDataTable(resultTable);
            }
        });

        //Button Chọn hóa đơn
        ButtonCustom buttonChoose = new ButtonCustom("Chọn hóa đơn", "success", 14);
        buttonChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = getRow();
                if (index < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn!)", "Thông báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    hoaDon = resultTable.get(index);
                    TaoBaoHanh baoHanh = new TaoBaoHanh(guiBaoHanh.m, hoaDon, nv);
                    guiBaoHanh.m.setPanel(baoHanh);
                    System.out.println(hoaDon.getId());
                    dispose();
                }
            }
        });

        panelSearch.add(jLabelSearch, BorderLayout.WEST);
        panelSearch.add(tfSearch, BorderLayout.CENTER);
        panelSearch.add(buttonChoose, BorderLayout.EAST);
        this.add(panelSearch, BorderLayout.NORTH);

        panelTable = new JPanel();
        panelSearch.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelTable.setLayout(new GridLayout(1, 1));

        tableHoaDon = new JTable();
        scrollTableHoaDon = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{ "Mã hóa đơn", "Khách Hàng", "Người lập phiếu", "Tổng tiền", "Ngày lập" };
        tblModel.setColumnIdentifiers(header);
        tableHoaDon.setModel(tblModel);
        tableHoaDon.setFocusable(false);
        scrollTableHoaDon.setViewportView(tableHoaDon);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableHoaDon.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableHoaDon.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableHoaDon.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tableHoaDon.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tableHoaDon.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        tableHoaDon.setAutoCreateRowSorter(true);
        TableSorter.configureTableColumnSorter(tableHoaDon, 0, TableSorter.STRING_COMPARATOR);

        panelTable.add(scrollTableHoaDon);
        this.add(panelTable, BorderLayout.CENTER);


    }

    public int getRow() {
        return tableHoaDon.getSelectedRow();
    }

    public void loadDataTable() {
        this.dataTable = new ArrayList<>(hoadonBUS.getSetHD());
        resultTable = dataTable;
        tblModel.setRowCount(0);
        for (HoaDonDTO hoadon : dataTable) {
            tblModel.addRow(new Object[]{
                    hoadon.getId(), hoadon.getNameKhachHang(), hoadon.getNameNhanVien(),
                    Formater.formatVND(hoadon.getTotal()),
                    Formater.formatTime(hoadon.getNgayLap())
            });
        }
    }

    public void loadDataTable(ArrayList<HoaDonDTO> data) {
        tblModel.setRowCount(0);
        resultTable = data;
        for (HoaDonDTO hoadon : data) {
            tblModel.addRow(new Object[]{
                    hoadon.getId(), hoadon.getNameKhachHang(), hoadon.getNameNhanVien(),
                    Formater.formatVND(hoadon.getTotal()),
                    Formater.formatTime(hoadon.getNgayLap())
            });
        }
    }
}
