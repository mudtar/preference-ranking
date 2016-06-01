package edu.pcc.fbj.rankingsystem.adminsetup;

/**
 * Class to represent
 * @author Eric Kristiansen
 * @version 5/31/2016.
 */
public class TestNameItem
{
    private int testNameItemID;
    private int testNameID;
    private int itemID;

    /**
     * Constructor
     * @param passTestNameID
     * @param passItemID
     */
    public TestNameItem(int passTestNameID, int passItemID)
    {
        testNameID = passTestNameID;
        itemID = passItemID;
    }

    /**
     * Constructor
     * @param passTestNameItemID
     * @param passTestNameID
     * @param passItemID
     */
    public TestNameItem(int passTestNameItemID,int passTestNameID, int passItemID)
    {
        testNameItemID = passTestNameItemID;
        testNameID = passTestNameID;
        itemID = passItemID;
    }

    /**
     * get the TestNameID
     * @return testNameID
     */
    public int getTestNameID()
    { return testNameID;}

    /**
     * Get the ItemID
     * @return itemID
     */
    public int getItemID()
    { return itemID;}
}
