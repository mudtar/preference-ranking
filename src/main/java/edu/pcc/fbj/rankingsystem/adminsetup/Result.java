package edu.pcc.fbj.rankingsystem.adminsetup;

/**
 * Class to represent
 * @author Eric Kristiansen
 * @version 5/31/2016.
 */
public class Result
{
    int resultID;
    int testID;
    int item1ID;
    int item2ID;
    int value;

    /**
     *  Constructor for Result
     * @param passResultID
     * @param passTestID
     * @param passItem1ID
     * @param passItem2ID
     * @param passValue
     */
    public Result(int passResultID, int passTestID, int passItem1ID, int passItem2ID, int passValue)
    {
        resultID = passResultID;
        testID = passTestID;
        item1ID = passItem1ID;
        item2ID = passItem2ID;
        value = passValue;
    }
}

