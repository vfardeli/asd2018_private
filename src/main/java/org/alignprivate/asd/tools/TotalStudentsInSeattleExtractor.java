package org.alignprivate.asd.tools;

import org.alignprivate.asd.dal.script.TotalStudentsInSeattleDao;

import java.sql.SQLException;

public class TotalStudentsInSeattleExtractor implements PrivateToPublicExtractor<Integer> {
    private TotalStudentsInSeattleDao totalStudentsInSeattleDao;

    public TotalStudentsInSeattleExtractor() {
        totalStudentsInSeattleDao = new TotalStudentsInSeattleDao();
    }

    @Override
    public Integer extractFromPrivateAndLoadToPublic() throws SQLException {
        int totalStudents = totalStudentsInSeattleDao.getTotalStudentsInSeattleFromPrivateDatabase();
        totalStudentsInSeattleDao.updateTotalStudentsInSeattleInPublicDatabase(totalStudents);
        return totalStudentsInSeattleDao.getTotalStudentsInSeattleFromPublicDatabase();
    }
}
