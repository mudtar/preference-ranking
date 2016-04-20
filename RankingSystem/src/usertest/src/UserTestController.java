import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

/**
 * The main controller for the User Taking Test package.
 *
 * @author  Ian Burton
 * @version 2016.04.18.01
 */
public class UserTestController
{
    private UserTestItemManager userTestItems = new UserTestItemManager();

    /**
     * The ToggleGroup that contains all of the user's options when
     * taking the test.
     */
    @FXML
    private ToggleGroup options;

    /**
     * The ToggleButton representing the first test option. For
     * scalability's sake, it might make sense to use an array of
     * ToggleButton rather than two individual ToggleButton variables. I
     * won't know without doing some research whether this would mesh
     * well with the FXML view.
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
    public void initialize()
    {
        updateOptionButtons();
    }

    public void handleSubmit()
    {
        Toggle selectedToggle = options.getSelectedToggle();

        // For now, this just disables the submit button when nothing is
        // selected. The user should be notified of this condition
        // somehow.
        if (selectedToggle != null)
        {
            String winner = "";
            String loser = "";
            for (Toggle t : options.getToggles())
            {
                // The winner is the toggle that is selected.
                if (t.isSelected())
                {
                    winner = t.getUserData().toString();
                }
                // The loser is the toggle that is not selected and
                // whose getUserData() is not null. The toggle with null
                // getUserData() is the "I Can't Decide" button.
                else if (t.getUserData() != null)
                {
                    loser = t.getUserData().toString();
                }
            }
            System.out.println("Winner: " + winner);
            System.out.println(" Loser: " + loser);

            // Unselect the previously selected toggle.
            selectedToggle.setSelected(false);

            try
            {
                updateOptionButtons();
            }
            catch (IndexOutOfBoundsException e)
            {
                // There are no more test item pairs to handle. This is
                // where the "You're Finished!" screen should be called
                // to replace the test screen.
                System.out.println("You're Finished!");
            }
        }
    }

    /**
     * Update the GUI's option buttons with the next pair of items to be
     * considered.
     *
     * @throws IndexOutOfBoundsException if there are no more test item
     *                                   pairs to handle
     */
    public void updateOptionButtons() throws IndexOutOfBoundsException
    {
        // This method should throw an exception if testItemPair is
        // null, signifying that there are no more pairs to test. The
        // caller can handle the exception by producing the "you're
        // finished!" screen and pushing the test results to the
        // database.
        try
        {
            List<String> testItemPair = userTestItems.getTestItemPair();

            // Populate the buttons with the names of the items.
            option1.setUserData(testItemPair.get(0));
            option2.setUserData(testItemPair.get(1));

            // Create the labels for the buttons.
            option1.setText(testItemPair.get(0));
            option2.setText(testItemPair.get(1));
        }
        catch (IndexOutOfBoundsException e)
        {
            // There are no more test item pairs to handle.
            throw e;
        }
    }
}
