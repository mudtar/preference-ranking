package edu.pcc.fbj.rankingsystem.resultreporting;

/**
 * Data access object factory used to have access to DAO
 * @Author: David Li
 * @Version: 2016.04.14
 */
public class ReportDAOFactory {
    public static ReportDAO getReportDAO() {
        ReportDAO reportDAO = new ReportDB();
        return reportDAO;
    }
}
