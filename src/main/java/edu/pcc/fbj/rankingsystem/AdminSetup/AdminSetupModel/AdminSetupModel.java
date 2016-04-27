package AdminSetupModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will hold all of the database code ideally
 *
 * @author Eric Kristiansen
 * @version 4/19/16
 */
public class AdminSetupModel {

    private static final String DB_NAME = "234a_FueledByJava";
    private static final String FUELDBYJAVA_URL = "jdbc:jtds:sqlserver://cisdbss.pcc.edu/" + DB_NAME;
    private static final String USERNAME = "234a_FueledByJava";
    private static final String PASSWORD = "avaJyBdeleuF_a432#";

    //Queries
    private static final String GET_ITEMS_SQL = "SELECT * FROM FBJ_ITEM";

    //Object to hold items
    private List<Item> items;

    /**
     * Create an items object
     * Read from the FBJ database and populate the items list
     */
    public AdminSetupModel() {
        items = readItems();
    }

    /**
     * Create and return a connection to the database
     * @return connection to the FBJ database
     */
    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(FUELDBYJAVA_URL, USERNAME, PASSWORD);
        return connection;
    }

    /**
     * Read items table.
     * If an error occurs, a stack trace is printed to standard error and an empty list is returned.
     * @return the list of items read
     */
    private List<Item> readItems() {
        ArrayList<Item> itemList = new ArrayList<>();

        //add items
        try (
                Connection connection = getConnection();
                PreparedStatement stmt = connection.prepareStatement(GET_ITEMS_SQL);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                itemList.add(new Item(rs.getString("Name")));
            }
        }
        catch (SQLException e) {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        return itemList;
    }

    /**
     * @return list of items read from the FBJ Item database
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * sets a list of Items to the database
     * @param passItems
     */
    public void setItems(List<Item> passItems) {
        //set items here
        for(Item i: passItems)
        {
            System.out.println(i.toString());
        }
    }


}
