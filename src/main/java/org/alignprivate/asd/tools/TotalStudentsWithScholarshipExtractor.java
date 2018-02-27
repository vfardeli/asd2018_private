package org.alignprivate.asd.tools;

import org.alignprivate.asd.dal.script.TotalStudentsWithScholarshipDao;

import java.sql.SQLException;

public class TotalStudentsWithScholarshipExtractor implements PrivateToPublicExtractor<Integer> {
    private TotalStudentsWithScholarshipDao totalStudentsWithScholarshipDao;

    public TotalStudentsWithScholarshipExtractor() {
        totalStudentsWithScholarshipDao = new TotalStudentsWithScholarshipDao();
    }

    @Override
    public Integer extractFromPrivateAndLoadToPublic() throws SQLException {
        int totalStudents = totalStudentsWithScholarshipDao.getTotalStudentsWithScholarshipFromPrivateDatabase();
        totalStudentsWithScholarshipDao.updateTotalStudentsWithScholarshipInPublicDatabase(totalStudents);
        return totalStudentsWithScholarshipDao.getTotalStudentsWithScholarshipFromPublicDatabase();
    }
}
