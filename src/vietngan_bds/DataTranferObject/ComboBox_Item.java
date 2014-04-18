/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vietngan_bds.DataTranferObject;

/**
 *
 * @author quocdunginfo
 */
public class ComboBox_Item {
    public String Text="";
    public Object Value=0;
    public ComboBox_Item(String _Text, Object _Value){
        Text = _Text;
        Value = _Value;
    }
    public ComboBox_Item(){
        
    }
    @Override
    public String toString()
    {
        return Text;
    }
    public int GetValueInt()
    {
        return Integer.parseInt(Value.toString());
    }
}
