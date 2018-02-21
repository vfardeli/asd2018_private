package org.alignprivate.asd.tools;

import org.alignprivate.asd.dal.script.TotalStudentsInSiliconValleyDao;

import java.sql.SQLException;

public class TotalStudentsInSiliconValleyExtractor implements PrivateToPublicExtractor<Integer> {
  private TotalStudentsInSiliconValleyDao totalStudentsInSiliconValleyDao;

  public TotalStudentsInSiliconValleyExtractor() {
    totalStudentsInSiliconValleyDao = new TotalStudentsInSiliconValleyDao();
  }

  @Override
  public Integer extractFromPrivateAndLoadToPublic() throws SQLException {
    int totalStudents = totalStudentsInSiliconValleyDao.getTotalStudentsInSiliconValleyFromPrivateDatabase();
    totalStudentsInSiliconValleyDao.updateTotalStudentsInSiliconValleyInPublicDatabase(totalStudents);
    return totalStudentsInSiliconValleyDao.getTotalStudentsInSiliconValleyFromPublicDatabase();
  }
}
