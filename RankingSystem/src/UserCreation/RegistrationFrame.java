package UserCreation; /**
 * Created by BeeYean on 4/10/2016.
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
        System.out.println("RegistrationPanel");
        this.registrationPanel.addSubmitButtonActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                //RegistrationFrame.this.resetErrorLabelVisibility(RegistrationFrame.this.registrationPanel);

                //boolean valid = RegistrationFrame.this.noEmptyRegistrationTextFields(RegistrationFrame.this.registrationPanel);
                //if (!RegistrationFrame.this.checkUsernameAvailability(RegistrationFrame.this.registrationPanel))
               // {
               //     RegistrationFrame.this.usernameAlreadyTakenUsernameMessage(RegistrationFrame.this.registrationPanel);
               // }
                //else if (RegistrationFrame.this.registrationPanel.getUsername().trim().equals(""))
                //{
                //    RegistrationFrame.this.emptyUsernameMessage();
                //}
               //else if (canProcess)
               // if (valid)
                //{
                    ArrayList<String> user = new ArrayList();

                    user.add(RegistrationFrame.this.registrationPanel.getEmail().trim());
                    user.add(RegistrationFrame.this.registrationPanel.getUsername().trim());
                    user.add(RegistrationFrame.this.registrationPanel.getPassword().trim());

                  //  RegistrationFrame.this.controller.saveUser(user);
                    //int userID = RegistrationFrame.this.controller.getUserID(RegistrationFrame.this.registrationPanel.getUsername());
                   // RegistrationFrame.this.controller.saveUserAccess(userID);
                    //RegistrationFrame.this.controller.hideRegistration();
                    //RegistrationFrame.this.newUserWelcomeMessage(RegistrationFrame.this.registrationPanel);
                    RegistrationFrame.this.controller.launchLogin();
              }
           // }
        });
        /*this.registrationPanel.addCancelButtonActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                RegistrationFrame.this.controller.hideRegistration();
                RegistrationFrame.this.updateRegistrationFrameState(true);
                RegistrationFrame.this.cancelRegistrationMessage(RegistrationFrame.this.registrationPanel);
                RegistrationFrame.this.controller.launchLogin();
            }
        });

        */
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
/*
    private void newUserWelcomeMessage(RegistrationPanel registrationPanel)
    {
        JOptionPane.showMessageDialog(null, new JLabel("Welcome " + registrationPanel.getUsername() + " " + registrationPanel.getEmail(), 0), "New User Registration Success", -1);
    }

    private void cancelRegistrationMessage(RegistrationPanel registrationPanel)
    {
        JOptionPane.showMessageDialog(null, "User Registration has been cancelled. Returning to the login screen.", "New User Registration Cancelled", -1);
    }

    private void usernameAlreadyTakenUsernameMessage(RegistrationPanel registrationPanel)
    {
        JOptionPane.showMessageDialog(null, "'" + registrationPanel.getUsername().trim() + "' is already taken. The username must contain at least 1 character and can not match another username in the database.", "Username  '" + registrationPanel.getUsername().trim() + "' Is Already Taken", 2);
    }

    private void validUsernameMessage(RegistrationPanel registrationPanel)
    {
        JOptionPane.showMessageDialog(null, "The username: '" + registrationPanel.getUsername().trim() + "' is available!", "Username  '" + registrationPanel.getUsername().trim() + "' Is Available", 1);
    }

    private void emptyUsernameMessage()
    {
        JOptionPane.showMessageDialog(null, "The username must contain at least 1 character and can not match another username in the database.", "Empty Username", 2);
    }
    */
}
