/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog;

import DTO.ChiTietSanPhamDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.ProductItem;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ListChiTietSanPham extends JDialog {
    Color BackgroundColor = new Color(240, 247, 250);
    private ArrayList<ChiTietSanPhamDTO> dataList;
    private ArrayList<ChiTietSanPhamDTO> resultList;
    private JTextField tfSearch;
    private JPanel panelSearch;
    private JPanel panelTable;
    private JPanel contentCTSP;
    private JScrollPane scrollPaneCTSP;
    private ProductItem[] listCTSP;

    public ListChiTietSanPham(ArrayList<ChiTietSanPhamDTO> data, JFrame owner, String title, boolean modal) {
        super(owner, title, modal);
        this.dataList = data;
        init();
        loadDataList();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public ListChiTietSanPham(ArrayList<ChiTietSanPhamDTO> data, JFrame owner, String title, boolean modal, ArrayList<ChiTietSanPhamDTO> oldData) {
        super(owner, title, modal);
        this.dataList = data;
        init();
        loadDataList();
        for (ProductItem item : listCTSP) {
            if (oldData.contains(item.getCtsanPham())) {
                item.getCheckbox().setSelected(true);
            }
        }
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public ArrayList<ChiTietSanPhamDTO> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<ChiTietSanPhamDTO> dataList) {
        this.dataList = dataList;
    }

    public ArrayList<ChiTietSanPhamDTO> getResultList() {
        return resultList;
    }

    public void setResultList(ArrayList<ChiTietSanPhamDTO> resultList) {
        this.resultList = resultList;
    }

    public void init() {
        this.setSize(new Dimension(400, 600));
        this.setLayout(new BorderLayout());

        //Panel Search
        panelSearch = new JPanel(new BorderLayout());
        panelSearch.setSize(new Dimension(0, 150));
        panelSearch.setBorder(new EmptyBorder(20, 20, 20, 20));
        JLabel jLabelSearch = new JLabel("Tìm kiếm  ");
        jLabelSearch.setSize(new Dimension(100, 0));
        tfSearch = new JTextField();
        tfSearch.setFont(new Font(FlatRobotoFont.FAMILY, 0, 13));
        tfSearch.putClientProperty("JTextField.placeholderText", "Tìm kiếm seri....");
        tfSearch.putClientProperty("JTextField.showClearButton", true);
        tfSearch.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (!tfSearch.getText().isEmpty()) {
                    String contentSearch = tfSearch.getText().trim();
                    ArrayList<ChiTietSanPhamDTO> result = dataList.stream()
                            .filter(chiTietSanPhamDTO -> chiTietSanPhamDTO.getSeri().contains(contentSearch))
                            .collect(Collectors.toCollection(ArrayList::new));
                    loadDataList(result);
                } else {
                    loadDataList();
                }
            }
        });

        //Button Chọn List Sản Phẩm
        ButtonCustom buttonChoose = new ButtonCustom("Chọn các seri", "success", 14);
        buttonChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultList = new ArrayList<>();
                for (ProductItem item : listCTSP) {
                    if (item.isSelected()) {
                        resultList.add(item.getCtsanPham());
                    }
                }
                dispose();
            }
        });

        panelSearch.add(jLabelSearch, BorderLayout.WEST);
        panelSearch.add(tfSearch, BorderLayout.CENTER);
        panelSearch.add(buttonChoose, BorderLayout.EAST);
        panelSearch.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.add(panelSearch, BorderLayout.NORTH);

        panelTable = new JPanel();
        panelTable.setLayout(new GridLayout(1, 1));

        // List sản phẩm
        contentCTSP = new JPanel();
        contentCTSP.setBackground(BackgroundColor);
        contentCTSP.setLayout(new BoxLayout(contentCTSP, BoxLayout.Y_AXIS));
        contentCTSP.setBorder(new EmptyBorder(5, 10, 10, 5));
        scrollPaneCTSP = new JScrollPane(contentCTSP, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneCTSP.setBackground(BackgroundColor);
        scrollPaneCTSP.setBorder(new EmptyBorder(0, 0, 15, 0));

        panelTable.add(scrollPaneCTSP);
        this.add(panelTable, BorderLayout.CENTER);
    }

    public void loadDataList() {
        contentCTSP.removeAll();
        contentCTSP.revalidate();
        contentCTSP.repaint();
        listCTSP = new ProductItem[dataList.size()];
        for (int i = 0; i < dataList.size(); i++) {
            listCTSP[i] = new ProductItem(dataList.get(i), ProductItem.ProductType.CHECKBOX);
            contentCTSP.add(listCTSP[i]);

            contentCTSP.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        contentCTSP.revalidate();
        contentCTSP.repaint();

        tfSearch.setText("");
    }

    public void loadDataList(ArrayList<ChiTietSanPhamDTO> data) {
        contentCTSP.removeAll();
        contentCTSP.revalidate();
        contentCTSP.repaint();
        listCTSP = new ProductItem[data.size()];
        for (int i = 0; i < data.size(); i++) {
            listCTSP[i] = new ProductItem(data.get(i), ProductItem.ProductType.CHECKBOX);
            contentCTSP.add(listCTSP[i]);

            contentCTSP.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        contentCTSP.revalidate();
        contentCTSP.repaint();

    }

}
