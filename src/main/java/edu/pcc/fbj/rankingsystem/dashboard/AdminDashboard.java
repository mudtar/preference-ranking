package edu.pcc.fbj.rankingsystem.dashboard;

import edu.pcc.fbj.rankingsystem.adminsetup.AdminSetupController;
import edu.pcc.fbj.rankingsystem.resultreporting.ReportTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * AdminDashboard is used to test launching report panel.
 *
 * @Author: David Li
 * @Version: 2016.04.20
 * 1. Initial version
 *
 * @Author: David Li
 * @Version: 2016.04.25
 * 1. Launch report panel in another window.
 */
public class AdminDashboard extends JPanel implements ActionListener {

    private JButton reportBtn;
    private JButton setupBtn;
    private static final String REPORT = "report";
    private static final String SETUP = "setup";
    //private ReportTable data;


    public AdminDashboard(){
        setOpaque(true);
        setLayout(new GridBagLayout());
        reportBtn = new JButton("Open Report List");
        reportBtn.setActionCommand("report");
        reportBtn.addActionListener(this);
        add(reportBtn);

        setupBtn = new JButton("Setup Test Items");
        setupBtn.setActionCommand("setup");
        setupBtn.addActionListener(this);

        add(setupBtn);

    }

    /**
     * Action listener userd for respond to event of Report button.
     * @param e  - action event
     */
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals(REPORT)) {
            ReportTable data;
            data = new ReportTable();
            /*JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.setContentPane(data.getReportPanel());
            frame.revalidate();
            */
            JFrame frame = new JFrame("User Test Result Report");
            frame.setPreferredSize(new Dimension(500,300));
            frame.setContentPane(data.getReportPanel());
            frame.setLocationByPlatform(true);
            frame.pack();
            frame.setVisible(true);
        } else if(e.getActionCommand().equals(SETUP)){
            try
            {
                JFrame frame = new JFrame("Admin Setup");
                AdminSetupController adminSetupController = new AdminSetupController(frame);

                frame.setPreferredSize(new Dimension(480,320));

                frame.setContentPane(adminSetupController.getRootPanel());
                frame.setLocationByPlatform(true);
                frame.pack();
                frame.setVisible(true);
            }
            catch (Exception ex)
            {
                System.out.println(ex.toString());
            }

        }

    }
}
