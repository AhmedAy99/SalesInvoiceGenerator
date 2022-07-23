package view;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;

public class InvoiceTableDialog extends JDialog {
    private JTextField customerNameField;
    private JTextField customerDateField;
    private JLabel customerNameLable;
    private JLabel customerDateLable;
    private JButton addButton;
    private JButton discardButton;

    public InvoiceTableDialog(MainJFrame jFrame) {
        customerNameLable = new JLabel("Customer Name:");
        customerNameField = new JTextField(20);
        customerDateLable = new JLabel("Invoice Date:");
        customerDateField = new JTextField(20);
        addButton = new JButton("Add");
        discardButton = new JButton("Discard");
        
        addButton.setActionCommand("addCustomer");
        discardButton.setActionCommand("discardCustomer");
        
        addButton.addActionListener(jFrame.getActionListener());
        discardButton.addActionListener(jFrame.getActionListener());
        setLayout(new GridLayout(3, 2));
         
        add(customerNameLable);
        add(customerNameField);
        add(customerDateLable);
        add(customerDateField);
        add(addButton);
        add(discardButton);
        
        pack();
        
    }

    public JTextField getCustomerNameField() {
        return customerNameField;
    }

    public JTextField getCustomerDateField() {
        return customerDateField;
    }
    
}
