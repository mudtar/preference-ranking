package edu.pcc.fbj.rankingsystem.resultreporting.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import edu.pcc.fbj.rankingsystem.resultreporting.ReportTestResult;

/**
 * ReportDAO is used for defining methods to retrieve report data.
 *
 * @author David Li
 * @version 2016.04.14
 */
public interface ReportDAO {
    //List<ReportTestResult> getUserTestResult(String email);
    Vector<String> getUserEmailList();
    HashMap<String, Object[][]> getHashTable();
}
