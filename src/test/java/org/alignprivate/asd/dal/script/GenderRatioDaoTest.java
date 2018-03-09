package org.alignprivate.asd.dal.script;

import org.alignprivate.asd.dal.maintable.StudentsDao;
import org.alignprivate.asd.enumeration.*;
import org.alignprivate.asd.model.GenderRatio;
import org.alignprivate.asd.model.Students;
import org.junit.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class GenderRatioDaoTest {
  private static StudentsDao studentsDao;
  private static GenderRatioDao genderRatioDao;

  @BeforeClass
  public static void initOnce() {
    studentsDao = new StudentsDao();
    genderRatioDao = new GenderRatioDao();

    Students newStudent = new Students("0000000", "tomcat@gmail.com", "Tom", "",
            "Cat", Gender.M, "F1", "1111111111",
            "401 Terry Ave", "WA", "Seattle", "98109", Term.FALL, 2015,
            Term.SPRING, 2017,
            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS, null, true);
    Students newStudent2 = new Students("1111111", "jerrymouse@gmail.com", "Jerry", "",
            "Mouse", Gender.M, "F1", "1111111111",
            "225 Terry Ave", "WA", "Seattle", "98109", Term.FALL, 2014,
            Term.SPRING, 2016,
            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS, null, true);
    Students newStudent3 = new Students("2222222", "tomcat3@gmail.com", "Tom", "",
            "Dog", Gender.F, "F1", "1111111111",
            "401 Terry Ave", "WA", "Seattle", "98109", Term.FALL, 2015,
            Term.SPRING, 2017,
            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS, null, true);
    studentsDao.addStudent(newStudent);
    studentsDao.addStudent(newStudent2);
    studentsDao.addStudent(newStudent3);
  }

  @AfterClass
  public static void deleteDatabasePlaceholder() {
    studentsDao.deleteStudent("0000000");
    studentsDao.deleteStudent("1111111");
    studentsDao.deleteStudent("2222222");
  }

  @Test
  public void getYearlyGenderRatioFromPrivateDatabaseTest() throws SQLException {
    List<GenderRatio> seattleGenderRatio = genderRatioDao.getYearlyGenderRatioFromPrivateDatabase(Campus.SEATTLE);
    Assert.assertTrue(seattleGenderRatio.get(0).getEntryYear() == 2014);
    Assert.assertTrue(seattleGenderRatio.get(1).getEntryYear() == 2015);
    Assert.assertTrue(seattleGenderRatio.get(0).getFemale() == 0);
    Assert.assertTrue(seattleGenderRatio.get(0).getMale() == 1);
    Assert.assertTrue(seattleGenderRatio.get(1).getFemale() == 1);
    Assert.assertTrue(seattleGenderRatio.get(1).getMale() == 1);
  }

  @Test
  public void updateAndGetGenderRatioFromPublicDatabaseTest() throws SQLException {
    List<GenderRatio> seattleGenderRatio = genderRatioDao.getYearlyGenderRatioFromPrivateDatabase(Campus.SEATTLE);
    genderRatioDao.insertYearlyGenderRatioToPublicDatabase(Campus.SEATTLE, seattleGenderRatio);
    List<GenderRatio> seattleGenderRatioFromDB = genderRatioDao.getYearlyGenderRatioFromPublicDatabase(Campus.SEATTLE);
    Assert.assertTrue(seattleGenderRatioFromDB.get(0).getEntryYear() == 2014);
    Assert.assertTrue(seattleGenderRatioFromDB.get(1).getEntryYear() == 2015);
    Assert.assertTrue(seattleGenderRatioFromDB.get(0).getFemale() == 0);
    Assert.assertTrue(seattleGenderRatioFromDB.get(0).getMale() == 1);
    Assert.assertTrue(seattleGenderRatioFromDB.get(1).getFemale() == 1);
    Assert.assertTrue(seattleGenderRatioFromDB.get(1).getMale() == 1);
    List<GenderRatio> bostonGenderRatioFromDB = genderRatioDao.getYearlyGenderRatioFromPublicDatabase(Campus.BOSTON);
    Assert.assertTrue(bostonGenderRatioFromDB.isEmpty());
  }
}