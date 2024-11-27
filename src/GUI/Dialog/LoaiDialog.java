package GUI.Dialog;

import BUS.PhanLoaiBUS;
import DTO.PhanLoaiDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.InputForm;
import GUI.Panel.Loai;
import helper.Validation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoaiDialog extends JDialog implements MouseListener {

    Loai jpLoai;
    private JPanel pnlMain, pnlButtom;
    private ButtonCustom btnThem, btnCapNhat, btnHuyBo;
    private InputForm maLoai, tenLoai;
    PhanLoaiDTO loai;

    public LoaiDialog(Loai jpLoai, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jpLoai = jpLoai;
        maLoai = new InputForm("Mã loại");
        setMaLoai(jpLoai.loaiBUS.createID());
        tenLoai = new InputForm("Tên loại");

        initComponents(type);
    }

    public LoaiDialog(Loai jpLoai, JFrame owner, String title, boolean modal, String type, PhanLoaiDTO loai) {
        super(owner, title, modal);
        this.jpLoai = jpLoai;
        this.loai = loai;
        maLoai = new InputForm("Mã loại");
        setMaLoai(loai.getId());
        tenLoai = new InputForm("Tên loại");
        setTenLoai(loai.getName());

        initComponents(type);
    }

    public void initComponents(String type) {
        this.setSize(new Dimension(500, 250));
        this.setLayout(new BorderLayout(0, 0));
        pnlMain = new JPanel(new GridLayout(2, 1, 15, 0));
        pnlMain.setBackground(Color.white);

        pnlMain.add(maLoai);
        pnlMain.add(tenLoai);


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
                maLoai.setDisable();
                pnlButtom.add(btnThem);
            }
            case "update" -> {
                maLoai.setDisable();
                pnlButtom.add(btnCapNhat);
            }
            case "view" -> {
                maLoai.setDisable();
                tenLoai.setDisable();
            }
            default -> throw new AssertionError();
        }
        pnlButtom.add(btnHuyBo);

        this.add(pnlMain, BorderLayout.CENTER);
        this.add(pnlButtom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setTenLoai(String name) {
        tenLoai.setText(name);
    }

    public String getTenLoai() {
        return tenLoai.getText();
    }

    public String getMaLoai() {
        return maLoai.getText();
    }

    public void setMaLoai(String id) {
        this.maLoai.setText(id);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean validation() {
        //Kiểm tra tên
        String ten = this.tenLoai.getText().trim();
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên không được để trống", "Tên loại", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        int errTen = Validation.isName(ten);
        if (errTen != -1) {
            JOptionPane.showMessageDialog(this, Validation.messName[errTen], "Tên loại", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == btnThem && validation()) {
            int addSuccess = jpLoai.loaiBUS.addPhanLoai(new PhanLoaiDTO(maLoai.getText().trim(), tenLoai.getText().trim(), false));
            if (addSuccess == -1) {
                JOptionPane.showMessageDialog(this, "Thêm loại thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                jpLoai.loadDataTable();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, PhanLoaiBUS.duplicateMess[addSuccess], "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }

        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat && validation()) {
            String name = tenLoai.getText().trim();
            int updateSuccess = jpLoai.loaiBUS.updatePhanLoai(new PhanLoaiDTO(loai.getId(), tenLoai.getText().trim(), false),
                    !loai.getName().equals(name));
            if (updateSuccess == -1) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                jpLoai.loadDataTable();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, PhanLoaiBUS.duplicateMess[updateSuccess], "Báo lỗi", JOptionPane.ERROR_MESSAGE);
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
