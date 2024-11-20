package GUI.HDon;

import GUI.BHanh.*;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.ui.FlatLineBorder;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ChiTietHoaDon extends javax.swing.JFrame {

    public ChiTietHoaDon() {
        setTitle("Thông tin hóa đơn");
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 700));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 200));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Mã bảo hành");
        jLabel1.setFocusable(false);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(80, 20, 112, 33);

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(220, 220, 220));
        jTextField1.setForeground(new java.awt.Color(0, 0, 0));
        jTextField1.setBorder(new FlatLineBorder(new Insets(7, 7, 7, 7), new Color(217, 217, 217), 1, 15));
        jTextField1.setFocusable(false);
        jPanel1.add(jTextField1);
        jTextField1.setBounds(210, 20, 212, 33);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nhân viên lập phiếu");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(480, 20, 180, 33);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Thời gian lập");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(80, 110, 120, 33);

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(255, 255, 255));
        jTextField2.setForeground(new java.awt.Color(0, 0, 0));
        jTextField2.setBorder(new FlatLineBorder(new Insets(7, 7, 7, 7), new Color(238, 238, 238), 1, 15));
        jTextField2.setFocusable(false);
        jPanel1.add(jTextField2);
        jTextField2.setBounds(210, 110, 212, 33);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Hóa đơn bảo hành");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(490, 110, 170, 33);

        jComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox1.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang xử lý", "Đã xử lý", "Đang Giao", "Đã Giao" }));
        jComboBox1.setBorder(new FlatLineBorder(new Insets(7, 7, 7, 7), new Color(238, 238, 238), 1, 15));
        jComboBox1.setFocusable(false);
        jPanel1.add(jComboBox1);
        jComboBox1.setBounds(670, 20, 190, 33);

        jComboBox2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox2.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã KH - Tên KH" }));
        jComboBox2.setBorder(new FlatLineBorder(new Insets(7, 7, 7, 7), new Color(238, 238, 238), 1, 15));
        jComboBox2.setFocusable(false);
        jPanel1.add(jComboBox2);
        jComboBox2.setBounds(670, 110, 290, 33);

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(720, 600));
        jPanel3.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("CHI TIẾT HÓA ĐƠN");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(50, 20, 320, 30);

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Seri", "Tên sản phẩm", "Màu", "Giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        jTable1.setFillsViewportHeight(true);
        jTable1.setFocusable(false);
        jTable1.setGridColor(new java.awt.Color(220, 220, 220));
        jTable1.setRowHeight(40);
        jTable1.setShowHorizontalLines(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(170);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(310);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        jPanel3.add(jScrollPane1);
        jScrollPane1.setBounds(20, 60, 680, 390);

        jPanel2.add(jPanel3, java.awt.BorderLayout.WEST);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(280, 600));
        jPanel4.setLayout(null);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Thoát");
        jButton1.setFocusPainted(false);
        jPanel4.add(jButton1);
        jButton1.setBounds(150, 420, 100, 30);

        jButton2.setBackground(new java.awt.Color(61, 66, 190));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Cập nhật");
        jButton2.setFocusPainted(false);
        jPanel4.add(jButton2);
        jButton2.setBounds(20, 420, 110, 30);
        jButton2.setVisible(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Khách đã trả");
        jPanel4.add(jLabel3);
        jLabel3.setBounds(20, 150, 90, 20);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Thành tiền");
        jPanel4.add(jLabel7);
        jLabel7.setBounds(20, 70, 70, 20);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tổng số lượng");
        jPanel4.add(jLabel8);
        jLabel8.setBounds(20, 110, 100, 20);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Phương thức thanh toán");
        jLabel9.setAutoscrolls(true);
        jPanel4.add(jLabel9);
        jLabel9.setBounds(20, 230, 160, 20);

        jTextField3.setEditable(false);
        jTextField3.setBorder(new FlatLineBorder(new Insets(5, 5, 5, 5), new Color(238, 238, 238), 1, 15));
        jTextField3.setFocusable(false);
        jPanel4.add(jTextField3);
        jTextField3.setBounds(130, 140, 140, 30);

        jTextField4.setEditable(false);
        jTextField4.setBorder(new FlatLineBorder(new Insets(5, 5, 5, 5), new Color(238, 238, 238), 1, 15));
        jTextField4.setFocusable(false);
        jPanel4.add(jTextField4);
        jTextField4.setBounds(130, 60, 110, 30);

        jTextField5.setEditable(false);
        jTextField5.setBorder(new FlatLineBorder(new Insets(5, 5, 5, 5), new Color(238, 238, 238), 1, 15));
        jTextField5.setFocusable(false);
        jPanel4.add(jTextField5);
        jTextField5.setBounds(130, 100, 80, 30);

        jTextField6.setEditable(false);
        jTextField6.setBorder(new FlatLineBorder(new Insets(5, 5, 5, 5), new Color(238, 238, 238), 1, 15));
        jTextField6.setFocusable(false);
        jPanel4.add(jTextField6);
        jTextField6.setBounds(20, 270, 250, 30);

        jPanel2.add(jPanel4, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatMacLightLaf());
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChiTietHoaDon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
