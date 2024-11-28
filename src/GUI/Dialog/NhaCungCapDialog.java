package GUI.Dialog;

import BUS.NhaCungCapBUS;
import DTO.NhaCungCapDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.InputForm;
import GUI.Component.NumericDocumentFilter;
import GUI.Component.SelectForm;
import GUI.Panel.NhaCungCap;
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

public class NhaCungCapDialog extends JDialog implements MouseListener {

    NhaCungCap jpNCC;
    private JPanel pnlMain, pnlButtom;
    private ButtonCustom btnThem, btnCapNhat, btnHuyBo;
    private InputForm maNCC, tenNCC, sdtNCC, diachiNCC;
    private SelectForm cbCity, cbDistrict, cbWard;
    NhaCungCapDTO ncc;
    private AddressAPI addressAPI;

    public NhaCungCapDialog(NhaCungCap jpNCC, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jpNCC = jpNCC;
        this.addressAPI = new AddressAPI();
        maNCC = new InputForm("Mã nhà cung cấp");
        setMaNCC(jpNCC.nccBUS.createID());
        tenNCC = new InputForm("Tên nhà cung cấp");
        sdtNCC = new InputForm("Số điện thoại");
        PlainDocument phonex = (PlainDocument) sdtNCC.getTxtForm().getDocument();
        phonex.setDocumentFilter((new NumericDocumentFilter()));
        diachiNCC = new InputForm("Số nhà, Tên đường");
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

    public NhaCungCapDialog(NhaCungCap jpNCC, JFrame owner, String title, boolean modal, String type, NhaCungCapDTO ncc) {
        super(owner, title, modal);
        this.ncc = ncc;
        maNCC = new InputForm("Mã nhà cung cấp");
        setMaNCC(ncc.getId());
        tenNCC = new InputForm("Tên nhà cung cấp");
        setTenNCC(ncc.getName());
        sdtNCC = new InputForm("Số điện thoại");
        setSdtNCC(ncc.getPhone());
        diachiNCC = new InputForm("Số nhà, tên đường");
        setDiaChiNCC(ncc.getAddress());
        this.jpNCC = jpNCC;
        addressAPI = new AddressAPI(ncc.getCity(), ncc.getDistrict(), ncc.getWard());
        cbCity = new SelectForm("Tỉnh/Thành phố", addressAPI.getAllCity());
        cbCity.setSelectedItem(ncc.getCity());
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
        cbDistrict.setSelectedItem(ncc.getDistrict());
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
        cbWard.setSelectedItem(ncc.getWard());
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

        pnlMain.add(maNCC);
        pnlMain.add(tenNCC);
        pnlMain.add(sdtNCC);
        pnlMain.add(diachiNCC);
        pnlMain.add(cbCity);
        pnlMain.add(cbDistrict);
        pnlMain.add(cbWard);


        pnlButtom = new JPanel(new FlowLayout());
        pnlButtom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnlButtom.setBackground(Color.white);
        btnThem = new ButtonCustom("Thêm nhà cung cấp", "success", 14);
        btnCapNhat = new ButtonCustom("Lưu thông tin", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);

        //Add MouseListener btn
        btnThem.addMouseListener(this);
        btnCapNhat.addMouseListener(this);
        btnHuyBo.addMouseListener(this);

        switch (type) {
            case "create" -> {
                maNCC.setDisable();
                pnlButtom.add(btnThem);
            }
            case "update" -> {
                maNCC.setDisable();
                pnlButtom.add(btnCapNhat);
            }
            case "view" -> {
                maNCC.setDisable();
                tenNCC.setDisable();
                sdtNCC.setDisable();
                diachiNCC.setDisable();
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

    public void setTenNCC(String name) {
        tenNCC.setText(name);
    }

    public String getTenNCC() {
        return tenNCC.getText();
    }

    public String getMaNCC() {
        return maNCC.getText();
    }

    public void setMaNCC(String id) {
        this.maNCC.setText(id);
    }

    public String getSdtNCC() {
        return sdtNCC.getText();
    }

    public void setSdtNCC(String id) {
        this.sdtNCC.setText(id);
    }

    public String getDiaChiNCC() {
        return diachiNCC.getText();
    }

    public void setDiaChiNCC(String id) {
        this.diachiNCC.setText(id);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean validation() {
        //Kiểm tra tên
        String ten = this.tenNCC.getText().trim();
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên không được để trống", "Tên nhà cung cấp", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        int errTen = Validation.isName(ten);
        if (errTen != -1) {
            JOptionPane.showMessageDialog(this, Validation.messName[errTen], "Tên nhà cung cấp", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //Kiểm tra số điện thoại
        String phone = this.sdtNCC.getText().trim();
        if (phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống", "Số điện thoại", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!Validation.isNumberPhone(phone)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ", "Số điện thoại", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //Kiểm tra số nhà, tên đường
        String address = this.diachiNCC.getText().trim();
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
            int addSuccess = jpNCC.nccBUS.addNhaCungCap(new NhaCungCapDTO(maNCC.getText().trim(), tenNCC.getText().trim(), sdtNCC.getText().trim(),
                    diachiNCC.getText().trim(), cbWard.getValue(), cbDistrict.getValue(), cbCity.getValue(), false));
            if (addSuccess == -1) {
                JOptionPane.showMessageDialog(this, "Thêm nhà cung cấp thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                jpNCC.loadDataTable();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, NhaCungCapBUS.duplicateMess[addSuccess], "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }

        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat && validation()) {
            String sdt = sdtNCC.getText().trim();
            int updateSuccess = jpNCC.nccBUS.updateNhaCungCap(new NhaCungCapDTO(ncc.getId(), tenNCC.getText().trim(), sdtNCC.getText().trim(),
                            diachiNCC.getText().trim(), cbWard.getValue(), cbDistrict.getValue(), cbCity.getValue(), false),
                    !ncc.getPhone().equals(sdt));
            if (updateSuccess == -1) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                jpNCC.loadDataTable();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, NhaCungCapBUS.duplicateMess[updateSuccess], "Báo lỗi", JOptionPane.ERROR_MESSAGE);
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
