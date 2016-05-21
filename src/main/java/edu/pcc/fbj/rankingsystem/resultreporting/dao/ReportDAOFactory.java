package edu.pcc.fbj.rankingsystem.resultreporting.dao;

import edu.pcc.fbj.rankingsystem.resultreporting.BasicReportDB;
import edu.pcc.fbj.rankingsystem.resultreporting.MatrixReportDB;
import edu.pcc.fbj.rankingsystem.resultreporting.ReportDB;

/**
 * Data access object factory used to have access to DAO
 * @author David Li
 * @version 2016.04.14
 */
public class ReportDAOFactory {
    public static ReportDAO getBasicReportDAO() {
        return new BasicReportDB();
    }

    public static ReportDAO getMatrixReportDAO() {
        return new MatrixReportDB();
    }
}
