package edu.pcc.fbj.rankingsystem.resultreporting;

import edu.pcc.fbj.rankingsystem.resultreporting.dao.ReportDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * @author David Li
 * @version 2016.05.19
 */
public class StatisticsReportDB  extends ReportDB implements ReportDAO
{
    /**
     * Constructor
     */
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
        int numOfTester = 0;
        int numOfFirstChoice = 0;
        int percent = 0;
        int i = 0;

        setMessage(DATABASE_DATA_READING);
        Vector<String> statisticsItemList = getStatisticsItemList(email);
        Vector<String> statisticsEmailList = getStatisticsEmailList(email);
        numOfTester = statisticsEmailList.size();
        Object[][] statisticsResults = new Object[statisticsItemList.size()][2];

        for(String item : statisticsItemList)
        {
            numOfFirstChoice = 0;
            for(String tester : statisticsEmailList)
            {

                try (
                        PreparedStatement stmt = connection.prepareStatement(GET_USER_STATISTICS_GET_RESULT_LIST_SQL);
                )
                {
                    stmt.setString(1, email);
                    stmt.setString(2, tester);
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next())
                    {
                        if(rs.getString("Item1").equals(item) && rs.getInt("Value") == -1)
                        {
                            numOfFirstChoice+=1;
                            break;
                        }
                        else if(rs.getString("Item2").equals(item) && rs.getInt("Value") == 1)
                        {
                            numOfFirstChoice+=1;
                            break;
                        }
                    }
                    stmt.close();


                }
                catch (SQLException se)
                {
                    return null;
                }
            }
            percent = numOfFirstChoice * 100 /numOfTester;

            statisticsResults[i][0] = item;
            statisticsResults[i][1] = percent;
            //System.out.println("Item:"+item+" -> "+percent);
            i++;
        }
        setMessage(DATABASE_DATA_COMPLETE);
        return statisticsResults;
    }

    /**
     *
     * @return column
     */
    @Override
    public List<String> getUserTestTableColumns(String email, String testID)
    {
        List<String> statisticsTableColumn =  new ArrayList<>();

        statisticsTableColumn.add("Item");
        statisticsTableColumn.add("Percent");

        return statisticsTableColumn;
    }

    /**
     * Retrieve user test names who are in a specific group of test items
     * @return Vector<String>
     */
    private Vector<String> getStatisticsEmailList(String testName)
    {
        Vector<String> statisticsEmailList = new Vector<>();
        setMessage(DATABASE_DATA_READING);
        try (
                PreparedStatement stmt = connection.prepareStatement(GET_USER_STATISTICS_GET_EMAIL_LIST_SQL);
        )
        {
            stmt.setString(1, testName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                statisticsEmailList.add(rs.getString("Email"));
            }
            stmt.close();
            setMessage(DATABASE_DATA_COMPLETE);
            return statisticsEmailList;
        }
        catch (SQLException se)
        {
            return null;
        }
    }

    /**
     * Retrieve items which are in a specific group of test items
     * @return Vector<String>
     */
    private Vector<String> getStatisticsItemList(String testName)
    {
        Vector<String> statisticsItemList = new Vector<>();
        setMessage(DATABASE_DATA_READING);
        try (
                PreparedStatement stmt = connection.prepareStatement(GET_USER_STATISTICS_GET_ITEM_LIST_SQL);
        )
        {
            stmt.setString(1, testName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                statisticsItemList.add(rs.getString("Name"));
            }
            stmt.close();
            setMessage(DATABASE_DATA_COMPLETE);
            return statisticsItemList;
        }
        catch (SQLException se)
        {
            return null;
        }
    }

}
