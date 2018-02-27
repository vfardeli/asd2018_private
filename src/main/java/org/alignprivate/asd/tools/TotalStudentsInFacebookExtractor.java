package org.alignprivate.asd.tools;

import org.alignprivate.asd.dal.script.TotalStudentsInFacebookDao;
import java.sql.SQLException;

public class TotalStudentsInFacebookExtractor implements PrivateToPublicExtractor<Integer> {
    private TotalStudentsInFacebookDao totalStudentsInFacebookDao;

    public TotalStudentsInFacebookExtractor() {
        totalStudentsInFacebookDao = new TotalStudentsInFacebookDao();
    }

    @Override
    public Integer extractFromPrivateAndLoadToPublic() throws SQLException {
        int totalStudents = totalStudentsInFacebookDao.getTotalStudentsFromPrivateDB();
        totalStudentsInFacebookDao.updateTotalStudentsInFacebook(totalStudents);
        return totalStudentsInFacebookDao.getTotalStudentsInFacebook();
    }
}
