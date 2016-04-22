package AdminSetupController;

import AdminSetupView.AdminSetupView;
import AdminSetupModel.AdminSetupModel;
import javax.swing.*;

/**
 * This Class will hold the code for facilitating action between the view
 * and the Model ideally
 *
 * @author Eric Kristiansen
 * @version 4/19/16
 */
public class AdminSetupController {

    //add controller stuff
    AdminSetupView view;
    AdminSetupModel model;

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

}
