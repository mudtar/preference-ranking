package edu.pcc.fbj.rankingsystem.usercreation;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The LoginController is used to communicate
 * between classes in the LoginModel and LoginView.
 * Methods use in this class are:
 *      updateView(),  loginAttempt(),
 *      getUsername(), getUserEmail(),
 *      getPassword(), launchAdminTestSetup(),
 *      launchAdminTestSetup(), login(),
 *      checkUserAccessRoleForAdmin(), checkUserAccessRoleForUser(),
 *      launchUserTestFrame(), launchRegistrationFrame().
 * The LoginController handles the user login checks, user
 * login state and user access role.
 *
 * @author BeeYean Tan
 * @version 2016-04-11
 */
public class LoginController
{
    private RankingSystemController controller;
    private LoginModel model;
    private LoginView view;
    private UserCreationDB sqlUser;
    private LoginPanel loginPanel;

    /**
     *
     * @param controller for communication
     * @param model will manage the data and logic of the login
     * @param view output the login information
     */
    public LoginController(RankingSystemController controller, LoginModel model, LoginView view)
    {
        this.controller = controller;
        this.model = model;
        this.view = view;

       this.sqlUser = UserCreationDB.INSTANCE;

        updateView();
    }

    /**
     * Update the view of the login frame
     */
    private void updateView()
    {
        // loginState is false when it is logged off, true when it is logged in
        this.view.updateLoginFrameState(this.model.getLoggedInState());
    }

    /**
     * The user is attempt to login in
     * @param email entered by the current user
     * @return true if the user's email is found in the database
     */
    public boolean loginAttempt(String email)
    {
        if (this.sqlUser.validateUser(email))
          {
               this.model.setValidatedUser(this.sqlUser.getUser(email));
               this.model.setUserAccessRole(this.sqlUser.getUserAccessRole(email));
               this.model.setLoginState(true);

              return true;
         }

        System.out.println("LoginController::loginAttempt - false");
        //invalidEmailMessage();
        return false;

    }

    /**
     * @return the user's name
     */
   public String getUsername()
    {
      return this.model.getUsername();
    }

   // public String getUserEmail()
   // {
  //      return this.model.getUserEmail();
   // }

  //  public String getPassword()
   // {
   //     return this.model.getPassword();
   // }

    /**
     * Launch Admin Setup Page
     */
    public void launchAdminTestSetup()
    {
        this.controller.launchAdminTestSetup();
    }

    /**
     * Hide the login page when the user is logged in
     */
    public void login()
    {
        this.model.setLoginState(true);
        updateView();
    }

    /**
     * Check the user access role to see if it is an Administrator
     * @return true if the user access role is Admin, false if it is not
     */
    public boolean checkUserAccessRoleForAdmin()
    {
        if (this.model.getUserAccessRole() == true)
        {
            return true;
        }
        return false;
    }

    /**
     * Check the user access role to see if it is an User
     * @return true if the user access role is User, false if it is not
     */
    public boolean checkUserAccessRoleForUser()
    {
        if (this.model.getUserAccessRole() == false)
        {
            return true;
        }
        return false;
    }

    /**
     * Launch User Test Page
     */
    public void launchUserTestFrame()
    {
        this.controller.launchUserTest();
    }

    /**
     * Launch the user registration page
     */
    public void launchRegistrationFrame()
    {
        this.controller.launchRegistration();
    }

   // public void invalidEmailMessage()
   // {
  //      JOptionPane.showMessageDialog(null, "'" + this.loginPanel.getUserEmailTextField().trim()
   //             + "' is not a registered email.", "Login Failed - Invalid Email", 2);
   // }

}
