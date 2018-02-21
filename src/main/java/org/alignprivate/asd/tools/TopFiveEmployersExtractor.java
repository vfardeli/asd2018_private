package org.alignprivate.asd.tools;

import org.alignprivate.asd.dal.script.TopFiveEmployersDao;

import java.sql.SQLException;
import java.util.List;

public class TopFiveEmployersExtractor implements PrivateToPublicExtractor<List<String>> {
  private TopFiveEmployersDao topFiveEmployersDao;

  public TopFiveEmployersExtractor() {
    topFiveEmployersDao = new TopFiveEmployersDao();
  }

  @Override
  public List<String> extractFromPrivateAndLoadToPublic() throws SQLException {
    List<String> listOfEmployers = topFiveEmployersDao.getTopFiveEmployersFromPrivateDatabase();
    topFiveEmployersDao.updateTopFiveEmployersInPublicDatabase(listOfEmployers);
    return topFiveEmployersDao.getTopFiveEmployersFromPublicDatabase();
  }
}
