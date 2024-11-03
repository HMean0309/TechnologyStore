/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class KhachHang extends javax.swing.JPanel {

    
    private final String[] columnTitles = {"STT", "Mã KH", "Tên KH", "SĐT", "Địa chỉ"};
    private final int[] columnWidths = {72, 200, 280, 200, 320};
    public KhachHang() {
        initComponents();
        this.setPreferredSize(new Dimension(1072, 512));
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        headLable = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        centerPanel = new javax.swing.JPanel();
        iconSearch = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnThemKH = new javax.swing.JButton();
        btnXoaKH = new javax.swing.JButton();
        btnSuaKH = new javax.swing.JButton();

        topPanel.setBackground(new java.awt.Color(255, 255, 255));
        topPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(56, 56, 56), 2));
        topPanel.setPreferredSize(new java.awt.Dimension(1150, 270));
        topPanel.setLayout(new java.awt.BorderLayout());

        headLable.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        headLable.setForeground(new java.awt.Color(0, 0, 0));
        headLable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headLable.setText("THÔNG TIN KHÁCH HÀNG");
        headLable.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        headLable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        topPanel.add(headLable, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setHorizontalScrollBar(null);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(456, 250));

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },columnTitles
        ) {
            Class[] types = new Class [] {
                Integer.class, String.class, String.class, Integer.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
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
        tblKhachHang.setGridColor(new java.awt.Color(220, 220, 220));
        tblKhachHang.setRowHeight(27);
        tblKhachHang.setShowGrid(true);
        tblKhachHang.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblKhachHang);
        if (tblKhachHang.getColumnModel().getColumnCount() > 0) {
            tblKhachHang.getColumnModel().getColumn(0).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(1).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(2).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(3).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(4).setResizable(false);
        }

        centerPanel.setBackground(new java.awt.Color(255, 255, 255));
        centerPanel.setForeground(new java.awt.Color(255, 255, 255));
        centerPanel.setPreferredSize(new java.awt.Dimension(100, 90));

        iconSearch.setIcon(new FlatSVGIcon("GUI/icon/search.svg")
        );
        iconSearch.setText(" ");

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        btnThemKH.setBackground(new java.awt.Color(172, 249, 175));
        btnThemKH.setIcon(new FlatSVGIcon("GUI/icon/add.svg")
        );
        btnThemKH.setText("Thêm");
        btnThemKH.setFocusPainted(false);
        btnThemKH.setPreferredSize(new java.awt.Dimension(100, 27));

        btnXoaKH.setBackground(new java.awt.Color(254, 147, 147));
        btnXoaKH.setIcon(new FlatSVGIcon("GUI/icon/delete.svg")
        );
        btnXoaKH.setText("Xóa");
        btnXoaKH.setFocusPainted(false);
        btnXoaKH.setPreferredSize(new java.awt.Dimension(100, 27));

        btnSuaKH.setBackground(new java.awt.Color(170, 211, 249));
        btnSuaKH.setIcon(new FlatSVGIcon("GUI/icon/edit.svg")
        );
        btnSuaKH.setText("Sửa");
        btnSuaKH.setFocusPainted(false);
        btnSuaKH.setPreferredSize(new java.awt.Dimension(100, 27));

        javax.swing.GroupLayout centerPanelLayout = new javax.swing.GroupLayout(centerPanel);
        centerPanel.setLayout(centerPanelLayout);
        centerPanelLayout.setHorizontalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(iconSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(centerPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSuaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        centerPanelLayout.setVerticalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, centerPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTimKiem)
                    .addComponent(iconSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(centerPanelLayout.createSequentialGroup()
                .addGroup(centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 35, Short.MAX_VALUE))
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
    private javax.swing.JButton btnSuaKH;
    private javax.swing.JButton btnThemKH;
    private javax.swing.JButton btnXoaKH;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JLabel headLable;
    private javax.swing.JLabel iconSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JPanel topPanel;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
