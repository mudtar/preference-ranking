import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class serves as an entry point for the user test component of
 * the hypnotherapy preference ranking system.
 *
 * There are three architectural approaches that can be taken in solving
 * this problem. They consist of putting the bulk of the responsibility
 * on the model, view, or controller.
 *
 * The first approach gives the MODEL the bulk of the responsibility
 * when managing the list of items and their pairs. When the controller
 * needs another pair of items, it asks the model, and only gets one
 * pair at a time. When all the pairs are exhausted, the model tells the
 * controller only when asked for another pair.
 *
 * The second approach gives the VIEW the bulk of the responsibility
 * when managing the list of items and their pairs. Upon initialization,
 * the view is populated with all of the unique pairs of items. All but
 * the first pair are hidden, and they are revealed one-by-one,
 * replacing the prior pair each time the user makes a selection.
 *
 * The third approach gives the CONTROLLER the bulk of the
 * responsibility when managing the list of items and their pairs. In
 * this case, the model is kept as a thin, abstract way to access the
 * database. It passes the full list of unique pairs to the controller,
 * and the controller manages its use directly.
 *
 * Something I haven't yet researched but that might provide some
 * insight: the concept of "fat model, skinny controller" is popular in
 * web development.
 *
 * @author  Ian Burton
 * @version 2016.04.18.01
 */
public class UserTest extends Application
{
    /**
     * The JavaFX entry point.
     *
     * @param  primaryStage The stage created by JavaFX for use with
     *                      this application.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("user_test.fxml"));
        primaryStage.setTitle("Select Your Preference");
        primaryStage.setScene(new Scene(root, 250, 150));
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     * The entry point to the class which launches the JavaFX
     * application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}
