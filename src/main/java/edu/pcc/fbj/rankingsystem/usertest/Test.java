package edu.pcc.fbj.rankingsystem.usertest;

/**
 * Data structure to handle information about a test, which  is a group
 * of test items.
 *
 * @author  Ian Burton
 * @version 2016.05.24.1
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

    @Override
    public String toString()
    {
        return name;
    }
}
