package org.alignprivate.asd.tools;

import org.alignprivate.asd.dal.script.TopFiveElectivesDao;

import java.sql.SQLException;
import java.util.List;

public class TopFiveElectivesExtractor implements PrivateToPublicExtractor<List<String>> {
  private TopFiveElectivesDao topFiveElectivesDao;

  public TopFiveElectivesExtractor() {
    topFiveElectivesDao = new TopFiveElectivesDao();
  }

  @Override
  public List<String> extractFromPrivateAndLoadToPublic() throws SQLException {
    List<String> listOfElectives = topFiveElectivesDao.getTopFiveElectivesFromPrivateDatabase();
    topFiveElectivesDao.updateTopFiveElectivesInPublicDatabase(listOfElectives);
    return topFiveElectivesDao.getTopFiveElectivesFromPublicDatabase();
  }
}
