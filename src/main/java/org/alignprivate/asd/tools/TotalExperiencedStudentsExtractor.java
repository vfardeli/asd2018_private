package org.alignprivate.asd.tools;

import org.alignprivate.asd.dal.script.TotalExperiencedStudentsDao;

import java.sql.SQLException;

public class TotalExperiencedStudentsExtractor implements PrivateToPublicExtractor<Integer>  {
    private TotalExperiencedStudentsDao totalExperiencedStudentsDao;

    public TotalExperiencedStudentsExtractor() {
        totalExperiencedStudentsDao = new TotalExperiencedStudentsDao();
    }

    @Override
    public Integer extractFromPrivateAndLoadToPublic() throws SQLException {
        int totalStudents = totalExperiencedStudentsDao.getTotalStudentsFromPrivateDB();
        totalExperiencedStudentsDao.updateTotalExperiencedStudents(totalStudents);
        return totalExperiencedStudentsDao.getTotalExperiencedStudents();
    }
}
