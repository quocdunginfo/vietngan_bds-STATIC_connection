/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vietngan_bds.DataTranferObject;

import java.util.Date;

/**
 * Thông tin bất động sản
 * @author quocdunginfo
 */
public class DTO_TTBDS {
    public int id = 0;
    public Date ngay=new Date();
    public String csh_hoten = "";
    public int csh_gioitinh = 1;
    public DTO_DIACHI fk_csh_diachi = new DTO_DIACHI();
    public String csh_dienthoai = "";
    public String csh_email = "";
    public String ghichu="";
    //fk
    public DTO_TTGD fk_thongtin_gd = new DTO_TTGD();
    public DTO_LOAIBDS fk_loai_bds = new DTO_LOAIBDS();
    public DTO_VITRI fk_vitri = new DTO_VITRI();
    public DTO_DTNXD fk_dientich_namxaydung = new DTO_DTNXD();
    public DTO_HIENTRANG fk_hientrang = new DTO_HIENTRANG();
    public DTO_TIENNGHI fk_tiennghi = new DTO_TIENNGHI();
    public DTO_TIENICH fk_tienich = new DTO_TIENICH();
    public DTO_USER fk_user = new DTO_USER();
    public DTO_TINHTRANGPHAPLY fk_tinhtrangphaply=new DTO_TINHTRANGPHAPLY();
    public DTO_GIAGD fk_gia_gd = new DTO_GIAGD();
}
