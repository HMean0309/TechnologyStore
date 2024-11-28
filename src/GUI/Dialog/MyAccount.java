package GUI.Dialog;

import BUS.NhanVienBUS;
import BUS.TaiKhoanBUS;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import GUI.Component.*;
import helper.Validation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyAccount extends JDialog implements ActionListener {

    CardLayout card;
    ButtonCustom save, cancel;
    HeaderTitle title;
    JPanel top, center, top_center, main_center, bottom;
    InputForm current_pwd, phone, email, new_pwd, confirm;
    NhanVienDTO nv;
    TaiKhoanBUS tkbus;
    NhanVienBUS nvbus;
    MenuTaskbar menuTaskbar;
    JRadioButton[] jbr;
    JPanel[] panel;

    public MyAccount(JFrame owner, MenuTaskbar menutaskbar, String title, boolean modal) {
        super(owner, title, modal);
        tkbus = new TaiKhoanBUS();
        nvbus = new NhanVienBUS();
        initComponent(menutaskbar);
        this.setLocationRelativeTo(null);
    }

    public void initComponent(MenuTaskbar menutaskbar) {

        this.menuTaskbar = menutaskbar;
        this.setSize(400, 300);
        this.setLayout(new BorderLayout(0, 0));
        this.setBackground(Color.WHITE);
        this.setResizable(false);
        nv = menuTaskbar.nhanVienDTO;
        top = new JPanel();
        top.setBackground(Color.WHITE);
        top.setLayout(new FlowLayout(0, 0, 0));
        title = new HeaderTitle("CHỈNH SỬA THÔNG TIN");
        top.add(title);
        this.add(top, BorderLayout.NORTH);

        top_center = new JPanel(new FlowLayout(1, 40, 0));
        top_center.setBorder(new EmptyBorder(20, 0, 0, 0));
        top_center.setBackground(Color.WHITE);
        main_center = new JPanel();
        main_center.setBorder(new EmptyBorder(0, 20, 0, 20));
        main_center.setBackground(Color.WHITE);

        ButtonGroup bg = new ButtonGroup();
        String opt[] = { "Số điện thoại", "Email", "Mật khẩu" };
        jbr = new JRadioButton[3];
        for (int i = 0; i < jbr.length; i++) {
            jbr[i] = new JRadioButton();
            jbr[i].addActionListener(this);
            jbr[i].setText(opt[i]);
            top_center.add(jbr[i]);
            bg.add(jbr[i]);
        }
        jbr[0].setSelected(true);

        center = new JPanel();
        center.setLayout(new BorderLayout());
        center.add(top_center, BorderLayout.NORTH);
        center.add(main_center, BorderLayout.CENTER);

        panel = new JPanel[3];
        panel[0] = new JPanel(new GridLayout(1, 1));
        panel[0].setPreferredSize(new Dimension(400, 100));
        phone = new InputForm("Số điện thoại");
        PlainDocument phonex = (PlainDocument) phone.getTxtForm().getDocument();
        phonex.setDocumentFilter((new NumericDocumentFilter()));
        phone.setText(nv.getPhone());
        panel[0].add(phone);

        panel[1] = new JPanel(new GridLayout(1, 1));
        panel[1].setPreferredSize(new Dimension(400, 100));
        email = new InputForm("Email");
        email.setText(nv.getEmail());
        panel[1].add(email);
        main_center.add(panel[0]);

        panel[2] = new JPanel(new GridLayout(3, 1));
        panel[2].setPreferredSize(new Dimension(400, 300));
        current_pwd = new InputForm("Mật khẩu hiện tại", "password");
        new_pwd = new InputForm("Mật khẩu mới", "password");
        confirm = new InputForm("Nhập lại mật khẩu mới", "password");
        panel[2].add(current_pwd);
        panel[2].add(new_pwd);
        panel[2].add(confirm);

        this.add(center, BorderLayout.CENTER);

        bottom = new JPanel(new FlowLayout(1, 20, 10));
        bottom.setBackground(Color.WHITE);

        cancel = new ButtonCustom("Hủy", "danger", 15);
        cancel.addActionListener(this);
        bottom.add(cancel);
        save = new ButtonCustom("Lưu", "success", 15);
        save.addActionListener(this);
        bottom.add(save);
        this.add(bottom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void resetChange() {
        menuTaskbar.resetChange();
        nv = menuTaskbar.nhanVienDTO;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
            this.dispose();
        }

        for (int i = 0; i < 3; i++) {
            if (e.getSource() == jbr[i]) {
                if (i == 2) {
                    this.setSize(new Dimension(400, 500));
                    this.setLocationRelativeTo(null);
                } else {
                    this.setSize(400, 300);
                }

                main_center.removeAll();
                main_center.add(panel[i]);
                main_center.repaint();
                main_center.validate();
            }
        }


        if (jbr[0].isSelected() && e.getSource() == save) {
            String sdtNew = phone.getText();
            if (Validation.isEmpty(sdtNew)) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không được bỏ trống", "Chỉnh sửa số điện thoại", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!Validation.isNumberPhone(sdtNew)) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ", "Chỉnh sửa số điện thoại", JOptionPane.WARNING_MESSAGE);
                return;
            }

            NhanVienDTO nvNew = new NhanVienDTO(nv.getId(), nv.getName(), sdtNew.trim(),
                    nv.getEmail(), nv.getBirth(), nv.getGender(), false);
            int updateSuccess = nvbus.updateNhanVien(nvNew, !nv.getPhone().equals(sdtNew.trim()), false);
            if (updateSuccess == -1) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            } else {
                JOptionPane.showMessageDialog(this, NhanVienBUS.duplicateMess[updateSuccess], "Báo lỗi", JOptionPane.WARNING_MESSAGE);
            }

        }

        if (jbr[1].isSelected() && e.getSource() == save) {
            String emailNew = email.getText();
            if (Validation.isEmpty(emailNew)) {
                JOptionPane.showMessageDialog(this, "Email không được để trống", "Chỉnh sửa email", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!Validation.isEmail(emailNew)) {
                JOptionPane.showMessageDialog(this, "Email không hợp lệ", "Chỉnh sửa email", JOptionPane.WARNING_MESSAGE);
                return;
            }

            NhanVienDTO nvNew = new NhanVienDTO(nv.getId(), nv.getName(), nv.getPhone(),
                    emailNew.trim(), nv.getBirth(), nv.getGender(), false);
            int updateSuccess = nvbus.updateNhanVien(nvNew, false, !nv.getEmail().equals(emailNew.trim()));
            if (updateSuccess == -1) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            } else {
                JOptionPane.showMessageDialog(this, NhanVienBUS.duplicateMess[updateSuccess], "Báo lỗi", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (jbr[2].isSelected() && e.getSource() == save) {
            TaiKhoanDTO tk = tkbus.getTaiKhoanByEmail(nv.getEmail());

            //Kiểm tra current_pwd
            String currentPass = current_pwd.getPass();

            if (Validation.isEmpty(currentPass)) {
                JOptionPane.showMessageDialog(this, "Mật khẩu hiện tại không được để trống", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!tk.getPassword().equals(currentPass)) {
                JOptionPane.showMessageDialog(this, "Mật khẩu hiện tại không đúng", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                return;
            }

            //Kiểm tra new_pwd
            String newPass = new_pwd.getPass();
            if (Validation.isEmpty(newPass)) {
                JOptionPane.showMessageDialog(this, "Mật khẩu mới không được để trống", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int errNewPass = Validation.isPassword(newPass);
            if (errNewPass != -1) {
                JOptionPane.showMessageDialog(this, Validation.messPassword[errNewPass], "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                return;
            }

            //Kiểm tra confirm
            String confirmPass = confirm.getPass();
            if (Validation.isEmpty(confirmPass)) {
                JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không được để trống", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!confirmPass.equals(newPass)) {
                JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không trùng khớp", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                return;
            }


            tk.setPassword(newPass);
            tkbus.updateTaiKhoan(tk, false);
            JOptionPane.showMessageDialog(this, "Cập nhật thành công");
            current_pwd.setPass("");
            new_pwd.setPass("");
            confirm.setPass("");
        }

        resetChange();
    }
}
