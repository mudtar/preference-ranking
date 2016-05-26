package edu.pcc.fbj.rankingsystem.resultreporting;

import edu.pcc.fbj.rankingsystem.resultreporting.dao.ReportDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author David Li
 * @version 2016.05.19
 */
public class StatisticsReportDB  extends ReportDB implements ReportDAO
{
    public StatisticsReportDB()
    {

    }

    /**
     * Retrieve user test result according to user's email
     * @param   email String
     * @return List<ReportTestResult>
     */
    @Override
    public Object[][] getUserTestResult(String email,  String testID)
    {
        List<ReportTestResult> results = new ArrayList<>();

        setMessage(DATABASE_DATA_READING);
        try (
                PreparedStatement stmt = connection.prepareStatement(GET_USER_BASIC_REPORT_SQL)
        )
        {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(new ReportTestResult(rs.getString("Name"),
                        rs.getInt("Wins"),
                        rs.getInt("Losses"),
                        rs.getInt("Ties"),
                        rs.getInt("Points")));
            }

            Object[][] userResults = new Object[results.size()][ReportTestResult.COLUMN_NUMBER];
            for (int i = 0; i < results.size(); i++)
            {
                userResults[i][0] = results.get(i).getItem1Name();
                userResults[i][1] = results.get(i).getWins();
                userResults[i][2] = results.get(i).getLosses();
                userResults[i][3] = results.get(i).getTies();
                userResults[i][4] = results.get(i).getScores();
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
    public List<String> getUserTestTableColumns(String email, String testID)
    {
        List<String> statisticsTableColumn =  new ArrayList<>();


        return statisticsTableColumn;
    }

}
