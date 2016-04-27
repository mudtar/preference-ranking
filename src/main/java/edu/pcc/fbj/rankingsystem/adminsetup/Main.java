package edu.pcc.fbj.rankingsystem.adminsetup;

/**
 *
 * This is the Main Class which is just an entry point
 *
 * @author Eric Kristiansen
 * @version 4/19/16
 */
public class Main {

    private static AdminSetupController controller = new AdminSetupController();

    /**
     * Main method
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
