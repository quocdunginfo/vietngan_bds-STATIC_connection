/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vietngan_bds.DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import vietngan_bds.Setting.My_Setting;
import vietngan_bds.Utilities.Debug;

/**
 *
 * @author quocdunginfo
 */
public class db {
    private static String username="";
    private static String password="";
    private static String port = "";
    private static String server = "";
    private static String dbname="";
    
    private static Connection con=null;

    public static Connection getCon() {
        return db.con;
    }
    public db()
    {
        Load_Setting();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String url = "jdbc:mysql://"+db.server+":"+db.port+"/"+db.dbname+"?useUnicode=true&characterEncoding=utf-8";
            
            db.con = DriverManager.getConnection(url,db.username, db.password);
            Debug.WriteLine(db.username+"-"+db.password+"-"+db.server+"-"+db.dbname+"-"+db.port);
            return;
        } catch (SQLException ex) {
            Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.con=null;
    }
    public static void Open()
    {
        Load_Setting();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String url = "jdbc:mysql://"+db.server+":"+db.port+"/"+db.dbname+"?useUnicode=true&characterEncoding=utf-8";
            
            db.con = DriverManager.getConnection(url,db.username, db.password);
            Debug.WriteLine(db.username+"-"+db.password+"-"+db.server+"-"+db.dbname+"-"+db.port);
            return;
        } catch (SQLException ex) {
            Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.con=null;
    }
    private static void Load_Setting()
    {
        db.username = My_Setting.username;
        db.password = My_Setting.password;
        db.port = My_Setting.port;
        db.server = My_Setting.server;
        db.dbname = My_Setting.dbname;
        //Debug.WriteLine(username+"-"+password+"-"+server+"-"+dbname+"-"+port);
    }
    public static ResultSet Get(String sql)
    {
        Debug.WriteLine("[SQL]: "+sql);
        try {
            Statement st = db.con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public static Boolean Run(String sql)
    {
        Debug.WriteLine("[SQL]: "+sql);
        try {
            Statement st = db.con.createStatement();
            st.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public static void Close()
    {
        if(db.con!=null)
        {
            try {
            db.con.close();
            } catch (SQLException ex) {
                Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
