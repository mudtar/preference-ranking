package edu.pcc.fbj.rankingsystem.adminsetup;

import javax.swing.*;

/**
 * @author Eric Kristiansen
 *
 * version 5/17/16
 *
 * This class was designed with future development in mind. It only holds a string variable
 * currently, but will eventually hold more
 */
public class Item
{
    private String name;
    //private Icon icon;

    /**
     * constructor for the Item object
     * @param passName
     */
    public Item(String passName /*, Icon passIcon */)
    {
        name = passName;
        //icon = passIcon;
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
    public String getName() { return name; }

    /*
    /**
     * Method to return icon independent of other variables of item object
     * @return icon
     * /
    public Icon getIcon()
    {
        return icon;
    }
    */
}
