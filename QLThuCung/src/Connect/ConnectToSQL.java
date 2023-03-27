/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class ConnectToSQL {
    public Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;Database=QLThuCung_1;user=lakiet;password=123456";
            Connection con = DriverManager.getConnection(url);
            return con;
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(),"Loi",JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
    }
}
