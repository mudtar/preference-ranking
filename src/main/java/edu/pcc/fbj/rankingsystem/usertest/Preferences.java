package edu.pcc.fbj.rankingsystem.usertest;

import edu.pcc.fbj.rankingsystem.dbfactory.RSystemConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletionException;

/**
 * Handles the preferences submitted by the user.
 *
 * @author  Ian Burton
 * @version 2016.05.04.1
 */
class Preferences
{
    /**
     * The data structure used to store the user's preference test
     * results until they are stored to the database. It is a list of
     * map entries where each map entry is made up of:
     * Key:   a list of the two item IDs that were compared to one
     *        another in the preference test
     * Value: the encoded result of the preference test between the two
     *        items, where:
     *            -1 is a win for option1 and a loss for option2
     *             0 is a tie
     *             1 is a loss for option1 and a win for option2
     */
    private List<Map.Entry<List<Integer>, Integer>> preferences =
        new ArrayList<>();

    private int outcome;
    private int preferenceResult;
    /**
     * The email address associated with the user who is currently
     * logged in.
     */
    private String userEmail;

    /**
     * The UserID associated with the user who is currently logged in.
     */
    private int userID;

    /**
     * Set the email address associated with the user who is currently
     * logged in so that the preferences can be associated in the
     * database with the proper user.
     *
     * @param userEmail the email address associated with the user who
     *                  is currently logged in
     */
    void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }

    /**
     * Register a user's preference test result with the local data
     * structure.
     *
     * @param option1 the first of the two options presented to the user
     * @param option2 the second of the two options presented to the
     *                user
     * @param result  the encoded result of the preference test between
     *                the two options, where:
     *                    -1 is a win for option1 and a loss for option2
     *                     0 is a tie
     *                     1 is a loss for option1 and a win for option2
     */
    void registerPreference(int option1, int option2, int result)
    {
        preferenceResult = result;
        List<Integer> comparedItems = Arrays.asList(option1, option2);

        preferences.add(new AbstractMap.SimpleEntry<List<Integer>, Integer>(
            comparedItems, result));
        result = result;
    }

    /**
     * Store to the database all of the the user's preference test
     * results that have been collected. This usually takes place once
     * the user has submitted a preference for all test item pairs.
     *
     * @throws CompletionException    if a unique test session ID can
     *                                not be generated
     * @throws NoSuchElementException if no valid user with this user's
     *                                email address is found in the
     *                                database
     * @throws SQLException           if a database access error occurs
     */
    void storePreferences()
        throws CompletionException, NoSuchElementException, SQLException
    {
        // The database connection used throughout the application.
        Connection con = RSystemConnection.getConnection();

        // This statement is reused for separate queries.
        Statement stmt;

        // If the UserID associated with this user hasn't been pulled
        // from the database yet, do so.
        if (userID == 0)
        {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT PK_UserID " +
                "FROM FBJ_USER " +
                "WHERE Email = '" + userEmail + "' " +
                ";");

            int resultCount = 0;
            while (rs.next())
            {
                resultCount++;
                if (resultCount > 1)
                {
                    // There should be only one result. If more than one
                    // result is encountered, this email address is
                    // associated with multiple users in the database,
                    // and we can't know which one to trust.
                    userID = 0;
                    throw new NoSuchElementException("More than one user " +
                        "with the email address '" + userEmail + "' was " +
                        "found in the database. This is an invalid state.");
                }
                else
                {
                    userID = rs.getInt("PK_UserID");
                }
            }

            if (resultCount == 0)
            {
                throw new NoSuchElementException("No valid user with the " +
                    "email address '" + userEmail + "' was found in the " +
                    "database.");
            }

            stmt.close();
        }

        // Create a new FBJ_TEST for this user and get back the unique
        // test session ID.
        stmt = con.createStatement();
        stmt.executeUpdate(
            "INSERT INTO FBJ_TEST (FK_UserID) " +
            "VALUES (" + userID + ") " +
            ";",
            Statement.RETURN_GENERATED_KEYS);

        ResultSet generatedKeys = stmt.getGeneratedKeys();
        int testID;
        if (generatedKeys.next())
        {
            testID = generatedKeys.getInt(1);
        }
        else
        {
            // Upon INSERT, there should have been a primary key
            // returned.
            throw new CompletionException("Unable to generate a unique test " +
                "session ID.", null);
        }

        stmt.close();

        // Iterate through the the preference test results and add them
        // to the database.
        for (Map.Entry<List<Integer>, Integer> preference : preferences)
        {
            int option1 = preference.getKey().get(0);
            int option2 = preference.getKey().get(1);
            outcome = preference.getValue();

            stmt = con.createStatement();
            stmt.executeUpdate(
                "INSERT INTO FBJ_RESULT " +
                "    (FK_TestID, FK_Item1ID, FK_Item2ID, Value) " +
                "VALUES (" + testID + ", " +
                             option1 + ", " +
                             option2 + ", " +
                             outcome + ") " +
                ";");
            stmt.close();
        }
    }

    List<Map.Entry<List<Integer>, Integer>> getPreferences()
    {
        return preferences;
    }

    int getOutcome()
    {
        return outcome;
    }

    int getPreferenceResult()
    {
        return preferenceResult;
    }
}
