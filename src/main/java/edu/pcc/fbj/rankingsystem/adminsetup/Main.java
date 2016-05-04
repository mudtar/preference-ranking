/**
 * Created by Electech on 5/3/2016.
 */
package edu.pcc.fbj.rankingsystem.adminsetup;

import javax.swing.*;
import java.awt.*;

public class Main {


   private static JFrame frame;
    private static MultiTabDoc  tabDoc = new MultiTabDoc();


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
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(tabDoc.getRootPanel());
            frame.setPreferredSize(new Dimension(900,800));

            //view.setItemList(model.getItems());

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
