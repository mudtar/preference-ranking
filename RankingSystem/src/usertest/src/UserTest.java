import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class serves as an entry point for the user test component of
 * the hypnotherapy preference ranking system.
 *
 * @author  Ian Burton
 * @version 2016.04.15.01
 */
public class UserTest extends Application {
    /**
     * The JavaFX entry point.
     *
     * @param  primaryStage The stage created by JavaFX for use with
     *                      this application.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("user_test.fxml"));
        primaryStage.setTitle("Select Your Preference");
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     * The entry point to the class which launches the JavaFX
     * application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
