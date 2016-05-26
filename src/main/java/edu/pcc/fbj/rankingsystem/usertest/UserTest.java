package edu.pcc.fbj.rankingsystem.usertest;

import edu.pcc.fbj.rankingsystem.usercreation.User;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The entry point for the preference test component of the system used
 * to reveal a person's values to a hypnotherapist. These values are
 * determined by analyzing the results of binary comparisons between
 * items. Specifically, this class initializes the JavaFX application
 * and passes necessary data from elsewhere in the application to the
 * preference test controller.
 *
 * @author  Ian Burton
 * @version 2016.05.04.1
 */
public class UserTest extends Application
{
    /**
     * The email address associated with the user who is currently
     * logged in.
     */
    private static String userEmail;

    /**
     * The public interface provided to the rest of the application by
     * which to launch the preference test component.
     *
     * @param user the usercreation.User object representing the
     *             user who is currently logged in
     */
    public static void beginUserTest(User user)
    {
        userEmail = user.getUserEmail();
        launch();
    }

    /**
     * The entry point to the JavaFX application.
     *
     * @param  primaryStage the stage created by JavaFX for use with
     *                      this application
     * @throws IOException  if the FXMLLoader can not load the fxml file
     */
    @Override
    public void start(final Stage primaryStage) throws IOException
    {
        final FXMLLoader loader = new FXMLLoader(
            getClass().getResource("user_test.fxml"));
        final Parent root = (Parent) loader.load();

        final UserTestController controller = loader.getController();
        controller.setStage(primaryStage);
        controller.setUserEmail(userEmail);

        primaryStage.setTitle("Select Your Preference");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }
}
