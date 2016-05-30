package edu.pcc.fbj.rankingsystem.dbfactory;

import edu.pcc.fbj.rankingsystem.adminsetup.TestName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides method to retrieve, delete and insert Tests in the
 * FueledByJava database
 *
 * @author Eric Kristiansen
 * @version 5/23/2016
 */
public class TestNameDAO
{
    //Queries
    private static final String GET_TEST_NAMES_SQL = "SELECT PK_TestNameID, Name FROM FBJ_TEST_NAME;";
    private static final String GET_TEST_ID_SQL = "SELECT PK_TestNameID FROM FBJ_TEST_NAME WHERE Name = ?;";
    private static final String DELETE_TEST_NAME_SQL = "DELETE FROM FBJ_TEST_NAME WHERE Name = ?;";
    private static final String INSERT_TEST_NAME_SQL = "INSERT INTO FBJ_TEST_NAME(Name) VALUES(?);";

    //Object to hold items
    private Connection connection = null;
    private List<TestName> testNames;

    /**
     * Create an items object
     * Read from the FBJ database and populate the items list
     */
    public TestNameDAO()
    {
        try
        {
            connection = RSystemConnection.getConnection();
            testNames = readTestNames();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
    }

    /**
     * Read Test_Name table.
     * If an error occurs, a stack trace is printed to standard error and an empty list is returned.
     * @return the list of items read
     */
    private List<TestName> readTestNames()
    {
        List<TestName> testList = new ArrayList<>();

        try
        {
            PreparedStatement stmt = connection.prepareStatement(GET_TEST_NAMES_SQL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                testList.add(new TestName(rs.getInt("PK_TestNameID"), rs.getString("Name")));
            }
        }
        catch (SQLException e)
        {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        return testList;
    }

    /**
     * @return list of tests read from the FBJ Item database
     */
    public List<TestName> getTestNames()
    {
        testNames = readTestNames();
        return testNames;
    }

     /**
     * sets a list of Tests to the database
     * @param passTestNames list of items chosen by the user
     */
    public void setTestNames(List<TestName> passTestNames)
    {
        try
        {
            boolean insertTest;
            boolean deleteTest;
            ArrayList<TestName> deleteList = new ArrayList<>();
            ArrayList<TestName> insertList = new ArrayList<>();

            //if TestName is in testNames, but not in  passTestNames, delete
            for (TestName t: testNames)  //exists in items
            {
                deleteTest = notFoundItem(t.toString(), passTestNames);
                if (deleteTest)
                {
                    deleteList.add(t);
                }
            }
            //after deletion, if item is in passItems, but not in Items, insert
            for (TestName t: passTestNames) //exists in passItems
            {
                insertTest = notFoundItem(t.toString(), testNames);
                if (insertTest)
                {
                    insertList.add(t);
                }
            }

            deleteRecords(deleteList);
            insertRecords(insertList);

        }
        catch (Exception ex)
        {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
    }

    /**
     *  Search a List of Item objects for a match to the string passed in
     * @param str
     * @param testList
     * @return true if not found
     */
    private Boolean notFoundItem(String str, List<TestName> testList)
    {
        for (TestName t: testList)
        {
            if (str.equals(t.toString()))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Delete appropriate records
     * @param deleteList
     */
    private void deleteRecords(List<TestName> deleteList)
    {
        try
        {
            PreparedStatement st = connection.prepareStatement(DELETE_TEST_NAME_SQL);
            for (TestName t : deleteList)
            {
                st.setString(1, t.toString());
                st.executeUpdate();
                System.out.println("deleting: " + t.toString());
            }
        }
        catch (SQLException e)
        {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
    }

    private void insertRecords(List<TestName> insertList)
    {
        try
        {
            PreparedStatement st = connection.prepareStatement(INSERT_TEST_NAME_SQL);

            for(TestName t : insertList)
            {
                st.setString(1, t.toString());
                st.executeUpdate();
                System.out.println("inserting: " + t.toString());
            }

        }
        catch (SQLException e)
        {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
    }


    public int getTestNameID(String passTestName)
    {
        try
        {
            PreparedStatement st = connection.prepareStatement(GET_TEST_ID_SQL);

            st.setString(1, passTestName);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                return rs.getInt("PK_TestNameID");
            }

        }
        catch (SQLException e)
        {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
        return -1;
    }

}
