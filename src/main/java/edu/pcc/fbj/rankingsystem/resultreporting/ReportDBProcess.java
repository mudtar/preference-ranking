package edu.pcc.fbj.rankingsystem.resultreporting;

import edu.pcc.fbj.rankingsystem.resultreporting.dao.ReportDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * Start a thread to process database transaction
 * @author David Li
 * @version 2016.04.28
 */
public class ReportDBProcess implements Runnable
{
    private List<ReportDAO> dao;
    private ReportPanel reportPane;
    private Signal signal;

    /**
     * Constructor
     * @param dao ReportDAO
     * @param reportPane ReportPanel
     */
    public ReportDBProcess(List<ReportDAO> dao, ReportPanel reportPane)
    {
        this.dao = dao;
        this.reportPane = reportPane;
    }

    /**
     * Start DB Processing thread
     */
    public void run()
    {
        System.out.println("Start a DB processing thread");
        Vector<String> userEmailList = null;
        if(dao.get(0).DBConnection() != null)
        {
            userEmailList = dao.get(0).getUserEmailList();
            reportPane.setListItem(userEmailList);
        }
        while(true)
        {
            if(reportPane.getSignal() == signal.DATABASE_SIGNAL_RETRIEVE_DATA )
            {
                if(dao.get(0).DBConnection() != null && userEmailList != null) {
                    reportPane.setBasicTableColumns(dao.get(0).getUserTestTableColumns(""));
                    reportPane.setBasicReportData(dao.get(0).getUserTestResult(userEmailList.get(reportPane.getEmailIndex())));
                    dao.get(0).setMessage(dao.get(0).DATABASE_DATA_SELECTION);
                    reportPane.setSignal(signal.DATABASE_SIGNAL_THREAD_WAIT);
                }

            }
            else if(reportPane.getSignal() == signal.DATABASE_SIGNAL_RETRIEVE_MATRIX_DATA)
            {
                if(dao.get(1).DBConnection() != null && userEmailList != null) {
                    reportPane.setMatrixTableColumns(dao.get(1).getUserTestTableColumns(userEmailList.get(reportPane.getEmailIndex())));
                    reportPane.setMatrixReportData(dao.get(1).getUserTestResult(userEmailList.get(reportPane.getEmailIndex())));
                    dao.get(1).setMessage(dao.get(1).DATABASE_DATA_SELECTION);
                    reportPane.setSignal(signal.DATABASE_SIGNAL_THREAD_WAIT);
                }
            }
            else if(reportPane.getSignal() == signal.DATABASE_SIGNAL_THREAD_TERMINATE)
            {
                System.out.println("End a DB processing thread");
                break;
            }

            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                System.out.println("Thread is interrupted!");;
            }
        }
    }
}
