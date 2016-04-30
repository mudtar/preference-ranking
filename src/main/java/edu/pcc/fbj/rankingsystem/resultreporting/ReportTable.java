package edu.pcc.fbj.rankingsystem.resultreporting;

import edu.pcc.fbj.rankingsystem.resultreporting.dao.ReportDAO;
import edu.pcc.fbj.rankingsystem.resultreporting.dao.ReportDAOFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create report GUI and retrieve report data.
 * @author David Li
 * @version 2016.04.13
 *
 * @author David Li
 * @version 2016.04.24
 * 1. Fix NullPointerException;
 *
 * @author David Li
 * @version 2016.04.28
 * 1. Refactor code;
 * 2. Use a separate thread to process database transaction.
 *
 */
public class ReportTable {

    private ReportPanel reportPane;

    /**
     * Constructor - construct GUI and retrieve report data
     */
    public ReportTable(){

        ReportDAO dao = ReportDAOFactory.getReportDAO();
        reportPane = new ReportPanel();
        reportPane.setOpaque(true);

        ReportDBProcess daoProcessor = new ReportDBProcess(dao, reportPane);
        ReportMsgProcess paneMsgProcessor = new ReportMsgProcess(dao, reportPane);
        ExecutorService executeTask = Executors.newCachedThreadPool();
        executeTask.execute(daoProcessor);
        executeTask.execute(paneMsgProcessor);

    }

    /**
     * Return report panel
     * @return - ReportPanel reportPane
     */
    public ReportPanel getReportPanel(){
        return reportPane;
    }

}
