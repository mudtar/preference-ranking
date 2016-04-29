package main.java.edu.pcc.fbj.rankingsystem.resultreporting;

import  main.java.edu.pcc.fbj.rankingsystem.resultreporting.dao.ReportDAO;

/**
 * Start a thread to process database transaction
 * @author David Li
 * @version 2016.04.28
 */
public class ReportDBProcess implements Runnable
{
    private ReportDAO dao;
    private ReportPanel reportPane;

    /**
     * Constructor
     * @param dao ReportDAO
     * @param reportPane ReportPanel
     */
    public ReportDBProcess(ReportDAO dao, ReportPanel reportPane){
        this.dao = dao;
        this.reportPane = reportPane;
    }

    /**
     * Start DB Processing thread
     */
    public void run() {
        System.out.println("Start a DB processing thread");
        dao.DBConnection();
        reportPane.setListItem(dao.getUserEmailList());
        reportPane.setListToTableData(dao.getHashTable());
        System.out.println("End a DB processing thread");

    }
}
