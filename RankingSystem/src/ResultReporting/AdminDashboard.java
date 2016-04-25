package ResultReporting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * AdminDashboard is used to test launching report panel.
 *
 * @Author: David Li
 * @Version: 2016.04.20
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
            ReportTable data = new ReportTable();
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.setContentPane(data.getReportPanel());
            frame.revalidate();
        } else if(e.getActionCommand().equals(SETUP)){
            System.out.println(SETUP);
        }

    }
}
