package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class InvoiceItems extends AbstractTableModel {

    private ArrayList<InvoiceItemsData> arrItemsData;
    private String[] column = {"Item Name", "Item Price", "Count", "Item Total"};

    public InvoiceItems(ArrayList<InvoiceItemsData> itemsArray) {
        this.arrItemsData = itemsArray;
    }

    @Override
    public int getRowCount() {
        return arrItemsData == null ? 0 : arrItemsData.size();
    }

    @Override
    public int getColumnCount() {
        return column.length;
    }

    @Override
    public Object getValueAt(int rowNo, int colNo) {
        if (arrItemsData == null) {
            return "";
        } else {
            InvoiceItemsData item = arrItemsData.get(rowNo);
            if(colNo==0){
                return item.getItemName();
            }else if(colNo==1){
                return item.getItemPrice();
            }else if(colNo==2){
                return item.getItemCount();
            }else if(colNo==3){
                return item.getItemsTotal();
            }else { return "";}
            
        }
    }

    @Override
    public String getColumnName(int c) {
        return column[c];
    }

}
