package ResultReporting;

import javax.swing.JFrame;
import ResultReporting.ReportPanel;

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

        JFrame frame = new JFrame("User Test Result Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ReportTable data = new ReportTable();
        frame.setContentPane(data.getReportPanel());

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(()->createAndShowGUI());
    }
}
