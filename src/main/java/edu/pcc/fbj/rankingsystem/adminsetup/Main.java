package edu.pcc.fbj.rankingsystem.adminsetup;

import javax.swing.*;

/**
 *
 * This is the Main Class which is just an entry point
 * Enables package to run independently for testing
 *
 * @author Eric Kristiansen
 * @version 4/30/16
 */
public class Main {

    private static AdminSetupController controller = new AdminSetupController(new JFrame());

    /**
     * Main method
     * @param args environment variables
     */
    public static void main(String[] args)
    {
        try
        {
            javax.swing.SwingUtilities.invokeLater( () -> controller.createAndShowGUI());
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }

    }





}
