/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vietngan_bds.DataTranferObject;

/**
 *
 * @author quocdunginfo
 */
public class DTO_DIACHI {
    public int id=0;
    public DTO_TINHTHANH fk_tinhthanh=new DTO_TINHTHANH();
    public DTO_QUANHUYEN fk_quanhuyen=new DTO_QUANHUYEN();
    public DTO_PHUONGXA fk_phuongxa=new DTO_PHUONGXA();
    public String duong="";
    public String sonha="";
    public String _ToString()
    {
        String re="";
        if(!sonha.equals(""))
        {
            re+=sonha+", ";
        }
        if(!duong.equals(""))
        {
            re+=duong+", ";
        }
        if(fk_phuongxa !=null && fk_phuongxa.id>0)
        {
            re+=fk_phuongxa.name+", ";
        }
        if(fk_quanhuyen !=null && fk_quanhuyen.id>0)
        {
            re+=fk_quanhuyen.name+", ";
        }
        if(fk_tinhthanh !=null && fk_tinhthanh.id>0)
        {
            re+=fk_tinhthanh.name;
        }
        
        return re;
    }
}
