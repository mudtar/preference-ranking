package edu.pcc.fbj.rankingsystem.dbfactory;

import edu.pcc.fbj.rankingsystem.adminsetup.TestNameItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides method to retrieve, delete and insert TestNameItems in the
 * FueledByJava database
 *
 * @author Eric Kristiansen
 * @version 5/23/2016
 */
public class TestNameItemDAO {

    //Queries
    private static final String GET_TEST_NAME_ITEMS_SQL = "SELECT PK_TestNameItemID, FK_TestNameID, FK_ItemID From FBJ_TEST_NAME_ITEM;";
    private static final String DELETE_TEST_NAME_ITEM = "DELETE FROM FBJ_TEST_NAME_ITEM WHERE FK_ItemID = ?;";
    private static final String INSERT_TEST_NAME_ITEM = "INSERT INTO FBJ_TEST_NAME_ITEM(FK_ItemID, FK_TestID) VALUES(?, ?);";

    //Object to hold items
    private Connection connection = null;
    private List<TestNameItem> testNameItems;

    /**
     * Create an items object
     * Read from the FBJ database and populate the items list
     */
    public TestNameItemDAO()
    {
        try
        {
            connection = RSystemConnection.getConnection();
            testNameItems = readTestNameItems();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
    }

    /**
     * Read items table.
     * If an error occurs, a stack trace is printed to standard error and an empty list is returned.
     * @return the list of items read
     */
    private List<TestNameItem> readTestNameItems()
    {
        ArrayList<TestNameItem> itemList = new ArrayList<>();

        try
        {
            PreparedStatement stmt = connection.prepareStatement(GET_TEST_NAME_ITEMS_SQL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                testNameItems.add(new TestNameItem(rs.getInt("PK_TestNameItemID"), rs.getInt("FK_TestNameID"), rs.getInt("FK_ItemID")));
            }
        }
        catch (SQLException e)
        {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        return itemList;
    }

    /**
     * @return list of TestNameItem read from the FBJ Item database
     */
    public List<TestNameItem> getTestNameItems()
    {
        testNameItems = readTestNameItems();
        return testNameItems;
    }

    /**
     * sets a list of TestNameItems to the database
     * @param passTestNameItems list of TestNameItem chosen by the user
     */
    public void setTestNameItems(List<TestNameItem> passTestNameItems)
    {
        try
        {
            boolean insertItem;
            boolean deleteItem;
            ArrayList<TestNameItem> deleteList = new ArrayList<>();
            ArrayList<TestNameItem> insertList = new ArrayList<>();

            //if item is in items, but not pass Items, delete
            for (TestNameItem testItem: testNameItems)  //exists in items
            {
                deleteItem = notFoundTestNameItem(testItem.toString(), passTestNameItems);
                if (deleteItem)
                {
                    deleteList.add(testItem);
                }
            }
            //after deletion, if item is in passItems, but not in Items, insert
            for (TestNameItem testItem: passTestNameItems) //exists in passItems
            {
                insertItem = notFoundTestNameItem(testItem.toString(), testNameItems);
                if (insertItem)
                {
                    insertList.add(testItem);
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
     *  Search a List of TestNameItem objects for a match to the string passed in
     * @param str
     * @param List<TestNameItem>
     * @return true if not found
     */
    protected Boolean notFoundTestNameItem(String str, List<TestNameItem> passTestNameItems)
    {
        for (TestNameItem testNameItem: passTestNameItems)
        {
            if (str.equals(testNameItem.toString()))
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
    protected void deleteRecords(List<TestNameItem> deleteList)
    {
        try
        {
            PreparedStatement st = connection.prepareStatement(DELETE_TEST_NAME_ITEM);
            for (TestNameItem testNameItem : deleteList)
            {
                st.setString(1, testNameItem.toString());
                st.executeUpdate();
                System.out.println("deleting: " + testNameItem.toString());
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

    /**
     * Insert appropriate recordsd
     * @param insertList
     */
    protected void insertRecords(List<TestNameItem> insertList)
    {
        try
        {
            PreparedStatement st = connection.prepareStatement(INSERT_TEST_NAME_ITEM);

            for(TestNameItem testNameItem : insertList)
            {
                st.setString(1, testNameItem.toString());
                st.executeUpdate();
                System.out.println("inserting: " + testNameItem.toString());
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


}
