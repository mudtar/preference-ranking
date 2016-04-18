import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

/**
 * The main controller for the User Taking Test package.
 *
 * @author  Ian Burton
 * @version 2016.04.18.01
 */
public class UserTestController {
    /**
     * The ToggleButton representing the first test option.
     */
    @FXML
    private ToggleButton option1;

    /**
     * The ToggleButton representing the second test option.
     */
    @FXML
    private ToggleButton option2;

    /**
     * This method is executed when the associated FXML view is loaded.
     */
    public void initialize() {
        System.out.println("Initializing");
        option1.setText("one");
        option2.setText("two");
    }

    public void handleSubmit() {
        System.out.println("Submit pressed");
    }
}
