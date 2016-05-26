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
    private TestNameDAO testNameDAO = DAOFactory.getTestNameDAO();
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
    public void setItems(List<Item> passItems) { itemDAO.setItems(passItems); }

    /**
     * set TestNames in TestNames database object
     */
    public void setTestNames(List<TestName> passTestNames) { testNameDAO.setTestNames(passTestNames); }

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
