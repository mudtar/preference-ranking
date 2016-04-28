package dbfactory;

/**
 * Data access object factory used to have access to DAO
 * @Author: David Li
 * @Version: 2016.04.14
 */
public class RSystemDAOFactory {
    public static RSystemDBAccess getReportDAO() {
        RSystemDBAccess reportDAO = new RSystemDBAccess();
        return reportDAO;
    }
}
