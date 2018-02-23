package org.alignprivate.asd.tools;

import org.alignprivate.asd.dal.script.TotalMaleStudentsDao;

import java.sql.SQLException;

public class TotalMaleStudentsExtractor implements PrivateToPublicExtractor<Integer> {
  private TotalMaleStudentsDao totalMaleStudentsDao;

  public TotalMaleStudentsExtractor() {
    totalMaleStudentsDao = new TotalMaleStudentsDao();
  }

  @Override
  public Integer extractFromPrivateAndLoadToPublic() throws SQLException {
    int totalStudents = totalMaleStudentsDao.getTotalMaleStudentsFromPrivateDatabase();
    totalMaleStudentsDao.updateTotalMaleStudentsInPublicDatabase(totalStudents);
    return totalMaleStudentsDao.getTotalMaleStudentsFromPublicDatabase();
  }
}
