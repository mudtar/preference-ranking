package edu.pcc.fbj.rankingsystem.adminsetup.dao;

/**
 *  * Create DAOs for the various tables in the FiredUp database
 * So far we only have Customer, but could have others
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
}
