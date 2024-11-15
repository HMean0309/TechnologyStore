package GUI.SPham;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.awt.Component;
import java.util.stream.IntStream;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;


public class DanhSachSanPham extends javax.swing.JFrame {

    
    public DanhSachSanPham() {
        setTitle("Danh sách sản phẩm");
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        customInit();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        PanelLoc = new javax.swing.JPanel();
        lbColor = new javax.swing.JLabel();
        cbColor = new javax.swing.JComboBox<>();
        lbStatus = new javax.swing.JLabel();
        cbStatus = new javax.swing.JComboBox<>();
        lbQuanity = new javax.swing.JLabel();
        txtQuanity = new javax.swing.JTextField();
        PanelTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDSSP = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        PanelLoc.setBackground(new java.awt.Color(255, 255, 255));
        PanelLoc.setPreferredSize(new java.awt.Dimension(900, 100));

        lbColor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbColor.setForeground(new java.awt.Color(0, 0, 0));
        lbColor.setText("Màu sắc");
        lbColor.setFocusable(false);

        cbColor.setBackground(new java.awt.Color(255, 255, 255));
        cbColor.setForeground(new java.awt.Color(0, 0, 0));
        cbColor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbColor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbColor.setFocusable(false);

        lbStatus.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbStatus.setForeground(new java.awt.Color(0, 0, 0));
        lbStatus.setText("Tình trạng");
        lbStatus.setFocusable(false);

        cbStatus.setBackground(new java.awt.Color(255, 255, 255));
        cbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Chưa bán", "Đã bán" }));
        cbStatus.setFocusable(false);

        lbQuanity.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbQuanity.setForeground(new java.awt.Color(0, 0, 0));
        lbQuanity.setText("Số lượng");
        lbQuanity.setFocusable(false);

        txtQuanity.setEditable(false);
        txtQuanity.setForeground(new java.awt.Color(0, 0, 0));
        txtQuanity.setFocusable(false);

        javax.swing.GroupLayout PanelLocLayout = new javax.swing.GroupLayout(PanelLoc);
        PanelLoc.setLayout(PanelLocLayout);
        PanelLocLayout.setHorizontalGroup(
            PanelLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLocLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(PanelLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbColor)
                    .addComponent(cbColor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80)
                .addGroup(PanelLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbStatus)
                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(105, 105, 105)
                .addGroup(PanelLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbQuanity)
                    .addComponent(txtQuanity, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        PanelLocLayout.setVerticalGroup(
            PanelLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLocLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbColor)
                    .addComponent(lbStatus)
                    .addComponent(lbQuanity))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbColor, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(txtQuanity)
                    .addComponent(cbStatus))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel1.add(PanelLoc, java.awt.BorderLayout.NORTH);

        jScrollPane1.setViewportView(tblDSSP);

        tblDSSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Seri", "Mã Phiếu Nhập", "Tình Trạng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDSSP.setFillsViewportHeight(true);
        tblDSSP.setFocusable(false);
        tblDSSP.setGridColor(new java.awt.Color(220, 220, 220));
        tblDSSP.setRowHeight(28);
        tblDSSP.setShowHorizontalLines(true);
        jScrollPane1.setViewportView(tblDSSP);
        if (tblDSSP.getColumnModel().getColumnCount() > 0) {
            tblDSSP.getColumnModel().getColumn(0).setResizable(false);
            tblDSSP.getColumnModel().getColumn(1).setResizable(false);
            tblDSSP.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout PanelTableLayout = new javax.swing.GroupLayout(PanelTable);
        PanelTable.setLayout(PanelTableLayout);
        PanelTableLayout.setHorizontalGroup(
            PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
            .addGroup(PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE))
        );
        PanelTableLayout.setVerticalGroup(
            PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
            .addGroup(PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
        );

        jPanel1.add(PanelTable, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

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
        
        IntStream.range(0, tblDSSP.getColumnModel().getColumnCount())
         .forEach(i -> tblDSSP.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer));
//        column.setHeaderRenderer(headerRenderer);
    }
    
    public static void main(String args[]) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatMacLightLaf());
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DanhSachSanPham().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelLoc;
    private javax.swing.JPanel PanelTable;
    private javax.swing.JComboBox<String> cbColor;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbColor;
    private javax.swing.JLabel lbQuanity;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JTable tblDSSP;
    private javax.swing.JTextField txtQuanity;
    // End of variables declaration//GEN-END:variables
}
