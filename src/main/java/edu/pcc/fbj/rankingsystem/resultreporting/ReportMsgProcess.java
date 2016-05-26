package edu.pcc.fbj.rankingsystem.resultreporting;

import edu.pcc.fbj.rankingsystem.resultreporting.dao.ReportDAO;

import java.util.List;

/**
 * Start a Message thread to display message while reading data from database.
 *
 * @author David Li
 * @version 2016.04.28
 */
public class ReportMsgProcess implements Runnable
{
    private List<ReportDAO> dao;
    private ReportPanel reportPane;

    /**
     * Constructor
     * @param dao ReportDAO
     * @param reportPane ReportPanel
     */
    public ReportMsgProcess(List<ReportDAO>  dao, ReportPanel reportPane)
    {
        this.dao = dao;
        this.reportPane = reportPane;
    }

    /**
     * Start a message thread
     */
    public void run()
    {
        System.out.println("Start a message processing thread");
        while(reportPane.getSignal() != Signal.DATABASE_SIGNAL_THREAD_TERMINATE)
        {
            reportPane.setLabelText(dao.get(0).getMessage());
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        reportPane.setLabelText(dao.get(0).getMessage());
        System.out.println("End a message processing thread");

    }
}