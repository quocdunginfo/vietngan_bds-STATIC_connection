/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vietngan_bds.Setting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import vietngan_bds.Utilities.Debug;
import vietngan_bds.Utilities.UTL_String;

/**
 *
 * @author quocdunginfo
 */
public class My_Setting {
    public static String username="root";
    public static String password="";
    public static String port = "3306";
    public static String server = "localhost";
    public static String dbname="vietngan_bds";
    private static String file_name = "vietngan_bds_setting.xml";
    public static Boolean Load_Setting()
    {
        //Debug.WriteLine("SQL Load_Config_File: ./vietngan_bds_setting.xml");
        //Reading properties file in Java example
        Properties props = new Properties();
        try{
            FileInputStream fis = new FileInputStream(file_name);
            //loading properites from properties file
            props.loadFromXML(fis);
            //reading proeprty
            username = props.getProperty("jdbc.username","root");
            password = UTL_String.Base64_Decode(props.getProperty("jdbc.password",""));
            dbname = props.getProperty("jdbc.dbname","vietngan_bds");
            server = props.getProperty("jdbc.server","localhost");
            port = props.getProperty("jdbc.port","3306");
            return true;
        }catch(Exception ex)
        {
            Debug.WriteLine("SQL Load_Config_File ERROR: "+ex.toString());
            return false;
        }
    }
    public static Boolean Save_Setting()
    {
        //Reading properties file in Java example
        Properties props = new Properties();
        try{
            FileOutputStream fos = new FileOutputStream(file_name);
            //set property
            props.setProperty("jdbc.username",username);
            props.setProperty("jdbc.password",UTL_String.Base64_Encode(password));
            props.setProperty("jdbc.dbname",dbname);
            props.setProperty("jdbc.server",server);
            props.setProperty("jdbc.port",port);
            props.storeToXML(fos, "No comment");
            return true;
        }catch(Exception ex)
        {
            Debug.WriteLine("Save_Setting ERROR: "+ex.toString());
            return false;
        }
    }
}
