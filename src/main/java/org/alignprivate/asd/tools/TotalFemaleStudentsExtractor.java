package org.alignprivate.asd.tools;

import org.alignprivate.asd.dal.script.TotalFemaleStudentsDao;

import java.sql.SQLException;

public class TotalFemaleStudentsExtractor implements PrivateToPublicExtractor<Integer> {
  private TotalFemaleStudentsDao totalFemaleStudentsDao;

  public TotalFemaleStudentsExtractor() {
    totalFemaleStudentsDao = new TotalFemaleStudentsDao();
  }

  @Override
  public Integer extractFromPrivateAndLoadToPublic() throws SQLException {
    int totalStudents = totalFemaleStudentsDao.getTotalFemaleStudentsFromPrivateDatabase();
    totalFemaleStudentsDao.updateTotalFemaleStudentsInPublicDatabase(totalStudents);
    return totalFemaleStudentsDao.getTotalFemaleStudentsFromPublicDatabase();
  }
}
