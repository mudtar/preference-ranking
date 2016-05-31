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


    /**
     * Check if username meets the following requirements:
     *  - Between 6 and 15 characters long in combination of letters and digit.
     *  - Contains characters, numbers and the ., -, _ symbols.
     */
    String USERNAME_PATTERN = "^[a-zA-Z0-9_-]{6,15}$";

    String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)" +
           "+[a-zA-Z]{2,6}$";

    /**
     *   PASSWORD_REGEX explanation
     *  (?=.*[0-9]) a digit must occur at least once
     *  (?=.*[a-z]) a lower case letter must occur at least once
     *  (?=.*[A-Z]) an upper case letter must occur at least once
     *  (?=.*[@#$%!\\.]) a special character must occur at least once
     *  (?=\\S+$) no whitespace allowed in the entire string
     *  .{6, 10} at least 6 characters and maximum 10 characters
     *
     * Check if the password meets the following requirements:
     *  - Be between 6 and 10 characters long
     *  - Contain at least one digit.
     *  - Contain at least one lower case character.
     *  - Contain at least one upper case character.
     *  - Contain at least on special character from [ @ # $ % ! . ].
     */

    String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!\\\\.])(?=\\S+$).{6,10}";

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
                RegistrationFrame.this.resetErrorLabelVisibility(RegistrationFrame.this.registrationPanel);
                boolean valid = RegistrationFrame.this.
                noEmptyRegistrationTextFields(RegistrationFrame.this.registrationPanel);

            if (RegistrationFrame.this.registrationPanel.getUsername().trim().equals(""))
            {
                RegistrationFrame.this.emptyNameMessage(RegistrationFrame.this.registrationPanel);
            }
            else if (!RegistrationFrame.this.registrationPanel.getUsername().trim().matches(USERNAME_PATTERN))
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
                RegistrationFrame.this.controller.hideRegistration();

                String email = RegistrationFrame.this.registrationPanel.getEmail().trim();
                String password = RegistrationFrame.this.registrationPanel.getPassword().trim();
                StaticUserCredential.setStaticEmail(email);
                StaticUserCredential.setStaticPassword(password);
                RegistrationFrame.this.controller.launchLogin();
              }
            }
        });
        this.registrationPanel.addCancelButtonActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                RegistrationFrame.this.controller.hideRegistration();
                RegistrationFrame.this.updateRegistrationFrameState(true);
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

    private void emailAlreadyTakenUsernameMessage(RegistrationPanel registrationPanel)
    {
        JOptionPane.showMessageDialog(null, "'" + registrationPanel.getEmail().trim()
                + "' is already taken. Please enter new email address. " , "Email  '"
                + registrationPanel.getEmail().trim()
                + "' Is Already Taken", 2);
        JTextField temp =  RegistrationFrame.this.registrationPanel.getEmailControl();
        temp.requestFocus();
    }

    private void emptyEmailMessage(RegistrationPanel registrationPanel)
    {
        registrationPanel.setEmailErrorLabelVisibility(true);
        JTextField temp =  RegistrationFrame.this.registrationPanel.getEmailControl();
        temp.requestFocus();

    }

    private void invalidEmailFormatMessage(RegistrationPanel registrationPanel)
    {
        registrationPanel.setEmailErrorLabelVisibility(true);
        JTextField temp =  RegistrationFrame.this.registrationPanel.getEmailControl();
        temp.requestFocus();
    }

    private void invalidPasswordFormatMessage(RegistrationPanel registrationPanel)
    {
       registrationPanel.setPasswordErrorLabelVisibility(true);
       JTextField temp =  RegistrationFrame.this.registrationPanel.getPasswordControl();
       temp.requestFocus();
       this.registrationPanel.setPasswordTextField("");
    }

    private void emptyPasswordMessage(RegistrationPanel registrationPanel)
    {
        registrationPanel.setPasswordErrorLabelVisibility(true);
        JTextField temp =  RegistrationFrame.this.registrationPanel.getPasswordControl();
        temp.requestFocus();
    }

    private void  emptyReenterPasswordMessage(RegistrationPanel registrationPanel)
    {
        registrationPanel.setReenterPasswordErrorLabelVisibility(true);
        JTextField temp =  RegistrationFrame.this.registrationPanel.getReenterPasswordControl();
        temp.requestFocus();
    }

    private void emptyNameMessage(RegistrationPanel registrationPanel)
    {
        registrationPanel.setUsernameErrorLabelVisibility(true);
        JTextField temp =  RegistrationFrame.this.registrationPanel.getNameControl();
        temp.requestFocus();
    }

    private void noMatchPasswordField(RegistrationPanel registrationPanel)
    {
        registrationPanel.setReenterPasswordErrorLabelVisibility(true);
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

    private void resetErrorLabelVisibility(RegistrationPanel registrationPanel)
    {
        registrationPanel.setPasswordErrorLabelVisibility(false);
        registrationPanel.setReenterPasswordErrorLabelVisibility(false);
        registrationPanel.setUsernameErrorLabelVisibility(false);
        registrationPanel.setEmailErrorLabelVisibility(false);
    }

}
