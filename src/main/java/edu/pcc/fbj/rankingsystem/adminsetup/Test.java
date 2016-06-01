package edu.pcc.fbj.rankingsystem.adminsetup;


import java.sql.Timestamp;

/**
 * @author Eric Kristiansen
 * @version 5/31/2016.
 */
public class Test
{
    int testID;
    int userID;
    int testNameID;
    Timestamp timeStamp;

    /**
     * Constructor
     * @param passTestID
     * @param passUserID
     * @param passTestNameID
     * @param passTimeStamp
     */
    public Test(int passTestID, int passUserID, int passTestNameID, Timestamp passTimeStamp)
    {
        testID = passTestID;
        userID = passUserID;
        testNameID = passTestNameID;
        timeStamp = passTimeStamp;
    }

}
