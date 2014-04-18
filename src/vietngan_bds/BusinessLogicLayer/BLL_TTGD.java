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
import vietngan_bds.DataTranferObject.DTO_TTGD;
import vietngan_bds.Utilities.UTL_String;

/**
 *
 * @author quocdunginfo
 */
public class BLL_TTGD {
    //private Database db=null;
    
    public BLL_TTGD() {
        //db=new Database();
    }
    /**
     * Lấy đối tượng
     * 
     * @param id
     * @return obj || null
     */
    public DTO_TTGD Get(int id)
    {
        String sql = "SELECT `ban`, `chothue`, `sangnhuong`, `timmua`,"
                + " `timthue`, `timsang`, `khac` FROM `thongtin_gd` WHERE id="+id;
        ResultSet re = db.Get(sql);
        if(re==null) return null;
        DTO_TTGD obj = new DTO_TTGD();
        try {
            while(re.next())
            {
                obj.id = id;
                obj.ban = re.getInt("ban");
                obj.chothue = re.getInt("chothue");
                obj.sangnhuong = re.getInt("sangnhuong");
                obj.timmua = re.getInt("timmua");
                obj.timthue = re.getInt("timthue");
                obj.timsang = re.getInt("timsang");
                obj.khac = re.getString("khac");
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_TTGD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }
    
    public ArrayList<DTO_TTGD> TimKiem(int ban, int chothue, int sangnhuong,
            int timmua, int timthue, int timsang)
    {
        ArrayList<DTO_TTGD> list = new ArrayList<DTO_TTGD>();
        
        String sql = "select id from hientrang where 1=1";
        if(ban!=-1)
        sql+=" and ban ="+ban;
        if(chothue!=-1)
        sql+=" and chothue ="+chothue;
        if(sangnhuong!=-1)
        sql+=" and sangnhuong ="+sangnhuong;
        if(timmua!=-1)
        sql+=" and timmua ="+timmua;
        if(timthue!=-1)
        sql+=" and timthue ="+timthue;
        if(timsang!=-1)
        sql+=" and timsang ="+timsang;
        
        ResultSet re = db.Get(sql);
        DTO_TTGD obj;
        if(re==null) return list;
        try {
            while(re.next())
            {
                obj= Get(re.getInt("id"));
                list.add(obj);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(BLL_TTGD.class.getName()).log(Level.SEVERE, null, ex);
            return list;
        }
    }
    /**
     * Thêm mới
     * @param obj
     * @return id mới thêm || -1 (lỗi)
     */
    public int Them(DTO_TTGD obj)
    {
        String sql = "INSERT INTO `thongtin_gd`(`ban`, `chothue`,"
                + " `sangnhuong`, `timmua`, `timthue`, `timsang`, `khac`) VALUES"
                + " ("+obj.ban+","+obj.chothue+","+obj.sangnhuong+","+obj.timmua+
                ","+obj.timthue+","+obj.timsang+",'"+obj.khac+"')";
        Boolean re = db.Run(sql);
        if(!re) return -1;
        return MaMoiNhat();
    }
    public int MaMoiNhat()
    {
        String sql = "select max(id) as moinhat from thongtin_gd";
        ResultSet re = db.Get(sql);
        if(re==null) return -1;
        try {
            while(re.next())
            {
                return re.getInt("moinhat");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_TTGD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    /**
     * Cập nhật thông tin obj
     * @param obj .id: mã loại, .XXX thông tin cần cập nhật
     * @return true || false
     */
    public Boolean Sua(DTO_TTGD obj)
    {
        String sql = "UPDATE `thongtin_gd` SET `ban`="+obj.ban+","
                + "`chothue`="+obj.chothue+",`sangnhuong`="+obj.sangnhuong+","
                + "`timmua`="+obj.timmua+",`timthue`="+obj.timthue
                +",`timsang`="+obj.timsang+", `khac`='"+obj.khac+"' WHERE id="+obj.id;
        Boolean re = db.Run(sql);
        return re;
    }
    /**
     * Xóa obj theo id
     * @param id
     * @return
     */
    public Boolean Xoa(int id)
    {
        String sql="DELETE FROM `thongtin_gd` WHERE id="+id;
        Boolean re = db.Run(sql);
        return re;
    }
    public ArrayList<Integer> Filter_All(ArrayList<Integer> list, int ban,
            int chothue, int sangnhuong, int timmua,
            int timthue, int timsang, String khac)
    {
        ArrayList<Integer> re = new ArrayList<Integer>();
        if(list.size()<=0) return list;
        String sql = "SELECT `id` from `thongtin_bds` WHERE `fk_thongtin_gd` IN"
        + " (SELECT `id` FROM `thongtin_gd` WHERE 1=1";
        if(ban==1)
        {
            sql+=" AND `ban`=1";
        }
        if(chothue==1)
        {
            sql+=" AND `chothue`=1";
        }
        if(sangnhuong==1)
        {
            sql+=" AND `sangnhuong`=1";
        }
        if(timmua==1)
        {
            sql+=" AND `timmua`=1";
        }
        if(timthue==1)
        {
            sql+=" AND `timthue`=1";
        }
        if(timsang==1)
        {
            sql+=" AND `timsang`=1";
        }
        sql+=" AND `khac` LIKE '%"+khac+"%'";
        sql+=")";
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
            Logger.getLogger(BLL_TTGD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;
    }
    
}
