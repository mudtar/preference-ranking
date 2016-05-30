package edu.pcc.fbj.rankingsystem.adminsetup;

import java.awt.*;

/**
 * @author Eric Kristiansen
 *
 * version 5/17/16
 * This class will represent data from the Item table in FBJ
 */
public class Item
{
    private int itemID;
    private String name;
    private Image image;

    /**
     * constructor for the Item object
     * @param passName
     */
    public Item(String passName)
    {
        name = passName;
    }

    /**
     * constructor for the Item object
     * @param passName
     * @param passImage
     */
    public Item(String passName, Image passImage)
    {
        name = passName;
        image = passImage;
    }

    /**
     * constructor for the Item object
     * @param passName
     */
    public Item(int passItemID, String passName)
    {
        itemID = passItemID;
        name = passName;
    }

    /**
     * constructor for the Item object
     * @param passName
     * @param passImage
     */
    public Item(int passItemID, String passName, Image passImage)
    {
        itemID = passItemID;
        name = passName;
        image = passImage;
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

    /**
     * Method to return icon independent of other variables of item object
     * @return icon
     */
    public Image getImage()
    {
        return image;
    }

    /**
     * Method to set icon for item
     * @param passImage
     */
    public void setImage(Image passImage)
    {
        image = passImage;
    }

    /**
     * Method to return icon independent of other variables of item object
     * @return icon
     */
    public int getItemID()
    {
        return itemID;
    }

}
