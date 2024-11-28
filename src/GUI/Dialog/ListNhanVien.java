/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.TableSorter;
import GUI.Panel.TaiKhoan;
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

/**
 * @author robot
 */
public class ListNhanVien extends JDialog {

    private TaiKhoan guiTaiKhoan;
    private JTable tableNhanVien;
    private JScrollPane scrollTableNhanVien;
    private DefaultTableModel tblModel;
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    public NhanVienBUS nhanvienBUS = new NhanVienBUS();
    public ArrayList<NhanVienDTO> dataTable = new ArrayList<>(nhanvienBUS.getAllNhanVienNotAcc());
    public ArrayList<NhanVienDTO> resultTable;
    private JTextField tfSearch;
    private JPanel panelSearch;
    private JPanel panelTable;

    public ListNhanVien(TaiKhoan taiKhoan, JFrame owner, String title, boolean modal) {
        super(owner, title, modal);
        this.guiTaiKhoan = taiKhoan;
        init();
        tableNhanVien.setDefaultEditor(Object.class, null);
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
        tfSearch.putClientProperty("JTextField.placeholderText", "Tìm kiếm nhân viên....");
        tfSearch.putClientProperty("JTextField.showClearButton", true);
        tfSearch.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String type = "Tất cả";
                String txt = tfSearch.getText();
                resultTable = new ArrayList<>(nhanvienBUS.search(txt, type));
                loadDataTable(resultTable);
            }
        });

        //Button Chọn nhân viên
        ButtonCustom buttonChoose = new ButtonCustom("Chọn nhân viên", "success", 14);
        buttonChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getRow() < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên!)", "Thông báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    dispose();
                    TaiKhoanDialog tkd = new TaiKhoanDialog(guiTaiKhoan, guiTaiKhoan.owner, "Thêm tài khoản", true, "create", dataTable.get(getRow()));
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

        tableNhanVien = new JTable();
        scrollTableNhanVien = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{ "Mã nhân viên", "Tên nhân viên", "Giới tính", "Ngày sinh", "Số điện thoại", "Email" };
        tblModel.setColumnIdentifiers(header);
        tableNhanVien.setModel(tblModel);
        tableNhanVien.setFocusable(false);
        scrollTableNhanVien.setViewportView(tableNhanVien);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableNhanVien.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableNhanVien.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableNhanVien.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tableNhanVien.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tableNhanVien.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tableNhanVien.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        tableNhanVien.setAutoCreateRowSorter(true);
        TableSorter.configureTableColumnSorter(tableNhanVien, 0, TableSorter.STRING_COMPARATOR);

        panelTable.add(scrollTableNhanVien);
        this.add(panelTable, BorderLayout.CENTER);


    }

    public int getRow() {
        return tableNhanVien.getSelectedRow();
    }

    public void loadDataTable() {
        this.dataTable = new ArrayList<>(nhanvienBUS.getAllNhanVienNotAcc());
        tblModel.setRowCount(0);
        for (NhanVienDTO nhanVien : dataTable) {
            tblModel.addRow(new Object[]{
                    nhanVien.getId(), nhanVien.getName(), nhanVien.getGenderValue(), Formater.formatDate(nhanVien.getBirth()),
                    nhanVien.getPhone(), nhanVien.getEmail()
            });
        }
    }

    public void loadDataTable(ArrayList<NhanVienDTO> data) {
        tblModel.setRowCount(0);
        for (NhanVienDTO nhanVien : data) {
            tblModel.addRow(new Object[]{
                    nhanVien.getId(), nhanVien.getName(), nhanVien.getGenderValue(), Formater.formatDate(nhanVien.getBirth()),
                    nhanVien.getPhone(), nhanVien.getEmail()
            });
        }
    }
}
