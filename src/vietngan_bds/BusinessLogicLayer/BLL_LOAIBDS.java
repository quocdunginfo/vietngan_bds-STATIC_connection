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
import vietngan_bds.DataTranferObject.DTO_LOAIBDS;
import vietngan_bds.Utilities.UTL_String;

/**
 *
 * @author quocdunginfo
 */
public class BLL_LOAIBDS {
    //private Database db=null;
    
    public BLL_LOAIBDS() {
        //db=new Database();
    }
    /**
     * Lấy đối tượng loại bds
     * 
     * @param id id loại bds
     * @return loại bds || null
     */
    public DTO_LOAIBDS Get(int id)
    {
        String sql = "select nhapho,bietthu,canhochungcu,caoocvanphong,matbang"
                + ",dat,khac from loai_bds where id="+id;
        ResultSet re = db.Get(sql);
        if(re==null) return null;
        DTO_LOAIBDS obj = new DTO_LOAIBDS();
        try {
            while(re.next())
            {
                obj.id = id;
                obj.nhapho = re.getInt("nhapho");
                obj.bietthu = re.getInt("bietthu");
                obj.canhochungcu = re.getInt("canhochungcu");
                obj.caoocvanphong = re.getInt("caoocvanphong");
                obj.matbang = re.getInt("matbang");
                obj.dat = re.getInt("dat");
                obj.khac = re.getString("khac");
                return obj;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_LOAIBDS.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }
    /**
     * Tìm kiếm theo tiêu chí, -1(int) hoặc ""(String) hoặc null(Object) là bỏ qua
     * @param nhapho
     * @param bietthu
     * @param canhochungcu
     * @param caoocvanphong
     * @param matbang
     * @param dat
     * @param khac
     * @return danh sách loại bds
     */
    public ArrayList<DTO_LOAIBDS> TimKiem(int nhapho, int bietthu, int canhochungcu,
            int caoocvanphong, int matbang, int dat, String khac)
    {
        ArrayList<DTO_LOAIBDS> list = new ArrayList<DTO_LOAIBDS>();
        
        String sql = "select id from loai_bds where 1=1";
        if(nhapho!=-1)
            sql+=" and nhapho="+nhapho;
        if(bietthu!=-1)
            sql+=" and bietthu="+bietthu;
        if(canhochungcu!=-1)
            sql+=" and canhochungcu="+canhochungcu;
        if(caoocvanphong!=-1)
            sql+=" and caoocvanphong="+caoocvanphong;
        if(matbang!=-1)
            sql+=" and matbang="+matbang;
        if(dat!=-1)
            sql+=" and dat="+dat;
        if(khac!=null && !"".equals(khac))
            sql+=" and khac like '%"+khac+"%'";
        
        ResultSet re = db.Get(sql);
        DTO_LOAIBDS obj;
        if(re==null) return list;
        try {
            while(re.next())
            {
                obj= Get(re.getInt("id"));
                list.add(obj);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(BLL_LOAIBDS.class.getName()).log(Level.SEVERE, null, ex);
            return list;
        }
    }
    /**
     * Thêm mới
     * @param obj
     * @return id mới thêm || -1 (lỗi)
     */
    public int Them(DTO_LOAIBDS obj)
    {
        String sql = "INSERT INTO `loai_bds`(`nhapho`, `bietthu`,"
                + "`canhochungcu`, `caoocvanphong`, `matbang`, `dat`, `khac`)"
                + "VALUES ("+obj.nhapho+","+obj.bietthu+","+obj.canhochungcu+","
                + obj.caoocvanphong+","+obj.matbang+","+obj.dat+",'"+obj.khac+"')";
        Boolean re = db.Run(sql);
        if(!re) return -1;
        return MaMoiNhat();
    }
    public int MaMoiNhat()
    {
        String sql = "select max(id) as moinhat from loai_bds";
        ResultSet re = db.Get(sql);
        if(re==null) return -1;
        try {
            while(re.next())
            {
                return re.getInt("moinhat");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BLL_LOAIBDS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    /**
     * Cập nhật thông tin loại bds
     * @param obj .id: mã loại, .XXX thông tin cần cập nhật
     * @return true || false
     */
    public Boolean Sua(DTO_LOAIBDS obj)
    {
        String sql = "UPDATE `loai_bds` SET `nhapho`="+obj.nhapho+","
                + "`bietthu`="+obj.bietthu+",`canhochungcu`="+obj.canhochungcu+","
                + "`caoocvanphong`="+obj.caoocvanphong+",`matbang`="+obj.matbang+","
                + "`dat`="+obj.dat+",`khac`='"+obj.khac+"' WHERE id="+obj.id;
        Boolean re = db.Run(sql);
        return re;
    }
    /**
     * Xóa loại bds theo id
     * @param id
     * @return
     */
    public Boolean Xoa(int id)
    {
        String sql="DELETE FROM `loai_bds` WHERE id="+id;
        Boolean re = db.Run(sql);
        return re;
    }
    public ArrayList<Integer> Filter_All(ArrayList<Integer> list, int nhapho,
            int bietthu, int canhochungcu, int caoocvanphong,
            int matbang, int dat, String khac)
    {
        ArrayList<Integer> re = new ArrayList<Integer>();
        if(list.size()<=0) return list;
        String sql = "SELECT `id` from `thongtin_bds` WHERE `fk_loai_bds` IN"
        + " (SELECT `id` FROM `loai_bds` WHERE 1=1";
        if(nhapho==1)
        {
            sql+=" AND `nhapho`=1";
        }
        if(bietthu==1)
        {
            sql+=" AND `bietthu`=1";
        }
        if(canhochungcu==1)
        {
            sql+=" AND `canhochungcu`=1";
        }
        if(caoocvanphong==1)
        {
            sql+=" AND `caoocvanphong`=1";
        }
        if(matbang==1)
        {
            sql+=" AND `matbang`=1";
        }
        if(dat==1)
        {
            sql+=" AND `dat`=1";
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
            Logger.getLogger(BLL_LOAIBDS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return re;
    }
    
}
