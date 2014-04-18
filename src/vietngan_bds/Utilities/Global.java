/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vietngan_bds.Utilities;

import vietngan_bds.BusinessLogicLayer.BLL_USER;
import vietngan_bds.DataTranferObject.DTO_USER;

/**
 *
 * @author quocdunginfo
 */
public class Global {
    static public DTO_USER user=new BLL_USER().Get("admin");
}
