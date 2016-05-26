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
public abstract class ReportDB implements ReportDAO
{
    protected Vector<String> userEmailList;
    protected Vector<String> userTestIDList;
    protected Vector<String> userTestNameList;
    private String message;
    protected Connection connection;

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
     * Retrieve user test id list
     * @return Vector<String>
     */
    @Override
    public Vector<String> getUserTestID(String email, String testName)
    {
        userTestIDList = new Vector<>();
        setMessage(DATABASE_DATA_READING);
        try (
                PreparedStatement stmt = connection.prepareStatement(GET_USER_TEST_ID_LIST_SQL);
        )
        {
            stmt.setString(1, email);
            stmt.setString(2, testName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                userTestIDList.add(rs.getString("TestID"));
            }
            stmt.close();
            setMessage(DATABASE_DATA_COMPLETE);
            return userTestIDList;
        }
        catch (SQLException se)
        {
            return null;
        }
    }

    /**
     * Retrieve user test name list
     * @return Vector<String>
     */
    @Override
    public Vector<String> getUserTestNameList(String email)
    {
        userTestNameList = new Vector<>();
        setMessage(DATABASE_DATA_READING);
        try (
                PreparedStatement stmt = connection.prepareStatement(GET_USER_TEST_NAME_LIST_SQL);
        )
        {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                userTestNameList.add(rs.getString("Name"));
            }
            stmt.close();
            setMessage(DATABASE_DATA_COMPLETE);
            return userTestNameList;
        }
        catch (SQLException se)
        {
            return null;
        }
    }

    @Override
    public String getUserEmail(int index)
    {
        if(userEmailList != null)
        {
            return userEmailList.get(index);
        }
        return null;
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
    @Override
    public void setMessage(String msg)
    {
        message = msg;
        System.out.println(message);
    }

}
