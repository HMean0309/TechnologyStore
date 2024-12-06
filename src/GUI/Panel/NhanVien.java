package GUI.Panel;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
import GUI.Component.*;
import GUI.Dialog.NhanVienDialog;
import GUI.Main;
import helper.Formater;
import helper.JTableExporter;
import helper.Validation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class NhanVien extends JPanel implements ActionListener {

    PanelBorderRadius main, functionBar, boxFilter;
    JPanel contentCenter;
    JTable tableNhanVien;
    JScrollPane scrollTableNhanVien;
    MainFunction mainFunction;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    IntegratedSearch search;
    DefaultTableModel tblModel;
    public NhanVienBUS nhanvienBUS = new NhanVienBUS();
    public ArrayList<NhanVienDTO> dataTable = new ArrayList<>(nhanvienBUS.getSetNV());
    public ArrayList<NhanVienDTO> resultTable = dataTable;
    public SelectForm filGioiTinh;
    public InputDate filFromBirth, filToBirth;
    //NhanVienDTO kh = new NhanVienDTO();
    Main m;
    Color BackgroundColor = new Color(240, 247, 250);

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setBorder(new EmptyBorder(10, 20, 10, 20));
        this.setOpaque(true);

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

        String[] action = { "create", "update", "delete", "detail", "import", "export" };
        mainFunction = new MainFunction(m.user.getIdQuyen(), "SER011", action);
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(this);
        }
        functionBar.add(mainFunction);

        search = new IntegratedSearch(NhanVienBUS.typeSearch);
        search.cbxChoose.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                Object[] filter;
                if (Validation.isFromToDate(filFromBirth.getLocalDate(), filToBirth.getLocalDate())) {
                    filter = new Object[]{ filGioiTinh.getValue(), filFromBirth.getLocalDate(), filToBirth.getLocalDate() };
                } else {
                    JOptionPane.showMessageDialog(null, "Khoảng thời gian không hợp lệ", "Lọc theo ngày sinh", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                resultTable = new ArrayList<>(nhanvienBUS.searchAndfilter(txt, type, filter));
                loadDataTable(resultTable);
            }
        });
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                Object[] filter;
                if (Validation.isFromToDate(filFromBirth.getLocalDate(), filToBirth.getLocalDate())) {
                    filter = new Object[]{ filGioiTinh.getValue(), filFromBirth.getLocalDate(), filToBirth.getLocalDate() };
                } else {
                    JOptionPane.showMessageDialog(null, "Khoảng thời gian không hợp lệ", "Lọc theo ngày sinh", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                resultTable = new ArrayList<>(nhanvienBUS.searchAndfilter(txt, type, filter));
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
        boxFilter.setLayout(new GridLayout(4, 1, 0, 5));
        boxFilter.setBorder(new EmptyBorder(0, 10, 150, 10));
        contentCenter.add(boxFilter, BorderLayout.WEST);

        filGioiTinh = new SelectForm(NhanVienBUS.typeFilter[0], new String[]{ "Tất cả", "Nam", "Nữ" });
        filGioiTinh.cbb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                Object[] filter;
                if (Validation.isFromToDate(filFromBirth.getLocalDate(), filToBirth.getLocalDate())) {
                    filter = new Object[]{ filGioiTinh.getValue(), filFromBirth.getLocalDate(), filToBirth.getLocalDate() };
                } else {
                    JOptionPane.showMessageDialog(null, "Khoảng thời gian không hợp lệ", "Lọc theo ngày sinh", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                resultTable = new ArrayList<>(nhanvienBUS.searchAndfilter(txt, type, filter));
                loadDataTable(resultTable);
            }
        });

        filFromBirth = new InputDate(NhanVienBUS.typeFilter[1]);
        filFromBirth.getDateChooser().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                Object[] filter;
                if (Validation.isFromToDate(filFromBirth.getLocalDate(), filToBirth.getLocalDate())) {
                    filter = new Object[]{ filGioiTinh.getValue(), filFromBirth.getLocalDate(), filToBirth.getLocalDate() };
                } else {
                    JOptionPane.showMessageDialog(null, "Khoảng thời gian không hợp lệ. Vui lòng chọn giá trị khác!", "Lọc theo ngày sinh", JOptionPane.ERROR_MESSAGE);
                    filFromBirth.getDateChooser().setDate(null);
                    return;
                }
                resultTable = new ArrayList<>(nhanvienBUS.searchAndfilter(txt, type, filter));
                loadDataTable(resultTable);
            }
        });

        filToBirth = new InputDate(NhanVienBUS.typeFilter[2]);
        filToBirth.getDateChooser().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                Object[] filter;
                if (Validation.isFromToDate(filFromBirth.getLocalDate(), filToBirth.getLocalDate())) {
                    filter = new Object[]{ filGioiTinh.getValue(), filFromBirth.getLocalDate(), filToBirth.getLocalDate() };
                } else {
                    JOptionPane.showMessageDialog(null, "Khoảng thời gian không hợp lệ. Vui lòng chọn giá trị khác!", "Lọc theo ngày sinh", JOptionPane.ERROR_MESSAGE);
                    filToBirth.getDateChooser().setDate(null);
                    return;
                }
                resultTable = new ArrayList<>(nhanvienBUS.searchAndfilter(txt, type, filter));
                loadDataTable(resultTable);
            }
        });

        boxFilter.add(filGioiTinh);
        boxFilter.add(filFromBirth);
        boxFilter.add(filToBirth);

        // main là phần ở dưới để thống kê bảng biểu
        main = new PanelBorderRadius();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        contentCenter.add(main, BorderLayout.CENTER);

        main.add(scrollTableNhanVien);
    }

    public NhanVien(Main m) {
        this.m = m;
        initComponent();
        tableNhanVien.setDefaultEditor(Object.class, null);
        loadDataTable();
    }

    public void loadDataTable() {
        search.txtSearchForm.setText("");
        search.cbxChoose.setSelectedIndex(0);
        filFromBirth.getDateChooser().setDate(null);
        filToBirth.getDateChooser().setDate(null);
        filGioiTinh.setSelectedIndex(0);

        this.dataTable = new ArrayList<>(nhanvienBUS.getSetNV());
        this.resultTable = dataTable;
        tblModel.setRowCount(0);
        for (NhanVienDTO nhanVien : resultTable) {
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

    public int getRowSelected() {
        int index = tableNhanVien.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên");
        }
        return index;
    }

//    public void importExcel() {
//        File excelFile;
//        FileInputStream excelFIS = null;
//        BufferedInputStream excelBIS = null;
//        XSSFWorkbook excelJTableImport = null;
//        ArrayList<NhanVienDTO> listExcel = new ArrayList<NhanVienDTO>();
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
//                    int id = NhanVienDAO.getInstance().getAutoIncrement();
//                    String tenkh = excelRow.getCell(0).getStringCellValue();
//                    String sdt = excelRow.getCell(1).getStringCellValue();
//                    String diachi = excelRow.getCell(2).getStringCellValue();
//                    if (Validation.isEmpty(tenkh) || Validation.isEmpty(sdt)
//                            || !isPhoneNumber(sdt) || sdt.length() != 10 || Validation.isEmpty(diachi)) {
//                        check = 0;
//                    }
//                    if (check == 1) {
//                        nhanvienBUS.add(new NhanVienDTO(id, tenkh, sdt, diachi));
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
    public void importExcel() {
        File excelFile;
        FileInputStream excelFIS = null;
        BufferedInputStream excelBIS = null;
        XSSFWorkbook excelJTableImport = null;
        ArrayList<NhanVienDTO> listExcel = new ArrayList<>();
        JFileChooser jf = new JFileChooser();
        jf.setDialogTitle("Open file");
        jf.setFileFilter(new FileNameExtensionFilter("Excel Files", "xls", "xlsx"));
        int result = jf.showOpenDialog(null);
        int k = 0;
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                excelFile = jf.getSelectedFile();
                excelFIS = new FileInputStream(excelFile);
                excelBIS = new BufferedInputStream(excelFIS);
                excelJTableImport = new XSSFWorkbook(excelBIS);
                XSSFSheet excelSheet = excelJTableImport.getSheetAt(0);
                for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
                    int check = 1;
                    XSSFRow excelRow = excelSheet.getRow(row);
                    String id = nhanvienBUS.createID(); // Tạo ID mới cho mỗi nhân viên
                    String name = excelRow.getCell(0).getStringCellValue();
                    String phone = String.valueOf((long) excelRow.getCell(1).getNumericCellValue());
                    boolean gender = excelRow.getCell(2).getStringCellValue().equalsIgnoreCase("Nam");
                    LocalDate birth = excelRow.getCell(3).getLocalDateTimeCellValue().toLocalDate();
                    String email = excelRow.getCell(4).getStringCellValue();

                    if (Validation.isEmpty(name) || Validation.isEmpty(phone) || !Validation.isEmail(email)) {
                        check = 0;
                    }

                    if (check == 1) {
                        NhanVienDTO nhanVien = new NhanVienDTO(id, name, phone, email, birth, gender, null, false);
                        nhanvienBUS.addNhanVien(nhanVien);
                    } else {
                        k += 1;
                    }
                }
                JOptionPane.showMessageDialog(this, "Nhập thành công");
            } catch (FileNotFoundException ex) {
                System.out.println("Lỗi đọc file");
            } catch (IOException ex) {
                System.out.println("Lỗi đọc file");
            } finally {
                try {
                    if (excelFIS != null) excelFIS.close();
                    if (excelBIS != null) excelBIS.close();
                    if (excelJTableImport != null) excelJTableImport.close();
                } catch (IOException ex) {
                    System.out.println("Lỗi đóng file");
                }
            }
        }
        if (k != 0) {
            JOptionPane.showMessageDialog(this, "Những dữ liệu không hợp lệ không được thêm vào");
        }
        loadDataTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFunction.btn.get("create")) {
            System.out.println("ok");
            NhanVienDialog khDialog = new NhanVienDialog(this, owner, "Thêm nhân viên", true, "create");
        } else if (e.getSource() == mainFunction.btn.get("update")) {
            int index = getRowSelected();
            if (index != -1) {
                NhanVienDialog khDialog = new NhanVienDialog(this, owner, "Chỉnh sửa nhân viên", true, "update", resultTable.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("delete")) {
            int index = getRowSelected();
            if (index != -1) {
                int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn xóa nhân viên ?", "Xóa nhân viên",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    nhanvienBUS.removeNhanVien(resultTable.get(index));
                    loadDataTable();
                }
            }
        } else if (e.getSource() == mainFunction.btn.get("detail")) {
            int index = getRowSelected();
            if (index != -1) {
                NhanVienDialog khDialog = new NhanVienDialog(this, owner, "Xem nhân viên", true, "view", resultTable.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("import")) {
            importExcel();
            System.out.println("Nhập excel");
        } else if (e.getSource() == mainFunction.btn.get("export")) {
            try {
                JTableExporter.exportJTableToExcel(tableNhanVien);
            } catch (IOException ex) {
                Logger.getLogger(NhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
