package GUI.Dialog;

import BUS.KhachHangBUS;
import DTO.KhachHangDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.InputForm;
import GUI.Component.NumericDocumentFilter;
import GUI.Component.SelectForm;
import GUI.Panel.KhachHang;
import helper.AddressAPI;
import helper.Validation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class KhachHangDialog extends JDialog implements MouseListener {

    KhachHang jpKH;
    private JPanel pnlMain, pnlButtom;
    private ButtonCustom btnThem, btnCapNhat, btnHuyBo;
    private InputForm maKH, tenKH, sdtKH, diachiKH;
    private SelectForm cbCity, cbDistrict, cbWard;
    KhachHangDTO kh;
    private AddressAPI addressAPI;
    KhachHangBUS khachhangBUS;

    public KhachHangDialog(KhachHang jpKH, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jpKH = jpKH;
        this.khachhangBUS = jpKH.khachhangBUS;
        this.addressAPI = new AddressAPI();
        maKH = new InputForm("Mã khách hàng");
        setMaKH(jpKH.khachhangBUS.createID());
        tenKH = new InputForm("Tên khách hàng");
        sdtKH = new InputForm("Số điện thoại");
        PlainDocument phonex = (PlainDocument) sdtKH.getTxtForm().getDocument();
        phonex.setDocumentFilter((new NumericDocumentFilter()));
        diachiKH = new InputForm("Số nhà, Tên đường");
        cbCity = new SelectForm("Tỉnh/Thành phố", addressAPI.getAllCity());
        cbCity.getCbb().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (!cbCity.getValue().equals("Chọn Tỉnh/Thành phố")) {
                    addressAPI.setCodeCity(cbCity.getValue());
                    cbDistrict.setArr(addressAPI.getAllDistrict());
                    cbDistrict.setEnable(true);
                }
            }
        });
        cbDistrict = new SelectForm("Quận/Huyện", new String[]{ "Vui lòng chọn Tỉnh/Thành phố" });
        cbDistrict.setEnable(false);
        cbDistrict.getCbb().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (!cbDistrict.getValue().equals("Chọn Quận/Huyện")) {
                    addressAPI.setCodeDistrict(cbDistrict.getValue());
                    cbWard.setArr(addressAPI.getAllWard());
                    cbWard.setEnable(true);
                }
            }
        });
        cbWard = new SelectForm("Phường/Xã", new String[]{ "Vui lòng chọn Quận/Huyện" });
        cbWard.setEnable(false);
        cbWard.getCbb().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (!cbWard.getValue().equals("Chọn Phường/Xã")) {
                    addressAPI.setCodeWard(cbWard.getValue());
                }
            }
        });
        initComponents(type);
    }

    public KhachHangDialog(KhachHangBUS bus, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.khachhangBUS = bus;
        this.addressAPI = new AddressAPI();
        maKH = new InputForm("Mã khách hàng");
        setMaKH(khachhangBUS.createID());
        tenKH = new InputForm("Tên khách hàng");
        sdtKH = new InputForm("Số điện thoại");
        PlainDocument phonex = (PlainDocument) sdtKH.getTxtForm().getDocument();
        phonex.setDocumentFilter((new NumericDocumentFilter()));
        diachiKH = new InputForm("Số nhà, Tên đường");
        cbCity = new SelectForm("Tỉnh/Thành phố", addressAPI.getAllCity());
        cbCity.getCbb().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (!cbCity.getValue().equals("Chọn Tỉnh/Thành phố")) {
                    addressAPI.setCodeCity(cbCity.getValue());
                    cbDistrict.setArr(addressAPI.getAllDistrict());
                    cbDistrict.setEnable(true);
                }
            }
        });
        cbDistrict = new SelectForm("Quận/Huyện", new String[]{ "Vui lòng chọn Tỉnh/Thành phố" });
        cbDistrict.setEnable(false);
        cbDistrict.getCbb().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (!cbDistrict.getValue().equals("Chọn Quận/Huyện")) {
                    addressAPI.setCodeDistrict(cbDistrict.getValue());
                    cbWard.setArr(addressAPI.getAllWard());
                    cbWard.setEnable(true);
                }
            }
        });
        cbWard = new SelectForm("Phường/Xã", new String[]{ "Vui lòng chọn Quận/Huyện" });
        cbWard.setEnable(false);
        cbWard.getCbb().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (!cbWard.getValue().equals("Chọn Phường/Xã")) {
                    addressAPI.setCodeWard(cbWard.getValue());
                }
            }
        });
        initComponents(type);
    }

    public KhachHangDialog(KhachHang jpKH, JFrame owner, String title, boolean modal, String type, KhachHangDTO kh) {
        super(owner, title, modal);
        this.kh = kh;
        maKH = new InputForm("Mã khách hàng");
        setMaKH(kh.getId());
        tenKH = new InputForm("Tên khách hàng");
        setTenKH(kh.getName());
        sdtKH = new InputForm("Số điện thoại");
        setSdtKH(kh.getPhone());
        diachiKH = new InputForm("Số nhà, tên đường");
        setDiaChiKH(kh.getAddress());
        this.jpKH = jpKH;
        this.khachhangBUS = jpKH.khachhangBUS;
        addressAPI = new AddressAPI(kh.getCity(), kh.getDistrict(), kh.getWard());
        cbCity = new SelectForm("Tỉnh/Thành phố", addressAPI.getAllCity());
        cbCity.setSelectedItem(kh.getCity());
        cbCity.getCbb().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (!cbCity.getValue().equals("Chọn Tỉnh/Thành phố")) {
                    addressAPI.setCodeCity(cbCity.getValue());
                    cbDistrict.setArr(addressAPI.getAllDistrict());
                    addressAPI.setCodeWard("");
                    cbWard.setArr(new String[]{ "Vui lòng chọn Quận/Huyện" });
                    cbWard.setDisable();

                }
            }
        });
        cbDistrict = new SelectForm("Quận/Huyện", addressAPI.getAllDistrict());
        cbDistrict.setSelectedItem(kh.getDistrict());
        cbDistrict.getCbb().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (!cbDistrict.getValue().equals("Chọn Quận/Huyện")) {
                    addressAPI.setCodeDistrict(cbDistrict.getValue());
                    cbWard.setArr(addressAPI.getAllWard());
                    cbWard.setEnable(true);
                }
            }
        });
        cbWard = new SelectForm("Phường/Xã", addressAPI.getAllWard());
        cbWard.setSelectedItem(kh.getWard());
        cbWard.getCbb().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (!cbWard.getValue().equals("Chọn Phường/Xã")) {
                    addressAPI.setCodeWard(cbWard.getValue());
                }
            }
        });
        initComponents(type);
    }

    public void initComponents(String type) {
        this.setSize(new Dimension(500, 620));
        this.setLayout(new BorderLayout(0, 0));
        pnlMain = new JPanel(new GridLayout(7, 1, 15, 0));
        pnlMain.setBackground(Color.white);

        pnlMain.add(maKH);
        pnlMain.add(tenKH);
        pnlMain.add(sdtKH);
        pnlMain.add(diachiKH);
        pnlMain.add(cbCity);
        pnlMain.add(cbDistrict);
        pnlMain.add(cbWard);


        pnlButtom = new JPanel(new FlowLayout());
        pnlButtom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnlButtom.setBackground(Color.white);
        btnThem = new ButtonCustom("Thêm khách hàng", "success", 14);
        btnCapNhat = new ButtonCustom("Lưu thông tin", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);

        //Add MouseListener btn
        btnThem.addMouseListener(this);
        btnCapNhat.addMouseListener(this);
        btnHuyBo.addMouseListener(this);

        switch (type) {
            case "create" -> {
                maKH.setDisable();
                pnlButtom.add(btnThem);
            }
            case "update" -> {
                maKH.setDisable();
                pnlButtom.add(btnCapNhat);
            }
            case "view" -> {
                maKH.setDisable();
                tenKH.setDisable();
                sdtKH.setDisable();
                diachiKH.setDisable();
                cbCity.setDisable();
                cbDistrict.setDisable();
                cbWard.setDisable();
            }
            default -> throw new AssertionError();
        }
        pnlButtom.add(btnHuyBo);

        this.add(pnlMain, BorderLayout.CENTER);
        this.add(pnlButtom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setTenKH(String name) {
        tenKH.setText(name);
    }

    public String getTenKH() {
        return tenKH.getText();
    }

    public String getMaKH() {
        return maKH.getText();
    }

    public void setMaKH(String id) {
        this.maKH.setText(id);
    }

    public String getSdtKH() {
        return sdtKH.getText();
    }

    public void setSdtKH(String id) {
        this.sdtKH.setText(id);
    }

    public String getDiaChiKH() {
        return diachiKH.getText();
    }

    public void setDiaChiKH(String id) {
        this.diachiKH.setText(id);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean validation() {
        //Kiểm tra tên
        String ten = this.tenKH.getText().trim();
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên không được để trống", "Tên khách hàng", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        int errTen = Validation.isName(ten);
        if (errTen != -1) {
            JOptionPane.showMessageDialog(this, Validation.messName[errTen], "Tên khách hàng", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //Kiểm tra số điện thoại
        String phone = this.sdtKH.getText().trim();
        if (phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống", "Số điện thoại", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!Validation.isNumberPhone(phone)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ", "Số điện thoại", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //Kiểm tra số nhà, tên đường
        String address = this.diachiKH.getText().trim();
        if (address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số nhà, tên đường không được để trống", "Số nhà, Tên đường", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //Kiểm tra Tỉnh/Thành
        if (cbCity.cbb.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn Tỉnh/Thành", "Tỉnh/Thành", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        //Kiểm tra Quận/Huyện
        if (cbDistrict.cbb.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn Quận/Huyện", "Quận/Huyện", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        //Kiểm tra Phường/Xã
        if (cbWard.cbb.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn Phường/Xã", "Phường/Xã", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == btnThem && validation()) {
            int addSuccess = khachhangBUS.addKhachHang(new KhachHangDTO(maKH.getText().trim(), tenKH.getText().trim(), sdtKH.getText().trim(),
                    diachiKH.getText().trim(), cbDistrict.getValue(), cbWard.getValue(), cbCity.getValue(), false));
            if (addSuccess == -1) {
                JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                if (jpKH != null) jpKH.loadDataTable();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, KhachHangBUS.duplicateMess[addSuccess], "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }

        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat && validation()) {
            String sdt = sdtKH.getText().trim();
            int updateSuccess = khachhangBUS.updateKhachHang(new KhachHangDTO(kh.getId(), tenKH.getText().trim(), sdtKH.getText().trim(),
                            diachiKH.getText().trim(), cbDistrict.getValue(), cbWard.getValue(), cbCity.getValue(), false),
                    !kh.getPhone().equals(sdt));
            if (updateSuccess == -1) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                if (jpKH != null) jpKH.loadDataTable();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, KhachHangBUS.duplicateMess[updateSuccess], "Báo lỗi", JOptionPane.ERROR_MESSAGE);
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
