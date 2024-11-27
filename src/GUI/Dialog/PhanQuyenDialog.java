package GUI.Dialog;

import BUS.ChucNangBUS;
import BUS.QuyenBUS;
import DTO.ChiTietQuyenDTO;
import DTO.ChucNangDTO;
import DTO.QuyenDTO;
import GUI.Component.ButtonCustom;
import GUI.Panel.Quyen;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import helper.Validation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class PhanQuyenDialog extends JDialog implements ActionListener {

    private JLabel lblTenQuyen;
    private JTextField txtTenQuyen;
    private JPanel jpTop, jpLeft, jpCen, jpBottom;
    private JCheckBox[][] listCheckBox;
    private ButtonCustom btnAdd, btnCapNhat, btnHuyBo;
    private Quyen jpPhanQuyen;
    private int sizeChucNang, sizePermission;
    private ArrayList<ChucNangDTO> listChucNang;
    String[] listPermission = { "view", "create", "update", "delete" };
    private ArrayList<ChiTietQuyenDTO> listCTQuyen;
    private QuyenDTO quyenDTO;

    public void initComponents(String type) {
        listChucNang = new ArrayList<>(ChucNangBUS.getInstance().getSetChucNang());

        String[] listHanhDong = { "Xem", "Tạo mới", "Cập nhật", "Xoá" };

        this.setSize(new Dimension(600, 580));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));

        // Hiển thị tên nhóm quyền
        jpTop = new JPanel(new BorderLayout(20, 10));
        jpTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        jpTop.setBackground(Color.WHITE);
        lblTenQuyen = new JLabel("Tên nhóm quyền");
        txtTenQuyen = new JTextField();
        txtTenQuyen.setPreferredSize(new Dimension(150, 35));
        jpTop.add(lblTenQuyen, BorderLayout.WEST);
        jpTop.add(txtTenQuyen, BorderLayout.CENTER);

        // Hiển thị danh mục chức năng
        jpLeft = new JPanel(new GridLayout(listChucNang.size() + 1, 1));
        jpLeft.setBackground(Color.WHITE);
        jpLeft.setBorder(new EmptyBorder(0, 20, 0, 14));
        JLabel dmcnl = new JLabel("Danh mục chức năng");
        dmcnl.setFont(new Font(FlatRobotoFont.FAMILY, Font.BOLD, 15));
        jpLeft.add(dmcnl);
        for (ChucNangDTO cn : listChucNang) {
            JLabel lblTenchucnang = new JLabel(cn.getName());
            jpLeft.add(lblTenchucnang);
        }

        // Hiển thị chức năng CRUD        
        sizeChucNang = listChucNang.size();
        sizePermission = listPermission.length;
        jpCen = new JPanel(new GridLayout(sizeChucNang + 1, sizePermission));
        jpCen.setBackground(Color.WHITE);
        listCheckBox = new JCheckBox[sizeChucNang][sizePermission];
        for (int i = 0; i < sizePermission; i++) {
            JLabel lblhanhdong = new JLabel(listHanhDong[i]);
            lblhanhdong.setHorizontalAlignment(SwingConstants.CENTER);
            jpCen.add(lblhanhdong);
        }

        for (int i = 0; i < sizeChucNang; i++) {
            for (int j = 0; j < sizePermission; j++) {
                listCheckBox[i][j] = new JCheckBox();
                listCheckBox[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                jpCen.add(listCheckBox[i][j]);
            }
        }

        // Hiển thị nút thêm
        jpBottom = new JPanel(new FlowLayout());
        jpBottom.setBackground(Color.white);
        jpBottom.setBorder(new EmptyBorder(20, 0, 20, 0));

        switch (type) {
            case "create" -> {
                btnAdd = new ButtonCustom("Thêm mới", "success", 14);
                btnAdd.addActionListener(this);
                jpBottom.add(btnAdd);
            }
            case "update" -> {
                btnCapNhat = new ButtonCustom("Cập nhật", "success", 14);
                btnCapNhat.addActionListener(this);
                jpBottom.add(btnCapNhat);
                initUpdate();
            }
            case "view" -> {
                initUpdate();
                for (int i = 0; i < sizeChucNang; i++) {
                    for (int j = 0; j < sizePermission; j++) {
                        listCheckBox[i][j].setEnabled(false);
                    }
                }
            }
            default -> throw new AssertionError();
        }


        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);
        btnHuyBo.addActionListener(this);

        jpBottom.add(btnHuyBo);

        this.add(jpTop, BorderLayout.NORTH);
        this.add(jpLeft, BorderLayout.WEST);
        this.add(jpCen, BorderLayout.CENTER);
        this.add(jpBottom, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public PhanQuyenDialog(Quyen jpPhanQuyen, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jpPhanQuyen = jpPhanQuyen;
        initComponents(type);
    }

    public PhanQuyenDialog(Quyen jpPhanQuyen, JFrame owner, String title, boolean modal, String type, QuyenDTO quyen) {
        super(owner, title, modal);
        this.jpPhanQuyen = jpPhanQuyen;
        this.quyenDTO = quyen;
        this.listCTQuyen = new ArrayList<>(jpPhanQuyen.quyenBUS.getAllCTQuyen(quyen.getId()));
        initComponents(type);
    }

    public boolean validation(String idQuyen) {
        String name = txtTenQuyen.getText();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên Quyền không được bỏ trống!", "Tên Quyền", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        int errName = Validation.isName(name);
        if (errName != -1) {
            JOptionPane.showMessageDialog(this, Validation.messName[errName], "Tên Quyền", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        this.listCTQuyen = getListChiTietQuyen(idQuyen);
        if (listCTQuyen.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất 1 hành động!", "Chi tiết quyền", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd && validation(jpPhanQuyen.quyenBUS.createID())) {
            String idQuyen = jpPhanQuyen.quyenBUS.createID();
            int addSuccess = jpPhanQuyen.quyenBUS.addQuyen(new QuyenDTO(idQuyen, txtTenQuyen.getText().trim(), false),
                    new LinkedHashSet<>(listCTQuyen));
            if (addSuccess == -1) {
                JOptionPane.showMessageDialog(this, "Thêm Quyền thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                jpPhanQuyen.loadDataTable();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, QuyenBUS.duplicateMess[addSuccess], "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }

        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat && validation(quyenDTO.getId())) {
            String nameQuyen = txtTenQuyen.getText().trim();
            int updateSuccess = jpPhanQuyen.quyenBUS.updateQuyen(new QuyenDTO(quyenDTO.getId(), txtTenQuyen.getText().trim(), false),
                    new LinkedHashSet<>(listCTQuyen),
                    !quyenDTO.getName().equals(nameQuyen));
            if (updateSuccess == -1) {
                JOptionPane.showMessageDialog(this, "Cập nhật Quyền thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                jpPhanQuyen.loadDataTable();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, QuyenBUS.duplicateMess[updateSuccess], "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public ArrayList<ChiTietQuyenDTO> getListChiTietQuyen(String idQuyen) {
        ArrayList<ChiTietQuyenDTO> result = new ArrayList<>();
        for (int i = 0; i < sizeChucNang; i++) {
            for (int j = 0; j < sizePermission; j++) {
                if (listCheckBox[i][j].isSelected()) {
                    result.add(new ChiTietQuyenDTO(idQuyen, listChucNang.get(i).getId(), listPermission[j]));
                }
            }
        }
        return result;
    }

    public void initUpdate() {
        this.txtTenQuyen.setText(quyenDTO.getName());
        System.out.println(listCTQuyen);
        for (ChiTietQuyenDTO k : listCTQuyen) {
            for (int i = 0; i < sizeChucNang; i++) {
                for (int j = 0; j < sizePermission; j++) {
                    if (k.getPermission().equals(listPermission[j]) && k.getIdChucNang().equals(listChucNang.get(i).getId())) {
                        listCheckBox[i][j].setSelected(true);
                    }
                }
            }
        }
    }
}
