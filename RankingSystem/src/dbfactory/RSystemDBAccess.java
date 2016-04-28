package dbfactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import ResultReporting.ReportTestResult;

/**
 * This is data accss object used for communicate with database to retrieve data
 * @Author: David Li
 * @Version: 2016.04.14
 *
 * @Author: David Li
 * @Version: 2016.04.21
 * 1. Only display users who have already completed a test;
 * 2. Display result in order of total values;
 */
public class RSystemDBAccess implements RSystemDAO{

    private static final String GET_USER_EMAIL_LIST_SQL
                                     = "SELECT FBJ_USER.Email FROM FBJ_USER " +
                                       " JOIN FBJ_TEST ON FBJ_USER.PK_UserID = FBJ_TEST.FK_UserID";

    private static final String GET_USER_TEST_RESULT_SQL
            = "SELECT FBJ_USER.Email " +
               " ,FBJ_ITEM.Name " +
               " ,ISNULL(SUM(CASE WHEN FBJ_RESULT.Value = 1 THEN 1 END), 0) AS Wins " +
               " ,ISNULL(SUM(CASE WHEN FBJ_RESULT.Value = -1 THEN -1 END), 0) AS Losses " +
               " ,ISNULL(SUM(CASE WHEN FBJ_RESULT.Value = 0 THEN 0 END), 0) AS Ties " +
               " FROM FBJ_USER " +
               " JOIN FBJ_TEST ON FBJ_USER.PK_UserID = FBJ_TEST.FK_UserID " +
               " JOIN FBJ_RESULT ON FBJ_TEST.PK_TestID = FBJ_RESULT.FK_TestID " +
               " JOIN FBJ_ITEM ON FBJ_ITEM.PK_ItemID = FBJ_RESULT.FK_Item1ID " +
               " WHERE FBJ_USER.Email = ? " +
               " GROUP BY FBJ_USER.Email, FBJ_ITEM.Name " +
               " ORDER BY SUM(FBJ_RESULT.Value) DESC";

    /**
     * Retrieve user test result according to user's email
     * @param String email
     * @return List<ReportTestResult>
     */
    @Override
    public List<ReportTestResult> getUserTestResult(String email) {
        List<ReportTestResult> results = new ArrayList<>();
        try (
                Connection connection = RSystemConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(GET_USER_TEST_RESULT_SQL)
        ) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(new ReportTestResult(rs.getString("Name"),
                        rs.getInt("Wins"),
                        rs.getInt("Losses"),
                        rs.getInt("Ties")));
            }
            return results;
        }
        catch (SQLException se) {
            System.err.println(se);
            return null;
        }
    }

    /**
     * Retrieve user email list
     * @return Vector<String>
     */
    @Override
    public Vector<String> getUserEmailList() {
        Vector<String> results = new Vector<>();
        try (
                Connection connection = RSystemConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement(GET_USER_EMAIL_LIST_SQL);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                results.add(rs.getString("Email"));
            }
            return results;
        }
        catch (SQLException se) {
            System.err.println(se);
            return null;
        }
    }
}
