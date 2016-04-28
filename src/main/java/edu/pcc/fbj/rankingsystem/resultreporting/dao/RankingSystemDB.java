package edu.pcc.fbj.rankingsystem.resultreporting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Used for defining parameters of database to establish connection to database
 * @Author: David Li
 * @Version: 2016.04.14
 */
public class RankingSystemDB {

    private static final String FUELDBYJAVA_URL = "jdbc:jtds:sqlserver://cisdbss.pcc.edu/234a_FueledByJava";
    private static final String USERNAME = "234a_FueledByJava";
    private static final String PASSWORD = "avaJyBdeleuF_a432#";

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(FUELDBYJAVA_URL, USERNAME, PASSWORD);
        return connection;
    }
}