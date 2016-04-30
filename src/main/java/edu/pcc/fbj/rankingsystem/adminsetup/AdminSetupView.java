package edu.pcc.fbj.rankingsystem.adminsetup;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * This is the view Class. It holds everything intended to be part of the GUI,
 * as well as the handlers for those controls.
 *
 * @author Eric Kristiansen
 * @version 4/30/16
 */
public class AdminSetupView {

    private JPanel rootPanel;
    private JList itemList;
    private JTextField itemTextField;
    private JButton finishedButton;
    private JButton removeButton;
    private JButton cancelButton;

    private DefaultListModel listModel = new DefaultListModel();

    /**
     * Constructor holding action handlers
     */
    public AdminSetupView()
    {
        //listeners
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeItem();
            }
        });
        finishedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finishedMessage();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelAdmin();
            }
        });
        itemTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appendTextFieldText();
            }
        });

    }

    /**
     * This method takes the value of the textfield, and
     * appends the list of items.
     */
    public void appendTextFieldText()
    {
        // add items to a default list
        listModel.addElement(itemTextField.getText());

        itemTextField.setText("");

        // set data in itemList
        itemList.setModel(listModel);
    }

    /**
     *
     * This function sets a border on the root panel, and returns it to be used by the controller.
     * @return JPanel returned to the controller for instantiating
     */
    public JPanel getRootPanel()
    {
        rootPanel.setBorder(new javax.swing.border.EmptyBorder(20, 50, 20, 50));
        return rootPanel;
    }

    /**
     * set the initial item list
     * @param passList is list of item objects passed from the controller
     *                 for use here in the view controls
     */
    public void setItemList(List<Item> passList)
    {
        // clear item list
        listModel.clear();

        //add items to list Model
        for (Item item: passList)
        {
            listModel.addElement(item.toString());
        }
        itemList.setModel(listModel);

    }

    /**
     * @return JList returned to the controller for use by the model
     * to update the dataBase
     */
    public JList getItemList()
    {
        return itemList;
    }

    /**
     * This method is called when the user selects an item in the list,
     * and presses the remove item button
     */
    public void removeItem()
    {
        try
        {
            listModel.remove(itemList.getSelectedIndex());
            // set data in itemList
            itemList.setModel(listModel);
        }
        catch(ArrayIndexOutOfBoundsException ex) {
            removeItemExceptionHandler(ex);
        }
    }

    /**
     * Handle array out of bounds issue
     * @param ex out of bounds thrown from removeItem
     *                  -appropriate item not selected
     */
    public void removeItemExceptionHandler(Exception ex)
    {
        JOptionPane.showMessageDialog(rootPanel, "You must choose an appropriate item to remove");
    }

    /**
     * pass the itemList back to the controller for use by the model, and
     * display a finished message
     */
    public void finishedMessage()
    {
        String result = "";
        ArrayList<Item> passItems = new ArrayList<>();

        for(int i = 0; i < listModel.size(); i++) {
            result += listModel.getElementAt(i) + "\n";
            passItems.add(new Item(listModel.getElementAt(i).toString()));
        }

        JOptionPane.showMessageDialog(rootPanel, result + "You are all finished!");
        AdminSetupController.setItems(passItems);
        AdminSetupController.closeFrame();
    }

    /**
     * Cancel, do not write list to database, close frame
     */
    public void cancelAdmin()
    {
        listModel.removeAllElements();
        // set data in itemList
        itemList.setModel(listModel);

        JOptionPane.showMessageDialog(rootPanel, "You have canceled, and will exit");
        AdminSetupController.closeFrame();
    }






}