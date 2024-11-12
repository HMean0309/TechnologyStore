/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.ui.FlatLineBorder;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;


import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class NhaCungCap extends javax.swing.JPanel {

    
    private final String[] columnTitles = {"Mã NCC", "Tên NCC", "SĐT", "Địa chỉ"};
    private final int[] columnWidths = {200, 302, 200, 370};
    public NhaCungCap() {
        initComponents();
        txtTimKiem.putClientProperty("JTextField.placeholderText", "Mã NCC, Tên NCC,...");
        soNhaTextField.putClientProperty("JTextField.placeholderText", "Số nhà, tên đường");
        phuongTextField.putClientProperty("JTextField.placeholderText", "Phường");
        quanTextField.putClientProperty("JTextField.placeholderText", "Quận");
        thanhPhoTextField.putClientProperty("JTextField.placeholderText", "Thành phố");
        btnThemNCC.putClientProperty("JButton.buttonType", "roundRect");
        this.setPreferredSize(new Dimension(1072, 512));
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        headLable = new javax.swing.JLabel();
        inforPanel = new javax.swing.JPanel();
        tenKHLabel = new javax.swing.JLabel();
        maKHLabel = new javax.swing.JLabel();
        sdtLabel = new javax.swing.JLabel();
        maKHTextField = new javax.swing.JTextField();
        tenKHTextField = new javax.swing.JTextField();
        sdtTextField = new javax.swing.JTextField();
        diachiLabel = new javax.swing.JLabel();
        soNhaTextField = new javax.swing.JTextField();
        phuongTextField = new javax.swing.JTextField();
        quanTextField = new javax.swing.JTextField();
        thanhPhoTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        centerPanel = new javax.swing.JPanel();
        iconSearch = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnThemNCC = new javax.swing.JButton();
        btnXoaNCC = new javax.swing.JButton();
        btnSuaNCC = new javax.swing.JButton();

        topPanel.setBackground(new java.awt.Color(255, 255, 255));
        topPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(56, 56, 56), 2));
        topPanel.setPreferredSize(new java.awt.Dimension(1150, 270));
        topPanel.setLayout(new java.awt.BorderLayout());

        headLable.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        headLable.setForeground(new java.awt.Color(0, 0, 0));
        headLable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headLable.setText("THÔNG TIN NHÀ CUNG CẤP");
        headLable.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        headLable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        topPanel.add(headLable, java.awt.BorderLayout.PAGE_START);

        inforPanel.setBackground(new java.awt.Color(255, 255, 255));

        tenKHLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tenKHLabel.setForeground(new java.awt.Color(0, 0, 0));
        tenKHLabel.setText("Tên NCC");

        maKHLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        maKHLabel.setForeground(new java.awt.Color(0, 0, 0));
        maKHLabel.setText("Mã NCC");

        sdtLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        sdtLabel.setForeground(new java.awt.Color(0, 0, 0));
        sdtLabel.setText("SĐT");

        maKHTextField.setEditable(false);
        maKHTextField.setBackground(new java.awt.Color(238, 238, 238));
        maKHTextField.setBorder(new FlatLineBorder(new Insets(7, 7, 7, 7), new Color(146, 143, 143), 1, 15));
        maKHTextField.setFocusable(false);
        maKHTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maKHTextFieldActionPerformed(evt);
            }
        });

        tenKHTextField.setEditable(false);
        tenKHTextField.setBackground(new java.awt.Color(255, 255, 255));
        tenKHTextField.setBorder(new FlatLineBorder(new Insets(7, 7, 7, 7), new Color(0, 0, 0), 1, 15));
        tenKHTextField.setFocusable(false);

        sdtTextField.setEditable(false);
        sdtTextField.setBackground(new java.awt.Color(255, 255, 255));
        sdtTextField.setBorder(new FlatLineBorder(new Insets(7, 7, 7, 7), new Color(0, 0, 0), 1, 15));
        sdtTextField.setFocusable(false);

        diachiLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        diachiLabel.setForeground(new java.awt.Color(0, 0, 0));
        diachiLabel.setText("Địa chỉ");

        soNhaTextField.setEditable(false);
        soNhaTextField.setBackground(new java.awt.Color(255, 255, 255));
        soNhaTextField.setBorder(new FlatLineBorder(new Insets(5, 5, 5, 5), new Color(0, 0, 0), 1, 15));
        soNhaTextField.setFocusable(false);

        phuongTextField.setEditable(false);
        phuongTextField.setBackground(new java.awt.Color(255, 255, 255));
        phuongTextField.setBorder(new FlatLineBorder(new Insets(5, 5, 5, 5), new Color(0, 0, 0), 1, 15));
        phuongTextField.setFocusable(false);

        quanTextField.setEditable(false);
        quanTextField.setBackground(new java.awt.Color(255, 255, 255));
        quanTextField.setBorder(new FlatLineBorder(new Insets(5, 5, 5, 5), new Color(0, 0, 0), 1, 15));
        quanTextField.setFocusable(false);

        thanhPhoTextField.setEditable(false);
        thanhPhoTextField.setBackground(new java.awt.Color(255, 255, 255));
        thanhPhoTextField.setBorder(new FlatLineBorder(new Insets(5, 5, 5, 5), new Color(0, 0, 0), 1, 15));
        thanhPhoTextField.setFocusable(false);

        javax.swing.GroupLayout inforPanelLayout = new javax.swing.GroupLayout(inforPanel);
        inforPanel.setLayout(inforPanelLayout);
        inforPanelLayout.setHorizontalGroup(
            inforPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inforPanelLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(inforPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(maKHLabel)
                    .addComponent(tenKHLabel)
                    .addComponent(sdtLabel))
                .addGap(18, 18, 18)
                .addGroup(inforPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sdtTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tenKHTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maKHTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 239, Short.MAX_VALUE)
                .addGroup(inforPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(thanhPhoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(diachiLabel)
                    .addComponent(soNhaTextField)
                    .addComponent(phuongTextField)
                    .addComponent(quanTextField))
                .addGap(185, 185, 185))
        );
        inforPanelLayout.setVerticalGroup(
            inforPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inforPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(inforPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(diachiLabel)
                    .addComponent(maKHLabel)
                    .addComponent(maKHTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(soNhaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(inforPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phuongTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tenKHLabel)
                    .addComponent(tenKHTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(inforPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inforPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(inforPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sdtLabel)
                            .addComponent(sdtTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(inforPanelLayout.createSequentialGroup()
                        .addComponent(quanTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(thanhPhoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 3, Short.MAX_VALUE)))
                .addGap(40, 40, 40))
        );

        topPanel.add(inforPanel, java.awt.BorderLayout.CENTER);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(456, 250));

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },columnTitles
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setBackground(new Color(179, 179, 179));
        headerRenderer.setForeground(Color.BLACK);

        for (int i = 0; i < tblKhachHang.getColumnCount(); i++) {
            tblKhachHang.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
            tblKhachHang.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }

        TableCellRenderer customRenderer = new CustomHeightRenderer();
        tblKhachHang.getColumnModel().getColumn(0).setCellRenderer(customRenderer);
        tblKhachHang.getColumnModel().getColumn(1).setCellRenderer(customRenderer);
        tblKhachHang.getColumnModel().getColumn(2).setCellRenderer(customRenderer);
        tblKhachHang.getColumnModel().getColumn(3).setCellRenderer(customRenderer);
        tblKhachHang.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblKhachHang.setFocusable(false);
        tblKhachHang.setGridColor(new java.awt.Color(220, 220, 220));
        tblKhachHang.setRowHeight(27);
        tblKhachHang.setRowSelectionAllowed(false);
        tblKhachHang.setShowGrid(true);
        tblKhachHang.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblKhachHang);
        tblKhachHang.getTableHeader().setReorderingAllowed(false);
        tblKhachHang.getTableHeader().setResizingAllowed(false);

        centerPanel.setBackground(new java.awt.Color(255, 255, 255));
        centerPanel.setForeground(new java.awt.Color(255, 255, 255));
        centerPanel.setPreferredSize(new java.awt.Dimension(100, 90));

        iconSearch.setIcon(new FlatSVGIcon("GUI/icon/search.svg")
        );
        iconSearch.setText(" ");

        txtTimKiem.setBorder(new FlatLineBorder(new Insets(3, 3, 3, 3), new Color(0, 0, 0), 1, 15));
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        btnThemNCC.setBackground(new java.awt.Color(172, 249, 175));
        btnThemNCC.setIcon(new FlatSVGIcon("GUI/icon/add.svg")
        );
        btnThemNCC.setText("Thêm");
        btnThemNCC.setBorder(null);
        btnThemNCC.setFocusPainted(false);
        btnThemNCC.setPreferredSize(new java.awt.Dimension(100, 27));

        btnXoaNCC.setBackground(new java.awt.Color(254, 147, 147));
        btnXoaNCC.setIcon(new FlatSVGIcon("GUI/icon/delete.svg")
        );
        btnXoaNCC.setText("Xóa");
        btnXoaNCC.setBorder(null);
        btnXoaNCC.setFocusPainted(false);
        btnXoaNCC.setPreferredSize(new java.awt.Dimension(100, 27));

        btnSuaNCC.setBackground(new java.awt.Color(170, 211, 249));
        btnSuaNCC.setIcon(new FlatSVGIcon("GUI/icon/edit.svg")
        );
        btnSuaNCC.setText("Sửa");
        btnSuaNCC.setBorder(null);
        btnSuaNCC.setFocusPainted(false);
        btnSuaNCC.setPreferredSize(new java.awt.Dimension(100, 27));

        javax.swing.GroupLayout centerPanelLayout = new javax.swing.GroupLayout(centerPanel);
        centerPanel.setLayout(centerPanelLayout);
        centerPanelLayout.setHorizontalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(iconSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSuaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        centerPanelLayout.setVerticalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, centerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, centerPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1072, Short.MAX_VALUE)
            .addComponent(centerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1072, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(centerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void maKHTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maKHTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maKHTextFieldActionPerformed
    public class CustomHeightRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            cell.setPreferredSize(new Dimension(cell.getPreferredSize().width, 200));
            return cell;
        }
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSuaNCC;
    private javax.swing.JButton btnThemNCC;
    private javax.swing.JButton btnXoaNCC;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JLabel diachiLabel;
    private javax.swing.JLabel headLable;
    private javax.swing.JLabel iconSearch;
    private javax.swing.JPanel inforPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel maKHLabel;
    private javax.swing.JTextField maKHTextField;
    private javax.swing.JTextField phuongTextField;
    private javax.swing.JTextField quanTextField;
    private javax.swing.JLabel sdtLabel;
    private javax.swing.JTextField sdtTextField;
    private javax.swing.JTextField soNhaTextField;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JLabel tenKHLabel;
    private javax.swing.JTextField tenKHTextField;
    private javax.swing.JTextField thanhPhoTextField;
    private javax.swing.JPanel topPanel;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
