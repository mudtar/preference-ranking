package edu.pcc.fbj.rankingsystem.resultreporting;

import edu.pcc.fbj.rankingsystem.dbfactory.RSystemDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import edu.pcc.fbj.rankingsystem.dbfactory.RSystemDAOFactory;

/**
 * Create report GUI and retrieve report data.
 * @Author: David Li
 * @Version: 2016.04.13
 *
 * @Author: David Li
 * @Version: 2016.04.24
 * 1. Fix NullPointerException;
 *
 */
public class ReportTable {

    private RSystemDAO reportDAO;
    private List<ReportTestResult> userTestResults;
    private Vector<String> userEmailList;
    private HashMap<String, Object[][]> usersToResults;
    private ReportPanel reportPane;


    /**
     * Constructor - construct GUI and retrieve report data
     */
    public ReportTable(){
        usersToResults = new HashMap<>();
        reportDAO = RSystemDAOFactory.getReportDAO();
        userEmailList = reportDAO.getUserEmailList();
        String[] columnNames = {"Item", "Wins", "Losses", "Ties"};

        if(userEmailList != null) {
            for (String email : userEmailList) {
                System.out.println(email);
                userTestResults = reportDAO.getUserTestResult(email);
                if(userTestResults != null) {
                    Object[][] userResults = new Object[userTestResults.size()][ReportTestResult.COLUMN_NUMBER];
                    for (int i = 0; i < userTestResults.size(); i++) {
                        userResults[i][0] = userTestResults.get(i).getItem1Name();
                        userResults[i][1] = userTestResults.get(i).getWins();
                        userResults[i][2] = userTestResults.get(i).getLosses();
                        userResults[i][3] = userTestResults.get(i).getTies();
                    }
                    usersToResults.put(email, userResults);
                }
            }
        }

        reportPane = new ReportPanel(userEmailList, columnNames, usersToResults);
        reportPane.setOpaque(true);

    }

    /**
     * Return report panel
     * @return - ReportPanel reportPane
     */
    public ReportPanel getReportPanel(){
        return reportPane;
    }

    /**
     * Print debug information
     */
    public void printUserTestResults(){
        for (ReportTestResult result : userTestResults) {
          System.out.println(result);
        }
    }
}
