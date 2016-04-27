package ResultReporting;

import java.util.List;
import java.util.Vector;

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
