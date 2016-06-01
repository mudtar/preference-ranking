package edu.pcc.fbj.rankingsystem.usertest;

/**
 * A pair of items to be presented to the user for preference testing.
 *
 * @author  Ian Burton
 * @version 2016.05.31.1
 */
class PreferencePair
{
    /**
     * The first of the two options to be presented to the user.
     */
    private Item option1;

    /**
     * The second of the two options to be presented to the user.
     */
    private Item option2;

    /**
     * the encoded result of the preference test between the two
     * options, where:
     *     -1 is a win for option1 and a loss for option2
     *      0 is a tie
     *      1 is a loss for option1 and a win for option2
     *
     * Initialize this to -2 so that no toggle buttons are highlighted
     * the first time the pair is displayed.
     */
    private int preference = -2;

    /**
     * Create a new pair of items.
     *
     * @param option1 the first item in the pair
     * @param option2 the second item in the pair
     */
    PreferencePair(Item option1, Item option2)
    {
        this.option1 = option1;
        this.option2 = option2;
    }

    /**
     * Return the first item in the pair.
     *
     * @return the first item in the pair
     */
    Item getOption1()
    {
        return option1;
    }

    /**
     * Return the second item in the pair.
     *
     * @return the second item in the pair
     */
    Item getOption2()
    {
        return option2;
    }

    /**
     * Return the user's preference regarding this item pair.
     *
     * @return the user's preference regarding this item pair
     */
    int getPreference()
    {
        return preference;
    }

    /**
     * Store the user's preference regarding this item pair.
     *
     * @param preference the encoded result of the preference test
     *                   between the two options, where:
     *                       -1 is a win for option1 and a loss for
     *                          option2
     *                        0 is a tie
     *                        1 is a loss for option1 and a win for
     *                          option2
     */
    void setPreference(int preference)
    {
        this.preference = preference;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString()
    {
        return "[" + option1.getName() + ", " + option2.getName() + "]";
    }
}
