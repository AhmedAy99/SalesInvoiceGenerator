
package model;

import java.util.Date;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class InvoiceTableData {
    private int no;
        private Date date;
    private String customerName;
    private ArrayList<InvoiceItemsData> items;
    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public InvoiceTableData() {
    }

    public InvoiceTableData(int no, String customerName, Date date) {
        this.no = no;
        this.customerName = customerName;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<InvoiceItemsData> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }
        return items;
    }

    public void setItems(ArrayList<InvoiceItemsData> items) {
        this.items = items;
    }
    
    public double getInvoiceTotal() {
        double total = 0.0;
        
        for (int i = 0; i < getItems().size(); i++) {
            total += getItems().get(i).getItemsTotal();
        }
        
        return total;
    }

    @Override
    public String toString() {
        return no + "," + dateFormat.format(date) + "," + customerName;
    }
    
}
