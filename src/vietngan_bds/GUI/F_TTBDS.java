/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vietngan_bds.GUI;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import vietngan_bds.BusinessLogicLayer.BLL_DONVI;
import vietngan_bds.BusinessLogicLayer.BLL_HUONG;
import vietngan_bds.BusinessLogicLayer.BLL_TIENTE;
import vietngan_bds.BusinessLogicLayer.BLL_TTBDS;
import vietngan_bds.DataTranferObject.DTO_DONVI;
import vietngan_bds.DataTranferObject.DTO_DTNXD;
import vietngan_bds.DataTranferObject.DTO_GIAGD;
import vietngan_bds.DataTranferObject.DTO_HIENTRANG;
import vietngan_bds.DataTranferObject.DTO_HUONG;
import vietngan_bds.DataTranferObject.DTO_LOAIBDS;
import vietngan_bds.DataTranferObject.DTO_TIENICH;
import vietngan_bds.DataTranferObject.DTO_TIENNGHI;
import vietngan_bds.DataTranferObject.DTO_TIENTE;
import vietngan_bds.DataTranferObject.DTO_TINHTRANGPHAPLY;
import vietngan_bds.DataTranferObject.DTO_TTBDS;
import vietngan_bds.DataTranferObject.DTO_TTGD;
import vietngan_bds.DataTranferObject.DTO_VITRI;
import vietngan_bds.Utilities.Global;
import vietngan_bds.Utilities.UTL_ComboBox;
import vietngan_bds.Utilities.UTL_Listener;
import vietngan_bds.Utilities.UTL_TextArea;
import vietngan_bds.Utilities.UTL_TextField;

/**
 *
 * @author quocdunginfo
 */
public class F_TTBDS extends javax.swing.JFrame {
    //them, sua, xemchitiet
    private String mode="xemchitiet";
    private DTO_TTBDS obj=null;
    public int result=0;
    /**
     * Creates new form F_TTBDS
     */
    public F_TTBDS() {
        initComponents();
    }
    public F_TTBDS(String mode, int id) {
        this.mode=mode;
        if(mode.equals("xemchitiet") || mode.equals("sua"))
        {
            this.obj=new BLL_TTBDS().Get(id);
        }
        else
        {
            //mode them
            this.obj = new DTO_TTBDS();
        }
        initComponents();
        //set listenner
        jTextField_dtnxd_chieudai.addKeyListener(UTL_Listener.TextField_OnlyDouble);
    }
    //điều khiển chức năng button,...
    private void Function_control()
    {
        if(mode.equals("xemchitiet"))
        {
            jButton_luu.setEnabled(false);
            jButton_them.setEnabled(false);
            return;
        }
        if(mode.equals("them"))
        {
            jButton_luu.setEnabled(false);
            jButton_them.setEnabled(true);
            return;
        }
        if(mode.equals("sua"))
        {
            jButton_luu.setEnabled(true);
            jButton_them.setEnabled(false);
        }
    }
    //dieu khien view giao dien
    private void Enable_control(Boolean control)
    {
        jTextArea_ghichu.setEditable(control);
        jTextField_csh_diachi.setEditable(control);
        jTextField_csh_dienthoai.setEditable(control);
        jTextField_csh_email.setEditable(control);
        jTextField_csh_hoten.setEditable(control);
        jTextField_dtnxd_chieudai.setEditable(control);
        jTextField_dtnxd_chieungang.setEditable(control);
        jTextField_dtnxd_dtkv.setEditable(control);
        jTextField_dtnxd_dtsan.setEditable(control);
        jTextField_dtnxd_dtsd.setEditable(control);
        jTextField_dtnxd_dtxd.setEditable(control);
        jTextField_hientrang_gac.setEditable(control);
        jTextField_hientrang_mai.setEditable(control);
        jTextField_hientrang_nhavesinh.setEditable(control);
        jTextField_hientrang_phongan.setEditable(control);
        jTextField_hientrang_phongkhach.setEditable(control);
        jTextField_hientrang_phongngu.setEditable(control);
        jTextField_hientrang_solau.setEditable(control);
        jTextField_hientrang_tuong.setEditable(control);
        jTextField_id.setEditable(control);
        jTextField_lbds_khac.setEditable(control);
        jTextField_nhanvien.setEditable(control);
        jTextField_tienich_benhvien.setEditable(control);
        jTextField_tienich_chosieuthi.setEditable(control);
        jTextField_tienich_congvien.setEditable(control);
        jTextField_tienich_khac.setEditable(control);
        jTextField_tienich_nganhang.setEditable(control);
        jTextField_tienich_truongcap.setEditable(control);
        jTextField_tienich_truongcddh.setEditable(control);
        jTextField_tienich_truongmaugiao.setEditable(control);
        jTextField_tiennghi_khac.setEditable(control);
        jTextField_tiennghi_maylanh.setEditable(control);
        jTextField_tiennghi_maynuocnong.setEditable(control);
        jTextField_tiennghi_mayphatdien.setEditable(control);
        jTextField_ttpl_khac.setEditable(control);
        jTextField_vt_cachduong.setEditable(control);
        jTextField_vt_hemchinh.setEditable(control);
        jTextField_vt_hemphu.setEditable(control);
        jTextField_vt_leduong.setEditable(control);
        jTextField_vt_mt.setEditable(control);
        jTextField_vt_mtnb.setEditable(control);
        
        //conmbobox
        jComboBox_csh_gioitinh.setEnabled(control);
        jComboBox_vt_huong.setEnabled(control);
        //datetime picker
        jDateChooser_ngay.setEnabled(control);
    }
    private void Load_blank_info()
    {
        this.obj.id=0;
        jDateChooser_ngay.setDate(new Date());
        jDateChooser_dtnxd_nxd.setDate(new Date());
        jTextField_nhanvien.setText(Global.user.username+" ("+Global.user.fullname+")");
    }
    //load cac gia tri ban dau
    private void Load_pre_value()
    {
        //HUONG
        jComboBox_vt_huong.removeAllItems();
        //load combobox huong
        ArrayList<DTO_HUONG> list = new BLL_HUONG().TimKiem(-1, "");
        for(DTO_HUONG tmp:list)
        {
            UTL_ComboBox.AddComboBoxItem(jComboBox_vt_huong, tmp.id, tmp.huong);
        }
        jComboBox_vt_huong.setSelectedIndex(0);
        
        //TIENTE 1 và 2
        jComboBox_giagd_ban_tiente.removeAllItems();
        jComboBox_giagd_thue_tiente.removeAllItems();
        //load combobox tiente
        ArrayList<DTO_TIENTE> list2 = new BLL_TIENTE().TimKiem(-1, "");
        for(DTO_TIENTE tmp:list2)
        {
            UTL_ComboBox.AddComboBoxItem(jComboBox_giagd_ban_tiente, tmp.id, tmp.name);
            UTL_ComboBox.AddComboBoxItem(jComboBox_giagd_thue_tiente, tmp.id, tmp.name);
        }
        jComboBox_giagd_ban_tiente.setSelectedIndex(0);
        jComboBox_giagd_thue_tiente.setSelectedIndex(0);
        
        //DONVI 1 và 2
        jComboBox_giagd_ban_donvi.removeAllItems();
        jComboBox_giagd_thue_donvi.removeAllItems();
        //load combobox tiente
        ArrayList<DTO_DONVI> list3 = new BLL_DONVI().TimKiem(-1, "");
        for(DTO_DONVI tmp:list3)
        {
            UTL_ComboBox.AddComboBoxItem(jComboBox_giagd_ban_donvi, tmp.id, tmp.name);
            UTL_ComboBox.AddComboBoxItem(jComboBox_giagd_thue_donvi, tmp.id, tmp.name);
        }
        jComboBox_giagd_ban_donvi.setSelectedIndex(0);
        jComboBox_giagd_thue_donvi.setSelectedIndex(0);
        
        
    }
    //hien thi tat ca thong tin của obj có sẵn
    private void Load_exist_info()
    {
        //load
        //DTO_TTBDS obj_tmp = new BLL_TTBDS().Get(1);
        if(obj!=null){
            //load thông tin chu so huu
            jTextArea_ghichu.setText(obj.ghichu);
            jTextField_csh_diachi.setText(obj.fk_csh_diachi._ToString());
            jTextField_csh_email.setText(obj.csh_email);
            jTextField_csh_dienthoai.setText(obj.csh_dienthoai);
            jTextField_csh_hoten.setText(obj.csh_hoten);
            jDateChooser_ngay.setDate(obj.ngay);
            jTextField_id.setText(obj.id+"");
            jComboBox_csh_gioitinh.setSelectedIndex(obj.csh_gioitinh==1?1:0);
            if(obj.fk_user!=null)
            {
                jTextField_nhanvien.setText(obj.fk_user.username+ " ("+obj.fk_user.fullname+")");
            }
        }
        else return;
        //load thong tin giao dich
        DTO_TTGD ttgd = obj.fk_thongtin_gd;
        if(ttgd!=null){
            jCheckBox_ttgd_ban.setSelected(ttgd.ban==1);
            jCheckBox_ttgd_chothue.setSelected(ttgd.chothue==1);
            jCheckBox_ttgd_sangnhuong.setSelected(ttgd.sangnhuong==1);
            jCheckBox_ttgd_timmua.setSelected(ttgd.timmua==1);
            jCheckBox_ttgd_timsang.setSelected(ttgd.timsang==1);
            jCheckBox_ttgd_timthue.setSelected(ttgd.timthue==1);
        }
        //load loai bds
        DTO_LOAIBDS lbds = obj.fk_loai_bds;
        if(lbds!=null){
            jCheckBox_lbds_bietthu.setSelected(lbds.bietthu==1);
            jCheckBox_lbds_canhochungcu.setSelected(lbds.canhochungcu==1);
            jCheckBox_lbds_caoocvanphong.setSelected(lbds.caoocvanphong==1);
            jCheckBox_lbds_dat.setSelected(lbds.dat==1);
            jCheckBox_lbds_matbang.setSelected(lbds.matbang==1);
            jCheckBox_lbds_nhapho.setSelected(lbds.nhapho==1);
            jTextField_lbds_khac.setText(lbds.khac);
        }
        //load vi tri
        DTO_VITRI vitri = obj.fk_vitri;
        if(vitri!=null){
            jTextField_vt_cachduong.setText(vitri.cachduong);
            jTextField_vt_hemchinh.setText(vitri.hemchinh);
            jTextField_vt_hemphu.setText(vitri.hemphu);
            jTextField_vt_leduong.setText(vitri.leduong);
            jTextField_vt_mt.setText(vitri.mt);
            jTextField_vt_mtnb.setText(vitri.mtnb);
            jComboBox_vt_huong.setSelectedIndex(vitri.fk_huong.id-1);
            jTextField_vt_diachi.setText(vitri.fk_diachi._ToString());
        }
        //load dien tich va nam xay dung
        DTO_DTNXD dtnxd = obj.fk_dientich_namxaydung;
        if(dtnxd!=null){
            jTextField_dtnxd_chieudai.setText(String.valueOf(dtnxd.chieudai));
            jTextField_dtnxd_chieungang.setText(String.valueOf(dtnxd.chieungang));
            jTextField_dtnxd_dtkv.setText(String.valueOf(dtnxd.dtkv));
            jTextField_dtnxd_dtsan.setText(String.valueOf(dtnxd.dtsan));
            jTextField_dtnxd_dtsd.setText(String.valueOf(dtnxd.dtsd));
            jTextField_dtnxd_dtxd.setText(String.valueOf(dtnxd.dtxd));
            jDateChooser_dtnxd_nxd.setDate(obj.fk_dientich_namxaydung.namxaydung);
        }
        //load hien trang
        DTO_HIENTRANG ht = obj.fk_hientrang;
        if(ht!=null){
            jTextField_hientrang_gac.setText(new DecimalFormat("#,###").format(ht.gac));
            jTextField_hientrang_mai.setText(ht.mai);
            jTextField_hientrang_nhavesinh.setText(ht.nhavesinh);
            jTextField_hientrang_phongan.setText(ht.phongan);
            jTextField_hientrang_phongkhach.setText(ht.phongkhach);
            jTextField_hientrang_phongngu.setText(ht.phongngu);
            jTextField_hientrang_solau.setText(new DecimalFormat("#,###").format(ht.solau));
            jTextField_hientrang_tuong.setText(ht.tuong);
        }
        //load tien nghi
        DTO_TIENNGHI tnghi = obj.fk_tiennghi;
        if(tnghi!=null){
            jCheckBox_tiennghi_dhdien.setSelected(tnghi.dh_dien==1);
            jCheckBox_tiennghi_dhnuoc.setSelected(tnghi.dh_nuoc==1);
            jCheckBox_tiennghi_dienthoai.setSelected(tnghi.dienthoai==1);
            jCheckBox_tiennghi_garage.setSelected(tnghi.garage==1);
            jCheckBox_tiennghi_hoboi.setSelected(tnghi.hoboi==1);
            jCheckBox_tiennghi_internet.setSelected(tnghi.internet==1);
            jCheckBox_tiennghi_truyenhinhcap.setSelected(tnghi.truyenhinhcap==1);
            jTextField_tiennghi_khac.setText(tnghi.khac);
            jTextField_tiennghi_maylanh.setText(new DecimalFormat("#,###").format(tnghi.maylanh));
            jTextField_tiennghi_maynuocnong.setText(new DecimalFormat("#,###").format(tnghi.maynuocnong));
            jTextField_tiennghi_mayphatdien.setText(new DecimalFormat("#,###").format(tnghi.mayphatdien));
        }
        //load tinh trang phap ly
        DTO_TINHTRANGPHAPLY ttpl=obj.fk_tinhtrangphaply;
        if(ttpl!=null){
            jCheckBox_ttpl_sodo.setSelected(ttpl.sodo==1);
            jCheckBox_ttpl_sohong.setSelected(ttpl.sohong==1);
            jTextField_ttpl_khac.setText(ttpl.khac);
        }
        //load tien ich
        DTO_TIENICH tich = obj.fk_tienich;
        if(tich!=null){
            jTextField_tienich_benhvien.setText(tich.benhvien);
            jTextField_tienich_chosieuthi.setText(tich.cho_sieuthi);
            jTextField_tienich_congvien.setText(tich.congvien);
            jTextField_tienich_khac.setText(tich.khac);
            jTextField_tienich_nganhang.setText(tich.nganhang);
            jTextField_tienich_truongcap.setText(tich.truongcap);
            jTextField_tienich_truongcddh.setText(tich.truongcddh);
            jTextField_tienich_truongmaugiao.setText(tich.truongmaugiao);
        }
        //load giá gd
        String giagd_tmp = new DecimalFormat("#,###.0").format(obj.fk_gia_gd.giaban);
        jTextField_giagd_ban.setText(giagd_tmp);
        UTL_ComboBox.SetComboBoxSelectedItem(jComboBox_giagd_ban_tiente, obj.fk_gia_gd.fk_giaban_tiente.id);
        UTL_ComboBox.SetComboBoxSelectedItem(jComboBox_giagd_ban_donvi, obj.fk_gia_gd.fk_giaban_donvi.id);
        
        giagd_tmp = new DecimalFormat("#,###.0").format(obj.fk_gia_gd.giathue);
        jTextField_giagd_thue.setText(giagd_tmp);
        UTL_ComboBox.SetComboBoxSelectedItem(jComboBox_giagd_thue_tiente, obj.fk_gia_gd.fk_giathue_tiente.id);
        UTL_ComboBox.SetComboBoxSelectedItem(jComboBox_giagd_thue_donvi, obj.fk_gia_gd.fk_giathue_donvi.id);
    }
    private DTO_TTBDS Get_form_data()
    {
        DTO_TTBDS obj_tmp=new DTO_TTBDS();
        obj_tmp.id= this.obj.id;
        obj_tmp.ngay=jDateChooser_ngay.getDate();
        if(obj_tmp.ngay==null) obj_tmp.ngay=new Date();
        
        if(mode.equals("them"))
        {
            obj_tmp.fk_user = Global.user;
        }
        else
        {
            obj_tmp.fk_user=obj.fk_user;
        }
        
        obj_tmp.fk_csh_diachi = obj.fk_csh_diachi;
        obj_tmp.csh_dienthoai = UTL_TextField.GetString(jTextField_csh_dienthoai);
        obj_tmp.csh_email = UTL_TextField.GetString(jTextField_csh_email);
        obj_tmp.csh_gioitinh = jComboBox_csh_gioitinh.getSelectedIndex();
        obj_tmp.csh_hoten = UTL_TextField.GetString(jTextField_csh_hoten);
        obj_tmp.ghichu = UTL_TextArea.GetString(jTextArea_ghichu);
        
        DTO_TTGD ttgd = new DTO_TTGD();
        ttgd.id=obj.fk_thongtin_gd.id;
        ttgd.ban = jCheckBox_ttgd_ban.isSelected()?1:0;
        ttgd.chothue = jCheckBox_ttgd_chothue.isSelected()?1:0;
        ttgd.sangnhuong = jCheckBox_ttgd_sangnhuong.isSelected()?1:0;
        ttgd.timmua = jCheckBox_ttgd_timmua.isSelected()?1:0;
        ttgd.timsang = jCheckBox_ttgd_timsang.isSelected()?1:0;
        ttgd.timthue = jCheckBox_ttgd_timthue.isSelected()?1:0;
        obj_tmp.fk_thongtin_gd=ttgd;
        
        DTO_LOAIBDS lbds = new DTO_LOAIBDS();
        lbds.id=obj.fk_loai_bds.id;
        lbds.bietthu= jCheckBox_lbds_bietthu.isSelected()?1:0;
        lbds.canhochungcu= jCheckBox_lbds_canhochungcu.isSelected()?1:0;
        lbds.caoocvanphong= jCheckBox_lbds_caoocvanphong.isSelected()?1:0;
        lbds.dat= jCheckBox_lbds_dat.isSelected()?1:0;
        lbds.khac= UTL_TextField.GetString(jTextField_lbds_khac);
        lbds.matbang= jCheckBox_lbds_matbang.isSelected()?1:0;
        lbds.nhapho= jCheckBox_lbds_nhapho.isSelected()?1:0;
        obj_tmp.fk_loai_bds=lbds;
        
        DTO_VITRI vitri = new DTO_VITRI();
        vitri.id=obj.fk_vitri.id;
        vitri.cachduong = UTL_TextField.GetString(jTextField_vt_cachduong);
        vitri.hemchinh = UTL_TextField.GetString(jTextField_vt_hemchinh);
        vitri.hemphu = UTL_TextField.GetString(jTextField_vt_hemphu);
        vitri.leduong = UTL_TextField.GetString(jTextField_vt_leduong);
        vitri.fk_huong = new BLL_HUONG().Get(UTL_ComboBox.GetComboBoxValueInt(jComboBox_vt_huong));
        vitri.mt = UTL_TextField.GetString(jTextField_vt_mt);
        vitri.mtnb = UTL_TextField.GetString(jTextField_vt_mtnb);
        vitri.fk_diachi = obj.fk_vitri.fk_diachi;
        obj_tmp.fk_vitri = vitri;
        
        DTO_DTNXD dtnxd = new DTO_DTNXD();
        dtnxd.id=obj.fk_dientich_namxaydung.id;
        dtnxd.chieudai = UTL_TextField.GetDouble(jTextField_dtnxd_chieudai);
        dtnxd.chieungang = UTL_TextField.GetDouble(jTextField_dtnxd_chieungang);
        dtnxd.dtkv = UTL_TextField.GetDouble(jTextField_dtnxd_dtkv);
        dtnxd.dtsan = UTL_TextField.GetDouble(jTextField_dtnxd_dtsan);
        dtnxd.dtsd = UTL_TextField.GetDouble(jTextField_dtnxd_dtsd);
        dtnxd.dtxd = UTL_TextField.GetDouble(jTextField_dtnxd_dtxd);
        dtnxd.namxaydung = jDateChooser_dtnxd_nxd.getDate();
        if(dtnxd.namxaydung==null) dtnxd.namxaydung = new Date();
        obj_tmp.fk_dientich_namxaydung=dtnxd;
        
        DTO_HIENTRANG ht=new DTO_HIENTRANG();
        ht.id=obj.fk_hientrang.id;
        ht.gac=UTL_TextField.GetDouble(jTextField_hientrang_gac);
        ht.mai=UTL_TextField.GetString(jTextField_hientrang_mai);
        ht.nhavesinh=UTL_TextField.GetString(jTextField_hientrang_nhavesinh);
        ht.phongan=UTL_TextField.GetString(jTextField_hientrang_phongan);
        ht.phongkhach=UTL_TextField.GetString(jTextField_hientrang_phongkhach);
        ht.phongngu=UTL_TextField.GetString(jTextField_hientrang_phongngu);
        ht.solau=UTL_TextField.GetDouble(jTextField_hientrang_solau);
        ht.tuong=UTL_TextField.GetString(jTextField_hientrang_tuong);
        obj_tmp.fk_hientrang=ht;
        
        DTO_TIENNGHI tnghi=new DTO_TIENNGHI();
        tnghi.id=obj.fk_tiennghi.id;
        tnghi.dh_dien = jCheckBox_tiennghi_dhdien.isSelected()?1:0;
        tnghi.dh_nuoc = jCheckBox_tiennghi_dhnuoc.isSelected()?1:0;
        tnghi.dienthoai = jCheckBox_tiennghi_dienthoai.isSelected()?1:0;
        tnghi.garage = jCheckBox_tiennghi_garage.isSelected()?1:0;
        tnghi.hoboi = jCheckBox_tiennghi_hoboi.isSelected()?1:0;
        tnghi.internet = jCheckBox_tiennghi_internet.isSelected()?1:0;
        tnghi.truyenhinhcap = jCheckBox_tiennghi_truyenhinhcap.isSelected()?1:0;
        tnghi.maylanh=UTL_TextField.GetDouble(jTextField_tiennghi_maylanh);
        tnghi.maynuocnong=UTL_TextField.GetDouble(jTextField_tiennghi_maynuocnong);
        tnghi.mayphatdien=UTL_TextField.GetDouble(jTextField_tiennghi_mayphatdien);
        tnghi.khac=UTL_TextField.GetString(jTextField_tiennghi_khac);
        obj_tmp.fk_tiennghi=tnghi;
        
        DTO_TINHTRANGPHAPLY ttpl=new DTO_TINHTRANGPHAPLY();
        ttpl.id=obj.fk_tinhtrangphaply.id;
        ttpl.khac = UTL_TextField.GetString(jTextField_ttpl_khac);
        ttpl.sodo = jCheckBox_ttpl_sodo.isSelected()?1:0;
        ttpl.sohong = jCheckBox_ttpl_sohong.isSelected()?1:0;
        obj_tmp.fk_tinhtrangphaply=ttpl;
        
        DTO_TIENICH tich=new DTO_TIENICH();
        tich.id=obj.fk_tienich.id;
        tich.benhvien = UTL_TextField.GetString(jTextField_tienich_benhvien);
        tich.cho_sieuthi = UTL_TextField.GetString(jTextField_tienich_chosieuthi);
        tich.congvien = UTL_TextField.GetString(jTextField_tienich_congvien);
        tich.khac = UTL_TextField.GetString(jTextField_tienich_khac);
        tich.nganhang = UTL_TextField.GetString(jTextField_tienich_nganhang);
        tich.truongcap = UTL_TextField.GetString(jTextField_tienich_truongcap);
        tich.truongcddh = UTL_TextField.GetString(jTextField_tienich_truongcddh);
        tich.truongmaugiao = UTL_TextField.GetString(jTextField_tienich_truongmaugiao);
        obj_tmp.fk_tienich=tich;
        
        DTO_GIAGD giagd = new DTO_GIAGD();
        giagd.id=obj.fk_gia_gd.id;
        giagd.giaban = UTL_TextField.GetDouble(jTextField_giagd_ban);
        giagd.fk_giaban_tiente =
                new BLL_TIENTE().Get(UTL_ComboBox.GetComboBoxValueInt(jComboBox_giagd_ban_tiente));
        giagd.fk_giaban_donvi =
                new BLL_DONVI().Get(UTL_ComboBox.GetComboBoxValueInt(jComboBox_giagd_ban_donvi));
        
        giagd.giathue = UTL_TextField.GetDouble(jTextField_giagd_thue);
        giagd.fk_giathue_tiente =
                new BLL_TIENTE().Get(UTL_ComboBox.GetComboBoxValueInt(jComboBox_giagd_thue_tiente));
        giagd.fk_giathue_donvi =
                new BLL_DONVI().Get(UTL_ComboBox.GetComboBoxValueInt(jComboBox_giagd_thue_donvi));
        obj_tmp.fk_gia_gd = giagd;
        
        return obj_tmp;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jCheckBox_ttgd_ban = new javax.swing.JCheckBox();
        jCheckBox_ttgd_chothue = new javax.swing.JCheckBox();
        jCheckBox_ttgd_sangnhuong = new javax.swing.JCheckBox();
        jCheckBox_ttgd_timthue = new javax.swing.JCheckBox();
        jCheckBox_ttgd_timmua = new javax.swing.JCheckBox();
        jCheckBox_ttgd_timsang = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextField_csh_hoten = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jComboBox_csh_gioitinh = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jTextField_csh_diachi = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField_csh_dienthoai = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField_csh_email = new javax.swing.JTextField();
        jButton_csh_chondc = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField_hientrang_solau = new javax.swing.JTextField();
        jTextField_hientrang_gac = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField_hientrang_tuong = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField_hientrang_mai = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField_hientrang_phongkhach = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField_hientrang_phongngu = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField_hientrang_phongan = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jTextField_hientrang_nhavesinh = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jTextField_vt_mt = new javax.swing.JTextField();
        jTextField_vt_mtnb = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField_vt_hemchinh = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField_vt_hemphu = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField_vt_cachduong = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextField_vt_leduong = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jComboBox_vt_huong = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        jCheckBox_lbds_nhapho = new javax.swing.JCheckBox();
        jCheckBox_lbds_bietthu = new javax.swing.JCheckBox();
        jCheckBox_lbds_canhochungcu = new javax.swing.JCheckBox();
        jCheckBox_lbds_caoocvanphong = new javax.swing.JCheckBox();
        jCheckBox_lbds_matbang = new javax.swing.JCheckBox();
        jCheckBox_lbds_dat = new javax.swing.JCheckBox();
        jLabel23 = new javax.swing.JLabel();
        jTextField_lbds_khac = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jTextField_dtnxd_chieungang = new javax.swing.JTextField();
        jTextField_dtnxd_chieudai = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField_dtnxd_dtkv = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField_dtnxd_dtxd = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextField_dtnxd_dtsd = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextField_dtnxd_dtsan = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jDateChooser_dtnxd_nxd = new com.toedter.calendar.JDateChooser();
        jPanel9 = new javax.swing.JPanel();
        jCheckBox_tiennghi_dhdien = new javax.swing.JCheckBox();
        jCheckBox_tiennghi_dhnuoc = new javax.swing.JCheckBox();
        jCheckBox_tiennghi_internet = new javax.swing.JCheckBox();
        jCheckBox_tiennghi_dienthoai = new javax.swing.JCheckBox();
        jCheckBox_tiennghi_truyenhinhcap = new javax.swing.JCheckBox();
        jCheckBox_tiennghi_hoboi = new javax.swing.JCheckBox();
        jCheckBox_tiennghi_garage = new javax.swing.JCheckBox();
        jLabel33 = new javax.swing.JLabel();
        jTextField_tiennghi_maylanh = new javax.swing.JTextField();
        jTextField_tiennghi_maynuocnong = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jTextField_tiennghi_mayphatdien = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jTextField_tiennghi_khac = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jTextField_tienich_truongmaugiao = new javax.swing.JTextField();
        jTextField_tienich_truongcap = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jTextField_tienich_truongcddh = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jTextField_tienich_congvien = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jTextField_tienich_chosieuthi = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jTextField_tienich_nganhang = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jTextField_tienich_benhvien = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jTextField_tienich_khac = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jCheckBox_ttpl_sohong = new javax.swing.JCheckBox();
        jCheckBox_ttpl_sodo = new javax.swing.JCheckBox();
        jLabel47 = new javax.swing.JLabel();
        jTextField_ttpl_khac = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea_ghichu = new javax.swing.JTextArea();
        jButton_luu = new javax.swing.JButton();
        jButton_them = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jTextField_vt_diachi = new javax.swing.JTextField();
        jButton_vt_chondc = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jTabbedPane_giagd = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jTextField_giagd_ban = new javax.swing.JTextField();
        jComboBox_giagd_ban_tiente = new javax.swing.JComboBox();
        jLabel48 = new javax.swing.JLabel();
        jComboBox_giagd_ban_donvi = new javax.swing.JComboBox();
        jPanel13 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jTextField_giagd_thue = new javax.swing.JTextField();
        jComboBox_giagd_thue_tiente = new javax.swing.JComboBox();
        jLabel50 = new javax.swing.JLabel();
        jComboBox_giagd_thue_donvi = new javax.swing.JComboBox();
        jPanel16 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField_id = new javax.swing.JTextField();
        jDateChooser_ngay = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jTextField_nhanvien = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Thông tin bất động sản");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin giao dịch"));
        jPanel2.setToolTipText("");
        jPanel2.setName(""); // NOI18N

        jCheckBox_ttgd_ban.setText("Bán");
        jCheckBox_ttgd_ban.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_ttgd_banActionPerformed(evt);
            }
        });

        jCheckBox_ttgd_chothue.setText("Cho thuê");

        jCheckBox_ttgd_sangnhuong.setText("Sang nhượng");

        jCheckBox_ttgd_timthue.setText("Tìm thuê");
        jCheckBox_ttgd_timthue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_ttgd_timthueActionPerformed(evt);
            }
        });

        jCheckBox_ttgd_timmua.setText("Tìm mua");
        jCheckBox_ttgd_timmua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_ttgd_timmuaActionPerformed(evt);
            }
        });

        jCheckBox_ttgd_timsang.setText("Tìm sang");
        jCheckBox_ttgd_timsang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_ttgd_timsangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox_ttgd_ban)
                    .addComponent(jCheckBox_ttgd_timmua))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox_ttgd_chothue)
                    .addComponent(jCheckBox_ttgd_timthue))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox_ttgd_sangnhuong)
                    .addComponent(jCheckBox_ttgd_timsang))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox_ttgd_ban)
                    .addComponent(jCheckBox_ttgd_chothue)
                    .addComponent(jCheckBox_ttgd_sangnhuong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox_ttgd_timmua)
                    .addComponent(jCheckBox_ttgd_timthue)
                    .addComponent(jCheckBox_ttgd_timsang)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin chủ sở hữu"));
        jPanel3.setToolTipText("");
        jPanel3.setName(""); // NOI18N

        jLabel8.setText("Họ và tên:");

        jLabel9.setText("Giới tính:");

        jComboBox_csh_gioitinh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nữ", "Nam" }));

        jLabel10.setText("Địa chỉ:");

        jLabel11.setText("Điện thoại:");

        jLabel12.setText("Email:");

        jButton_csh_chondc.setText("Chọn");
        jButton_csh_chondc.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton_csh_chondc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_csh_chondcActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_csh_email)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField_csh_hoten)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox_csh_gioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField_csh_diachi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_csh_chondc))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField_csh_dienthoai)
                        .addGap(111, 111, 111))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField_csh_hoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox_csh_gioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField_csh_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_csh_chondc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_csh_dienthoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField_csh_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Hiện trạng"));
        jPanel4.setToolTipText("");
        jPanel4.setName(""); // NOI18N

        jLabel3.setText("Số lầu:");

        jTextField_hientrang_gac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_hientrang_gacActionPerformed(evt);
            }
        });

        jLabel5.setText("Gác:");

        jLabel6.setText("Tường:");

        jLabel7.setText("Mái:");

        jLabel13.setText("Phòng khách:");

        jLabel14.setText("Phòng ngủ:");

        jLabel15.setText("Phòng ăn:");

        jLabel31.setText("Nhà vệ sinh:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField_hientrang_gac, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_hientrang_phongngu, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_hientrang_phongkhach, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_hientrang_mai, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_hientrang_tuong, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_hientrang_phongan, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_hientrang_solau)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField_hientrang_nhavesinh))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_hientrang_solau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField_hientrang_gac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField_hientrang_tuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField_hientrang_mai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField_hientrang_phongkhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField_hientrang_phongngu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_hientrang_phongan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_hientrang_nhavesinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Vị trí"));
        jPanel6.setToolTipText("");
        jPanel6.setName(""); // NOI18N

        jLabel16.setText("MT:");

        jLabel17.setText("MTNB:");

        jLabel18.setText("Hẻm chính:");

        jLabel19.setText("Hẻm phụ:");

        jLabel20.setText("Cách đường:");

        jLabel21.setText("Lề đường:");

        jLabel22.setText("Hướng:");

        jComboBox_vt_huong.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Đông", "Tây", "Nam", "Bắc", "Đông nam", "Đông bắc", "Tây nam", "Tây bắc" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel20)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_vt_hemphu)
                            .addComponent(jTextField_vt_cachduong)
                            .addComponent(jTextField_vt_leduong)
                            .addComponent(jTextField_vt_hemchinh)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jComboBox_vt_huong, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_vt_mt)
                            .addComponent(jTextField_vt_mtnb))))
                .addGap(3, 3, 3))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField_vt_mt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTextField_vt_mtnb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField_vt_hemchinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTextField_vt_hemphu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jTextField_vt_cachduong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTextField_vt_leduong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jComboBox_vt_huong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Loại bất động sản"));
        jPanel7.setToolTipText("");
        jPanel7.setName(""); // NOI18N

        jCheckBox_lbds_nhapho.setText("Nhà phố");
        jCheckBox_lbds_nhapho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_lbds_nhaphoActionPerformed(evt);
            }
        });

        jCheckBox_lbds_bietthu.setText("Biệt thự");
        jCheckBox_lbds_bietthu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_lbds_bietthuActionPerformed(evt);
            }
        });

        jCheckBox_lbds_canhochungcu.setText("Căn hộ chung cư");
        jCheckBox_lbds_canhochungcu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_lbds_canhochungcuActionPerformed(evt);
            }
        });

        jCheckBox_lbds_caoocvanphong.setText("Cao ốc văn phòng");
        jCheckBox_lbds_caoocvanphong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_lbds_caoocvanphongActionPerformed(evt);
            }
        });

        jCheckBox_lbds_matbang.setText("Mặt bằng");
        jCheckBox_lbds_matbang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_lbds_matbangActionPerformed(evt);
            }
        });

        jCheckBox_lbds_dat.setText("Đất");
        jCheckBox_lbds_dat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_lbds_datActionPerformed(evt);
            }
        });

        jLabel23.setText("Khác:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox_lbds_dat)
                            .addComponent(jCheckBox_lbds_nhapho)
                            .addComponent(jCheckBox_lbds_bietthu)
                            .addComponent(jCheckBox_lbds_canhochungcu)
                            .addComponent(jCheckBox_lbds_caoocvanphong)
                            .addComponent(jCheckBox_lbds_matbang))
                        .addContainerGap(143, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField_lbds_khac))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jCheckBox_lbds_nhapho)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox_lbds_bietthu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox_lbds_canhochungcu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox_lbds_caoocvanphong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox_lbds_matbang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox_lbds_dat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextField_lbds_khac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Diện tích và năm xây dựng"));
        jPanel8.setToolTipText("");
        jPanel8.setName(""); // NOI18N

        jLabel24.setText("Chiều ngang:");

        jTextField_dtnxd_chieudai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_dtnxd_chieudaiActionPerformed(evt);
            }
        });

        jLabel25.setText("Chiều dài:");

        jLabel26.setText("DTKV:");

        jLabel27.setText("DTXD:");

        jLabel28.setText("DTSD:");

        jLabel29.setText("DT sân:");

        jLabel30.setText("Năm XD:");

        jDateChooser_dtnxd_nxd.setDateFormatString("MM/yyyy");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_dtnxd_chieungang))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel25)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_dtnxd_chieudai)
                            .addComponent(jTextField_dtnxd_dtsan)
                            .addComponent(jTextField_dtnxd_dtsd)
                            .addComponent(jTextField_dtnxd_dtxd)
                            .addComponent(jTextField_dtnxd_dtkv)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jDateChooser_dtnxd_nxd, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTextField_dtnxd_chieungang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jTextField_dtnxd_chieudai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jTextField_dtnxd_dtkv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jTextField_dtnxd_dtxd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jTextField_dtnxd_dtsd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jTextField_dtnxd_dtsan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(jDateChooser_dtnxd_nxd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Tiện nghi"));
        jPanel9.setToolTipText("");
        jPanel9.setName(""); // NOI18N

        jCheckBox_tiennghi_dhdien.setText("ĐH điện");
        jCheckBox_tiennghi_dhdien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_tiennghi_dhdienActionPerformed(evt);
            }
        });

        jCheckBox_tiennghi_dhnuoc.setText("ĐH nước");
        jCheckBox_tiennghi_dhnuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_tiennghi_dhnuocActionPerformed(evt);
            }
        });

        jCheckBox_tiennghi_internet.setText("Internet");
        jCheckBox_tiennghi_internet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_tiennghi_internetActionPerformed(evt);
            }
        });

        jCheckBox_tiennghi_dienthoai.setText("Điện thoại");
        jCheckBox_tiennghi_dienthoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_tiennghi_dienthoaiActionPerformed(evt);
            }
        });

        jCheckBox_tiennghi_truyenhinhcap.setText("Truyền hình cáp");
        jCheckBox_tiennghi_truyenhinhcap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_tiennghi_truyenhinhcapActionPerformed(evt);
            }
        });

        jCheckBox_tiennghi_hoboi.setText("Hồ bơi");
        jCheckBox_tiennghi_hoboi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_tiennghi_hoboiActionPerformed(evt);
            }
        });

        jCheckBox_tiennghi_garage.setText("Garage");
        jCheckBox_tiennghi_garage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_tiennghi_garageActionPerformed(evt);
            }
        });

        jLabel33.setText("Máy lạnh:");

        jLabel34.setText("Máy n.nóng:");

        jLabel35.setText("Máy phát điện:");

        jLabel36.setText("Khác:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox_tiennghi_internet)
                            .addComponent(jLabel33))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox_tiennghi_dienthoai)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox_tiennghi_hoboi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox_tiennghi_garage))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jTextField_tiennghi_maylanh))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jCheckBox_tiennghi_dhdien)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox_tiennghi_dhnuoc)
                        .addGap(10, 10, 10)
                        .addComponent(jCheckBox_tiennghi_truyenhinhcap))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35)
                            .addComponent(jLabel36))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_tiennghi_khac)
                            .addComponent(jTextField_tiennghi_mayphatdien)
                            .addComponent(jTextField_tiennghi_maynuocnong)))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox_tiennghi_dhdien)
                    .addComponent(jCheckBox_tiennghi_dhnuoc)
                    .addComponent(jCheckBox_tiennghi_truyenhinhcap))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox_tiennghi_internet)
                    .addComponent(jCheckBox_tiennghi_dienthoai)
                    .addComponent(jCheckBox_tiennghi_hoboi)
                    .addComponent(jCheckBox_tiennghi_garage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jTextField_tiennghi_maylanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jTextField_tiennghi_maynuocnong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jTextField_tiennghi_mayphatdien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jTextField_tiennghi_khac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Tiện ích"));
        jPanel10.setToolTipText("");
        jPanel10.setName(""); // NOI18N

        jLabel32.setText("T.mẫu giáo:");

        jTextField_tienich_truongcap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_tienich_truongcapActionPerformed(evt);
            }
        });

        jLabel37.setText("Trường cấp:");

        jLabel38.setText("T.CĐ-ĐH:");

        jLabel39.setText("Công viên:");

        jLabel40.setText("Chợ/Siêu thị:");

        jLabel41.setText("Ngân hàng:");

        jLabel42.setText("Bệnh viện:");

        jLabel43.setText("Khác:");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 171, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 69, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_tienich_truongmaugiao)
                    .addComponent(jTextField_tienich_truongcap)
                    .addComponent(jTextField_tienich_nganhang)
                    .addComponent(jTextField_tienich_chosieuthi)
                    .addComponent(jTextField_tienich_congvien)
                    .addComponent(jTextField_tienich_truongcddh)
                    .addComponent(jTextField_tienich_benhvien)
                    .addComponent(jTextField_tienich_khac)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jTextField_tienich_truongmaugiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jTextField_tienich_truongcap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jTextField_tienich_truongcddh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jTextField_tienich_congvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jTextField_tienich_chosieuthi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jTextField_tienich_nganhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_tienich_benhvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_tienich_khac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addGap(42, 42, 42)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Tình trạng pháp lý"));
        jPanel11.setToolTipText("");
        jPanel11.setName(""); // NOI18N

        jCheckBox_ttpl_sohong.setText("Sổ hồng");
        jCheckBox_ttpl_sohong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_ttpl_sohongActionPerformed(evt);
            }
        });

        jCheckBox_ttpl_sodo.setText("Sổ đỏ");
        jCheckBox_ttpl_sodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_ttpl_sodoActionPerformed(evt);
            }
        });

        jLabel47.setText("Khác:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox_ttpl_sohong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox_ttpl_sodo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_ttpl_khac))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox_ttpl_sohong)
                    .addComponent(jCheckBox_ttpl_sodo)
                    .addComponent(jLabel47)
                    .addComponent(jTextField_ttpl_khac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Ghi chú"));

        jTextArea_ghichu.setColumns(20);
        jTextArea_ghichu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextArea_ghichu.setRows(2);
        jScrollPane2.setViewportView(jTextArea_ghichu);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
        );

        jButton_luu.setText("Lưu");
        jButton_luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_luuActionPerformed(evt);
            }
        });

        jButton_them.setText("Thêm");
        jButton_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_themActionPerformed(evt);
            }
        });

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Địa chỉ BDS"));

        jLabel44.setText("Địa chỉ:");

        jButton_vt_chondc.setText("Chọn");
        jButton_vt_chondc.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton_vt_chondc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_vt_chondcActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_vt_diachi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_vt_chondc))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel44)
                .addComponent(jTextField_vt_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton_vt_chondc))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Giá giao dịch"));

        jLabel46.setText("Giá:");

        jLabel48.setText("/");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_giagd_ban, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox_giagd_ban_tiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox_giagd_ban_donvi, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 8, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(jTextField_giagd_ban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_giagd_ban_tiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48)
                    .addComponent(jComboBox_giagd_ban_donvi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane_giagd.addTab("   Giá bán   ", jPanel12);

        jLabel49.setText("Giá:");

        jLabel50.setText("/");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_giagd_thue, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox_giagd_thue_tiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox_giagd_thue_donvi, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(0, 8, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(jTextField_giagd_thue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_giagd_thue_tiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50)
                    .addComponent(jComboBox_giagd_thue_donvi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane_giagd.addTab("   Giá thuê   ", jPanel13);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane_giagd)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTabbedPane_giagd, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane_giagd.getAccessibleContext().setAccessibleDescription("");

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin cơ bản"));
        jPanel16.setToolTipText("");
        jPanel16.setName(""); // NOI18N

        jLabel1.setText("Mã phiếu:");

        jTextField_id.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField_id.setEnabled(false);

        jDateChooser_ngay.setDateFormatString("dd/MM/yyyy");

        jLabel4.setText("Nhân viên GD:");

        jTextField_nhanvien.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField_nhanvien.setEnabled(false);

        jLabel2.setText("Ngày:");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_id, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser_ngay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField_nhanvien))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jDateChooser_ngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField_nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton_luu)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_them)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_luu)
                            .addComponent(jButton_them))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        Load_pre_value();
        if(mode.equals("xemchitiet") || mode.equals("sua"))
        {
            Load_exist_info();
        }
        else
        {
            Load_blank_info();
        }
        //phân chức năng
        jButton_luu.setEnabled(this.mode.equals("sua"));
        jButton_them.setEnabled(this.mode.equals("them"));
        
        pack();
    }//GEN-LAST:event_formWindowOpened

    private void jCheckBox_ttpl_sodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_ttpl_sodoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_ttpl_sodoActionPerformed

    private void jCheckBox_ttpl_sohongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_ttpl_sohongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_ttpl_sohongActionPerformed

    private void jTextField_tienich_truongcapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_tienich_truongcapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_tienich_truongcapActionPerformed

    private void jCheckBox_tiennghi_garageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_tiennghi_garageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_tiennghi_garageActionPerformed

    private void jCheckBox_tiennghi_hoboiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_tiennghi_hoboiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_tiennghi_hoboiActionPerformed

    private void jCheckBox_tiennghi_truyenhinhcapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_tiennghi_truyenhinhcapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_tiennghi_truyenhinhcapActionPerformed

    private void jCheckBox_tiennghi_dienthoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_tiennghi_dienthoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_tiennghi_dienthoaiActionPerformed

    private void jCheckBox_tiennghi_internetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_tiennghi_internetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_tiennghi_internetActionPerformed

    private void jCheckBox_tiennghi_dhnuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_tiennghi_dhnuocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_tiennghi_dhnuocActionPerformed

    private void jCheckBox_tiennghi_dhdienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_tiennghi_dhdienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_tiennghi_dhdienActionPerformed

    private void jTextField_dtnxd_chieudaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_dtnxd_chieudaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_dtnxd_chieudaiActionPerformed

    private void jCheckBox_lbds_datActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_lbds_datActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_lbds_datActionPerformed

    private void jCheckBox_lbds_matbangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_lbds_matbangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_lbds_matbangActionPerformed

    private void jCheckBox_lbds_caoocvanphongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_lbds_caoocvanphongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_lbds_caoocvanphongActionPerformed

    private void jCheckBox_lbds_canhochungcuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_lbds_canhochungcuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_lbds_canhochungcuActionPerformed

    private void jCheckBox_lbds_bietthuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_lbds_bietthuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_lbds_bietthuActionPerformed

    private void jCheckBox_lbds_nhaphoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_lbds_nhaphoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_lbds_nhaphoActionPerformed

    private void jTextField_hientrang_gacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_hientrang_gacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_hientrang_gacActionPerformed

    private void jCheckBox_ttgd_timsangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_ttgd_timsangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_ttgd_timsangActionPerformed

    private void jCheckBox_ttgd_timmuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_ttgd_timmuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_ttgd_timmuaActionPerformed

    private void jCheckBox_ttgd_timthueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_ttgd_timthueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_ttgd_timthueActionPerformed

    private void jCheckBox_ttgd_banActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_ttgd_banActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_ttgd_banActionPerformed

    private void jButton_luuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_luuActionPerformed
        // TODO add your handling code here:
        DTO_TTBDS obj_tmp = Get_form_data();
        Boolean re = new BLL_TTBDS().Sua(obj_tmp);
        if(re)
        {
            JOptionPane.showMessageDialog(null, "Lưu thành công!");
        }
    }//GEN-LAST:event_jButton_luuActionPerformed

    private void jButton_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_themActionPerformed
        // TODO add your handling code here:
        //get data
        DTO_TTBDS obj_tmp = Get_form_data();
        
        int re = new BLL_TTBDS().Them(obj_tmp);
        if(re>0)
        {
            JOptionPane.showMessageDialog(null, "Thêm thành công!\nMã phiếu mới thêm vào là "+re);
        }
        jTextField_id.setText(re+"");
    }//GEN-LAST:event_jButton_themActionPerformed

    private void jButton_csh_chondcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_csh_chondcActionPerformed
        // TODO add your handling code here:
        F_DIACHI_PICKUP vd = new F_DIACHI_PICKUP(this, true, this.mode, this.obj.fk_csh_diachi);
        vd.setVisible(true);
        //chọn xong
        //cập nhật obj_csh_diachi và textfield
        this.obj.fk_csh_diachi = vd.result;
        jTextField_csh_diachi.setText(this.obj.fk_csh_diachi._ToString());
    }//GEN-LAST:event_jButton_csh_chondcActionPerformed

    private void jButton_vt_chondcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_vt_chondcActionPerformed
        // TODO add your handling code here:
        F_DIACHI_PICKUP vd = new F_DIACHI_PICKUP(this, true, this.mode, this.obj.fk_vitri.fk_diachi);
        vd.setVisible(true);
        //chọn xong
        //cập nhật obj_csh_diachi và textfield
        this.obj.fk_vitri.fk_diachi = vd.result;
        jTextField_vt_diachi.setText(this.obj.fk_vitri.fk_diachi._ToString());
    }//GEN-LAST:event_jButton_vt_chondcActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(F_TTBDS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(F_TTBDS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(F_TTBDS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(F_TTBDS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new F_TTBDS().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_csh_chondc;
    private javax.swing.JButton jButton_luu;
    private javax.swing.JButton jButton_them;
    private javax.swing.JButton jButton_vt_chondc;
    private javax.swing.JCheckBox jCheckBox_lbds_bietthu;
    private javax.swing.JCheckBox jCheckBox_lbds_canhochungcu;
    private javax.swing.JCheckBox jCheckBox_lbds_caoocvanphong;
    private javax.swing.JCheckBox jCheckBox_lbds_dat;
    private javax.swing.JCheckBox jCheckBox_lbds_matbang;
    private javax.swing.JCheckBox jCheckBox_lbds_nhapho;
    private javax.swing.JCheckBox jCheckBox_tiennghi_dhdien;
    private javax.swing.JCheckBox jCheckBox_tiennghi_dhnuoc;
    private javax.swing.JCheckBox jCheckBox_tiennghi_dienthoai;
    private javax.swing.JCheckBox jCheckBox_tiennghi_garage;
    private javax.swing.JCheckBox jCheckBox_tiennghi_hoboi;
    private javax.swing.JCheckBox jCheckBox_tiennghi_internet;
    private javax.swing.JCheckBox jCheckBox_tiennghi_truyenhinhcap;
    private javax.swing.JCheckBox jCheckBox_ttgd_ban;
    private javax.swing.JCheckBox jCheckBox_ttgd_chothue;
    private javax.swing.JCheckBox jCheckBox_ttgd_sangnhuong;
    private javax.swing.JCheckBox jCheckBox_ttgd_timmua;
    private javax.swing.JCheckBox jCheckBox_ttgd_timsang;
    private javax.swing.JCheckBox jCheckBox_ttgd_timthue;
    private javax.swing.JCheckBox jCheckBox_ttpl_sodo;
    private javax.swing.JCheckBox jCheckBox_ttpl_sohong;
    private javax.swing.JComboBox jComboBox_csh_gioitinh;
    private javax.swing.JComboBox jComboBox_giagd_ban_donvi;
    private javax.swing.JComboBox jComboBox_giagd_ban_tiente;
    private javax.swing.JComboBox jComboBox_giagd_thue_donvi;
    private javax.swing.JComboBox jComboBox_giagd_thue_tiente;
    private javax.swing.JComboBox jComboBox_vt_huong;
    private com.toedter.calendar.JDateChooser jDateChooser_dtnxd_nxd;
    private com.toedter.calendar.JDateChooser jDateChooser_ngay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane_giagd;
    private javax.swing.JTextArea jTextArea_ghichu;
    private javax.swing.JTextField jTextField_csh_diachi;
    private javax.swing.JTextField jTextField_csh_dienthoai;
    private javax.swing.JTextField jTextField_csh_email;
    private javax.swing.JTextField jTextField_csh_hoten;
    private javax.swing.JTextField jTextField_dtnxd_chieudai;
    private javax.swing.JTextField jTextField_dtnxd_chieungang;
    private javax.swing.JTextField jTextField_dtnxd_dtkv;
    private javax.swing.JTextField jTextField_dtnxd_dtsan;
    private javax.swing.JTextField jTextField_dtnxd_dtsd;
    private javax.swing.JTextField jTextField_dtnxd_dtxd;
    private javax.swing.JTextField jTextField_giagd_ban;
    private javax.swing.JTextField jTextField_giagd_thue;
    private javax.swing.JTextField jTextField_hientrang_gac;
    private javax.swing.JTextField jTextField_hientrang_mai;
    private javax.swing.JTextField jTextField_hientrang_nhavesinh;
    private javax.swing.JTextField jTextField_hientrang_phongan;
    private javax.swing.JTextField jTextField_hientrang_phongkhach;
    private javax.swing.JTextField jTextField_hientrang_phongngu;
    private javax.swing.JTextField jTextField_hientrang_solau;
    private javax.swing.JTextField jTextField_hientrang_tuong;
    private javax.swing.JTextField jTextField_id;
    private javax.swing.JTextField jTextField_lbds_khac;
    private javax.swing.JTextField jTextField_nhanvien;
    private javax.swing.JTextField jTextField_tienich_benhvien;
    private javax.swing.JTextField jTextField_tienich_chosieuthi;
    private javax.swing.JTextField jTextField_tienich_congvien;
    private javax.swing.JTextField jTextField_tienich_khac;
    private javax.swing.JTextField jTextField_tienich_nganhang;
    private javax.swing.JTextField jTextField_tienich_truongcap;
    private javax.swing.JTextField jTextField_tienich_truongcddh;
    private javax.swing.JTextField jTextField_tienich_truongmaugiao;
    private javax.swing.JTextField jTextField_tiennghi_khac;
    private javax.swing.JTextField jTextField_tiennghi_maylanh;
    private javax.swing.JTextField jTextField_tiennghi_maynuocnong;
    private javax.swing.JTextField jTextField_tiennghi_mayphatdien;
    private javax.swing.JTextField jTextField_ttpl_khac;
    private javax.swing.JTextField jTextField_vt_cachduong;
    private javax.swing.JTextField jTextField_vt_diachi;
    private javax.swing.JTextField jTextField_vt_hemchinh;
    private javax.swing.JTextField jTextField_vt_hemphu;
    private javax.swing.JTextField jTextField_vt_leduong;
    private javax.swing.JTextField jTextField_vt_mt;
    private javax.swing.JTextField jTextField_vt_mtnb;
    // End of variables declaration//GEN-END:variables
}
