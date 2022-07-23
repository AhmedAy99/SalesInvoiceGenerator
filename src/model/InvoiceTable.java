package model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import view.MainJFrame;


public class InvoiceTable extends AbstractTableModel {

    private ArrayList<InvoiceTableData> arrTableData;
    private String[] column = {"No.", "Date", "Customer", "Total"};
    
    public InvoiceTable(ArrayList<InvoiceTableData> arrTableData) {
        this.arrTableData = arrTableData;
    }

    @Override
    public int getRowCount() {
        return arrTableData.size();
    }

    @Override
    public int getColumnCount() {
        return column.length;
    }

    @Override
    public Object getValueAt(int rowNo, int colNo) {
        InvoiceTableData table = arrTableData.get(rowNo);
        
        if(colNo==0){
                return table.getNo();
            }else if(colNo==1){
                return MainJFrame.sFormat.format(table.getDate());
            }else if(colNo==2){
                return table.getCustomerName();
            }else if(colNo==3){
                return table.getInvoiceTotal();
            }else { return "";}
    }

    @Override
    public String getColumnName(int c) {
        return column[c];
    }
}
