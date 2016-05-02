package edu.pcc.fbj.rankingsystem.usertest;

import edu.pcc.fbj.rankingsystem.dbfactory.RSystemConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A model class to handle the basic data elements of the user test.
 *
 * @author  Ian Burton
 * @version 2016.04.29.1
 */
public class UserTestItemManager
{
    /**
     * The connection to the database used throughout the application.
     */
    private Connection connection = null;

    /**
     * A dummy list of items to present to the user. This will not be
     * part of the final program, but is used here until code is written
     * to pull the test items from the database.
     */
    /*
    private List<String> testItems = new ArrayList<>(
        Arrays.asList("a", "b", "c", "d"));
    */
    private List<String> testItems = new ArrayList<>();

    /**
     * A list of paired test items. This is populated by
     * createTestItemPairs() from testItems.
     */
    private List<List<String>> testItemPairs = null;

    /**
     * The list index of the next element of testItemPairs to return. I
     * should look into handling this functionality with an iterator
     * instead, but this works for the moment.
     */
    private int nextTestItemPairIndex = 0;

    /**
     * Default constructor.
     *
     * @throws SQLException if a database access error occurs or the url
     *                      is null
     */
    public UserTestItemManager() throws SQLException
    {
        connection = RSystemConnection.getConnection();
        System.out.println(getTestItems());
    }

    /**
     * Return the current test items. If the object doesn't yet have any
     * test items, first populate them from the database.
     *
     * @return              a List of test items
     * @throws SQLException if a database access error occurs or the url
     *                      is null
     */
    private List<String> getTestItems() throws SQLException
    {
        if (testItems.isEmpty())
        {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT DISTINCT Name " +
                "FROM FBJ_ITEM " +
                ";");
            while (rs.next())
            {
                testItems.add(rs.getString("Name"));
            }
        }

        return testItems;
    }

    /**
     * Return the next of the randomly-ordered pairs of test items. If
     * the collection of pairs hasn't yet been created, do so first.
     * 
     * @return the next of the randomly-ordered pairs of test items
     * @throws IndexOutOfBoundsException if there are no more test item
     *                                   pairs to return
     */
    public List<String> getTestItemPair() throws IndexOutOfBoundsException
    {
        if (testItemPairs == null)
        {
            createTestItemPairs();
        }

        List<String> nextTestItemPair;

        try
        {
            nextTestItemPair = testItemPairs.get(nextTestItemPairIndex);

            nextTestItemPairIndex++;
            return nextTestItemPair;
        }
        catch (IndexOutOfBoundsException e)
        {
            // There are no more test item pairs.
            throw e;
        }
    }

    /**
     * Creates and stores as a field a list of all unique pairs of test
     * items. Look into: how will this work with an odd number of
     * testItems?
     */
    private void createTestItemPairs()
    {
        if (testItemPairs == null) {
            testItemPairs = new ArrayList<>();

            // The List index of the most recent item whose unique
            // permutations have all been generated and stored. We
            // haven't handled any yet, so start at -1.
            int handledUpToIndex = -1;

            for (String testItem1 : testItems)
            {
                // The List index of the current item being paired.
                int innerLoopIndex = 0;

                for (String testItem2 : testItems)
                {
                    // Make sure that the item being paired is not one
                    // that's already been fully handled. Also make sure
                    // that we aren't making a pair of duplicates.
                    if ((innerLoopIndex > handledUpToIndex) &&
                        (testItem1 != testItem2))
                    {
                        // Add a List of two unique test items to the
                        // List of pairs. Randomize the order of the
                        // pair.
                        List<String> pair = Arrays.asList(testItem1, testItem2);
                        Collections.shuffle(pair);
                        testItemPairs.add(pair);
                    }

                    // Increment to represent the next item to be
                    // paired.
                    innerLoopIndex++;
                }

                // All possible unique permutations for the most
                // recently handled item have been generated and stored.
                // Increment this so that we don't deal with this item
                // anymore, so as not to generate duplicate pairs.
                handledUpToIndex++;
            }

            // Randomize the order of the pairs.
            Collections.shuffle(testItemPairs);
        }
    }
}
