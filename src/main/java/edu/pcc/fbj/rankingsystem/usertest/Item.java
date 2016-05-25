package edu.pcc.fbj.rankingsystem.usertest;

/**
 * Each Item is an individual item to be tested.
 *
 * @author  Ian Burton
 * @version 2016.05.24.1
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
     * The number of times that this item has been paired so far with
     * other items.
     */
    private int paired;

    Item(int ID, String name)
    {
        this.ID = ID;
        this.name = name;
    }

    int getID()
    {
        return ID;
    }

    String getName()
    {
        return name;
    }
}
