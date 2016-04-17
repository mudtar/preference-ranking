
import javax.swing.*;

/**
 * Created by HenryWinkler on 4/11/2016.
 */
public class Main {

    public static void createAndShowGUI()
    {
        JFrame frame = new JFrame("Admin SetUp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new AdminSetupView().getRootPanel());
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater( () -> createAndShowGUI());

    }
}