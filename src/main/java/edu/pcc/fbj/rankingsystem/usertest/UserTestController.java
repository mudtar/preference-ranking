package edu.pcc.fbj.rankingsystem.usertest;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

/**
 * The controller for the preference test, which brings together the GUI
 * view with the items to be tested and the preferences selected by the
 * user.
 *
 * @author  Ian Burton
 * @version 2016.05.04.1
 */
public class UserTestController implements Initializable
{
    //This the counter used for progress bar
    private double currentPairCount = 0;

    /**
     * The items to be presented to the user by which to determine user
     * preferences.
     */
    private Items items;

    /**
     * The user's preferences, which are the results of the test.
     */
    private Preferences preferences;

    /**
     * The stage created by JavaFX when the preference test GUI was
     * initialized.
     */
    private Stage primaryStage;

    /**
     * The group of toggle buttons that represent the user's options for
     * selecting a preference.
     */
    @FXML
    private ToggleGroup options;

    /**
     * The button representing the first test option.
     */
    @FXML
    private ToggleButton option1;

    /**
     * The button representing the second test option.
     */
    @FXML
    private ToggleButton option2;

    /**
     * The button representing the "I Can't Decide" test option, which
     * the user selects when they can't decide between the two test
     * items.
     */
    @FXML
    private ToggleButton tie;

    /**
     * The label indicating to the user which pair number they are
     * seeing and how many total pairs will be presented.
     */
    @FXML
    private Label progressLabel;

    /**
     * The progress bar used to visually indicate the progress the
     * user's progress toward completion of the preference test.
     */
    @FXML
    private ProgressBar progressBar;

    /**
     * Constructs the controller for the preference test.
     *
     * @throws SQLException if a database access error occurs
     */
    public UserTestController() throws SQLException
    {
        items = new Items();
        preferences = new Preferences();
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for
     *                  the root object, or null if the location is not
     *                  known.
     * @param resources The resources used to localize the root object,
     *                  or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        updateOptionButtons();
    }

    /**
     * Set the primaryStage field.
     *
     * @param primaryStage the stage created by JavaFX when the
     *                     preference test GUI was initialized
     */
    public void setStage(final Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }

    /**
     * Pass to the object used to manage user preferences the email
     * address associated with the user who is currently logged in so
     * that the preferences can be associated in the database with the
     * proper user.
     *
     * @param userEmail the email address associated with the user who
     *                  is currently logged in
     */
    public void setUserEmail(String userEmail)
    {
        preferences.setUserEmail(userEmail);
    }

    /**
     * Executes when the submit button is pressed. If one of the options
     * has been selected, register the preference. When all items are
     * exhausted, store the preferences to the database.
     *
     * @throws SQLException if a database access error occurs
     */
    public void handleSubmit() throws SQLException
    {
        Toggle selectedToggle = options.getSelectedToggle();

        // Only register the user's preference if they actually made a
        // selection before pressing the submit button.
        if (selectedToggle != null)
        {
            registerPreference();

            updateProgressBar();
            // Unselect the selected button in preparation for the
            // buttons to be updated with new items.
            selectedToggle.setSelected(false);

            try
            {
                updateOptionButtons();
            }
            catch (IndexOutOfBoundsException e)
            {
                // There are no more test item pairs to handle.
                preferences.storePreferences();
                primaryStage.close();
            }
        }
    }

    /**
     * Update the GUI's option buttons with the next pair of items to be
     * presented to the user.
     *
     * @throws IndexOutOfBoundsException when there are no more test
     *                                   item pairs to handle
     */
    private void updateOptionButtons() throws IndexOutOfBoundsException
    {
        // When there are no more test item pairs available, this throws
        // an IndexOutOfBoundsException.
        List<Map.Entry<Integer, String>> itemPair = items.getNextItemPair();

        // Associate the items' IDs with their toggle buttons.
        option1.getProperties().put("itemID", itemPair.get(0).getKey());
        option2.getProperties().put("itemID", itemPair.get(1).getKey());

        // Create the text labels for the buttons from the items' names.
        option1.setText(itemPair.get(0).getValue());
        option2.setText(itemPair.get(1).getValue());
    }

    public void handleBack()
    {
        // Items: right now, getItemPair(), but we want: getNextItemPair() + getPreviousItemPair()
    }

    private void registerPreference()
    {
        // I would have preferred to simply grab the fx:id of
        // selectedToggle in order to identify which toggle was
        // selected, but there doesn't seem to be a convenient way
        // to do so. Instead, I have to call each toggle by name and
        // ask whether it's selected.
        if (option1.isSelected())
        {
            preferences.registerPreference(
                    (int) option1.getProperties().get("itemID"),
                    (int) option2.getProperties().get("itemID"), -1);
        }
        else if (option2.isSelected())
        {
            preferences.registerPreference(
                    (int) option1.getProperties().get("itemID"),
                    (int) option2.getProperties().get("itemID"), 1);
        }
        else if (tie.isSelected())
        {
            preferences.registerPreference(
                    (int) option1.getProperties().get("itemID"),
                    (int) option2.getProperties().get("itemID"), 0);
        }
    }

    // Update the progress bar
    public void updateProgressBar()
    {

        if (currentPairCount < items.getItemPairsCount())
        {
            currentPairCount++;
            progressLabel.setText("Question " + (int)currentPairCount + " of " + items.getItemPairsCount());
            System.out.println(items.getItemPairsCount());
            progressBar.setProgress(currentPairCount/(items.getItemPairsCount()));
            System.out.println(currentPairCount);

        }
    }
}
