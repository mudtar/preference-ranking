package edu.pcc.fueledbyjava.rankingsystem.usercreation;

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
 *
 * @author BeeYean Tan
 * @version 2016-04-11
 */
public class LoginController
{
    private RankingSystemController controller;
    private LoginModel model;
    private LoginView view;
    //private SqlUser_FueledByJava sqlUser;

    public LoginController(RankingSystemController controller, LoginModel model, LoginView view)
    {
        this.controller = controller;
        this.model = model;
        this.view = view;

       // this.sqlUser = SqlUser_FueledByJava.INSTANCE;

        updateView();
    }

    private void updateView()
    {
        this.view.updateLoginFrameState(this.model.getLoggedInState());
    }

    /**
     *
     * @param username the user's name
     *
     */
    public boolean loginAttempt(String username)
    {
      //  if (this.sqlUser.validateUser(username))
      //  {
     //       this.model.setValidatedUser(this.sqlUser.getUser(username));
     //       this.model.setUserAccessRole(this.sqlUser.getUserAccessRole(username));
     //       this.model.setLoginState(true);
          return true;
    //    }
      //return false;
    }

    /**
     *
     * @return the user's name
     */
    public String getUsername()
    {
        return this.model.getUsername();
    }

    /**
     *
     * @return the user's email address
     */
    public String getUserEmail()
    {
        return this.model.getUserEmail();
    }

    /**
     *
     * @return the user's password
     */
    public String getPassword() { return this.model.getPassword();}

    /**
     * This will launch Admin Setup Test Page
     */
    public void launchAdminTestSetup()
    {
       // this.controller.launchAdminTestSetup();
    }

    public void login()
    {
        this.model.setLoginState(true);
        updateView();
    }

    /**
     *
     * @return the value of true or false to check if user is Admin
     */

    public boolean checkUserAccessRoleForAdmin()
    {
        if (this.model.getUserAccessRole() == Role.UserAccessRole.Admin) {
            return true;
        }
        return false;
    }

    public boolean checkUserAccessRoleForUser()
    {
        if (this.model.getUserAccessRole() == Role.UserAccessRole.User) {
            return true;
        }
        return false;
    }

    /**
     * This method will launch User Test Page
     */
    public void launchUserTestFrame()
    {
        //this.controller.launchUserTest();
    }

    /**
     * This method will launch Registration Page
     */

    public void launchRegistrationFrame()
    {
        //this.controller.launchRegistration();
    }
}
