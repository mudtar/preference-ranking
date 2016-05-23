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
    public static TestNameDAO getTestDAO()
    {
        TestNameDAO testNameDAO = new TestNameDAO();
        return testNameDAO;
    }

    /**
     * get result database access object
     * @return resultDAO
     */
    public static ResultDAO getResultDAO()
    {
        ResultDAO resultDAO = new ResultDAO();
        return resultDAO;
    }

    /**
     * get TestNameItem database access object
     * @return testNameItemDAO
     */
    public static TestNameItemDAO getTestNameItemDAO()
    {
        TestNameItemDAO testNameItemDAO = new TestNameItemDAO();
        return testNameItemDAO;
    }
}
