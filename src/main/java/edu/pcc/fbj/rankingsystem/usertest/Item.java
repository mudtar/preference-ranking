package edu.pcc.fbj.rankingsystem.usertest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

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
     * The image associated with the item.
     */
    private byte[] image;

    /**
     * Create a new item.
     *
     * @param ID    the item's unique ID
     * @param name  the name of the item
     * @param image the image associated with the item
     */
    Item(int ID, String name, InputStream image) {
        this.ID = ID;
        this.name = name;

        try
        {
            this.image = IOUtils.toByteArray(image);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (NullPointerException e)
        {
            // In this case, image is null, and we don't need to set this.image.
        }
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

    /**
     * Return the image associated with the item.
     *
     * @return the image associated with the item
     */
    InputStream getImage()
    {
        return new ByteArrayInputStream(image);
    }
}
