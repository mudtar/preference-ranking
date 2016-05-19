package edu.pcc.fbj.rankingsystem.adminsetup;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eric Kristiansen
 * @version 5/17/16
 */
public class MultiTabDoc {
    private JTabbedPane tabbedPane1;
    private JPanel rootPanel;
    private JList itemListItemControl;
    private JTextField itemTextFieldItemControl;
    private JButton removeItemButtonItemControl;
    private JButton cancelButtonItemControl;
    private JButton finishedButton;
    private JList testListTestControl;
    private JTextField testTextFieldTestControl;
    private JButton removeTestButtonTestControl;
    private JList itemListTestControl;
    private JList assignedItemListTestControl;
    private JButton assignItemButtonTestControl;
    private JButton removeItemButtonTestControl;
    private JLabel errorLabelTestControl;
    private JLabel imageLabelItemControl;
    private JLabel imageLabelTestControl;
    private JFileChooser fileChooserItemControl;
    private JLabel errorLabelItemControl;
    private JButton finishButtonItemControl;
    private JButton cancelAdminButton;
    private JButton cancelButtonTestControl;
    private JButton SaveButtonTestControl;

    private DefaultListModel itemListModel = new DefaultListModel();
    private DefaultListModel testListModel = new DefaultListModel();

    //Image tools
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Image defaultImage = toolkit.getImage("./noImage200x200.png");
    Icon defaultIcon = new ImageIcon(defaultImage);

    /**
     * Constructor for View Class
     * Set up Handlers and initial components
     */
    public MultiTabDoc() {

        setDefaultIcon();

        /**
         * Simple Handlers Section for Item Control
         */
        itemListItemControl.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) { checkListSelection(); }
        });
        removeItemButtonItemControl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { removeItem(); }
        });
        itemTextFieldItemControl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { appendTextFieldText(); }
        });
        finishedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { finishAdminSetup(); }
        });
        cancelButtonItemControl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { cancelAdmin(); }
        });
        fileChooserItemControl.addPropertyChangeListener(new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent)
            {
                if (fileChooserItemControl.SELECTED_FILE_CHANGED_PROPERTY
                        .equals(propertyChangeEvent.getPropertyName()) &&
                        fileChooserItemControl.getSelectedFile() != null)
                {
                    getImageFile();
                }
            }
        });

        /**
         * Simple handlers for Test Control
         */
        testListTestControl.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) { checkListSelection(); }
        });
        removeTestButtonTestControl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {removeTest();}
        });

        itemListTestControl.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });
        assignedItemListTestControl.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });

        testTextFieldTestControl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        assignItemButtonTestControl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        removeItemButtonTestControl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // fileChooser options
        fileChooserItemControl.setDragEnabled(true);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
        fileChooserItemControl.setFileFilter(filter);
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

    private void getImageFile()
    {
        //get image from selected path
        System.out.println(fileChooserItemControl.getSelectedFile());
        Image itemImage = toolkit.getImage(fileChooserItemControl.getSelectedFile().toString());

        //resize image
        Icon itemIcon = new ImageIcon(itemImage.getScaledInstance(200, 200, Image.SCALE_DEFAULT));

        //display Image in Image Label
        imageLabelItemControl.setIcon(itemIcon);
    }

    /**
     * set the
     */
    public void setDefaultIcon()
    {
        imageLabelItemControl.setIcon(defaultIcon);
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
            itemListModel.addElement(itemTextFieldItemControl.getText());
            itemTextFieldItemControl.setText("");

            // set data in itemListt
            itemListItemControl.setModel(itemListModel);

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
        if (itemTextFieldItemControl.getText().length() < 18)
        {
            resetErrorLabel();
            return true;
        }
        else
        {
            itemTextFieldItemControl.requestFocus();
            errorLabelItemControl.setText("<html>Item name must be less<br> than 18 characters long</html>");
            return false;
        }
    }

    /**
     * pass the itemList back to the controller for use by the model, and
     * display a finished message
     */
    public void finishAdminSetup()
    {
        ArrayList<Item> passItems = new ArrayList<>();

        for(int i = 0; i < itemListModel.size(); i++)
        {
            passItems.add(new Item(itemListModel.getElementAt(i).toString()));
        }

        resetErrorLabel();
        itemTextFieldItemControl.setText("");
        AdminSetupController.setItems(passItems);
        AdminSetupController.closeFrame();
    }

    /**
     * Cancel, do not write list to database, close frame
     */
    public void cancelAdmin()
    {
        itemTextFieldItemControl.setText("");
        itemListModel.removeAllElements();
        resetErrorLabel();
        // set data in itemList
        setItemList(AdminSetupController.getItems());
        setInitialFocus();
        setDefaultIcon();
    }

    /**
     * set the initial item list
     * @param passList is list of item objects passed from the controller
     *                 for use here in the view controls
     */
    public void setItemList(List<Item> passList)
    {
        // clear item list
        itemListModel.clear();

        //add items to list Model
        for (Item item: passList)
        {
            itemListModel.addElement(item.toString());
        }
        itemListItemControl.setModel(itemListModel);
        itemListTestControl.setModel(itemListModel);
    }

    /**
     *
     */
    private void resetErrorLabel()
    {
        errorLabelItemControl.setText("");
    }

    /**
     * Set Focus to itemList
     */
    public void setInitialFocus()
    {
        itemTextFieldItemControl.requestFocus();
    }

    /**
     * This method is called when the user selects an item in the list,
     * and presses the remove item button
     */
    public void removeItem()
    {
        try
        {
            itemListModel.remove(itemListItemControl.getSelectedIndex());
            // set data in itemList
            itemListItemControl.setModel(itemListModel);
            itemListItemControl.setSelectedIndex(-1);

            // enable and disable components
            checkListLength();
            checkListSelection();
        }
        catch(ArrayIndexOutOfBoundsException ex) {
            System.out.println(ex.toString());
        }
    }

    /**
     * This method is called when the user selects an item in the list,
     * and presses the remove item button. Its purpose is to remove the
     * selected list field from the default list model.
     */
    public void removeTest()
    {
        try
        {
            testListModel.remove(itemListItemControl.getSelectedIndex());
            // set data in itemList
            itemListItemControl.setModel(testListModel);
            itemListItemControl.setSelectedIndex(-1);

            // enable and disable components
            checkListLength();
            checkListSelection();
        }
        catch(ArrayIndexOutOfBoundsException ex) {
            System.out.println(ex.toString());
        }
    }

    /**
     * This method checks the list length to enable or disable the finish button
     * based on the criteria of > 2 items
     */
    public void checkListLength()
    {
        if(itemListModel.getSize() > 2)
        {
            finishedButton.setEnabled(true);
        }
        else
        {
            finishedButton.setEnabled(false);
        }
    }

    /**
     * Enable or disable Remove Item Button
     * based on selected Index
     */
    private void checkListSelection()
    {
        if (itemListItemControl.getSelectedIndex() != -1)
        {
            removeItemButtonItemControl.setEnabled(true);
        }
        else
        {
            removeItemButtonItemControl.setEnabled(false);
        }
    }

}
