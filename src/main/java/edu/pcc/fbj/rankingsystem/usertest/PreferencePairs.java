package edu.pcc.fbj.rankingsystem.usertest;

import edu.pcc.fbj.rankingsystem.dbfactory.RSystemConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
 * @author  BeeYean Tan
 * @version 2016.05.31.1
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
     * A map to keep track of which items have been paired with which
     * other items. The key is the ID of the item, and the value is a
     * set of the IDs of the items that are paired with the item. This
     * is kept symmetrical, e.g. if the entry with key 1 has a value
     * whose Set contains 2, the entry with key 2 has a value whose Set
     * contains 1.
     */
    Map<Integer, Set<Integer>> itemsPaired = new HashMap<>();

    /**
     * The list index of the next element of preferencePairs to return.
     * Initialized to -1 so that the first time an item pair is
     * retrieved and this index is incremented, it begins at 0.
     */
    private int preferencePairIndex = -1;

    /**
     * The TestNameID associated with the test that is currently being
     * taken.
     */
    private int testNameID;

    /**
     * Constructs the PreferencePairs object by getting the items from
     * the database and putting them into pairs.
     *
     * @param  testNameID   the ID of the current test being taken
     * @throws SQLException if a database access error occurs
     */
    PreferencePairs(int testNameID) throws SQLException
    {
        this.testNameID = testNameID;

        // The database connection used throughout the application.
        Connection con = RSystemConnection.getConnection();

        // Get from the database all existing items.
        PreparedStatement selectItems = con.prepareStatement(
            "SELECT DISTINCT PK_ItemID, Name, ImageBinary " +
            "FROM FBJ_ITEM " +
            "JOIN FBJ_TEST_NAME_ITEM " +
            "ON FBJ_TEST_NAME_ITEM.FK_ItemID = FBJ_ITEM.PK_ItemID " +
            "WHERE FK_TestNameID = ?"
        );
        selectItems.setInt(1, testNameID);
        ResultSet rs = selectItems.executeQuery();

        // For each of the items in the database, add a new map entry.
        while (rs.next())
        {
            // Add each item to the collection of items.
            items.put(rs.getInt("PK_ItemID"), new Item(
                rs.getInt("PK_ItemID"), rs.getString("Name"),
                rs.getBinaryStream("ImageBinary")));

            // Map<Integer, Set<Integer>> itemsPaired = new HashMap<>();
            // Initialize the itemsPaired collection so that there is an
            // entry for each item. So far, there are no pairs, so the
            // sets are initialized empty.
            itemsPaired.put(rs.getInt("PK_ItemID"), new HashSet<Integer>());
        }

        createPreferencePairs();
    }

    PreferencePair getCurrentPreferencePair() throws IndexOutOfBoundsException
    {
        PreferencePair preferencePair =
            preferencePairs.get(preferencePairIndex);

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
        preferencePairIndex++;

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
    // I can get rid of option1 and option2 here, as this is already
    // stored in the current PreferencePair.
    void registerPreference(int option1, int option2, int result)
    {
        preferencePairs.get(preferencePairIndex).setPreference(result);
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

        // If the UserID associated with this user hasn't been pulled
        // from the database yet, do so.
        if (User.getID() == 0)
        {
            PreparedStatement selectUserID = con.prepareStatement(
                "SELECT PK_UserID " +
                "FROM FBJ_USER " +
                "WHERE Email = ?"
            );
            selectUserID.setString(1, User.getEmail());
            ResultSet rs = selectUserID.executeQuery();

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
                    User.setID(0);
                    throw new NoSuchElementException("More than one user " +
                        "with the email address '" + User.getEmail() + "' was " +
                        "found in the database. This is an invalid state.");
                }
                else
                {
                    User.setID(rs.getInt("PK_UserID"));
                }
            }

            if (resultCount == 0)
            {
                throw new NoSuchElementException("No valid user with the " +
                    "email address '" + User.getEmail() + "' was found in the " +
                    "database.");
            }
        }

        // Create a new FBJ_TEST for this user and get back the unique
        // test session ID.
        PreparedStatement createTest = con.prepareStatement(
            "INSERT INTO FBJ_TEST (FK_UserID, FK_TestNameID) " +
            "VALUES (?, ?)",
            Statement.RETURN_GENERATED_KEYS
        );
        createTest.setInt(1, User.getID());
        createTest.setInt(2, testNameID);
        createTest.executeUpdate();

        ResultSet generatedKeys = createTest.getGeneratedKeys();
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

        // Iterate through the the preference test results and add them
        // to the database.
        for (PreferencePair preferencePair : preferencePairs)
        {
            PreparedStatement insertResult = con.prepareStatement(
                "INSERT INTO FBJ_RESULT " +
                "    (FK_TestID, FK_Item1ID, FK_Item2ID, Value) " +
                "VALUES (?, ?, ?, ?)"
            );
            insertResult.setInt(1, testID);
            insertResult.setInt(2, preferencePair.getOption1().getID());
            insertResult.setInt(3, preferencePair.getOption2().getID());
            insertResult.setInt(4, preferencePair.getPreference());
            insertResult.executeUpdate();
        }
    }
}
