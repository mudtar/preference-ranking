package edu.pcc.fbj.rankingsystem.usercreation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This LoginFrame creates the main GUI of the login for Ranking System program.
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
    private UserCreationDB sqlUser;

    /**
     * Constructor of the class
     */
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

    /**
     * Update the current view to be shown or hidden depends on the state of the frame.
     * @param loginFrameState
     * @return true means the user is done with the view and the view will be hidden.
     * false means the user is still in the view and the view will be visible.
     */
    public boolean updateLoginFrameState(boolean loginFrameState)
    {
        setVisible(!loginFrameState);
        return loginFrameState;
    }

    /**
     * Register the given controller with this view
     * @param controller
     */
    public void registerController(LoginController controller)
    {
        this.controller = controller;
    }

    public void loginAttempt()
    {
        //String EMAIL_REGEX1 = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        //  "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

        //Pattern pattern = Pattern.compile(EMAIL_REGEX);
        //Matcher matcher = pattern.matcher(this.loginPanel.getUserEmailTextField());
        //System.out.println(this.loginPanel.getUserEmailTextField() +" : "+ matcher.matches());

        //String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";


      //  if (!this.loginPanel.getUserEmailTextField().trim().equalsIgnoreCase("")
       //    && (this.loginPanel.getUserEmailTextField().trim().matches(EMAIL_REGEX)))
       // {
            System.out.println("Starting LoginFrame:: loginAttempt()function!");
            if ((this.controller.loginAttempt(this.loginPanel.getUserEmailTextField().trim()))
                    && (this.loginPanel.getUserEmailTextField().trim().matches(EMAIL_REGEX)))
                   //.&& (TextUtils.isEmpty(this.loginPanel.getUserEmailTextField().trim()))
                    //(this.controller.loginAttempt(this.loginPanel.getPasswordTextField().trim().length()!= 0))
            {
                System.out.println("LoginFrame::loginAttempt()");
                this.controller.login();

                if (this.controller.checkUserAccessRoleForAdmin())
                {
                    JOptionPane.showMessageDialog(null, " Welcome to Admin Setup Page."
                                    + "\r\n You must setup the test criteria.",
                            "Ranking System - Welcome Message", 1);
                    this.controller.launchAdminTestSetup();
                }
                if (this.controller.checkUserAccessRoleForUser())
                {
                    //JOptionPane.showMessageDialog(null, "Welcome, " + this.controller.getUsername().trim() +
                    //         "\r\n\r\n This test compares items in groups of 2." +
                    //         "\r\n Each item on the test will be compared to every other item on the test." +
                    //         "\r\n You must complete the test in order to save the test results.", "User Test - Welcome Message", 1);
                    JOptionPane.showMessageDialog(null, " Welcome to User Test Page."
                                    + "\r\n You must must complete the test.",
                            "Ranking System - Welcome Message", 1);
                    this.controller.launchUserTestFrame();

                }
            }
       // }
        //if (!(this.loginPanel.getUserEmailTextField().trim().matches(EMAIL_REGEX)))
        //{
        //    invalidEmailFormatMessage();
        //}
        else if (this.loginPanel.getUserEmailTextField().trim().equalsIgnoreCase(""))
        {
            emptyEmailMessage();
            //this.loginPanel.setUserEmailTextField("");
            //this.loginPanel.setPasswordTextField("");
        }
        else if (!this.loginPanel.getUserEmailTextField().trim().matches(EMAIL_REGEX))
        {

            invalidEmailFormatMessage();
            this.loginPanel.setUserEmailTextField("");
           // this.loginPanel.setPasswordTextField("");
        }
        //else if (this.loginPanel.getPasswordTextField().trim().equalsIgnoreCase(""))
       // {
       //     emptyPasswordMessage();
       // }
        else //if (!this.sqlUser.validateUser(this.loginPanel.getUserEmailTextField().trim()))
        {
            invalidEmailMessage();
            System.out.println("LoginFrame:: LoginAttempt( Failed)");
            this.loginPanel.setUserEmailTextField("");
            //this.loginPanel.setPasswordTextField("");
        }

         //return;  // System.out.println("LoginFrame:: LoginAttempt() successfully");
    }

    public void invalidEmailFormatMessage()
    {
           JOptionPane.showMessageDialog(null, "'" + this.loginPanel.getUserEmailTextField().trim()
                + "' is not a valid email format (must be in this format i.e user@domain.com.",
                "Login Failed - Invalid Email Format", 2);
           //this.loginPanel.setPasswordTextField("");
    }

    public void invalidEmailMessage()
    {
        JOptionPane.showMessageDialog(null, "'" + this.loginPanel.getUserEmailTextField().trim()
                + "' is not a registered email.", "Login Failed - Invalid Email", 2);
        //this.loginPanel.setPasswordTextField("");
    }

    public void emptyEmailMessage()
    {
        JOptionPane.showMessageDialog(null, "The Email must contain at least" +
                " 1 character and must match an email in the database.", "Login Failed - Empty Email", 2);
    }

    public void emptyPasswordMessage()
    {
        JOptionPane.showMessageDialog(null, "The password field must not be empty.", "Login Failed - Empty Password", 2);
    }

}
