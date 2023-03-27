/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect;

import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Admin
 */
public class frmDatThuCung extends javax.swing.JFrame {

    private String sql="SELECT * FROM DatThuCung";
    private boolean add=false, change=false;
    public frmDatThuCung() {
        initComponents();
        //setResizable(false);
        setLocationRelativeTo(this);
        LoadData(sql);
        Disabled();
    }
    private void LoadData(String sql){
        try{
            String[] arry={"Mã DTC","Tên KH","SDT","Tên TC","Đặc điểm","Nơi NK","TG","Ngày","Thanh toán","Ghi chú"};
            DefaultTableModel model=new DefaultTableModel(arry,0);
            DBAccess acc=new DBAccess();
            ResultSet rs = acc.Query(sql); 
            
            while(rs.next()){
                Vector vector=new Vector();
                vector.add(rs.getString("maDTC").trim());
                vector.add(rs.getString("tenKH").trim());
                vector.add(rs.getString("sdt").trim());
                vector.add(rs.getString("tenTC").trim());
                vector.add(rs.getString("dacDiem").trim());
                vector.add(rs.getString("noiNK").trim());
                vector.add(rs.getString("thoiGian").trim());
                vector.add(rs.getString("ngay").trim());
                vector.add(rs.getString("thanhToan").trim());
                vector.add(rs.getString("ghiChu").trim());
                
                model.addRow(vector);
            }
            tableDatTC.setModel(model);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    private void LoadHour(){
        cboHour.removeAllItems();
        
        for(int i=1;i <=23;i++){
            cboHour.addItem(String.valueOf(i));
        }
    }
    private void LoadMinute(){
        cboMinute.removeAllItems();
        
        for(int i=0;i <= 59;i++){
            if(i<10){
                cboMinute.addItem(String.valueOf("0"+i));
            }
            else cboMinute.addItem(String.valueOf(i));
  
        }
    }
    private String thanhtoan(){
        if(radNo.isSelected()){
            return radNo.getText();
        }else
            return radDaThanhToan.getText();
    }
    private void checkThanhtoan(String tt){
        if(tt.equals("Chưa")){
            radNo.setSelected(true);
        }else
            radDaThanhToan.setSelected(true);
    }
    private boolean check(){
        try {
            DBAccess acc=new DBAccess();
            ResultSet rs=acc.Query(sql);
            while(rs.next()){
                if(rs.getString("maDTC").toString().trim().equals(txtDTC.getText())){
                    lbTrangthai.setText("Mã đặt thú cưng đã tồn tại");
                    return false;
                }  
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
    private boolean checkNull(){
        if(txtDTC.getText().equals(""))
        {
            lbTrangthai.setText("Bạn chưa nhập mã đặt thú cưng");
            return false;
        }else
        if(txtTenKH.getText().equals(""))
        {
            lbTrangthai.setText("Bạn chưa nhập tên khách hàng");
            return false;
        }else
        if(txtSDT.getText().equals(""))
        {
            lbTrangthai.setText("Bạn chưa nhập số điện thoại");
            return false;
        }else
        if(txtTenTC.getText().equals(""))
        {
            lbTrangthai.setText("Bạn chưa nhập tên thú cưng");
            return false;
        }else
        if(txtDacDiem.getText().equals(""))
        {
            lbTrangthai.setText("Bạn chưa nhập đặc điểm thú cưng");
            return false;
        }else
        if(txtNoiNK.getText().equals(""))
        {
            lbTrangthai.setText("Bạn chưa nhập nơi nhập khẩu");
            return false;
        }else
        if(((JTextField)txtDay.getDateEditor().getUiComponent()).getText().equals("")){
            lbTrangthai.setText("Chọn ngày nhận thú cưng");
            return false;
        }else
        if(radNo.isSelected()==false && radDaThanhToan.isSelected()==false){
            lbTrangthai.setText("Bạn chưa chọn tình trạng thanh toán!");
            return false;
        }
        return true;
    }
    private void addMaDTC(){
        if(checkNull()){
            String maDTC= txtDTC.getText();
            String tenKH = txtTenKH.getText();
            String sdt = txtSDT.getText();
            String tenTC = txtTenTC.getText();
            String dacDiem = txtDacDiem.getText();
            String noiNK = txtNoiNK.getText();
            String thoiGian= cboHour.getSelectedItem()+lbl2Cham.getText()+cboMinute.getSelectedItem();
            String ngay =((JTextField)txtDay.getDateEditor().getUiComponent()).getText();
            String Note = txtNote.getText();
            DBAccess acc=new DBAccess();
            int kq= acc.Update("insert into DatThuCung values('"+maDTC+"',N'"+tenKH+"','"+sdt+"',N'"+tenTC+"',N'"+dacDiem+"',N'"+noiNK+"','"+thoiGian+"','"+ngay+"',N'"+thanhtoan()+"',N'"+Note+"')");
            // reset();
            LoadData(sql);
            // Disabled();
            lbTrangthai.setText("Thêm đơn đặt thú cưng thành công!");
        }      
    }      
    private void editMaDTC(){
        if(checkNull()){
            int click=tableDatTC.getSelectedRow();
            TableModel model=tableDatTC.getModel();
            String maDTC= txtDTC.getText();
            String tenKH= txtTenKH.getText();
            String sdt= txtSDT.getText();
            String tenTC= txtTenTC.getText();
            String dacDiem= txtDacDiem.getText();
            String noiNK= txtNoiNK.getText();
            String thoiGian= cboHour.getSelectedItem()+lbl2Cham.getText()+cboMinute.getSelectedItem();
            String ngay =((JTextField)txtDay.getDateEditor().getUiComponent()).getText();
            String Note = txtNote.getText();
            DBAccess acc=new DBAccess();
            int kq= acc.Update("UPDATE DatThuCung SET maDTC='"+maDTC+"', tenKH=N'"+tenKH+"',sdt='"+sdt+"',tenTC=N'"+tenTC+"',dacDiem=N'"+dacDiem+"',noiNK=N'"+noiNK+"',thoiGian='"+thoiGian+"',ngay='"+ngay+"',thanhToan=N'"+thanhtoan()+"',ghiChu=N'"+Note+"' WHERE maDTC='"+model.getValueAt(click, 0)+"'");
            reset();
            LoadData(sql);
            Disabled();
            lbTrangthai.setText("Sửa thông tin  thành công!");
        }
    }
    private void Enabled(){
        txtDTC.setEnabled(true);
        txtTenKH.setEnabled(true);
        txtSDT.setEnabled(true);
        txtTenTC.setEnabled(true);
        txtDacDiem.setEnabled(true);
        txtNoiNK.setEnabled(true);
        cboHour.setEnabled(true);
        cboMinute.setEnabled(true);
        txtDay.setEnabled(true);
        radNo.setEnabled(true);
        radDaThanhToan.setEnabled(true);
        txtNote.setEnabled(true);
    }
    private void Disabled(){
        txtDTC.setEnabled(false);
        txtTenKH.setEnabled(false);
        txtSDT.setEnabled(false);
        txtTenTC.setEnabled(false);
        txtDacDiem.setEnabled(false);
        txtNoiNK.setEnabled(false);
        cboHour.setEnabled(false);
        cboMinute.setEnabled(false);
        txtDay.setEnabled(false);
        radNo.setEnabled(false);
        radDaThanhToan.setEnabled(false);
        txtNote.setEnabled(false);
    }
    private void reset(){
        LoadHour();
        LoadMinute();
        txtDTC.setText("");
        txtTenKH.setText("");
        txtSDT.setText("");
        txtTenTC.setText("");
        txtDacDiem.setText("");
        txtNoiNK.setText("");
        cboHour.setSelectedIndex(0);
        cboMinute.setSelectedIndex(0);
        ((JTextField)txtDay.getDateEditor().getUiComponent()).setText("");
        radNo.setSelected(false);
        radDaThanhToan.setSelected(false);
        txtNote.setText("");
        btnThem.setEnabled(true);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
        btnLuu.setEnabled(false);
        btnThoat.setEnabled(false);
        lbTrangthai.setText("Trạng thái");  
    }
    private String cutChar(String arry){
        return arry.replaceAll("\\D+","");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDatTC = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtDacDiem = new javax.swing.JTextField();
        txtNoiNK = new javax.swing.JTextField();
        txtTenTC = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        cboHour = new javax.swing.JComboBox<>();
        txtTenKH = new javax.swing.JTextField();
        txtDTC = new javax.swing.JTextField();
        lbl2Cham = new javax.swing.JLabel();
        cboMinute = new javax.swing.JComboBox<>();
        txtDay = new com.toedter.calendar.JDateChooser();
        txtNote = new javax.swing.JTextField();
        radNo = new javax.swing.JRadioButton();
        radDaThanhToan = new javax.swing.JRadioButton();
        lbTrangthai = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1043, 570));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("ĐẶT THÚ CƯNG");

        tableDatTC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "TenKH", "SDT", "TenTC", "Đặc điểm"
            }
        ));
        tableDatTC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDatTCMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDatTC);

        jLabel2.setText("MãDTC:");

        jLabel3.setText("TênKH:");

        jLabel4.setText("SDT:");

        jLabel5.setText("TênTC:");

        jLabel6.setText("Đặc điểm:");

        jLabel7.setText("Nơi NK:");

        jLabel8.setText("Thời gian:");

        jLabel9.setText("Ngày");

        jLabel10.setText("Thanh toán");

        jLabel11.setText("Ghi chú");

        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDTKeyReleased(evt);
            }
        });

        lbl2Cham.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl2Cham.setText(":");

        cboMinute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMinuteActionPerformed(evt);
            }
        });

        radNo.setText("Chưa");
        radNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radNoActionPerformed(evt);
            }
        });

        radDaThanhToan.setText("Đã thanh toán");
        radDaThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDaThanhToanActionPerformed(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(cboHour, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl2Cham, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cboMinute, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radNo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radDaThanhToan))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtDTC)
                                    .addComponent(txtTenKH)
                                    .addComponent(txtSDT)
                                    .addComponent(txtTenTC)
                                    .addComponent(txtDacDiem)
                                    .addComponent(txtNoiNK, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(txtNote, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(69, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(txtDay, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTenTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDacDiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNoiNK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl2Cham)
                    .addComponent(cboMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(radNo)
                    .addComponent(radDaThanhToan))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbTrangthai.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTrangthai.setText("Trạng thái");

        btnThem.setIcon(new javax.swing.ImageIcon("F:\\A. Học\\Đồ án java\\Test\\QLCF\\src\\Photos\\plus.png")); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setIcon(new javax.swing.ImageIcon("F:\\A. Học\\Đồ án java\\Test\\QLCF\\src\\Photos\\edit.png")); // NOI18N
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon("F:\\A. Học\\Đồ án java\\Test\\QLCF\\src\\Photos\\delete.png")); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLuu.setIcon(new javax.swing.ImageIcon("F:\\A. Học\\Đồ án java\\Test\\QLCF\\src\\Photos\\floppy-disk (1).png")); // NOI18N
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
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
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThoat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(370, 370, 370)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbTrangthai)
                .addGap(439, 439, 439))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTrangthai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboMinuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMinuteActionPerformed
        
    }//GEN-LAST:event_cboMinuteActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        reset();
        add=true;
        Enabled();
        LoadHour();
        LoadMinute();
        btnLuu.setEnabled(true);   
        btnThem.setEnabled(false);
        btnThoat.setEnabled(true);
    }//GEN-LAST:event_btnThemActionPerformed

    private void radNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radNoActionPerformed
        radNo.setSelected(true);
        radDaThanhToan.setSelected(false);
    }//GEN-LAST:event_radNoActionPerformed

    private void radDaThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDaThanhToanActionPerformed
        radDaThanhToan.setSelected(true);
        radNo.setSelected(false);
    }//GEN-LAST:event_radDaThanhToanActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if(add==true){
            if(check()){
                addMaDTC();
            }
        }
        else{
            if(change==true)
                editMaDTC();
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void tableDatTCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDatTCMouseClicked
        cboHour.removeAllItems();
        cboMinute.removeAllItems();
        
        int click=tableDatTC.getSelectedRow();
        TableModel model=tableDatTC.getModel();
        
        txtDTC.setText(model.getValueAt(click, 0).toString());
        txtTenKH.setText(model.getValueAt(click, 1).toString());
        txtSDT.setText(model.getValueAt(click, 2).toString());
        txtTenTC.setText(model.getValueAt(click, 3).toString());
        txtDacDiem.setText(model.getValueAt(click, 4).toString());
        txtNoiNK.setText(model.getValueAt(click, 5).toString());
        String[] s=model.getValueAt(click, 6).toString().split(":");
        cboHour.addItem(s[0]);
        cboMinute.addItem(s[1]);
        ((JTextField)txtDay.getDateEditor().getUiComponent()).setText(model.getValueAt(click, 7).toString());
        checkThanhtoan(model.getValueAt(click, 8).toString());
        txtNote.setText(model.getValueAt(click, 9).toString());

        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
    }//GEN-LAST:event_tableDatTCMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        btnLuu.setEnabled(true); 
        int click=JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa đơn đặt thú cưng này không?", "Thông báo", 2);
        if(click==JOptionPane.YES_OPTION){
            
            String sqlDelete="DELETE FROM DatThuCung WHERE maDTC=N'"+txtDTC.getText()+"'";
            DBAccess acc=new DBAccess();
            int update=acc.Update(sqlDelete);
            
            reset();
            LoadData(sql);
            Disabled();
            lbTrangthai.setText("Xóa thành công đơn đặt thú cưng");
            btnThoat.setEnabled(true);
        }
        else reset();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        LoadHour();
        LoadMinute();

        add=false;
        change=true;
        Enabled();
        btnThem.setEnabled(false);
        btnXoa.setEnabled(false);
        btnSua.setEnabled(false);
        btnLuu.setEnabled(true);
        btnThoat.setEnabled(true);
    }//GEN-LAST:event_btnSuaActionPerformed

    private void txtSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyReleased
        txtSDT.setText(cutChar(txtSDT.getText()));
        if(txtSDT.getText().length()==11 || txtSDT.getText().length()==10 ){
            btnLuu.setEnabled(true);
            lbTrangthai.setText("Số điện thoại đã hợp lệ!!");
        }
        else
        if(txtSDT.getText().length()>11 || txtSDT.getText().length()<10){
            btnLuu.setEnabled(false);
            lbTrangthai.setText("Số điện thoại không được ít hơn 10 số hoặc vượt quá 11 số!!");
        }
    }//GEN-LAST:event_txtSDTKeyReleased

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
            java.util.logging.Logger.getLogger(frmDatThuCung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmDatThuCung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmDatThuCung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmDatThuCung.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmDatThuCung().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboHour;
    private javax.swing.JComboBox<String> cboMinute;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTrangthai;
    private javax.swing.JLabel lbl2Cham;
    private javax.swing.JRadioButton radDaThanhToan;
    private javax.swing.JRadioButton radNo;
    private javax.swing.JTable tableDatTC;
    private javax.swing.JTextField txtDTC;
    private javax.swing.JTextField txtDacDiem;
    private com.toedter.calendar.JDateChooser txtDay;
    private javax.swing.JTextField txtNoiNK;
    private javax.swing.JTextField txtNote;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenTC;
    // End of variables declaration//GEN-END:variables
}
