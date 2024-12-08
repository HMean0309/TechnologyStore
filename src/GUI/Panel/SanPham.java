package GUI.Panel;

import BUS.OptionSanPhamBUS;
import BUS.PhanLoaiBUS;
import BUS.SanPhamBUS;
import DTO.SanPhamDTO;
import GUI.Component.*;
import GUI.Dialog.SanPhamDialog;
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

public class SanPham extends JPanel implements ActionListener {

    PanelBorderRadius main, functionBar;
    JPanel contentCenter;
    JTable tableSanPham;
    JScrollPane scrollTableSanPham;
    MainFunction mainFunction;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    IntegratedSearch search;
    DefaultTableModel tblModel;
    public SanPhamBUS sanphamBUS = new SanPhamBUS();
    public OptionSanPhamBUS optionBUS = new OptionSanPhamBUS();
    public ArrayList<SanPhamDTO> dataTable = new ArrayList<>(sanphamBUS.getSetSP());
    public ArrayList<SanPhamDTO> resultTable = dataTable;
    //SanPhamDTO kh = new SanPhamDTO();
    private SelectForm filCate;
    public HashMap<String, String> mapCate = PhanLoaiBUS.getInstance().toMap();
    Main m;
    Color BackgroundColor = new Color(240, 247, 250);
    private PanelBorderRadius boxFilter;

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setBorder(new EmptyBorder(10, 20, 10, 20));
        this.setOpaque(true);

        tableSanPham = new JTable();
        scrollTableSanPham = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{ "Mã sản phẩm", "Tên sản phẩm", "Số lượng tồn", "Phân loại", "Thời gian bảo hành" };
        tblModel.setColumnIdentifiers(header);
        tableSanPham.setModel(tblModel);
        tableSanPham.setFocusable(false);
        scrollTableSanPham.setViewportView(tableSanPham);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableSanPham.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableSanPham.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableSanPham.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tableSanPham.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tableSanPham.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        tableSanPham.setAutoCreateRowSorter(true);
        TableSorter.configureTableColumnSorter(tableSanPham, 0, TableSorter.STRING_COMPARATOR);

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

        String[] action = { "create", "update", "delete", "detail", "view", "export" };
        mainFunction = new MainFunction(m.user.getIdQuyen(), "SER006", action);
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(this);
        }
        functionBar.add(mainFunction);

        search = new IntegratedSearch(SanPhamBUS.typeSearch);
        search.cbxChoose.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                Object[] filter = new Object[]{ filCate.getValue() };

                resultTable = new ArrayList<>(sanphamBUS.searchAndfilter(txt, type, filter));
                loadDataTable(resultTable);
            }
        });
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                Object[] filter = new Object[]{ filCate.getValue() };

                resultTable = new ArrayList<>(sanphamBUS.searchAndfilter(txt, type, filter));
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

        ArrayList<String> dataCate = new ArrayList<>(mapCate.keySet());
        dataCate.add(0, "Tất cả");
        filCate = new SelectForm("Phân loại", dataCate.toArray(new String[0]));
        filCate.setSelectedIndex(0);
        filCate.cbb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                Object[] filter = new Object[]{ filCate.getValue() };

                resultTable = new ArrayList<>(sanphamBUS.searchAndfilter(txt, type, filter));
                loadDataTable(resultTable);
            }
        });

        boxFilter.add(filCate);

        contentCenter.add(boxFilter, BorderLayout.WEST);
        // main là phần ở dưới để thống kê bảng biểu
        main = new PanelBorderRadius();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        contentCenter.add(main, BorderLayout.CENTER);

        main.add(scrollTableSanPham);
    }

    public SanPham(Main m) {
        this.m = m;
        initComponent();
        tableSanPham.setDefaultEditor(Object.class, null);
        loadDataTable();
    }

    public void loadDataTable() {
        search.txtSearchForm.setText("");
        search.cbxChoose.setSelectedIndex(0);
        filCate.setSelectedIndex(0);

        this.dataTable = new ArrayList<>(sanphamBUS.getSetSP());
        this.resultTable = dataTable;
        tblModel.setRowCount(0);
        for (SanPhamDTO sp : resultTable) {
            tblModel.addRow(new Object[]{
                    sp.getId(), sp.getName(), sp.getTonKho(), sp.getNameCate(), sp.getBaoHanhString()
            });
        }
    }

    public void loadDataTable(ArrayList<SanPhamDTO> data) {
        tblModel.setRowCount(0);
        for (SanPhamDTO sp : data) {
            tblModel.addRow(new Object[]{
                    sp.getId(), sp.getName(), sp.getTonKho(), sp.getNameCate(), sp.getBaoHanhString()
            });
        }
    }


    public int getRowSelected() {
        int index = tableSanPham.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm");
        }
        return index;
    }

//    public void importExcel() {
//        File excelFile;
//        FileInputStream excelFIS = null;
//        BufferedInputStream excelBIS = null;
//        XSSFWorkbook excelJTableImport = null;
//        ArrayList<SanPhamDTO> listExcel = new ArrayList<SanPhamDTO>();
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
//                    int id = SanPhamDAO.getInstance().getAutoIncrement();
//                    String tenkh = excelRow.getCell(0).getStringCellValue();
//                    String sdt = excelRow.getCell(1).getStringCellValue();
//                    String diachi = excelRow.getCell(2).getStringCellValue();
//                    if (Validation.isEmpty(tenkh) || Validation.isEmpty(sdt)
//                            || !isPhoneNumber(sdt) || sdt.length() != 10 || Validation.isEmpty(diachi)) {
//                        check = 0;
//                    }
//                    if (check == 1) {
//                        sanphamBUS.add(new SanPhamDTO(id, tenkh, sdt, diachi));
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
            SanPhamDialog khDialog = new SanPhamDialog(this, owner, "Thêm sản phẩm", true, "create");
        } else if (e.getSource() == mainFunction.btn.get("update")) {
            int index = getRowSelected();
            if (index != -1) {
                SanPhamDialog khDialog = new SanPhamDialog(this, owner, "Chỉnh sửa sản phẩm", true, "update", resultTable.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("delete")) {
            int index = getRowSelected();
            if (index != -1) {
                int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn xóa sản phẩm ?", "Xóa sản phẩm",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    sanphamBUS.removeSanPham(resultTable.get(index));
                    loadDataTable();
                }
            }
        } else if (e.getSource() == mainFunction.btn.get("detail")) {
            int index = getRowSelected();
            if (index != -1) {
                SanPhamDialog khDialog = new SanPhamDialog(this, owner, "Xem sản phẩm", true, "view", resultTable.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("import")) {
            //importExcel();
            System.out.println("Nhập excel");
        } else if (e.getSource() == mainFunction.btn.get("export")) {
            try {
                JTableExporter.exportJTableToExcel(tableSanPham);
            } catch (IOException ex) {
                Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
