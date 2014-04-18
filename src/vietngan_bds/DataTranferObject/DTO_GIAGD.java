/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vietngan_bds.DataTranferObject;

/**
 *
 * @author quocdunginfo
 */
public class DTO_GIAGD {
    public int id=0;
    public Double giaban=0.0;
    public DTO_TIENTE fk_giaban_tiente=new DTO_TIENTE();
    public DTO_DONVI fk_giaban_donvi=new DTO_DONVI();
    
    public Double giathue=0.0;
    public DTO_TIENTE fk_giathue_tiente=new DTO_TIENTE();
    public DTO_DONVI fk_giathue_donvi=new DTO_DONVI();
}
