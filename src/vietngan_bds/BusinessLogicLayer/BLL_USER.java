/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vietngan_bds.BusinessLogicLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import vietngan_bds.DataAccessLayer.db;
import vietngan_bds.DataTranferObject.DTO_USER;
import vietngan_bds.Utilities.UTL_String;

/**
 *
 * @author quocdunginfo
 */
public class BLL_USER {
    //private Database db=null;
    public BLL_USER() {
        //db=new Database();
    }
    public DTO_USER Get(int id)
    {
        String sql = "SELECT `username`, `password`, `fullname`, `role`, `sex`"
                + " FROM `user` WHERE id="+id;
        ResultSet re=db.Get(sql);
        if(re==null) return null;
        try {
            while(re.next())
            {
                DTO_USER obj = new DTO_USER();
                obj.id=id;
                obj.username=re.getString("username");
                obj.password=re.getString("password");
                obj.fullname=re.getString("fullname");
                obj.role=re.getInt("role");
                obj.sex=re.getInt("sex");
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_USER.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public DTO_USER Get(String username)
    {
        String sql = "SELECT id FROM `user` WHERE username='"+username+"'";
        ResultSet re=db.Get(sql);
        if(re==null) return null;
        try {
            while(re.next())
            {
                DTO_USER obj = Get(re.getInt("id"));
                
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_USER.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Boolean Xoa(int id, int new_assign_uid)
    {
        String sql="UPDATE `thongtin_bds` SET `fk_user`="+new_assign_uid+" WHERE `fk_user`="+id;
        Boolean re= db.Run(sql);
        
        sql="DELETE FROM `user` WHERE id="+id;
        re = re && db.Run(sql);
        
        return re;
    }
    /**
     * password_raw sẽ được hash
     * @param obj
     * @return
     */
    public int Them(DTO_USER obj, String raw_password)
    {
        String sql="INSERT INTO `user`(`username`, `password`, `fullname`, `role`, `sex`)"
                + " VALUES ('"+obj.username+"','"+UTL_String.MD5Hash(raw_password)+"','"+obj.fullname+"',"+obj.role+","+obj.sex+")";
        Boolean re=db.Run(sql);
        if(!re) return -1;
        return MaMoiNhat();
    }
    /**
     * Phải tự hash password ở ngoài qua obj.password trước
     * @param obj
     * @return
     */
    public Boolean Sua(DTO_USER obj)
    {
        String sql = "UPDATE `user` SET `username`='"+obj.username+"', `password`='"+obj.password+"',"
                + "`fullname`='"+obj.fullname+"',`role`="+obj.role+",`sex`="+obj.sex+" WHERE id="+obj.id;
        Boolean re=db.Run(sql);
        return re;
    }
    public int MaMoiNhat()
    {
        String sql = "select max(id) as moinhat from `user`";
        ResultSet re = db.Get(sql);
        if(re==null) return -1;
        try {
            while(re.next())
            {
                return re.getInt("moinhat");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_USER.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    public ArrayList<DTO_USER> TimKiem(int id, String username, String fullname, int role, int sex)
    {
        ArrayList<DTO_USER> list = new ArrayList<DTO_USER>();
        String sql = "SELECT `id` FROM `user` WHERE 1=1";
        if(id!=-1)
            sql+=" and id="+id;
        sql+=" and username like '%"+username+"%'";
        sql+=" and fullname like '%"+fullname+"%'";
        if(role!=-1)
            sql+=" and role="+role;
        if(sex!=-1)
            sql+=" and sex="+sex;
        
        ResultSet re = db.Get(sql);
        DTO_USER obj;
        if(re==null) return list;
        try {
            while(re.next())
            {
                obj= Get(re.getInt("id"));
                list.add(obj);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(BLL_USER.class.getName()).log(Level.SEVERE, null, ex);
            return list;
        }
    }
    public Boolean DoiMatKhau(int id, String raw_mkcu, String raw_mkmoi)
    {
        //khởi tạo đối tượng 
        DTO_USER nv = Get(id);
        //nếu nv đã bị xóa
        if(nv==null) return false;
        //nếu mật khẩu cũ sai
        if(!UTL_String.MD5Hash(raw_mkcu).equals(nv.password)) return false;
        //đổi mật khẩu mới
        nv.password = UTL_String.MD5Hash(raw_mkmoi);
        //gọi cập nhật thay đổi
        Boolean re = Sua(nv);
        return re;
    }
    public Boolean Login(String username, String raw_password)
    {
        //nếu username không tồn tại
        if(Get(username)==null) return false;
        //Lấy thông tin nhanvien theo tendangnhap
        DTO_USER nv = Get(username);
        //nếu đúng mật khẩu
        if(UTL_String.MD5Hash(raw_password).equals(nv.password)) return true;
        return false;
    }
    public ArrayList<Integer> Filter_id(ArrayList<Integer> list, int id)
    {
        ArrayList<Integer> re = new ArrayList<Integer>();
        if(list.size()<=0) return list;
        String sql = "SELECT `id` from `thongtin_bds` WHERE `fk_user` IN" +
        " (SELECT `id` FROM `user` WHERE `id`="+id+")";
        
        sql+= " AND `id` IN ("+ UTL_String.From_Array(list)+")";
        
        
        ResultSet rs = db.Get(sql);
        if(rs==null) return re;
        try {
            while(rs.next())
            {
                re.add(rs.getInt("id"));
            }
            return re;
        } catch (SQLException ex) {
            Logger.getLogger(BLL_USER.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;
    }
}
