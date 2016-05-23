package edu.pcc.fbj.rankingsystem.dbfactory;

import edu.pcc.fbj.rankingsystem.adminsetup.TestName;

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
 * @version 5/23/2016
 */
public class TestNameDAO
{
    //Queries
    private static final String GET_TESTS_SQL = "SELECT PK_TestNameID, Name FROM FBJ_TEST_NAME";
    private static final String DELETE_TEST = "DELETE FROM FBJ_TEST_NAME WHERE Name = ?";
    private static final String INSERT_TEST = "INSERT INTO FBJ_TEST_NAME(Name) VALUES(?)";

    //Object to hold items
    private Connection connection = null;
    private List<TestName> testNames;

    /**
     * Create an items object
     * Read from the FBJ database and populate the items list
     */
    public TestNameDAO()
    {
        try
        {
            connection = RSystemConnection.getConnection();
            testNames = readTestNames();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
    }

    /**
     * Read Test_Name table.
     * If an error occurs, a stack trace is printed to standard error and an empty list is returned.
     * @return the list of items read
     */
    private List<TestName> readTestNames()
    {
        ArrayList<TestName> testList = new ArrayList<>();

        try
        {
            PreparedStatement stmt = connection.prepareStatement(GET_TESTS_SQL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                testNames.add(new TestName(rs.getInt("PK_TestNameID"), rs.getString("Name")));
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
     * @return list of tests read from the FBJ Item database
     */
    public List<TestName> getTestNames()
    {
        testNames = readTestNames();
        return testNames;
    }

    /**
     *
     */
    public void setTestNames(List<TestName> passTestName)
    {
        passTestName.forEach(t->
        {
            System.out.println(t.toString());
        });
    }

}
