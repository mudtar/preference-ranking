package edu.pcc.fbj.rankingsystem.dbfactory;

import edu.pcc.fbj.rankingsystem.adminsetup.Item;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.in;
import static java.lang.System.out;

/**
 * Provides method to retrieve, delete and insert items in the
 * FueledByJava database
 *
 * @author Eric Kristiansen
 * @version 5/26/2016
 */
public class ItemDAO {

    //Queries
    private static final String GET_ITEMS_SQL = "SELECT Name, ImageBinary FROM FBJ_ITEM";
    private static final String DELETE_ITEM_SQL = "DELETE FROM FBJ_ITEM WHERE Name = ?";
    private static final String INSERT_ITEM_NAME_AND_IMAGE_SQL =
            "INSERT INTO FBJ_ITEM(Name, ImageBinary) VALUES(?, ?)";
    private static final String INSERT_ITEM_NAME_SQL = "INSERT INTO FBJ_ITEM(Name) VALUES(?)";
    private static final String UPDATE_IMAGE_SQL =
            "UPDATE FBJ_ITEM SET ImageBinary = ? WHERE Name = ?";

    //Other
    private Connection connection = null;
    private List<Item> items;
    private ByteArrayOutputStream outputStream;
    private InputStream inputStream;

    /**
     * Create an items object
     * Read from the FBJ database and populate the items list
     */
    public ItemDAO()
    {
        try
        {
            connection = RSystemConnection.getConnection();
            items = readItems();
        }
        catch (SQLException ex)
        {
            out.println(ex.toString());
            ex.printStackTrace();
        }
    }

    /**
     * Read items table.
     * If an error occurs, a stack trace is printed to standard error and an empty list is returned.
     * @return the list of items read
     */
    private List<Item> readItems()
    {
        ArrayList<Item> itemList = new ArrayList<>();

        try
        {
            PreparedStatement stmt = connection.prepareStatement(GET_ITEMS_SQL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                if (rs.getBinaryStream("ImageBinary") == null)
                {
                    itemList.add(new Item(rs.getString("Name")));
                }
                else
                {
                    itemList.add(new Item(rs.getString("Name"), ImageIO.read(rs.getBinaryStream("ImageBinary"))));
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        catch(Exception ex)
        {
            out.println(ex.toString());
            ex.printStackTrace();
        }

        return itemList;
    }

    /**
     * @return list of items read from the FBJ Item database
     */
    public List<Item> getItems()
    {
        items = readItems();
        return items;
    }

    /**
     * parse data into applicable lists for applicable methods
     * @param passItems list of items chosen by the user
     */
    public void setItems(List<Item> passItems)
    {
        try
        {
            boolean insertItem;
            boolean deleteItem;
            ArrayList<Item> deleteList = new ArrayList<>();
            ArrayList<Item> insertList = new ArrayList<>();
            ArrayList<Item> updateList = new ArrayList<>();

            //if item is in items, but not pass Items, delete
            for (Item i: items)  //exists in items
            {
                deleteItem = notFoundItem(i.toString(), passItems);
                if (deleteItem)
                {
                    deleteList.add(i);
                }
            }
            //after deletion, if item is in passItems, but not in Items, insert new item
            for (Item j: passItems) //exists in passItems
            {
                insertItem = notFoundItem(j.toString(), items);
                if (insertItem)
                {
                    insertList.add(new Item(j.toString(), j.getImage())); // new item to insert
                }
                else
                {
                    updateList.add(new Item(j.toString(), j.getImage())); // possible image update
                }
            }

            deleteRecords(deleteList);
            insertRecords(insertList);
            updateRecords(updateList);

        }
        catch (Exception ex)
        {
            out.println(ex.toString());
            ex.printStackTrace();
        }
    }

    /**
     *  Search a List of Item objects for a match to the string passed in
     * @param str
     * @param itemList
     * @return true if not found
     */
    protected Boolean notFoundItem(String str, List<Item> itemList)
    {
        for (Item i: itemList)
        {
            if (str.equals(i.toString()))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Delete appropriate records
     * @param deleteList
     */
    protected void deleteRecords(List<Item> deleteList)
    {
        try
        {
            PreparedStatement st = connection.prepareStatement(DELETE_ITEM_SQL);
            for (Item i : deleteList)
            {
                st.setString(1, i.toString());
                st.executeUpdate();
                out.println("deleting: " + i.toString());
            }
        }
        catch (SQLException e)
        {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        catch(Exception ex)
        {
            out.println(ex.toString());
            ex.printStackTrace();
        }
    }

    /**
     * Insert appropriate recordsd
     * @param insertList
     */
    protected void insertRecords(List<Item> insertList)
    {
        try
        {
            PreparedStatement st = connection.prepareStatement(INSERT_ITEM_NAME_AND_IMAGE_SQL);

            for(Item i : insertList)
            {
                //set name
                st.setString(1, i.toString());
                //set image
                if (i.getImage() != null)
                {
                    outputStream = new ByteArrayOutputStream();
                    BufferedImage bufferedImage = getBufferedImageFromImage(i.getImage());
                    ImageIO.write(bufferedImage, "png", outputStream);
                    inputStream = new ByteArrayInputStream(outputStream.toByteArray());

                    st.setBinaryStream(2, inputStream, inputStream.available());
                }
                else
                {
                    st.setNull(2, Types.BINARY);
                }

                st.executeUpdate();
                out.println("inserting: " + i.toString());
            }
        }
        catch (SQLException e)
        {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        catch(Exception ex)
        {
            out.println(ex.toString());
            ex.printStackTrace();
        }
    }

    /**
     * Insert appropriate recordsd
     * @param updateList
     */
    protected void updateRecords(List<Item> updateList)
    {
        try
        {
            PreparedStatement st = connection.prepareStatement(UPDATE_IMAGE_SQL);

            for(Item passItem : updateList)
            {
                out.println("This item to check if need update: " + passItem.toString());
                //if image has been updated, write to database
                out.println("image is not null?: " +  (passItem.getImage() != null));
                if ( hasDifferentImage(passItem))
                {

                    if (passItem.getImage() == null)
                    {
                        //set image
                        st.setNull(1, Types.BINARY);
                        st.setString(2, passItem.toString());
                        st.executeUpdate();
                        System.out.println("updating Image: " + passItem.toString());
                    }
                    else
                    {
                        outputStream = new ByteArrayOutputStream();
                        BufferedImage bufferedImage = getBufferedImageFromImage(passItem.getImage());
                        ImageIO.write(bufferedImage, "png", outputStream);
                        inputStream = new ByteArrayInputStream(outputStream.toByteArray());
                        st.setBinaryStream(1, inputStream, inputStream.available());

                        st.setString(2, passItem.toString());

                        st.executeUpdate();
                        System.out.println("updating Image: " + passItem.toString());
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        catch(Exception ex)
        {
            out.println(ex.toString());
            ex.printStackTrace();
        }
    }

    /**
     * take image object, and return a bufferedImage object
     * @passImage
     */
    private BufferedImage getBufferedImageFromImage(Image passImage)
    {
        BufferedImage bufferedImage = new BufferedImage(passImage.getWidth(null),
                passImage.getHeight(null), BufferedImage.TYPE_INT_RGB);

        bufferedImage.getGraphics().drawImage(passImage, 0, 0, null);
        return bufferedImage;
    }

    /**
     * return true if the item corresponding with passImageName in the image table
     * has a null image value
     * @param passImage
     * @return
     */
    private Boolean hasDifferentImage(Item passImage)
    {
        Boolean hasDifferentImage = false;
        try
        {
            for (Item i : items)
            {
                if (i.toString().equals(passImage.toString()))
                {
                    System.out.println("item: " + i.toString() + " : " + "passItem: " + passImage.toString());
                    if (i.getImage() == null && passImage.getImage() != null)
                    {
                        hasDifferentImage =  true;
                    }
                    else if (i.getImage() != null && passImage.getImage() == null)
                    {
                        hasDifferentImage =  true;
                    }
                    else if (i.getImage() == null && passImage.getImage() == null)
                    {
                        hasDifferentImage =  false;
                    }
                    else
                    {
                        hasDifferentImage =  compareImages(getBufferedImageFromImage(i.getImage()),
                                getBufferedImageFromImage(passImage.getImage()));
                    }
                }
            }
        }
        catch(Exception ex)
        {
            out.println(ex.toString());
        }
        return hasDifferentImage;
    }

    /**
     * Compares two images pixel by pixel.
     *
     * @param imgA the first image.
     * @param imgB the second image.
     * @return whether the images are both the same or not.
     */
    public static boolean compareImages(BufferedImage imgA, BufferedImage imgB)
    {
        Boolean result = false;

        if (imgA.getWidth() != imgB.getWidth() || imgA.getHeight() != imgB.getHeight())
        {
            return false;
        }
         else
        {
            int width = imgA.getWidth();
            int height = imgA.getHeight();

            // Loop over every pixel.
            for (int y = 0; y < height; y++)
            {
                for (int x = 0; x < width; x++) {
                    // Compare the pixels for equality.
                    if (imgA.getRGB(x, y) != imgB.getRGB(x, y))
                    {
                        result =  false;
                    }
                }
            }
        }

        return result;
    }

    public List<Item> getTestItems()
    {
        //get TestNameID

        //get Items Associated with TestNameID
        return null;
    }


}
