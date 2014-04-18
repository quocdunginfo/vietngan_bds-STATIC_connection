/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vietngan_bds.Utilities;

import java.util.ArrayList;
import javax.swing.JComboBox;
import vietngan_bds.DataTranferObject.ComboBox_Item;

/**
 *
 * @author quocdunginfo
 */
public class UTL_ComboBox {
    /**
    * SET ComboxBox DATA (cách 1)
    * . ComboxBox sẽ bị Clear trước khi SET DATA
    * @return void
    */
    public static void SetComboBoxData(JComboBox _combobox, ArrayList<ComboBox_Item> _listitem)
    {
        _combobox.removeAllItems();
        for(ComboBox_Item tmp:_listitem)
        {
            _combobox.addItem(tmp);
        }
    }
    /**
    * SET ComboxBox DATA (cách 2), _value và _label phải có cùng size, 
    * nếu khác size thì sẽ lấy theo size nhỏ nhất để ADD
    * . ComboxBox sẽ bị Clear trước khi SET DATA
    * 
    * @return void
    */
    public static void SetComboBoxData(JComboBox _combobox, ArrayList<Object> _value, ArrayList<String> _label)
    {
        _combobox.removeAllItems();
        ComboBox_Item item;
        int minindex = _value.size()<_label.size()?_value.size():_label.size();
        int i;
        for(i=0;i<minindex;i++)
        {
            item = new ComboBox_Item(_label.get(i), _value.get(i));
            _combobox.addItem(item);
        }
    }
    /**
    * Thêm vào _combobox một Item mới (cách 1)
    * 
    * @return void
    */
    public static void AddComboBoxItem(JComboBox _combobox, ComboBox_Item _item)
    {
        _combobox.addItem(_item);
    }
    /**
    * Thêm vào _combobox một Item mới (cách 2), 
    * _value: giá trị đại diện cho Item (ẩn), 
    * _label: tên đại diện cho Item (cái hiển thị trên ComboBox)
    * 
    * @return void
    */
    public static void AddComboBoxItem(JComboBox _combobox, Object _value, String _label)
    {
        ComboBox_Item item =new ComboBox_Item(_label, _value);
        _combobox.addItem(item);
    }
    /**
    * Lấy ComboBox Item tại vị trí index
    * 
    * @return ComboBox_Item
    */
    public static ComboBox_Item GetComboBoxItem(JComboBox _combobox, int index)
    {
        if(index<0 || index>=_combobox.getItemCount()) return null;
        return (ComboBox_Item)_combobox.getItemAt(index);
    }
    /**
    * Lấy ComboBox Item đang được chọn (Selected)
    * 
    * @return ComboBox_Item (null nếu lỗi)
    */
    public static ComboBox_Item GetComboBoxItem(JComboBox _combobox)
    {
        if(_combobox.getItemCount()<=0) return null;
        ComboBox_Item item = (ComboBox_Item)_combobox.getSelectedItem();
        return item;
    }
    /**
    * Lấy Value kiểu int của ComboBox Item thứ index
    * 
    * @return int (-1 nếu lỗi)
    */
    public static int GetComboBoxValueInt(JComboBox _combobox, int index)
    {
        if(index<0 || index>=_combobox.getItemCount()) return -1;
        ComboBox_Item item = (ComboBox_Item)_combobox.getItemAt(index);
        return Integer.parseInt(item.Value.toString());
    }
    /**
    * Lấy Value kiểu int của Item đang được chọn (Selected)
    * 
    * @return int (-1 nếu lỗi)
    */
    public static int GetComboBoxValueInt(JComboBox _combobox)
    {
        if(_combobox.getItemCount()<=0) return -1;
        ComboBox_Item item = (ComboBox_Item)_combobox.getSelectedItem();
        return Integer.parseInt(item.Value.toString());
    }
    /**
    * SET ComboxBox Selected Item với Item.value=_value
    * 
    * @return true nếu _value có tồn tại (set thành công), false nếu _value không tồn tại (set thất bại)
    */
    public static Boolean SetComboBoxSelectedItem(JComboBox _combobox, Object _value)
    {
        int i;
        for(i=0;i<_combobox.getItemCount();i++)
        {
            ComboBox_Item tmp = (ComboBox_Item)_combobox.getItemAt(i);
            if(tmp.Value.toString().equals(_value.toString()))
            {
                _combobox.setSelectedIndex(i);
                return true;
            }
        }
        return false;
    }
}
