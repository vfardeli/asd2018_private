package org.alignprivate.asd.tools;

import org.alignprivate.asd.dal.script.TotalStudentsInSeattleDao;

import java.sql.SQLException;

public class TotalStudentsInSeattleExtractor implements PrivateToPublicExtractor<Integer> {
    private TotalStudentsInSeattleDao totalStudentsInSeattleDaoDao;

    public TotalStudentsInSeattleExtractor() {
        totalStudentsInSeattleDaoDao = new TotalStudentsInSeattleDao();
    }

    @Override
    public Integer extractFromPrivateAndLoadToPublic() throws SQLException {
        int totalStudents = totalStudentsInSeattleDaoDao.getTotalStudentsInSeattleFromPrivateDatabase();
        totalStudentsInSeattleDaoDao.updateTotalStudentsInSeattleInPublicDatabase(totalStudents);
        return totalStudentsInSeattleDaoDao.getTotalStudentsInSeattleFromPublicDatabase();
    }
}
