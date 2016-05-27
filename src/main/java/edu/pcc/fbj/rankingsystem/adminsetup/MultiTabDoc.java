package edu.pcc.fbj.rankingsystem.adminsetup;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eric Kristiansen
 * @version 5/23/16
 */
public class MultiTabDoc {
    private JTabbedPane tabbedPane1;
    private JPanel rootPanel;
    private JList itemListItemControl;
    private JTextField itemTextFieldItemControl;
    private JButton removeItemButtonItemControl;
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
    private JButton cancelAdminButton;
    private JButton removeImageButtonItemControl;

    private DefaultListModel itemListModel = new DefaultListModel();
    private DefaultListModel testListModel = new DefaultListModel();
    private DefaultListModel assignedListModel = new DefaultListModel();
    private List<Item> items;
    private List<TestNameItem> currentTestNameItems = new ArrayList<>();
    private List<TestName> testNames;
    private Boolean isGood;

    //Image tools
    Image defaultImage;
    ImageIcon defaultImageIcon;

    /**
     * Constructor for View Class
     * Set up Handlers and initial components
     */
    public MultiTabDoc() {

        /**
         * Simple Handlers Section for Item Control
         */
        itemListItemControl.addListSelectionListener(e ->
            {
                checkListSelection(itemListItemControl, removeItemButtonItemControl);
                getSelectedItemImage(itemListItemControl, imageLabelItemControl);
            });

        removeItemButtonItemControl.addActionListener(e -> removeItem());

        itemTextFieldItemControl.addActionListener(e -> appendItemTextFieldText());

        removeImageButtonItemControl.addActionListener(e -> removeImage() );

        fileChooserItemControl.addPropertyChangeListener(propertyChangeEvent ->
            {
                if (fileChooserItemControl.SELECTED_FILE_CHANGED_PROPERTY
                        .equals(propertyChangeEvent.getPropertyName()) &&
                        fileChooserItemControl.getSelectedFile() != null)
                { getImageFromFileChooser(imageLabelItemControl); }
            });

        /**
         * Simple handlers for Test Control
         */
        testListTestControl.addListSelectionListener(e ->
            {
                checkListSelection(testListTestControl, removeTestButtonTestControl);
                if (testListTestControl.getSelectedIndex() != -1) itemListTestControl.setEnabled(true);
                loadItemsForSelectedTest();
            });

        removeTestButtonTestControl.addActionListener(e -> removeTest());

        itemListTestControl.addListSelectionListener(e ->
            {
                checkListSelection(itemListTestControl, assignItemButtonTestControl);
                getSelectedItemImage(itemListTestControl, imageLabelTestControl);
            });

        assignedItemListTestControl.addListSelectionListener(e ->
               checkListSelection(assignedItemListTestControl, removeItemButtonTestControl));

        testTextFieldTestControl.addActionListener(e ->  appendTestTextFieldText());

        assignItemButtonTestControl.addActionListener(e -> assignItemToTest());

        removeItemButtonTestControl.addActionListener(e -> removeItemFromTestList());

        /**
         * Simple Handlers for Frame
         */
        finishedButton.addActionListener(e -> finishAdminSetup());

        cancelAdminButton.addActionListener(e -> cancelAdmin() );

        tabbedPane1.addChangeListener(c-> setFocusInTab());

        try
        {
            // set file Chooser options
            setupFileChooser();

            defaultImage = ImageIO.read(new File(URLDecoder.decode(getClass().getResource("default.png").getPath(),
                    "UTF-8").replaceAll("%20", "")));
            defaultImageIcon = new ImageIcon(defaultImage);

            setInitialFocus();

            currentTestNameItems = AdminSetupController.getTestNameItems();

            //List<Integer> uniqueItemsUsed = AdminSetupController.getUniqueItemsUsed();
            //uniqueItemsUsed.forEach(i -> System.out.println(i));
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
    }

    /**
     *
     */
    private void setFocusInTab()
    {
        if (tabbedPane1.getSelectedIndex() == 0)
        {
            itemTextFieldItemControl.requestFocus();
        }
        else
        {
            testTextFieldTestControl.requestFocus();
        }
    }

    /**
     * Set image of selected ImageObject to null
     */
    private void removeImage()
    {
        items.forEach(i ->
        {
            if (i.toString().equals(itemListItemControl.getSelectedValue().toString()))
            {
                i.setImage(null);
                getSelectedItemImage(itemListItemControl, imageLabelItemControl);
            }
        });
    }

    /**
     * This function sets a border on the root panel, and returns it to be used by the controller.
     * @return JPanel returned to the controller for instantiating
     */
    public JPanel getRootPanel()
    {
        rootPanel.setBorder(new javax.swing.border.EmptyBorder(20, 50, 20, 50));
        return rootPanel;
    }

    /**
     * Basic setup stuff for fileChooser
     */
    private void setupFileChooser()
    {
        fileChooserItemControl.setDragEnabled(true);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
        fileChooserItemControl.setFileFilter(filter);
    }

    /**
     * add selected item to the testItemList
     */
    private void assignItemToTest()
    {
        // check if element is already in assigned list
        //if not assign it
        if (assignedListModel.indexOf(itemListTestControl.getSelectedValue()) == -1)
        {
            assignedListModel.addElement(itemListTestControl.getSelectedValue());
            currentTestNameItems.add(new TestNameItem(testListTestControl.getSelectedValue().toString(),
                    itemListTestControl.getSelectedValue().toString()));
            assignedItemListTestControl.setModel(assignedListModel);
        }
    }

    /**
     *remove Item from test list
     */
    private void removeItemFromTestList()
    {
        TestNameItem temp;

        System.out.println("before removal*********************");
        for (TestNameItem tn: currentTestNameItems)
        {
            System.out.println("testName: " + tn.getTestNameID() + " : Itemid: " + tn.getItemID());
        }

        /*
        for (Item i: testNames )
        {
            System.out.println("looking at testname: " + t.getTestNameID() + " : " + t.getName());
            System.out.println("looking for: " + assignedItemListTestControl.getSelectedValue());
            if (t.getName().equals(assignedItemListTestControl.getSelectedValue()))
            {
                System.out.println("have a match:... " + t.getName() + " : " + t.getTestNameID());
            }
        }
        */
        //remove testNameitem from current list of test name items
        for (TestNameItem tni: currentTestNameItems)
        {
            if(tni.getTestName().equals(testListTestControl.getSelectedValue())) //&& tni.getItemName().equals(assignedItemListTestControl.getSelectedValue()))
            {
                if (tni.getItemName().equals(assignedItemListTestControl.getSelectedValue()))
                {
                    currentTestNameItems.remove(currentTestNameItems.indexOf(tni));
                    break;
                }
            }

        }

        System.out.println("after removal*********************");
        for (TestNameItem tn: currentTestNameItems)
        {
            System.out.println("testNameID: " + tn.getTestNameID() + " : Itemid: " + tn.getItemID());
        }


        assignedListModel.removeElementAt(assignedItemListTestControl.getSelectedIndex());

        assignedItemListTestControl.setModel(assignedListModel);
    }

    /**
     * load the items assigned for a given test
     */
    private void loadItemsForSelectedTest()
    {
        int testID = 0;
        assignedListModel.clear();

        if (testListTestControl.getSelectedIndex() != -1)
        {
            List<TestNameItem> testNameItems = AdminSetupController.getTestNameItems();
            List<TestName> tests = AdminSetupController.getTestNames();
            List<Integer> itemIds = new ArrayList<>();

            for(TestName t: tests)
            {
                if (t.getName().equals(testListTestControl.getSelectedValue()))
                {
                    System.out.println("setting testID " + t.getTestNameID());
                    testID = t.getTestNameID();
                }
            }

            for (TestNameItem tni: testNameItems)
            {
                System.out.println("itemID " + tni.getItemID());
                if (tni.getTestNameID() == testID)
                {
                    System.out.println("adding " + tni.getItemID() + " ");
                    itemIds.add(tni.getItemID());
                }
            }
            for (Integer i: itemIds)
            {
                for (Item j : items)
                {
                    if (i == j.getItemID())
                    {
                        System.out.println("adding " + j.getName());
                        assignedListModel.addElement(j.getName());
                    }
                }
            }


            assignedItemListTestControl.setModel(assignedListModel);
            //load list model for selected test.....
            //////////////////////////////////////////////////////////////////////////
        }
    }

    /**
     * Get the image from the fileChooser, and display at appropriate size
     */
    private void getImageFromFileChooser(JLabel passLabel)
    {
        try
        {
            System.out.println("filepath: " + fileChooserItemControl.getSelectedFile());
            Image FileImage = ImageIO.read(new File(fileChooserItemControl.getSelectedFile().toString())).getScaledInstance(200, 200, Image.SCALE_DEFAULT);

            setLabelImage(passLabel, FileImage);

            if (itemListItemControl.getSelectedIndex() != -1)
            {
                items.forEach(x ->
                {
                    if (x.toString().equals(itemListItemControl.getSelectedValue()))
                    {
                        x.setImage(FileImage);
                    }
                });
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
    }


    /**
     *
     */
    private void setLabelImage(JLabel passLabel, Image passImage)
    {
        ImageIcon FileIcon = new ImageIcon(passImage);
        passLabel.setIcon(FileIcon);
    }

    /**
     *
     */
    private void getSelectedItemImage(JList passList, JLabel passLabel)
    {
        try
        {
            //find selected item in items, and display icon
            items.forEach((i) ->
            {
                if (i.toString().equals(passList.getSelectedValue()))
                {
                    //set default image
                    if (i.getImage() == null)
                    {
                        System.out.println("isNull");
                        passLabel.setIcon(defaultImageIcon);
                        removeImageButtonItemControl.setEnabled(false);
                    }
                    else
                    {
                        System.out.println("is not null");
                        passLabel.setIcon(new ImageIcon(i.getImage()));
                        removeImageButtonItemControl.setEnabled(true);
                    }
                }
            });
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
    }

    /**
     * set the image label to default icon
     * @param passLabel
     */
    private void setDefaultIcon(JLabel passLabel)
    {
        passLabel.setIcon(defaultImageIcon);
    }

    /**
     * This method takes the value of the textfield, and
     * appends the list of items.
     */
    private void appendItemTextFieldText()
    {
        if (validateFieldLength(itemTextFieldItemControl, errorLabelItemControl))
        {
            //add item to items
            items.add(new Item(itemTextFieldItemControl.getText()));

            // add items to a default list
            itemListModel.addElement(itemTextFieldItemControl.getText());
            itemTextFieldItemControl.setText("");

            // set data in itemListt
            itemListItemControl.setModel(itemListModel);

            checkListLength();
        }
    }

    /**
     * This method takes the value of the textfield, and
     * appends the list of items.
     */
    private void appendTestTextFieldText()
    {
        if (validateFieldLength(testTextFieldTestControl, errorLabelTestControl))
        {
            // add items to a default list
            testListModel.addElement(testTextFieldTestControl.getText());
            testNames.add(new TestName(testTextFieldTestControl.getText()));
            testTextFieldTestControl.setText("");

            // set data in testList
            testListTestControl.setModel(testListModel);

            //checkListLength();  validate this entry?
        }
    }

    /**
     * @return true if length is less than 18
     * the database field is varchar(20)
     * Sets error label if appropriate
     *
     * @param passTextField and passLabel
     */
    private Boolean validateFieldLength(JTextField passTextField, JLabel passLabel)
    {
        if (passTextField.getText().length() < 18)
        {
            resetErrorLabel(passLabel);
            return true;
        }
        else
        {
            passTextField.requestFocus();
            passLabel.setText("<html>Item name must be less<br> than 18 characters long</html>");
            return false;
        }
    }

    /**
     * pass the itemList back to the controller for use by the model, and
     * display a finished message
     */
    private void finishAdminSetup()
    {
        //ArrayList<Item> passItems = new ArrayList<>();
        //ArrayList<TestName> passTestNames = new ArrayList<>();

      /*  for(int i = 0; i < testListModel.size(); i++)
        {
            passTestNames.add(new TestName(testListModel.getElementAt(i).toString()));
        }
        */
        //String testName = testListTestControl.getSelect

        resetErrorLabel(errorLabelItemControl);
        resetErrorLabel(errorLabelTestControl);
        itemTextFieldItemControl.setText("");
        testTextFieldTestControl.setText("");
        AdminSetupController.setItems(items);
        AdminSetupController.setTestNames(testNames);
        AdminSetupController.setTestItems(currentTestNameItems);
        AdminSetupController.closeFrame();
    }

    /**
     * Cancel, do not write list to database, close frame
     */
    private void cancelAdmin()
    {
        itemTextFieldItemControl.setText("");
        testTextFieldTestControl.setText("");
        itemListModel.removeAllElements();
        testListModel.removeAllElements();
        resetErrorLabel(errorLabelItemControl);
        resetErrorLabel(errorLabelTestControl);
        // set data in Lists
        setItemList(AdminSetupController.getItems());
        setTestNameList(AdminSetupController.getTestNames());
        setInitialFocus();
        setDefaultIcon(errorLabelItemControl);
        setDefaultIcon(errorLabelTestControl);
    }

    /**
     * set the initial item list
     * @param passList is list of item objects passed from the controller
     *                 for use here in the view controls
     */
    public void setItemList(List<Item> passList)
    {
        items = passList;

        // set List Model
        itemListModel.clear();
        for (Item item: items)
        {
            itemListModel.addElement(item.toString());
        }
        itemListItemControl.setModel(itemListModel);
        itemListTestControl.setModel(itemListModel);
    }

    /**
     * set the initial item list
     * @param passList is list of item objects passed from the controller
     *                 for use here in the view controls
     */
    public void setTestNameList(List<TestName> passList)
    {
        testNames = passList;

        // set List Model
        testListModel.clear();
        for (TestName testName: testNames)
        {
            testListModel.addElement(testName.toString());
        }
        testListTestControl.setModel(testListModel);
    }


    /**
     * reset error label blank
     * @param passLabel
     */
    private void resetErrorLabel(JLabel passLabel) { passLabel.setText(""); }

    /**
     * reset error labels to blank
     */
    private void resetErrorLabels()
    {
        errorLabelItemControl.setText("");
        errorLabelTestControl.setText("");
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
            //remove item from list model and items
            items.forEach(i -> {if(i.toString().equals(itemListItemControl.getSelectedValue())) items.remove(i);});
            itemListModel.remove(itemListItemControl.getSelectedIndex());

            // set data in itemList
            itemListItemControl.setModel(itemListModel);
            itemListItemControl.setSelectedIndex(-1);

            // enable and disable components
            checkListLength();
            checkListSelection(itemListItemControl, removeItemButtonItemControl);
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
            testNames.forEach(i -> {if(i.toString().equals(testListTestControl.getSelectedValue())) testNames.remove(i);});
            testListModel.remove(testListTestControl.getSelectedIndex());

            // set data in testList
            testListTestControl.setModel(testListModel);
            testListTestControl.setSelectedIndex(-1);

            // enable and disable components
            checkListSelection(testListTestControl, removeTestButtonTestControl);
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
        isGood = (itemListModel.getSize() > 2) ? true : false;
        finishedButton.setEnabled(isGood);
    }

    /**
     * Enable or disable Remove Button
     * based on selected Index
     */
    private void checkListSelection(JList passList, JButton passButton)
    {
        isGood = (passList.getSelectedIndex() != -1) ? true : false;
        passButton.setEnabled(isGood);
    }

}
