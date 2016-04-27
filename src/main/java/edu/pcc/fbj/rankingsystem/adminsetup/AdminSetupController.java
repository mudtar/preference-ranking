package edu.pcc.fbj.rankingsystem.adminsetup;


import javax.swing.*;
import java.awt.*;

/**
 * This Class will hold the code for facilitating action between the view
 * and the Model ideally
 *
 * @author Eric Kristiansen
 * @version 4/19/16
 */
public class AdminSetupController {

    //add controller stuff
    private static AdminSetupView view = new AdminSetupView();
    private static AdminSetupModel model = new AdminSetupModel();

    /**
     * This method is called from the main method to do the work
     * of instantiating the GUI
     */
    public static void createAndShowGUI()
    {

        try
        {
            // create and set up window
            JFrame frame = new JFrame("Admin SetUp");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(view.getRootPanel());
            frame.setPreferredSize(new Dimension(480,320));

            // Display the window
            frame.pack();
            frame.setVisible(true);
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }




    }


    /**
     * This method is called to get the root pael for the AdminSetup Functionality
     * @return JPanel
     */
    public javax.swing.JPanel getRootPanel()
    {
        return view.getRootPanel();
    }

    /**
     * This Method is called to return the items from the database
     */
    public java.util.List<Item> getItems()
    {
        return model.getItems();
    }

    /**
     * This Method is called to set the items from the database
     */
    public static void setItems(java.util.List<Item> passItems)
    {
        model.setItems(passItems);
    }

}
