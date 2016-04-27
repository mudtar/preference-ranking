package AdminSetupModel;

/**
 * @author Eric Kristiansen
 *
 * version 4/25/16
 *
 * This class was designed with future development in mind. It only holds a string variable
 * currently, but will eventually hold more
 */
public class Item {

    private String name;

    /**
     * just a constructor for the Item object
     * @param passName
     */
    public Item(String passName)
    {
        name = passName;
    }

    /**
     * @return returns the string value in the name variable
     */
    @Override public String toString()
    {
        return name;
    }
}
