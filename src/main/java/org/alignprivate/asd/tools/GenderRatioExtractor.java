package org.alignprivate.asd.tools;

import org.alignprivate.asd.dal.script.GenderRatioDao;
import org.alignprivate.asd.enumeration.Campus;
import org.alignprivate.asd.model.GenderRatio;

import java.sql.SQLException;
import java.util.List;

public class GenderRatioExtractor {
  private GenderRatioDao genderRatioDao;

  public GenderRatioExtractor() {
    genderRatioDao = new GenderRatioDao();
  }

  public void extractFromPrivateAndLoadToPublic() throws SQLException {
    List<GenderRatio> seattleGenderRatio =
            genderRatioDao.getYearlyGenderRatioFromPrivateDatabase(Campus.SEATTLE);
    List<GenderRatio> bostonGenderRatio =
            genderRatioDao.getYearlyGenderRatioFromPrivateDatabase(Campus.BOSTON);
    List<GenderRatio> charlotteGenderRatio =
            genderRatioDao.getYearlyGenderRatioFromPrivateDatabase(Campus.CHARLOTTE);
    List<GenderRatio> siliconValleyGenderRatio =
            genderRatioDao.getYearlyGenderRatioFromPrivateDatabase(Campus.SILICON_VALLEY);
    genderRatioDao.insertYearlyGenderRatioToPublicDatabase(Campus.SEATTLE, seattleGenderRatio);
    genderRatioDao.insertYearlyGenderRatioToPublicDatabase(Campus.BOSTON, bostonGenderRatio);
    genderRatioDao.insertYearlyGenderRatioToPublicDatabase(Campus.CHARLOTTE, charlotteGenderRatio);
    genderRatioDao.insertYearlyGenderRatioToPublicDatabase(Campus.SILICON_VALLEY, siliconValleyGenderRatio);
  }
}
