/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vietngan_bds.DataTranferObject;


/**
 *
 * @author quocdunginfo
 */
public class DTO_USER {
    //primary
    public int id=0;
    public String username="";
    public int role=0;
    public String fullname="";
    public String password="";//MD5 HASH
    public int sex=0;
    
    //sub
    
    /**
    * Lấy tên role: admin, user
    *
    * @return [đối tượng CHUCVU] nếu thành công, null nếu lỗi
    */
    public String _RoleName()
    {
        switch(role)
        {
            case 0: return "admin";
            case 1: return "user";
            default: return "";
        }
    }
}
