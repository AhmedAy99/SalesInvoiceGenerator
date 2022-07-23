
package model;

public class InvoiceItemsData {
    private String itemName;
    private double itemPrice;
    private int itemCount;
    private InvoiceTableData table;

    public InvoiceItemsData() {
    }

    public InvoiceItemsData(String itemName, double itemPrice, int itemCount, InvoiceTableData table) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
        this.table = table;
    }

    public InvoiceTableData getTable() {
        return table;
    }

    public void setTable(InvoiceTableData table) {
        this.table = table;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
    
    public double getItemsTotal() {
        return itemPrice * itemCount;
    }

    @Override
    public String toString() {
        return table.getNo() + "," + itemName + "," + itemPrice + "," + itemCount;
    }

    
    
}
