package edu.pcc.fbj.rankingsystem.usertest;

import edu.pcc.fbj.rankingsystem.dbfactory.RSystemConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * A model class to handle user test results.
 *
 * @author  Ian Burton
 * @version 2016.04.29.1
 */
public class UserTestResultManager
{
    /**
     * The connection to the database used throughout the application.
     */
    private Connection connection = null;

    /**
     * Uniquely identifies this group of test results.
     */
    private String uniqueTestId;

    /**
     * The data structure used to store the test results until they are
     * committed to the database.
     * A list of map entries where each map entry is made up of:
     * Key:   a list of the two item IDs that were compared
     * Value: the encoded result of the test between the two items,
     *        where -1 is a win for option1,
     *               0 is a tie, and
     *               1 is a win for option2.
     */
    private List<Map.Entry<List<Integer>, Integer>> results = new ArrayList<>();

    /**
     * Default constructor.
     *
     * @throws SQLException if a database access error occurs or the url
     *                      is null
     */
    public UserTestResultManager() throws SQLException
    {
        connection = RSystemConnection.getConnection();
        generateUniqueTestId();
    }

    /**
     * Generate a new uniqueTestId. In a future implementation, make
     * sure the uniqueTestId doesn't collide in the database with one
     * that has already been used.
     */
    private void generateUniqueTestId()
    {
        uniqueTestId = UUID.randomUUID().toString();
        System.out.println("uniqueTestId: " + uniqueTestId);
    }

    /**
     * Store a test result in preparation of storing it to the database.
     * Right now, the test option parameters are passed as Strings, but
     * in the future they should be database PK item IDs.
     *
     * A next step is to add a collection field to this object that can
     * store the test results until they can be stored in the database.
     *
     * @param option1 the first of the two options presented to the user
     * @param option2 the second of the two options presented to the
     *                user
     * @param result  the result of the test between the two options.
     *                -1 represents that option1 won and option2 lost.
     *                 0 represents that the user couldn't decide
     *                   between the options.
     *                 1 represents that option1 lost and option2 won.
     */
    public void registerTestResult(int option1, int option2, int result)
    {
        /*
        //                     comp. items  , result
        private List<Map.Entry<List<Integer>, Integer>> result = new ArrayList<>();
        */

        List<Integer> comparedItems = Arrays.asList(option1, option2);

        results.add(new AbstractMap.SimpleEntry<List<Integer>, Integer>(
            comparedItems, result));

        switch (result)
        {
            case -1:
                System.out.println("Winner: " + option1);
                System.out.println(" Loser: " + option2);
                break;
            case 0:
                System.out.println(option1 + " and " + option2 + " tied.");
                break;
            case 1:
                System.out.println("Winner: " + option2);
                System.out.println(" Loser: " + option1);
                break;
        }
    }
}
