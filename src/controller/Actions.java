package controller;

import view.MainJFrame;
import model.InvoiceTableData;
import model.InvoiceTable;
import model.InvoiceItemsData;
import model.InvoiceItems;
import view.InvoiceTableDialog;
import view.InvoiceItemDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class Actions implements ActionListener {

    private MainJFrame jFrame;
    private InvoiceTableDialog tableDialog;
    private InvoiceItemDialog itemDialog;

    public Actions(MainJFrame jFrame) {
        this.jFrame = jFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            
            case "Load File":
                loadFile();
                break;

            case "Save File":
                saveFile();
                break;

            case "Create New Invoice":
                createNewInvoice();
                break;

            case "Delete Invoice":
                deleteInvoice();
                break;

            case "Add Item":
                createNewItem();
                break;

            case "Delete Item":
                deleteItem();
                break;

            case "addCustomer":
                addCustomer();
                break;

            case "discardCustomer":
                discardCustomer();
                break;

            case "addItem":
                addItem();
                break;    

            case "discardItem":
                discardItem();
                break;
        }
    }

    private void loadFile() {
        JFileChooser fileChooser = new JFileChooser();
        try {
            int result = fileChooser.showOpenDialog(jFrame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File tableFile = fileChooser.getSelectedFile();
                Path tablePath = Paths.get(tableFile.getAbsolutePath());
                List<String> tableDATA = Files.readAllLines(tablePath);
                ArrayList<InvoiceTableData> iTableData = new ArrayList<>();
                for (String tableData : tableDATA) {
                    String[] arr = tableData.split(",");
                    int no = Integer.parseInt(arr[0]);
                    Date date = MainJFrame.sFormat.parse(arr[1]);
                    InvoiceTableData invTableData = new InvoiceTableData(no, arr[2], date);
                    iTableData.add(invTableData);
                }
                jFrame.setTableArray(iTableData);

                result = fileChooser.showOpenDialog(jFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File itemFile = fileChooser.getSelectedFile();
                    Path itemPath = Paths.get(itemFile.getAbsolutePath());
                    List<String> itemsData = Files.readAllLines(itemPath);
                    //ArrayList<InvoiceItemsData> iItemsData = new ArrayList<>();
                    for (String itemData : itemsData) {
                        String[] arr = itemData.split(",");
                        int invId = Integer.parseInt(arr[0]);
                        double price = Double.parseDouble(arr[2]);
                        int count = Integer.parseInt(arr[3]);
                        InvoiceTableData inv = jFrame.getHeader(invId);
                        InvoiceItemsData invItemsData = new InvoiceItemsData(arr[1], price, count, inv);
                        inv.getItems().add(invItemsData);
                    }
                }
                InvoiceTable iTable = new InvoiceTable(iTableData);
                jFrame.setiTable(iTable);
                jFrame.getInvTable().setModel(iTable);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(jFrame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(jFrame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void saveFile() {
        ArrayList<InvoiceTableData> tableArray = jFrame.getTableArray();
        JFileChooser filechooser = new JFileChooser();
        try {
            int result = filechooser.showSaveDialog(jFrame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File tableFile = filechooser.getSelectedFile();
                FileWriter TFileWriter = new FileWriter(tableFile);
                String table = "";
                String items = "";
                for (InvoiceTableData iTableData : tableArray) {
                    table += iTableData.toString();
                    table += "\n";
                    for (InvoiceItemsData iItemsData : iTableData.getItems()) {
                        items += iItemsData.toString();
                        items += "\n";
                    }
                }
                table = table.substring(0, table.length()-1);
                items = items.substring(0, items.length()-1);
                result = filechooser.showSaveDialog(jFrame);
                File itemsFile = filechooser.getSelectedFile();
                FileWriter iFileWriter = new FileWriter(itemsFile);
                TFileWriter.write(table);
                iFileWriter.write(items);
                TFileWriter.close();
                iFileWriter.close();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(jFrame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createNewInvoice() {
        tableDialog = new InvoiceTableDialog(jFrame);
        tableDialog.setVisible(true);
    }

    private void deleteInvoice() {
        int selectedInvoice = jFrame.getInvTable().getSelectedRow();
        if (selectedInvoice != -1) {
            jFrame.getTableArray().remove(selectedInvoice);
            jFrame.getiTable().fireTableDataChanged();

            jFrame.getItemTable().setModel(new InvoiceItems(null));
            jFrame.setItemsArray(null);
            jFrame.getCurrCustomerName().setText("");
            jFrame.getCurrInvoiceNumber().setText("");
            jFrame.getCurrTotal().setText("");
            jFrame.getCurrDate().setText("");
        }
    }

    private void createNewItem() {
        itemDialog = new InvoiceItemDialog(jFrame);
        itemDialog.setVisible(true);
    }

    private void deleteItem() {
        int selectedItem = jFrame.getItemTable().getSelectedRow();
        int selectedInvoice = jFrame.getInvTable().getSelectedRow();
        if (selectedItem != -1) {
            jFrame.getItemsArray().remove(selectedItem);
            InvoiceItems itemTable = (InvoiceItems) jFrame.getItemTable().getModel();
            itemTable.fireTableDataChanged();
            jFrame.getCurrTotal().setText("" + jFrame.getTableArray().get(selectedInvoice).getInvoiceTotal());
            jFrame.getiTable().fireTableDataChanged();
            jFrame.getInvTable().setRowSelectionInterval(selectedInvoice, selectedInvoice);
        }
    }
    
    private void addCustomer() {
        tableDialog.setVisible(false);

        String customerName = tableDialog.getCustomerNameField().getText();
        String customerData = tableDialog.getCustomerDateField().getText();
        Date iDate = new Date();
        try {
            iDate = MainJFrame.sFormat.parse(customerData);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(jFrame, "Invalid date format","Resetting to todays date." , JOptionPane.ERROR_MESSAGE);
        }

        int iNo = 0;
        for (InvoiceTableData iTableData : jFrame.getTableArray()) {
            if (iTableData.getNo() > iNo) {
                iNo = iTableData.getNo();
            }
        }
        iNo++;
        InvoiceTableData newCustomer = new InvoiceTableData(iNo, customerName, iDate);
        jFrame.getTableArray().add(newCustomer);
        jFrame.getiTable().fireTableDataChanged();
        tableDialog.dispose();
        tableDialog = null;
    }
    
    private void discardCustomer() {
        tableDialog.setVisible(false);
        tableDialog.dispose();
        tableDialog = null;
    }

    private void addItem() {
        itemDialog.setVisible(false);

        String itemName = itemDialog.getItemNameField().getText();
        String itemCount = itemDialog.getItemCountField().getText();
        String itemPrice = itemDialog.getItemPriceField().getText();
        int count = 1;
        double price = 1;
        try {
            count = Integer.parseInt(itemCount);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(jFrame, "Invalid count format"," Cannot convert count" , JOptionPane.ERROR_MESSAGE);
        }

        try {
            price = Double.parseDouble(itemPrice);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(jFrame, "Invalid price format","Cannot convert price" , JOptionPane.ERROR_MESSAGE);
        }
        int selectedInvoice = jFrame.getInvTable().getSelectedRow();
        if (selectedInvoice != -1) {
            InvoiceTableData iTableData = jFrame.getTableArray().get(selectedInvoice);
            InvoiceItemsData iItemsData = new InvoiceItemsData(itemName, price, count, iTableData);
            jFrame.getItemsArray().add(iItemsData);
            InvoiceItems ItemsTable = (InvoiceItems) jFrame.getItemTable().getModel();
            ItemsTable.fireTableDataChanged();
            jFrame.getiTable().fireTableDataChanged();
        }
        jFrame.getInvTable().setRowSelectionInterval(selectedInvoice, selectedInvoice);
        itemDialog.dispose();
        itemDialog = null;
    }
        
    private void discardItem() {
        itemDialog.setVisible(false);
        itemDialog.dispose();
        itemDialog = null;
    }

}
