
import AdminSetupView.AdminSetupView;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 *
 * This is the Main Class which is used to instantiate the GUI
 *
 * @author Eric Kristiansen
 * @version 4/19/16
 */
public class Main {

    //private AdminSetupModel adminSetupModel;
    private static AdminSetupView adminSetupView;
    private static JButton addButton;
    private static ActionListener actionListener;
    private DefaultListModel listModel;
    private static JList list;
    private JList<String> countryList;

    /**
     * This function is called from the main method to do the work
     * of instantiating the GUI
     */
    public static void createAndShowGUI()
    {
        // create and set up window
        JFrame frame = new JFrame("Admin SetUp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(new AdminSetupView().getRootPanel());

        // Display the window
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Main method
     */
    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater( () -> createAndShowGUI());

    }




}
