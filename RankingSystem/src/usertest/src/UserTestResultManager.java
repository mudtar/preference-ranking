import java.util.UUID;

/**
 * A model class to handle user test results.
 *
 * @author  Ian Burton
 * @version 2016.04.19.1
 */
public class UserTestResultManager {
    /**
     * Uniquely identifies this group of test results.
     */
    private String uniqueTestId;

    /**
     * Default constructor.
     */
    public UserTestResultManager()
    {
        generateUniqueTestId();
    }

    /**
     * Generate a new uniqueTestId. In a future implementation, make
     * sure the uniqueTestId doesn't collide in the database with one
     * that has already been used.
     */
    private void generateUniqueTestId()
    {
        uniqueTestId = UUID.randomUUID().toString();
        System.out.println("uniqueTestId: " + uniqueTestId);
    }

    /**
     * Store a test result in preparation of storing it to the database.
     * Right now, the test option parameters are passed as Strings, but
     * in the future they should be database PK item IDs.
     *
     * @param option1 the first of the two options presented to the user
     * @param option2 the second of the two options presented to the
     *                user
     * @param result  the result of the test between the two options.
     *                -1 represents that option1 won and option2 lost.
     *                 0 represents that the user couldn't decide
     *                   between the options.
     *                 1 represents that option1 lost and option2 won.
     */
    public void registerTestResult(String option1, String option2, int result)
    {
    }
}
