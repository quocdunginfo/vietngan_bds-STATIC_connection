/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vietngan_bds.Utilities;

import javax.swing.JTextArea;

/**
 *
 * @author quocdunginfo
 */
public class UTL_TextArea {
    
    public static String GetString(JTextArea obj)
    {
        return UTL_String.FilterQuote(obj.getText());
    }
}
