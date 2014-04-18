/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vietngan_bds.Utilities;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author quocdunginfo
 */
public class UTL_Date {
    public static String ToMysqlValue(Date value)
    {
        return UTL_Date.ToStringValue(value, "yyyy-MM-dd HH:mm:ss");
    }
    public static String ToStringValue(Date value, String format)
    {
        Format formatter = new SimpleDateFormat(format);
        String re = formatter.format(value);
        return re;
    }
}
