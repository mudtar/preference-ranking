package edu.pcc.fbj.rankingsystem.resultreporting;

import edu.pcc.fbj.rankingsystem.resultreporting.dao.ReportDAO;
import edu.pcc.fbj.rankingsystem.resultreporting.dao.ReportDAOFactory;

import java.util.ArrayList;
import java.util.List;
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
public class ReportTable
{

    private ReportPanel reportPane;
    private List<ReportDAO> dao;

    /**
     * Constructor - construct GUI and retrieve report data
     */
    public ReportTable()
    {

        dao = new ArrayList<>();
        dao.add(ReportDAOFactory.getBasicReportDAO());
        dao.add(ReportDAOFactory.getMatrixReportDAO());
        dao.add(ReportDAOFactory.getStatisticsReportDAO());

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
