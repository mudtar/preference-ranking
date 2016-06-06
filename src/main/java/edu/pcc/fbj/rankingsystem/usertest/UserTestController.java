package edu.pcc.fbj.rankingsystem.usertest;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The controller for the preference test, which brings together the GUI
 * view with the items to be tested and the preferences selected by the
 * user.
 *
 * @author  Ian Burton
 * @author  BeeYean Tan
 * @version 2016.05.31.1
 */
public class UserTestController implements Initializable
{
    /**
     * The existing tests available to this test session.
     */
    private Tests tests;

    /**
     * The items to be presented to the user by which to determine user
     * preferences and the user's preferences, which are the results of
     * the test.
     */
    private PreferencePairs preferencePairs;

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
     * The test that the user is currently taking.
     */
    @FXML
    private ComboBox<Test> testName;

    @FXML
    private Button back;

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

    /*
     * Constructs the controller for the preference test.
     *
     * @throws SQLException if a database access error occurs
     */
    /*
    public UserTestController() throws SQLException
    {
    }
    */

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
        try
        {
            tests = new Tests();
            updateTests();
            // The first test in the list is selected by default.
            initializeSelectedTest(testName.getItems().get(0));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initializeSelectedTest(Test selectedTest) throws SQLException
    {
        Toggle selectedToggle = options.getSelectedToggle();
        if (selectedToggle != null)
        {
            selectedToggle.setSelected(false);
        }

        preferencePairs = new PreferencePairs(selectedTest.getID());
        updateOptionsNext();
        updateProgressBar();
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
        User.setEmail(userEmail);
    }

    public void handleTestNameChange() throws SQLException
    {
        initializeSelectedTest(testName.getValue());
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

            // Unselect the selected button in preparation for the
            // buttons to be updated with new items.
            selectedToggle.setSelected(false);

            try
            {
                updateOptionsNext();
            }
            catch (IndexOutOfBoundsException e)
            {
                // There are no more test item pairs to handle.
                preferencePairs.storePreferences();
                primaryStage.close();
            }

            updateProgressBar();
        }
    }

    /**
     * Update the GUI's option buttons with the next pair of items to be
     * presented to the user.
     *
     * @param  preferencePair            the pair of items with which to
     *                                   update the option buttons
     * @throws IndexOutOfBoundsException when there are no more test
     *                                   item pairs to handle
     */
    private void updateButtons(PreferencePair preferencePair)
    {
        // Enable or disable the back button based upon whether there are
        // items available to go back to.
        if (preferencePairs.getPreferencePairIndex() <= 0)
        {
            back.setDisable(true);
        }
        else
        {
            back.setDisable(false);
        }

        // Associate the items' IDs with their toggle buttons.
        option1.getProperties().put("itemID",
                                    preferencePair.getOption1().getID());
        option2.getProperties().put("itemID",
                                    preferencePair.getOption2().getID());

        try
        {
            option1.setGraphic(new ImageView(new Image(
                preferencePair.getOption1().getImage(), 200, 200, true, true)));
            option1.setText("");
        }
        catch (NullPointerException e)
        {
            // This exception is thrown when the InputStream passed to
            // the Image constructor is null. That's OK in this case; it
            // just means that there's no image associated with this
            // item. Set text instead.
            option1.setGraphic(null);
            option1.setText(preferencePair.getOption1().getName());
        }

        try
        {
            option2.setGraphic(new ImageView(new Image(
                preferencePair.getOption2().getImage(), 200, 200, true, true)));
            option2.setText("");
        }
        catch (NullPointerException e)
        {
            // This exception is thrown when the InputStream passed to
            // the Image constructor is null. That's OK in this case; it
            // just means that there's no image associated with thians
            // item. Set text instead.
            option2.setGraphic(null);
            option2.setText(preferencePair.getOption2().getName());
        }

        switch (preferencePair.getPreference())
        {
            case -1:
                option1.setSelected(true);
                break;
            case 1:
                option2.setSelected(true);
                break;
            case 0:
                tie.setSelected(true);
                break;
        }

    }

    private void updateTests()
    {
        testName.setItems(FXCollections.observableArrayList(tests.getTests()));
        // Set the current selection of the ComboBox to the first test
        // in the list.
        Test selectedTest = testName.getItems().get(0);
        testName.setValue(selectedTest);
    }

    private void updateOptionsNext() throws IndexOutOfBoundsException
    {
        // When there are no more test item pairs available, this throws
        // an IndexOutOfBoundsException.
        PreferencePair preferencePair = preferencePairs.getNextPreferencePair();

        updateButtons(preferencePair);
    }

    private void updateOptionsPrevious() throws IndexOutOfBoundsException
    {
        // When there are no more test item pairs available, this throws
        // an IndexOutOfBoundsException.
        PreferencePair preferencePair =
            preferencePairs.getPreviousPreferencePair();

        updateButtons(preferencePair);
    }

    public void handleBack() throws SQLException {
        Toggle selectedToggle = options.getSelectedToggle();
        // Only register the user's preference if they actually made a
        // selection before pressing the submit button.
        if (selectedToggle != null)
        {
            registerPreference();

            // Unselect the selected button in preparation for the
            // buttons to be updated with new items.
            selectedToggle.setSelected(false);
        }

        updateOptionsPrevious();
        updateProgressBar();
    }

    private void registerPreference()
    {
        // I would have preferred to simply grab the fx:id of
        // selectedToggle in order to identify which toggle was
        // selected, but there doesn't seem to be a convenient way to do
        // so. Instead, I have to call each toggle by name and ask
        // whether it's selected.
        if (option1.isSelected())
        {
            preferencePairs.registerPreference(
                    (int) option1.getProperties().get("itemID"),
                    (int) option2.getProperties().get("itemID"), -1);
        }
        else if (option2.isSelected())
        {
            preferencePairs.registerPreference(
                    (int) option1.getProperties().get("itemID"),
                    (int) option2.getProperties().get("itemID"), 1);
        }
        else if (tie.isSelected())
        {
            preferencePairs.registerPreference(
                    (int) option1.getProperties().get("itemID"),
                    (int) option2.getProperties().get("itemID"), 0);
        }
    }

    public void updateProgressBar()
    {
        int currentPairCount = preferencePairs.getPreferencePairIndex() + 1;

        progressLabel.setText("Question " + currentPairCount + " of " +
                              preferencePairs.getPreferencePairsCount());
        System.out.println(preferencePairs.getPreferencePairsCount());
        // This question hasn't been answered yet, so we shouldn't count
        // this current pair yet as progress.
        progressBar.setProgress((double) (currentPairCount - 1) /
            preferencePairs.getPreferencePairsCount());;
        System.out.println(currentPairCount);
    }
}
