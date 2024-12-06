package GUI.Panel;

import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.PhieuNhapBUS;
import DTO.NhanVienDTO;
import DTO.PhieuNhapDTO;
import GUI.Component.*;
import GUI.Dialog.ChiTietPhieuDialog;
import GUI.Main;
import helper.Formater;
import helper.JTableExporter;
import helper.Validation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhieuNhap extends JPanel implements ActionListener {

    PanelBorderRadius main, functionBar, boxFilter;
    JPanel contentCenter;
    JTable tablePhieuNhap;
    JScrollPane scrollTablePhieuNhap;
    MainFunction mainFunction;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    IntegratedSearch search;
    DefaultTableModel tblModel;
    public PhieuNhapBUS phieunhapBUS = new PhieuNhapBUS();
    public ArrayList<PhieuNhapDTO> dataTable = new ArrayList<>(phieunhapBUS.getSetPN());
    public ArrayList<PhieuNhapDTO> resultTable = dataTable;
    public SelectForm filNhanVien, filNCC;
    public InputDate filFromNgay, filToNgay;
    public InputForm filFromTongTien, filToTongTien;
    //PhieuNhapDTO kh = new PhieuNhapDTO();
    Main m;
    NhanVienDTO nv;
    HashMap<String, String> mapNV = NhanVienBUS.getInstance().toMap();
    HashMap<String, String> mapNCC = NhaCungCapBUS.getInstance().toMap();
    Color BackgroundColor = new Color(240, 247, 250);

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setBorder(new EmptyBorder(10, 20, 10, 20));
        this.setOpaque(true);

        tablePhieuNhap = new JTable();
        scrollTablePhieuNhap = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{ "Mã phiếu nhập", "Nhà cung cấp", "Người lập phiếu", "Tổng tiền", "Ngày nhập" };
        tblModel.setColumnIdentifiers(header);
        tablePhieuNhap.setModel(tblModel);
        tablePhieuNhap.setFocusable(false);
        scrollTablePhieuNhap.setViewportView(tablePhieuNhap);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tablePhieuNhap.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tablePhieuNhap.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tablePhieuNhap.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tablePhieuNhap.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tablePhieuNhap.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        tablePhieuNhap.setAutoCreateRowSorter(true);
        TableSorter.configureTableColumnSorter(tablePhieuNhap, 0, TableSorter.STRING_COMPARATOR);

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
        mainFunction = new MainFunction(m.user.getIdQuyen(), "SER004", action);
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(this);
        }
        functionBar.add(mainFunction);

        search = new IntegratedSearch(PhieuNhapBUS.typeSearch);
        search.cbxChoose.setSelectedIndex(0);
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                LocalDate fromNgay = filFromNgay.getLocalDate();
                LocalDate toNgay = filToNgay.getLocalDate();
                Integer fromTongTien = null;
                Integer toTongTien = null;
                if (!filFromTongTien.getText().isEmpty()) {
                    fromTongTien = Integer.parseInt(filFromTongTien.getText());
                }
                if (!filToTongTien.getText().isEmpty()) {
                    toTongTien = Integer.parseInt(filToTongTien.getText());
                }

                Object[] filter;
                if (validateFilter(fromNgay, toNgay, fromTongTien, toTongTien)) {
                    filter = new Object[]{ filNhanVien.getValue(), filNCC.getValue(), filFromNgay.getLocalDate(), filToNgay.getLocalDate(),
                            fromTongTien, toTongTien };
                } else {

                    return;
                }
                resultTable = new ArrayList<>(phieunhapBUS.searchAndfilter(txt, type, filter));
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
        filNhanVien = new SelectForm(PhieuNhapBUS.typeFilter[0], dataNhanVien.toArray(new String[0]));
        filNhanVien.cbb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                LocalDate fromNgay = filFromNgay.getLocalDate();
                LocalDate toNgay = filToNgay.getLocalDate();
                Integer fromTongTien = null;
                Integer toTongTien = null;
                if (!filFromTongTien.getText().isEmpty()) {
                    fromTongTien = Integer.parseInt(filFromTongTien.getText());
                }
                if (!filToTongTien.getText().isEmpty()) {
                    toTongTien = Integer.parseInt(filToTongTien.getText());
                }

                Object[] filter;
                if (validateFilter(fromNgay, toNgay, fromTongTien, toTongTien)) {
                    filter = new Object[]{ filNhanVien.getValue(), filNCC.getValue(), filFromNgay.getLocalDate(), filToNgay.getLocalDate(),
                            fromTongTien, toTongTien };
                } else {

                    return;
                }
                resultTable = new ArrayList<>(phieunhapBUS.searchAndfilter(txt, type, filter));
                loadDataTable(resultTable);
            }
        });

        ArrayList<String> dataNCC = new ArrayList<>(mapNCC.keySet());
        dataNCC.add(0, "Tất cả");
        filNCC = new SelectForm(PhieuNhapBUS.typeFilter[1], dataNCC.toArray(new String[0]));
        filNCC.cbb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                LocalDate fromNgay = filFromNgay.getLocalDate();
                LocalDate toNgay = filToNgay.getLocalDate();
                Integer fromTongTien = null;
                Integer toTongTien = null;
                if (!filFromTongTien.getText().isEmpty()) {
                    fromTongTien = Integer.parseInt(filFromTongTien.getText());
                }
                if (!filToTongTien.getText().isEmpty()) {
                    toTongTien = Integer.parseInt(filToTongTien.getText());
                }

                Object[] filter;
                if (validateFilter(fromNgay, toNgay, fromTongTien, toTongTien)) {
                    filter = new Object[]{ filNhanVien.getValue(), filNCC.getValue(), filFromNgay.getLocalDate(), filToNgay.getLocalDate(),
                            fromTongTien, toTongTien };
                } else {

                    return;
                }
                resultTable = new ArrayList<>(phieunhapBUS.searchAndfilter(txt, type, filter));
                loadDataTable(resultTable);
            }
        });

        filFromNgay = new InputDate(PhieuNhapBUS.typeFilter[2]);
        filFromNgay.getDateChooser().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                LocalDate fromNgay = filFromNgay.getLocalDate();
                LocalDate toNgay = filToNgay.getLocalDate();
                Integer fromTongTien = null;
                Integer toTongTien = null;
                if (!filFromTongTien.getText().isEmpty()) {
                    fromTongTien = Integer.parseInt(filFromTongTien.getText());
                }
                if (!filToTongTien.getText().isEmpty()) {
                    toTongTien = Integer.parseInt(filToTongTien.getText());
                }

                Object[] filter;
                if (validateFilter(fromNgay, toNgay, fromTongTien, toTongTien)) {
                    filter = new Object[]{ filNhanVien.getValue(), filNCC.getValue(), filFromNgay.getLocalDate(), filToNgay.getLocalDate(),
                            fromTongTien, toTongTien };
                } else {
                    filFromNgay.getDateChooser().setDate(null);
                    return;
                }
                resultTable = new ArrayList<>(phieunhapBUS.searchAndfilter(txt, type, filter));
                loadDataTable(resultTable);
            }
        });

        filToNgay = new InputDate(PhieuNhapBUS.typeFilter[3]);
        filToNgay.getDateChooser().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                LocalDate fromNgay = filFromNgay.getLocalDate();
                LocalDate toNgay = filToNgay.getLocalDate();
                Integer fromTongTien = null;
                Integer toTongTien = null;
                if (!filFromTongTien.getText().isEmpty()) {
                    fromTongTien = Integer.parseInt(filFromTongTien.getText());
                }
                if (!filToTongTien.getText().isEmpty()) {
                    toTongTien = Integer.parseInt(filToTongTien.getText());
                }

                Object[] filter;
                if (validateFilter(fromNgay, toNgay, fromTongTien, toTongTien)) {
                    filter = new Object[]{ filNhanVien.getValue(), filNCC.getValue(), filFromNgay.getLocalDate(), filToNgay.getLocalDate(),
                            fromTongTien, toTongTien };
                } else {
                    filToNgay.getDateChooser().setDate(null);
                    return;
                }
                resultTable = new ArrayList<>(phieunhapBUS.searchAndfilter(txt, type, filter));
                loadDataTable(resultTable);
            }
        });

        filFromTongTien = new InputForm(PhieuNhapBUS.typeFilter[4]);
        PlainDocument fromTongTien = (PlainDocument) filFromTongTien.getTxtForm().getDocument();
        fromTongTien.setDocumentFilter((new NumericDocumentFilter()));
        filFromTongTien.getTxtForm().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                LocalDate fromNgay = filFromNgay.getLocalDate();
                LocalDate toNgay = filToNgay.getLocalDate();
                Integer fromTongTien = null;
                Integer toTongTien = null;
                if (!filFromTongTien.getText().isEmpty()) {
                    fromTongTien = Integer.parseInt(filFromTongTien.getText());
                }
                if (!filToTongTien.getText().isEmpty()) {
                    toTongTien = Integer.parseInt(filToTongTien.getText());
                }

                Object[] filter;
                if (validateFilter(fromNgay, toNgay, fromTongTien, toTongTien)) {
                    filter = new Object[]{ filNhanVien.getValue(), filNCC.getValue(), filFromNgay.getLocalDate(), filToNgay.getLocalDate(),
                            fromTongTien, toTongTien };
                } else {

                    return;
                }
                resultTable = new ArrayList<>(phieunhapBUS.searchAndfilter(txt, type, filter));
                loadDataTable(resultTable);
            }
        });

        filToTongTien = new InputForm(PhieuNhapBUS.typeFilter[5]);
        PlainDocument toTongTien = (PlainDocument) filToTongTien.getTxtForm().getDocument();
        toTongTien.setDocumentFilter((new NumericDocumentFilter()));
        filToTongTien.getTxtForm().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                LocalDate fromNgay = filFromNgay.getLocalDate();
                LocalDate toNgay = filToNgay.getLocalDate();
                Integer fromTongTien = null;
                Integer toTongTien = null;
                if (!filFromTongTien.getText().isEmpty()) {
                    fromTongTien = Integer.parseInt(filFromTongTien.getText());
                }
                if (!filToTongTien.getText().isEmpty()) {
                    toTongTien = Integer.parseInt(filToTongTien.getText());
                }

                Object[] filter;
                if (validateFilter(fromNgay, toNgay, fromTongTien, toTongTien)) {
                    filter = new Object[]{ filNhanVien.getValue(), filNCC.getValue(), filFromNgay.getLocalDate(), filToNgay.getLocalDate(),
                            fromTongTien, toTongTien };
                } else {

                    return;
                }
                resultTable = new ArrayList<>(phieunhapBUS.searchAndfilter(txt, type, filter));
                loadDataTable(resultTable);
            }
        });

        boxFilter.add(filNhanVien);
        boxFilter.add(filNCC);
        boxFilter.add(filFromNgay);
        boxFilter.add(filToNgay);
        boxFilter.add(filFromTongTien);
        boxFilter.add(filToTongTien);

        // main là phần ở dưới để thống kê bảng biểu
        main = new PanelBorderRadius();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        contentCenter.add(main, BorderLayout.CENTER);

        main.add(scrollTablePhieuNhap);
    }

    public PhieuNhap(Main m, NhanVienDTO nv) {
        this.m = m;
        this.nv = nv;
        initComponent();
        tablePhieuNhap.setDefaultEditor(Object.class, null);
        loadDataTable();
    }

    public void loadDataTable() {
        search.txtSearchForm.setText("");
        search.cbxChoose.setSelectedIndex(0);
        filFromNgay.getDateChooser().setDate(null);
        filToNgay.getDateChooser().setDate(null);
        filNhanVien.setSelectedIndex(0);
        filNCC.setSelectedIndex(0);
        filFromTongTien.setText("");
        filToTongTien.setText("");

        this.dataTable = new ArrayList<>(phieunhapBUS.getSetPN());
        this.resultTable = dataTable;
        tblModel.setRowCount(0);
        for (PhieuNhapDTO phieunhap : resultTable) {
            tblModel.addRow(new Object[]{
                    phieunhap.getId(), phieunhap.getNameNCC(), phieunhap.getNameNhanVien(),
                    Formater.formatVND(phieunhap.getTotal()),
                    Formater.formatTime(phieunhap.getNgayNhap())
            });
        }
    }

    public void loadDataTable(ArrayList<PhieuNhapDTO> data) {
        tblModel.setRowCount(0);
        for (PhieuNhapDTO phieunhap : data) {
            tblModel.addRow(new Object[]{
                    phieunhap.getId(), phieunhap.getNameNCC(), phieunhap.getNameNhanVien(),
                    Formater.formatVND(phieunhap.getTotal()),
                    Formater.formatTime(phieunhap.getNgayNhap())
            });
        }
    }

    public int getRowSelected() {
        int index = tablePhieuNhap.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu nhập");
        }
        return index;
    }

    public boolean validateFilter(LocalDate fromNgay, LocalDate toNgay, Integer fromTong, Integer toTong) {
        if (!Validation.isFromToDate(fromNgay, toNgay)) {
            JOptionPane.showMessageDialog(null, "Khoảng thời gian không hợp lệ! Vui lòng chọn giá trị khác", "Lọc theo ngày nhập", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!Validation.isFromToInt(fromTong, toTong)) {
            JOptionPane.showMessageDialog(null, "Khoảng tiền không hợp lệ! Vui lòng chọn giá trị khác", "Lọc theo tổng tiền", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

//    public void importExcel() {
//        File excelFile;
//        FileInputStream excelFIS = null;
//        BufferedInputStream excelBIS = null;
//        XSSFWorkbook excelJTableImport = null;
//        ArrayList<PhieuNhapDTO> listExcel = new ArrayList<PhieuNhapDTO>();
//        JFileChooser jf = new JFileChooser();
//        int result = jf.showOpenDialog(null);
//        jf.setDialogTitle("Open file");
//        Workbook workbook = null;
//        int k = 0;
//        if (result == JFileChooser.APPROVE_OPTION) {
//            try {
//                excelFile = jf.getSelectedFile();
//                excelFIS = new FileInputStream(excelFile);
//                excelBIS = new BufferedInputStream(excelFIS);
//                excelJTableImport = new XSSFWorkbook(excelBIS);
//                XSSFSheet excelSheet = excelJTableImport.getSheetAt(0);
//                for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
//                    int check = 1;
//                    XSSFRow excelRow = excelSheet.getRow(row);
//                    int id = PhieuNhapDAO.getInstance().getAutoIncrement();
//                    String tenkh = excelRow.getCell(0).getStringCellValue();
//                    String sdt = excelRow.getCell(1).getStringCellValue();
//                    String diachi = excelRow.getCell(2).getStringCellValue();
//                    if (Validation.isEmpty(tenkh) || Validation.isEmpty(sdt)
//                            || !isPhoneNumber(sdt) || sdt.length() != 10 || Validation.isEmpty(diachi)) {
//                        check = 0;
//                    }
//                    if (check == 1) {
//                        phieunhapBUS.add(new PhieuNhapDTO(id, tenkh, sdt, diachi));
//                    } else {
//                        k += 1;
//                    }
//                }
//                JOptionPane.showMessageDialog(this, "Nhập thành công");
//            } catch (FileNotFoundException ex) {
//                System.out.println("Lỗi đọc file");
//            } catch (IOException ex) {
//                System.out.println("Lỗi đọc file");
//            }
//        }
//        if (k != 0) {
//            JOptionPane.showMessageDialog(this, "Những dữ liệu không hợp lệ không được thêm vào");
//        }
//        loadDataTable(listkh);
//    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFunction.btn.get("create")) {
            System.out.println("ok");
            TaoPhieuNhap created = new TaoPhieuNhap(m, nv);
            m.setPanel(created);
        } else if (e.getSource() == mainFunction.btn.get("delete")) {
            int index = getRowSelected();
            if (index != -1) {
                int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn xóa phiếu nhập ?", "Xóa phiếu nhập",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    //phieunhapBUS.removePhieuNhap(resultTable.get(index));
                    loadDataTable();
                }
            }
        } else if (e.getSource() == mainFunction.btn.get("detail")) {
            int index = getRowSelected();
            if (index != -1) {
                ChiTietPhieuDialog ctPhieu = new ChiTietPhieuDialog(owner, "Thông tin phiếu nhập", true, resultTable.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("import")) {
            //importExcel();
            System.out.println("Nhập excel");
        } else if (e.getSource() == mainFunction.btn.get("export")) {
            try {
                JTableExporter.exportJTableToExcel(tablePhieuNhap);
            } catch (IOException ex) {
                Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
