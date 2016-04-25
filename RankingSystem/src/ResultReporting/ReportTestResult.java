package ResultReporting;

/**
 * Test Result Class defining test report structure
 *
 * @Author: David Li
 * @Version: 2016.04.14
 */
public class ReportTestResult {
    private String userEmail;
    private String item1Name;
    private String item2Name;
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
        this.userEmail = userEmail;
        this.item1Name = item1Name;
        this.item2Name = item2Name;
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
    }

    /**
     * Retrieve user email
     * @return String userEmail
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Retrieve item1 name
     * @return String item1Name
     */
    public String getItem1Name () {
        return item1Name;
    }

    /**
     * REtrieve item2 name
     * @return String item2Name
     */
    public String getItem2Name () {
        return item2Name;
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
