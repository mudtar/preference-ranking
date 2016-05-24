package edu.pcc.fbj.rankingsystem.dbfactory;

import edu.pcc.fbj.rankingsystem.adminsetup.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides method to retrieve, delete and insert items in the
 * FueledByJava database
 *
 * @author Eric Kristiansen
 * @version 5/18/2016
 */
public class ItemDAO {

    //Queries
    private static final String GET_ITEMS_SQL = "SELECT Name FROM FBJ_ITEM";
    private static final String DELETE_ITEM = "DELETE FROM FBJ_ITEM WHERE Name = ?";
    private static final String INSERT_ITEM = "INSERT INTO FBJ_ITEM(Name) VALUES(?)";

    //Object to hold items
    private Connection connection = null;
    private List<Item> items;

    /**
     * Create an items object
     * Read from the FBJ database and populate the items list
     */
    public ItemDAO()
    {
        try
        {
            connection = RSystemConnection.getConnection();
            items = readItems();
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
    private List<Item> readItems()
    {
        ArrayList<Item> itemList = new ArrayList<>();

        try
        {
            PreparedStatement stmt = connection.prepareStatement(GET_ITEMS_SQL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                itemList.add(new Item(rs.getString("Name")));
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
     * @return list of items read from the FBJ Item database
     */
    public List<Item> getItems()
    {
        items = readItems();
        return items;
    }

    /**
     * sets a list of Items to the database
     * @param passItems list of items chosen by the user
     */
    public void setItems(List<Item> passItems)
    {
        try
        {
            boolean insertItem;
            boolean deleteItem;
            ArrayList<Item> deleteList = new ArrayList<>();
            ArrayList<Item> insertList = new ArrayList<>();

            //if item is in items, but not pass Items, delete
            for (Item i: items)  //exists in items
            {
                deleteItem = notFoundItem(i.toString(), passItems);
                if (deleteItem)
                {
                    deleteList.add(i);
                }
            }
            //after deletion, if item is in passItems, but not in Items, insert
            for (Item j: passItems) //exists in passItems
            {
                insertItem = notFoundItem(j.toString(), items);
                if (insertItem)
                {
                    insertList.add(j);
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
     * @param itemList
     * @return true if not found
     */
    protected Boolean notFoundItem(String str, List<Item> itemList)
    {
        for (Item i: itemList)
        {
            if (str.equals(i.toString()))
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
    protected void deleteRecords(List<Item> deleteList)
    {
        try
        {
            PreparedStatement st = connection.prepareStatement(DELETE_ITEM);
            for (Item i : deleteList)
            {
                st.setString(1, i.toString());
                st.executeUpdate();
                System.out.println("deleting: " + i.toString());
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
    protected void insertRecords(List<Item> insertList)
    {
        try
        {
            PreparedStatement st = connection.prepareStatement(INSERT_ITEM);

            for(Item i : insertList)
            {
                st.setString(1, i.toString());
                st.executeUpdate();
                System.out.println("inserting: " + i.toString());
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
