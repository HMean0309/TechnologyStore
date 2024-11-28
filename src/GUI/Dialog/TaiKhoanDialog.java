package GUI.Dialog;

import BUS.QuyenBUS;
import BUS.TaiKhoanBUS;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.InputForm;
import GUI.Component.SelectForm;
import GUI.Panel.TaiKhoan;
import helper.Validation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

public class TaiKhoanDialog extends JDialog implements MouseListener {
    TaiKhoan jpTK;
    private JPanel pnlMain, pnlButtom;
    private ButtonCustom btnThem, btnCapNhat, btnHuyBo;
    private InputForm tfUsername, tfPassword, tfNhanVien, tfEmailNV;
    private SelectForm cbQuyen, cbTrangthai;
    TaiKhoanDTO tk;
    public HashMap<String, String> mapQuyen = QuyenBUS.getInstance().toMap();


    public TaiKhoanDialog(TaiKhoan jpTK, JFrame owner, String title, boolean modal, String type, NhanVienDTO nv) {
        super(owner, title, modal);
        this.jpTK = jpTK;
        tfUsername = new InputForm("Tên đăng nhập");
        tfPassword = new InputForm("Mật khẩu", "password");
        tfNhanVien = new InputForm("ID Nhân viên");
        tfNhanVien.setText(nv.getId());
        tfEmailNV = new InputForm("Email Nhân viên");
        tfEmailNV.setText(nv.getEmail());

        ArrayList<String> dataQuyen = new ArrayList<>(mapQuyen.keySet());
        dataQuyen.add(0, "Chọn Quyền cho Tài khoản");
        cbQuyen = new SelectForm("Quyền", dataQuyen.toArray(new String[0]));

        cbTrangthai = new SelectForm("Trạng thái", new String[]{ "Đang hoạt động", "Bị khóa" });
        cbTrangthai.setSelectedIndex(0);
        initComponents(type);
    }

    public TaiKhoanDialog(TaiKhoan jpTK, JFrame owner, String title, boolean modal, String type, TaiKhoanDTO tk) {
        super(owner, title, modal);
        this.tk = tk;
        this.jpTK = jpTK;
        tfUsername = new InputForm("Tên đăng nhập");
        tfUsername.setText(tk.getUsername());
        tfPassword = new InputForm("Mật khẩu", "password");
        tfNhanVien = new InputForm("ID Nhân viên");
        tfNhanVien.setText(tk.getIdNV());
        tfEmailNV = new InputForm("Email Nhân viên");
        tfEmailNV.setText(tk.getEmail());

        ArrayList<String> dataQuyen = new ArrayList<>(mapQuyen.keySet());
        dataQuyen.add(0, "Chọn Quyền cho Tài khoản");
        cbQuyen = new SelectForm("Quyền", dataQuyen.toArray(new String[0]));
        cbQuyen.setSelectedItem((String) tk.getNameQuyen());

        cbTrangthai = new SelectForm("Trạng thái", new String[]{ "Đang hoạt động", "Bị khóa" });
        if (tk.getDelete()) {
            cbTrangthai.setSelectedItem("Bị khóa");
        } else {
            cbTrangthai.setSelectedItem("Đang hoạt động");
        }
        cbTrangthai.setSelectedIndex(0);
        initComponents(type);
    }

    public void initComponents(String type) {
        this.setSize(new Dimension(500, 540));
        this.setLayout(new BorderLayout(0, 0));
        pnlMain = new JPanel(new GridLayout(5, 1, 15, 0));
        pnlMain.setBackground(Color.white);

        pnlMain.add(tfUsername);
        pnlMain.add(tfPassword);
        pnlMain.add(tfNhanVien);
        pnlMain.add(cbQuyen);
        pnlMain.add(cbTrangthai);

        pnlButtom = new JPanel(new FlowLayout());
        pnlButtom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnlButtom.setBackground(Color.white);
        btnThem = new ButtonCustom("Thêm mới", "success", 14);
        btnCapNhat = new ButtonCustom("Lưu thông tin", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);

        //Add MouseListener btn
        btnThem.addMouseListener(this);
        btnCapNhat.addMouseListener(this);
        btnHuyBo.addMouseListener(this);

        switch (type) {
            case "create" -> {
                tfNhanVien.setDisable();
                tfEmailNV.setDisable();
                cbTrangthai.setDisable();
                pnlButtom.add(btnThem);
            }
            case "update" -> {
                tfUsername.setDisable();
                tfNhanVien.setDisable();
                tfEmailNV.setDisable();
                pnlMain.remove(tfPassword);
                pnlMain.revalidate();
                pnlMain.repaint();
                pnlButtom.add(btnCapNhat);
            }
            case "view" -> {
                tfUsername.setDisable();
                tfNhanVien.setDisable();
                tfEmailNV.setDisable();
                cbTrangthai.setDisable();
                cbQuyen.setDisable();
                pnlMain.remove(tfPassword);
                pnlMain.revalidate();
                pnlMain.repaint();
            }
            default -> throw new AssertionError();
        }
        pnlButtom.add(btnHuyBo);

        this.add(pnlMain, BorderLayout.CENTER);
        this.add(pnlButtom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean validation() {
        //Kiểm tra Username
        String username = tfUsername.getText().trim();
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập không để trống", "Tên đăng nhập", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (username.length() > 30) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập không quá 30 kí tự", "Tên đăng nhập", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (Validation.isComponentInPanel(pnlMain, tfPassword)) {
            //Kiểm tra mật khẩu
            String pass = tfPassword.getPass();
            if (pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mật khẩu không để trống", "Mật khẩu", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            int errPass = Validation.isPassword(pass);
            if (errPass != -1) {
                JOptionPane.showMessageDialog(this, Validation.messPassword[errPass], "Mật khẩu", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }

        //Kiểm tra Quyền
        if (cbQuyen.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn Quyền cho Tài khoản!", "Quyền", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        String nameQuyen = (String) cbQuyen.getSelectedItem();
        String idQuyen = mapQuyen.get(nameQuyen);
        boolean isDelete = false;
        if (cbTrangthai.getSelectedIndex() == 1) isDelete = true;

        if (e.getSource() == btnThem && validation()) {
            int addSuccess = jpTK.taikhoanBUS.addTaiKhoan(new TaiKhoanDTO(tfUsername.getText().trim(), tfPassword.getPass(), tfNhanVien.getText(),
                    idQuyen, tfEmailNV.getText(), nameQuyen, isDelete));
            if (addSuccess == -1) {
                JOptionPane.showMessageDialog(this, "Thêm tài khoản thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                jpTK.loadDataTable();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, TaiKhoanBUS.duplicateMess[addSuccess], "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }

        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat && validation()) {
            String username = tfUsername.getText().trim();
            int updateSuccess = jpTK.taikhoanBUS.updateTaiKhoan(new TaiKhoanDTO(tfUsername.getText().trim(), tk.getPassword(), tk.getIdNV(),
                            idQuyen, tk.getEmail(), nameQuyen, isDelete),
                    !tk.getUsername().equals(username));
            if (updateSuccess == -1) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                jpTK.loadDataTable();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, TaiKhoanBUS.duplicateMess[updateSuccess], "Báo lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
