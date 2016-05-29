package edu.pcc.fbj.rankingsystem.usertest;

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
     */
    private int preference = -2;

    PreferencePair(Item option1, Item option2)
    {
        this.option1 = option1;
        this.option2 = option2;
    }

    Item getOption1()
    {
        return option1;
    }

    Item getOption2()
    {
        return option2;
    }

    int getPreference()
    {
        return preference;
    }

    void setPreference(int preference)
    {
        this.preference = preference;
    }

    @Override
    public String toString()
    {
        return "[" + option1.getName() + ", " + option2.getName() + "]";
    }
}
