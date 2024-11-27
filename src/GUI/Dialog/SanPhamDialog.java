package GUI.Dialog;

import BUS.OptionSanPhamBUS;
import BUS.PhanLoaiBUS;
import BUS.SanPhamBUS;
import DTO.OptionSanPhamDTO;
import DTO.SanPhamDTO;
import GUI.Component.*;
import GUI.Panel.SanPham;
import helper.Validation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public final class SanPhamDialog extends JDialog implements ActionListener {
    private JPanel pnInfoSanPham, pnBottom, pnCenter, pnInfoSanPhamRight, pnMain, pncard2;
    private ButtonCustom btnXemOption, btnHuyBo, btnAddOption, btnDeleteOption, btnAddSanPham, btnAdd, btnUpdate, btnUpdateSanPham, btnCancelOption, btnBack;
    InputForm maSP, tenSP, baoHanhSP;
    InputForm tfMau;
    SelectForm cbCate;
    HashMap<String, String> mapCate = PhanLoaiBUS.getInstance().toMap();
    InputImage inputHinhAnh;
    JTable tableOptionMau;
    JScrollPane scrollTableOptionMau;
    DefaultTableModel tblModel;
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    SanPham jpSP;
    SanPhamDTO sp;
    ArrayList<OptionSanPhamDTO> listOption;
    String type;

    public void init(SanPham jpSP) {
        this.jpSP = jpSP;
    }

    public SanPhamDialog(SanPham jpSP, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        init(jpSP);
        listOption = new ArrayList<>();
        initComponents(type);
    }

    public SanPhamDialog(SanPham jpSP, JFrame owner, String title, boolean modal, String type, SanPhamDTO sp) {
        super(owner, title, modal);
        init(jpSP);
        this.sp = sp;
        listOption = new ArrayList<>(jpSP.optionBUS.getAllOSPByIDSP(sp.getId()));
        initComponents(type);
    }

    public SanPhamDialog(JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        initComponents(type);
    }

    public void initCardOne(String type) {
        pnCenter = new JPanel(new BorderLayout());
        pnInfoSanPham = new JPanel(new GridLayout(4, 1, 0, 0));
        pnInfoSanPham.setBackground(Color.WHITE);
        pnCenter.add(pnInfoSanPham, BorderLayout.CENTER);

        //Hình ảnh
        pnInfoSanPhamRight = new JPanel();
        pnInfoSanPhamRight.setBackground(Color.WHITE);
        pnInfoSanPhamRight.setPreferredSize(new Dimension(300, 600));
        pnInfoSanPhamRight.setBorder(new EmptyBorder(0, 10, 0, 10));
        pnCenter.add(pnInfoSanPhamRight, BorderLayout.WEST);

        inputHinhAnh = new InputImage("Hình minh họa");
        maSP = new InputForm("Mã Sản Phẩm");
        tenSP = new InputForm("Tên sản phẩm");
        baoHanhSP = new InputForm("Thời gian bảo hành (tháng)");
        PlainDocument baohanh = (PlainDocument) baoHanhSP.getTxtForm().getDocument();
        baohanh.setDocumentFilter((new NumericDocumentFilter()));
        //Gán giá trị mặc định
        baohanh.addDocumentListener(new DocumentListener() {
            private void setDefault() {
                if (baoHanhSP.getText().trim().isEmpty()) {
                    baoHanhSP.setText("0");
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                //Kiểm tra nội dung khi bị xóa
                SwingUtilities.invokeLater(this::setDefault);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        ArrayList<String> dataLoai = new ArrayList<>(mapCate.keySet());
        dataLoai.add(0, "Chọn Loại");
        cbCate = new SelectForm("Phân loại", dataLoai.toArray(new String[0]));
        cbCate.cbb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

            }
        });

        pnInfoSanPhamRight.add(inputHinhAnh);
        pnInfoSanPham.add(maSP);
        pnInfoSanPham.add(tenSP);
        pnInfoSanPham.add(baoHanhSP);
        pnInfoSanPham.add(cbCate);


        pnBottom = new JPanel(new FlowLayout());
        pnBottom.setBorder(new EmptyBorder(60, 0, 10, 0));
        pnBottom.setBackground(Color.white);
        switch (type) {
            case "view" -> {
                btnXemOption = new ButtonCustom("Xem lựa chọn", "warning", 14);
                btnXemOption.addActionListener(this);
                pnBottom.add(btnXemOption);
            }
            case "update" -> {

                btnUpdateSanPham = new ButtonCustom("Lưu thông tin", "success", 14);
                btnXemOption = new ButtonCustom("Sửa lựa chọn", "warning", 14);
                btnUpdateSanPham.addActionListener(this);
                btnXemOption.addActionListener(this);
                btnXemOption.setEnabled(false);
                pnBottom.add(btnUpdateSanPham);
                pnBottom.add(btnXemOption);
            }
            case "create" -> {

                btnXemOption = new ButtonCustom("Tạo lựa chọn", "warning", 14);
                btnXemOption.addActionListener(this);
                btnXemOption.setEnabled(false);
                btnAddSanPham = new ButtonCustom("Thêm sản phẩm", "success", 14);
                btnAddSanPham.addActionListener(this);
                pnBottom.add(btnAddSanPham);
                pnBottom.add(btnXemOption);
            }
        }

        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);
        btnHuyBo.addActionListener(this);
        pnBottom.add(btnHuyBo);
        pnCenter.add(pnBottom, BorderLayout.SOUTH);
    }

    public void initCardTwo(String type) {
        pncard2 = new JPanel(new BorderLayout());
        JPanel optionTop = new JPanel();
        optionTop.setLayout(new GridLayout(1, 1, 0, 15));
        tfMau = new InputForm("Tên Màu");
        optionTop.add(tfMau);

        JPanel optionCenter = new JPanel(new BorderLayout());

        JPanel optionCenter_left = new JPanel();
        BoxLayout bl = new BoxLayout(optionCenter_left, BoxLayout.Y_AXIS);
        optionCenter_left.setPreferredSize(new Dimension(100, 226));
        optionCenter_left.setBorder(new EmptyBorder(10, 10, 10, 10));
        optionCenter_left.setLayout(bl);
        optionCenter_left.setBackground(Color.WHITE);
        tableOptionMau = new JTable();
        tableOptionMau.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = getRowOption();
                if (index != -1) {
                    tfMau.setText(listOption.get(index).getColor());
                    tfMau.setDisable();
                    btnAddOption.setEnabled(false);
                    btnCancelOption.setEnabled(true);
                    btnDeleteOption.setEnabled(true);
                }
            }
        });

        scrollTableOptionMau = new JScrollPane(tableOptionMau);
        tblModel = new DefaultTableModel();
        String[] header = new String[]{ "STT", "Màu" };
        tblModel.setColumnIdentifiers(header);
        tableOptionMau.setModel(tblModel);
        tableOptionMau.setFocusable(false);
        scrollTableOptionMau.setViewportView(tableOptionMau);
        tableOptionMau.setDefaultRenderer(Object.class, centerRenderer);
        optionCenter_left.add(scrollTableOptionMau);

        JPanel optionCenter_right = new JPanel(new FlowLayout());
        optionCenter_right.setPreferredSize(new Dimension(180, 10));
        optionCenter_right.setBackground(Color.white);
        optionCenter_right.setBorder(new EmptyBorder(0, 0, 0, 10));

        btnAddOption = new ButtonCustom("Thêm lựa chọn", "excel", 14);
        btnDeleteOption = new ButtonCustom("Xoá lựa chọn", "danger", 14);
        btnCancelOption = new ButtonCustom("Thoát lựa chọn", "success", 14);
        btnDeleteOption.setEnabled(false);
        btnCancelOption.setEnabled(false);

        btnAddOption.addActionListener(this);
        btnDeleteOption.addActionListener(this);
        btnCancelOption.addActionListener(this);

        optionCenter_right.add(btnAddOption);
        optionCenter_right.add(btnDeleteOption);
        optionCenter_right.add(btnCancelOption);

        optionCenter.add(optionCenter_left, BorderLayout.CENTER);
        optionCenter.add(optionCenter_right, BorderLayout.EAST);

        JPanel optionBottom = new JPanel(new FlowLayout());
        optionBottom.setBackground(Color.white);
        optionBottom.setBorder(new EmptyBorder(0, 0, 10, 0));

        switch (type) {
            case "view" -> {
                loadDataOption();
                btnAddOption.setVisible(false);
                btnDeleteOption.setVisible(false);
                optionCenter.remove(optionCenter_right);
            }
            case "update" -> {
                loadDataOption();
                btnUpdate = new ButtonCustom("Hoàn thành", "success", 14);
                btnUpdate.addActionListener(this);
                optionBottom.add(btnUpdate);
            }
            case "create" -> {
                btnAdd = new ButtonCustom("Hoàn thành", "success", 14);
                btnAdd.addActionListener(this);
                optionBottom.add(btnAdd);
            }
        }

        btnBack = new ButtonCustom("Quay lại", "warning", 14);
        btnBack.addActionListener(this);
        optionBottom.add(btnBack);

        pncard2.add(optionTop, BorderLayout.NORTH);
        pncard2.add(optionCenter, BorderLayout.CENTER);
        pncard2.add(optionBottom, BorderLayout.SOUTH);

    }

    public void initComponents(String type) {
        this.type = type;
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        this.setSize(new Dimension(680, 460));
        this.setLayout(new BorderLayout(0, 0));

        pnMain = new JPanel(new CardLayout());
        initCardOne(type);
        initCardTwo(type);

        pnMain.add(pnCenter);
        pnMain.add(pncard2);

        switch (type) {
            case "create" -> {
                maSP.setText(jpSP.sanphamBUS.createID());
                baoHanhSP.setText("0");
                maSP.setDisable();
            }
            case "view" -> {
                setInfoSP();
                maSP.setDisable();
                tenSP.setDisable();
                baoHanhSP.setDisable();
                cbCate.setDisable();
            }
            case "update" -> {
                setInfoSP();
                maSP.setDisable();
            }
            default -> {
            }
        }

        this.add(pnMain, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public String addImage(String urlImg) {
        if (urlImg == null) {
            return "";
        }
        Random randomGenerator = new Random();
        int ram = randomGenerator.nextInt(1000);
        File sourceFile = new File(urlImg);
        String destPath = "./src/img_product";
        File destFolder = new File(destPath);
        String newName = ram + sourceFile.getName();
        try {
            Path dest = Paths.get(destFolder.getPath(), newName);
            Files.copy(sourceFile.toPath(), dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newName;
    }

    public boolean validationSP() {
        //Kiểm tra tên
        String name = tenSP.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên không được bỏ trống", "Tên sản phẩm", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        //Kiểm tra phân loại
        if (cbCate.cbb.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn Phân loại", "Phân loại", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public boolean validationOSP() {
        //Kiểm tra tên màu
        String mau = tfMau.getText().trim();
        if (mau.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên không được bỏ trống", "Tên màu", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        int errTenMau = Validation.isName(mau);
        if (errTenMau != -1) {
            JOptionPane.showMessageDialog(this, Validation.messName[errTenMau], "Tên màu", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnHuyBo || source == btnAdd || source == btnUpdate) {
            dispose();
            return;
        }

        if (source == btnXemOption) {
            CardLayout c = (CardLayout) pnMain.getLayout();
            c.next(pnMain);
            return;
        }

        if (source == btnBack) {
            CardLayout c = (CardLayout) pnMain.getLayout();
            c.previous(pnMain);
            return;
        }

        if (source == btnAddSanPham && validationSP()) {
            if (eventAddSP()) {
                jpSP.loadDataTable();
                btnXemOption.setEnabled(true);
                btnAddSanPham.setEnabled(false);
            }
            return;
        }
        if (source == btnUpdateSanPham && validationSP()) {
            if (eventUpdateSP()) {
                jpSP.loadDataTable();
                btnXemOption.setEnabled(true);
                btnUpdateSanPham.setEnabled(false);
            }
            return;
        }

        if (source == btnAddOption && validationOSP()) {
            String idSP = maSP.getText();
            String color = tfMau.getText().trim();
            OptionSanPhamDTO option = new OptionSanPhamDTO(idSP, color, false);
            int addSuccess = jpSP.optionBUS.addOSP(option);
            if (addSuccess == -1) {
                JOptionPane.showMessageDialog(this, "Thêm lựa chọn thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                listOption.add(option);
                loadDataOption();
            } else {
                JOptionPane.showMessageDialog(this, OptionSanPhamBUS.duplicateMess[addSuccess], "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            }
            return;
        }
        if (source == btnDeleteOption) {
            String idSP = maSP.getText();
            String color = tfMau.getText().trim();
            OptionSanPhamDTO option = new OptionSanPhamDTO(idSP, color, false);
            jpSP.optionBUS.removeOSP(option);
            JOptionPane.showMessageDialog(this, "Xóa lựa chọn thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            listOption.remove(option);
            loadDataOption();
            return;
        }
        if (source == btnCancelOption) {
            tfMau.setText("");
            tfMau.getTxtForm().setEnabled(true);
            btnAddOption.setEnabled(true);
            btnCancelOption.setEnabled(false);
            btnDeleteOption.setEnabled(false);
            tableOptionMau.clearSelection();
        }
    }

    public boolean eventAddSP() {
        String nameCate = (String) cbCate.cbb.getSelectedItem();
        String idCate = mapCate.get(nameCate);
        int baoHanh = Integer.parseInt(baoHanhSP.getText().trim());
        int addSuccess = jpSP.sanphamBUS.addSPWithData(new SanPhamDTO(maSP.getText(), tenSP.getText().trim(), addImage(inputHinhAnh.getUrl_img()),
                idCate, nameCate, baoHanh, 0, false));
        if (addSuccess == -1) {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(this, SanPhamBUS.duplicateMess[addSuccess], "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    public boolean eventUpdateSP() {
        String nameCate = (String) cbCate.cbb.getSelectedItem();
        String idCate = mapCate.get(nameCate);
        int baoHanh = Integer.parseInt(baoHanhSP.getText().trim());
        String nameSP = tenSP.getText().trim();
        int updateSuccess = jpSP.sanphamBUS.updateSanPham(new SanPhamDTO(sp.getId(), nameSP, inputHinhAnh.getUrl_img(),
                        idCate, nameCate, baoHanh, sp.getTonKho(), false),
                !sp.getName().equals(nameSP));
        if (updateSuccess == -1) {
            JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(this, SanPhamBUS.duplicateMess[updateSuccess], "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    public int getRowOption() {
        int index = tableOptionMau.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn màu!");
        }
        return index;
    }

    public void loadDataOption() {
        tfMau.setText("");
        tfMau.getTxtForm().setEnabled(true);
        btnAddOption.setEnabled(true);
        btnCancelOption.setEnabled(false);
        btnDeleteOption.setEnabled(false);
        tblModel.setRowCount(0);
        for (int i = 0; i < listOption.size(); i++) {
            tblModel.addRow(new Object[]{
                    i + 1, listOption.get(i).getColor()
            });
        }
    }

    public void setInfoSP() {
        maSP.setText(sp.getId());
        tenSP.setText(sp.getName());
        baoHanhSP.setText(sp.getBaoHanh().toString());
        cbCate.setSelectedItem(sp.getNameCate());
        if (sp.getImg().isEmpty()) {
            inputHinhAnh = new InputImage();
        } else {
            inputHinhAnh.setUrl_img(sp.getImg());
        }
    }


    public static void main(String[] args) {
        SanPhamDialog jDialog = new SanPhamDialog(null, "Them", false, "create");
    }
}
