package GUI.Component;

import DTO.ChiTietSanPhamDTO;
import DTO.HoaDonDTO;
import DTO.SanPhamDTO;
import com.formdev.flatlaf.FlatClientProperties;
import helper.Formater;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ProductItem extends JPanel implements MouseListener {

    public static enum ProductType {
        CHECKBOX, RADIO, NONE;
    }

    public boolean isProduct;
    // Để quản lý các PropertyChangeListener
    PropertyChangeSupport support = new PropertyChangeSupport(this);

    ProductType type;
    Color FontColor = new Color(96, 125, 139);
    Color ColorBlack = new Color(26, 26, 26);
    Color DefaultColor = new Color(255, 255, 255);
    Color HoverColor = new Color(223, 241, 255, 255);
    JLabel img, lblTenSP, lblMaSP;
    JLabel[] lblSub;
    JPanel panelCenter, panelContent, panelSubContent;
    JRadioButton radio;
    private boolean isSelected = false;

    private SanPhamDTO sanPham;
    private ChiTietSanPhamDTO ctsanPham;
    JCheckBox checkbox;


    public JRadioButton getRadio() {
        return radio;
    }

    public void setRadio(JRadioButton radio) {
        this.radio = radio;
    }

    public JCheckBox getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(JCheckBox checkbox) {
        this.checkbox = checkbox;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public ChiTietSanPhamDTO getCtsanPham() {
        return ctsanPham;
    }

    public void setSelected(boolean isSelected) {
        boolean oldValue = this.isSelected;
        this.isSelected = isSelected;
        // Bắn sự kiện PropertyChangeEvent
        support.firePropertyChange("isSelected", oldValue, isSelected);
    }

    // Thêm PropertyChangeListener
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        support.addPropertyChangeListener(listener);
    }

    // Xóa PropertyChangeListener
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        if (support == null) {
            support = new PropertyChangeSupport(this);
        }
        support.removePropertyChangeListener(listener);
    }

    public void initComponents(String linkImg, String tenSP, String maSP, String[] subTitle, String[] sub, ProductType type) {
        this.setLayout(new BorderLayout(10, 0));
        this.setBackground(DefaultColor);
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(180, 80));
        this.setMaximumSize(new Dimension(500, 80));
        this.setMinimumSize(new Dimension(80, 80));
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.putClientProperty(FlatClientProperties.STYLE, "arc: 15");

        this.type = type;
        switch (type) {
            case RADIO: {
                radio = new JRadioButton();
                radio.setOpaque(false);
                radio.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        setSelected(radio.isSelected());
                        if (isSelected) {
                            setBackground(HoverColor);
                        } else {
                            setBackground(DefaultColor);
                        }
                    }
                });
                this.add(radio, BorderLayout.WEST);
                break;
            }
            case CHECKBOX: {
                checkbox = new JCheckBox();
                checkbox.setOpaque(false);
                checkbox.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        setSelected(checkbox.isSelected());
                        if (isSelected) {
                            setBackground(HoverColor);
                        } else {
                            setBackground(DefaultColor);
                        }
                    }
                });
                this.add(checkbox, BorderLayout.WEST);
                break;
            }
        }

        panelCenter = new JPanel();
        panelCenter.setLayout(new BorderLayout(10, 0));
        panelCenter.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelCenter.setOpaque(false);
        this.add(panelCenter, BorderLayout.CENTER);

        if (isProduct && linkImg.isEmpty()) {
            linkImg = "product.png";
        }
        img = new JLabel("");
        img.setIcon(InputImage.resizeImage(new ImageIcon("./src/img_product/" + linkImg), 38));
        img.setOpaque(false);
        panelCenter.add(img, BorderLayout.WEST);

        panelContent = new JPanel();
        panelContent.setLayout(new GridLayout(3, 1));
        panelContent.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelContent.setOpaque(false);
        panelCenter.add(panelContent, BorderLayout.CENTER);

        lblTenSP = new JLabel(tenSP);
        lblTenSP.putClientProperty("FlatLaf.style", "font: 120% $semibold.font");
        lblTenSP.setForeground(Color.black);
        panelContent.add(lblTenSP);

        lblMaSP = new JLabel(maSP);
        if (isProduct) {
            lblMaSP.putClientProperty("FlatLaf.style", "font: 80% $semibold.font");
            lblMaSP.setForeground(Color.black);
        } else {
            lblMaSP.putClientProperty("FlatLaf.style", "font: 90% $semibold.font");
            lblMaSP.setForeground(Color.BLUE);
        }
        panelContent.add(lblMaSP);

        panelSubContent = new JPanel();
        panelSubContent.setLayout(new GridLayout(1 + (sub.length % 2), 2, 10, 0));
        panelSubContent.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelSubContent.setOpaque(false);
        panelContent.add(panelSubContent);

        lblSub = new JLabel[sub.length];
        for (int i = 0; i < lblSub.length; i++) {
            lblSub[i] = new JLabel(subTitle[i] + ": " + sub[i]);
            lblSub[i].putClientProperty("FlatLaf.style", "font: 80% $medium.font");
            lblSub[i].setForeground(Color.gray);
            panelSubContent.add(lblSub[i]);
        }

        addMouseListener(this);
    }

    public ProductItem(SanPhamDTO sanPham, ProductType type) {
        this.sanPham = sanPham;
        this.isProduct = true;
        initComponents(sanPham.getImg(), sanPham.getName(), sanPham.getId(),
                new String[]{ "Phân loại", "Số lượng tồn" }, new String[]{ sanPham.getNameCate(), sanPham.getTonKhoString() },
                type);
        this.support = new PropertyChangeSupport(this);
    }

    public ProductItem(SanPhamDTO sanPham, ProductType type, HoaDonDTO hd) {
        this.sanPham = sanPham;
        this.isProduct = true;
        initComponents(sanPham.getImg(), sanPham.getName(), sanPham.getId(),
                new String[]{ "Bảo hành", "" }, new String[]{ sanPham.getBaoHanhString(), hd.getId() },
                type);
        this.support = new PropertyChangeSupport(this);
    }

    public ProductItem(ChiTietSanPhamDTO ctsp, ProductType type) {
        this.ctsanPham = ctsp;
        this.isProduct = false;
        initComponents("", ctsp.getSeri(), Formater.formatVND(ctsp.getPrice()),
                new String[]{ "Sản phẩm", "Màu" }, new String[]{ ctsanPham.getIdSP(), ctsanPham.getColor() },
                type);
        this.support = new PropertyChangeSupport(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (type) {
            case RADIO: {
                radio.setSelected(true);
                break;
            }
            case CHECKBOX: {
                if (!isSelected) {
                    checkbox.setSelected(true);
                } else {
                    checkbox.setSelected(false);
                }

                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (!isSelected) {
            setBackground(HoverColor);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!isSelected) {
            setBackground(DefaultColor);
        }
    }

    public SanPhamDTO getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPhamDTO sanPham) {
        this.sanPham = sanPham;
    }

    public void setDisable() {
        this.setEnabled(false);
    }
}
