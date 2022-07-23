package view;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;


public class InvoiceItemDialog extends JDialog{
    private JTextField itemNameField;
    private JTextField itemCountField;
    private JTextField itemPriceField;
    private JLabel itemNameLable;
    private JLabel itemCountLable;
    private JLabel itemPriceLable;
    private JButton addButton;
    private JButton discardButton;
    
    public InvoiceItemDialog(MainJFrame jFrame) {
        itemNameField = new JTextField(20);
        itemNameLable = new JLabel("Item Name");
        
        itemCountField = new JTextField(20);
        itemCountLable = new JLabel("Item Count");
        
        itemPriceField = new JTextField(20);
        itemPriceLable = new JLabel("Item Price");
        
        addButton = new JButton("Add");
        discardButton = new JButton("Discard");
        
        addButton.setActionCommand("addItem");
        discardButton.setActionCommand("discardItem");
        
        addButton.addActionListener(jFrame.getActionListener());
        discardButton.addActionListener(jFrame.getActionListener());
        setLayout(new GridLayout(4, 2));
        
        add(itemNameLable);
        add(itemNameField);
        add(itemCountLable);
        add(itemCountField);
        add(itemPriceLable);
        add(itemPriceField);
        add(addButton);
        add(discardButton);
        
        pack();
    }

    public JTextField getItemNameField() {
        return itemNameField;
    }

    public JTextField getItemCountField() {
        return itemCountField;
    }

    public JTextField getItemPriceField() {
        return itemPriceField;
    }
}
