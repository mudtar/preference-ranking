package ResultReporting;

import javax.swing.JFrame;
import ResultReporting.ReportPanel;

import java.awt.*;

/**
 * Main class used for tesing report module.
 * @Author:  David Li
 * @Version: 2016.04.06
 */

public class ReportMain {

    /**
     * Create Main frame and display GUI.
     */
    private static void createAndShowGUI() {

        JFrame frame = new JFrame("User Test Result Report");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AdminDashboard dashboard = new AdminDashboard();
        frame.setPreferredSize(new Dimension(500,300));
        frame.setContentPane(dashboard);

        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(()->createAndShowGUI());
    }
}