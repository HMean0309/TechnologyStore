package GUI.Panel;

import BUS.PhanLoaiBUS;
import BUS.SanPhamBUS;
import DTO.PhanLoaiDTO;
import DTO.SanPhamDTO;
import GUI.Component.*;
import GUI.Dialog.LoaiDialog;
import GUI.Main;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Loai extends JPanel implements ActionListener {

    PanelBorderRadius main, functionBar;
    JPanel contentCenter, contentRight;
    JTable tablePhanLoai;
    JScrollPane scrollTablePhanLoai;
    MainFunction mainFunction;
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);
    IntegratedSearch search;
    DefaultTableModel tblModel;
    public PhanLoaiBUS loaiBUS = new PhanLoaiBUS();
    private SanPhamBUS sanPhamBUS = new SanPhamBUS();
    public ArrayList<PhanLoaiDTO> dataTable = new ArrayList<>(loaiBUS.getSetPL());
    public ArrayList<PhanLoaiDTO> resultTable = dataTable;
    public ArrayList<SanPhamDTO> dataSP;
    public ProductItem[] listSP;
    //QuyenDTO kh = new QuyenDTO();
    Main m;
    Color BackgroundColor = new Color(240, 247, 250);
    private JScrollPane scrollPaneDSSP;
    private JLabel titleDSSP;
    private JPanel contentDSSP;

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setBorder(new EmptyBorder(10, 20, 10, 20));
        this.setOpaque(true);

        tablePhanLoai = new JTable();
        scrollTablePhanLoai = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{ "Mã phân loại", "Tên phân loại" };
        tblModel.setColumnIdentifiers(header);
        tablePhanLoai.setModel(tblModel);
        tablePhanLoai.setFocusable(false);
        scrollTablePhanLoai.setViewportView(tablePhanLoai);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tablePhanLoai.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tablePhanLoai.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tablePhanLoai.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = tablePhanLoai.getSelectedRow();
                if (index != -1) {
                    loadListSanPham(resultTable.get(index).getName());
                } else {
                    contentDSSP.removeAll();
                    contentDSSP.revalidate();
                    contentDSSP.repaint();
                }
            }
        });

        tablePhanLoai.setAutoCreateRowSorter(true);
        TableSorter.configureTableColumnSorter(tablePhanLoai, 0, TableSorter.STRING_COMPARATOR);

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
        mainFunction = new MainFunction(m.user.getIdQuyen(), "SER002", action);
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(this);
        }
        functionBar.add(mainFunction);

        search = new IntegratedSearch(PhanLoaiBUS.typeSearch);
        search.cbxChoose.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                resultTable = new ArrayList<>(loaiBUS.search(txt, type));
                loadDataTable(resultTable);
            }
        });
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String type = (String) search.cbxChoose.getSelectedItem();
                String txt = search.txtSearchForm.getText();
                resultTable = new ArrayList<>(loaiBUS.search(txt, type));
                loadDataTable(resultTable);
            }
        });

        search.btnReset.addActionListener((ActionEvent e) -> {
            loadDataTable();
        });
        functionBar.add(search);

        contentCenter.add(functionBar, BorderLayout.NORTH);

        // main là phần ở dưới để thống kê bảng biểu, danh sách
        main = new PanelBorderRadius();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.add(scrollTablePhanLoai);
        contentCenter.add(main, BorderLayout.CENTER);

        //Hiện danh sách sản phẩm
        contentRight = new JPanel();
        contentRight.setBackground(BackgroundColor);
        contentRight.setLayout(new BorderLayout(0, 15));
        contentRight.setBorder(new EmptyBorder(15, 10, 0, 10));
        contentRight.setPreferredSize(new Dimension(400, 600));

        contentDSSP = new JPanel();
        contentDSSP.setBackground(BackgroundColor);
        contentDSSP.setLayout(new BoxLayout(contentDSSP, BoxLayout.Y_AXIS));
        //contentDSSP.setPreferredSize(new Dimension(400, 500));

        titleDSSP = new JLabel("Danh sách sản phẩm");
        titleDSSP.setFont(new java.awt.Font(FlatRobotoFont.FAMILY, 1, 16));

        contentRight.add(titleDSSP, BorderLayout.PAGE_START);
        //contentRight.add(contentDSSP, BorderLayout.CENTER);
        scrollPaneDSSP = new JScrollPane(contentDSSP, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneDSSP.setBackground(BackgroundColor);
        scrollPaneDSSP.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentRight.add(scrollPaneDSSP, BorderLayout.CENTER);

        contentCenter.add(contentRight, BorderLayout.EAST);
    }

    public Loai(Main m) {
        this.m = m;
        initComponent();
        tablePhanLoai.setDefaultEditor(Object.class, null);
        loadDataTable();
    }

    public void loadDataTable() {
        search.txtSearchForm.setText("");
        search.cbxChoose.setSelectedIndex(0);

        contentDSSP.removeAll();
        contentDSSP.revalidate();
        contentDSSP.repaint();

        this.dataSP = new ArrayList<>();
        this.dataTable = new ArrayList<>(loaiBUS.getSetPL());
        this.resultTable = dataTable;
        tblModel.setRowCount(0);
        for (PhanLoaiDTO loai : resultTable) {
            tblModel.addRow(new Object[]{
                    loai.getId(), loai.getName()
            });
        }
    }

    public void loadDataTable(ArrayList<PhanLoaiDTO> data) {
        tblModel.setRowCount(0);
        for (PhanLoaiDTO loai : data) {
            tblModel.addRow(new Object[]{
                    loai.getId(), loai.getName()
            });
        }
    }

    public void loadListSanPham(String nameCate) {
        contentDSSP.removeAll();
        contentDSSP.revalidate();
        contentDSSP.repaint();
        dataSP = new ArrayList<>(sanPhamBUS.filterNameCate(nameCate));
        listSP = new ProductItem[dataSP.size()];
        for (int i = 0; i < dataSP.size(); i++) {
            listSP[i] = new ProductItem(dataSP.get(i), ProductItem.ProductType.NONE);
            contentDSSP.add(listSP[i]);
            listSP[i].setPreferredSize(new Dimension(380, 80));
            contentDSSP.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        contentDSSP.revalidate();
        contentDSSP.repaint();
    }

    public int getRowSelected() {
        int index = tablePhanLoai.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phân loại");
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
//                        loaiBUS.add(new QuyenDTO(id, tenkh, sdt, diachi));
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
            LoaiDialog pqDialog = new LoaiDialog(this, owner, "Thêm phân loại", true, "create");
        } else if (e.getSource() == mainFunction.btn.get("update")) {
            int index = getRowSelected();
            if (index != -1) {
                LoaiDialog pqDialog = new LoaiDialog(this, owner, "Chỉnh sửa phân loại", true, "update", resultTable.get(index));
            }
        } else if (e.getSource() == mainFunction.btn.get("delete")) {
            int index = getRowSelected();
            if (index != -1) {
                int input = JOptionPane.showConfirmDialog(null,
                        "Bạn có chắc chắn muốn xóa phân loại ?", "Xóa phân loại",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    loaiBUS.removePhanLoai(resultTable.get(index));
                    loadDataTable();
                }
            }
        } else if (e.getSource() == mainFunction.btn.get("detail")) {
            int index = getRowSelected();
            if (index != -1) {
                LoaiDialog pqDialog = new LoaiDialog(this, owner, "Xem phân loại", true, "view", resultTable.get(index));
            }
        }
    }

}
