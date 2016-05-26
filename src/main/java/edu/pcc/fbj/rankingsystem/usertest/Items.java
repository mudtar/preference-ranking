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

/**
 * Handles the items to be presented to the user for preference testing.
 *
 * @author  Ian Burton
 * @version 2016.05.04.1
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
}
