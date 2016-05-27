package edu.pcc.fbj.rankingsystem.adminsetup;

import javax.swing.*;

/**
 * This Class will hold the code for facilitating action between the view
 * and the Model ideally
 *
 * @author Eric Kristiansen
 * @version 5/26/16
 */
public class AdminSetupController
{
    private static AdminSetupView view = new AdminSetupView();
    private static AdminSetupModel model = new AdminSetupModel();

    private static JFrame frame;

    /**
     * controller constructor
     * @param passFrame passed reference from initializing class
     *                  to facilitate closing Panel as required by flow
     */
    public  AdminSetupController(JFrame passFrame)
    {
        frame = passFrame;
    }

    /**
     * This method is called to get the root panel for the AdminSetup Functionality
     * @return JPanel
     */
    public javax.swing.JPanel getRootPanel()
    {
        view.setItemList(model.getItems());
        return view.getRootPanel();
    }

    /**
     * This Method is called to return the uniqueItemsUsed from the resultDAO
     */
    public static java.util.List<Integer> getUniqueItemsUsed()
    {
        return model.getUniqueItemsUsed();
    }

    /**
     * This Method is called to return the TestNames from the database
     */
    public static java.util.List<TestName> getTestNames()
    {
        return model.getTestNames();
    }

    /**
     * This Method is called to return the items from the database
     */
    public static java.util.List<TestNameItem> getTestNameItems()
    {
        return model.getTestNameItems();
    }

    /**
     * This Method is called to return the items from the database
     */
    public static java.util.List<Item> getItems()
    {
        return model.getItems();
    }

    /**
     * This method calls the method in view to set initial focus
     */
    public void setInitialFocus()
    {
        view.setInitialFocus();
    }

    /**
     * This Method is called to set the TestNames in the database
     * @param passTestNames
     */
    public static void setTestNames(java.util.List<TestName> passTestNames)
    {
        model.setTestNames(passTestNames);
    }

    /**
     * This Method is called to set the TestNames in the database
     * @param passTestItems
     */
    public static void setTestItems(java.util.List<TestNameItem> passTestItems)
    {
        model.setTestItems(passTestItems);
    }

    /**
     * This Method is called to set the items from the database
     * @param passItems
     */
    public static void setItems(java.util.List<Item> passItems) { model.setItems(passItems); }

    /**
     * This Method is called to close frame
     */
    public static void closeFrame()
    {
        frame.dispose();
    }


}
