package GUI.Panel;

import BUS.QuyenBUS;
import DTO.QuyenDTO;
import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import GUI.Component.PanelBorderRadius;
import GUI.Component.TableSorter;
import GUI.Dialog.PhanQuyenDialog;
import GUI.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Quyen extends JPanel implements ActionListener {

    PanelBorderRadius main, functionBar;
    JPanel contentCenter;
    JTable tableQuyen;
    JScrollPane scrollTableQuyen;
    MainFunction mainFunction;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    IntegratedSearch search;
    DefaultTableModel tblModel;
    public QuyenBUS quyenBUS = new QuyenBUS();
    public ArrayList<QuyenDTO> dataTable = new ArrayList<>(quyenBUS.getSetQuyen());
    public ArrayList<QuyenDTO> resultTable = dataTable;
    //QuyenDTO kh = new QuyenDTO();
    Main m;
    Color BackgroundColor = new Color(240, 247, 250);

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setBorder(new EmptyBorder(10, 20, 10, 20));
        this.setOpaque(true);

        tableQuyen = new JTable();
        scrollTableQuyen = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{ "Mã quyền", "Tên quyền" };
        tblModel.setColumnIdentifiers(header);
        tableQuyen.setModel(tblModel);
        tableQuyen.setFocusable(false);
        scrollTableQuyen.setViewportView(tableQuyen);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableQuyen.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableQuyen.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        tableQuyen.setAutoCreateRowSorter(true);
        TableSorter.configureTableColumnSorter(tableQuyen, 0, TableSorter.STRING_COMPARATOR);

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

        String[] action = { "create", "update", "delete", "detail" };
        mainFunction = new MainFunction(m.user.getIdQuyen(), "SER005", action);
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(this);
        }
        functionBar.add(mainFunction);

        search = new IntegratedSearch(QuyenBUS.typeSearch);
        search.cbxChoose.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                resultTable = new ArrayList<>(quyenBUS.search(txt, type));
                loadDataTable(resultTable);
            }
        });
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                resultTable = new ArrayList<>(quyenBUS.search(txt, type));
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

        main.add(scrollTableQuyen);
    }

    public Quyen(Main m) {
        this.m = m;
        initComponent();
        tableQuyen.setDefaultEditor(Object.class, null);
        loadDataTable();
    }

    public void loadDataTable() {
        search.txtSearchForm.setText("");
        search.cbxChoose.setSelectedIndex(0);

        this.dataTable = new ArrayList<>(quyenBUS.getSetQuyen());
        this.resultTable = dataTable;
        tblModel.setRowCount(0);
        for (QuyenDTO quyen : resultTable) {
            tblModel.addRow(new Object[]{
                    quyen.getId(), quyen.getName()
            });
        }
    }

    public void loadDataTable(ArrayList<QuyenDTO> data) {
        tblModel.setRowCount(0);
        for (QuyenDTO quyen : data) {
            tblModel.addRow(new Object[]{
                    quyen.getId(), quyen.getName()
            });
        }
    }


    public int getRowSelected() {
        int index = tableQuyen.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn quyền");
        }
        return index;
    }

//    public void importExcel() {
//        File excelFile;
//        FileInputStream excelFIS = null;
//        BufferedInputStream excelBIS = null;
//        XSSFWorkbook excelJTableImport = null;
//        ArrayList<QuyenDTO> listExcel = new ArrayList<QuyenDTO>();
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
//                    int id = QuyenDAO.getInstance().getAutoIncrement();
//                    String tenkh = excelRow.getCell(0).getStringCellValue();
//                    String sdt = excelRow.getCell(1).getStringCellValue();
//                    String diachi = excelRow.getCell(2).getStringCellValue();
//                    if (Validation.isEmpty(tenkh) || Validation.isEmpty(sdt)
//                            || !isPhoneNumber(sdt) || sdt.length() != 10 || Validation.isEmpty(diachi)) {
//                        check = 0;
//                    }
//                    if (check == 1) {
//                        quyenBUS.add(new QuyenDTO(id, tenkh, sdt, diachi));
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
            PhanQuyenDialog pqDialog = new PhanQuyenDialog(this, owner, "Thêm quyền", true, "create");
        } else if (e.getSource() == mainFunction.btn.get("update")) {
            int index = getRowSelected();
            if (index != -1) {
                PhanQuyenDialog pqDialog = new PhanQuyenDialog(this, owner, "Chỉnh sửa quyền", true, "update", resultTable.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("delete")) {
            int index = getRowSelected();
            if (index != -1) {
                int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn xóa quyền ?", "Xóa quyền",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    quyenBUS.removeQuyen(resultTable.get(index));
                    loadDataTable();
                }
            }
        } else if (e.getSource() == mainFunction.btn.get("detail")) {
            int index = getRowSelected();
            if (index != -1) {
                PhanQuyenDialog pqDialog = new PhanQuyenDialog(this, owner, "Xem quyền", true, "view", resultTable.get(index));
            }
        }
    }

}
