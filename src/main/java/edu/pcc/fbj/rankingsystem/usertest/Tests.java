package edu.pcc.fbj.rankingsystem.usertest;

import edu.pcc.fbj.rankingsystem.dbfactory.RSystemConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The existing tests available to this test session.
 *
 * @author  Ian Burton
 * @version 2016.05.24.1
 */
class Tests
{
    /**
     * The existing tests available to this test session.
     */
    private List<Test> tests = new ArrayList<>();

    /**
     * The index of the current test.
     */
    private int currentTestIndex;

    /**
     * The database connection used throughout the application.
     */
    private Connection con;

    Tests() throws SQLException
    {
        con = RSystemConnection.getConnection();
        populateTests();
        System.out.println(tests);
    }

    private void populateTests() throws SQLException
    {
        PreparedStatement selectTestNames = con.prepareStatement(
            "SELECT PK_TestNameID, Name " +
            "FROM FBJ_TEST_NAME " +
            ";"
        );
        ResultSet rs = selectTestNames.executeQuery();

        while (rs.next()) {
            tests.add(new Test(rs.getInt("PK_TestNameID"),
                               rs.getString("Name")));
        }
    }

    List<Test> getTests()
    {
        return tests;
    }
}
