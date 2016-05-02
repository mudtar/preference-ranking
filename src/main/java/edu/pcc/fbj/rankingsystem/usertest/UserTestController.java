package edu.pcc.fbj.rankingsystem.usertest;

import java.util.List;
import java.sql.SQLException;
import java.util.Map;

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
    private UserTestItemManager userTestItems;
    private UserTestResultManager userTestResults;

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
     * Default constructor.
     *
     * @throws SQLException if a database access error occurs or the url
     *                      is null
     */
    public UserTestController() throws SQLException
    {
        userTestItems = new UserTestItemManager();
        userTestResults = new UserTestResultManager();
    }

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
            boolean tie = false;
            for (Toggle t : options.getToggles())
            {
                Object userData = t.getUserData();

                if (userData == null)
                {
                    // This toggle is the "I Can't Decide" button.

                    if (t.isSelected())
                    {
                        // And the "I Can't Decide" button is selected.
                        // Since we know that this submission is a tie,
                        // we know there are no winners or losers, so we
                        // can break out of the loop.
                        tie = true;
                        break;
                    }
                }
                else
                {
                    // This toggle must be one of the test items.

                    if (t.isSelected())
                    {
                        // The winner is the selected toggle.
                        winner = userData.toString();
                    }
                    else
                    {
                        // The loser is the unselected toggle.
                        loser = userData.toString();
                    }
                }
            }

            if (tie)
            {
                // It doesn't feel right to reach back to the
                // ToggleButtons to get their text. For now, this will
                // do.
                userTestResults.registerTestResult(option1.getText(),
                                                   option2.getText(), 0);
            }
            else
            {
                // This properly reflects which item was the winner and
                // which was the loser, but by this design, the winner
                // is always the first argument and the loser always the
                // second. I'd like to be able to pass option1 as the
                // first argument and option2 as the second, with the
                // result argument varying based on which won or lost.
                // For now, this will do.
                userTestResults.registerTestResult(winner, loser, -1);
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
            List<Map.Entry<Integer, String>> testItemPair =
                userTestItems.getTestItemPair();

            // Populate the buttons with the names of the items.
            option1.setUserData(testItemPair.get(0).getValue());
            option2.setUserData(testItemPair.get(1).getValue());

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
