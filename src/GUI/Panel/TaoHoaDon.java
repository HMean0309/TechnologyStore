package GUI.Panel;

import BUS.*;
import DTO.*;
import GUI.Component.*;
import GUI.Dialog.ListChiTietSanPham;
import GUI.Dialog.ListKhachHang;
import GUI.Main;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import helper.Formater;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

//ItemListener, ActionListener
public class TaoHoaDon extends JPanel implements ActionListener, ItemListener {

    PanelBorderRadius right, left;
    JPanel contentCenter, left_top, main, content_right_bottom, content_btn;
    JTable tableHoaDon;
    JScrollPane scrollTableHoaDon;
    DefaultTableModel tblModel;
    ButtonCustom btnAddSP, btnEditSP, btnDelete, btnDatHang;
    InputForm txtMaphieu, txtNhanVien, txtMaSp, txtTenSp, txtSLTon;
    SelectForm cbOption;
    JTextField txtTimKiem;
    Main m;
    Color BackgroundColor = new Color(240, 247, 250);
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);

    ButtonGroup groupSanPham = new ButtonGroup();
    SanPhamBUS sanphamBUS = new SanPhamBUS();
    ChiTietSPBUS ctspBUS = new ChiTietSPBUS();
    OptionSanPhamBUS optionBUS = new OptionSanPhamBUS();
    HashMap<String, String> mapKH = KhachHangBUS.getInstance().toMap();
    HoaDonBUS hoadonBUS = new HoaDonBUS();
    ChiTietHoaDonBUS cthdBUS = new ChiTietHoaDonBUS();
    NhanVienDTO nv;
    ArrayList<String> dataFilterCate;
    ArrayList<SanPhamDTO> dataSP;
    private ProductItem[] listSP;
    HashMap<String, HashMap<String, ArrayList<ChiTietSanPhamDTO>>> gioHang;
    ArrayList<ChiTietSanPhamDTO> seriesAtMoment;
    int mahoadon;
    int rowPhieuSelect = -1;
    private JLabel value_tongtien;
    private ButtonCustom btnHuyBo;
    private JComboBox<String> filterCate;
    private JPanel contentSanPham;
    private JScrollPane scrollPaneSanPham;
    private int tongTien;
    private JTextField txtKh, txtKM;
    private JPanel pn_discount;
    private int discount;
    private JLabel value_discount;


    public TaoHoaDon(NhanVienDTO nv, String type, Main m) {
        this.nv = nv;
        this.m = m;
        initComponent();
        gioHang = new HashMap<>();
    }

    public TaoHoaDon(Main m, NhanVienDTO nv) {
        this.nv = nv;
        this.m = m;
        initComponent();
        loadListSanPham();
        gioHang = new HashMap<>();
    }

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);
        this.setBorder(new EmptyBorder(10, 15, 10, 15));


        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(5, 5));
        this.add(contentCenter, BorderLayout.CENTER);

        left = new PanelBorderRadius();
        left.setLayout(new BorderLayout(0, 5));
        left.setBackground(Color.white);

        // Chứa tất cả phần ở phía trái trên cùng
        left_top = new JPanel();
        left_top.setLayout(new BorderLayout(10, 0));
        left_top.setBorder(new EmptyBorder(5, 5, 10, 10));
        left_top.setOpaque(false);

        JPanel content_top, content_left, content_right, content_right_top;
        content_top = new JPanel(new GridLayout(1, 2, 10, 5));
        content_top.setOpaque(false);
        content_left = new JPanel(new BorderLayout(5, 5));
        content_left.setOpaque(false);
        content_left.setPreferredSize(new Dimension(0, 300));

        JPanel pn_timkiem_filter = new JPanel();
        pn_timkiem_filter.setLayout(new BorderLayout(5, 0));

        txtTimKiem = new JTextField();
        txtTimKiem.putClientProperty("JTextField.placeholderText", "Tên sản phẩm, mã sản phẩm...");
        txtTimKiem.putClientProperty("JTextField.showClearButton", true);
        txtTimKiem.putClientProperty("JTextField.leadingIcon", new FlatSVGIcon("./icon/search.svg"));
        txtTimKiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                String type = "Tất cả";
                String txt = txtTimKiem.getText().trim();
                Object[] filter = new Object[]{ filterCate.getSelectedItem() };

                dataSP = new ArrayList<>(sanphamBUS.searchAndfilter(txt, type, filter));
                loadListSanPham(dataSP);
                loadFormChiTiet();
            }
        });

        dataFilterCate = new ArrayList<>(PhanLoaiBUS.getInstance().toMap().keySet());
        dataFilterCate.add(0, "Tất cả");
        filterCate = new JComboBox<>(dataFilterCate.toArray(new String[0]));
        filterCate.setPreferredSize(new Dimension(120, 40));
        filterCate.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String type = "Tất cả";
                String txt = txtTimKiem.getText().trim();
                Object[] filter = new Object[]{ filterCate.getSelectedItem() };

                dataSP = new ArrayList<>(sanphamBUS.searchAndfilter(txt, type, filter));
                loadListSanPham(dataSP);
                loadFormChiTiet();
            }
        });
        pn_timkiem_filter.add(txtTimKiem, BorderLayout.CENTER);
        pn_timkiem_filter.add(filterCate, BorderLayout.WEST);

        // List sản phẩm
        contentSanPham = new JPanel();
        contentSanPham.setBackground(BackgroundColor);
        contentSanPham.setLayout(new BoxLayout(contentSanPham, BoxLayout.Y_AXIS));
        contentSanPham.setBorder(new EmptyBorder(5, 10, 10, 5));
        scrollPaneSanPham = new JScrollPane(contentSanPham, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneSanPham.setBackground(BackgroundColor);
        scrollPaneSanPham.setBorder(new EmptyBorder(0, 0, 15, 0));

        content_left.add(pn_timkiem_filter, BorderLayout.NORTH);
        content_left.add(scrollPaneSanPham, BorderLayout.CENTER);

        //Form chi tiết
        content_right = new JPanel(new BorderLayout(5, 5));
        content_right.setOpaque(false);

        content_right_top = new JPanel(new GridLayout(2, 1, 0, 0));
        content_right_top.setPreferredSize(new Dimension(100, 160));
        txtMaSp = new InputForm("Mã sản phẩm");
        txtMaSp.setEditable(false);
        txtTenSp = new InputForm("Tên sản phẩm");
        txtTenSp.setEditable(false);
        content_right_top.add(txtMaSp);
        content_right_top.add(txtTenSp);


        content_right_bottom = new JPanel(new GridLayout(3, 1, 0, 0));
        content_right_bottom.setOpaque(false);

        JPanel content_right_bottom_1 = new JPanel(new BorderLayout());
        cbOption = new SelectForm("Lựa chọn", new String[]{ "Chọn màu" });
        cbOption.cbb.addItemListener(this);
        txtSLTon = new InputForm("Số lượng tồn");
        //txtSLTon.setPreferredSize(new Dimension(160, 40));
        PlainDocument soLuongNhap = (PlainDocument) txtSLTon.getTxtForm().getDocument();
        soLuongNhap.setDocumentFilter((new NumericDocumentFilter()));
        txtSLTon.setEditable(false);

        content_right_bottom_1.add(cbOption, BorderLayout.WEST);
        content_right_bottom_1.add(txtSLTon, BorderLayout.CENTER);
        content_right_bottom.add(content_right_bottom_1);

        content_right.add(content_right_top, BorderLayout.NORTH);
        content_right.add(content_right_bottom, BorderLayout.CENTER);

        content_top.add(content_left);
        content_top.add(content_right);

        //Btn Giỏ hàng
        content_btn = new JPanel();
        content_btn.setPreferredSize(new Dimension(0, 50));
        content_btn.setLayout(new GridLayout(1, 3, 10, 5));
        content_btn.setBorder(new EmptyBorder(10, 250, 0, 10));
        content_btn.setOpaque(false);
        btnAddSP = new ButtonCustom("Thêm chi tiết", "success", 14);
        btnEditSP = new ButtonCustom("Sửa chi tiết", "warning", 14);
        btnDelete = new ButtonCustom("Xoá chi tiết", "danger", 14);
        btnAddSP.addActionListener(this);
        btnEditSP.addActionListener(this);
        btnDelete.addActionListener(this);
        btnEditSP.setEnabled(false);
        btnDelete.setEnabled(false);
        btnAddSP.setEnabled(false);
        content_btn.add(btnAddSP);
        content_btn.add(btnEditSP);
        content_btn.add(btnDelete);

        left_top.add(content_top, BorderLayout.CENTER);
        left_top.add(content_btn, BorderLayout.SOUTH);

        // Giỏ hàng
        tableHoaDon = new JTable();
        scrollTableHoaDon = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{ "Mã SP", "Màu sắc", "Số lượng", "Tổng giá trị" };
        tblModel.setColumnIdentifiers(header);
        tableHoaDon.setModel(tblModel);
        scrollTableHoaDon.setViewportView(tableHoaDon);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = tableHoaDon.getColumnModel();
        for (int i = 0; i < header.length; i++) {
            columnModel.getColumn(i).setCellRenderer(centerRenderer);
        }
        tableHoaDon.setDefaultEditor(Object.class, null);
        tableHoaDon.setFocusable(false);
        tableHoaDon.setAutoCreateRowSorter(true);
        scrollTableHoaDon.setViewportView(tableHoaDon);

        tableHoaDon.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = tableHoaDon.getSelectedRow();
                if (index != -1) {
                    String idSP = (String) tblModel.getValueAt(index, 0);
                    String color = (String) tblModel.getValueAt(index, 1);
                    SanPhamDTO sp = dataSP.stream()
                            .filter(sanPhamDTO -> sanPhamDTO.getId().equals(idSP))
                            .findFirst()
                            .orElse(null);
                    loadFormChiTiet(sp);
                    cbOption.setSelectedItem(color);
                }
            }
        });

        main = new JPanel();
        main.setOpaque(false);
        main.setPreferredSize(new Dimension(0, 250));
        main.setBorder(new EmptyBorder(0, 5, 10, 10));
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        main.add(scrollTableHoaDon);
        left.add(left_top, BorderLayout.CENTER);
        left.add(main, BorderLayout.SOUTH);

        //Panel Right - Thông tin phiếu nhập
        right = new PanelBorderRadius();
        right.setPreferredSize(new Dimension(320, 0));
        right.setBorder(new EmptyBorder(5, 5, 5, 5));
        right.setLayout(new BorderLayout());

        JPanel right_top, right_center, right_bottom, pn_tongtien;
        right_top = new JPanel(new GridLayout(2, 1, 0, 0));
        right_top.setPreferredSize(new Dimension(300, 180));
        right_top.setOpaque(false);
        txtMaphieu = new InputForm("Mã hóa đơn");
        txtMaphieu.setText(hoadonBUS.createID());
        txtMaphieu.setEditable(false);
        txtNhanVien = new InputForm("Nhân viên lập");
        txtNhanVien.setText(nv.getId());
        txtNhanVien.setEditable(false);

        right_top.add(txtMaphieu);
        right_top.add(txtNhanVien);

        right_center = new JPanel();
        right_center.setLayout(new GridLayout(5, 1));
        right_center.setPreferredSize(new Dimension(300, 180));
        //Chọn khách hàng
        JPanel khachJPanel = new JPanel(new BorderLayout());
        khachJPanel.setPreferredSize(new Dimension(0, 40));
        khachJPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
        khachJPanel.setOpaque(false);
        JPanel kJPanelLeft = new JPanel(new GridLayout(1, 1));
        kJPanelLeft.setOpaque(false);
        kJPanelLeft.setPreferredSize(new Dimension(40, 0));
        ButtonCustom btnKh = new ButtonCustom("Chọn khách hàng", "success", 14);
        kJPanelLeft.add(btnKh);
        btnKh.addActionListener((ActionEvent e) -> {
            ListKhachHang listkh = new ListKhachHang(this, owner, "Chọn khách hàng", true);
            KhachHangDTO khachHangDTO = listkh.getKh();
            if (khachHangDTO != null) {
                txtKh.setText(khachHangDTO.getId());
            }
        });

        txtKh = new JTextField("");
        txtKh.setEditable(false);
        khachJPanel.add(kJPanelLeft, BorderLayout.EAST);
        khachJPanel.add(txtKh, BorderLayout.CENTER);
        JPanel khPanel = new JPanel(new GridLayout(2, 1, 5, 0));
        khPanel.setBackground(Color.WHITE);
        khPanel.setPreferredSize(new Dimension(0, 80));
        JLabel khachKhangJLabel = new JLabel("Khách hàng");
        khachKhangJLabel.setBorder(new EmptyBorder(0, 10, 0, 10));
        khPanel.add(khachKhangJLabel);
        khPanel.add(khachJPanel);

        right_center.add(khPanel);
        right_center.setOpaque(false);

        right_bottom = new JPanel(new GridLayout(3, 1));
        right_bottom.setPreferredSize(new Dimension(300, 160));
        right_bottom.setBorder(new EmptyBorder(10, 10, 10, 10));
        right_bottom.setOpaque(false);

        pn_tongtien = new JPanel(new BorderLayout(5, 0));
        pn_tongtien.setBorder(new EmptyBorder(0, 5, 0, 5));
        pn_tongtien.setOpaque(false);
        JLabel title_tongtien = new JLabel("TỔNG TIỀN:");
        title_tongtien.setFont(new Font(FlatRobotoFont.FAMILY, 1, 18));
        title_tongtien.setForeground(new Color(255, 51, 51));

        tongTien = 0;
        value_tongtien = new JLabel(Formater.formatVND(tongTien));
        value_tongtien.setFont(new Font(FlatRobotoFont.FAMILY, 1, 18));
        value_tongtien.setHorizontalAlignment(SwingConstants.RIGHT);
        pn_tongtien.add(title_tongtien, BorderLayout.WEST);
        pn_tongtien.add(value_tongtien, BorderLayout.CENTER);
        right_bottom.add(pn_tongtien);

        btnDatHang = new ButtonCustom("Đặt hàng", "excel", 14);
        btnDatHang.addActionListener(this);
        btnHuyBo = new ButtonCustom("Hủy Bỏ", "danger", 14);
        btnHuyBo.addActionListener(this);
        right_bottom.add(btnDatHang);
        right_bottom.add(btnHuyBo);

        right.add(right_top, BorderLayout.NORTH);
        right.add(right_center, BorderLayout.CENTER);
        right.add(right_bottom, BorderLayout.SOUTH);

        //Merge
        contentCenter.add(left, BorderLayout.CENTER);
        contentCenter.add(right, BorderLayout.EAST);
    }

    public void loadListSanPham() {
        contentSanPham.removeAll();
        contentSanPham.revalidate();
        contentSanPham.repaint();
        dataSP = new ArrayList<>(sanphamBUS.getSetSP());
        listSP = new ProductItem[dataSP.size()];
        for (int i = 0; i < dataSP.size(); i++) {
            listSP[i] = new ProductItem(dataSP.get(i), ProductItem.ProductType.RADIO);
            contentSanPham.add(listSP[i]);
            groupSanPham.add(listSP[i].getRadio());
            int index = i;
            listSP[i].addPropertyChangeListener(new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if ("isSelected".equals(evt.getPropertyName()) && (Boolean) evt.getNewValue()) {
                        loadFormChiTiet(listSP[index].getSanPham());
                    }
                }
            });

            contentSanPham.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        contentSanPham.revalidate();
        contentSanPham.repaint();

        txtTimKiem.setText("");
        filterCate.setSelectedIndex(0);
    }

    public void loadListSanPham(ArrayList<SanPhamDTO> result) {
        contentSanPham.removeAll();
        contentSanPham.revalidate();
        contentSanPham.repaint();
        listSP = new ProductItem[result.size()];
        for (int i = 0; i < result.size(); i++) {
            listSP[i] = new ProductItem(result.get(i), ProductItem.ProductType.RADIO);
            contentSanPham.add(listSP[i]);
            groupSanPham.add(listSP[i].getRadio());
            int index = i;
            listSP[i].addPropertyChangeListener(new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if ("isSelected".equals(evt.getPropertyName()) && (Boolean) evt.getNewValue()) {
                        loadFormChiTiet(listSP[index].getSanPham());
                    }
                }
            });

            contentSanPham.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        contentSanPham.revalidate();
        contentSanPham.repaint();
    }

    public void loadFormChiTiet(SanPhamDTO sp) {
        String idSP = sp.getId();
        txtMaSp.setText(sp.getId());
        txtTenSp.setText(sp.getName());
        ArrayList<String> dataOption = new ArrayList<>(optionBUS.getAllColor(idSP));
        dataOption.add(0, "Chọn màu");
        cbOption.setArr(dataOption.toArray(new String[0]));
        txtSLTon.setText("");

        btnAddSP.setEnabled(true);
        btnEditSP.setEnabled(false);
        btnDelete.setEnabled(false);

    }


    public void loadFormChiTiet() {
        txtMaSp.setText("");
        txtTenSp.setText("");
        txtSLTon.setText("");
        cbOption.setArr(new String[]{ "Chọn màu" });
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnAddSP && validateForm()) {
            String idSP = txtMaSp.getText().trim();
            String color = cbOption.getValue();
            ListChiTietSanPham selectSeri = new ListChiTietSanPham(seriesAtMoment, owner, "Chọn seri", true);
            if (selectSeri.getResultList().isEmpty()) {
                return;
            }
            ArrayList<ChiTietSanPhamDTO> selectedSeries = selectSeri.getResultList();
            boolean isDupliIdSP = gioHang.containsKey(idSP);
            HashMap<String, ArrayList<ChiTietSanPhamDTO>> option_seri;

            if (isDupliIdSP) {
                option_seri = gioHang.get(idSP);
                option_seri.put(color, selectedSeries);
                gioHang.put(idSP, option_seri);
            } else {
                option_seri = new HashMap<>();
                option_seri.put(color, selectedSeries);
                gioHang.put(idSP, option_seri);
            }

            loadFormChiTiet();
            loadTableHoaDon();
            loadListSanPham();
            btnAddSP.setEnabled(false);
        } else if (source == btnDelete) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == 1) {
                return;
            }
            String idSP = txtMaSp.getText().trim();
            String color = cbOption.getValue();
            HashMap<String, ArrayList<ChiTietSanPhamDTO>> option_seri = gioHang.get(idSP);
            option_seri.remove(color);
            if (option_seri.isEmpty()) {
                gioHang.remove(idSP);
            }
            loadFormChiTiet();
            loadTableHoaDon();
            loadListSanPham();
            btnAddSP.setEnabled(true);
            btnEditSP.setEnabled(false);
            btnDelete.setEnabled(false);
        } else if (source == btnEditSP && validateForm()) {
            String idSP = txtMaSp.getText().trim();
            String color = cbOption.getValue();
            HashMap<String, ArrayList<ChiTietSanPhamDTO>> option_seri = gioHang.get(idSP);
            ArrayList<ChiTietSanPhamDTO> oldSelected = option_seri.get(color);
            ListChiTietSanPham selectSeri = new ListChiTietSanPham(seriesAtMoment, owner, "Chọn seri", true, oldSelected);
            ArrayList<ChiTietSanPhamDTO> selectedSeries = selectSeri.getResultList();
            option_seri.put(color, selectedSeries);
            gioHang.put(idSP, option_seri);

            loadFormChiTiet();
            loadTableHoaDon();
            loadListSanPham();
            btnEditSP.setEnabled(false);
            btnDelete.setEnabled(false);

        } else if (source == btnDatHang && validateHoaDon()) {
            String idHD = txtMaphieu.getText();
            String idNV = nv.getId();
            String idKH = txtKh.getText();

            hoadonBUS.addHoaDon(new HoaDonDTO(idHD, LocalDateTime.now(), tongTien, 0,
                    idKH, idNV, mapKH.get(idKH), nv.getName(), false));
            for (String idSP : gioHang.keySet()) {
                HashMap<String, ArrayList<ChiTietSanPhamDTO>> option_seri = gioHang.get(idSP);
                for (String color : option_seri.keySet()) {
                    ArrayList<ChiTietSanPhamDTO> series = option_seri.get(color);
                    ctspBUS.addAllOSP(series);

                    ArrayList<ChiTietHoaDonDTO> chiTietHoaDon = new ArrayList<>(series.size());
                    for (ChiTietSanPhamDTO ctsp : series) {
                        chiTietHoaDon.add(new ChiTietHoaDonDTO(idHD, ctsp.getSeri(), ctsp.getPrice()));
                    }
                    cthdBUS.addAllChiTietHoaDon(chiTietHoaDon);
                }
            }
            JOptionPane.showMessageDialog(this, "Tạo Hóa Đơn Thành Công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            HoaDon pn = new HoaDon(m, nv);
            m.setPanel(pn);
        } else if (source == btnHuyBo) {
            HoaDon pn = new HoaDon(m, nv);
            m.setPanel(pn);
        }
    }

    private void loadTableHoaDon() {
        tblModel.setRowCount(0);
        tongTien = 0;
        for (String idSP : gioHang.keySet()) {
            HashMap<String, ArrayList<ChiTietSanPhamDTO>> color_seri = gioHang.get(idSP);
            for (String color : color_seri.keySet()) {
                ArrayList<ChiTietSanPhamDTO> series = color_seri.get(color);
                int count = series.size();
                int sum = 0;
                for (ChiTietSanPhamDTO seri : series) {
                    sum += seri.getPrice();
                }
                tongTien += sum;
                tblModel.addRow(new Object[]{ idSP, color, count, Formater.formatVND(sum) });
            }
        }
        value_tongtien.setText(Formater.formatVND(tongTien));
    }

    private boolean validateForm() {
        //Kiểm tra lựa chọn màu
        if (cbOption.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn màu", "Lựa chọn", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    public boolean validateHoaDon() {
        //Kiểm tra chọn khách hàng
        String khachHang = txtKh.getText();
        if (khachHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng!", "Khách hàng", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        //Kiểm tra chi tiết phiếu nhập
        if (tongTien == 0) {
            JOptionPane.showMessageDialog(this, "Hóa đơn trống", "Hóa đơn", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        String idSP = txtMaSp.getText().trim();
        String color = cbOption.getValue();
        seriesAtMoment = ctspBUS.getSeriByColorSP(idSP, color);
        txtSLTon.setText(String.valueOf(seriesAtMoment.size()));
        if (gioHang.containsKey(idSP)) {
            HashMap<String, ArrayList<ChiTietSanPhamDTO>> option_seri = gioHang.get(idSP);
            if (option_seri.containsKey(color)) {
                btnEditSP.setEnabled(true);
                btnDelete.setEnabled(true);
                btnAddSP.setEnabled(false);
            }
        }
    }

//    public void eventBtnNhapHang() {
//        if (chitietphieu.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Chưa có sản phẩm nào trong phiếu !", "Cảnh báo !", JOptionPane.ERROR_MESSAGE);
//        } else {
//            int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn tạo phiếu nhập !", "Xác nhận tạo phiếu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
//            if (input == 0) {
//                int mancc = nccBus.getByIndex(cbxKhachHang.getSelectedIndex()).getMancc();
//                long now = System.currentTimeMillis();
//                Timestamp currenTime = new Timestamp(now);
//                HoaDonDTO pn = new HoaDonDTO(mancc, mahoadon, nvDto.getManv(), currenTime, hoadonBus.getTongTien(chitietphieu), 1);
//                boolean result = hoadonBus.add(pn, chitietphieu, chitietsanpham);
//                if (result) {
//                    JOptionPane.showMessageDialog(this, "Nhập hàng thành công !");
//                    HoaDon pnlPhieu = new HoaDon(m, nvDto);
//                    m.setPanel(pnlPhieu);
//                } else {
//                    JOptionPane.showMessageDialog(this, "Nhập hàng không thành công !", "Cảnh báo !", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        }
//    }
//
//    public void getImeifromFile() {
//        File excelFile;
//        FileInputStream excelFIS = null;
//        BufferedInputStream excelBIS = null;
//        XSSFWorkbook excelJTableImport = null;
//        JFileChooser jf = new JFileChooser();
//        int result = jf.showOpenDialog(null);
//        jf.setDialogTitle("Open file");
//        Workbook workbook = null;
//        if (result == JFileChooser.APPROVE_OPTION) {
//            try {
//                excelFile = jf.getSelectedFile();
//                JOptionPane.showMessageDialog(this, excelFile);
//                excelFIS = new FileInputStream(excelFile);
//                excelBIS = new BufferedInputStream(excelFIS);
//                excelJTableImport = new XSSFWorkbook(excelBIS);
//                XSSFSheet excelSheet = excelJTableImport.getSheetAt(0);
//                for (int row = 1; row <= excelSheet.getLastRowNum(); row++) {
//                    XSSFRow excelRow = excelSheet.getRow(row);
//                    String maimei = excelRow.getCell(0).getStringCellValue();
//                    if (maimei.length() != 15) {
//                        continue;
//                    } else {
//                        this.listmaimei.add(maimei);
//                        System.out.println(maimei);
//                    }
//                }
//            } catch (FileNotFoundException ex) {
//                System.out.println("Lỗi đọc file 1");
//            } catch (IOException ex) {
//                System.out.println("Lỗi đọc file 2");
//            }
//        }
//    }
}