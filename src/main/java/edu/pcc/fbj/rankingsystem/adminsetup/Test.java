package edu.pcc.fbj.rankingsystem.adminsetup;


import java.sql.Timestamp;

/**
 * @author Eric Kristiansen
 * @version 5/23/2016.
 */
public class Test
{
    int testID;
    int userID;
    int testNameID;
    Timestamp timeStamp;

    public Test(int passTestID, int passUserID, int passTestNameID, Timestamp passTimeStamp)
    {
        testID = passTestID;
        userID = passUserID;
        testNameID = passTestNameID;
        timeStamp = passTimeStamp;
    }

    /**
     *
     * @return
     */
    public int getTestID() { return testID; }

    /**
     *
     * @return
     */
    public int getUserID() { return userID; }

    /**
     *
     * @return
     */
    public int getTestNameID() { return testNameID; }
}
