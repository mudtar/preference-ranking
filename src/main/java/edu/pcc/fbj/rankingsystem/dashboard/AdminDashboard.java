package edu.pcc.fbj.rankingsystem.dashboard;

import edu.pcc.fbj.rankingsystem.adminsetup.AdminSetupController;
import edu.pcc.fbj.rankingsystem.resultreporting.ReportTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import edu.pcc.fbj.rankingsystem.resultreporting.ReportTable;

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

<<<<<<< HEAD
    //setup consts for AdminSetup
=======
>>>>>>> master
    private final static int WIDTH = 910;
    private final static int HEIGHT = 640;

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

        if(e.getActionCommand().equals(REPORT))
        {
            JFrame frame = new JFrame();
            frame.setPreferredSize(new Dimension(800,600));
            ReportTable report = new ReportTable();
            frame.setContentPane(report.getReportPanel());
            frame.setTitle("Test Report");
            frame.setLocationByPlatform(true);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
        else if(e.getActionCommand().equals(SETUP))
        {
            try
            {
<<<<<<< HEAD
                JFrame setupFrame = new JFrame();
                AdminSetupController adminSetupController = new AdminSetupController(setupFrame);
                setupFrame.setContentPane(adminSetupController.getRootPanel());
                setupFrame.setTitle("Admin Setup");
                setupFrame.setLocationByPlatform(true);

                setupFrame.setPreferredSize(new Dimension(WIDTH,HEIGHT));
=======
                JFrame frame = new JFrame();
                AdminSetupController adminSetupController = new AdminSetupController(frame);
                frame.setContentPane(adminSetupController.getRootPanel());
                frame.setTitle("Admin Setup");

                frame.setPreferredSize(new Dimension(WIDTH,HEIGHT));
>>>>>>> master

                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation(dim.width/2 - WIDTH/2, dim.height/2 - HEIGHT/2);

<<<<<<< HEAD
                setupFrame.pack();
                setupFrame.setLocationRelativeTo(null);
                setupFrame.setVisible(true);
=======
                frame.setLocationByPlatform(true);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
>>>>>>> master

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
