package edu.pcc.fbj.rankingsystem.dbfactory;

import edu.pcc.fbj.rankingsystem.adminsetup.Item;
import edu.pcc.fbj.rankingsystem.adminsetup.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides method to retrieve Results from the
 * FueledByJava database
 *
 * @author Eric Kristiansen
 * @version 5/23/2016
 */
public class ResultDAO {

    //Queries
    private static final String GET_RESULTS_SQL = "SELECT PK_ResultID, FK_TestID, FK_Item1ID, FK_Item2ID, Value FROM FBJ_RESULT;";
    private static final String GET_UNIQUE_ITEM_IDS = "SELECT FK_Item1ID AS UniqueItemID FROM FBJ_Result UNION SELECT FK_Item2ID FROM FBJ_RESULT";

    //Object to hold items
    private Connection connection = null;
    private List<Result> results;

    /**
     * Create a results object
     * Read from the FBJ database and populate the results list
     */
    public ResultDAO()
    {
        try
        {
            connection = RSystemConnection.getConnection();
            results = readResults();
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
    private List<Result> readResults()
    {
        ArrayList<Result> resultList = new ArrayList<>();

        try
        {
            PreparedStatement stmt = connection.prepareStatement(GET_RESULTS_SQL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                resultList.add(new Result(rs.getInt("PK_ResultID"), rs.getInt("FK_TestID"),
                        rs.getInt("FK_Item1ID"), rs.getInt("FK_Item2ID"), rs.getInt("Value")));
            }
        }
        catch (SQLException e)
        {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        return resultList;
    }

    /**
     * @return list of items read from the FBJ result table
     */
    public List<Result> getResults()
    {
        results = readResults();
        return results;
    }

    /**
     *  get a List of ItemIDs used in Result objects
     */
    public List<Integer> getItemsUsed()
    {
        try
        {
            List<Integer> uniqueItemList = new ArrayList<>();
            PreparedStatement stmt = connection.prepareStatement(GET_UNIQUE_ITEM_IDS);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                uniqueItemList.add(rs.getInt("UniqueItemID"));
            }
            return uniqueItemList;
        }
        catch (SQLException e)
        {
            System.err.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }




}
