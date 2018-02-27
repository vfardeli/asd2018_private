package org.alignprivate.asd.tools;

import org.alignprivate.asd.dal.script.TotalStudentsInMicrosoftDao;
import java.sql.SQLException;

public class TotalStudentsInMicrosoftExtractor implements PrivateToPublicExtractor<Integer> {
    private TotalStudentsInMicrosoftDao totalStudentsInMicrosoftDao;

    public TotalStudentsInMicrosoftExtractor() {
        totalStudentsInMicrosoftDao = new TotalStudentsInMicrosoftDao();
    }

    @Override
    public Integer extractFromPrivateAndLoadToPublic() throws SQLException {
        int totalStudents = totalStudentsInMicrosoftDao.getTotalStudentsFromPrivateDB();
        totalStudentsInMicrosoftDao.updateTotalStudentsInMicrosoft(totalStudents);
        return totalStudentsInMicrosoftDao.getTotalStudentsInMicrosoft();
    }
}
