package edu.pcc.fbj.rankingsystem.adminsetup;

import edu.pcc.fbj.rankingsystem.dbfactory.*;

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
    public void setTestItems(List<ListTestNameItems> passTestNameItems)
    {
        //update data
        testNames = testNameDAO.getTestNames();
        itemNames = itemDAO.getItems();

        for (ListTestNameItems ltni: passTestNameItems)
        {
            for (String i: ltni.getTestnameItems())
            {
                ltni.setItem(new Item(findItemID(i), i));
                testNameItemDAO.setTestNameItems(passTestNameItems);
            }
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
                return i.getItemID();
            }
        }

        return -1;
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
    public ListTestNameItems getTestNameItemsList(String passTestName)
    {
        //get test id for TestName, and create new list
        ListTestNameItems listTestNameItems = new ListTestNameItems(passTestName,
                testNameDAO.getTestNameID(passTestName));

        //get list of item ids for test
        for (Integer i: testNameItemDAO.getTestNameItemsIDs(listTestNameItems.getTestNameID()))
        {
            for (Item item: itemNames)
            {
                if (item.getItemID() == i)
                {
                    listTestNameItems.setTestNameItem(item.toString());
                }
            }
        }
        return listTestNameItems;
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
