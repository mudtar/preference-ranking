import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

/**
 * The main controller for the User Taking Test package.
 *
 * @author  Ian Burton
 * @version 2016.04.18.01
 */
public class UserTestController {
    private UserTestModel model = new UserTestModel();

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
     * This method is executed automatically when the associated FXML
     * view is loaded.
     */
    public void initialize() {
        List<List<String>> testItemPairs = model.getTestItemPairs();
        List<String> firstPair = testItemPairs.get(0);

        // Populate the button labels with the items in the first pair.
        option1.setText(firstPair.get(0));
        option2.setText(firstPair.get(1));
    }

    public void handleSubmit() {
        System.out.println("Preference submitted.");
    }
}
