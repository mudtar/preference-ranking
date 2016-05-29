package edu.pcc.fbj.rankingsystem.adminsetup;

/**
 * Class to represent
 * @author Eric Kristiansen
 * @version 5/23/2016.
 */
public class TestNameItem
{
    private int testNameItemID;
    private int testNameID;
    private int itemID;

    public TestNameItem(int passTestNameID, int passItemID)
    {
        testNameID = passTestNameID;
        itemID = passItemID;
    }

    public TestNameItem(int passTestNameItemID,int passTestNameID, int passItemID)
    {
        testNameItemID = passTestNameItemID;
        testNameID = passTestNameID;
        itemID = passItemID;
    }

    public int getTestNameID()
    { return testNameID;}

    public int getItemID()
    { return itemID;}

    public void setTestID(int passTestNameID)
    {
        testNameID = passTestNameID;
    }
    public void setItemID(int passItemID)
    {
        itemID = passItemID;
    }


}
