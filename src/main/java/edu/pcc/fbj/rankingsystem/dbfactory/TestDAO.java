package edu.pcc.fbj.rankingsystem.dbfactory;

import edu.pcc.fbj.rankingsystem.adminsetup.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides method to retrieve, delete and insert Tests in the
 * FueledByJava database
 *
 * @author Eric Kristiansen
 * @version 5/17/2016
 */
public class TestDAO
{
    //Queries
    private static final String GET_TESTS_SQL = "SELECT * FROM FBJ_Test";
    //private static final String DELETE_TEST = "DELETE FROM FBJ_ITEM WHERE Name = ?";
    //private static final String INSERT_TEST = "INSERT INTO FBJ_ITEM(Name) VALUES(?)";

    //Object to hold items
    private Connection connection = null;
    private List<Test> tests;

    /**
     * Create an items object
     * Read from the FBJ database and populate the items list
     */
    public TestDAO()
    {
        try
        {
            connection = RSystemConnection.getConnection();
            tests = readTests();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
    }

    /**
     * Read items table.
     * If an error occurs, a stack trace is printed to standard error and an empty list is returned.
     * @return the list of items read
     */
    private List<Test> readTests()
    {
        ArrayList<Test> testList = new ArrayList<>();

        //add items
        try
        {
            PreparedStatement stmt = connection.prepareStatement(GET_TESTS_SQL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                tests.add(new Test(rs.getInt("TestID"), rs.getString("Name")));
            }
        }
        catch (SQLException e)
        {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        return testList;
    }

    /**
     * @return list of items read from the FBJ Item database
     */
    public List<Test> getItems()
    {
        tests = readTests();
        return tests;
    }

}
