package org.alignprivate.asd.tools;

import org.alignprivate.asd.dal.script.TotalStudentsInGoogleDao;
import java.sql.SQLException;

public class TotalStudentsInGoogleExtractor implements PrivateToPublicExtractor<Integer> {
    private TotalStudentsInGoogleDao totalStudentsInGoogleDao;

    public TotalStudentsInGoogleExtractor() {
        totalStudentsInGoogleDao = new TotalStudentsInGoogleDao();
    }

    @Override
    public Integer extractFromPrivateAndLoadToPublic() throws SQLException {
        int totalStudents = totalStudentsInGoogleDao.getTotalStudentsFromPrivateDB();
        totalStudentsInGoogleDao.updateTotalStudentsInGoogle(totalStudents);
        return totalStudentsInGoogleDao.getTotalStudentsInGoogle();
    }
}
