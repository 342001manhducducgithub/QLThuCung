/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Admin
 */
public class frmQLThuCung extends javax.swing.JFrame {

    private String sql="SELECT * FROM ThuCung";
    Connection con = null;
    private boolean add=false, change=false;
    public frmQLThuCung() {
        initComponents();
       // setResizable(false);
        setLocationRelativeTo(this);
        loadData(sql);
        Disable();
    }
    private void loadData(String sql){
        try{
            String[] arry={"Mã thú cưng","Tên thú cưng","Đặc điểm","Nơi nhập khẩu","Giá"};
            DefaultTableModel model=new DefaultTableModel(arry,0);
            DBAccess acc=new DBAccess();
            ResultSet rs = acc.Query(sql); 
            
            while(rs.next()){
                Vector vector=new Vector();
                vector.add(rs.getString("maTC").trim());
                vector.add(rs.getString("TenTC").trim());
                vector.add(rs.getString("dacDiem").trim());
                vector.add(rs.getString("noiNK").trim());
                vector.add(rs.getString("Gia").trim());
                
                model.addRow(vector);
            }
            tableTC.setModel(model);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    private void Enabled(){
        txtMaTC.setEnabled(true);
        txtTenTC.setEnabled(true);
        txtDacDiem.setEnabled(true);
        txtNNK.setEnabled(true);
        txtGia.setEnabled(true);
    }
    private void Disable(){
        txtMaTC.setEnabled(false);
        txtTenTC.setEnabled(false);
        txtDacDiem.setEnabled(false);
        txtNNK.setEnabled(false);
        txtGia.setEnabled(false);
    }
    private void reset(){
        add=false;
        change=false;
        txtMaTC.setText("");
        txtTenTC.setText("");
        txtDacDiem.setText("");
        txtNNK.setText("");
        txtGia.setText("");
        btnThem.setEnabled(true);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        btnThoat.setEnabled(false);
    }
    private boolean checkNull(){
        if(txtMaTC.getText().equals("")){
            lblTrangThai.setText("Bạn chưa nhập mã thú cưng!");
            return false;
        }
        else
        if(txtTenTC.getText().equals("")){
            lblTrangThai.setText("Bạn chưa nhập tên thú cưng");
            return false;
        }
        else   
        if(txtDacDiem.getText().equals("")){
            lblTrangThai.setText("Bạn chưa nhập đặc điểm!");
            return false;
        }
        else   
        if(txtNNK.getText().equals("")){
            lblTrangThai.setText("Bạn chưa nhập nơi nhập khẩu!");
            return false;
        }
        else   
        if(txtGia.getText().equals("")){
            lblTrangThai.setText("Bạn chưa nhập giá!");
            return false;
        }
        return true;
    }
    private boolean check(){
        try {
            DBAccess acc=new DBAccess();
            ResultSet rs=acc.Query(sql);
            while(rs.next()){
                if(rs.getString("maTC").toString().trim().equals(txtMaTC.getText())){
                    lblTrangThai.setText("Mã thú cưng bạn nhập đã tồn tại");
                    return false;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
    private void addTC(){
        if(checkNull()){
            String maThuCung=txtMaTC.getText();
            String tenThuCung=txtTenTC.getText();
            String maDacDiem= txtDacDiem.getText();
            String maNNK = txtNNK.getText();
            String giaTien = (txtGia.getText()+" "+"VNĐ");
            DBAccess acc=new DBAccess();
            int kq= acc.Update("insert into ThuCung values('"+maThuCung+"',N'"+tenThuCung+"',N'"+maDacDiem+"',N'"+maNNK+"','"+giaTien+"')");
            reset();
            loadData(sql);
            Disable();
            lblTrangThai.setText("Thêm thú cưng thành công!");
        }
    }
    private void editTC(){
        if(checkNull()){
            int click=tableTC.getSelectedRow();
            TableModel model=tableTC.getModel();
            String maThuCung=txtMaTC.getText();
            String tenThuCung=txtTenTC.getText();
            String maDacDiem= txtDacDiem.getText();
            String maNNK = txtNNK.getText();
            String giaTien = (txtGia.getText()+" "+"VNĐ");
            DBAccess acc=new DBAccess();
            int kq= acc.Update("UPDATE ThuCung SET maTC='"+maThuCung+"',tenTC=N'"+tenThuCung+"',dacDiem=N'"+maDacDiem+"',noiNK=N'"+maNNK+"',Gia='"+giaTien+"'WHERE maTC=N'"+model.getValueAt(click, 0)+"'");
            reset();
            loadData(sql);
            Disable();
            lblTrangThai.setText("Thay đổi thông tin thú cưng thành công");
        }
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaTC = new javax.swing.JTextField();
        txtTenTC = new javax.swing.JTextField();
        txtDacDiem = new javax.swing.JTextField();
        txtNNK = new javax.swing.JTextField();
        txtGia = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTC = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        lblTrangThai = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnLuu = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(373, 244));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Thông tin thú cưng");

        jLabel2.setText("Mã thú cưng:");

        jLabel3.setText("Tên thú cưng:");

        jLabel4.setText("Đặc điểm:");

        jLabel5.setText("Nơi nhập khẩu:");

        jLabel6.setText("Giá tiền:");

        txtTenTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenTCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(55, 55, 55)
                            .addComponent(jLabel1))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2)
                                .addComponent(jLabel4))
                            .addGap(22, 22, 22)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtTenTC, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtDacDiem, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                .addComponent(txtMaTC)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel6)
                            .addGap(50, 50, 50)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNNK)
                                .addComponent(txtGia, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDacDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNNK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 43, Short.MAX_VALUE))
        );

        tableTC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã thú cưng", "Tên thú cưng", "Đặc điểm", "Nơi nhập khẩu", "Giá tiền"
            }
        ));
        tableTC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableTCMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableTC);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel7.setText("QUẢN LÝ THÚ CƯNG");

        btnTimKiem.setIcon(new javax.swing.ImageIcon("F:\\A. Học\\Đồ án java\\Test\\QLCF\\src\\Photos\\search (1).png")); // NOI18N
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        lblTrangThai.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTrangThai.setText("Trạng thái");

        btnLuu.setIcon(new javax.swing.ImageIcon("F:\\A. Học\\Đồ án java\\Test\\QLCF\\src\\Photos\\floppy-disk.png")); // NOI18N
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnThem.setIcon(new javax.swing.ImageIcon("F:\\A. Học\\Đồ án java\\Test\\QLCF\\src\\Photos\\plus.png")); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon("F:\\A. Học\\Đồ án java\\Test\\QLCF\\src\\Photos\\delete.png")); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setIcon(new javax.swing.ImageIcon("F:\\A. Học\\Đồ án java\\Photos\\fix1.png")); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThoat.setIcon(new javax.swing.ImageIcon("F:\\A. Học\\Đồ án java\\Test\\QLCF\\src\\Photos\\x-button.png")); // NOI18N
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnThem)
                    .addComponent(btnSua))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnXoa)
                    .addComponent(btnThoat))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                            .addComponent(btnThoat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(168, 168, 168))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTrangThai)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTimKiem)
                            .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTrangThai)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        String sql = "SELECT * FROM ThuCung where maTC like N'%"+txtTimKiem.getText()+"%' or tenTC like N'%"+txtTimKiem.getText()+"%' or dacDiem like N'%"+txtTimKiem.getText()+"%' or noiNK like N'%"+txtTimKiem.getText()+"%' or Gia like '%"+txtTimKiem.getText()+"%'";
        loadData(sql);
        txtTimKiem.setText("");
        Disable();
        reset();
        btnThoat.setEnabled(true);
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
       reset();
       add=true;
       Enabled();
       btnThem.setEnabled(false);
       btnLuu.setEnabled(true);
       btnThoat.setEnabled(true);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int click=JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa thú cưng này hay không?", "Thông báo", 2);
        if(click==JOptionPane.YES_OPTION){
            
            String sqlDelete="DELETE FROM ThuCung WHERE maTC='"+txtMaTC.getText()+"'";
            DBAccess acc=new DBAccess();
            int update=acc.Update(sqlDelete);
            
            reset();
            loadData(sql);
            Disable();
            btnThoat.setEnabled(true);
        }
        else reset();             
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        add=false;
        change=true;
        Enabled();
        btnThem.setEnabled(false);
        btnXoa.setEnabled(false);
        btnSua.setEnabled(false);
        btnLuu.setEnabled(true);
        btnThoat.setEnabled(true);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
         if(add==true){
            if(check()){
                addTC();
            }
        }
        else{
            if(change==true)
                editTC();
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void tableTCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTCMouseClicked
        int click=tableTC.getSelectedRow();
        TableModel model=tableTC.getModel();
        
        txtMaTC.setText(model.getValueAt(click, 0).toString());
        txtTenTC.setText(model.getValueAt(click, 1).toString());
        txtDacDiem.setText(model.getValueAt(click, 2).toString());
        txtNNK.setText(model.getValueAt(click, 3).toString());
        
        String []s1=model.getValueAt(click,4).toString().split("\\s");
        txtGia.setText(s1[0]);
        
        btnThem.setEnabled(true);// luu ý
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        btnThoat.setEnabled(true);
                                      
    }//GEN-LAST:event_tableTCMouseClicked

    private void txtTenTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenTCActionPerformed

    }//GEN-LAST:event_txtTenTCActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        dispose();
    }//GEN-LAST:event_btnThoatActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmQLThuCung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmQLThuCung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmQLThuCung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmQLThuCung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmQLThuCung().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JTable tableTC;
    private javax.swing.JTextField txtDacDiem;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMaTC;
    private javax.swing.JTextField txtNNK;
    private javax.swing.JTextField txtTenTC;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
