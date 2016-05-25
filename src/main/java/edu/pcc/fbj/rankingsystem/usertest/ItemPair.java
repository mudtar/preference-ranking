package edu.pcc.fbj.rankingsystem.usertest;

class ItemPair
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
    private int preference;
}
