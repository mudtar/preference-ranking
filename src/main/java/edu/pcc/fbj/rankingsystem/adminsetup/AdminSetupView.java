package edu.pcc.fbj.rankingsystem.adminsetup;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * This class I read in a combination of view and controller
 * my task will be to seperate the functionality... forcing SWING into
 * a more MVC design... so, I think
 *
 * @author Eric Kristiansen
 * @version 4/19/16
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

        //itemList = new JList();
        //itemList.setModel(AdminSetupController.setItems());
    }

    /**
     * This is just a function I was playing with to better understand the list setup
     * It is currently called on the add button handler
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
     * This function sets a border on the root panel, because the default is extremely bad
     * looking
     * @return JPanel currently returned to the Main class in order to instantiate the GUI
     */
    public JPanel getRootPanel()
    {
        rootPanel.setBorder(new javax.swing.border.EmptyBorder(20, 50, 20, 50));
        return rootPanel;
    }

    /**
     * set the initial item list
     * @param passList is list of item objects
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
     * @return JList returned my idea was to separate work done on this control in
     * a controller class... Not sure yet if that is appropriate
     */
    public JList getItemList()
    {
        return itemList;
    }

    /**
     *
     */
    public void removeItem()
    {
        listModel.remove(itemList.getSelectedIndex());
        // set data in itemList
        itemList.setModel(listModel);
    }

    /**
     *
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
     *
     */
    public void cancelAdmin()
    {
        listModel.removeAllElements();
        // set data in itemList
        itemList.setModel(listModel);

        JOptionPane.showMessageDialog(rootPanel, "You have canceled, and will exit");
        AdminSetupController.closeFrame();
    }



    /**
     * @return JTextField returned my idea was to separate work done on this control in
     * a controller class... Not sure yet if that is appropriate
     */
    public JTextField getTextField()
    {
        return itemTextField;
    }




}