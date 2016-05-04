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
 * @author David Li
 * @version 2016.04.20
 * 1. Initial version
 *
 * @author David Li
 * @version 2016.04.25
 * 1. Launch report panel in another window.
 */
public class AdminDashboard extends JPanel implements ActionListener
{

    private static final String REPORT = "report";
    private static final String SETUP = "setup";
    private static final String LOGOUT = "logout";

    public AdminDashboard()
    {
        setOpaque(true);
        setLayout(new GridBagLayout());

        //report
        JButton reportBtn = new JButton("Open Report List");
        reportBtn.setActionCommand(REPORT);
        reportBtn.addActionListener(this);
        add(reportBtn);

        //setup
        JButton setupBtn = new JButton("Setup Test Items");
        setupBtn.setActionCommand(SETUP);
        setupBtn.addActionListener(this);
        add(setupBtn);

        //logout
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setActionCommand(LOGOUT);
        logoutBtn.addActionListener(this);
        add(logoutBtn);

    }

    /**
     * Action listener userd for respond to event of Report button.
     * @param e  - action event
     */
    public void actionPerformed(ActionEvent e)
    {

        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(480,320));

        if(e.getActionCommand().equals(REPORT))
        {
            ReportTable report = new ReportTable();
            frame.setContentPane(report.getReportPanel());
            frame.setTitle("Test Report");
        }
        else if(e.getActionCommand().equals(SETUP))
        {
            try
            {
                AdminSetupController adminSetupController = new AdminSetupController(frame);
                frame.setContentPane(adminSetupController.getRootPanel());
                frame.setTitle("Admin Setup");
                frame.setLocationByPlatform(true);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                //set focus after frame is visible
                adminSetupController.setInitialFocus();
                return;
            }
            catch (Exception ex)
            {
                System.out.println(ex.toString());
            }

        }
        else if(e.getActionCommand().equals(LOGOUT))
        {
            //launch login
            //System.out.println("launch login, and close AdminDashboard - line 82 AdminDashboard");
            //RankingSystemController controller = RankingSystemController.INSTANCE;
            //controller.launchLogin();
            //frame.dispose();
            //return;
            System.exit(0);
        }

        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void showAdminDashboardDisplay()
    {
        JFrame frame = new JFrame("User Test Result Report");
        frame.setPreferredSize(new Dimension(500,300));
        frame.setContentPane(this);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }
}
