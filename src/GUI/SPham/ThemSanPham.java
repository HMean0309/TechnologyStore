package GUI.SPham;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.ui.FlatLineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class ThemSanPham extends javax.swing.JFrame {

    public ThemSanPham() {
        setTitle("Thêm sản phẩm");
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        customInit();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelLeft = new javax.swing.JPanel();
        Panelimage = new javax.swing.JPanel();
        lbImage = new javax.swing.JLabel();
        btnChooseImage = new javax.swing.JButton();
        PanelColor = new javax.swing.JPanel();
        lbTableColor = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblColor = new javax.swing.JTable();
        PanelRight = new javax.swing.JPanel();
        Panelinfor = new javax.swing.JPanel();
        lbMaSP = new javax.swing.JLabel();
        txtMaSp = new javax.swing.JTextField();
        lbTenSP = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        lbLoai = new javax.swing.JLabel();
        cbLoai = new javax.swing.JComboBox<>();
        lbBaoHanh = new javax.swing.JLabel();
        txtBaoHanh = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        PanelChooseColor = new javax.swing.JPanel();
        lbColor = new javax.swing.JLabel();
        txtColor = new javax.swing.JTextField();
        PanelButton = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        PanelButton2 = new javax.swing.JPanel();
        btnUpdate = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 600));

        PanelLeft.setBackground(new java.awt.Color(255, 255, 255));
        PanelLeft.setPreferredSize(new java.awt.Dimension(300, 600));
        PanelLeft.setLayout(new java.awt.BorderLayout(0, 10));

        Panelimage.setBackground(new java.awt.Color(255, 255, 255));
        Panelimage.setPreferredSize(new java.awt.Dimension(300, 270));

        lbImage.setBackground(new java.awt.Color(255, 255, 255));
        lbImage.setIcon(new FlatSVGIcon("GUI/icon/image.svg"));
        lbImage.setPreferredSize(new java.awt.Dimension(240, 200));

        btnChooseImage.setBackground(new java.awt.Color(162, 162, 162));
        btnChooseImage.setText("Chọn File Ảnh");
        btnChooseImage.setFocusable(false);

        javax.swing.GroupLayout PanelimageLayout = new javax.swing.GroupLayout(Panelimage);
        Panelimage.setLayout(PanelimageLayout);
        PanelimageLayout.setHorizontalGroup(
            PanelimageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelimageLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(PanelimageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChooseImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelimageLayout.setVerticalGroup(
            PanelimageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelimageLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lbImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnChooseImage, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        PanelLeft.add(Panelimage, java.awt.BorderLayout.PAGE_START);

        PanelColor.setBackground(new java.awt.Color(255, 255, 255));
        PanelColor.setPreferredSize(new java.awt.Dimension(300, 320));
        PanelColor.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5));

        lbTableColor.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbTableColor.setForeground(new java.awt.Color(0, 0, 0));
        lbTableColor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTableColor.setText("Bảng Màu Lựa Chọn");
        lbTableColor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbTableColor.setMaximumSize(new java.awt.Dimension(127, 20));
        lbTableColor.setMinimumSize(new java.awt.Dimension(127, 20));
        lbTableColor.setPreferredSize(new java.awt.Dimension(127, 20));
        PanelColor.add(lbTableColor);

        jScrollPane1.setFocusable(false);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(300, 250));
        jScrollPane1.setViewportView(tblColor);

        tblColor.setBackground(new java.awt.Color(255, 255, 255));
        tblColor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));
        tblColor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Màu sắc"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblColor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblColor.setFillsViewportHeight(true);
        tblColor.setFocusable(false);
        tblColor.setGridColor(new java.awt.Color(220, 220, 220));
        tblColor.setPreferredSize(new java.awt.Dimension(300, 180));
        tblColor.setRowHeight(37);
        tblColor.setShowGrid(false);
        tblColor.setShowHorizontalLines(true);
        tblColor.setShowVerticalLines(true);
        jScrollPane1.setViewportView(tblColor);
        if (tblColor.getColumnModel().getColumnCount() > 0) {
            tblColor.getColumnModel().getColumn(0).setResizable(false);
        }

        PanelColor.add(jScrollPane1);

        PanelLeft.add(PanelColor, java.awt.BorderLayout.LINE_START);

        getContentPane().add(PanelLeft, java.awt.BorderLayout.WEST);

        PanelRight.setBackground(new java.awt.Color(0, 0, 0));
        PanelRight.setPreferredSize(new java.awt.Dimension(600, 600));
        PanelRight.setLayout(new java.awt.BorderLayout());

        Panelinfor.setBackground(new java.awt.Color(255, 255, 255));
        Panelinfor.setPreferredSize(new java.awt.Dimension(600, 300));

        lbMaSP.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        lbMaSP.setForeground(new java.awt.Color(0, 0, 0));
        lbMaSP.setText("Mã sản phẩm");

        txtMaSp.setEditable(false);
        txtMaSp.setBackground(new java.awt.Color(220, 220, 220));
        txtMaSp.setBorder(new FlatLineBorder(new Insets(5, 5, 5, 5), new Color(146, 143, 143), 1, 15));
        txtMaSp.setFocusable(false);
        txtMaSp.setSelectionColor(new java.awt.Color(146, 143, 143));

        lbTenSP.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        lbTenSP.setForeground(new java.awt.Color(0, 0, 0));
        lbTenSP.setText("Tên sản phẩm");

        txtTenSP.setBackground(new java.awt.Color(255, 255, 255));
        txtTenSP.setForeground(new java.awt.Color(0, 0, 0));
        txtTenSP.setBorder(new FlatLineBorder(new Insets(5, 5, 5, 5), new Color(238, 238, 238), 1, 15));
        txtTenSP.setSelectionColor(new java.awt.Color(0, 0, 0));

        lbLoai.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        lbLoai.setForeground(new java.awt.Color(0, 0, 0));
        lbLoai.setText("Loại");

        cbLoai.setBackground(new java.awt.Color(255, 255, 255));
        cbLoai.setForeground(new java.awt.Color(0, 0, 0));
        cbLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbLoai.setBorder(new FlatLineBorder(new Insets(5, 5, 5, 5), new Color(238, 238, 238), 1, 15));
        cbLoai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbLoai.setFocusable(false);
        cbLoai.setName(""); // NOI18N

        lbBaoHanh.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        lbBaoHanh.setForeground(new java.awt.Color(0, 0, 0));
        lbBaoHanh.setText("Bảo hành");

        txtBaoHanh.setBackground(new java.awt.Color(255, 255, 255));
        txtBaoHanh.setForeground(new java.awt.Color(0, 0, 0));
        txtBaoHanh.setBorder(new FlatLineBorder(new Insets(5, 5, 5, 5), new Color(238, 238, 238), 1, 15));
        txtBaoHanh.setSelectionColor(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Tên sản phẩm không hợp lệ");

        javax.swing.GroupLayout PanelinforLayout = new javax.swing.GroupLayout(Panelinfor);
        Panelinfor.setLayout(PanelinforLayout);
        PanelinforLayout.setHorizontalGroup(
            PanelinforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelinforLayout.createSequentialGroup()
                .addGroup(PanelinforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelinforLayout.createSequentialGroup()
                        .addContainerGap(19, Short.MAX_VALUE)
                        .addGroup(PanelinforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbTenSP)
                            .addComponent(lbLoai)
                            .addComponent(lbMaSP, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)))
                    .addGroup(PanelinforLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbBaoHanh)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelinforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(PanelinforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtTenSP)
                        .addComponent(txtMaSp)
                        .addComponent(cbLoai, 0, 320, Short.MAX_VALUE)
                        .addComponent(txtBaoHanh)))
                .addGap(120, 120, 120))
        );
        PanelinforLayout.setVerticalGroup(
            PanelinforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelinforLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(PanelinforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMaSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaSp, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelinforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTenSP)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(PanelinforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelinforLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(lbLoai))
                    .addGroup(PanelinforLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(PanelinforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbBaoHanh)
                    .addComponent(txtBaoHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(136, 136, 136))
        );

        PanelRight.add(Panelinfor, java.awt.BorderLayout.NORTH);

        PanelChooseColor.setBackground(new java.awt.Color(255, 255, 255));
        PanelChooseColor.setPreferredSize(new java.awt.Dimension(600, 200));

        lbColor.setBackground(new java.awt.Color(255, 255, 255));
        lbColor.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        lbColor.setForeground(new java.awt.Color(0, 0, 0));
        lbColor.setText("Màu");
        lbColor.setFocusable(false);

        txtColor.setBackground(new java.awt.Color(255, 255, 255));
        txtColor.setForeground(new java.awt.Color(0, 0, 0));
        txtColor.setBorder(new FlatLineBorder(new Insets(5, 5, 5, 5), new Color(238, 238, 238), 1, 15));
        txtColor.setSelectionColor(new java.awt.Color(0, 0, 0));

        PanelButton.setBackground(new java.awt.Color(255, 255, 255));

        btnThem.setBackground(new java.awt.Color(84, 198, 137));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.setFocusable(false);
        btnThem.setPreferredSize(new java.awt.Dimension(100, 35));
        PanelButton.add(btnThem);

        btnSua.setBackground(new java.awt.Color(56, 63, 159));
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Sửa");
        btnSua.setFocusable(false);
        btnSua.setPreferredSize(new java.awt.Dimension(100, 35));
        PanelButton.add(btnSua);
        btnSua.setVisible(false);

        btnXoa.setBackground(new java.awt.Color(247, 108, 108));
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.setFocusable(false);
        btnXoa.setPreferredSize(new java.awt.Dimension(100, 35));
        PanelButton.add(btnXoa);
        btnXoa.setVisible(false);

        javax.swing.GroupLayout PanelChooseColorLayout = new javax.swing.GroupLayout(PanelChooseColor);
        PanelChooseColor.setLayout(PanelChooseColorLayout);
        PanelChooseColorLayout.setHorizontalGroup(
            PanelChooseColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelChooseColorLayout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(lbColor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PanelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );
        PanelChooseColorLayout.setVerticalGroup(
            PanelChooseColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelChooseColorLayout.createSequentialGroup()
                .addGroup(PanelChooseColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelChooseColorLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(PanelChooseColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbColor)
                            .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelChooseColorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(PanelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelRight.add(PanelChooseColor, java.awt.BorderLayout.CENTER);

        PanelButton2.setBackground(new java.awt.Color(255, 255, 255));
        PanelButton2.setPreferredSize(new java.awt.Dimension(600, 60));

        btnUpdate.setBackground(new java.awt.Color(0, 0, 0));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Cập nhật");
        btnUpdate.setFocusable(false);
        btnUpdate.setPreferredSize(new java.awt.Dimension(140, 40));

        btnExit.setBackground(new java.awt.Color(255, 255, 255));
        btnExit.setForeground(new java.awt.Color(0, 0, 0));
        btnExit.setText("Thoát");
        btnExit.setToolTipText("");
        btnExit.setFocusable(false);
        btnExit.setPreferredSize(new java.awt.Dimension(140, 40));

        javax.swing.GroupLayout PanelButton2Layout = new javax.swing.GroupLayout(PanelButton2);
        PanelButton2.setLayout(PanelButton2Layout);
        PanelButton2Layout.setHorizontalGroup(
            PanelButton2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelButton2Layout.createSequentialGroup()
                .addContainerGap(296, Short.MAX_VALUE)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PanelButton2Layout.setVerticalGroup(
            PanelButton2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelButton2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(PanelButton2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        PanelRight.add(PanelButton2, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(PanelRight, java.awt.BorderLayout.EAST);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void customInit(){
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(new Color(179,179,179));
                c.setForeground(Color.BLACK);
                setHorizontalAlignment(SwingConstants.CENTER);
                return c;
            }
        };
        
        TableColumn column = tblColor.getColumnModel().getColumn(0);
        column.setHeaderRenderer(headerRenderer);
    }
    public static void main(String args[]) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatMacLightLaf());
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThemSanPham().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelButton;
    private javax.swing.JPanel PanelButton2;
    private javax.swing.JPanel PanelChooseColor;
    private javax.swing.JPanel PanelColor;
    private javax.swing.JPanel PanelLeft;
    private javax.swing.JPanel PanelRight;
    private javax.swing.JPanel Panelimage;
    private javax.swing.JPanel Panelinfor;
    private javax.swing.JButton btnChooseImage;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbLoai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbBaoHanh;
    private javax.swing.JLabel lbColor;
    private javax.swing.JLabel lbImage;
    private javax.swing.JLabel lbLoai;
    private javax.swing.JLabel lbMaSP;
    private javax.swing.JLabel lbTableColor;
    private javax.swing.JLabel lbTenSP;
    private javax.swing.JTable tblColor;
    private javax.swing.JTextField txtBaoHanh;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextField txtMaSp;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
