package edu.pcc.fbj.rankingsystem.adminsetup;

/**
 * Class to represent
 * @author Eric Kristiansen
 * @version 5/23/2016.
 */
public class TestNameItem
{
    //SELECT PK_TestNameItemID, FK_TestNameID, FK_ItemID From FBJ_TEST_NAME_ITEM
    int testNameItemID;
    int testNameID;
    int itemID;

    public TestNameItem(int passTestNameItemID, int passTestName, int passItemID)
    {
        testNameItemID = passTestNameItemID;
        testNameID = passTestName;
        itemID = passItemID;
    }
}
