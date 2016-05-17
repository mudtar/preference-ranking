package edu.pcc.fbj.rankingsystem.dbfactory;

/**
 * get database objects per table
 * @author Eric Kristiansen
 * @version 5/16/16
 */
public class DAOFactory {

    /**
     * get item database access object
     * @return itemDAO
     */
    public static ItemDao getItemDAO()
    {
        ItemDao itemDAO = new ItemDao();
        return itemDAO;
    }
}
