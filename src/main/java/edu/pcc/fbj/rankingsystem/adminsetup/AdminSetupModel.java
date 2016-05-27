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
    private List<TestName> testNames = new ArrayList<>();
    private List<Item> itemNames = new ArrayList<>();

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
        //get testData
        testNames = testNameDAO.getTestNames();
        itemNames = itemDAO.getItems();

        for (TestNameItem t: passTestNameItems)
        {

            for(TestName tn: testNames)
            {
                System.out.println(tn.getName() + " : " + tn.getTestNameID());
                if (tn.getName().equals(t.getTestName())){ t.setTestID(tn.getTestNameID());}
            }
            for (Item i: itemNames)
            {
                System.out.println(i.getName() + " : " + i.getItemID());
                if (i.getName().equals(t.getItemName()))
                {
                    System.out.println("&&&&&&&&&&&&&&&&&WE HAVE A WINNER&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                    t.setItemID(i.getItemID());
                }
            }

            System.out.println("**************************************");
            System.out.println("Test Name: " + t.getTestName());
            System.out.println(" TestID: " + t.getTestNameID());
            System.out.println("Test Item: " + t.getItemName());
            System.out.println(" ItemID: " + t.getItemID());

            testNameItemDAO.setTestNameItems(passTestNameItems);
        }

    }


    /**
     * get TestNameItems for selected test
     * @return List of Items associated with selected test
     */
    public List<TestNameItem> getTestNameItems() { return testNameItemDAO.getTestNameItems(); }

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
