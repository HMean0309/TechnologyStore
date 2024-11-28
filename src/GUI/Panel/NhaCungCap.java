package GUI.Panel;

import BUS.NhaCungCapBUS;
import DTO.NhaCungCapDTO;
import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import GUI.Component.PanelBorderRadius;
import GUI.Component.TableSorter;
import GUI.Dialog.NhaCungCapDialog;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhaCungCap extends JPanel implements ActionListener {

    PanelBorderRadius main, functionBar;
    JPanel contentCenter;
    JTable tableNhaCungCap;
    JScrollPane scrollTableNhaCungCap;
    MainFunction mainFunction;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    IntegratedSearch search;
    DefaultTableModel tblModel;
    public NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    public ArrayList<NhaCungCapDTO> dataTable = new ArrayList<>(nccBUS.getSetNCC());
    public ArrayList<NhaCungCapDTO> resultTable = dataTable;
    //NhaCungCapDTO kh = new NhaCungCapDTO();
    Main m;
    Color BackgroundColor = new Color(240, 247, 250);

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setBorder(new EmptyBorder(10, 20, 10, 20));
        this.setOpaque(true);

        tableNhaCungCap = new JTable();
        scrollTableNhaCungCap = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{ "Mã nhà cung cấp", "Tên nhà cung cấp", "Số điện thoại", "Địa chỉ" };
        tblModel.setColumnIdentifiers(header);
        tableNhaCungCap.setModel(tblModel);
        tableNhaCungCap.setFocusable(false);
        scrollTableNhaCungCap.setViewportView(tableNhaCungCap);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableNhaCungCap.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableNhaCungCap.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableNhaCungCap.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tableNhaCungCap.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        tableNhaCungCap.setAutoCreateRowSorter(true);
        TableSorter.configureTableColumnSorter(tableNhaCungCap, 0, TableSorter.STRING_COMPARATOR);

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
        mainFunction = new MainFunction(m.user.getIdQuyen(), "SER003", action);
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(this);
        }
        functionBar.add(mainFunction);

        search = new IntegratedSearch(NhaCungCapBUS.type);
        search.cbxChoose.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                resultTable = new ArrayList<>(nccBUS.search(txt, type));
                loadDataTable(resultTable);
            }
        });
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                resultTable = new ArrayList<>(nccBUS.search(txt, type));
                loadDataTable(resultTable);
            }
        });

        search.btnReset.addActionListener((ActionEvent e) -> {
            loadDataTable();
        });
        functionBar.add(search);

        contentCenter.add(functionBar, BorderLayout.NORTH);

        // main là phần ở dưới để thống kê bảng biểu
        main = new PanelBorderRadius();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        contentCenter.add(main, BorderLayout.CENTER);

        main.add(scrollTableNhaCungCap);
    }

    public NhaCungCap(Main m) {
        this.m = m;
        initComponent();
        tableNhaCungCap.setDefaultEditor(Object.class, null);
        loadDataTable();
    }

    public void loadDataTable() {
        search.txtSearchForm.setText("");
        search.cbxChoose.setSelectedIndex(0);

        this.dataTable = new ArrayList<>(nccBUS.getSetNCC());
        this.resultTable = dataTable;
        tblModel.setRowCount(0);
        for (NhaCungCapDTO nhaCungCap : resultTable) {
            tblModel.addRow(new Object[]{
                    nhaCungCap.getId(), nhaCungCap.getName(), nhaCungCap.getPhone(), nhaCungCap.getFullAddress()
            });
        }
    }

    public void loadDataTable(ArrayList<NhaCungCapDTO> data) {
        tblModel.setRowCount(0);
        for (NhaCungCapDTO nhaCungCap : data) {
            tblModel.addRow(new Object[]{
                    nhaCungCap.getId(), nhaCungCap.getName(), nhaCungCap.getPhone(), nhaCungCap.getFullAddress()
            });
        }
    }

    public int getRowSelected() {
        int index = tableNhaCungCap.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà cung cấp");
        }
        return index;
    }

//    public void importExcel() {
//        File excelFile;
//        FileInputStream excelFIS = null;
//        BufferedInputStream excelBIS = null;
//        XSSFWorkbook excelJTableImport = null;
//        ArrayList<NhaCungCapDTO> listExcel = new ArrayList<NhaCungCapDTO>();
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
//                    int id = NhaCungCapDAO.getInstance().getAutoIncrement();
//                    String tenkh = excelRow.getCell(0).getStringCellValue();
//                    String sdt = excelRow.getCell(1).getStringCellValue();
//                    String diachi = excelRow.getCell(2).getStringCellValue();
//                    if (Validation.isEmpty(tenkh) || Validation.isEmpty(sdt)
//                            || !isPhoneNumber(sdt) || sdt.length() != 10 || Validation.isEmpty(diachi)) {
//                        check = 0;
//                    }
//                    if (check == 1) {
//                        khachhangBUS.add(new NhaCungCapDTO(id, tenkh, sdt, diachi));
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
            NhaCungCapDialog khDialog = new NhaCungCapDialog(this, owner, "Thêm nhà cung cấp", true, "create");
        } else if (e.getSource() == mainFunction.btn.get("update")) {
            int index = getRowSelected();
            if (index != -1) {
                NhaCungCapDialog khDialog = new NhaCungCapDialog(this, owner, "Chỉnh sửa nhà cung cấp", true, "update", resultTable.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("delete")) {
            int index = getRowSelected();
            if (index != -1) {
                int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn xóa nhà cung cấp ?", "Xóa nhà cung cấp",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    nccBUS.removeNhaCungCap(resultTable.get(index));
                    loadDataTable();
                }
            }
        } else if (e.getSource() == mainFunction.btn.get("detail")) {
            int index = getRowSelected();
            if (index != -1) {
                NhaCungCapDialog khDialog = new NhaCungCapDialog(this, owner, "Xem nhà cung cấp", true, "view", resultTable.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("import")) {
            //importExcel();
            System.out.println("Nhập excel");
        } else if (e.getSource() == mainFunction.btn.get("export")) {
            try {
                JTableExporter.exportJTableToExcel(tableNhaCungCap);
            } catch (IOException ex) {
                Logger.getLogger(NhaCungCap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
