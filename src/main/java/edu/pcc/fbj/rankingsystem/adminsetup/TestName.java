package edu.pcc.fbj.rankingsystem.adminsetup;

/**
 * @author Eric Kristiansen
 * @version 5/31/2016.
 */
public class TestName
{
    private int testNameID;
    private String name;

    /**
     * Constructor
     * @param passTestNameID
     * @param passName
     */
    public TestName(int passTestNameID, String passName)
    {
        testNameID = passTestNameID;
        name = passName;
    }

    /**
     * Constructor
     * @param passName
     */
    public TestName(String passName)
    {
        name = passName;
        testNameID = 0;
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

}
