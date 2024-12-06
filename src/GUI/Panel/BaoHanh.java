package GUI.Panel;

import BUS.BaoHanhBUS;
import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import DTO.BaoHanhDTO;
import DTO.NhanVienDTO;
import GUI.Component.*;
import GUI.Dialog.ChiTietPhieuDialog;
import GUI.Dialog.ListHoaDon;
import GUI.Main;
import helper.Formater;
import helper.Validation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class BaoHanh extends JPanel implements ActionListener {

    PanelBorderRadius main, functionBar, boxFilter;
    JPanel contentCenter;
    JTable tableBaoHanh;
    JScrollPane scrollTableBaoHanh;
    MainFunction mainFunction;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    IntegratedSearch search;
    DefaultTableModel tblModel;
    public BaoHanhBUS baohanhBUS = new BaoHanhBUS();
    public ArrayList<BaoHanhDTO> dataTable = new ArrayList<>(baohanhBUS.getSetBH());
    public ArrayList<BaoHanhDTO> resultTable = dataTable;
    public SelectForm filNhanVien, filKH;
    public InputDate filFromNgayBaoHanh, filToNgayBaoHanh, filFromNgayTraHang, filToNgayTraHang;
    //BaoHanhDTO kh = new BaoHanhDTO();
    public Main m;
    NhanVienDTO nv;
    HashMap<String, String> mapNV = NhanVienBUS.getInstance().toMap();
    HashMap<String, String> mapKH = KhachHangBUS.getInstance().toMap();
    Color BackgroundColor = new Color(240, 247, 250);

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setBorder(new EmptyBorder(10, 20, 10, 20));
        this.setOpaque(true);

        tableBaoHanh = new JTable();
        scrollTableBaoHanh = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{ "Mã bảo hành", "Mã phiếu bảo hành", "Khách hàng", "Người lập phiếu", "Ngày lập", "Ngày trả hàng" };
        tblModel.setColumnIdentifiers(header);
        tableBaoHanh.setModel(tblModel);
        tableBaoHanh.setFocusable(false);
        scrollTableBaoHanh.setViewportView(tableBaoHanh);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableBaoHanh.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableBaoHanh.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableBaoHanh.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tableBaoHanh.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tableBaoHanh.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tableBaoHanh.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        tableBaoHanh.setAutoCreateRowSorter(true);
        TableSorter.configureTableColumnSorter(tableBaoHanh, 0, TableSorter.STRING_COMPARATOR);

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(5, 10));
        this.add(contentCenter, BorderLayout.CENTER);

        // functionBar là thanh bên trên chứa các nút chức năng như thêm xóa sửa, và tìm kiếm
        functionBar = new PanelBorderRadius();
        functionBar.setPreferredSize(new Dimension(0, 190));
        functionBar.setLayout(new GridLayout(2, 1, 0, 20));
        functionBar.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] action = { "create", "delete", "detail" };
        mainFunction = new MainFunction(m.user.getIdQuyen(), "SER012", action);
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(this);
        }
        functionBar.add(mainFunction);

        search = new IntegratedSearch(BaoHanhBUS.typeSearch);
        search.cbxChoose.setSelectedIndex(0);
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                LocalDate fromNgayBaoHanh = filFromNgayBaoHanh.getLocalDate();
                LocalDate toNgayBaoHanh = filToNgayBaoHanh.getLocalDate();
                LocalDate fromNgayTraHang = filFromNgayTraHang.getLocalDate();
                LocalDate toNgayTraHang = filToNgayTraHang.getLocalDate();


                Object[] filter;
                if (validateFilter(fromNgayBaoHanh, toNgayBaoHanh, fromNgayTraHang, toNgayTraHang)) {
                    filter = new Object[]{ filNhanVien.getValue(), filKH.getValue(),
                            fromNgayBaoHanh, toNgayBaoHanh, fromNgayTraHang, toNgayTraHang };
                } else {

                    return;
                }
                resultTable = new ArrayList<>(baohanhBUS.searchAndfilter(txt, type, filter));
                loadDataTable(resultTable);
            }
        });

        search.btnReset.addActionListener((ActionEvent e) -> {
            loadDataTable();
        });

        functionBar.add(search);

        contentCenter.add(functionBar, BorderLayout.NORTH);

        //boxFilter là phần lọc bên trái bảng biểu
        boxFilter = new PanelBorderRadius();
        boxFilter.setPreferredSize(new Dimension(260, 0));
        boxFilter.setLayout(new GridLayout(6, 1, 0, 5));
        boxFilter.setBorder(new EmptyBorder(0, 10, 15, 10));
        contentCenter.add(boxFilter, BorderLayout.WEST);

        ArrayList<String> dataNhanVien = new ArrayList<>(mapNV.keySet());
        dataNhanVien.add(0, "Tất cả");
        filNhanVien = new SelectForm(BaoHanhBUS.typeFilter[0], dataNhanVien.toArray(new String[0]));
        filNhanVien.cbb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                LocalDate fromNgayBaoHanh = filFromNgayBaoHanh.getLocalDate();
                LocalDate toNgayBaoHanh = filToNgayBaoHanh.getLocalDate();
                LocalDate fromNgayTraHang = filFromNgayTraHang.getLocalDate();
                LocalDate toNgayTraHang = filToNgayTraHang.getLocalDate();


                Object[] filter;
                if (validateFilter(fromNgayBaoHanh, toNgayBaoHanh, fromNgayTraHang, toNgayTraHang)) {
                    filter = new Object[]{ filNhanVien.getValue(), filKH.getValue(),
                            fromNgayBaoHanh, toNgayBaoHanh, fromNgayTraHang, toNgayTraHang };
                } else {

                    return;
                }
                resultTable = new ArrayList<>(baohanhBUS.searchAndfilter(txt, type, filter));
                loadDataTable(resultTable);
            }
        });

        ArrayList<String> dataKH = new ArrayList<>(mapKH.keySet());
        dataKH.add(0, "Tất cả");
        filKH = new SelectForm(BaoHanhBUS.typeFilter[1], dataKH.toArray(new String[0]));
        filKH.cbb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                LocalDate fromNgayBaoHanh = filFromNgayBaoHanh.getLocalDate();
                LocalDate toNgayBaoHanh = filToNgayBaoHanh.getLocalDate();
                LocalDate fromNgayTraHang = filFromNgayTraHang.getLocalDate();
                LocalDate toNgayTraHang = filToNgayTraHang.getLocalDate();


                Object[] filter;
                if (validateFilter(fromNgayBaoHanh, toNgayBaoHanh, fromNgayTraHang, toNgayTraHang)) {
                    filter = new Object[]{ filNhanVien.getValue(), filKH.getValue(),
                            fromNgayBaoHanh, toNgayBaoHanh, fromNgayTraHang, toNgayTraHang };
                } else {

                    return;
                }
                resultTable = new ArrayList<>(baohanhBUS.searchAndfilter(txt, type, filter));
                loadDataTable(resultTable);
            }
        });

        filFromNgayBaoHanh = new InputDate(BaoHanhBUS.typeFilter[2]);
        filFromNgayBaoHanh.getDateChooser().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                LocalDate fromNgayBaoHanh = filFromNgayBaoHanh.getLocalDate();
                LocalDate toNgayBaoHanh = filToNgayBaoHanh.getLocalDate();
                LocalDate fromNgayTraHang = filFromNgayTraHang.getLocalDate();
                LocalDate toNgayTraHang = filToNgayTraHang.getLocalDate();


                Object[] filter;
                if (validateFilter(fromNgayBaoHanh, toNgayBaoHanh, fromNgayTraHang, toNgayTraHang)) {
                    filter = new Object[]{ filNhanVien.getValue(), filKH.getValue(),
                            fromNgayBaoHanh, toNgayBaoHanh, fromNgayTraHang, toNgayTraHang };
                } else {
                    filFromNgayBaoHanh.getDateChooser().setDate(null);
                    return;
                }
                resultTable = new ArrayList<>(baohanhBUS.searchAndfilter(txt, type, filter));
                loadDataTable(resultTable);
            }
        });

        filToNgayBaoHanh = new InputDate(BaoHanhBUS.typeFilter[3]);
        filToNgayBaoHanh.getDateChooser().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                LocalDate fromNgayBaoHanh = filFromNgayBaoHanh.getLocalDate();
                LocalDate toNgayBaoHanh = filToNgayBaoHanh.getLocalDate();
                LocalDate fromNgayTraHang = filFromNgayTraHang.getLocalDate();
                LocalDate toNgayTraHang = filToNgayTraHang.getLocalDate();


                Object[] filter;
                if (validateFilter(fromNgayBaoHanh, toNgayBaoHanh, fromNgayTraHang, toNgayTraHang)) {
                    filter = new Object[]{ filNhanVien.getValue(), filKH.getValue(),
                            fromNgayBaoHanh, toNgayBaoHanh, fromNgayTraHang, toNgayTraHang };
                } else {
                    filToNgayBaoHanh.getDateChooser().setDate(null);
                    return;
                }
                resultTable = new ArrayList<>(baohanhBUS.searchAndfilter(txt, type, filter));
                loadDataTable(resultTable);
            }
        });

        filFromNgayTraHang = new InputDate(BaoHanhBUS.typeFilter[4]);
        filFromNgayTraHang.getDateChooser().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                LocalDate fromNgayBaoHanh = filFromNgayBaoHanh.getLocalDate();
                LocalDate toNgayBaoHanh = filToNgayBaoHanh.getLocalDate();
                LocalDate fromNgayTraHang = filFromNgayTraHang.getLocalDate();
                LocalDate toNgayTraHang = filToNgayTraHang.getLocalDate();


                Object[] filter;
                if (validateFilter(fromNgayBaoHanh, toNgayBaoHanh, fromNgayTraHang, toNgayTraHang)) {
                    filter = new Object[]{ filNhanVien.getValue(), filKH.getValue(),
                            fromNgayBaoHanh, toNgayBaoHanh, fromNgayTraHang, toNgayTraHang };
                } else {
                    filFromNgayTraHang.getDateChooser().setDate(null);
                    return;
                }
                resultTable = new ArrayList<>(baohanhBUS.searchAndfilter(txt, type, filter));
                loadDataTable(resultTable);
            }
        });

        filToNgayTraHang = new InputDate(BaoHanhBUS.typeFilter[5]);
        filToNgayTraHang.getDateChooser().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                LocalDate fromNgayBaoHanh = filFromNgayBaoHanh.getLocalDate();
                LocalDate toNgayBaoHanh = filToNgayBaoHanh.getLocalDate();
                LocalDate fromNgayTraHang = filFromNgayTraHang.getLocalDate();
                LocalDate toNgayTraHang = filToNgayTraHang.getLocalDate();


                Object[] filter;
                if (validateFilter(fromNgayBaoHanh, toNgayBaoHanh, fromNgayTraHang, toNgayTraHang)) {
                    filter = new Object[]{ filNhanVien.getValue(), filKH.getValue(),
                            fromNgayBaoHanh, toNgayBaoHanh, fromNgayTraHang, toNgayTraHang };
                } else {
                    filToNgayTraHang.getDateChooser().setDate(null);
                    return;
                }
                resultTable = new ArrayList<>(baohanhBUS.searchAndfilter(txt, type, filter));
                loadDataTable(resultTable);
            }
        });

        boxFilter.add(filNhanVien);
        boxFilter.add(filKH);
        boxFilter.add(filFromNgayBaoHanh);
        boxFilter.add(filToNgayBaoHanh);
        boxFilter.add(filFromNgayTraHang);
        boxFilter.add(filToNgayTraHang);

        // main là phần ở dưới để thống kê bảng biểu
        main = new PanelBorderRadius();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        contentCenter.add(main, BorderLayout.CENTER);

        main.add(scrollTableBaoHanh);
    }

    public BaoHanh(Main m, NhanVienDTO nv) {
        this.m = m;
        this.nv = nv;
        initComponent();
        tableBaoHanh.setDefaultEditor(Object.class, null);
        loadDataTable();
    }

    public void loadDataTable() {
        search.txtSearchForm.setText("");
        search.cbxChoose.setSelectedIndex(0);
        filFromNgayBaoHanh.getDateChooser().setDate(null);
        filToNgayBaoHanh.getDateChooser().setDate(null);
        filFromNgayTraHang.getDateChooser().setDate(null);
        filToNgayTraHang.getDateChooser().setDate(null);
        filNhanVien.setSelectedIndex(0);
        filKH.setSelectedIndex(0);

        this.dataTable = new ArrayList<>(baohanhBUS.getSetBH());
        this.resultTable = dataTable;
        tblModel.setRowCount(0);
        for (BaoHanhDTO baohanh : resultTable) {
            tblModel.addRow(new Object[]{
                    baohanh.getId(), baohanh.getIdHoaDon(),
                    baohanh.getNameKhachHang(), baohanh.getNameNhanVien(),
                    Formater.formatTime(baohanh.getNgayBaoHanh()),
                    Formater.formatDate(baohanh.getNgayTraHang())
            });
        }
    }

    public void loadDataTable(ArrayList<BaoHanhDTO> data) {
        tblModel.setRowCount(0);
        for (BaoHanhDTO baohanh : data) {
            tblModel.addRow(new Object[]{
                    baohanh.getId(), baohanh.getIdHoaDon(),
                    baohanh.getNameKhachHang(), baohanh.getNameNhanVien(),
                    Formater.formatTime(baohanh.getNgayBaoHanh()),
                    Formater.formatDate(baohanh.getNgayTraHang())
            });
        }
    }

    public int getRowSelected() {
        int index = tableBaoHanh.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu bảo hành");
        }
        return index;
    }

    public boolean validateFilter(LocalDate fromNgayBaoHanh, LocalDate toNgayBaoHanh, LocalDate fromNgayTraHang, LocalDate toNgayTraHang) {
        if (!Validation.isFromToDate(fromNgayBaoHanh, toNgayBaoHanh)) {
            JOptionPane.showMessageDialog(null, "Khoảng thời gian không hợp lệ! Vui lòng chọn giá trị khác", "Lọc theo ngày lập", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!Validation.isFromToDate(fromNgayTraHang, toNgayTraHang)) {
            JOptionPane.showMessageDialog(null, "Khoảng thời gian không hợp lệ! Vui lòng chọn giá trị khác", "Lọc theo ngày trả hàng", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFunction.btn.get("create")) {
            System.out.println("ok");
            ListHoaDon chooseHoaDon = new ListHoaDon(nv, this, owner, "Chọn hóa đơn", true);
        } else if (e.getSource() == mainFunction.btn.get("delete")) {
            int index = getRowSelected();
            if (index != -1) {
                int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn xóa phiếu bảo hành ?", "Xóa phiếu bảo hành",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    //baohanhBUS.removeBaoHanh(resultTable.get(index));
                    loadDataTable();
                }
            }
        } else if (e.getSource() == mainFunction.btn.get("detail")) {
            int index = getRowSelected();
            if (index != -1) {
                ChiTietPhieuDialog ctPhieu = new ChiTietPhieuDialog(owner, "Thông tin phiếu bảo hành", true, resultTable.get(index));
            }
        }
    }

}
