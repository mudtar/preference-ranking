package edu.pcc.fbj.rankingsystem.dbfactory;

import edu.pcc.fbj.rankingsystem.adminsetup.Item;
import edu.pcc.fbj.rankingsystem.adminsetup.ListTestNameItems;
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
    private static final String GET_TEST_NAME_ITEM_IDS_SQL = "SELECT FK_ItemID FROM FBJ_TEST_NAME_ITEM WHERE FK_TestNameID = ?";
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
    public void setTestNameItems(List<ListTestNameItems> passTestNameItems)
    {

        //update testNameItems from db
        testNameItems = readTestNameItems();

        boolean insertItem;
        boolean deleteItem;
        ArrayList<TestNameItem> deleteList = new ArrayList<>();
        ArrayList<TestNameItem> insertList = new ArrayList<>();

        try
        {
                //if TestNameItem is in TestNameItems, but not in  passTestNameItems, delete
                for (TestNameItem testItem : testNameItems)  //exists in items
                {
                    deleteItem = notFoundTestNameItem(testItem.getTestNameID(), testItem.getItemID(), passTestNameItems);
                    if (deleteItem) {
                        deleteList.add(testItem);
                    }
                }
                //after deletion, if item is in passItems, but not in Items, insert
                for (ListTestNameItems ltni : passTestNameItems) //exists in passItems
                {
                    for(Item item: ltni.getItems())
                    {
                        insertItem = findItem(ltni.getTestNameID(), item.getItemID());
                        if (insertItem)
                        {
                            insertList.add(new TestNameItem(ltni.getTestNameID(), item.getItemID()));
                        }
                    }
                }

            deleteRecords(deleteList);
            insertRecords(insertList);

            System.out.println("marked for delection");
            for (TestNameItem tni: deleteList)
            {
                System.out.println("delete test " + tni.getTestNameID()+ " item: " + tni.getItemID());
            }

            System.out.println("marked for insertion");
            for (TestNameItem tni: insertList)
            {
                System.out.println("insert test " + tni.getTestNameID()+ " item: " + tni.getItemID());
            }

        }
        catch (Exception ex)
        {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
    }

    /**
     * Search a List of TestNameItem objects for a match to the string passed in
     * @param passTestNameID
     * @param passItemID
     * @param passTestNameItems
     * @return true if not found
     */
    protected Boolean notFoundTestNameItem(int passTestNameID, int passItemID, List<ListTestNameItems> passTestNameItems)
    {
        Boolean delete = false;

        for (ListTestNameItems ltni: passTestNameItems)
        {
            //find correct list
            if (ltni.getTestNameID() == passTestNameID)
            {
                delete = true;
                for (Item item : ltni.getItems())
                {
                    if (item.getItemID() == passItemID)
                    {
                        delete = false; //item was found
                    }
                }
            }
        }
        return delete;
    }

    /**
     * find testNameItem
     * @param passTestID
     * @param passItemID
     * @return true if not found
     */
    private Boolean findItem(int passTestID, int passItemID)
    {
        for (TestNameItem tni: testNameItems)
        {
            if (tni.getTestNameID() == passTestID && tni.getItemID() == passItemID)
            {
                return false;
            }
        }
        return true; //not found, go insert
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
     * get Test Name Item IDs
     * @param passID
     * @return List<Integer>
     */
    public List<Integer> getTestNameItemsIDs(int passID)
    {
        ArrayList<Integer> itemIDs = new ArrayList<>();

        try
        {
            PreparedStatement st = connection.prepareStatement(GET_TEST_NAME_ITEM_IDS_SQL);
            st.setInt(1, passID);
            ResultSet rs = st.executeQuery();

            while (rs.next())
            {
                itemIDs.add(rs.getInt("FK_ItemID"));
            }
        }
        catch (SQLException e)
        {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        return itemIDs;
    }

}
