package edu.pcc.fbj.rankingsystem.usertest;

/**
 * Data structure to handle information about a test, which  is a group
 * of test items.
 *
 * @author  Ian Burton
 * @version 2016.05.31.1
 */
class Test
{
    /**
     * The ID of the test.
     */
    private int ID;

    /**
     * The name of the test.
     */
    private String name;

    /**
     * Create a new test.
     *
     * @param ID   the ID of the test
     * @param name the name of the test
     */
    Test(int ID, String name)
    {
        this.ID = ID;
        this.name = name;
    }

    /**
     * Return the ID of the test.
     *
     * @return the ID of the test
     */
    int getID()
    {
        return ID;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString()
    {
        return name;
    }
}
