package org.alignprivate.asd.tools;

import org.alignprivate.asd.dal.script.TotalStudentsInAmazonDao;

import java.sql.SQLException;

public class TotalStudentsInAmazonExtractor implements PrivateToPublicExtractor<Integer> {
    private TotalStudentsInAmazonDao totalStudentsInAmazonDao;

    public TotalStudentsInAmazonExtractor() {
        totalStudentsInAmazonDao = new TotalStudentsInAmazonDao();
    }

    @Override
    public Integer extractFromPrivateAndLoadToPublic() throws SQLException {
        int totalStudents = totalStudentsInAmazonDao.getTotalStudentsFromPrivateDB();
        totalStudentsInAmazonDao.updateTotalStudentsInAmazon(totalStudents);
        return totalStudentsInAmazonDao.getTotalStudentsInAmazon();
    }
}
