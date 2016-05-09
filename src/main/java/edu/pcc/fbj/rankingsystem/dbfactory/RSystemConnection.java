package edu.pcc.fbj.rankingsystem.dbfactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Serves as a repository for the database credentials and maintains a
 * single database connection to be used throughout the application.
 *
 * @author  David Li
 * @author  Ian Burton
 * @version 2016.04.29
 */
public class RSystemConnection
{
    /**
     * the URL for the application's database
     */
    private static final String URL =
        "jdbc:jtds:sqlserver://cisdbss.pcc.edu/234a_FueledByJava";

    /**
     * the database user on whose behalf the connection is being made
     */
    private static final String USER = "234a_FueledByJava";

    /**
     * the user's password
     */
    private static final String PASSWORD = "avaJyBdeleuF_a432#";

    /**
     * the static connection to be used by all aspects of the
     * application by which to access the database
     */
    private static Connection connection;

    /**
     * Return a connection to URL. If one already has been created,
     * return it. Otherwise, create one and save it as a field.
     *
     * @return              the one and only connection to the database
     *                      used throughout the application
     * @throws SQLException if a database access error occurs or the url
     *                      is null
     */
    public static Connection getConnection() throws SQLException
    {
        if (connection == null)
        {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }

        return connection;
    }
}
