package edu.pcc.fbj.rankingsystem.adminsetup;

/**
 * Created by Electech on 5/17/2016.
 */
public class Test
{
    int testID;
    String name;

    public Test(int passTestID, String passName)
    {
        testID = passTestID;
        name = passName;
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
    public String getName()
    {
        return name;
    }

    /**
     * Method to return name independent of other variables of test object
     * @return returns name
     */
    public int getTestID()
    {
        return testID;
    }
}
