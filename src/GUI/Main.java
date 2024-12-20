/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;


import java.awt.Dimension;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.MatteBorder;
import com.formdev.flatlaf.extras.FlatSVGIcon;


public class Main extends javax.swing.JFrame {
    
    public Main() {
        this.setPreferredSize(new Dimension(1366, 677));
        
//        addIconTaskbar();
        setTitle("Cửa Hàng Bán Đồ Công Nghệ");
        
        
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        taskBar = new javax.swing.JPanel();
        btnSanPham = new javax.swing.JToggleButton();
        btnLoai = new javax.swing.JToggleButton();
        btnDatHang = new javax.swing.JToggleButton();
        btnNhanVien = new javax.swing.JToggleButton();
        btnPhieuNhap = new javax.swing.JToggleButton();
        btnKhachHang = new javax.swing.JToggleButton();
        btnNhaCungCap = new javax.swing.JToggleButton();
        btnThongKe = new javax.swing.JToggleButton();
        btnHoaDon = new javax.swing.JToggleButton();
        btnPhanQuyen = new javax.swing.JToggleButton();
        btnKhuyenMai = new javax.swing.JToggleButton();
        btnTaiKhoan = new javax.swing.JToggleButton();
        btnBaoHanh = new javax.swing.JToggleButton();
        btnDangXuat = new javax.swing.JButton();
        Header = new javax.swing.JPanel();
        contentPanel = new javax.swing.JPanel();
        contentPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        taskBar.setBackground(new java.awt.Color(245, 245, 245));

        btnSanPham.setBackground(new java.awt.Color(238, 238, 238));
        buttonGroup1.add(btnSanPham);
        btnSanPham.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnSanPham.setForeground(new java.awt.Color(0, 0, 0));
        btnSanPham.setIcon(new FlatSVGIcon("GUI/icon/product.svg"));
        btnSanPham.setText("SẢN PHẨM");
        btnSanPham.setBorder(new MatteBorder(0, 0, 2, 0, new Color(255,255,255)));
        btnSanPham.setFocusPainted(false);
        btnSanPham.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSanPham.setIconTextGap(7);
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });

        btnLoai.setBackground(new java.awt.Color(238, 238, 238));
        buttonGroup1.add(btnLoai);
        btnLoai.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnLoai.setForeground(new java.awt.Color(0, 0, 0));
        btnLoai.setIcon(new FlatSVGIcon("GUI/icon/cate.svg"));
        btnLoai.setText("LOẠI");
        btnLoai.setBorder(new MatteBorder(0, 0, 2, 0, new Color(255,255,255)));
        btnLoai.setFocusPainted(false);
        btnLoai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLoai.setIconTextGap(7);
        btnLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoaiActionPerformed(evt);
            }
        });

        btnDatHang.setBackground(new java.awt.Color(238, 238, 238));
        buttonGroup1.add(btnDatHang);
        btnDatHang.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnDatHang.setForeground(new java.awt.Color(0, 0, 0));
        btnDatHang.setIcon(new FlatSVGIcon("GUI/icon/shopping_cart.svg"));
        btnDatHang.setText("ĐẶT HÀNG");
        btnDatHang.setBorder(new MatteBorder(0, 0, 2, 0, new Color(255,255,255)));
        btnDatHang.setFocusPainted(false);
        btnDatHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDatHang.setIconTextGap(7);
        btnDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatHangActionPerformed(evt);
            }
        });

        btnNhanVien.setBackground(new java.awt.Color(238, 238, 238));
        buttonGroup1.add(btnNhanVien);
        btnNhanVien.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnNhanVien.setForeground(new java.awt.Color(0, 0, 0));
        btnNhanVien.setIcon(new FlatSVGIcon("GUI/icon/users.svg"));
        btnNhanVien.setText("NHÂN VIÊN");
        btnNhanVien.setBorder(new MatteBorder(0, 0, 2, 0, new Color(255,255,255)));
        btnNhanVien.setFocusPainted(false);
        btnNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNhanVien.setIconTextGap(7);
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });

        btnPhieuNhap.setBackground(new java.awt.Color(238, 238, 238));
        buttonGroup1.add(btnPhieuNhap);
        btnPhieuNhap.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnPhieuNhap.setForeground(new java.awt.Color(0, 0, 0));
        btnPhieuNhap.setIcon(new FlatSVGIcon("GUI/icon/import.svg"));
        btnPhieuNhap.setText("PHIẾU NHẬP");
        btnPhieuNhap.setBorder(new MatteBorder(0, 0, 2, 0, new Color(255,255,255)));
        btnPhieuNhap.setFocusPainted(false);
        btnPhieuNhap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPhieuNhap.setIconTextGap(7);
        btnPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhieuNhapActionPerformed(evt);
            }
        });

        btnKhachHang.setBackground(new java.awt.Color(238, 238, 238));
        buttonGroup1.add(btnKhachHang);
        btnKhachHang.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnKhachHang.setForeground(new java.awt.Color(0, 0, 0));
        btnKhachHang.setIcon(new FlatSVGIcon("GUI/icon/customer.svg"));
        btnKhachHang.setText("KHÁCH HÀNG");
        btnKhachHang.setBorder(new MatteBorder(0, 0, 2, 0, new Color(255,255,255)));
        btnKhachHang.setFocusPainted(false);
        btnKhachHang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKhachHang.setIconTextGap(7);
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
            }
        });

        btnNhaCungCap.setBackground(new java.awt.Color(238, 238, 238));
        buttonGroup1.add(btnNhaCungCap);
        btnNhaCungCap.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnNhaCungCap.setForeground(new java.awt.Color(0, 0, 0));
        btnNhaCungCap.setIcon(new FlatSVGIcon("GUI/icon/provider.svg"));
        btnNhaCungCap.setText("NHÀ CUNG CẤP");
        btnNhaCungCap.setBorder(new MatteBorder(0, 0, 2, 0, new Color(255,255,255)));
        btnNhaCungCap.setFocusPainted(false);
        btnNhaCungCap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNhaCungCap.setIconTextGap(7);
        btnNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhaCungCapActionPerformed(evt);
            }
        });

        btnThongKe.setBackground(new java.awt.Color(238, 238, 238));
        buttonGroup1.add(btnThongKe);
        btnThongKe.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnThongKe.setForeground(new java.awt.Color(0, 0, 0));
        btnThongKe.setIcon(new FlatSVGIcon("GUI/icon/bar_chart.svg"));
        btnThongKe.setText("THỐNG KÊ");
        btnThongKe.setBorder(new MatteBorder(0, 0, 2, 0, new Color(255,255,255)));
        btnThongKe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThongKe.setFocusPainted(false);
        btnThongKe.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnThongKe.setIconTextGap(7);
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        btnHoaDon.setBackground(new java.awt.Color(238, 238, 238));
        buttonGroup1.add(btnHoaDon);
        btnHoaDon.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnHoaDon.setForeground(new java.awt.Color(0, 0, 0));
        btnHoaDon.setIcon(new FlatSVGIcon("GUI/icon/bill.svg"));
        btnHoaDon.setText("HÓA ĐƠN");
        btnHoaDon.setBorder(new MatteBorder(0, 0, 2, 0, new Color(255,255,255)));
        btnHoaDon.setFocusPainted(false);
        btnHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHoaDon.setIconTextGap(7);
        btnHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonActionPerformed(evt);
            }
        });

        btnPhanQuyen.setBackground(new java.awt.Color(238, 238, 238));
        buttonGroup1.add(btnPhanQuyen);
        btnPhanQuyen.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnPhanQuyen.setForeground(new java.awt.Color(0, 0, 0));
        btnPhanQuyen.setIcon(new FlatSVGIcon("GUI/icon/permission.svg"));
        btnPhanQuyen.setText("PHÂN QUYỀN");
        btnPhanQuyen.setBorder(new MatteBorder(0, 0, 2, 0, new Color(255,255,255)));
        btnPhanQuyen.setFocusPainted(false);
        btnPhanQuyen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPhanQuyen.setIconTextGap(7);
        btnPhanQuyen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhanQuyenActionPerformed(evt);
            }
        });

        btnKhuyenMai.setBackground(new java.awt.Color(238, 238, 238));
        buttonGroup1.add(btnKhuyenMai);
        btnKhuyenMai.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnKhuyenMai.setForeground(new java.awt.Color(0, 0, 0));
        btnKhuyenMai.setIcon(new FlatSVGIcon("GUI/icon/ticket.svg"));
        btnKhuyenMai.setText("KHUYẾN MÃI");
        btnKhuyenMai.setBorder(new MatteBorder(0, 0, 2, 0, new Color(255,255,255)));
        btnKhuyenMai.setFocusPainted(false);
        btnKhuyenMai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKhuyenMai.setIconTextGap(7);
        btnKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhuyenMaiActionPerformed(evt);
            }
        });

        btnTaiKhoan.setBackground(new java.awt.Color(238, 238, 238));
        buttonGroup1.add(btnTaiKhoan);
        btnTaiKhoan.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnTaiKhoan.setForeground(new java.awt.Color(0, 0, 0));
        btnTaiKhoan.setIcon(new FlatSVGIcon("GUI/icon/manage_accounts.svg"));
        btnTaiKhoan.setText("TÀI KHOẢN");
        btnTaiKhoan.setBorder(new MatteBorder(0, 0, 2, 0, new Color(255,255,255)));
        btnTaiKhoan.setFocusPainted(false);
        btnTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTaiKhoan.setIconTextGap(7);
        btnTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiKhoanActionPerformed(evt);
            }
        });

        btnBaoHanh.setBackground(new java.awt.Color(238, 238, 238));
        buttonGroup1.add(btnBaoHanh);
        btnBaoHanh.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnBaoHanh.setForeground(new java.awt.Color(0, 0, 0));
        btnBaoHanh.setIcon(new FlatSVGIcon("GUI/icon/shield.svg"));
        btnBaoHanh.setText("BẢO HÀNH");
        btnBaoHanh.setBorder(new MatteBorder(0, 0, 2, 0, new Color(255,255,255)));
        btnBaoHanh.setFocusPainted(false);
        btnBaoHanh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBaoHanh.setIconTextGap(7);
        btnBaoHanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaoHanhActionPerformed(evt);
            }
        });

        btnDangXuat.setBackground(new java.awt.Color(238, 238, 238));
        btnDangXuat.setForeground(new java.awt.Color(250, 2, 2));
        btnDangXuat.setIcon(new FlatSVGIcon("GUI/icon/logout.svg"));
        btnDangXuat.setText("ĐĂNG XUẤT");
        btnDangXuat.setBorder(new MatteBorder(2, 0, 0, 0, new Color(255,255,255)));
        btnDangXuat.setFocusPainted(false);
        btnDangXuat.setIconTextGap(7);

        javax.swing.GroupLayout taskBarLayout = new javax.swing.GroupLayout(taskBar);
        taskBar.setLayout(taskBarLayout);
        taskBarLayout.setHorizontalGroup(
            taskBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnLoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnDatHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPhanQuyen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnKhuyenMai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnBaoHanh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(taskBarLayout.createSequentialGroup()
                .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        taskBarLayout.setVerticalGroup(
            taskBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taskBarLayout.createSequentialGroup()
                .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnPhanQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnBaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(taskBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 68, 220, 570));

        Header.setBackground(new java.awt.Color(170, 211, 249));

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1370, Short.MAX_VALUE)
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 65, Short.MAX_VALUE)
        );

        getContentPane().add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 65));

        contentPanel.setBackground(new java.awt.Color(255, 255, 255));
        contentPanel.setPreferredSize(new java.awt.Dimension(1150, 570));

        contentPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout contentPanel2Layout = new javax.swing.GroupLayout(contentPanel2);
        contentPanel2.setLayout(contentPanel2Layout);
        contentPanel2Layout.setHorizontalGroup(
            contentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1072, Short.MAX_VALUE)
        );
        contentPanel2Layout.setVerticalGroup(
            contentPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 512, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(contentPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(contentPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        contentPanel.add(contentPanel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(contentPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 1150, 580));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void btnLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLoaiActionPerformed

    private void btnDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDatHangActionPerformed

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNhanVienActionPerformed

    private void btnPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhieuNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPhieuNhapActionPerformed

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnKhachHangActionPerformed

    private void btnNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhaCungCapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNhaCungCapActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHoaDonActionPerformed

    private void btnPhanQuyenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhanQuyenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPhanQuyenActionPerformed

    private void btnKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhuyenMaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKhuyenMaiActionPerformed

    private void btnTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiKhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTaiKhoanActionPerformed

    private void btnBaoHanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaoHanhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBaoHanhActionPerformed

    public static void main(String args[]) {
      
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new Main().setVisible(true);
                JFrame frame = new JFrame("Khách Hàng");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                KhachHang khachHangPanel = new KhachHang();
                frame.add(khachHangPanel);

                frame.pack();
                frame.setSize(1072, 512);
                frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Header;
    private javax.swing.JToggleButton btnBaoHanh;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JToggleButton btnDatHang;
    private javax.swing.JToggleButton btnHoaDon;
    private javax.swing.JToggleButton btnKhachHang;
    private javax.swing.JToggleButton btnKhuyenMai;
    private javax.swing.JToggleButton btnLoai;
    private javax.swing.JToggleButton btnNhaCungCap;
    private javax.swing.JToggleButton btnNhanVien;
    private javax.swing.JToggleButton btnPhanQuyen;
    private javax.swing.JToggleButton btnPhieuNhap;
    private javax.swing.JToggleButton btnSanPham;
    private javax.swing.JToggleButton btnTaiKhoan;
    private javax.swing.JToggleButton btnThongKe;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JPanel contentPanel2;
    private javax.swing.JPanel taskBar;
    // End of variables declaration//GEN-END:variables
}
