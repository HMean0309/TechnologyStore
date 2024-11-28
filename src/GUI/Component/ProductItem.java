package GUI.Component;

import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ProductItem extends JPanel implements MouseListener {
    public static enum ProductType {
        CHECKBOX, RADIO;
    }
    ProductType type;
    Color FontColor = new Color(96, 125, 139);
    Color ColorBlack = new Color(26, 26, 26);
    Color DefaultColor = new Color(255, 255, 255);
    Color HoverColor = new Color(246, 246, 246, 255);
    JLabel img, lblTenSP, lblMaSP;
    JLabel[] lblSub;
    JPanel panelCenter, panelContent, panelSubContent ;
    JRadioButton radio;
    public boolean isSelected = false;

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


    public ProductItem(String linkImg, String tenSP, String maSP, String[] subTitle ,String[] sub, ProductType type){
        this.setLayout(new BorderLayout(10, 0));
        this.setPreferredSize(new Dimension(380, 80));
        this.setBorder(new EmptyBorder(10, 20, 10, 20));
        this.setBackground(Color.white);
        this.putClientProperty( FlatClientProperties.STYLE, "arc: 15" );

        this.type = type;
        switch (type){
            case RADIO: {
                radio = new JRadioButton();
                radio.setOpaque(false);
                radio.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        isSelected = radio.isSelected();
                        if (isSelected){
                            setBackground(HoverColor);
                        } else {
                            setBackground(DefaultColor);
                        }
                    }
                });
                this.add(radio, BorderLayout.WEST);
                break;
            }
            case CHECKBOX:{
                checkbox = new JCheckBox();
                checkbox.setOpaque(false);
                checkbox.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        isSelected = checkbox.isSelected();
                        if (isSelected){
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
        panelCenter.setLayout(new BorderLayout(10,0));
        panelCenter.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelCenter.setOpaque(false);
        this.add(panelCenter, BorderLayout.CENTER);

        img = new JLabel("");
        img.setIcon(InputImage.resizeImage(new ImageIcon("./src/img_product/" + linkImg), 38));
        img.setOpaque(false);
        panelCenter.add(img, BorderLayout.WEST);

        panelContent = new JPanel();
        panelContent.setLayout(new GridLayout(3,1));
        panelContent.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelContent.setOpaque(false);
        panelCenter.add(panelContent,BorderLayout.CENTER);

        lblTenSP = new JLabel(tenSP);
        lblTenSP.putClientProperty("FlatLaf.style", "font: 120% $semibold.font");
        lblTenSP.setForeground(Color.black);
        panelContent.add(lblTenSP);

        lblMaSP = new JLabel(maSP);
        lblMaSP.putClientProperty("FlatLaf.style", "font: 80% $semibold.font");
        lblMaSP.setForeground(Color.black);
        panelContent.add(lblMaSP);

        panelSubContent = new JPanel();
        panelSubContent.setLayout(new GridLayout(1 + (sub.length % 2),2));
        panelSubContent.setBorder(new EmptyBorder(0, 0, 0, 0));
        panelSubContent.setOpaque(false);
        panelContent.add(panelSubContent);

        lblSub = new JLabel[sub.length];
        for (int i = 0; i < lblSub.length; i++) {
            lblSub[i] = new JLabel(subTitle[i] + ": " + sub[i]);
            lblSub[i].putClientProperty("FlatLaf.style", "font: 100% $medium.font");
            lblSub[i].setForeground(Color.gray);
            panelSubContent.add(lblSub[i]);
        }

        addMouseListener(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        switch (type){
            case RADIO: {
                radio.setSelected(true);
                break;
            }
            case CHECKBOX:{
                if (!isSelected){
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test sp");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);

        ProductItem item1 = new ProductItem("oppo_f9.png", "OPPO F9", "PRODUCT00002",
                new String[]{"Số lượng", "Phân loại"}, new String[]{"15","Điện thoại"}, ProductType.CHECKBOX );
        frame.getContentPane().setLayout(new FlowLayout());
        frame.add(item1);

        frame.pack();
        frame.setVisible(true);
    }
}
