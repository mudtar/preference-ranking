package edu.pcc.fbj.rankingsystem.adminsetup;

/**
 * @author Eric Kristiansen
 *
 * version 4/30/16
 *
 * This class was designed with future development in mind. It only holds a string variable
 * currently, but will eventually hold more
 */
public class Item {

    private String name;

    /**
     * just a constructor for the Item object
     * @param passName
     */
    public Item(String passName)
    {
        name = passName;
    }

    /**
     * Overidden Method
     * @return the string values of the object variables
     */
    @Override public String toString()
    {
        return name;
    }

    /**
     * Method to return name independent of other variables of item object
     * @return returns name
     */
    public String getName()
    {
        return name;
    }
}
