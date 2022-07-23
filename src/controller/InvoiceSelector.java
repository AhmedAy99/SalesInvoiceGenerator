package controller;

import javax.swing.event.ListSelectionListener;
import view.MainJFrame;
import javax.swing.event.ListSelectionEvent;
import model.InvoiceTableData;
import java.util.ArrayList;
import model.InvoiceItemsData;
import model.InvoiceItems;

public class InvoiceSelector implements ListSelectionListener {

    private MainJFrame jFrame;

    public InvoiceSelector(MainJFrame jFrame) {
        this.jFrame = jFrame;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedTable = jFrame.getInvTable().getSelectedRow();
        if (selectedTable != -1) {
            InvoiceTableData iTableData = jFrame.getTableArray().get(selectedTable);
            ArrayList<InvoiceItemsData> iItemsData = iTableData.getItems();
            InvoiceItems iItems = new InvoiceItems(iItemsData);
            jFrame.setItemsArray(iItemsData);
            jFrame.getItemTable().setModel(iItems);
            jFrame.getCurrCustomerName().setText(iTableData.getCustomerName());
            jFrame.getCurrInvoiceNumber().setText("" + iTableData.getNo());
            jFrame.getCurrTotal().setText("" + iTableData.getInvoiceTotal());
            jFrame.getCurrDate().setText(MainJFrame.sFormat.format(iTableData.getDate()));
        }
    }

}
