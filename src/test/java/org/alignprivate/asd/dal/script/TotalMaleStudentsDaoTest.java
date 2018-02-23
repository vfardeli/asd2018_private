package org.alignprivate.asd.dal.script;

import org.alignprivate.asd.dal.maintable.StudentsDao;
import org.alignprivate.asd.enumeration.Campus;
import org.alignprivate.asd.enumeration.DegreeCandidacy;
import org.alignprivate.asd.enumeration.EnrollmentStatus;
import org.alignprivate.asd.enumeration.Gender;
import org.alignprivate.asd.model.Students;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TotalMaleStudentsDaoTest {
  public static final int TOTAL_MALE_STUDENTS_TEST = 5;

  TotalMaleStudentsDao totalMaleStudentsDao;
  StudentsDao studentsDao;

  @Before
  public void init() throws Exception {
    totalMaleStudentsDao = TotalMaleStudentsDao.getInstance();
    studentsDao = new StudentsDao();
  }

  @Test
  public void getTotalMaleStudentsFromPrivateDatabaseTest() throws Exception {
    int totalMaleStudents = totalMaleStudentsDao.getTotalMaleStudentsFromPrivateDatabase();
    Assert.assertTrue(totalMaleStudents == TOTAL_MALE_STUDENTS_TEST);
    Students newStudent = new Students("0000000","tomcat@gmail.com", "Tom", "",
            "Cat", Gender.M, "F1", "1111111111",
            "401 Terry Ave", "WA", "Seattle", "98109",
            EnrollmentStatus.FULL_TIME, Campus.SEATTLE, DegreeCandidacy.MASTERS,null);

    studentsDao.addStudent(newStudent);
    totalMaleStudents = totalMaleStudentsDao.getTotalMaleStudentsFromPrivateDatabase();
    Assert.assertTrue(totalMaleStudents == TOTAL_MALE_STUDENTS_TEST + 1);
    studentsDao.deleteStudent("0000000");
  }

  @Test
  public void updateTotalMaleStudentsInPublicDatabase() throws Exception {
    int totalMaleStudents = totalMaleStudentsDao.getTotalMaleStudentsFromPrivateDatabase();

    totalMaleStudentsDao.updateTotalMaleStudentsInPublicDatabase(
            TOTAL_MALE_STUDENTS_TEST + 1);
    Assert.assertTrue(totalMaleStudentsDao.getTotalMaleStudentsFromPublicDatabase()
            == TOTAL_MALE_STUDENTS_TEST + 1);

    totalMaleStudentsDao.updateTotalMaleStudentsInPublicDatabase(
            totalMaleStudents);
    Assert.assertTrue(totalMaleStudentsDao.getTotalMaleStudentsFromPublicDatabase()
            == totalMaleStudents);
  }
}