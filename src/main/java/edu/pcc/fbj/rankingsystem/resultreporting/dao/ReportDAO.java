package edu.pcc.fbj.rankingsystem.resultreporting.dao;

import java.util.List;
import java.util.Vector;
import edu.pcc.fbj.rankingsystem.resultreporting.ReportTestResult;

/**
 * ReportDAO is used for defining methods to retrieve report data.
 *
 * @Author: David Li
 * @Version: 2016.04.14
 */
public interface ReportDAO {
    List<ReportTestResult> getUserTestResult(String email);
    Vector<String> getUserEmailList();
}
