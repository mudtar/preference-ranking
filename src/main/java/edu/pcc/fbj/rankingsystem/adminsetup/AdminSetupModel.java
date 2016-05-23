package edu.pcc.fbj.rankingsystem.adminsetup;

import edu.pcc.fbj.rankingsystem.dbfactory.DAOFactory;
import edu.pcc.fbj.rankingsystem.dbfactory.ItemDAO;
import edu.pcc.fbj.rankingsystem.dbfactory.ResultDAO;
import edu.pcc.fbj.rankingsystem.dbfactory.TestNameDAO;

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
    TestNameDAO testNameDAO = DAOFactory.getTestDAO();
    private ResultDAO resultDAO = DAOFactory.getResultDAO();

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
    public void setItems(List<Item> passItems)
    {
        itemDAO.setItems(passItems);
    }

    /**
     * get TestNames from tests database object
     */
    public List<TestName> getTests()
    {
        return testNameDAO.getTestNames();
    }

    /**
     * set testNames in tests database object
     **/
    public void setTests(List<TestName> passTests)
    {
        testNameDAO.setTestNames(passTests);
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
