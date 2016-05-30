package edu.pcc.fbj.rankingsystem.resultreporting.dao;

import edu.pcc.fbj.rankingsystem.resultreporting.ReportTestResult;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * ReportDAO is used for defining methods to retrieve report data.
 *
 * @author David Li
 * @version 2016.04.14
 */
public interface ReportDAO {
    String DATABASE_MESSAGE_INIT = "Database initail ...";
    String DATABASE_CONNECTION_CONNECTING = "Connecting ...";
    String DATABASE_CONNECTION_FAILED = "Failed to connect!";
    String DATABASE_CONNECTION_SUCCESS = "Connect successfully!";
    String DATABASE_DATA_READING = "Retrieving data ...";
    String DATABASE_DATA_COMPLETE = "Reading data complete!";
    String DATABASE_DATA_SELECTION = "Email List:";

    String GET_USER_EMAIL_LIST_SQL
            = "SELECT DISTINCT FBJ_USER.Email FROM FBJ_USER " +
            " JOIN FBJ_TEST ON FBJ_USER.PK_UserID = FBJ_TEST.FK_UserID";

    String GET_USER_TEST_NAME_LIST_SQL
            = "SELECT Name FROM FBJ_TEST_NAME_LIST" +
            " WHERE Email = ?";

    String GET_USER_TEST_ID_LIST_SQL
            = "SELECT TestID FROM FBJ_USER_TEST_ID_LIST " +
            " WHERE UserEmail = ? AND TestName = ? ORDER BY TestID DESC";

    String GET_USER_BASIC_REPORT_SQL =
            " SELECT Item1 AS Name, Wins, Losses, Ties, Points FROM FBJ_BASIC_REPORT" +
                    " WHERE Email = ? AND FK_TestID = ?" +
                    " ORDER BY Points DESC";

    String GET_USER_MATRIX_REPORT_SQL =
            "SELECT Item1, Item2, Value FROM FBJ_MATRIX_REPORT" +
                    " WHERE Email = ? AND FK_TestID = ?";

    String GET_USER_MATRIX_ITEMS_SQL =
            "SELECT Item1 FROM FBJ_MATRIX_ITEMS WHERE Email = ? AND FK_TestID = ?";

    String GET_USER_STATISTICS_GET_EMAIL_LIST_SQL
            = "SELECT DISTINCT FBJ_USER.Email" +
            " FROM FBJ_USER JOIN FBJ_TEST ON FBJ_TEST.FK_UserID = FBJ_USER.PK_UserID" +
            " JOIN FBJ_TEST_NAME ON FBJ_TEST.FK_TestNameID = FBJ_TEST_NAME.PK_TestNameID" +
            " WHERE  FBJ_TEST_NAME.Name = ?";

    String GET_USER_STATISTICS_GET_ITEM_LIST_SQL
            = "SELECT DISTINCT FBJ_ITEM.Name" +
            " FROM FBJ_ITEM JOIN FBJ_TEST_NAME_ITEM ON FBJ_TEST_NAME_ITEM.FK_ItemID = FBJ_ITEM.PK_ItemID" +
            " JOIN FBJ_TEST_NAME ON FBJ_TEST_NAME.PK_TestNameID = FBJ_TEST_NAME_ITEM.FK_TestNameID" +
            " WHERE FBJ_TEST_NAME.Name = ?";

    String GET_USER_STATISTICS_GET_RESULT_LIST_SQL
            = "SELECT FBJ_USER.Email " +
            " ,FBJ_TEST.PK_TestID " +
            " ,FBJ_RESULT.PK_ResultID " +
            " ,I1.Name AS Item1 " +
            " ,I2.Name AS Item2 " +
            " ,FBJ_RESULT.Value " +
            " FROM FBJ_USER     " +
            " JOIN FBJ_TEST ON FBJ_USER.PK_UserID = FBJ_TEST.FK_UserID     " +
            " JOIN FBJ_RESULT ON FBJ_TEST.PK_TestID = FBJ_RESULT.FK_TestID " +
            " JOIN FBJ_ITEM I1 ON I1.PK_ItemID = FBJ_RESULT.FK_Item1ID " +
            " JOIN FBJ_ITEM I2 ON I2.PK_ItemID = FBJ_RESULT.FK_Item2ID " +
            " JOIN FBJ_TEST_NAME ON FBJ_TEST.FK_TestNameID = FBJ_TEST_NAME.PK_TestNameID " +
            " WHERE FBJ_RESULT.FK_TestID IN (SELECT MAX(PK_TestID) FROM FBJ_TEST GROUP BY FBJ_TEST.FK_UserID) " +
            " AND FBJ_TEST_NAME.Name = ? AND FBJ_USER.Email = ? " +
            " ORDER BY FBJ_USER.Email,FBJ_TEST_NAME.Name, PK_TestID, PK_ResultID";

    Vector<String> getUserEmailList();
    String getUserEmail(int index);
    Vector<String> getUserTestID(String email, String testName);
    Vector<String> getUserTestNameList(String email);
    //Object[][] getUserTestResult(String email, String testID);
    //List<String> getUserTestTableColumns(String email, String testID);
    Object[][] getUserTestResult(HashMap<String, Object> param);
    List<String> getUserTestTableColumns(HashMap<String, Object> param);
    Connection DBConnection();
    String getMessage();
    void setMessage(String msg);
    boolean getStatus();
}
