package edu.pcc.fbj.rankingsystem.dbfactory;

/**
 * get database objects per table
 * @author Eric Kristiansen
 * @version 5/17/16
 */
public class DAOFactory {

    /**
     * get item database access object
     * @return itemDAO
     */
    public static ItemDAO getItemDAO()
    {
        ItemDAO itemDAO = new ItemDAO();
        return itemDAO;
    }

    /**
     * get test database access object
     * @return testDAO
     */
    public static TestDAO getTestDAO()
    {
        TestDAO testDAO = new TestDAO();
        return testDAO;
    }

}
