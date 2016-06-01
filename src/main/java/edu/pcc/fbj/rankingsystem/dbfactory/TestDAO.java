package edu.pcc.fbj.rankingsystem.dbfactory;

import edu.pcc.fbj.rankingsystem.adminsetup.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eric Kristiansen
 * @version 5/31/2016.
 */
public class TestDAO
{
    //Queries
    private static final String GET_TESTS_SQL = "SELECT PK_TestID, FK_UserID, FK_TestNameID, Updt " +
            "FROM FBJ_TEST;";
    private static final String GET_UNIQUE_ITEM_IDS = "SELECT FK_Item1ID AS UniqueItemID " +
            "FROM FBJ_Result UNION SELECT FK_Item2ID FROM FBJ_RESULT";

    //Object to hold items
    private Connection connection = null;
    private List<Test> tests;

    /**
     * Create a results object
     * Read from the FBJ database and populate the results list
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
     * Read test table.
     * If an error occurs, a stack trace is printed to standard error and an empty list is returned.
     * @return the list of items read
     */
    private List<Test> readTests()
    {
        ArrayList<Test> testList = new ArrayList<>();

        try
        {
            PreparedStatement stmt = connection.prepareStatement(GET_TESTS_SQL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                testList.add(new Test(rs.getInt("PK_TestID"), rs.getInt("FK_UserID"),
                        rs.getInt("FK_TestNameItemID"), rs.getTimestamp("Updt")));
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
     * @return list of items read from the FBJ result table
     */
    public List<Test> getTests()
    {
        tests = readTests();
        return tests;
    }
}
