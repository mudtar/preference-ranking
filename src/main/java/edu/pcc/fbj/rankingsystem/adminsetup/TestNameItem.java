package edu.pcc.fbj.rankingsystem.adminsetup;

/**
 * Class to represent
 * @author Eric Kristiansen
 * @version 5/23/2016.
 */
public class TestNameItem
{
    //SELECT PK_TestNameItemID, FK_TestNameID, FK_ItemID From FBJ_TEST_NAME_ITEM
    private int testNameItemID;
    private int testNameID;
    private int itemID;
    private String testName;
    private String itemName;

    public TestNameItem(int passTestNameItemID, int passTestName, int passItemID)
    {
        testNameItemID = passTestNameItemID;
        testNameID = passTestName;
        itemID = passItemID;
    }
    public TestNameItem(String passTestName, String passItemName)
    {
        testName = passTestName;
        itemName = passItemName;
    }
    public String getTestName()
    { return testName;}
    public String getItemName()
    { return itemName;}


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

    public void setTestName(String passTestName)
    {
        testName = passTestName;
    }
    public void setItemName(String passItemName)
    {
        itemName = passItemName;
    }
}
