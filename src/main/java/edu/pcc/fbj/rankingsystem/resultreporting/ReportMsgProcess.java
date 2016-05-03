package edu.pcc.fbj.rankingsystem.resultreporting;

import edu.pcc.fbj.rankingsystem.resultreporting.dao.ReportDAO;

/**
 * Start a Message thread to display message while reading data from database.
 *
 * @author David Li
 * @version 2016.04.28
 */
public class ReportMsgProcess implements Runnable
{
    private ReportDAO dao;
    private ReportPanel reportPane;

    /**
     * Constructor
     * @param dao ReportDAO
     * @param reportPane ReportPanel
     */
    public ReportMsgProcess(ReportDAO dao, ReportPanel reportPane){
        this.dao = dao;
        this.reportPane = reportPane;
    }

    /**
     * Start a message thread
     */
    public void run()
    {
        System.out.println("Start a message processing thread");
        while(!dao.getStatus()){
            reportPane.setLabelText(dao.getMessage());
        }
        reportPane.setLabelText(dao.getMessage());
        System.out.println("End a message processing thread");

    }
}