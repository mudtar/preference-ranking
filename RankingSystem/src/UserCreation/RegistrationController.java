package UserCreation; /**
 * Created by BeeYean on 4/10/2016.
 */

public class RegistrationController
{
    private RankingSystemController controller;
    private RegistrationModel model;
    private RegistrationView view;
    //private 234a_FueledByJava sqlUser;

    public RegistrationController(RankingSystemController controller, RegistrationModel model, RegistrationView view)
    {
        this.controller = controller;
        this.model = model;
        this.view = view;

        //this.sqlUser = 234a_FueledByJava.INSTANCE;

        updateView();
    }

    private void updateView()
    {
        this.view.updateRegistrationFrameState(this.model.getRegistrationState());
    }

    public void hideRegistration()
    {
        this.model.setRegistrationState(true);
        updateView();
    }

    public void launchLogin()
    {
        this.controller.launchLogin();
    }

    public void launchRegistrationFrame()
    {
        this.controller.launchRegistration();
    }

    //public boolean CheckUsernameAvailability(String username)
    //{
        //return !this.sqlUser.validateUser(username);
    //}

   // public void saveUser(ArrayList<String> user)
   // {
        //this.sqlUser.saveUser(user);
   // }

    //public void saveUserAccess(int userID)
    //{
        //this.sqlUser.saveUserAccess(userID);
    //}

   // public int getUserID(String username)
   // {
        //return this.sqlUser.getUserID(username);
   /// }
}

