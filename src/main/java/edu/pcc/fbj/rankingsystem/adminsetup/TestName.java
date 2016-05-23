package edu.pcc.fbj.rankingsystem.adminsetup;

/**
 * @author Eric Kristiansen on 5/17/2016.
 */
public class TestName
{
    int testNameID;
    String name;

    public TestName(int passTestNameID, String passName)
    {
        testNameID = passTestNameID;
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
        return testNameID;
    }
}
