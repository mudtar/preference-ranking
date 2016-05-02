package edu.pcc.fbj.rankingsystem.resultreporting.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Vector;

/**
 * ReportDAO is used for defining methods to retrieve report data.
 *
 * @author David Li
 * @version 2016.04.14
 */
public interface ReportDAO {
    Vector<String> getUserEmailList();
    HashMap<String, Object[][]> getHashTable();
    Connection DBConnection();
    String getMessage();
    boolean getStatus();
}
