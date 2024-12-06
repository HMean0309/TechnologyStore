package GUI.Dialog;

import BUS.ChiTietSPBUS;
import BUS.HoaDonBUS;
import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;
import DTO.ChiTietSanPhamDTO;
import DTO.HoaDonDTO;
import DTO.PhieuNhapDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import helper.Formater;
import helper.writePDF;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

public final class ChiTietPhieuDialog extends JDialog implements ActionListener {

    private boolean isHoaDon;
    HeaderTitle titlePage;
    JPanel pnmain, pnmain_top, pnmain_bottom, pnmain_bottom_right, pnmain_bottom_left, pnmain_btn;
    InputForm txtMaPhieu, txtNhanVien, txtDoiTac, txtThoiGian, txtPTTT, txtKM, txtChietKhau, txtTongGiaTri;
    DefaultTableModel tblModel, tblModelSeri;
    JTable table, tblSeri;
    JScrollPane scrollTable, scrollTableSeri;

    PhieuNhapDTO phieunhap;
    HoaDonDTO hoadon;
    ChiTietSPBUS ctspBUS = new ChiTietSPBUS();
    PhieuNhapBUS phieunhapBUS;
    HoaDonBUS hoadonBUS;

    HashMap<String, String> mapNameSP = SanPhamBUS.getInstance().toMapName();
    ButtonCustom btnPdf, btnHuyBo;
    HashMap<String, HashMap<String, ArrayList<ChiTietSanPhamDTO>>> chitietphieu;


    public ChiTietPhieuDialog(JFrame owner, String title, boolean modal, PhieuNhapDTO phieunhapDTO) {
        super(owner, title, modal);
        this.phieunhap = phieunhapDTO;
        this.isHoaDon = false;
        phieunhapBUS = new PhieuNhapBUS();
        ctspBUS = new ChiTietSPBUS();
        initComponent(title);
        initPhieuNhap();
        initChiTietPhieuNhap(phieunhap.getId());
        loadDataTableChiTietPhieu();
        this.setVisible(true);
    }

    public ChiTietPhieuDialog(JFrame owner, String title, boolean modal, HoaDonDTO hoadonDTO) {
        super(owner, title, modal);
        this.hoadon = hoadonDTO;
        this.isHoaDon = true;
        hoadonBUS = new HoaDonBUS();
        ctspBUS = new ChiTietSPBUS();
        initComponent(title);
        initHoaDon();
        initChiTietHoaDon(hoadon.getId());
        loadDataTableChiTietPhieu();
        this.setVisible(true);
    }

    private void initChiTietPhieuNhap(String idPN) {
        chitietphieu = new HashMap<>();
        LinkedHashSet<String> setIdSP = ctspBUS.getAllIdSPInPN(idPN);
        for (String idSP : setIdSP) {
            LinkedHashSet<String> setOption = ctspBUS.getAllOptionOfIdSPInPN(idPN, idSP);
            HashMap<String, ArrayList<ChiTietSanPhamDTO>> option_seri = new HashMap<>();
            for (String option : setOption) {
                ArrayList<ChiTietSanPhamDTO> series = new ArrayList<>(ctspBUS.getListOSPHaveIdSPColorInPN(idPN, idSP, option));
                option_seri.put(option, series);
            }
            this.chitietphieu.put(idSP, option_seri);
        }
    }

    private void initChiTietHoaDon(String idHD) {
        chitietphieu = new HashMap<>();
        LinkedHashSet<String> setIdSP = ctspBUS.getAllIdSPInHD(idHD);
        for (String idSP : setIdSP) {
            LinkedHashSet<String> setOption = ctspBUS.getAllOptionOfIdSPInHD(idHD, idSP);
            HashMap<String, ArrayList<ChiTietSanPhamDTO>> option_seri = new HashMap<>();
            for (String option : setOption) {
                ArrayList<ChiTietSanPhamDTO> series = new ArrayList<>(ctspBUS.getListOSPHaveIdSPColorInHD(idHD, idSP, option));
                option_seri.put(option, series);
            }
            this.chitietphieu.put(idSP, option_seri);
        }
    }

    public void initPhieuNhap() {
        txtMaPhieu.setText(phieunhap.getId());
        txtDoiTac.setText(phieunhap.getNameNCC());
        txtNhanVien.setText(phieunhap.getNameNhanVien());
        txtThoiGian.setText(Formater.formatTime(phieunhap.getNgayNhap()));
        txtTongGiaTri.setText(Formater.formatVND(phieunhap.getTotal()));
    }

    public void initHoaDon() {
        txtMaPhieu.setText(hoadon.getId());
        txtDoiTac.setText(hoadon.getNameKhachHang());
        txtNhanVien.setText(hoadon.getNameNhanVien());
        txtThoiGian.setText(Formater.formatTime(hoadon.getNgayLap()));
        txtKM.setText(hoadon.getKm());
        txtChietKhau.setText(Formater.formatVND(hoadon.getDiscountAmount()));
        txtTongGiaTri.setText(Formater.formatVND(hoadon.getOrderAmount()));
    }

    public void loadDataTableChiTietPhieu() {
        tblModel.setRowCount(0);
        for (String idSP : chitietphieu.keySet()) {
            HashMap<String, ArrayList<ChiTietSanPhamDTO>> color_seri = chitietphieu.get(idSP);
            for (String color : color_seri.keySet()) {
                ArrayList<ChiTietSanPhamDTO> series = color_seri.get(color);
                int count = series.size();
                int sum = 0;
                for (ChiTietSanPhamDTO seri : series) {
                    if (isHoaDon) {
                        sum += seri.getPrice();
                    } else {
                        sum += seri.getCost();
                    }
                }
                tblModel.addRow(new Object[]{
                        idSP, mapNameSP.get(idSP), color, count, Formater.formatVND(sum) });
            }
        }
    }

    public void loadDataTableSeri(ArrayList<ChiTietSanPhamDTO> dssp) {
        tblModelSeri.setRowCount(0);
        int size = dssp.size();
        for (int i = 0; i < size; i++) {
            int donGia = 0;
            if (isHoaDon) {
                donGia = dssp.get(i).getPrice();
            } else {
                donGia = dssp.get(i).getCost();
            }
            tblModelSeri.addRow(new Object[]{
                    i + 1, dssp.get(i).getSeri(), Formater.formatVND(donGia)
            });
        }
    }

    public void initComponent(String title) {
        this.setSize(new Dimension(1190, 500));
        this.setLayout(new BorderLayout(0, 0));

        pnmain = new JPanel(new BorderLayout());

        pnmain_top = new JPanel(new GridLayout(2, 1));
        JPanel pnmain_top_1 = new JPanel(new GridLayout(1, 4));
        pnmain_top_1.setBackground(Color.WHITE);
        txtMaPhieu = new InputForm("Mã phiếu");
        txtNhanVien = new InputForm("Nhân viên nhập");
        if (isHoaDon) {
            txtDoiTac = new InputForm("Khách hàng");
        } else {
            txtDoiTac = new InputForm("Nhà cung cấp");
        }
        txtThoiGian = new InputForm("Thời gian tạo");

        txtMaPhieu.setEditable(false);
        txtNhanVien.setEditable(false);
        txtDoiTac.setEditable(false);
        txtThoiGian.setEditable(false);

        pnmain_top_1.add(txtMaPhieu);
        pnmain_top_1.add(txtNhanVien);
        pnmain_top_1.add(txtDoiTac);
        pnmain_top_1.add(txtThoiGian);

        pnmain_top.add(pnmain_top_1);

        JPanel pnmain_top_2 = new JPanel(new GridLayout(1, 4));
        pnmain_top_2.setBackground(Color.WHITE);
        txtPTTT = new InputForm("Phương thức thanh toán");
        txtKM = new InputForm("Mã khuyến mãi");
        txtChietKhau = new InputForm("Chiết khấu");
        txtTongGiaTri = new InputForm("Tổng giá trị phiếu");

        txtPTTT.setVisible(false);
        if (isHoaDon) {
            txtKM.setEditable(false);
            txtChietKhau.setEditable(false);
            txtTongGiaTri.setEditable(false);
        } else {
            txtKM.setVisible(false);
            txtChietKhau.setVisible(false);
            txtTongGiaTri.setEditable(false);
        }

        pnmain_top_2.add(txtPTTT);
        pnmain_top_2.add(txtKM);
        pnmain_top_2.add(txtChietKhau);
        pnmain_top_2.add(txtTongGiaTri);

        pnmain_top.add(pnmain_top_2);

        pnmain_bottom = new JPanel(new BorderLayout(15, 5));
        pnmain_bottom.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnmain_bottom.setBackground(Color.WHITE);

        pnmain_bottom_left = new JPanel(new GridLayout(1, 1));
        table = new JTable();
        scrollTable = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{ "Mã SP", "Tên SP", "Màu sắc", "Số lượng", "Tổng giá" };
        tblModel.setColumnIdentifiers(header);
        table.setModel(tblModel);
        table.setFocusable(false);
        scrollTable.setViewportView(table);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int index = table.getSelectedRow();
                if (index != -1) {
                    String idSP = (String) table.getValueAt(index, 0);
                    String option = (String) table.getValueAt(index, 2);
                    loadDataTableSeri(chitietphieu.get(idSP).get(option));
                }
            }
        });
        pnmain_bottom_left.add(scrollTable);

        pnmain_bottom_right = new JPanel(new GridLayout(1, 1));
        pnmain_bottom_right.setPreferredSize(new Dimension(320, 10));
        tblSeri = new JTable();
        scrollTableSeri = new JScrollPane();
        tblModelSeri = new DefaultTableModel();
        tblModelSeri.setColumnIdentifiers(new String[]{ "STT", "Mã Seri", "Đơn giá" });
        tblSeri.setModel(tblModelSeri);
        tblSeri.setFocusable(false);
        tblSeri.setDefaultRenderer(Object.class, centerRenderer);
        tblSeri.getColumnModel().getColumn(1).setPreferredWidth(100);
        scrollTableSeri.setViewportView(tblSeri);
        pnmain_bottom_right.add(scrollTableSeri);

        pnmain_bottom.add(pnmain_bottom_left, BorderLayout.CENTER);
        pnmain_bottom.add(pnmain_bottom_right, BorderLayout.EAST);

        pnmain_btn = new JPanel(new FlowLayout());
        pnmain_btn.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnmain_btn.setBackground(Color.white);
        btnPdf = new ButtonCustom("Xuất file PDF", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);
        btnPdf.addActionListener(this);
        btnHuyBo.addActionListener(this);
        pnmain_btn.add(btnPdf);
        pnmain_btn.add(btnHuyBo);

        pnmain.add(pnmain_top, BorderLayout.NORTH);
        pnmain.add(pnmain_bottom, BorderLayout.CENTER);
        pnmain.add(pnmain_btn, BorderLayout.SOUTH);

        this.add(pnmain, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnHuyBo) {
            dispose();
        }
        if (source == btnPdf) {
            writePDF w = new writePDF();
            if (this.hoadon != null) {
                //w.writePX(hoadon.getMaphieu());
            }
            if (this.phieunhap != null) {
                w.writePN(phieunhap.getId(),tblModel);
            }
        }
    }
}
