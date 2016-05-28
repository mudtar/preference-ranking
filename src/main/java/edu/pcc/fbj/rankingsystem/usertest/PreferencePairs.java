package edu.pcc.fbj.rankingsystem.usertest;

import edu.pcc.fbj.rankingsystem.dbfactory.RSystemConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.CompletionException;

/**
 * Handles the items to be presented to the user for preference testing
 * and the preferences submitted by the user.
 *
 * This is the result of the combination of the classes Items and
 * Preferences. It's become apparent that these two data structures
 * should be combined to more efficiently and cohesively underpin newly
 * required features, including the back button and new comparison
 * ordering requirements.
 *
 * @author  Ian Burton
 * @version 2016.05.24.1
 */
class PreferencePairs
{
    /**
     * The items that are to be made into pairs and presented to the
     * user. This is a list of map entries where the key is the
     * item's ID and the value is the item.
     */
    Map<Integer, Item> items = new HashMap<>();

    /**
     * All of the items made into comparison pairs to be presented to
     * the user.
     */
    private List<PreferencePair> preferencePairs;

    /**
     * The list index of the next element of preferencePairs to return.
     */
    private int nextPreferencePairIndex;

    /**
     * A map to keep track of which items have been paired with which
     * other items. The key is the ID of the item, and the value is a
     * set of the IDs of the items that are paired with the item. This
     * is kept symmetrical, e.g. if the entry with key 1 has a value
     * whose Set contains 2, the entry with key 2 has a value whose Set
     * contains 1.
     */
    Map<Integer, Set<Integer>> itemsPaired = new HashMap<>();

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
     * The list index of the next element of preferencePairs to return.
     * Initialized to -1 so that the first time an item pair is
     * retrieved and this index is incremented, it begins at 0.
     */
    private int preferencePairIndex = -1;

    /**
     * The UserID associated with the user who is currently logged in.
     */
    private int userID;

    /**
     * Constructs the PreferencePairs object by getting the items from
     * the database and putting them into pairs.
     *
     * @throws SQLException if a database access error occurs
     */
    PreferencePairs() throws SQLException
    {
        // The database connection used throughout the application.
        Connection con = RSystemConnection.getConnection();

        // Get from the database all existing items.
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "SELECT DISTINCT PK_ItemID, Name " +
            "FROM FBJ_ITEM " +
            ";");

        // For each of the items in the database, add a new map entry.
        while (rs.next())
        {
            // Add each item to the collection of items.
            items.put(rs.getInt("PK_ItemID"), new Item(rs.getInt("PK_ItemID"),
                                                       rs.getString("Name")));

            // Map<Integer, Set<Integer>> itemsPaired = new HashMap<>();
            // Initialize the itemsPaired collection so that there is an
            // entry for each item. So far, there are no pairs, so the
            // sets are initialized empty.
            itemsPaired.put(rs.getInt("PK_ItemID"), new HashSet<Integer>());
        }

        stmt.close();

        createPreferencePairs();
    }

    PreferencePair getCurrentPreferencePair() throws IndexOutOfBoundsException
    {
        PreferencePair preferencePair =
            preferencePairs.get(nextPreferencePairIndex);

        return preferencePair;
    }

    /**
     * Return the next of the randomly-ordered pairs of test items.
     * 
     * @return the next of the randomly-ordered pairs of test items
     * @throws IndexOutOfBoundsException when there are no more test
     *                                   item pairs to return
     */
    PreferencePair getNextPreferencePair() throws IndexOutOfBoundsException
    {
        // Increment the index of the pair of items so that this method
        // grabs the next one.
        nextPreferencePairIndex++;

        PreferencePair preferencePair = getCurrentPreferencePair();

        return preferencePair;
    }

    PreferencePair getPreviousPreferencePair() throws IndexOutOfBoundsException
    {
        // Decrement the index of the pair of items so that this method
        // grabs the previous one.
        preferencePairIndex--;

        PreferencePair preferencePair = getCurrentPreferencePair();

        return preferencePair;
    }

    /**
     * Return the total count of item pairs.
     *
     * @return the total count of item pairs
     */
    public int getPreferencePairsCount()
    {
        if (preferencePairs !=null)
        {
            return preferencePairs.size();
        }
        return -1;
    }

    int getPreferencePairIndex()
    {
        return preferencePairIndex;
    }

    /**
     * Creates and stores a list of all unique pairs of test items. The
     * items are paired in random order, and the order of the pairs is
     * randomized as well.
     *
     * Right now this is in line with Sprint 1 ordering. Sprint 2
     * "Ordering Comparisons" story is in progress.
     */
    private void createPreferencePairs()
    {
        preferencePairs = new ArrayList<>();

        // The list index of the item that has most recently been paired
        // with all other items. We haven't handled any yet, and indexes
        // start at 0, so start our count at -1.
        int item1Index = -1;

        for (Item item1 : items.values())
        {
            // The List index of the current item to be paired with
            // item1.
            int item2Index = 0;

            for (Item item2 : items.values())
            {
                // Make sure that the item being paired is not one
                // that's already been fully paired. Also make sure that
                // we aren't making a pair of duplicates.
                if ((item2Index > item1Index) && (item1 != item2))
                {
                    // Add a list of two unique test items to the list
                    // of pairs. Randomize the order of the pair.
                    List<Item> pair = Arrays.asList(item1, item2);
                    Collections.shuffle(pair);
                    PreferencePair preferencePair =
                        new PreferencePair(pair.get(0), pair.get(1));
                    preferencePairs.add(preferencePair);

                    // Add each of the two paired items to itemsPaired.
                    // Add option2 to option1's Set.
                    itemsPaired.get(preferencePair.getOption1().getID())
                               .add(preferencePair.getOption2().getID());
                    // Add option1 to option2's Set.
                    itemsPaired.get(preferencePair.getOption2().getID())
                               .add(preferencePair.getOption1().getID());
                }

                // Increment to represent the next item to be paired.
                item2Index++;
            }

            // All possible unique permutations for the most recently
            // handled item have been generated and stored.  Increment
            // this so that we don't deal with this item anymore, so as
            // not to generate duplicate pairs.
            item1Index++;
        }

        // Randomize the order of the pairs.
        Collections.shuffle(preferencePairs);
    }

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
