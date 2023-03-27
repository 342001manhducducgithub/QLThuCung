/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class frmThongKe extends javax.swing.JFrame {

    private String sql = "select * from ThongKe";
    private boolean add=false, change=false;
    private boolean leapYear=false,Year=false,Month=false,Day=false;
    public frmThongKe() {
        initComponents();
        //setResizable(false);
        setLocationRelativeTo(this);
        cbxYear.setValue(Double.parseDouble(new SimpleDateFormat("yyyy").format(new java.util.Date())));
        checkYear(); 
        addDay();
        LoadData(sql);
        Refresh();
    }

    private void LoadData (String sql){
        int count=0;
        long tongTien=0;
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        try{
            String[] arry={"Tên TC","Tổng tiền","Tiền nhận của khách","Tiền thừa","Tên nhân viên","Ngày Bán","Thời gian"};
            DefaultTableModel model=new DefaultTableModel(arry,0);
            DBAccess acc=new DBAccess();
            ResultSet rs = acc.Query(sql); 
            
            while(rs.next()){
                Vector vector=new Vector();
                vector.add(rs.getString("tenTC").trim());
                vector.add(rs.getString("tongTien").trim());
                vector.add(rs.getString("tienKH").trim());
                vector.add(rs.getString("tienThua").trim());
                vector.add(rs.getString("tenNV").trim());
                vector.add(rs.getString("ngay").trim());
                vector.add(rs.getString("thoiGian").trim());
                model.addRow(vector);
                String []s=rs.getString("tongTien").trim().split("\\s");
                tongTien=convertedToNumbers(s[0])+tongTien;
                count++;
            }
            tableThongke.setModel(model);
            lblSoHoaDon.setText(String.valueOf(count));
            lblTongDoanhThu.setText(formatter.format(tongTien)+" "+"VND");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    private long convertedToNumbers(String s){
        String number="";
        String []array=s.replace(","," ").split("\\s");
        for(String i:array){
            number=number.concat(i);
        }
        return Long.parseLong(number);
    }
    private void checkYear(){
        if(Double.parseDouble(String.valueOf(cbxYear.getValue()))%4==0 && Double.parseDouble(String.valueOf(cbxYear.getValue()))%100!=0 || Double.parseDouble(String.valueOf(cbxYear.getValue()))%400==0 ){
            leapYear=true;
        }
        else leapYear=false;
    }
    private void Refresh(){
        Year=false;
        Month=false;
        Day=false;
        cbxDay.setEnabled(false);
        cbxMonth.setEnabled(false);
        cbxYear.setEnabled(false);
        cbxMonth.setSelectedIndex(0);
        cbxDay.setSelectedIndex(0);
    }
    private void checkOption(){
        Refresh();
        if(radXemnam.isSelected()){
            cbxYear.setEnabled(true);
            Year=true;
        }
        else
        if(radXemthang.isSelected()){
            cbxMonth.setEnabled(true);
            cbxYear.setEnabled(true);
            Month=true;
        }
        else
        if(radXemngay.isSelected()){
            cbxDay.setEnabled(true);
            cbxMonth.setEnabled(true);
            cbxYear.setEnabled(true);
            Day=true;
        } 
    }
    private void addDay(){
        cbxDay.setEnabled(true);
        String []day={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        switch(Integer.parseInt(cbxMonth.getSelectedItem().toString())){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                cbxDay.removeAllItems();
                for(String i:day){
                    cbxDay.addItem(i);
                }
                break;
            
            case 4:
            case 6:
            case 9:
            case 11:
                cbxDay.removeAllItems();
                for(int i=0;i<day.length-1;i++){
                    cbxDay.addItem(day[i]);
                }
                break;
            
            case 2:
                cbxDay.removeAllItems();
                if(leapYear==true){
                    for(int i=0;i<day.length-2;i++){
                        cbxDay.addItem(day[i]);
                    }
                }
                else{
                    for(int i=0;i<day.length-3;i++){
                        cbxDay.addItem(day[i]);
                    }
                }
                break;
        }
    }
    private double getDay(String s){
        String []arry=s.replace("/"," ").split("\\s");
        return  Double.parseDouble(arry[arry.length-3]);
    }
    
    private double getMonth(String s){
        String []arry=s.replace("/"," ").split("\\s");
        return  Double.parseDouble(arry[arry.length-2]);
    }
    
    private double getYear(String s){
        String []arry=s.replace("/"," ").split("\\s");
        return  Double.parseDouble(arry[arry.length-1]);
    }
    private void FindDay(){
        int count=0;
        long tongTien=0;
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        try{
            String[] arry={"Tên TC","Tổng tiền","Tiền nhận của khách","Tiền thừa","Tên nhân viên","Ngày Bán","Thời gian"};
            DefaultTableModel modle=new DefaultTableModel(arry,0);
            DBAccess acc=new DBAccess();
            ResultSet rs = acc.Query(sql);
            while(rs.next()){
                if(getDay(rs.getString("ngay"))==Double.parseDouble(cbxDay.getSelectedItem().toString()) && getMonth(rs.getString("ngay"))==Double.parseDouble(cbxMonth.getSelectedItem().toString()) && getYear(rs.getString("ngay"))==Double.parseDouble(cbxYear.getValue().toString())){
                    Vector vector=new Vector();
                    vector.add(rs.getString("tenTC").trim());
                    vector.add(rs.getString("tongTien").trim());
                    vector.add(rs.getString("tienKH").trim());
                    vector.add(rs.getString("tienThua").trim());
                    vector.add(rs.getString("tenNV").trim());
                    vector.add(rs.getString("ngay").trim());
                    vector.add(rs.getString("thoiGian").trim());
                    modle.addRow(vector);
                    String []s=rs.getString("tongTien").trim().split("\\s");
                    tongTien=convertedToNumbers(s[0])+tongTien;
                    count++;
                }
            }
            tableThongke.setModel(modle);
            lblSoHoaDon.setText(String.valueOf(count));
            lblTongDoanhThu.setText(formatter.format(tongTien)+" "+"VND");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    private void FindMonth(){
        int count=0;
        long tongTien=0;
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        try{
            String[] arry={"Tên TC","Tổng tiền","Tiền nhận của khách","Tiền thừa","Tên nhân viên","Ngày Bán","Thời gian"};
            DefaultTableModel modle=new DefaultTableModel(arry,0);
            DBAccess acc=new DBAccess();
            ResultSet rs = acc.Query(sql);
            
            while(rs.next()){
                if(getMonth(rs.getString("ngay"))==Double.parseDouble(cbxMonth.getSelectedItem().toString()) && getYear(rs.getString("ngay"))==Double.parseDouble(cbxYear.getValue().toString())){
                    Vector vector=new Vector();
                    vector.add(rs.getString("tenTC").trim());
                    vector.add(rs.getString("tongTien").trim());
                    vector.add(rs.getString("tienKH").trim());
                    vector.add(rs.getString("tienThua").trim());
                    vector.add(rs.getString("tenNV").trim());
                    vector.add(rs.getString("ngay").trim());
                    vector.add(rs.getString("thoiGian").trim());
                    modle.addRow(vector);
                    String []s=rs.getString("tongTien").trim().split("\\s");
                    tongTien=convertedToNumbers(s[0])+tongTien;
                    count++;
                }
            }
            tableThongke.setModel(modle);
            lblSoHoaDon.setText(String.valueOf(count));
            lblTongDoanhThu.setText(formatter.format(tongTien)+" "+"VND");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    private void  FindYear(){
        int count=0;
        long tongTien=0;
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        try{
            String[] arry={"Tên TC","Tổng tiền","Tiền nhận của khách","Tiền thừa","Tên nhân viên","Ngày Bán","Thời gian"};
            DefaultTableModel modle=new DefaultTableModel(arry,0);
            DBAccess acc=new DBAccess();
            ResultSet rs = acc.Query(sql);
            while(rs.next()){
                if(getYear(rs.getString("ngay"))==Double.parseDouble(cbxYear.getValue().toString())){
                    Vector vector=new Vector();
                    vector.add(rs.getString("tenTC").trim());
                    vector.add(rs.getString("tongTien").trim());
                    vector.add(rs.getString("tienKH").trim());
                    vector.add(rs.getString("tienThua").trim());
                    vector.add(rs.getString("tenNV").trim());
                    vector.add(rs.getString("ngay").trim());
                    vector.add(rs.getString("thoiGian").trim());
                    modle.addRow(vector);
                    String []s=rs.getString("tongTien").trim().split("\\s");
                    tongTien=convertedToNumbers(s[0])+tongTien;
                    count++;
                }
            }
            tableThongke.setModel(modle);
            lblSoHoaDon.setText(String.valueOf(count));
            lblTongDoanhThu.setText(formatter.format(tongTien)+" "+"VND");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableThongke = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lblSoHoaDon = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTongDoanhThu = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        radXemngay = new javax.swing.JRadioButton();
        radXemthang = new javax.swing.JRadioButton();
        radXemnam = new javax.swing.JRadioButton();
        cbxDay = new javax.swing.JComboBox<>();
        cbxMonth = new javax.swing.JComboBox<>();
        cbxYear = new javax.swing.JSpinner();
        btnBack = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        btnFind = new javax.swing.JButton();
        lbTrangthai = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1320, 460));

        tableThongke.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "TenTC", "Tiền cần thanh toán", "Tiền KH đưa", "Tiền thừa", "Nhân viên nhận", "Ngày", "Giờ"
            }
        ));
        jScrollPane1.setViewportView(tableThongke);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Tổng số hóa đơn :");

        lblSoHoaDon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblSoHoaDon.setText("0");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Tổng tiền thu về :");

        lblTongDoanhThu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTongDoanhThu.setText("0 VNĐ");

        radXemngay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radXemngay.setText("Xem theo ngày");
        radXemngay.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radXemngayItemStateChanged(evt);
            }
        });

        radXemthang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radXemthang.setText("Xem theo tháng");
        radXemthang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radXemthangItemStateChanged(evt);
            }
        });

        radXemnam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radXemnam.setText("Xem theo năm");
        radXemnam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radXemnamItemStateChanged(evt);
            }
        });

        cbxMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cbxMonth.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbxMonthPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        btnBack.setText("Trở về");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbxDay, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(radXemngay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(radXemthang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxMonth, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(radXemnam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxYear)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radXemngay)
                    .addComponent(radXemthang)
                    .addComponent(radXemnam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbxYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbxDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(btnThoat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnFind.setText("Find");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        lbTrangthai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTrangthai.setText("Trạng thái");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(lbTrangthai)))
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSoHoaDon)
                .addGap(198, 198, 198)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTongDoanhThu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(191, 191, 191))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblSoHoaDon)
                            .addComponent(jLabel2)
                            .addComponent(lblTongDoanhThu)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(219, 219, 219))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lbTrangthai)
                                .addGap(50, 50, 50)))
                        .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        if(Day==true)
            FindDay();
        else
        if(Month==true)
            FindMonth();
        else
        if(Year==true)
            FindYear();
        else lbTrangthai.setText("Bạn cần chọn phương thức tìm kiếm!");
    }//GEN-LAST:event_btnFindActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        LoadData(sql);
    }//GEN-LAST:event_btnBackActionPerformed

    private void radXemngayItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radXemngayItemStateChanged
        checkOption();
    }//GEN-LAST:event_radXemngayItemStateChanged

    private void radXemthangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radXemthangItemStateChanged
        checkOption();
    }//GEN-LAST:event_radXemthangItemStateChanged

    private void radXemnamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radXemnamItemStateChanged
        checkOption();
    }//GEN-LAST:event_radXemnamItemStateChanged

    private void cbxMonthPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbxMonthPopupMenuWillBecomeInvisible
        checkYear();
        if(Day==true)   addDay();
        else    return;
    }//GEN-LAST:event_cbxMonthPopupMenuWillBecomeInvisible

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
            java.util.logging.Logger.getLogger(frmThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmThongKe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnThoat;
    private javax.swing.JComboBox<String> cbxDay;
    private javax.swing.JComboBox<String> cbxMonth;
    private javax.swing.JSpinner cbxYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTrangthai;
    private javax.swing.JLabel lblSoHoaDon;
    private javax.swing.JLabel lblTongDoanhThu;
    private javax.swing.JRadioButton radXemnam;
    private javax.swing.JRadioButton radXemngay;
    private javax.swing.JRadioButton radXemthang;
    private javax.swing.JTable tableThongke;
    // End of variables declaration//GEN-END:variables
}
