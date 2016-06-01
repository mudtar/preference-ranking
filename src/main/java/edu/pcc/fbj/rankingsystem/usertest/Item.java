package edu.pcc.fbj.rankingsystem.usertest;

/**
 * Each Item is an individual item to be tested.
 *
 * @author  Ian Burton
 * @version 2016.05.31.1
 */
class Item
{
    /**
     * The item's unique ID.
     */
    private int ID;

    /**
     * The name of the item.
     */
    private String name;

    /**
     * Create a new item.
     *
     * @param ID   the item's unique ID
     * @param name the name of the item
     */
    Item(int ID, String name)
    {
        this.ID = ID;
        this.name = name;
    }

    /**
     * Return the item's unique ID.
     *
     * @return the item's unique ID
     */
    int getID()
    {
        return ID;
    }

    /**
     * Return the name of the item.
     *
     * @return the name of the item
     */
    String getName()
    {
        return name;
    }
}
