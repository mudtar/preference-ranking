package main.java.edu.pcc.fbj.rankingsystem.usercreation;

import java.util.ArrayList;

/**
 * The Registration takes care of the user registration verification, user registration state
 * and user access role logic.
 * i.e valid user's email in the database, logged in or logged out state.
 *
 * @author BeeYean Tan
 * @version  2016-04-16
 */

public class RegistrationController
{
    private RankingSystemController controller;
    private RegistrationModel model;
    private RegistrationView view;
    private UserCreationDB sqlUser;

    public RegistrationController(RankingSystemController controller, RegistrationModel model, RegistrationView view)
    {
        this.controller = controller;
        this.model = model;
        this.view = view;

        this.sqlUser = UserCreationDB.INSTANCE;

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

    public void saveUser(ArrayList<String> user)
    {
        this.sqlUser.saveUser(user);
    }

    public void saveUserAccess(int userID)
    {
        this.sqlUser.saveUserAccess(userID);
    }
    public int getUserID(String emailAddress)
    {
        return this.sqlUser.getUserID(emailAddress);
    }

    public boolean checkUserEmailAvailability(String userEmail)
    {
        System.out.println("RegistrationController::checkUserEmailAvailability()");
        return !this.sqlUser.validateUser(userEmail);

    }

    public boolean checkUserEmailAvailability1(String userEmail, String password)
    {
        System.out.println("RegistrationController::checkUserEmailAvailability1()");
        return !this.sqlUser.validateUser1(userEmail, password);
    }

}

