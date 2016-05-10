package edu.pcc.fbj.rankingsystem.usertest;

import edu.pcc.fbj.rankingsystem.usercreation.User;

import java.awt.*;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sun.net.ProgressEvent;

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
    private ProgressBar progress;
    private Label label;


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
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Scene scene = new Scene(grid, 300, 275);

        Text sceneTitle = new Text("Progress Meter");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        sceneTitle.setFill(Color.RED);
        grid.add(sceneTitle, 0, 0, 2, 1);



        primaryStage.show();



    }
}
