package edu.pcc.fbj.rankingsystem.adminsetup;

import edu.pcc.fbj.rankingsystem.dbfactory.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class coordinates all of the database operations necessary for our package
 *
 * @author Eric Kristiansen
 * @version 5/23/16
 */
public class AdminSetupModel
{
    private ItemDAO itemDAO = DAOFactory.getItemDAO();
    private TestNameDAO testNameDAO = DAOFactory.getTestNameDAO();
    private ResultDAO resultDAO = DAOFactory.getResultDAO();
    private TestNameItemDAO testNameItemDAO = DAOFactory.getTestNameItemDAO();
    private List<TestName> testNames = testNameDAO.getTestNames();
    private List<Item> itemNames = itemDAO.getItems();

    /**
     * get Items from items database object
     */
    public List<Item> getItems()
    {
        return itemDAO.getItems();
    }

    /**
     * set items in items database object
     */
    public void setItems(List<Item> passItems) { itemDAO.setItems(passItems); }

    /**
     * set TestNames in TestNames database object
     */
    public void setTestNames(List<TestName> passTestNames) { testNameDAO.setTestNames(passTestNames); }

    /**
     *
     */
    public void setTestItems(List<TestNameItem> passTestNameItems)
    {
        //update data
        testNames = testNameDAO.getTestNames();
        itemNames = itemDAO.getItems();

        for (TestNameItem t: passTestNameItems)
        {
            t.setTestID(findTestNameID(t.getTestName()));
            /*
            for(TestName tn: testNames)
            {
                System.out.println(tn.getName() + " : " + tn.getTestNameID());
                if (tn.getName().equals(t.getTestName())){ t.setTestID(tn.getTestNameID());}
            }
            */
            t.setItemID(findItemID(t.getItemName()));
            /*
            for (Item i: itemNames)
            {
                System.out.println(i.getName() + " : " + i.getItemID());
                if (i.getName().equals(t.getItemName()))
                {
                    System.out.println("&&&&&&&&&&&&&&&&&WE HAVE A WINNER&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                    t.setItemID(i.getItemID());
                }
            }
            */

            System.out.println("**************************************");
            System.out.println("Test Name: " + t.getTestName());
            System.out.println(" TestID: " + t.getTestNameID());
            System.out.println("Test Item: " + t.getItemName());
            System.out.println(" ItemID: " + t.getItemID());

            testNameItemDAO.setTestNameItems(passTestNameItems);
        }

    }

    /**
     * take an Item namme as a param, and return an item id
     * @param passItemName
     */
    private int findItemID(String passItemName)
    {
        for (Item i: itemNames)
        {
            System.out.println(i.getName() + " : " + i.getItemID());
            if (i.getName().equals(passItemName))
            {
                System.out.println("&&&&&&&&&&&&&&&&&WE HAVE A WINNER&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                return i.getItemID();
            }
        }

        return -1;
    }


    /**
     * take an item ID as a param, and return an item name
     * @param passItemID
     */
    private String findItemName(int passItemID)
    {
        for (Item i: itemNames)
        {
            System.out.println(i.getName() + " : " + i.getItemID());
            if (i.getItemID() == passItemID)
            {
                System.out.println("&&&&&&&&&&&&&&&&&WE HAVE A WINNER&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                return i.getName();
            }
        }

        return null;
    }

    /**
     * take a TestNameID, and return a corresponding TestName
     * @param passTestNameID
     */
    private String findTestName(int passTestNameID)
    {
        for(TestName tn: testNames)
        {
            if (tn.getTestNameID() == passTestNameID){ return tn.getName();}
            System.out.println("setting test Name: " + tn.getName());
        }
        return null;
    }

    /**
     * take a testname as a param, and return the corresponding testID
     * @param passTestName
     */
    private int findTestNameID(String passTestName)
    {
        for(TestName tn: testNames)
        {
            System.out.println(tn.getName() + " : " + tn.getTestNameID());
            if (tn.getName().equals(passTestName)){ return tn.getTestNameID();}
        }
        return -1;
    }


    /**
     * get TestNameItems for selected test
     * @return List of Items associated with selected test
     */
    public List<TestNameItem> getTestNameItems()
    {
        testNames = testNameDAO.getTestNames();
        itemNames = itemDAO.getItems();

        List<TestNameItem> testNameItems = testNameItemDAO.getTestNameItems();

        System.out.println("In Model before setting names");
        for(TestNameItem tni: testNameItems)
        {
            System.out.println("in for loop");
            System.out.println("item id: " + tni.getItemID());
            System.out.println(findItemName(tni.getItemID()));
            //System.out.println(findTestName(tni.getTestNameID()));
            tni.setTestName(findTestName(tni.getTestNameID()));
            tni.setItemName(findItemName(tni.getItemID()));
        }
        return testNameItems;
    }

    /**
     * get TestNames from tests database object
     */
    public List<TestName> getTestNames()
    {
        return testNameDAO.getTestNames();
    }

    /**
     * Called from Controller to retrieve unique Item Ids from
     * DAO object
     * @return List<Integers> resultDAO.getItemsUsed();
     */
    public List<Integer> getUniqueItemsUsed()
    {
        return resultDAO.getItemsUsed();
    }

}
