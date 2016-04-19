package UserCreation;

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
    //private 234a_FueledByJava sqlUser

    public LoginController(RankingSystemController controller, LoginModel model, LoginView view)
    {
        this.controller = controller;
        this.model = model;
        this.view = view;

        // this.sqlUser = 234a_FueledByJava.INSTANCE;

        updateView();
    }

    private void updateView()
    {
        this.view.updateLoginFrameState(this.model.getLoggedInState());
    }

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

    public String getUsername()
    {
        return this.model.getUsername();
    }

    public String getUserEmail()
    {
        return this.model.getUserEmail();
    }

    public void launchAdminTestSetup()
    {
        // this.controller.launchAdminTestSetup();
    }

    public void login()
    {
        this.model.setLoginState(true);
        updateView();
    }

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

    public void launchUserTestFrame()
    {
        //this.controller.launchUserTest();
    }

    public void launchRegistrationFrame()
    {
        this.controller.launchRegistration();
    }
}
