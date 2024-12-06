/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog;

import BUS.KhachHangBUS;
import DTO.KhachHangDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.TableSorter;
import GUI.Panel.TaoHoaDon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

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

public class ListKhachHang extends JDialog {

    private TaoHoaDon taoHoaDon;
    private JTable tableKhachHang;
    private JScrollPane scrollTableKhachHang;
    private DefaultTableModel tblModel;
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    public KhachHangBUS khachhangBUS = new KhachHangBUS();
    public ArrayList<KhachHangDTO> dataTable = new ArrayList<>(khachhangBUS.getAllKhachHang());
    public ArrayList<KhachHangDTO> resultTable = dataTable;
    private JTextField tfSearch;
    private JPanel panelSearch;
    private JPanel panelTable;
    private KhachHangDTO kh;

    public KhachHangDTO getKh() {
        return kh;
    }

    public ListKhachHang(TaoHoaDon taoHoaDon, JFrame owner, String title, boolean modal) {
        super(owner, title, modal);
        this.taoHoaDon = taoHoaDon;
        init();
        tableKhachHang.setDefaultEditor(Object.class, null);
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
        tfSearch.putClientProperty("JTextField.placeholderText", "Tìm kiếm khách hàng....");
        tfSearch.putClientProperty("JTextField.showClearButton", true);
        tfSearch.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String type = "Tất cả";
                String txt = tfSearch.getText();
                resultTable = new ArrayList<>(khachhangBUS.search(txt, type));
                loadDataTable(resultTable);
            }
        });

        //Button Chọn khách hàng
        ButtonCustom buttonChoose = new ButtonCustom("Chọn khách hàng", "success", 14);
        buttonChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = getRow();
                if (index < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn khách hàng!)", "Thông báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    kh = resultTable.get(index);
                    dispose();
                    //TaiKhoanDialog tkd = new TaiKhoanDialog(guiTaiKhoan, guiTaiKhoan.owner, "Thêm tài khoản", true, "create", dataTable.get(getRow()));
                }
            }
        });

        //Button Thêm khách hàng
        ButtonCustom buttonAdd = new ButtonCustom("Thêm mới", "excel", 14);
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KhachHangDialog themNV = new KhachHangDialog(khachhangBUS, null, ListKhachHang.super.getTitle(), true, "create");
                loadDataTable();
            }
        });

        JPanel panelButton = new JPanel();
        panelButton.setLayout(new GridLayout(1, 2));
        panelButton.add(buttonChoose);
        panelButton.add(buttonAdd);


        panelSearch.add(jLabelSearch, BorderLayout.WEST);
        panelSearch.add(tfSearch, BorderLayout.CENTER);
        panelSearch.add(panelButton, BorderLayout.EAST);
        this.add(panelSearch, BorderLayout.NORTH);

        panelTable = new JPanel();
        panelSearch.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelTable.setLayout(new GridLayout(1, 1));

        tableKhachHang = new JTable();
        scrollTableKhachHang = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{ "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Địa chỉ" };
        tblModel.setColumnIdentifiers(header);
        tableKhachHang.setModel(tblModel);
        tableKhachHang.setFocusable(false);
        scrollTableKhachHang.setViewportView(tableKhachHang);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableKhachHang.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableKhachHang.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableKhachHang.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tableKhachHang.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tableKhachHang.getColumnModel().getColumn(3).setPreferredWidth(350);

        tableKhachHang.setAutoCreateRowSorter(true);
        TableSorter.configureTableColumnSorter(tableKhachHang, 0, TableSorter.STRING_COMPARATOR);

        panelTable.add(scrollTableKhachHang);
        this.add(panelTable, BorderLayout.CENTER);


    }

    public int getRow() {
        return tableKhachHang.getSelectedRow();
    }

    public void loadDataTable() {
        this.dataTable = new ArrayList<>(khachhangBUS.getAllKhachHang());
        resultTable = dataTable;
        tblModel.setRowCount(0);
        for (KhachHangDTO khachHang : dataTable) {
            tblModel.addRow(new Object[]{
                    khachHang.getId(), khachHang.getName(), khachHang.getPhone(), khachHang.getFullAddress()
            });
        }
    }

    public void loadDataTable(ArrayList<KhachHangDTO> data) {
        tblModel.setRowCount(0);
        for (KhachHangDTO khachHang : data) {
            tblModel.addRow(new Object[]{
                    khachHang.getId(), khachHang.getName(), khachHang.getPhone(), khachHang.getFullAddress()
            });
        }
    }
}
