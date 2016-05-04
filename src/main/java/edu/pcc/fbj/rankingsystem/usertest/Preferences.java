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

/**
 * A model class to handle user test results.
 *
 * @author  Ian Burton
 * @version 2016.04.29.1
 */
class Preferences
{
    /**
     * The connection to the database used throughout the application.
     */
    private Connection connection = null;

    /**
     * The email address of the currently logged in user.
     */
    private String userEmail;

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
    Preferences() throws SQLException
    {
        connection = RSystemConnection.getConnection();
    }

    void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }

    /**
     * Store a test result locally in preparation to store it to the
     * database.
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
    void registerTestResult(int option1, int option2, int result)
    {
        List<Integer> comparedItems = Arrays.asList(option1, option2);

        results.add(new AbstractMap.SimpleEntry<List<Integer>, Integer>(
            comparedItems, result));

        // This output is for development/debugging purposes only.
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

    /**
     * Store to the database the results that have been collected. This
     * usually takes place once the user has responded to all test
     * options.
     */
    void storeResults() throws SQLException
    {
        System.out.println(results);

        Statement stmt;

        // Get the UserID for this user.
        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(
            "SELECT PK_UserID " +
            "FROM FBJ_USER " +
            "WHERE Email = '" + userEmail + "'" +
            ";");
        // There should only be one result
        rs.next();
        int userID = rs.getInt("PK_UserID");

        // Create a FBJ_TEST for this user and get back the TestID.
        stmt = connection.createStatement();
        stmt.executeUpdate(
            "INSERT INTO FBJ_TEST (FK_UserID)" +
            "VALUES (" + userID + ")" +
            ";",
            Statement.RETURN_GENERATED_KEYS);
        ResultSet generatedKeys = stmt.getGeneratedKeys();
        // There should only be one result
        generatedKeys.next();
        int testID = generatedKeys.getInt(1);

        // Iterate through the results and add them to the database.
        for (Map.Entry<List<Integer>, Integer> result : results)
        {
            int option1 = result.getKey().get(0);
            int option2 = result.getKey().get(1);
            int outcome = result.getValue();

            stmt = connection.createStatement();
            stmt.executeUpdate(
                "INSERT INTO FBJ_RESULT " +
                "    (FK_TestID, FK_Item1ID, FK_Item2ID, Value) " +
                "VALUES (" + testID + ", " + option1 + ", " + option2 + ", " +
                             outcome + ")" +
                ";");
        }
    }
}
