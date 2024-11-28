package GUI.Panel;

import BUS.QuyenBUS;
import BUS.TaiKhoanBUS;
import DTO.TaiKhoanDTO;
import GUI.Component.*;
import GUI.Dialog.ListNhanVien;
import GUI.Dialog.TaiKhoanDialog;
import GUI.Main;
import helper.JTableExporter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaiKhoan extends JPanel implements ActionListener {

    PanelBorderRadius main, functionBar, boxFilter;
    JPanel contentCenter;
    JTable tableTaiKhoan;
    JScrollPane scrollTableTaiKhoan;
    MainFunction mainFunction;
    public JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    IntegratedSearch search;
    private SelectForm filTrangThai, filQuyen;
    DefaultTableModel tblModel;
    public TaiKhoanBUS taikhoanBUS = new TaiKhoanBUS();
    public ArrayList<TaiKhoanDTO> dataTable = new ArrayList<>(taikhoanBUS.getSetTK());
    public ArrayList<TaiKhoanDTO> resultTable = dataTable;
    //TaiKhoanDTO kh = new TaiKhoanDTO();
    Main m;
    Color BackgroundColor = new Color(240, 247, 250);
    public HashMap<String, String> mapQuyen = QuyenBUS.getInstance().toMap();

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setBorder(new EmptyBorder(10, 20, 10, 20));
        this.setOpaque(true);

        tableTaiKhoan = new JTable();
        scrollTableTaiKhoan = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{ "Username", "Mã Nhân Viên", "Quyền", "Email", "Trạng thái" };
        tblModel.setColumnIdentifiers(header);
        tableTaiKhoan.setModel(tblModel);
        tableTaiKhoan.setFocusable(false);
        scrollTableTaiKhoan.setViewportView(tableTaiKhoan);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableTaiKhoan.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableTaiKhoan.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableTaiKhoan.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tableTaiKhoan.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tableTaiKhoan.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        tableTaiKhoan.setAutoCreateRowSorter(true);
        TableSorter.configureTableColumnSorter(tableTaiKhoan, 0, TableSorter.STRING_COMPARATOR);

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(10, 10));
        this.add(contentCenter, BorderLayout.CENTER);

        // functionBar là thanh bên trên chứa các nút chức năng như thêm xóa sửa, và tìm kiếm
        functionBar = new PanelBorderRadius();
        functionBar.setPreferredSize(new Dimension(0, 190));
        functionBar.setLayout(new GridLayout(2, 1, 0, 20));
        functionBar.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] action = { "create", "update", "delete", "detail", "import", "export" };
        mainFunction = new MainFunction(m.user.getIdQuyen(), "SER007", action);
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(this);
        }
        functionBar.add(mainFunction);

        search = new IntegratedSearch(TaiKhoanBUS.typeSearch);
        search.cbxChoose.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                Object[] filter = new Object[]{ filTrangThai.getValue(), filQuyen.getValue() };

                resultTable = new ArrayList<>(taikhoanBUS.searchAndfilter(txt, type, filter));
                loadDataTable(resultTable);
            }
        });
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                Object[] filter = new Object[]{ filTrangThai.getValue(), filQuyen.getValue() };

                resultTable = new ArrayList<>(taikhoanBUS.searchAndfilter(txt, type, filter));
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
        boxFilter.setLayout(new GridLayout(3, 1, 0, 5));
        boxFilter.setBorder(new EmptyBorder(0, 10, 250, 10));
        contentCenter.add(boxFilter, BorderLayout.WEST);

        filTrangThai = new SelectForm("Trạng thái", new String[]{ "Đang hoạt động", "Bị khóa" });
        filTrangThai.cbb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                Object[] filter = new Object[]{ filTrangThai.getValue(), filQuyen.getValue() };

                resultTable = new ArrayList<>(taikhoanBUS.searchAndfilter(txt, type, filter));
                loadDataTable(resultTable);
            }
        });

        ArrayList<String> dataQuyen = new ArrayList<>(mapQuyen.keySet());
        dataQuyen.add(0, "Tất cả");
        filQuyen = new SelectForm("Quyền", dataQuyen.toArray(new String[0]));
        filQuyen.cbb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                Object[] filter = new Object[]{ filTrangThai.getValue(), filQuyen.getValue() };

                resultTable = new ArrayList<>(taikhoanBUS.searchAndfilter(txt, type, filter));
                loadDataTable(resultTable);
            }
        });

        boxFilter.add(filTrangThai);
        boxFilter.add(filQuyen);

        // main là phần ở dưới để thống kê bảng biểu
        main = new PanelBorderRadius();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        contentCenter.add(main, BorderLayout.CENTER);

        main.add(scrollTableTaiKhoan);
    }

    public TaiKhoan(Main m) {
        this.m = m;
        initComponent();
        tableTaiKhoan.setDefaultEditor(Object.class, null);
        loadDataTable();
    }

    public void loadDataTable() {
        search.txtSearchForm.setText("");
        search.cbxChoose.setSelectedIndex(0);
        filTrangThai.setSelectedIndex(0);
        filQuyen.setSelectedIndex(0);

        this.dataTable = new ArrayList<>(taikhoanBUS.getSetTK());
        this.resultTable = dataTable;
        tblModel.setRowCount(0);
        for (TaiKhoanDTO taiKhoan : resultTable) {
            tblModel.addRow(new Object[]{
                    taiKhoan.getUsername(), taiKhoan.getIdNV(), taiKhoan.getNameQuyen(), taiKhoan.getEmail(),
                    taiKhoan.getTrangThai()
            });
        }
    }

    public void loadDataTable(ArrayList<TaiKhoanDTO> data) {
        tblModel.setRowCount(0);
        for (TaiKhoanDTO taiKhoan : data) {
            tblModel.addRow(new Object[]{
                    taiKhoan.getUsername(), taiKhoan.getIdNV(), taiKhoan.getNameQuyen(), taiKhoan.getEmail(),
                    taiKhoan.getTrangThai()
            });
        }
    }

    public int getRowSelected() {
        int index = tableTaiKhoan.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn tài khoản");
        }
        return index;
    }

//    public void importExcel() {
//        File excelFile;
//        FileInputStream excelFIS = null;
//        BufferedInputStream excelBIS = null;
//        XSSFWorkbook excelJTableImport = null;
//        ArrayList<TaiKhoanDTO> listExcel = new ArrayList<TaiKhoanDTO>();
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
//                    int id = TaiKhoanDAO.getInstance().getAutoIncrement();
//                    String tenkh = excelRow.getCell(0).getStringCellValue();
//                    String sdt = excelRow.getCell(1).getStringCellValue();
//                    String diachi = excelRow.getCell(2).getStringCellValue();
//                    if (Validation.isEmpty(tenkh) || Validation.isEmpty(sdt)
//                            || !isPhoneNumber(sdt) || sdt.length() != 10 || Validation.isEmpty(diachi)) {
//                        check = 0;
//                    }
//                    if (check == 1) {
//                        taikhoanBUS.add(new TaiKhoanDTO(id, tenkh, sdt, diachi));
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
            ListNhanVien khDialog = new ListNhanVien(this, owner, "Thêm tài khoản", true);
        } else if (e.getSource() == mainFunction.btn.get("update")) {
            int index = getRowSelected();
            if (index != -1) {
                TaiKhoanDialog khDialog = new TaiKhoanDialog(this, owner, "Chỉnh sửa tài khoản", true, "update", resultTable.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("delete")) {
            int index = getRowSelected();
            if (index != -1) {
                int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn xóa tài khoản ?", "Xóa tài khoản",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    taikhoanBUS.removeTaiKhoan(resultTable.get(index));
                    loadDataTable();
                }
            }
        } else if (e.getSource() == mainFunction.btn.get("detail")) {
            int index = getRowSelected();
            if (index != -1) {
                TaiKhoanDialog khDialog = new TaiKhoanDialog(this, owner, "Xem tài khoản", true, "view", resultTable.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("import")) {
            //importExcel();
            System.out.println("Nhập excel");
        } else if (e.getSource() == mainFunction.btn.get("export")) {
            try {
                JTableExporter.exportJTableToExcel(tableTaiKhoan);
            } catch (IOException ex) {
                Logger.getLogger(TaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
