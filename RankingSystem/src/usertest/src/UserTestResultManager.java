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
}
