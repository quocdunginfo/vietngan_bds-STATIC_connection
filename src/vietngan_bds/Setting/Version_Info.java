/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vietngan_bds.Setting;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import vietngan_bds.Utilities.Debug;

/**
 *
 * @author quocdunginfo
 */
public class Version_Info {
    public static String ver_number="";
    public static String ver_description="";
    public static String ver_server="";
    public static String ver_mainfile="";
    public static String ver_mainfile_md5="";
    public static String ver_libfiles="";
    public static String ver_libfiles_md5="";
    private static String file_name="version_info.xml";
    public static Boolean Load_Info()
    {
        //Debug.WriteLine("SQL Load_Config_File: ./vietngan_bds_setting.xml");
        //Reading properties file in Java example
        Properties props = new Properties();
        try{
            FileInputStream fis = new FileInputStream(file_name);
            //loading properites from properties file
            props.loadFromXML(fis);
            //reading proeprty
            ver_number = props.getProperty("ver.number","0");
            ver_description = props.getProperty("ver.description","");
            ver_server = props.getProperty("ver.server","http://qd.vietngancash.com/vietngan_bds/version_info.xml");
            ver_mainfile = props.getProperty("ver.mainfile","vietngan_bds.jar");
            ver_mainfile_md5 = props.getProperty("ver.mainfile.md5","cvfgtrdeswnhgcfvgfrtyudszxwqasdr");
            ver_libfiles = props.getProperty("ver.libfiles","");
            ver_libfiles_md5 = props.getProperty("ver.libfiles.md5","");
            return true;
        }catch(Exception ex)
        {
            Debug.WriteLine("SQL Load_Version-Info_File ERROR: "+ex.toString());
            return false;
        }
    }
    public static Boolean Load_Info_From_Server()
    {
        Load_Info();
        
        return true;
    }
    public static Boolean Save_Info()
    {
        //Reading properties file in Java example
        Properties props = new Properties();
        try{
            FileOutputStream fos = new FileOutputStream(file_name);
            //set property
            props.setProperty("ver.number",ver_number);
            props.setProperty("ver.description",ver_description);
            props.setProperty("ver.server",ver_server);
            props.setProperty("ver.mainfile",ver_mainfile);
            props.setProperty("ver.mainfile.md5",ver_mainfile_md5);
            props.setProperty("ver.libfiles",ver_libfiles);
            props.setProperty("ver.libfiles.md5",ver_libfiles_md5);
            
            props.storeToXML(fos, "Chứa thông tin về phiên bản phần mềm hiện tại");
            return true;
        }catch(Exception ex)
        {
            Debug.WriteLine("Save_Version-Info ERROR: "+ex.toString());
            return false;
        }
    }
}
