package edu.pcc.fbj.rankingsystem.resultreporting;

import edu.pcc.fbj.rankingsystem.dbfactory.RSystemConnection;
import edu.pcc.fbj.rankingsystem.resultreporting.dao.ReportDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: David li
 * @version: 2016.05.19
 */
public class MatrixReportDB extends ReportDB implements ReportDAO
{
    public MatrixReportDB()
    {
    }

    /**
     * Retrieve user test result according to user's email
     * @param   email String
     * @return List<ReportTestResult>
     */
    @Override
    public Object[][] getUserTestResult(String email)
    {
        List<ReportTestResult> results = new ArrayList<>();

        setMessage(DATABASE_DATA_READING);
        try (
                PreparedStatement stmt = connection.prepareStatement(GET_USER_MATRIX_REPORT_SQL)
        )
        {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(new ReportTestResult(
                        rs.getString("Item1"),
                        rs.getString("Item2"),
                        rs.getInt("Value")));
            }

            Object[][] userResults = new Object[results.size()][ReportTestResult.COLUMN_NUMBER];
            for (int i = 0; i < results.size(); i++)
            {
                userResults[i][0] = results.get(i).getItem1Name();
                userResults[i][1] = results.get(i).getItem2Name();
                userResults[i][2] = results.get(i).getPairValue();
            }
            setMessage(DATABASE_DATA_COMPLETE);
            stmt.close();
            return userResults;
        }
        catch (SQLException se)
        {
            return null;
        }
    }

    /**
     *
     * @return column
     */
    @Override
    public List<String> getUserTestTableColumns(String email)
    {
        List<String> results = new ArrayList<>();

        setMessage(DATABASE_DATA_READING);
        try (
                PreparedStatement stmt = connection.prepareStatement(GET_USER_MATRIX_ITEMS_SQL)
        )
        {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(rs.getString("Item1"));
            }

            setMessage(DATABASE_DATA_COMPLETE);
            stmt.close();
            return results;
        }
        catch (SQLException se)
        {
            return null;
        }
    }

}
