package edu.pcc.fbj.rankingsystem.adminsetup;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the view Class. It holds everything intended to be part of the GUI,
 * as well as the handlers for those controls.
 *
 * @author Eric Kristiansen
 * @version 5/3/16
 */
public class AdminSetupView
{
    private JPanel rootPanel;
    private JList itemList;
    private JTextField itemTextField;
    private JButton finishedButton;
    private JButton removeButton;
    private JButton cancelButton;
    private JLabel errorLabel;

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

        itemList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                checkListSelection();
            }
        });
    }

    /**
     * This method takes the value of the textfield, and
     * appends the list of items.
     */
    public void appendTextFieldText()
    {
        if (validateItemField())
        {
            // add items to a default list
            listModel.addElement(itemTextField.getText());
            itemTextField.setText("");

            // set data in itemList
            itemList.setModel(listModel);

            checkListLength();
        }

    }

    /**
     *
     * @return true if item length is less than 18
     * the database field is varchar(20)
     */
    public Boolean validateItemField()
    {
        if (itemTextField.getText().length() < 18)
        {
            resetErrorLabel();
            return true;
        }
        else
        {
            itemTextField.requestFocus();
            errorLabel.setText("Error: Item name too long");
            return false;
        }
    }

    /**
     * This method checks the list length to enable or disable the finish button
     * based on the criteria of > 2 items
     */
    public void checkListLength()
    {
        if(listModel.getSize() > 2)
        {
            finishedButton.setEnabled(true);
        }
        else
        {
            finishedButton.setEnabled(false);
        }
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
            itemList.setSelectedIndex(-1);

            // enable and disable components
            checkListLength();
            checkListSelection();
        }
        catch(ArrayIndexOutOfBoundsException ex) {
            System.out.println(ex.toString());
        }
    }

    /**
     * Enable or disable Remove Item Button
     * based on selected Index
     */
     private void checkListSelection()
     {
         if (itemList.getSelectedIndex() != -1)
         {
             removeButton.setEnabled(true);
         }
         else
         {
             removeButton.setEnabled(false);
         }
     }

    /**
     * pass the itemList back to the controller for use by the model, and
     * display a finished message
     */
    public void finishedMessage()
    {
        ArrayList<Item> passItems = new ArrayList<>();

        for(int i = 0; i < listModel.size(); i++)
        {
            passItems.add(new Item(listModel.getElementAt(i).toString()));
        }

        resetErrorLabel();
        itemTextField.setText("");
        AdminSetupController.setItems(passItems);
        AdminSetupController.closeFrame();
    }

    /**
     * Cancel, do not write list to database, close frame
     */
    public void cancelAdmin()
    {
        itemTextField.setText("");
        listModel.removeAllElements();
        resetErrorLabel();
        // set data in itemList
        setItemList(AdminSetupController.getItems());
        setInitialFocus();
    }

    /**
     *
     */
    private void resetErrorLabel()
    {
        errorLabel.setText("Enter Items Below:");
    }

    /**
     * Set Focus to itemList
     */
    public void setInitialFocus()
    {
        itemTextField.requestFocus();
    }
}