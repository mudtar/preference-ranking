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
    private RegistrationPanel registrationPanel;
    private User user;
    private LoginModel loginModel;

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

        // Email Format validation
        String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]" +
                "+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        /**
         *   PASSWORD_REGEX explanation
         *  (?=.*[0-9]) a digit must occur at least once
         *  (?=.*[a-z]) a lower case letter must occur at least once
         *  (?=.*[A-Z]) an upper case letter must occur at least once
         *  (?=.*[@#$%!\\.]) a special character must occur at least once
         *  (?=\\S+$) no whitespace allowed in the entire string
         *  .{6, 10} at least 6 characters
         */

        //String PASSWORD_REGEX = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{5,10}";
        String PASSWORD_REGEX = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!\\\\.])(?=\\S+$).{6,10}";

            System.out.println("Starting LoginFrame:: loginAttempt1()function!");
            if ((this.controller.loginAttempt1(this.loginPanel.getUserEmailTextField().trim(),
                                              (this.loginPanel.getPasswordTextField().trim())))
                  && (this.loginPanel.getUserEmailTextField().trim().matches(EMAIL_REGEX))
                  &&(this.loginPanel.getPasswordTextField().trim().matches(PASSWORD_REGEX)))

            {
                System.out.println("LoginFrame::loginAttempt()");
                this.controller.login();

                if (this.controller.checkUserAccessRoleForAdmin())
                {
                    this.controller.launchAdminTestSetup();
                }
                if (this.controller.checkUserAccessRoleForUser())
                {
                    this.controller.launchUserTestFrame();
                }
             }
             else if (this.loginPanel.getUserEmailTextField().trim().equalsIgnoreCase(""))
            {
                invalidEmailFormatMessage();
                this.loginPanel.setUserEmailTextField("");
                JTextField temp =  LoginFrame.this.loginPanel.getLoginEmailControl();
                temp.requestFocus();
            }
            else if (!this.loginPanel.getUserEmailTextField().trim().matches(EMAIL_REGEX))
            {
                invalidEmailFormatMessage();
                //this.loginPanel.setUserEmailTextField("");
                JTextField temp =  LoginFrame.this.loginPanel.getLoginEmailControl();
                temp.requestFocus();
            }
            else if (this.loginPanel.getPasswordTextField().trim().equalsIgnoreCase(""))
            {
                invalidPasswordFormatMessage();
                //emptyPasswordMessage();
                this.loginPanel.setPasswordTextField("");
                JTextField temp =  LoginFrame.this.loginPanel.getLoginPasswordControl();
                temp.requestFocus();
            }
            else if (!this.loginPanel.getPasswordTextField().trim().matches(PASSWORD_REGEX))
            {
                invalidPasswordFormatMessage();
                this.loginPanel.setPasswordTextField("");
                JTextField temp =  LoginFrame.this.loginPanel.getLoginPasswordControl();
                temp.requestFocus();

            }
           else {
                invalidMessage();
                System.out.println("LoginFrame:: Invalid Email & Password-> LoginAttempt Failed)");
                //this.loginPanel.setUserEmailTextField("");
                this.loginPanel.setPasswordTextField("");
                JTextField temp = LoginFrame.this.loginPanel.getLoginEmailControl();
                temp.requestFocus();
            }

        }

        public void invalidEmailFormatMessage()
        {
               JOptionPane.showMessageDialog(null, "'" + this.loginPanel.getUserEmailTextField().trim()
                    + "' is not a valid email or invalid format (must be in the format i.e user@domain.com.)",
                    "Login Failed - Invalid Email Format", 2);
        }

        public void invalidMessage()
        {
            JOptionPane.showMessageDialog(null, "'" +
                   "' This is either not a registered email or password, please verify information!",
                    "Login Failed", 2);
        }

        public void invalidEmailMessage()
        {
            JOptionPane.showMessageDialog(null, "'" + this.loginPanel.getUserEmailTextField().trim() +
                            "' is not a registered email or password, please verify information!",
                    "Login Failed - Invalid Email", 2);
        }
       public void emptyEmailMessage()
        {
            JOptionPane.showMessageDialog(null, "Email field must not be empty " +
                    "and must match an email in the database.", "Login Failed - Empty Email", 2);
        }

        public void emptyPasswordMessage()
        {
            JOptionPane.showMessageDialog(null, "Password field must not be empty.", "Login Failed - Empty Password", 2);
        }

        public void invalidPasswordFormatMessage()
        {
            JOptionPane.showMessageDialog(null, "'" + this.loginPanel.getPasswordTextField().trim()
                            + "' is either not a valid password format or registered password. " +
                  "Please reenter the password. ", "Login Failed " +
                    "- Invalid password Format", 2);
        }

        public void invalidPasswordMessage()
        {
            JOptionPane.showMessageDialog(null, "'" + this.loginPanel.getPasswordTextField().trim()
                    + "' is not a registered password.", "Login Failed - Invalid Password", 2);
        }
    }

