package edu.pcc.fbj.rankingsystem.usertest;

import java.util.List;
import java.sql.SQLException;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * The main controller for the User Taking Test package.
 *
 * @author  Ian Burton
 * @version 2016.04.18.01
 */
public class UserTestController
{
    private Items userTestItems;
    private Preferences userTestResults;

    /**
     * The primary JavaFX stage.
     */
    private Stage primaryStage;

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
     * The ToggleButton representing the "I Can't Decide" option.
     */
    @FXML
    private ToggleButton tie;

    /**
     * Default constructor.
     *
     * @throws SQLException if a database access error occurs or the url
     *                      is null
     */
    public UserTestController() throws SQLException
    {
        userTestItems = new Items();
        userTestResults = new Preferences();
    }

    /**
     * This method is executed automatically when the associated FXML
     * view is loaded.
     */
    public void initialize()
    {
        updateOptionButtons();
    }

    public void setStage(final Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }

    public void setUserEmail(String userEmail)
    {
        userTestResults.setUserEmail(userEmail);
    }

    /**
     * Executed when the submit button is pressed. If one of the options
     * has been selected, register the test result with the
     * ResultManager.
     */
    public void handleSubmit() throws SQLException
    {
        Toggle selectedToggle = options.getSelectedToggle();

        // Determine whether any of the toggles have been selected.
        if (selectedToggle != null)
        {
            // I would have preferred to get the fx:id of the selected
            // toggle in order to identify which one was selected, but
            // there doesn't seem to be a convenient way to do so.
            // Instead, I have to call each toggle out by name and ask
            // if it's selected or not.
            if (option1.isSelected())
            {
                userTestResults.registerTestResult(
                    (int) option1.getProperties().get("itemID"),
                    (int) option2.getProperties().get("itemID"), -1);
            }
            else if (option2.isSelected())
            {
                userTestResults.registerTestResult(
                    (int) option1.getProperties().get("itemID"),
                    (int) option2.getProperties().get("itemID"), 1);
            }
            else if (tie.isSelected())
            {
                userTestResults.registerTestResult(
                    (int) option1.getProperties().get("itemID"),
                    (int) option2.getProperties().get("itemID"), 0);
            }

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
                userTestResults.storeResults();

                primaryStage.close();
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
    void updateOptionButtons() throws IndexOutOfBoundsException
    {
        // This method should throw an exception if testItemPair is
        // null, signifying that there are no more pairs to test. The
        // caller can handle the exception by producing the "you're
        // finished!" screen and pushing the test results to the
        // database.
        try
        {
            List<Map.Entry<Integer, String>> testItemPair =
                userTestItems.getTestItemPair();

            // Associate the items' IDs with their toggle buttons.
            option1.getProperties().put("itemID", testItemPair.get(0).getKey());
            option2.getProperties().put("itemID", testItemPair.get(1).getKey());

            // Create the labels for the buttons.
            option1.setText(testItemPair.get(0).getValue());
            option2.setText(testItemPair.get(1).getValue());
        }
        catch (IndexOutOfBoundsException e)
        {
            // There are no more test item pairs to handle.
            throw e;
        }
    }
}
