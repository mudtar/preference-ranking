package edu.pcc.fbj.rankingsystem.resultreporting;

/**
 * Test Result Class defining test report structure
 *
 * @author David Li
 * @version 2016.04.14
 */
public class ReportTestResult {

    private String item1Name;
    private int wins;
    private int losses;
    private int ties;
    static final int COLUMN_NUMBER = 4;

    /**
     * Constructor
     * @param item1Name - item name
     * @param wins - points of wins
     * @param losses - points of losses
     * @param ties - points of ties
     */
    public ReportTestResult(String item1Name, int wins, int losses, int ties) {

        this.item1Name = item1Name;
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
    }


    /**
     * Retrieve item1 name
     * @return String item1Name
     */
    public String getItem1Name () {
        return item1Name;
    }


    /**
     * Retrieve points of wins
     * @return  int wins
     */
    public int getWins () {
        return wins;
    }

    /**
     * Retrieve points of losses
     * @return int losses
     */
    public int getLosses() {
        return losses;
    }

    /**
     * Retrieve points of ties
     * @return int ties
     */
    public int getTies () {
        return ties;
    }

    /**
     * Print data
     * @return String
     */
    @Override
    public String toString() {
        return "User Test Result-> " //+
                 // " User Email: " + userEmail
                + " Item 1: " + item1Name
                //+ " Item 2: " + item2Name
                + " Wins: " + wins
                + " Losses: " + losses
                + " Ties: " + ties;
    }
}
