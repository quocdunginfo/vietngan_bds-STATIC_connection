/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vietngan_bds.Utilities;

import javax.swing.JTextField;

/**
 *
 * @author quocdunginfo
 */
public class UTL_TextField {
    /**
     * Lấy số nguyên
     * @param obj
     * @return
     */
    public static int GetInt(JTextField obj)
    {
        if(obj.getText().equals("")) return 0;
        String filter = UTL_String.IntOnly(obj.getText());
        try{
            int re = Integer.parseInt(filter);
            return re;
        }catch(Exception ex)
        {
            return 0;
        }
    }
    /**
     * Lấy chuỗi không có ký tự '
     * @param obj
     * @return
     */
    public static String GetString(JTextField obj)
    {
        String filter = UTL_String.FilterQuote(obj.getText());
        return filter;
    }
    /**
     * Lấy số thực
     * @param obj
     * @return
     */
    public static Double GetDouble(JTextField obj)
    {
        if(obj.getText().equals("")) return 0.0;
        try{
            Double re= Double.parseDouble(obj.getText().replaceAll(",", ""));
            return re;
        }catch(Exception ex)
        {
            return 0.0;
        }
    }
}
