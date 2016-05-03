package edu.pcc.fbj.rankingsystem.usercreation;

/**
 * The RegistrationFrame class is the GUI of the user registration page.
 *
 *  @author BeeYean Tan
 *  @version 2016-04-16
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class RegistrationFrame
        extends JFrame
        implements RegistrationView
{
    private RegistrationPanel registrationPanel;
    private RegistrationController controller;
    private LoginModel model;
    private LoginController loginController;

    String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)" +
           "+[a-zA-Z]{2,6}$";

    /**
     *   PASSWORD_REGEX explanation
     *  (?=.*[0-9]) a digit must occur at least once
     *  (?=.*[a-z]) a lower case letter must occur at least once
     *  (?=.*[A-Z]) an upper case letter must occur at least once
     *  (?=.*[@#$%^&+=]) a special character must occur at least once
     *  (?=\\S+$) no whitespace allowed in the entire string
     *  .{8,} at least 8 characters
     */
    String PASSWORD_REGEX = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{5,10}";

    /**
     * constructor of the class
     */
    public RegistrationFrame()
    {
        super("Ranking System - Registration");
        this.registrationPanel = createRegistrationPanel();
        getContentPane().add(this.registrationPanel);
        setResizable(false);
        setDefaultCloseOperation(0);
        pack();
        setVisible(false);
        setLocationRelativeTo(null);
    }

    private RegistrationPanel createRegistrationPanel()
    {
        this.registrationPanel = new RegistrationPanel();
        this.registrationPanel.requestFocus();
        System.out.println("RegistrationPanel");

        this.registrationPanel.addSubmitButtonActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                boolean valid = RegistrationFrame.this.
                noEmptyRegistrationTextFields(RegistrationFrame.this.registrationPanel);

            if (RegistrationFrame.this.registrationPanel.getUsername().trim().equals(""))
            {
                RegistrationFrame.this.emptyNameMessage(RegistrationFrame.this.registrationPanel);

            }
            else if (RegistrationFrame.this.registrationPanel.getEmail().trim().equals(""))
            {
                RegistrationFrame.this.emptyEmailMessage(RegistrationFrame.this.registrationPanel);

            }
            else if (!registrationPanel.getEmail().trim().matches(EMAIL_REGEX))
            {
                RegistrationFrame.this.invalidEmailFormatMessage(RegistrationFrame.this.registrationPanel);

            }
            else if (!RegistrationFrame.this.checkUsernameAvailability(RegistrationFrame.this.registrationPanel))
            {
                RegistrationFrame.this.emailAlreadyTakenUsernameMessage(RegistrationFrame.this.registrationPanel);

            }
            else if (RegistrationFrame.this.registrationPanel.getPassword().trim().equals(""))
            {
                RegistrationFrame.this.emptyPasswordMessage(RegistrationFrame.this.registrationPanel);
            }
            else if (!registrationPanel.getPassword().trim().matches(PASSWORD_REGEX))
            {
                RegistrationFrame.this.invalidPasswordFormatMessage(RegistrationFrame.this.registrationPanel);

            }
            else if (RegistrationFrame.this.registrationPanel.getReenterPassword().trim().equals(""))
            {
                RegistrationFrame.this.emptyReenterPasswordMessage(RegistrationFrame.this.registrationPanel);
            }
            else if (!RegistrationFrame.this.registrationPanel.getPassword().trim()
                    .equalsIgnoreCase(RegistrationFrame.this.registrationPanel.getReenterPassword().trim()))

            {
               System.out.println("compare 2 password string");
               RegistrationFrame.this.noMatchPasswordField(RegistrationFrame.this.registrationPanel);
            }
            else if (valid)
            {
                ArrayList<String> user = new ArrayList();
                user.add(RegistrationFrame.this.registrationPanel.getUsername().trim());
                user.add(RegistrationFrame.this.registrationPanel.getEmail().trim());
                user.add(RegistrationFrame.this.registrationPanel.getPassword().trim());
                user.add(RegistrationFrame.this.registrationPanel.getReenterPassword().trim());

                RegistrationFrame.this.controller.saveUser(user);
                int userID = RegistrationFrame.this.controller.getUserID(RegistrationFrame.
                        this.registrationPanel.getUsername());
                // RegistrationFrame.this.controller.saveUserAccess(userID);
                RegistrationFrame.this.controller.hideRegistration();
                //RegistrationFrame.this.newUserWelcomeMessage(RegistrationFrame.this.registrationPanel);
                String email = RegistrationFrame.this.registrationPanel.getEmail().trim();
                String password = RegistrationFrame.this.registrationPanel.getPassword().trim();
                StaticUserCredential.setStaticEmail(email);
                StaticUserCredential.setStaticPassword(password);
                //RankingSystemController controller1 = RankingSystemController.INSTANCE;
                RegistrationFrame.this.controller.launchLogin();
                //RegistrationFrame.this.controller.controller.launchLogin1(email, password);

              }
            }
        });
        this.registrationPanel.addCancelButtonActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                RegistrationFrame.this.controller.hideRegistration();
                RegistrationFrame.this.updateRegistrationFrameState(true);
                //RegistrationFrame.this.cancelRegistrationMessage(RegistrationFrame.this.registrationPanel);
                RegistrationFrame.this.controller.launchLogin();
            }
        });

        return this.registrationPanel;
    }

    public boolean updateRegistrationFrameState(boolean registrationFrameState)
    {
        setVisible(!registrationFrameState);
        return registrationFrameState;
    }

    public void registerController(RegistrationController controller)
    {
        this.controller = controller;
    }

    private void cancelRegistrationMessage(RegistrationPanel registrationPanel)
    {
        JOptionPane.showMessageDialog(null, "User Registration has been cancelled. " +
                "Returning to the login screen.", "New User Registration Cancelled", -1);

    }

    private void emailAlreadyTakenUsernameMessage(RegistrationPanel registrationPanel)
    {
        JOptionPane.showMessageDialog(null, "'" + registrationPanel.getEmail().trim()
                + "' is already taken. The email must contain at least 1 character and can not match " +
                "another email in the database.", "Email  '" + registrationPanel.getEmail().trim()
                + "' Is Already Taken", 2);
        JTextField temp =  RegistrationFrame.this.registrationPanel.getEmailControl();
        temp.requestFocus();

    }

    private void validUsernameMessage(RegistrationPanel registrationPanel)
    {
        JOptionPane.showMessageDialog(null, "The username: '" + registrationPanel.getUsername().trim()
                + "' is available!", "Username  '" + registrationPanel.getUsername().trim() + "' Is Available", 1);
    }

    private void matchPasswordField(RegistrationPanel registrationPane) {
        JOptionPane.showMessageDialog(null, "Both Password match!",
                "matchPassword", 1);
        JTextField temp =  RegistrationFrame.this.registrationPanel.getPasswordControl();
        temp.requestFocus();
    }

    private void emptyEmailMessage(RegistrationPanel registrationPanel)
    {
        JOptionPane.showMessageDialog(null, "The Email can not be empty, " +
                "please fill in the email field.", "Empty Email", 2);
        JTextField temp =  RegistrationFrame.this.registrationPanel.getEmailControl();
        temp.requestFocus();
    }

    private void invalidEmailFormatMessage(RegistrationPanel registrationPanel)
    {
        JOptionPane.showMessageDialog(null, "The Email format is invalid, " +
                "it should looks like - i.e yyyy@yahoo.com.", "Invalid Email", 2);
        JTextField temp =  RegistrationFrame.this.registrationPanel.getEmailControl();
        temp.requestFocus();
        this.registrationPanel.setEmailTextField("");
    }

    private void invalidPasswordFormatMessage(RegistrationPanel registrationPanel)
    {
        JOptionPane.showMessageDialog(null, "The Password format is invalid, " +
                "it should looks like - i.e: Cis234a$.", "Invalid Password", 2);
        JTextField temp =  RegistrationFrame.this.registrationPanel.getPasswordControl();
        temp.requestFocus();
        this.registrationPanel.setPasswordTextField("");
    }

    private void emptyPasswordMessage(RegistrationPanel registrationPanel)
    {
        JOptionPane.showMessageDialog(null, "The Password can not be empty, " +
                "please fill in the password field.", "Empty Password", 3);
        JTextField temp =  RegistrationFrame.this.registrationPanel.getPasswordControl();
        temp.requestFocus();

    }

    private void  emptyReenterPasswordMessage(RegistrationPanel registrationPanel)
    {
        JOptionPane.showMessageDialog(null, "The Reenter Password can not be empty, " +
                "please fill in the password field.", "Empty Password", 3);
        JTextField temp =  RegistrationFrame.this.registrationPanel.getReenterPasswordControl();
        temp.requestFocus();

    }

    private void emptyNameMessage(RegistrationPanel registrationPanel)
    {
        JOptionPane.showMessageDialog(null, "The Name can not be empty, please " +
                "fill in the name field.", "Empty Name", 1);
        JTextField temp =  RegistrationFrame.this.registrationPanel.getNameControl();
        temp.requestFocus();

    }

    private void noMatchPasswordField(RegistrationPanel registrationPanel)
    {
        JOptionPane.showMessageDialog(null, "Both Password fields must match, please reenter password fields.",
                "Empty NoMatchPassword", 1);
        JTextField temp =  RegistrationFrame.this.registrationPanel.getReenterPasswordControl();
        temp.requestFocus();
        this.registrationPanel.setReenterPasswordTextField("");
    }

    private boolean noEmptyRegistrationTextFields(RegistrationPanel registrationPanel)
    {
        boolean valid = true;

       if (registrationPanel.getUsername().trim().equals(""))
        {
            valid = false;
        }
        if (registrationPanel.getEmail().trim().equals(""))
        {
            valid = false;
        }
        else if (!registrationPanel.getEmail().trim().matches(EMAIL_REGEX))
        {
            valid = false;
        }
        if (registrationPanel.getPassword().trim().equals(""))
        {
            valid = false;
        }
        if (registrationPanel.getReenterPassword().trim().equals(""))
        {
            valid = false;
        }
        if (!RegistrationFrame.this.registrationPanel.getPassword().trim()
                .equalsIgnoreCase(RegistrationFrame.this.registrationPanel.getReenterPassword().trim()))
        {
            valid = false;
        }

        return valid;
    }

    private boolean checkUsernameAvailability(RegistrationPanel registrationPanel)
    {
        System.out.println("RegistrationController::checkUsernameAvailability");
        return this.controller.checkUserEmailAvailability1(registrationPanel.getEmail().trim(),
                registrationPanel.getPassword().trim());
    }

}
