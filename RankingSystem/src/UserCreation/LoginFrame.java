package UserCreation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This LoginFrame creates
 * the main windows of the program
 *
 * @author BeeYean Tan
 * @version 2016-04-11
 */
public class LoginFrame
        extends JFrame
        implements LoginView
{
    private LoginPanel loginPanel;
    private LoginController controller;

    public LoginFrame()
    {
        super("Ranking System - Login Page");
        this.loginPanel = createLoginPanel();
        getContentPane().add(this.loginPanel);

        setDefaultCloseOperation(3);
        setResizable(false);
        pack();
        setVisible(false);
        setLocationRelativeTo(null);
    }

    private LoginPanel createLoginPanel()
    {
        this.loginPanel = new LoginPanel();
        this.loginPanel.addLoginButtonActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                LoginFrame.this.loginAttempt();
            }
        });
        this.loginPanel.addUserEmailTextFieldKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                int key = e.getKeyCode();
                if (key == 10) {
                    LoginFrame.this.loginPanel.loginButtonClick();
                }
            }
        });
        this.loginPanel.addRegisterButtonActionListener(new ActionListener()
       {
            public void actionPerformed(ActionEvent ae)
           {
               LoginFrame.this.loginPanel.setUserEmailTextField("");
               LoginFrame.this.loginPanel.setPasswordTextField("");
               LoginFrame.this.setVisible(false);
               LoginFrame.this.controller.launchRegistrationFrame();
            }
        });
        return this.loginPanel;
    }

    public boolean updateLoginFrameState(boolean loginFrameState)
    {
        setVisible(!loginFrameState);
        return loginFrameState;
    }

    public void registerController(LoginController controller)
    {
        this.controller = controller;
    }

    public void loginAttempt()
    {
        // This is welcome message page
        JOptionPane.showMessageDialog(null, " Welcome to Ranking System User Testing Page."
                + "\r\n\r\n This test compares items in groups of 2." +
                "\r\n Each item on the test will be compared to every other item on the test." +
                "\r\n You must complete the test in order to save the test results.",
                "Ranking System - Welcome Message", 1);

    }

}
