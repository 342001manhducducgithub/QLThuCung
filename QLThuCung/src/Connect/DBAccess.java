/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public class DBAccess {
    private Connection con;
    private Statement stmt;
    public DBAccess(){
        try{
            ConnectToSQL mycon = new ConnectToSQL();
            con = mycon.getConnection();
            stmt = con.createStatement();
        }catch(Exception e){   
        }
    }
    public int Update(String str){
        try{
            int i=stmt.executeUpdate(str);
            return i;
        }catch(Exception e){
            return -1;
        }
    }
    public ResultSet Query(String srt){
        try{
            ResultSet rs = stmt.executeQuery(srt);
            return rs;
        }catch(Exception e){
            return null;
        }
    }   
}
