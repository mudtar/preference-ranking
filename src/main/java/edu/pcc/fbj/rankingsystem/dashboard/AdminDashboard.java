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
public class AdminDashboard extends JPanel implements ActionListener {

    private static final String REPORT = "report";
    private static final String SETUP = "setup";
    private static final String LOGIN = "login";

    public AdminDashboard(){
        setOpaque(true);
        setLayout(new GridBagLayout());

        //report
        JButton reportBtn = new JButton("Open Report List");
        reportBtn.setActionCommand("report");
        reportBtn.addActionListener(this);
        add(reportBtn);

        //setup
        JButton setupBtn = new JButton("Setup Test Items");
        setupBtn.setActionCommand("setup");
        setupBtn.addActionListener(this);
        add(setupBtn);

        //login
        JButton loginBtn = new JButton("Back to Login");
        loginBtn.setActionCommand("login");
        loginBtn.addActionListener(this);
        add(loginBtn);

    }

    /**
     * Action listener userd for respond to event of Report button.
     * @param e  - action event
     */
    public void actionPerformed(ActionEvent e){

        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(480,320));

        if(e.getActionCommand().equals(REPORT)) {
            ReportTable report = new ReportTable();
            frame.setContentPane(report.getReportPanel());
            frame.setTitle("Test Report");

        } else if(e.getActionCommand().equals(SETUP)){
            try
            {
                AdminSetupController adminSetupController = new AdminSetupController(frame);
                frame.setContentPane(adminSetupController.getRootPanel());
                frame.setTitle("Admin Setup");
            }
            catch (Exception ex)
            {
                System.out.println(ex.toString());
            }

        }
        else if(e.getActionCommand().equals(LOGIN))
        {
            //launch login
            System.out.println("TODO: launch login, and close AdminDashboard - line 82 AdminDashboard");
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
