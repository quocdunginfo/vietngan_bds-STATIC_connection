/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vietngan_bds.Utilities;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author quocdunginfo
 */
public class UTL_Table
{
    /**
    * SET data cho một JTable (cách 1), mặc định Cell Editor là false
    *
    * @return void
    */
    public static void SetTableData(JTable _table, ArrayList<ArrayList<Object>> _data, ArrayList<Object> _col)
    {
        UTL_Table.SetTableData(_table, _data, _col, false);
    }
    /**
    * SET data cho một JTable (cách 2), chỉ định Enable Cell Editor thông qua "_enable_cell_edit"
    *
    * @return void
    */
    public static void SetTableData(JTable _table ,ArrayList<ArrayList<Object>> _data, ArrayList<Object> _col, java.lang.Boolean _enable_cell_edit)
    {
        //khởi tạo vector object
        Object[][] _objdata = new Object[_data.size()][_col.size()];
        int i,j;
        for(i=0;i<_data.size();i++)
        {
            for(j=0;j<_col.size();j++)
            {
                _objdata[i][j]=_data.get(i).get(j);
            }
        }
        Object[] _objcol = new Object[_col.size()];
        for(i=0;i<_col.size();i++)
        {
            _objcol[i]=_col.get(i);
        }
        DefaultTableModel model;
        if(_enable_cell_edit)
        {
            model = new DefaultTableModel(_objdata, _objcol) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                       //all cells false
                       return true;
                    }
            };
        }
        else
        {
            model = new DefaultTableModel(_objdata, _objcol) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                       //all cells false
                       return false;
                    }
            };
        }
        _table.setModel(model);
    }
    /**
    * Xóa một dòng khỏi JTable
    *
    * @return void
    */
    public static void RemoveTableRow(JTable _table, int rowindex)
    {
        DefaultTableModel tmp = (DefaultTableModel)_table.getModel();
        tmp.removeRow(rowindex);
    }
    /**
    * Xóa tất cả row khỏi JTable
    *
    * @return void
    */
    public static void RemoveAllTableRows(JTable _table)
    {
        while(_table.getRowCount()>0)
        {
            UTL_Table.RemoveTableRow(_table, 0);
        }
    }
    /**
    * Thêm một dòng vào JTable (thêm cuối)
    *
    * @return void
    */
    public static void AddTableRow(JTable _table, ArrayList<Object> _row)
    {
        DefaultTableModel tmp = (DefaultTableModel)_table.getModel();
        Object[] _objrow = new Object[tmp.getColumnCount()];
        int i;
        for(i=0;i<tmp.getColumnCount();i++)
        {
            _objrow[i] = _row.get(i);
        }
        tmp.addRow(_objrow);
    }
    /**
    * Lấy dòng thứ index từ JTable
    *
    * @return ArrayList các Object
    */
    public static ArrayList<Object> GetTableRow(JTable _table, int rowindex)
    {
        DefaultTableModel tmp = (DefaultTableModel)_table.getModel();
        int i;
        ArrayList<Object> re = new ArrayList<Object>();
        if(rowindex<0 || rowindex>=tmp.getRowCount()) return re;
        for(i=0;i<tmp.getColumnCount();i++)
        {
            re.add(tmp.getValueAt(rowindex, i));
        }
        return re;
    }
    /**
    * Lấy danh sách các dòng được chọn trong JTable
    *
    * @return ArrayList các Object
    */
    public static ArrayList<ArrayList<Object>> GetTableSelectedRows(JTable _table)
    {
        DefaultTableModel tmp = (DefaultTableModel)_table.getModel();
        ArrayList<ArrayList<Object>> rows = new ArrayList<ArrayList<Object>>();
        int[] selected_rows = _table.getSelectedRows();
        for(int rowindex:selected_rows)
        {
            rows.add(UTL_Table.GetTableRow(_table, rowindex));
        }
        
        return rows;
    }
}

