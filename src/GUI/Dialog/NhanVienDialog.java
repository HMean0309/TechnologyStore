package GUI.Dialog;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.InputDate;
import GUI.Component.InputForm;
import GUI.Component.NumericDocumentFilter;
import GUI.Panel.NhanVien;
import helper.Validation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.Enumeration;

public class NhanVienDialog extends JDialog implements MouseListener {

    private NhanVien jpNV;
    private JPanel main, bottom;
    private ButtonCustom btnAdd, btnEdit, btnExit;
    private InputForm maNV;
    private InputForm tenNV;
    private InputForm sdtNV;
    private InputForm emailNV;
    private ButtonGroup btnGrGender;
    private JRadioButton male;
    private JRadioButton female;
    private InputDate birthNV;
    private NhanVienDTO nhanVien;

    public NhanVienDialog(NhanVien jpNV, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jpNV = jpNV;
        init(title, type);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public NhanVienDialog(NhanVien jpNV, JFrame owner, String title, boolean modal, String type, NhanVienDTO nhanVien) {
        super(owner, title, modal);
        this.jpNV = jpNV;
        this.nhanVien = nhanVien;
        init(title, type);
        maNV.setText(nhanVien.getId());
        tenNV.setText(nhanVien.getName());
        sdtNV.setText(nhanVien.getPhone());
        emailNV.setText(nhanVien.getEmail());
        if (nhanVien.getGender()) {
            male.setSelected(true);
        } else {
            female.setSelected(true);
        }
        birthNV.setDate(nhanVien.getBirth());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void init(String title, String type) {
        this.setSize(new Dimension(450, 550));
        this.setLayout(new BorderLayout(0, 0));

        //main chứa các input
        main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(Color.white);

        maNV = new InputForm("Mã nhân viên");
        maNV.setText(jpNV.nhanvienBUS.createID());
        tenNV = new InputForm("Tên nhân viên");

        sdtNV = new InputForm("Số điện thoại");
        PlainDocument phonex = (PlainDocument) sdtNV.getTxtForm().getDocument();
        phonex.setDocumentFilter((new NumericDocumentFilter()));

        emailNV = new InputForm("Email");

        //Giới tính
        male = new JRadioButton("Nam");
        female = new JRadioButton("Nữ");
        male.setSelected(true);
        btnGrGender = new ButtonGroup();
        btnGrGender.add(male);
        btnGrGender.add(female);
        JPanel jpanelG = new JPanel(new GridLayout(2, 1, 0, 2));
        jpanelG.setBackground(Color.white);
        jpanelG.setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel jgender = new JPanel(new GridLayout(1, 2));
        //jgender.setSize(new Dimension(500, 80));
        jgender.setBackground(Color.white);
        jgender.add(male);
        jgender.add(female);
        JLabel labelGender = new JLabel("Giới tính");
        jpanelG.add(labelGender);
        jpanelG.add(jgender);

        //Ngày sinh
        birthNV = new InputDate("Ngày sinh");

        main.add(maNV);
        main.add(tenNV);
        main.add(sdtNV);
        main.add(emailNV);
        main.add(jpanelG);
        main.add(birthNV);

        this.add(main, BorderLayout.CENTER);

        //bottom chứa các btn
        bottom = new JPanel(new FlowLayout());
        bottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        bottom.setBackground(Color.white);
        btnAdd = new ButtonCustom("Thêm nhân viên", "success", 14);
        btnEdit = new ButtonCustom("Lưu thông tin", "success", 14);
        btnExit = new ButtonCustom("Hủy bỏ", "danger", 14);

        //Add MouseListener btn
        btnAdd.addMouseListener(this);
        btnEdit.addMouseListener(this);
        btnExit.addMouseListener(this);

        switch (type) {
            case "create" -> {
                maNV.setDisable();
                bottom.add(btnAdd);
            }
            case "update" -> {
                maNV.setDisable();
                bottom.add(btnEdit);
            }
            case "view" -> {
                maNV.setDisable();
                tenNV.setDisable();
                sdtNV.setDisable();
                emailNV.setDisable();
                Enumeration<AbstractButton> enumeration = btnGrGender.getElements();
                while (enumeration.hasMoreElements()) {
                    enumeration.nextElement().setEnabled(false);
                }
                birthNV.setDisable();
            }
            default -> throw new AssertionError();
        }
        bottom.add(btnExit);
        this.add(bottom, BorderLayout.SOUTH);

    }

    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean validation() {
        //Kiểm tra tên
        String ten = this.tenNV.getText().trim();
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên không được để trống", "Tên nhân viên", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        int errTen = Validation.isName(ten);
        if (errTen != -1) {
            JOptionPane.showMessageDialog(this, Validation.messName[errTen], "Tên nhân viên", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //Kiểm tra số điện thoại
        String phone = this.sdtNV.getText().trim();
        if (phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống", "Số điện thoại", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!Validation.isNumberPhone(phone)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ", "Số điện thoại", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //Kiểm tra email
        String email = this.emailNV.getText().trim();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email không được để trống", "Email", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!Validation.isEmail(email)) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ", "Email", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //Kiểm tra ngày sinh
        LocalDate birth = birthNV.getLocalDate();
        if (birth == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày sinh", "Ngày sinh", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!Validation.isBirth(birth)) {
            JOptionPane.showMessageDialog(this, "Chưa đủ 18 tuổi", "Ngày sinh", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == btnAdd && validation()) {
            boolean gender = true;
            if (female.isSelected()) {
                gender = false;
            }
            if (male.isSelected()) {
                gender = true;
            }
            int addSuccess = jpNV.nhanvienBUS.addNhanVien(new NhanVienDTO(maNV.getText().trim(), tenNV.getText().trim(), sdtNV.getText().trim(),
                    emailNV.getText().trim(), birthNV.getLocalDate(), gender, false));
            if (addSuccess == -1) {
                JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                jpNV.loadDataTable();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, NhanVienBUS.duplicateMess[addSuccess], "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }

        } else if (e.getSource() == btnExit) {
            dispose();
        } else if (e.getSource() == btnEdit && validation()) {
            boolean gender = true;
            if (female.isSelected()) {
                gender = false;
            }
            if (male.isSelected()) {
                gender = true;
            }
            String sdt = sdtNV.getText().trim();
            String email = emailNV.getText().trim();
            int updateSuccess = jpNV.nhanvienBUS.updateNhanVien(new NhanVienDTO(nhanVien.getId(), tenNV.getText().trim(), sdtNV.getText().trim(),
                            emailNV.getText().trim(), birthNV.getLocalDate(), gender, false),
                    !nhanVien.getPhone().equals(sdt),
                    !nhanVien.getEmail().equals(email));
            if (updateSuccess == -1) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                jpNV.loadDataTable();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, NhanVienBUS.duplicateMess[updateSuccess], "Báo lỗi", JOptionPane.ERROR_MESSAGE);
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
