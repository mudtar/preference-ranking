package edu.pcc.fbj.rankingsystem.resultreporting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;

import edu.pcc.fbj.rankingsystem.dbfactory.RSystemConnection;
import edu.pcc.fbj.rankingsystem.resultreporting.dao.*;

/**
 * This is data accss object used for communicate with database to retrieve data
 * @author David Li
 * @version 2016.04.14
 *
 * @author David Li
 * @version 2016.04.21
 * 1. Only display users who have already completed a test;
 * 2. Display result in order of total values;
 *
 * @author David Li
 * @version 2016.04.28
 * 1. Refactor code;
 * 2. Use a separate thread to process database transaction.
 */
public class ReportDB implements ReportDAO
{

    private static final String GET_USER_EMAIL_LIST_SQL
                                     = "SELECT FBJ_USER.Email FROM FBJ_USER " +
                                       " JOIN FBJ_TEST ON FBJ_USER.PK_UserID = FBJ_TEST.FK_UserID";

    private static final String GET_USER_TEST_RESULT_SQL
            = "SELECT FBJ_USER.Email " +
               " ,FBJ_ITEM.Name " +
               " ,ISNULL(SUM(CASE WHEN FBJ_RESULT.Value = 1 THEN 1 END), 0) AS Wins " +
               " ,ISNULL(SUM(CASE WHEN FBJ_RESULT.Value = -1 THEN 1 END), 0) AS Losses " +
               " ,ISNULL(SUM(CASE WHEN FBJ_RESULT.Value = 0 THEN 1 END), 0) AS Ties " +
               " ,SUM(FBJ_RESULT.Value) AS Points " +
               " FROM FBJ_USER " +
               " JOIN FBJ_TEST ON FBJ_USER.PK_UserID = FBJ_TEST.FK_UserID " +
               " JOIN FBJ_RESULT ON FBJ_TEST.PK_TestID = FBJ_RESULT.FK_TestID " +
               " JOIN FBJ_ITEM ON FBJ_ITEM.PK_ItemID = FBJ_RESULT.FK_Item1ID " +
               " WHERE FBJ_USER.Email = ? " +
               " GROUP BY FBJ_USER.Email, FBJ_ITEM.Name " +
               " ORDER BY SUM(FBJ_RESULT.Value) DESC";

    private final String DATABASE_MESSAGE_INIT = "Database initail ...";
    private final String DATABASE_CONNECTION_CONNECTING = "Connecting to database...";
    private final String DATABASE_CONNECTION_FAILED = "Failed to connect to database!";
    private final String DATABASE_CONNECTION_SUCCESS = "Connect to database successfully!";
    private final String DATABASE_DATA_READING = "Reading data from database.....";
    private final String DATABASE_DATA_COMPLETE = "Reading data complete!";
    private final String DATABASE_DATA_SELECTION = "Please select email address to display test result:";


    private Vector<String> userEmailList;
    private String message;
    private Connection connection;

    public ReportDB()
    {
        message = DATABASE_MESSAGE_INIT;
        System.out.println("Create ReportDB.");
    }

    /**
     * eslablish database connection
     */
    @Override
    public Connection DBConnection()
    {
        setMessage(DATABASE_CONNECTION_CONNECTING);
        try
        {
            connection = RSystemConnection.getConnection();
            setMessage(DATABASE_CONNECTION_SUCCESS);
        }
        catch(SQLException se)
        {
            setMessage(DATABASE_CONNECTION_FAILED);
            return null;
        }

        return connection;
    }

    /**
     * Retrieve user test result according to user's email
     * @param   email String
     * @return List<ReportTestResult>
     */
     public List<ReportTestResult> getUserTestResult(String email)
     {
        List<ReportTestResult> results = new ArrayList<>();

         setMessage(DATABASE_DATA_READING);
        try (
                PreparedStatement stmt = connection.prepareStatement(GET_USER_TEST_RESULT_SQL)
        )
        {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(new ReportTestResult(rs.getString("Name"),
                        rs.getInt("Wins"),
                        rs.getInt("Losses"),
                        rs.getInt("Ties"),
                        rs.getInt("Points")));
            }
            setMessage(DATABASE_DATA_COMPLETE);
            stmt.close();
            return results;
        }
        catch (SQLException se)
        {
            return null;
        }
    }

    /**
     * Retrieve user email list
     * @return Vector<String>
     */
    @Override
    public Vector<String> getUserEmailList()
    {
        userEmailList = new Vector<>();
        setMessage(DATABASE_DATA_READING);
        try (
                PreparedStatement stmt = connection.prepareStatement(GET_USER_EMAIL_LIST_SQL);
                ResultSet rs = stmt.executeQuery()
        )
        {
            while (rs.next())
            {
                userEmailList.add(rs.getString("Email"));
            }
            stmt.close();
            setMessage(DATABASE_DATA_COMPLETE);
            return userEmailList;
        }
        catch (SQLException se)
        {
            return null;
        }
    }

    /**
     * Format report data to be fill in JTable
     * @return HashMap <String, Object[][]>
     */
    @Override
    public HashMap<String, Object[][]> getHashTable()
    {
        HashMap<String, Object[][]> usersToResults = new HashMap<>();
        List<ReportTestResult> userTestResults;
        if(userEmailList != null)
        {
            for (String email : userEmailList)
            {
                System.out.println(email);
                userTestResults = getUserTestResult(email);
                if(userTestResults != null)
                {
                    Object[][] userResults = new Object[userTestResults.size()][ReportTestResult.COLUMN_NUMBER];
                    for (int i = 0; i < userTestResults.size(); i++)
                    {
                        userResults[i][0] = userTestResults.get(i).getItem1Name();
                        userResults[i][1] = userTestResults.get(i).getWins();
                        userResults[i][2] = userTestResults.get(i).getLosses();
                        userResults[i][3] = userTestResults.get(i).getTies();
                        userResults[i][4] = userTestResults.get(i).getScores();
                    }
                    usersToResults.put(email, userResults);
                }
            }
        }
        setMessage(DATABASE_DATA_SELECTION);
        return usersToResults;
    }

    /**
     * Return message such as database status, warning, or exception;
     * @return String message
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * Identify if current database processing is completed.
     * @return boolean
     */
    @Override
    public boolean getStatus(){
        return message.equals(DATABASE_DATA_SELECTION);
    }

    /**
     * Set message
     * @param msg String
     */
    private void setMessage(String msg)
    {
        message = msg;
        System.out.println(message);
    }
}
