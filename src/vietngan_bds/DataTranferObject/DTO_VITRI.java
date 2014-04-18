/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vietngan_bds.DataTranferObject;

/**
 *
 * @author quocdunginfo
 */
public class DTO_VITRI {
    public int id=0;
    public String mt ="";
    public String mtnb ="";
    public String hemchinh ="";
    public String hemphu ="";
    public String cachduong ="";
    public String leduong ="";
    public DTO_DIACHI fk_diachi=new DTO_DIACHI();
    public DTO_HUONG fk_huong=new DTO_HUONG();
}
