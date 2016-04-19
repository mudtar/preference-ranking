package UserCreation;

/**
 * The RankingSystemController is used as the interface
 * to connect Login, Registration, AdminSetup
 * and UserTest components.
 *
 * @author BeeYean Tan
 * @version 2016-04-11
 *
 */

public enum RankingSystemController
{
    INSTANCE;

    private User user;
    //private 234a_FueledByJava sqlUser;

    private RankingSystemController()
    {
        this.user = new User();
        //this.sqlUser = 234a_FueledByJava.INSTANCE;
        System.out.println("Program Starts Here!");
    }

    public void launchLogin()
    {
        LoginModel model = new LoginModel();
        model.setUser(this.user);
        System.out.println("RankingSystemController::launchLogin()");

        LoginView view = new LoginFrame();
        LoginController controller = new LoginController(this, model, view);
        view.registerController(controller);
    }

    /*    public void launchAdminTestSetup()
        {
            //AdminTestSetupModel model = new AdminTestSetupModel();
            //model.setExistingItemsList(this.sqlUser.pullExistingItems());
            //model.setTestItemsList(this.sqlUser.pullTestItemsAndImages());
           // model.setImagesList(this.sqlUser.pullAllImages());
            //model.setUser(this.user);

            //AdminTestSetupView view = new AdminTestSetupFrame();
           // AdminTestSetupController controller = new AdminTestSetupController(this, model, view);
            //view.registerController(controller);
        }

    /*
       public void launchUserTest()
        {
            UserTestModel model = new UserTestModel();
            model.setTestItemsList(this.sqlUser.pullTestItemsAndImages());
            model.setUserTest();
            model.setUser(this.user);
            model.setTestSessionID(this.sqlUser.getTestSessionIDScopeIdentity());
            UserTestView view = new UserTestFrame();
            UserTestController controller = new UserTestController(this, model, view);
            view.registerController(controller);
        }
    */
    public void launchRegistration()
    {
        System.out.println("RankingSystemController::launchRegistration()");
        RegistrationModel model = new RegistrationModel();
        RegistrationView view = new RegistrationFrame();
        RegistrationController controller = new RegistrationController(this, model, view);
        view.registerController(controller);
    }

}