package UserCreation;

import ResultReporting.AdminDashboard;


import javax.swing.*;
import java.awt.*;

/**
 * The RankingSystemController is used as the interface
 * to connect Login, Registration, AdminSetup
 * and UserTest components. The RankingSystemController is the parent
 * controller for Login, AdminTestSetup, UserTest and Report controllers.
 *
 * @author BeeYean Tan
 * @version 2016-04-11
 *
 */

public enum RankingSystemController
{
    INSTANCE;

    private User user;
    private UserCreationDB sqlUser;

    /**
     * Constructor of the class
     */
   RankingSystemController()
    {
        this.user = new User();
        this.sqlUser = UserCreationDB.INSTANCE;
        System.out.println("Program Starts Here!");
    }

    /**
     * Launch the Login Page
     */
    public void launchLogin()
    {
        LoginModel model = new LoginModel();
        model.setUser(this.user);
        System.out.println("RankingSystemController::launchLogin()");

        LoginView view = new LoginFrame();
        LoginController controller = new LoginController(this, model, view);
        view.registerController(controller);
    }

    /**
     * Launch the Admin Setup Page
     */
     public void launchAdminTestSetup()
     {
        AdminDashboard adminPanel = new AdminDashboard();
        System.out.println("RankingSystemController::launchAdminTestSetup()");
        JFrame frame = new JFrame("User Test Result Report");
        frame.setPreferredSize(new Dimension(500,300));
        frame.setContentPane(adminPanel);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
     }

    /**
     * Launch the User Test Page
     */
   public void launchUserTest()
    {
        System.out.println("RankingSystemController::launchUserTest()");
        RegistrationModel model = new RegistrationModel();
        RegistrationView view = new RegistrationFrame();
        RegistrationController controller = new RegistrationController(this, model, view);
        view.registerController(controller);

    }

    /**
     * Launch the Registration Page
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