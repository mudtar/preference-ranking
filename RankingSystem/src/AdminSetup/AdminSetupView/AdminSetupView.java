package AdminSetupView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton addButton;
    private JButton finishedButton;
    private JLabel directionsLabel;
    private JButton removeButton;
    private JButton cancelButton;

    private DefaultListModel listModel;

    /**
     * Constructor holding action handlers
     */
    public AdminSetupView()
    {

        //listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addButtonClicked();
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //remove selected items from list
            }
        });

        finishedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //finished ... go do something else
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //cancel the addition of any new data, and go to main page
            }
        });
    }

    /**
     * This class will handle the add Button click code
     * -will probably add the textField string to a struct or class
     * until user hits finish
     */
    public void addButtonClicked()
    {
        listSetUp();
        JOptionPane.showMessageDialog(rootPanel, "This is a dialog");
    }

    /**
     * This is just a function I was playing with to better understand the list setup
     * It is currently called on the add button handler
     */
    public void listSetUp()
    {
        // add items to a default list
        listModel = new DefaultListModel();
        listModel.addElement("Jane Doe");
        listModel.addElement("John Smith");
        listModel.addElement("Kathy Green");

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
     * @return JList returned my idea was to separate work done on this control in
     * a controller class... Not sure yet if that is appropriate
     */
    public JList getItemList()
    {
        return itemList;
    }

    /**
     * @return JList returned my idea was to separate work done on this control in
     * a controller class... Not sure yet if that is appropriate
     */
    public JButton getAddButton()
    {
        return addButton;
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