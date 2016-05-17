package edu.pcc.fbj.rankingsystem.adminsetup;

import edu.pcc.fbj.rankingsystem.dbfactory.DAOFactory;
import edu.pcc.fbj.rankingsystem.dbfactory.ItemDao;

import java.util.List;

/**
 * This class handles all of the database operations necessary for our package
 *
 * @author Eric Kristiansen
 * @version 5/16/16
 */
public class AdminSetupModel
{
    ItemDao itemDAO = DAOFactory.getItemDAO();

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


}
