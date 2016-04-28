package edu.pcc.fbj.rankingsystem.resultreporting;

import edu.pcc.fbj.rankingsystem.resultreporting.dao.ReportDAO;
import edu.pcc.fbj.rankingsystem.resultreporting.dao.ReportDAOFactory;

/**
 * Create report GUI and retrieve report data.
 * @author David Li
 * @version 2016.04.13
 *
 * @author David Li
 * @version 2016.04.24
 * 1. Fix NullPointerException;
 *
 */
public class ReportTable {

    private ReportPanel reportPane;

    /**
     * Constructor - construct GUI and retrieve report data
     */
    public ReportTable(){

        ReportDAO dao = ReportDAOFactory.getReportDAO();
        reportPane = new ReportPanel(dao.getUserEmailList(), dao.getHashTable());
        reportPane.setOpaque(true);
    }

    /**
     * Return report panel
     * @return - ReportPanel reportPane
     */
    public ReportPanel getReportPanel(){
        return reportPane;
    }

}
