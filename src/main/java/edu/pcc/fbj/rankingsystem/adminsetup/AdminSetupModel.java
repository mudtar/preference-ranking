package edu.pcc.fbj.rankingsystem.adminsetup;

import edu.pcc.fbj.rankingsystem.dbfactory.DAOFactory;
import edu.pcc.fbj.rankingsystem.dbfactory.ItemDAO;
import edu.pcc.fbj.rankingsystem.dbfactory.TestDAO;

import java.util.List;

/**
 * This class coordinates all of the database operations necessary for our package
 *
 * @author Eric Kristiansen
 * @version 5/17/16
 */
public class AdminSetupModel
{
    ItemDAO itemDAO = DAOFactory.getItemDAO();
    //TestDAO testDAO = DAOFactory.getTestDAO();

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


    /*
    /**
     * get Tests from tests database object
     * /
    public List<Test> getTests()
    {
        return testDAO.getTests();
    }

    /**
     * set tests in tests database object
     * /
    public void setTests(List<Test> passTests)
    {
        testDAO.setTests(passTests);
    }

    */

}
