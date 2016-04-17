import javax.swing.*;


/**
 * Created by HenryWinkler on 4/17/2016.
 */
public class AdminSetupView {

    private JPanel rootPanel;
    private JList itemList;
    private JTextField itemTextField;
    private JButton addButton;
    private JButton finishedButton;
    private JLabel directionsLabel;
    private JButton removeButton;
    private JButton cancelButton;

    public JPanel getRootPanel()
    {
        rootPanel.setBorder(new javax.swing.border.EmptyBorder(20, 50, 20, 50));
        return rootPanel;
    }

}