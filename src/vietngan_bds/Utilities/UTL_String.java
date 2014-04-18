/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vietngan_bds.Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author quocdunginfo
 */
public class UTL_String {
    public static Boolean CheckSingleQuote(java.lang.String input)
    {
        return input.indexOf("'")==-1?false:true;
    }
    public static Boolean CheckIntOnly(java.lang.String input)
    {
        int i;
        for(char tmp:input.toCharArray())
        {
            if(tmp<'0' || tmp>'9') return false;
        }
        return true;
    }
    public static Boolean CheckDoubleOnly(java.lang.String input)
    {
        input = input.replaceAll("\\,", "");
        input = input.replaceAll("\\.", "");
        int i;
        for(char tmp:input.toCharArray())
        {
            if(tmp<'0' || tmp>'9') return false;
        }
        return true;
    }
    public static String FilterQuote(String input)
    {
        return input.replace("\'", "");
    }
    public static String IntOnly(String input)
    {
        String re = "";
        int i;
        for(i=0;i<input.length();i++)
        {
            if(input.charAt(i)>='0' && input.charAt(i)<='9')
            {
                re +=String.valueOf(input.charAt(i));
            }
        }
        if(re.equals("")) re="0";
        return re;
    }
    public static String DoubleOnly(String input)
    {
        char[] tmp = new char[]{'0','1','2','3','4','5','6','7','8','9','.',','};
        String re = "";
        int i;
        for(i=0;i<input.length();i++)
        {
            if(Arrays.asList(tmp).contains(input.charAt(i)))
            {
                re +=String.valueOf(input.charAt(i));
            }
        }
        if(re.equals("")) re="0";
        return re;
    }
    public static String MD5Hash(String input)
    {
        //use commons-codec-1.7.jar
        //http://mirrors.digipower.vn/apache//commons/codec/binaries/commons-codec-1.7-bin.tar.gz
        return DigestUtils.md5Hex(input);
    }
    public static String Base64_Encode(String text)
    {
        byte[] encodedBytes = Base64.encodeBase64(text.getBytes());
        encodedBytes = Base64.encodeBase64(encodedBytes);
        return new String(encodedBytes);
    }
    public static String Base64_Decode(String text)
    {
        byte[] decodedBytes = Base64.decodeBase64(text);
        decodedBytes = Base64.decodeBase64(decodedBytes);
        return new String(decodedBytes);
    }
    public static String From_Array(ArrayList<Integer> input)
    {
        int i=0;
        String re="";
        for(i=0;i<input.size();i++)
        {
            re+=String.valueOf(input.get(i));
            if(i<input.size()-1)
            {
                re+=",";
            }
        }
        return re;
    }
}
