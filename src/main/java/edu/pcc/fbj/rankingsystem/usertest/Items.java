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
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletionException;

/**
 * Handles the items to be presented to the user for preference testing
 * and the preferences submitted by the user.
 *
 * @author  Ian Burton
 * @version 2016.05.24.1
 */
class Items
{
    /**
     * All of the items made into comparison pairs to be presented to
     * the user.
     */
    private List<List<Map.Entry<Integer, String>>> itemPairs;

    /**
     * The list index of the next element of itemPairs to return.
     */
    private int nextItemPairIndex;

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
     * Constructs the Items object by getting the items from the
     * database and putting them into pairs.
     *
     * @throws SQLException if a database access error occurs
     */
    Items() throws SQLException
    {
        // The database connection used throughout the application.
        Connection con = RSystemConnection.getConnection();

        // All of the items that are to be made into pairs and presented
        // to the user.This is a list of map entries where the key is
        // the item's ID and the value is the item's name.
        List<Map.Entry<Integer, String>> items = new ArrayList<>();

        // Get from the database all existing items.
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "SELECT DISTINCT PK_ItemID, Name " +
            "FROM FBJ_ITEM " +
            ";");

        // For each of the items in the database, add a new map entry.
        while (rs.next())
        {
            items.add(new AbstractMap.SimpleEntry<Integer, String>(
                rs.getInt("PK_ItemID"), rs.getString("Name")));
        }

        stmt.close();

        createItemPairs(items);
    }

    /**
     * Return the next of the randomly-ordered pairs of test items.
     * 
     * @return the next of the randomly-ordered pairs of test items
     * @throws IndexOutOfBoundsException when there are no more test
     *                                   item pairs to return
     */
    List<Map.Entry<Integer, String>> getItemPair()
        throws IndexOutOfBoundsException
    {
        // Get the first pair of items that hasn't yet been returned,
        // i.e. the next pair of items.
        List<Map.Entry<Integer, String>> itemPair =
            itemPairs.get(nextItemPairIndex);

        // Increment the index of the next pair of items so that this
        // method grabs the next one next time it's called.
        nextItemPairIndex++;

        return itemPair;
    }

    /**
     * Return the total count of item pairs.
     *
     * @return the total count of item pairs
     */
    public int getItemPairsCount()
    {
        if (itemPairs !=null)
        {
            return itemPairs.size();
        }
        return -1;
    }

    /**
     * Creates and stores a list of all unique pairs of test items. The
     * items are paired in random order, and the order of the pairs is
     * randomized as well.
     *
     * @param items all of the items that are to be made into pairs.
     *              This is a list of map entries where the key is the
     *              item's ID and the value is the item's name.
     */
    private void createItemPairs(List<Map.Entry<Integer, String>> items)
    {
        itemPairs = new ArrayList<>();

        // The list index of the item that has most recently been paired
        // with all other items. We haven't handled any yet, and indexes
        // start at 0, so start our count at -1.
        int item1Index = -1;

        for (Map.Entry<Integer, String> item1 : items)
        {
            // The List index of the current item to be paired with
            // item1.
            int item2Index = 0;

            for (Map.Entry<Integer, String> item2 : items)
            {
                // Make sure that the item being paired is not one
                // that's already been fully paired. Also make sure that
                // we aren't making a pair of duplicates.
                if ((item2Index > item1Index) && (item1 != item2))
                {
                    // Add a list of two unique test items to the list
                    // of pairs. Randomize the order of the pair.
                    List<Map.Entry<Integer, String>> pair =
                        Arrays.asList(item1, item2);
                    Collections.shuffle(pair);
                    itemPairs.add(pair);
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
        Collections.shuffle(itemPairs);
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
        List<Integer> comparedItems = Arrays.asList(option1, option2);

        preferences.add(new AbstractMap.SimpleEntry<List<Integer>, Integer>(
            comparedItems, result));
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
            int outcome = preference.getValue();

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
}
