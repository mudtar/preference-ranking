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
    private static final String DELETE_TEST_NAME_ITEM = "DELETE FROM FBJ_TEST_NAME_ITEM WHERE FK_TestNameID = ? AND FK_ItemID = ?;";
    private static final String INSERT_TEST_NAME_ITEM = "INSERT INTO FBJ_TEST_NAME_ITEM(FK_TestNameID, FK_ItemID) VALUES(?, ?);";

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
        ArrayList<TestNameItem> testNameItemList = new ArrayList<>();

        try
        {
            PreparedStatement stmt = connection.prepareStatement(GET_TEST_NAME_ITEMS_SQL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                testNameItemList.add(new TestNameItem(rs.getInt("PK_TestNameItemID"), rs.getInt("FK_TestNameID"), rs.getInt("FK_ItemID")));
            }
        }
        catch (SQLException e)
        {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        return testNameItemList;
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
            //update testNameItems from db
            testNameItems = readTestNameItems();

            boolean insertItem;
            boolean deleteItem;
            ArrayList<TestNameItem> deleteList = new ArrayList<>();
            ArrayList<TestNameItem> insertList = new ArrayList<>();

            //if TestNameItem is in TestNameItems, but not in  passTestNameItems, delete
            for (TestNameItem testItem: testNameItems)  //exists in items
            {
                deleteItem = notFoundTestNameItem(testItem.getTestNameID(), testItem.getItemID(), passTestNameItems);
                if (deleteItem)
                {
                    deleteList.add(testItem);
                }
            }
            //after deletion, if item is in passItems, but not in Items, insert
            for (TestNameItem testItem: passTestNameItems) //exists in passItems
            {
                insertItem = notFoundTestNameItem(testItem.getTestNameID(), testItem.getItemID(), testNameItems);
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
     * @param
     * @param
     * @return true if not found
     */
    protected Boolean notFoundTestNameItem(int passTestNameID, int passItemID, List<TestNameItem> passTestNameItems)
    {
        for (TestNameItem testNameItem: passTestNameItems)
        {
            if (testNameItem.getTestNameID() == passTestNameID && testNameItem.getItemID() == passItemID)
            {
                System.out.println("will not delete " + testNameItem.getItemID());
                return false;
            }
        }
        System.out.println("not found, will delete " + passTestNameID);
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
                st.setInt(1, testNameItem.getTestNameID());
                st.setInt(2, testNameItem.getItemID());
                st.executeUpdate();
                System.out.println("deleting item: " + testNameItem.getItemName() + " , Test: " +
                        testNameItem.getTestName());
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
                st.setInt(1, testNameItem.getTestNameID());
                st.setInt(2, testNameItem.getItemID());
                st.executeUpdate();
                System.out.println("inserting item: " + testNameItem.getTestName() + ", test: " +
                        testNameItem.getTestName());
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
