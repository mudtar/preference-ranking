/**
 * Class to serve as main entry for testing GUI and module functionality for sprint 2 GUI
 * This is only a temporary parallel solution before I fully implement sprint 2 GUI
 *
 * @author Eric Kristiansen
 * @version 5/17/16
 */
package edu.pcc.fbj.rankingsystem.adminsetup;

import javax.swing.*;
import java.awt.*;

public class Main {

   private static JFrame frame;
    private static MultiTabDoc  tabDoc = new MultiTabDoc();
    private static AdminSetupModel adminSetupModel = new AdminSetupModel();
    private static AdminSetupController adminSetupController;

    private final static int WIDTH = 910;
    private final static int HEIGHT = 640;

    public static void main(String[] args) {
        try
        {
            javax.swing.SwingUtilities.invokeLater( () -> createAndShowGUI());
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
    }

    private static void createAndShowGUI()
    {
        try
        {
            // create and set up window
            frame = new JFrame("Admin Setup");
            adminSetupController = new AdminSetupController(frame);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(tabDoc.getRootPanel());
            frame.setPreferredSize(new Dimension(WIDTH,HEIGHT));

            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setLocation(dim.width/2 - WIDTH/2, dim.height/2 - HEIGHT/2);

            //tabDoc.setItemList(adminSetupModel.getItems());
            //tabDoc.setTestNameList(adminSetupModel.getTestNames());

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

}
